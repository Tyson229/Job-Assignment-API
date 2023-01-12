package nology.io.JobAssignmentAPI.Job;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

  @GetMapping
  public Respo test() {
    return "hello";
  }
}
