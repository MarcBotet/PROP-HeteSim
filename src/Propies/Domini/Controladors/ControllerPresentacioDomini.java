package Propies.Domini.Controladors;

import Propies.Domini.Classes.Levenshtein;
import Propies.Domini.Classes.Perfil;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;


import java.util.*;

/**
 * Created by Riqui on 13/04/2016.
 */
public class ControllerPresentacioDomini {
    ArrayList<ArrayList<String> > filters;
    private ArrayList<String> setofpaths;
    private int id;
    private String cami;
    private ControllerGUI_Domini cgd;
    public ArrayList<String> getSetofPaths() {
        return setofpaths;
    }
    public ArrayList <ArrayList<String> >getFilters() {
        return filters;
    }

    public int getId() {
        return id;
    }


    
    private int findidGUI(String skt,HINController hc) {
            int id = 0;
                try {
                    Author a;
                    a = hc.getAuthor(skt);
                    id = a.getId();
                    cami = "A";
                }catch (NullPointerException ex){
                  try{
                    Paper p;
                    p = hc.getPaper(skt);
                    id = p.getId();
                    cami = "P";
                    }catch (NullPointerException ex2){
                      try{
                         Conference c;
                         c = hc.getConference(skt);
                         id = c.getId();
                         cami = "C";
                        }catch (NullPointerException ex3){
                            
                              try{
                                 if (!hc.getNameBadTerms().contains(skt)) {

                                   Term t;
                                    t = hc.getTerm(skt);
                                    id = t.getId();
                                    cami = "T";
                                } else {

                                    cgd.call_error_terms(); 
                                  
                                 }                                 
                                }catch (NullPointerException ex4){
                                  Levenshtein l = new Levenshtein();
                                  String corrected = l.correction(hc,skt);
                                  if (corrected != null) {
                                      cgd.error(corrected);

                                  } else {
                                      System.err.println("L'element no existeix.");
                                      String a = "";
                                      cgd.error(a);
                                  }
                                }
                            }

                       }

                }
            return id;

    }

    //retorna tots els strings dels camins de les cerca sense filtres
    private ArrayList<String> nofiltmakepath(String path){
        ArrayList<String> camins = new ArrayList<String>();
        if(path == "A"){

            camins.add("APA");
            camins.add("AP");
            camins.add("APC");
            camins.add("APT");

        }
        if(path == "P"){
            camins.add("PA");
            camins.add("PTP");
            camins.add("PC");
            camins.add("PT");
        }
        if(path == "C"){

            camins.add("CPA");
            camins.add("CP");
            camins.add("CPC");
            camins.add("CPT");

        }
        if(path == "T"){


            camins.add("TPA");
            camins.add("TP");
            camins.add("TPC");
            camins.add("TPT");
        }
        return camins;
    }
    //PRE: En SKT esta el nombre del elemento de lo que quieres crear un perfil.
    //POST:SKT devolvera el nombre del elemento de la pre + todos los filtros separados por comas
    public void PresentationControler(String skt,HINController hc,Boolean filt,ControllerGUI_Domini cgd,ArrayList<String> setoffiltres,Boolean andor){
        
        this.cgd = cgd;
        //this.id = findid(cami,skt,h);
        this.id = findidGUI(skt,hc);
        if(!filt){
            setofpaths = nofiltmakepath(cami);
        }
        else {
            filters = new ArrayList<ArrayList<String> >();
            //path = path + "P";
            ArrayList<String> filtaut = new ArrayList<String>();
            ArrayList<String> filtpap = new ArrayList<String>();
            ArrayList<String> filtconf = new ArrayList<String>();
            ArrayList<String> filtterm = new ArrayList<String>();
            ArrayList<String> filtany = new ArrayList<>();
            ArrayList<String> filtcontinent = new ArrayList<>();
            for(String filtrus : setoffiltres) {
                //per a cada filtre comprovem els filtres i s'afegeixen al tipus que els pertoca
                if (hc.getAuthorsByName().get(filtrus) != null) {
                    filtaut.add(filtrus);
                } else if (hc.getPapersByName().get(filtrus) != null) {
                    filtpap.add(filtrus);
                } else if (hc.getConferencesByName().get(filtrus) != null) {
                        if(!filtrus.equals(skt))filtconf.add(filtrus);
                } else if (hc.getTermsByName().get(filtrus) != null) {
                    filtterm.add(filtrus);
                } else if (hc.getConfContinent().get(filtrus) != null) {
                    for (Conference c : hc.getConfContinent().get(filtrus)) {
                        if(!c.getName().equals(skt))filtcontinent.add(c.getName());
                    }
                }else {
                    try {
                        for (Conference c : hc.getConfYear().get(Integer.parseInt(filtrus))) {
                            if(!c.getName().equals(skt))filtany.add(c.getName());
                        }
                    } catch (NumberFormatException n) {
                        System.err.println(filtrus + " no existeix.");
                    }
                }
            }
            filters.add(filtaut);
            filters.add(filtpap);
            filters.add(filtconf);
            filters.add(filtterm);
            filters.add(filtany);
            filters.add(filtcontinent);

            //STRING = ST.NEXT()
            //setofpaths = makeMarcPaths(path);
            setofpaths = nofiltmakepath(cami);
        }
        ArrayList<String> setofpaths = getSetofPaths();
        ArrayList<ArrayList<String> > filters = getFilters();
        int id = getId();
        Perfil per = new Perfil(cami,skt,id,50,setofpaths,filters,filt,cgd,andor);
        per.main(hc);
        
    }
    

}
