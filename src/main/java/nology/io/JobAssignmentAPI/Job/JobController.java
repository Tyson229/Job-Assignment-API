package nology.io.JobAssignmentAPI.Job;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {

  @Autowired
  private JobService service;
  
  @PostMapping
  public ResponseEntity<Job> create(@Valid @RequestBody JobCreateDTO data) {

    Job createdJob = this.service.create(data);

    return new ResponseEntity<Job>(createdJob, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Job> assignTemp(@PathVariable Long id, @Valid @RequestBody JobUpdateDTO data) {
    Job updatedJob = this.service.updateJob(id, data);

    return new ResponseEntity<Job>(updatedJob, HttpStatus.OK);  
  }

  @GetMapping("/")
  public ResponseEntity<List<Job>> all() {
    List<Job> allJobs = this.service.all();

    return new ResponseEntity<>(allJobs, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Job> findOne(@PathVariable Long id) {
    Optional<Job> maybeJob = this.service.findOne(id);

    if (maybeJob.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find job");
    }

    return new ResponseEntity<Job>(maybeJob.get(), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Job>> filterByTemps(@RequestParam boolean assigned){
    List<Job> filteredJobs = this.service.filter(assigned);

    return new ResponseEntity<>(filteredJobs, HttpStatus.OK);
  }
 
}
