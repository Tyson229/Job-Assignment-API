package nology.io.JobAssignmentAPI.Temp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TempService {
  @Autowired
  private TempRepository repository;
  
  public Temp create(TempCreateDTO data) {
    String cleanedFirstName = data.getFirstName().trim();
    String cleanedLastName = data.getLastName().trim();

    Temp newTemp = new Temp(cleanedFirstName,cleanedLastName);

    this.repository.save(newTemp);

    return newTemp;
  }

  public List<Temp> all() {
    return this.repository.findAll();
  }

  public Optional<Temp> findOne(Long TempId) {
    return this.repository.findById(TempId);
  }

}


