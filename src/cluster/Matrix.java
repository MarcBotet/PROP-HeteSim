package cluster;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

public class Matrix implements Serializable{

    private HashMap<Integer, LinkedList<Vertex>> matrix;
    private HashMap<Integer, LinkedList<Vertex>> mTrans;

    //Constructora de la classe
    //Pre: Cert
    //Post: Crea una matriu buida i inicialitza el pair
    public Matrix() {
        this.matrix = new HashMap<>();
        this.mTrans = new HashMap<>();
    }

    //Constructora privada. Intercanvia les estructures matrix i mTrans.
    private Matrix(HashMap<Integer, LinkedList<Vertex>> m, HashMap<Integer, LinkedList<Vertex>> mTrans) {
        this.matrix = m;
        this.mTrans = mTrans;
    }

    //Pre: Cert
    //Post: Retorna un vector amb les files valides
    public Set<Integer> rows() {
        return this.matrix.keySet();
    }

    //Pre: Cert
    //Post: Retorna un vector amb les columnes valides
    public Set<Integer> cols() {
        return this.mTrans.keySet();
    }

    //Pre: Existeix la fila key
    //Post: Retorna un vector amb els valors de les columnes de la fila key
    public LinkedList<Vertex> getRow(int key) {
        return this.matrix.get(key);
    }

    //Pre: Existeix la columna key
    //Post: Retorna un vector amb els valors de les files de la columna key
    private LinkedList<Vertex> getColumn(int key) {
        return this.mTrans.get(key);
    }

    //Pre: Cert
    //Post: Afegeix una fila buida (un HashMap<Integer, Double>> buit)
    private void addRow(int i, LinkedList<Vertex> l){
        this.matrix.put(i, l);
    }

    private void addRowTranspose(int j, LinkedList<Vertex> l) {
        this.mTrans.put(j, l);
    }

    //Pre: Existeix la posicio i,j
    //Post: Elimina el valor de la posicio i,j
    private void deleteValue(int i, int j) {
        try {
            this.matrix.get(i).remove(j);
            this.mTrans.get(j).remove(i);
        } catch (NullPointerException e) {
            System.out.println("No existeix la posició " + i + "," + j);
        }
    }


    //Pre: Cert
    //Post: Afegeix el valor value a la posició i,j. Afegeix una fila si es necessari.
    //      Si el valor es 0, comprova si cal eliminar la fila.
    public void addValue(int i, int j, double value){
        if (value == 0) { //Si el valor es 0 i existeix a la matriu, s'elimina.
            if (getValue(i, j) != -1) {
                deleteValue(i, j);
            }
        } else {    //Sino:
            Vertex p = new Vertex(i, j, value);
            Vertex pt = new Vertex(j, i, value);
            //Insercio a matrix:
            if (rows().contains(i)) {
                if (getRow(i).getLast().getSecond() < j) { //Si l'index de la columna és més gran que la columna, inserta al final.
                    getRow(i).addLast(p);
                } else {        //Sinó busca on ha d'insertar.
                    ListIterator<Vertex> it = getRow(i).listIterator();
                    Vertex aux = null;
                    if (it.hasNext())aux = it.next();
                    //Busca la posició on l'ha d'insertar.
                    while (aux.getSecond() < j) {
                        if (it.hasNext()) aux = it.next();
                    }
                    aux = it.previous();
                    it.add(p);
                }
            } else { //Sino crea una nova columna amb el valor.
                LinkedList<Vertex> newRow = new LinkedList<>();
                newRow.add(p);
                addRow(i, newRow);
            }

            //Insercio a mTrans:
            if (cols().contains(j)) {
                if (getColumn(j).getLast().getSecond() < i) {//Si l'index de la columna és més gran que la columna, inserta al final.
                    getColumn(j).addLast(pt);
                } else {    //Sinó busca on ha d'insertar.
                    ListIterator<Vertex> it = getColumn(j).listIterator();
                    Vertex aux = null;
                    if (it.hasNext()) aux = it.next();
                    //Busca la posició on l'ha d'insertar.
                    while (aux.getSecond() < i) {
                        if (it.hasNext()) aux = it.next();
                    }
                    aux = it.previous();
                    it.add(pt);
                }
            } else {    //Sino crea una nova columna amb el valor.
                LinkedList<Vertex> newCol = new LinkedList<>();
                newCol.add(pt);
                addRowTranspose(j, newCol);
            }
        }
    }


    //Pre: Cert.
    //Post: Retorna el valor de i, j, si existeix, sino retorna -1.
    public double getValue(int i, int j){
        if (this.matrix.containsKey(i)) {
            for (Vertex p : getRow(i)) {
                if (p.getSecond() == j) return p.getValue();
                else if (p.getSecond() > j) return -1;
            }
            return -1;
        } else return -1;
    }


    //Pre: Cert
    //Post: Retorna la matriu transposada.
    public Matrix transpose(){
        return new Matrix(this.mTrans, this.matrix);
    }


    //Pre: el paràmetre implícit té el mateix nombre de columnes que files té m
    //Post: retorna la matriu producte del paràmetre implícit amb m
    public Matrix multiply(Matrix m){
        Matrix mult = new Matrix();
        Set<Integer> r = rows();
        Set<Integer> c = m.cols();

        for (int x : r) {
            for (int y : c) {
                double value = 0;
                ListIterator<Vertex> itR = getRow(x).listIterator();
                ListIterator<Vertex> itC = m.getColumn(y).listIterator();
                Vertex p, s;
                while (itR.hasNext() && itC.hasNext()) {
                    p = itR.next();
                    while (itC.hasNext()) {
                        s = itC.next();
                        if (p.getSecond() == s.getSecond()) {
                            value += p.getValue()*s.getValue();
                            break;
                        } else if (p.getSecond() < s.getSecond()) {
                            s = itC.previous();
                            break;
                        }
                    }
                }
                mult.addValue(x, y, value);
            }
        }
        return mult;
    }


    //Pre: el paràmetre implícit té el mateix nombre de columnes que files té m
    //Post: retorna la divisó de cada cel·la de la matriu del paràmetre implícit amb m
    public Matrix dividycells(Matrix m){
        Matrix div = new Matrix();
        Set<Integer> r = rows();
        for (int x : r) {
            ListIterator<Vertex> itA = getRow(x).listIterator();
            ListIterator<Vertex> itB = m.getRow(x).listIterator();
            while (itA.hasNext() && itB.hasNext()) {
                Vertex p = itA.next();
                Vertex s = itB.next();
                if (p.getSecond() == s.getSecond()) {
                    double val1 = p.getValue();
                    double val2 = s.getValue();
                    if (val1 != -1 && val2 != -1) {
                        double value = val1 / val2;
                        div.addValue(x, p.getSecond(), value);
                    }
                } else if (p.getSecond() > s.getSecond()) {
                    if (itB.hasNext()) itB.next();
                } else if (itA.hasNext()) itA.next();
            }
        }
        return div;
    }


    //Pre: Cert
    //Post: retorna el modul de la fila i. Si no existeix, retorna 0.
    public double rowModulus(int i) {
        if (this.matrix.containsKey(i)) {
            LinkedList<Vertex> list = this.matrix.get(i);
            double m = 0;
            for (Vertex p : list) {
                m += p.getValue() * p.getValue();
            }
            return Math.sqrt(m);
        } else return 0;
    }


    //Pre: Cert
    //Post: retorna el modul de la columna j. Si no existeix, retorna 0.
    public double columModulus(int j) {
        if (this.mTrans.containsKey(j)) {
            LinkedList<Vertex> list = this.mTrans.get(j);
            double m = 0;
            for (Vertex p : list) {
                m += p.getValue() * p.getValue();
            }
            return Math.sqrt(m);
        } else return 0;
    }


    //Pre: la matriu nomes conte 1s i 0s.
    //Post: Divideix totes les files per el nombre de valors
    public Matrix normalize_bin(){
        Matrix n = new Matrix();
        for (int i : rows()) {
            double valor = 0;
            if (getRow(i) != null) valor = getRow(i).size();
            for (ListIterator<Vertex> it = getRow(i).listIterator(); it.hasNext();) {
                Vertex p = it.next();
                n.addValue(i, p.getSecond(), p.getValue()/valor);
            }
        }
        return n;
    }

    //Pre: la matriu nomes qualsevol valor
    //Post: Divideix totes les files per el nombre de valors
    public Matrix normalize(){
        Matrix n = new Matrix();
        Set<Integer> r = rows();
        for (int i : r) {
            if (getRow(i) != null) {
                double valor = getRow(i).size();
                valor = 1/Math.sqrt(valor);

                for (ListIterator<Vertex> it = getRow(i).listIterator(); it.hasNext();) {
                    Vertex p = it.next();
                    n.addValue(i, p.getSecond(), valor);
                }
            }

        }
        return n;
    }

    //Pre: Cert
    //Post: Imprimeix la matriu del parametre implicit
    public void print(){
        System.out.println("------------");
        for(int x : rows()){
            for (Vertex p : getRow(x)) {
                System.out.print(x);
                System.out.print(",");
                System.out.print(p.getSecond());
                System.out.print(":");
                System.out.println(p.getValue());
            }
        }
        System.out.println("------------");
    }

}
