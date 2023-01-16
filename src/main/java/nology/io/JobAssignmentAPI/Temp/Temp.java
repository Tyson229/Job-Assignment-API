package nology.io.JobAssignmentAPI.Temp;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import nology.io.JobAssignmentAPI.Job.Job;

@Entity
public class Temp {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column
  String firstName;

  @Column
  String lastName;

  @OneToMany(mappedBy = "temp")
  @JsonIgnoreProperties("temp")
  List<Job> jobs = new ArrayList<Job>();

  public Temp() {
  }

  public Temp(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public List<Job> getJobs() {
    return jobs;
  }

  public void setJobs(List<Job> jobs) {
    this.jobs = jobs;
  }

}
