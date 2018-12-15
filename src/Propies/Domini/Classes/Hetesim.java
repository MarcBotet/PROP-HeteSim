package Propies.Domini.Classes;

import java.lang.StringBuilder;
import java.util.*;

import cluster.Matrix;
import cluster.Vertex;
import Propies.Persistencia.HIN;

/**
 * Created by marc.botet on 11/04/2016.
 */
public class Hetesim {
    private Boolean evenPath,filtres;
    private String leftPath,rightPath;
    private int lengthPath;
    private HashMap<String,Matrix> k;
    private HIN graph;

    private String path;

    /*
     * Example: Path = APC (Author, Paper, Conference)
     */
    public Hetesim(String path,HashMap<String,Matrix> k,Boolean filtres,HIN h) {
        this.lengthPath = path.length();
        this.evenPath = isEvenPath();
        this.k = k;
        this.filtres = filtres;
        this.graph = h;
        this.path = path;
        decompose(path);
    }

    /*
     * És parell si l'string és senar
     */
    private Boolean isEvenPath() {
        return lengthPath % 2 == 1;
    }

    private void decompose(String path) {
        if (evenPath) decomposedEvenPath(path);
        else decomposedOddPath(path);
    }

    /*
     * Example: ABCDE--left: AB,BC, ---right: ED,DC,
     * ABCDEFG---AB,BC,CD,---GF,FE,ED,
     */
    private void decomposedEvenPath(String path){
        StringBuilder sbLeft = new StringBuilder();
        StringBuilder sbRight = new StringBuilder();
        int length = path.length();
        for (int i = 0; i < length/2; ++i) {
            sbLeft.append(path.charAt(i));
            sbLeft.append(path.charAt(i+1));
            sbLeft.append(',');
        }
        //sbLeft.deleteCharAt(sbLeft.length() - 1);
        for (int j = length-1; j > length/2; --j) {
            sbRight.append(path.charAt(j));
            sbRight.append(path.charAt(j-1));
            sbRight.append(',');
        }
        // sbRight.deleteCharAt(sbRight.length()-1);
        this.leftPath = sbLeft.toString();
        this.rightPath = sbRight.toString();
    }

    /*
     *  Example: ABCD--left: AB,B*, --right: DC,C*,
     *  ABCD---AB*CD
     */
    private void decomposedOddPath(String path) {
        StringBuffer sb = new StringBuffer();
        StringBuffer mt = new StringBuffer();
        for (int i = 0; i < lengthPath/2; ++i) sb.append(path.charAt(i));
        sb.append('*');
        for (int i = lengthPath/2; i < lengthPath; ++i) sb.append(path.charAt(i));
        String s = sb.toString();
        decomposedEvenPath(s);
        // Generar matrius intermitges
        int aux =(lengthPath/2)-1;
        mt.append(path.charAt(aux));
        mt.append(path.charAt(aux+1));
        String[] se = leftPath.split(",");
        String[] sd = rightPath.split(",");
        String left = se[se.length-1];
        String right = sd[sd.length-1];
        //Si ja les hem fet anteriorment no fa falta tornar-les a fer

        if (!filtres && graph.getMatriusIntermitges().get(mt.toString()) == null) middleType(mt.toString(),left,right,mt.toString());
        else if (!filtres) {
            k.put(left,graph.getMatriusIntermitges().get(mt.toString()).getFirst());
            k.put(right,graph.getMatriusIntermitges().get(mt.toString()).getSecond());
        } else middleType(mt.toString(),left,right,mt.toString());
    }

    private void middleType(String m,String se, String sd,String pa) {
        Matrix or = k.get(m);
        Matrix tmpL = new Matrix();
        Matrix tmpR = new Matrix();
        Set<Integer> r = or.rows();
        int e = 0;
        for (int i : r) {
            for (Vertex j : or.getRow(i)) {
                tmpL.addValue(i,e,1);
                tmpR.addValue(e,j.getSecond(),1);
                ++e;
            }
        }
        tmpR = tmpR.transpose();
        k.put(se,tmpL);
        k.put(sd,tmpR);
        if (!filtres) {
            HashMap<String, Pair<Matrix, Matrix>> mi = new HashMap<String, Pair<Matrix, Matrix>>();
            mi.put(pa, new Pair<Matrix, Matrix>(tmpL, tmpR));
            graph.setMatriusIntermitges(mi);
        }
    }

    private Matrix reduce(Matrix m, int a) {
        Matrix aux = new Matrix();
        try {
            for (Vertex i : m.getRow(a)) {
                aux.addValue(a, i.getSecond(), 1);
            }
        } catch (NullPointerException e){return aux.normalize_bin();}
        return aux.normalize_bin();
    }

    public NavigableMap<Double,List<Integer>> calculate(int a) {
        TreeMap<Double,List<Integer>> pa = new TreeMap<Double,List<Integer>>();
        String[] right = rightPath.split(",");
        Matrix ap,apr;
        String[] left = leftPath.split(",");
        int n = left.length;
        if (n == 1) ap = reduce(k.get(left[0]),a);
        else {
            Matrix m;
            m = reduce(k.get(left[0]), a);
            ap = m.multiply(k.get(left[1]));
            for (int i = 2; i < n; ++i) {
                m = k.get(left[i]);
                ap = ap.multiply(m);
            }
        }
        n = right.length;
        if (n == 1) apr = k.get(right[0]);
        else {
            Matrix m = k.get(right[0]);
            apr = m.multiply(k.get(right[1]));
            for (int i = 2; i < n; ++i) {
                m = k.get(right[i]);
                apr = apr.multiply(m);
            }
        }
        apr = apr.transpose();
        Matrix mult = ap.multiply(apr);
        double norma = ap.rowModulus(a);
        try {
            for (Vertex j : mult.getRow(a)) {
                double aux = mult.getValue(a, j.getSecond());
                if (aux != -1) {
                    double normCol = apr.columModulus(j.getSecond());
                    aux = aux / (norma * normCol);
                    List<Integer> l;
                    if (pa.get(aux) == null) {
                        l = new ArrayList<>();
                        l.add(j.getSecond());
                        pa.put(aux, l);
                    } else {
                        l = pa.get(aux);
                        l.add(j.getSecond());
                        pa.replace(aux, l);
                    }
                }
            }
        } catch (NullPointerException e) {
            return (new TreeMap<Double,List<Integer>>()).descendingMap();
        }
        return pa.descendingMap();
    }
}