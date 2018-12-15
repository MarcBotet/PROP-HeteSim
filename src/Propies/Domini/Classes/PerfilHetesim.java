package Propies.Domini.Classes;
import Propies.Domini.Controladors.HINController;
import cluster.*;
import Propies.Domini.Classes.*;

import java.util.*;


/**
 * Created by MarcBotet on 23/04/2016.
 */
public class PerfilHetesim {
    ArrayList<String> paths;

    public PerfilHetesim(ArrayList<String> paths) {
        this.paths = paths;
    }

    /* PRE: a és un id vàlid. k no és buit
     * POST: Retorna un arrayList amb el mateix ordre que paths. Cada posició conté
     * un map, Key: valor del hetesim. Value: llista dels id que tenen aquest valor
     */
    public ArrayList<NavigableMap<Double,List<Integer>>> calcul(int a,HashMap<String,Matrix> k,Boolean filtres,HINController hc) {
        ArrayList<NavigableMap<Double,List<Integer>>> ret = new ArrayList<NavigableMap<Double,List<Integer>>>();
        Iterator<String> it = paths.iterator();
        while (it.hasNext()) {
            String p = it.next();
            Hetesim het = new Hetesim(p,k,filtres,hc.getHIN());
            ret.add(het.calculate(a));
        }

        return ret;
    }
}
