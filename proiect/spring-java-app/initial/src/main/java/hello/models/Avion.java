package hello;

import java.util.List;
import java.util.ArrayList;

public class Avion {
  private String name;
  private int id;

  public Avion() {}

  public Avion(int id, String name) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public int getId() {
    return this.id;
  }

  void setNameA(String name) {
    this.name = name;
  }
}
