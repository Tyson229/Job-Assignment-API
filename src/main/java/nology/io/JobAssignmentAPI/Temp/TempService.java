package nology.io.JobAssignmentAPI.Temp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import nology.io.JobAssignmentAPI.Job.Job;
import nology.io.JobAssignmentAPI.Job.JobService;

@Service
@Transactional
public class TempService {
  @Autowired
  private TempRepository repository;

  private JobService jobService;
  
  @Autowired
  public TempService(@Lazy JobService jobService) {
    this.jobService = jobService;
  }

  public Temp create(TempCreateDTO data) {
    String cleanedFirstName = data.getFirstName().trim();
    String cleanedLastName = data.getLastName().trim();

    Temp newTemp = new Temp(cleanedFirstName,cleanedLastName);

    this.repository.save(newTemp);

    return newTemp;
  }

  public List<Temp> all() {
    return this.repository.findAll();
  }

  public Optional<Temp> findOne(Long TempId) {
    return this.repository.findById(TempId);
  }

  public List<Temp> findAvailableTemps(Long jobId) {
    List<Temp> allTemps = this.all();
    Optional<Job> maybeJob = this.jobService.findOne(jobId);

    if(maybeJob.isEmpty())
      throw new RuntimeException("This job doesn't exist");
    
    Job job = maybeJob.get();
    LocalDate startDate = job.getStartDate();

    // Take all temps
    // Go through each temp
      // Go through list of jobs
      // Compare with the job
      // if no matching start date, show
      // if yes ignore

    List<Temp> availbleTemps = allTemps.stream().filter(
      temp -> temp.getJobs().stream().allMatch(j -> !j.getStartDate().isEqual(startDate))
      ).toList();

    return availbleTemps;
  }
}


