package cluster.Drivers;

import cluster.Vertex;

/**
 * Created by MarcBotet on 01/06/2016.
 */
public class Vertex_driver {
    //AQUEST DRIVER PASSA TOTES LES PROVES CORRECTAMENT
    public static void main(String[] args) {

        //Crea un objecte de la classe amb fila 1, columna 1 i valor 2.5
        Vertex p = new Vertex(1, 1, 2.5);
        System.out.println("S'ha creat un pair amb fila = 1, columna = 1 i valor = 2.5");

        //Consulta la fila de p: 1
        System.out.println("La fila ha de ser 1");
        System.out.println("Fila: " + p.getFirst());

        //Consulta la columna de p: 1
        System.out.println("La columna ha de ser 1");
        System.out.println("Columna: " + p.getSecond());

        //Consulta el valor de p: 2.5
        System.out.println("El valor ha de ser 2.5");
        System.out.println("Valor: " + p.getValue());

        //Modifica la clau de p: 2
        p.setFirst(2);
        System.out.println("La fila ha de ser 2");
        System.out.println("Fila modificada: " + p.getFirst());

        //Modifica la columna de p: 2
        p.setSecond(2);
        System.out.println("La columna ha de ser 2");
        System.out.println("Columna modificada: " + p.getSecond());

        //Modifica el valor de p: 4.12
        p.setValue(4.12);
        System.out.println("El  valor ha de ser 4.12");
        System.out.println("Valor modificat: " + p.getValue());
    }

}