package Propies.Domini.Classes;

/**
 * Created by MarcBotet on 02/05/2016.
 */
public class Pair<K,V> {
    private K first;    //Primer element del parell: clau
    private V second;   //Segon element del parell: valor

    //Constructora de la classe: crea un nou objecte.
    //Pre: Cert.
    //Post:Retorna un nou objecte del tipus pair.
    public Pair(K first, V second){
        this.first=first;
        this.second=second;
    }

    //Pre: Cert.
    //Post: Retorna la clau del paràmetre implícit.
    public K getFirst(){
        return first;
    }

    //Pre: Cert.
    //Post: Retorna el valor del paràmetre implícit.
    public V getSecond(){
        return second;
    }

    //Pre: Cert.
    //Post: Canvia el valor de la clau del paràmetre implícit per first.
    public void setFirst(K first){
        this.first=first;
    }

    //Pre: Cert.
    //Post: Canvia el valor del valor del paràmetre implícit per valor.
    public void setSecond(V second){
        this.second=second;
    }
}
