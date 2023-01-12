package nology.io.JobAssignmentAPI.Temp;

import jakarta.validation.constraints.NotBlank;

public class TempCreateDTO {
  @NotBlank
  String firstName;

  @NotBlank
  String lastName;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
}
