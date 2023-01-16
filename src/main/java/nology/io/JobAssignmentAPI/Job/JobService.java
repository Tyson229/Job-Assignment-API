package nology.io.JobAssignmentAPI.Job;

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
    String cleanedName = data.getName().trim();
    Job newJob = new Job(cleanedName, data.getStartDate(), data.getEndDate());
    
    // Fetch temp data from db
    Long tempId = data.getTemp();

    if(tempId != null){
      Temp temp = tempService.findOne(tempId).orElseThrow(() -> new RuntimeException("Temp not found"));  
      // Set new job for that temp from the data variable
      newJob.setTemp(temp);
      temp.getJobs().add(newJob);
    } else {
      this.jobRepository.save(newJob);
    }
    return newJob;
  }

  public List<Job> all() {
    return this.jobRepository.findAll();
  }

  public Optional<Job> findOne(Long JobId) {
    return this.jobRepository.findById(JobId);
  }
}
