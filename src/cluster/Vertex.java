package cluster;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by MarcBotet on 07/05/2016.
 */
public class Vertex implements Comparable<Vertex>, Serializable{
    private int first;      //Primer element del parell: fila
    private int second;     //Segon element del parell: columna
    private double value;   //Tercer element del parell: valor

    //Constructora de la classe: crea un nou objecte.
    //Pre: Cert.
    //Post:Retorna un nou objecte del tipus pair.
    public Vertex(int first, int second, double value){
        this.first = first;
        this.second = second;
        this.value = value;
    }

    //Pre: Cert.
    //Post: Retorna el valor de la fila del paràmetre implícit.
    public int getFirst(){
        return this.first;
    }

    //Pre: Cert.
    //Post: Retorna el valor de la columna paràmetre implícit.
    public int getSecond(){
        return this.second;
    }

    //Pre: Cert.
    //Post: Retorna el valor del paràmetre implícit.
    public double getValue() { return this.value; }

    //Pre: Cert.
    //Post: Canvia el valor de la clau del paràmetre implícit per first.
    public void setFirst(int first){
        this.first = first;
    }

    //Pre: Cert.
    //Post: Canvia el valor del valor del paràmetre implícit per valor.
    public void setSecond(int second){
        this.second = second;
    }

    //Pre: Cert.
    //Post: Canvia el valor del valor del paràmetre implícit per valor.
    public void setValue(double value){
        this.value = value;
    }

    @Override
    public int compareTo(Vertex p) {
        return Comparators.VALUE.compare(this, p);
    }

    public static class Comparators {

        public static Comparator<Vertex> VALUE = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                if (v1.getValue() < v2.getValue()) return 1;
                if (v1.getValue() > v2.getValue()) return -1;
                return 0;
            }
        };
    }

}