package nology.io.JobAssignmentAPI.Job;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

  @Autowired
  private TempService tempService;

  public Job create(JobCreateDTO data) {
    if(data.getStartDate().isAfter(data.getEndDate()))
      throw new RuntimeException("Invalid date input");
    String cleanedName = data.getName().trim();
    Job newJob = new Job(cleanedName, data.getStartDate(), data.getEndDate());
    
    // Fetch temp data from db
    Long tempId = data.getTemp();

    if(tempId != null)
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
    
    // When both new dates exist
    if(startDate != null && endDate != null && startDate.isAfter(endDate))
      throw new RuntimeException("Invalid date");
    else if(startDate != null)
      job.setStartDate(startDate);
    if(endDate != null)
      job.setEndDate(endDate);

    return job;

  }

  private Job assignTemp(Job job, Long tempId) {
    Temp temp = tempService.findOne(tempId).orElseThrow(() -> new RuntimeException("Temp not found"));  
      // Set new job for that temp from the data variable
    job.setTemp(temp);
    temp.getJobs().add(job);

    return job;
  }
}
