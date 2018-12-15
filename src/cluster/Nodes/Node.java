package cluster.Nodes;
import java.io.Serializable;

public class Node implements Serializable {
    private int id;
    private String name;


    public Node(String name, int id) {
        this.name = name;
        this.id = id;
    }


    //Pre: Cert.
    //Post: Retorna el nom de l'objecte.
    public String getName() {
        return name;
    }


    //Pre: name es un string valid.
    //Post: Modifica el nom del parametre implicit per el valor name.
    public void setName(String name) {
        this.name = name;
    }


    //Pre: Cert.
    //Post: Retorna l'id de l'objecte.
    public int getId() {
        return id;
    }

    //Pre: Cert.
    //Post: Modifica l'indentificador del parametre implicit per el valor name.
    public void setId(int id) {
        this.id = id;
    }
}
