package nology.io.JobAssignmentAPI.Job;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import nology.io.JobAssignmentAPI.Temp.Temp;
import nology.io.JobAssignmentAPI.Temp.TempRepository;

@Service
@Transactional
public class JobService {
  @Autowired
  private JobRepository jobRepository;


  public Job create(JobCreateDTO data) {
    String cleanedName = data.getName().trim();
    Job newJob = new Job(cleanedName, data.getStartDate(), data.getEndDate());

    return newJob;
  }

  public List<Job> all() {
    return this.jobRepository.findAll();
  }

  public Optional<Job> findOne(Long JobId) {
    return this.jobRepository.findById(JobId);
  }
}
