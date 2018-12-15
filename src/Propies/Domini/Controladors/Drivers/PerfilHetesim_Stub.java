package Propies.Domini.Controladors.Drivers;

import cluster.Matrix;

import java.util.*;

/**
 * Created by markb on 25/04/2016.
 */
public class PerfilHetesim_Stub {
    ArrayList<String> paths;

    public PerfilHetesim_Stub(ArrayList<String> paths) {
        this.paths = paths;
    }

    private NavigableMap<Double,List<Integer>> stub() {
        TreeMap<Double,List<Integer>> ret = new TreeMap<Double,List<Integer>>();
        List<Integer> r = new ArrayList<Integer>();
        r.add(1);r.add(2);
        ret.put(0.5,r);
        r.clear();
        r.add(3);r.add(4);
        ret.put(0.75,r);
        return ret.descendingMap();
    }

    public ArrayList<NavigableMap<Double,List<Integer>>> calcul(int a, HashMap<String,Matrix> k) {
        ArrayList<NavigableMap<Double,List<Integer>>> ret = new ArrayList<NavigableMap<Double,List<Integer>>>();
        Iterator<String> it = paths.iterator();
        while (it.hasNext()) {
            String p = it.next();
            ret.add(stub());
        }

        return ret;
    }
}
