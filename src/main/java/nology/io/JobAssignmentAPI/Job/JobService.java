package nology.io.JobAssignmentAPI.Job;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import nology.io.JobAssignmentAPI.Temp.Temp;
import nology.io.JobAssignmentAPI.Temp.TempService;

@Service
@Transactional
public class JobService {
  @Autowired
  private JobRepository jobRepository;

  private TempService tempService;

  @Autowired
  public JobService (TempService tempService) {
    this.tempService = tempService;
  }

  public Job create(JobCreateDTO data) {
    if (data.getStartDate().isAfter(data.getEndDate()))
      throw new RuntimeException("Invalid date input");

    String cleanedName = data.getName().trim();
    Job newJob = new Job(cleanedName, data.getStartDate(), data.getEndDate());

    // Fetch temp data from db
    Long tempId = data.getTemp();

    if (tempId != null)
      assignTemp(newJob, tempId);

    this.jobRepository.save(newJob);
    return newJob;
  }

  public List<Job> all() {
    return this.jobRepository.findAll();
  }

  public Optional<Job> findOne(Long JobId) {
    return this.jobRepository.findById(JobId);
  }

  public Job updateJob(Long JobId,JobUpdateDTO data) {

    Job job = this.findOne(JobId).orElseThrow(() -> new RuntimeException("Job not found"));

    Long tempId = data.getTemp();
    if(tempId != null)
      assignTemp(job, tempId);

    String name = data.getName();
    String cleanedName;
    if(name != null) {
      cleanedName = name.trim();
      job.setName(cleanedName);
    }

    LocalDate startDate = data.getStartDate();
    LocalDate endDate = data.getEndDate();
    switch (validateDate(startDate, endDate, job.getStartDate(), job.getEndDate())) {
      case 0:  
        throw new RuntimeException("Invalid dates"); 
        
      case 1: 
        job.setStartDate(startDate);
        job.setEndDate(endDate);
        break;
      
      case 2:
        job.setStartDate(startDate);
        break;

      case 3:
        job.setEndDate(endDate);
        break;
      
      default: break;
    }
    

  return job;

  }

  public List<Job> filter(boolean assigned) {
    List<Job> filteredJobs;
    if(assigned)
      filteredJobs = this.all().stream().filter(job -> job.getTemp() != null).collect(Collectors.toList());
    else
     filteredJobs = this.all().stream().filter(job -> job.getTemp() == null).collect(Collectors.toList());
    return filteredJobs;
  }

  private Job assignTemp(Job job, Long tempId) {
    Temp temp = tempService.findOne(tempId).orElseThrow(() -> new RuntimeException("Temp not found"));
    // Set new job for that temp from the data variable
    job.setTemp(temp);
    temp.getJobs().add(job);

    return job;
  }

  private int validateDate(LocalDate newStartDate, LocalDate newEndDate, LocalDate currentStartDate, LocalDate currentEndDate) {
    // Validate dates
    if(newStartDate != null && newEndDate != null) {
      if(newStartDate.isAfter(newEndDate))
        return 0;
      return 1;
    }
    if(newStartDate != null) {
      if(newStartDate.isAfter(currentEndDate))
        return 0;
      return 2;
    } 
    if(newEndDate != null) {
      if(newEndDate.isBefore(currentStartDate)) 
        return 0;
      return 3;
    }
    return -1;
  }
}
