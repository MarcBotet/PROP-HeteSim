package Propies.Domini.Controladors.Drivers;

import java.util.*;
import cluster.Matrix;
import Propies.Domini.Classes.Hetesim;
import Propies.Persistencia.HIN;

/**
 * Created by MarcBotet on 25/04/2016.
 */

public class Hetesim_Driver {
    public static void main(String[] args) {
        HIN h = new HIN();
        Scanner scan = new Scanner(System.in);
        Matrix AB = new Matrix();
        AB.addValue(0,0,1);AB.addValue(0,1,1);
        AB.addValue(1,1,1);AB.addValue(1,2,1);AB.addValue(1,3,1);
        AB.addValue(2,3,1);
        HashMap<String,Matrix> k = new HashMap<String,Matrix>();
        k.put("AB",AB);
        System.out.println("Escriu quin cami vols? (AB o ABA)");
        String path = scan.nextLine();
        System.out.println("Tria quina fila vols: 0-1-2");
        int fila = scan.nextInt();
        Hetesim het = new Hetesim(path,k,false,h);

        NavigableMap<Double,List<Integer>> r = het.calculate(fila);

        for (double i : r.keySet()) {
            Iterator<Integer> it = r.get(i).iterator();
            System.out.print("Valor: "+i);
            while (it.hasNext()) {
                System.out.print(" ID: "+it.next());
            }
            System.out.println();
        }
    }
}
