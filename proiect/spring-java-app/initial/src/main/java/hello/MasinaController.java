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
public class MasinaController {
  private List<Masina> masini = new ArrayList<Masina>();
  private static int scounter;

  MasinaController() {
    Masina p1 = new Masina(1, "BMW");
    Masina p2 = new Masina(2, "Dacia");
    Masina p3 = new Masina(3, "Porche");

    masini.add(p1);
    masini.add(p2);
    masini.add(p3);
  }


  @RequestMapping(value="/masina", method = RequestMethod.GET)
  public List<Masina> index() {
    return this.masini;
  }


  @RequestMapping(value="/masina/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Masina p : this.masini) {
      if(p.getId() == id) {
        return new ResponseEntity<Masina>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


  @RequestMapping(value="/masina/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Masina p : this.masini) {
      if(p.getId() == id) {
        this.masini.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


  @RequestMapping(value="/masina/{name}", method = RequestMethod.POST)
  public ResponseEntity create(@PathVariable("name") String name) {
    scounter=masini.size()+1;
    Masina p = new Masina(scounter, name);
    masini.add(p);
    return new ResponseEntity<Masina>(p, new HttpHeaders(), HttpStatus.OK);
  }


  @RequestMapping(value="/masina/{id}/{name}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable("id") int id, @PathVariable("name") String name) {
    for(Masina p : this.masini) {
      if(p.getId() == id) {
        p.setNameM(name);
        return new ResponseEntity<Masina>(null, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}
