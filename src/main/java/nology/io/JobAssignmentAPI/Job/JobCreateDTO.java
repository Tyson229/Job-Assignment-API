package nology.io.JobAssignmentAPI.Job;

import java.time.LocalDate;

//import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class JobCreateDTO {
  @NotBlank
  String name;

  LocalDate startDate;

  LocalDate endDate;

  Long temp; // Need to figure out how to assign this value to the database
  // Create a new record with data which is a foreign key from another table

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Long getTemp() {
    return temp;
  }

  public void setTemp(Long temp) {
    this.temp = temp;
  }
  
}
