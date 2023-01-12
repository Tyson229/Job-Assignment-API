package nology.io.JobAssignmentAPI.Job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
  
}
