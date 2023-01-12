package nology.io.JobAssignmentAPI.Temp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/temps")
public class TempController {
    @Autowired
    private TempService service;

    @GetMapping
    public ResponseEntity<List<Temp>> all(){
      List<Temp> allTemps = this.service.all();
      
      return new ResponseEntity<>(allTemps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Temp> findOne(@PathVariable Long id) {
      Optional<Temp> maybeTemp = this.service.findOne(id);

      if(maybeTemp.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find temp");
      }

      return new ResponseEntity<Temp>(maybeTemp.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Temp> create(@Valid @RequestBody TempCreateDTO data) {
      Temp createdTemp = this.service.create(data);

      return new ResponseEntity<Temp>(createdTemp,HttpStatus.CREATED);
    }
}
