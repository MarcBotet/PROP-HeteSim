package Propies.Domini.Classes;

import Propies.Domini.Controladors.HINController;

import java.util.Set;

/**
 * Created by MarcBotet on 18/05/2016.
 */
public class Levenshtein {

    private int distance(String s1, String s2) {
        if (s1.length() == 0) {
            return s2.length();
        }

        if (s2.length() == 0) {
            return s1.length();
        }
        if (Math.abs(s2.length()-s1.length()) > 3) return 100;

        // create two work vectors of integer distances
        int[] v0 = new int[s2.length() + 1];
        int[] v1 = new int[s2.length() + 1];
        int[] vtemp;

        // initialize v0 (the previous row of distances)
        // this row is A[0][i]: edit distance for an empty s
        // the distance is just the number of characters to delete from t
        for (int i = 0; i < v0.length; i++) {
            v0[i] = i;
        }

        for (int i = 0; i < s1.length(); i++) {
            // calculate v1 (current row distances) from the previous row v0
            // first element of v1 is A[i+1][0]
            //   edit distance is delete (i+1) chars from s to match empty t
            v1[0] = i + 1;

            // use formula to fill in the rest of the row
            for (int j = 0; j < s2.length(); j++) {
                int cost = (s1.charAt(i) == s2.charAt(j)) ? 0 : 1;
                v1[j + 1] = Math.min(
                        v1[j] + 1,              // Cost of insertion
                        Math.min(
                                v0[j + 1] + 1,  // Cost of remove
                                v0[j] + cost)); // Cost of substitution
            }
            // copy v1 (current row) to v0 (previous row) for next iteration
            //System.arraycopy(v1, 0, v0, 0, v0.length);

            // Flip references to current and previous row
            vtemp = v0;
            v0 = v1;
            v1 = vtemp;

        }

        return v0[s2.length()];
    }

    public String correction (HINController hc, String s) {
        String name = "";
        int aux,dis = 1000000000;
        Boolean b = false;
        for (int i = 0; i < 4 && !b; ++i) {
            Set<String> set;
            if (i==0) set = hc.getAuthorsByName().keySet();
            else if (i==1) set = hc.getPapersByName().keySet();
            else if (i==2) set = hc.getTermsByName().keySet();
            else set = hc.getConferencesByName().keySet();
            for (String n : set ) {
                aux = distance(n,s);
                if (aux == 1 || aux == 0) {
                    dis = aux;
                    name = n;
                    b = true;
                    break;
                }
                if (aux < dis) {
                    dis = aux;
                    name = n;
                }
            }
        }
        if(dis <= 3) return name;
        else return null;

    }

}
