package nology.io.JobAssignmentAPI.Job;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;

public class JobCreateDTO {
  @NotBlank
  String name;

  // yyyy-MM-dd
  Date startDate;
  
  // yyyy-MM-dd
  Date endDate;
}
