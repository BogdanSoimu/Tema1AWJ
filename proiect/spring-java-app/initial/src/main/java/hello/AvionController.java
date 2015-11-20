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
public class AvionController {
  private List<Avion> avioane = new ArrayList<Avion>();
  private static int scounter;

  AvionController() {
    Avion p1 = new Avion(1, "Boeing 777");
    Avion p2 = new Avion(2, "AirBus");
    Avion p3 = new Avion(3, "Fokker");

    avioane.add(p1);
    avioane.add(p2);
    avioane.add(p3);
  }


  @RequestMapping(value="/avion", method = RequestMethod.GET)
  public List<Avion> index() {
    return this.avioane;
  }


  @RequestMapping(value="/avion/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Avion p : this.avioane) {
      if(p.getId() == id) {
        return new ResponseEntity<Avion>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


  @RequestMapping(value="/avion/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Avion p : this.avioane) {
      if(p.getId() == id) {
        this.avioane.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


  @RequestMapping(value="/avion/{name}", method = RequestMethod.POST)
  public ResponseEntity create(@PathVariable("name") String name) {
    scounter=avioane.size()+1;
    Avion p = new Avion(scounter, name);
    avioane.add(p);
    return new ResponseEntity<Avion>(p, new HttpHeaders(), HttpStatus.OK);
    }


  @RequestMapping(value="/avion/{id}/{name}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable("id") int id, @PathVariable("name") String name) {
    for(Avion p : this.avioane) {
      if(p.getId() == id) {
        p.setNameA(name);
        return new ResponseEntity<Avion>(null, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}
