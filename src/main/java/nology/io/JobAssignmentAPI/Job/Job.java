package nology.io.JobAssignmentAPI.Job;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import nology.io.JobAssignmentAPI.Temp.Temp;

@Entity
public class Job {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column
  String name;

  @Column
  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate startDate;

  @Column
  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate endDate;

  @ManyToOne
  @JoinColumn(name = "temp_id", nullable = true)
  Temp temp;

  public Job(String name, LocalDate startDate, LocalDate endDate, Temp temp) {
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    
  }

  public Job() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Temp getTemp() {
    return temp;
  }

  public void setTemp(Temp temp) {
    this.temp = temp;
  }

}
