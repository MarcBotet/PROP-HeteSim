package Propies.Domini.Classes;

import Propies.Domini.Controladors.HINController;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.util.*;

/**
 * Created by MarcBotet on 31/05/2016.
 */
public class Ranking {
    HINController hc;
    HashMap<Integer, LinkedList<Conference>> anys;
    HashMap<String, LinkedList<Conference>> continents;

    public Ranking(HINController hc) {
        this.hc = hc;
        this.anys = hc.getConfYear();
        this.continents = hc.getConfContinent();
    }

    private void fer(HashMap<Integer,Integer> num, int id) {
        if (num.containsKey(id)) {
            int cont = num.get(id);
            ++cont;
            num.replace(id,cont);
        } else {
            num.put(id,1);
        }
    }

    public NavigableMap<Integer,ArrayList<Author>> authorRanking(int year) {
        LinkedList<Conference> con = anys.get(year);
        if (con == null) {
            return (new TreeMap<Integer,ArrayList<Author>>()).descendingMap();
        }
        HashMap<Integer,Integer> contAuthors = new HashMap<>();
        for (Conference c : con) {
            for (Paper p : c.getExposedPapersById(hc.getPapersById()).values()) {
                for (int id : p.getRelatedAuthors()) {
                    fer(contAuthors,id);
                }

            }
        }
        TreeMap<Integer,ArrayList<Author>> au = new TreeMap<>();
        for (int i : contAuthors.keySet()) {
            int v = contAuthors.get(i);
            if (au.containsKey(v)) {
                ArrayList<Author> aux = au.get(v);
                aux.add(hc.getAuthor(i));
                au.replace(v,aux);
            } else {
                ArrayList<Author> aux = new ArrayList<>();
                aux.add(hc.getAuthor(i));
                au.put(v,aux);
            }
        }
        return au.descendingMap();
    }

    public NavigableMap<Integer,ArrayList<Author>> continentAuthorRanking(String continent) {
        LinkedList<Conference> con = continents.get(continent);
        if (con == null) {
            return (new TreeMap<Integer,ArrayList<Author>>()).descendingMap();
        }
        HashMap<Integer,Integer> contAuthors = new HashMap<>();
        for (Conference c : con) {
            for (Paper p : c.getExposedPapersById(hc.getPapersById()).values()) {
                for (int id : p.getRelatedAuthors()) {
                    fer(contAuthors,id);
                }

            }
        }
        TreeMap<Integer,ArrayList<Author>> au = new TreeMap<>();
        for (int i : contAuthors.keySet()) {
            int v = contAuthors.get(i);
            if (au.containsKey(v)) {
                ArrayList<Author> aux = au.get(v);
                aux.add(hc.getAuthor(i));
                au.replace(v,aux);
            } else {
                ArrayList<Author> aux = new ArrayList<>();
                aux.add(hc.getAuthor(i));
                au.put(v,aux);
            }
        }
        return au.descendingMap();
    }

    public NavigableMap<Integer,ArrayList<Term>> termsRanking(int year) {
        LinkedList<Conference> con = anys.get(year);
        if (con == null) {
            return (new TreeMap<Integer,ArrayList<Term>>()).descendingMap();
        }
        HashMap<Integer,Integer> contTerms = new HashMap<>();
        for (Conference c : con) {
            for (Paper p : c.getExposedPapersById(hc.getPapersById()).values()) {
                for (int id : p.getRelationesTerms()) {
                    if(!hc.getBadTerms().contains(id)) fer(contTerms,id);
                }

            }
        }
        TreeMap<Integer,ArrayList<Term>> au = new TreeMap<>();
        for (int i : contTerms.keySet()) {
            int v = contTerms.get(i);
            if (au.containsKey(v)) {
                ArrayList<Term> aux = au.get(v);
                aux.add(hc.getTerm(i));
                au.replace(v,aux);
            } else {
                ArrayList<Term> aux = new ArrayList<>();
                aux.add(hc.getTerm(i));
                au.put(v,aux);
            }
        }
        return au.descendingMap();
    }

    public NavigableMap<Integer,ArrayList<Term>> continentTermsRanking(String continent) {
        LinkedList<Conference> con = continents.get(continent);
        if (con == null) {
            return (new TreeMap<Integer,ArrayList<Term>>()).descendingMap();
        }
        HashMap<Integer,Integer> contTerms = new HashMap<>();
        for (Conference c : con) {
            for (Paper p : c.getExposedPapersById(hc.getPapersById()).values()) {
                for (int id : p.getRelationesTerms()) {
                    if(!hc.getBadTerms().contains(id)) fer(contTerms,id);
                }

            }
        }
        TreeMap<Integer,ArrayList<Term>> au = new TreeMap<>();
        for (int i : contTerms.keySet()) {
            int v = contTerms.get(i);
            if (au.containsKey(v)) {
                ArrayList<Term> aux = au.get(v);
                aux.add(hc.getTerm(i));
                au.replace(v,aux);
            } else {
                ArrayList<Term> aux = new ArrayList<>();
                aux.add(hc.getTerm(i));
                au.put(v,aux);
            }
        }
        return au.descendingMap();
    }

    public NavigableMap<Integer,ArrayList<Conference>> confRanking(int year) {
        LinkedList<Conference> con = anys.get(year);
        if (con == null) {
            return (new TreeMap<Integer,ArrayList<Conference>>()).descendingMap();
        }
        TreeMap<Integer,ArrayList<Conference>> au = new TreeMap<>();
        for (Conference c : con) {
            int v = c.getExposedPapers().size();
            if (au.containsKey(v)) {
                ArrayList<Conference> aux = au.get(v);
                aux.add(c);
                au.replace(v,aux);
            } else {
                ArrayList<Conference> aux = new ArrayList<>();
                aux.add(c);
                au.put(v,aux);
            }
        }
        return au.descendingMap();
    }

    public NavigableMap<Integer,ArrayList<Conference>> ContinentConfRanking(String continent) {
        LinkedList<Conference> con = continents.get(continent);
        if (con == null) {
            return (new TreeMap<Integer,ArrayList<Conference>>()).descendingMap();
        }
        TreeMap<Integer,ArrayList<Conference>> au = new TreeMap<>();
        for (Conference c : con) {
            int v = c.getExposedPapers().size();
            if (au.containsKey(v)) {
                ArrayList<Conference> aux = au.get(v);
                aux.add(c);
                au.replace(v,aux);
            } else {
                ArrayList<Conference> aux = new ArrayList<>();
                aux.add(c);
                au.put(v,aux);
            }
        }
        return au.descendingMap();
    }


}
