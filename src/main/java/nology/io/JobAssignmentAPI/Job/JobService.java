package nology.io.JobAssignmentAPI.Job;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import nology.io.JobAssignmentAPI.Temp.TempService;

@Service
@Transactional
public class JobService {
  @Autowired
  private JobRepository repository;

  public Job create(JobCreateDTO data) {
    String cleanedName = data.getName().trim();
    Job newJob = new Job(cleanedName, data.getStartDate(), data.getEndDate(), TempService.findOne(data.getTemp()).get());

    this.repository.save(newJob);

    return newJob;
  }

  public List<Job> all() {
    return this.repository.findAll();
  }

  public Optional<Job> findOne(Long JobId) {
    return this.repository.findById(JobId);
  }
}
