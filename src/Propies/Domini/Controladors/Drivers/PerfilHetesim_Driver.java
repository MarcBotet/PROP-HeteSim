package Propies.Domini.Controladors.Drivers;

import cluster.Matrix;

import java.util.*;

/**
 * Created by MarcBotet on 25/04/2016.
 */
public class PerfilHetesim_Driver {
    public static void main(String[] args) {
        ArrayList<String> paths = new ArrayList<String>();
        paths.add("APA");paths.add("AP");paths.add("APC");paths.add("APT");
        PerfilHetesim_Stub phet = new PerfilHetesim_Stub(paths);
        HashMap<String, Matrix> k = new HashMap<String, Matrix>();
        ArrayList<NavigableMap<Double,List<Integer>>> result = phet.calcul(0,k);
        for (int i = 0; i < 4; ++i) {
            NavigableMap<Double,List<Integer>> aux = result.get(i);
            for (Double j : aux.keySet()) {
                System.out.print(" Key: "+j);
                Iterator<Integer> it = aux.get(j).iterator();
                while (it.hasNext()) System.out.print(" "+it.next());
            }
            System.out.println();
        }

    }


}
