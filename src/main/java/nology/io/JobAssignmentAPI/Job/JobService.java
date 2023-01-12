package nology.io.JobAssignmentAPI.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService {
  @Autowired
  private JobRepository repository;

  public Job createJob(JobCreateDTO data) {
    
  }
}
