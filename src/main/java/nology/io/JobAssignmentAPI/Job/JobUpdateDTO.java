package nology.io.JobAssignmentAPI.Job;

import java.time.LocalDate;

//import io.micrometer.common.lang.Nullable;
import jakarta.annotation.Nullable;


public class JobUpdateDTO {
  @Nullable
  String name;
  
  @Nullable
  LocalDate startDate;

  @Nullable
  LocalDate endDate;

  @Nullable
  Long temp;

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
