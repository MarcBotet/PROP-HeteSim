package cluster.Drivers;

/**
 * Created by MarcBotet on 21/04/2016.
 */


import cluster.*;

import java.util.*;

public class Matrix_driver {
    //<--AQUEST DRIVER PASSA TOTES LES PROVES CORRECTAMENT-->
    private static void escriu_matriu(Matrix m) {
        for (int i : m.rows()) {
            for (ListIterator<Vertex> it = m.getRow(i).listIterator(); it.hasNext();) {
                Vertex p = it.next();
                System.out.println(" Fila " + i + ", columna " + p.getSecond() + ", valor: " + p.getValue());
            }
        }
    }

    public static void main(String[] args) {

        //Crea un objecte de la classe matrix:
        Matrix m = new Matrix();
        System.out.println("Matriu buida creada");

        //Afegim valors per crear una matriu 3x2
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                double valor = y % 2;
                m.addValue(x, y, valor);
                System.out.println("Afegit el valor " + valor + " a la posicio " + x + "," + y);
            }
        }
        System.out.println("Valors afegits correctament");
        escriu_matriu(m);
        System.out.println("-----Matriu transposada-----");
        escriu_matriu(m.transpose());

        //Vector amb columnes vàlides de la fila 1:
        LinkedList<Vertex> list = m.getRow(1);
        System.out.println("Columnes vàlides de la fila 1 amb els seus valors");
        ListIterator<Vertex> it = list.listIterator();
        while (it.hasNext()) {
            Vertex p = it.next();
            System.out.println("Columna: " + p.getSecond() + " Valor: " + p.getValue());
        }


        //Imprimeix les files vàlides (0, 1, 2) i el seu mòdul.
        Set<Integer> files = m.rows();
        System.out.println("Files vàlides: ");
        for (int x : files) {
            System.out.println("Fila: " + x + " Mòdul: " + m.rowModulus(x));
        }

        //Imprimeix les columnes vàlides (0 i 1) i el seu mòdul.
        Set<Integer> columnes = m.cols();
        System.out.printf("Columnes vàlides: ");
        for (int x : columnes) {
            System.out.println("Columna: " + x + " Mòdul: " + m.columModulus(x));
        }

        //Transposa la matriu m.
        Matrix trans = m.transpose();
        System.out.println("Matriu transposada: ");
        escriu_matriu(trans);

        //Multiplica m x m.
        Matrix mult = m.multiply(trans);
        System.out.println("Matriu multiplicació: ");
        escriu_matriu(mult);

        //Normalitza la matriu mult.
        Matrix norm = mult.normalize_bin();
        System.out.println("Matriu multiplicació normalitzada: ");
        escriu_matriu(norm);

        System.out.println();
        System.out.println("Metode print:");
        m.print();

    }

}
