package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StudentController {
  private List<Student> studenti = new ArrayList<Student>();
  private static int scounter;

  StudentController() {
    Student p1 = new Student(1, "Andrei");
    Student p2 = new Student(2, "Bogdan");
    Student p3 = new Student(3, "Dan");

    studenti.add(p1);
    studenti.add(p2);
    studenti.add(p3);
  }

  @RequestMapping(value="/student", method = RequestMethod.GET)
  public List<Student> index() {
    return this.studenti;
  }

  @RequestMapping(value="/student/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Student p : this.studenti) {
      if(p.getId() == id) {
        return new ResponseEntity<Student>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


  @RequestMapping(value="/student/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Student p : this.studenti) {
      if(p.getId() == id) {
        this.studenti.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


   @RequestMapping(value="/student/{name}", method = RequestMethod.POST)
  public ResponseEntity create(@PathVariable("name") String name) {
    scounter=studenti.size()+1;
    Student p = new Student(scounter, name);
    studenti.add(p);
    return new ResponseEntity<Student>(p, new HttpHeaders(), HttpStatus.OK);
  }


  @RequestMapping(value="/student/{id}/{name}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable("id") int id, @PathVariable("name") String name) {
    for(Student p : this.studenti) {
      if(p.getId() == id) {
        p.setNameStud(name);
        return new ResponseEntity<Student>(null, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}
