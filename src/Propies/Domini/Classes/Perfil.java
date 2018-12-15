package Propies.Domini.Classes;

import Propies.Domini.Controladors.ControllerGUI_Domini;
import Propies.Domini.Controladors.HINController;
import Propies.Persistencia.HIN;
import cluster.*;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.util.*;


/**
 * Created by Riqui on 30/03/2016.
 */
public class Perfil {
    private String path;
    private String MVPinfo;
    private int id;
    private ArrayList<String> setofpaths;
    private ArrayList<ArrayList<String> > filters;
    private ArrayList<NavigableMap<Double,List<Integer> > > perfil_info;
    private int max;
    private char c;
    private boolean tefiltres;
    private Boolean andor;
    private Scanner capt = new Scanner(System.in);
    private HIN hin = new HIN();
    private ControllerGUI_Domini cgd;


    //PRIMER ELEMENTO es el elemento del que quieres crear el perfil
    //MVPinfo es el nombre del elemento a buscar, porcentaje es la relevancia requerida, el boleano indica si es una busqueda con o sin filtros
    public Perfil(String path, String MVPinfo,int id,int max,ArrayList<String> setofpaths, ArrayList<ArrayList<String> > filters,boolean tefiltres,ControllerGUI_Domini cgd,Boolean andor) {
        this.path = path;
        this.MVPinfo = MVPinfo;
        this.id = id;
        this.max = max;
        this.setofpaths = setofpaths;
        this.filters = filters;
        this.tefiltres = tefiltres;
        this.cgd = cgd;
        this.andor = andor;
    }


    public void main(HINController hc){
        if (!tefiltres) summon_hetesim(setofpaths, id, hc.getMatrius(),hc);
        else {
            //farem els paths
            char cc;
            cc = setofpaths.get(0).charAt(0);
            ArrayList<String> camins = new ArrayList<String>();
            if (cc != 'P') {
                if (filters.get(0).isEmpty()) {
                    if (filters.get(2).isEmpty() && filters.get(4).isEmpty() && filters.get(5).isEmpty()) {
                        if (filters.get(3).isEmpty()) {
                            camins = setofpaths;
                        } else {
                            camins.add(cc + "PTPA");
                            camins.add(cc + "PTP");
                            camins.add(cc + "PTPC");
                            camins.add(cc + "PT");
                        }
                    } else {
                        if (filters.get(3).isEmpty()) {
                            camins.add(cc + "PCPA");
                            camins.add(cc + "PCP");
                            camins.add(cc + "PAPC");
                            camins.add(cc + "PCPT");
                        } else {
                            camins.add(cc + "PCPTPA");
                            camins.add(cc + "PCPTP");
                            camins.add(cc + "PTPC");
                            camins.add(cc + "PCPT");
                        }
                    }
                } else {
                    if (filters.get(2).isEmpty() && filters.get(4).isEmpty() && filters.get(5).isEmpty()) {
                        if (filters.get(3).isEmpty()) {
                            camins.add(cc + "PA");
                            camins.add(cc + "PAP");
                            camins.add(cc + "PAPC");
                            camins.add(cc + "PAPT");
                        } else {
                            camins.add(cc + "PTPA");
                            camins.add(cc + "PAPTP");
                            camins.add(cc + "PAPTPC");
                            camins.add(cc + "PAPT");
                        }
                    } else {
                        if (filters.get(3).isEmpty()) {
                            camins.add(cc + "PCPA");
                            camins.add(cc + "PAPCP");
                            camins.add(cc + "PAPC");
                            camins.add(cc + "PAPCPT");
                        } else {
                            camins.add(cc + "PCPTPA");
                            camins.add(cc + "PAPCPTP");
                            camins.add(cc + "PAPTPC");
                            camins.add(cc + "PAPCPT");
                        }
                    }
                }
            } else {
                if (filters.get(0).isEmpty()) {
                    if (filters.get(2).isEmpty() && filters.get(4).isEmpty() && filters.get(5).isEmpty()) {
                        if (filters.get(3).isEmpty()) {
                            camins = setofpaths;
                        } else {
                            camins.add(cc + "TPA");
                            camins.add(cc + "TP");
                            camins.add(cc + "TPC");
                            camins.add(cc + "T");
                        }
                    } else {
                        if (filters.get(3).isEmpty()) {
                            camins.add(cc + "CPA");
                            camins.add(cc + "CP");
                            camins.add(cc + "C");
                            camins.add(cc + "CPT");
                        } else {
                            camins.add(cc + "CPTPA");
                            camins.add(cc + "CPTP");
                            camins.add(cc + "TPC");
                            camins.add(cc + "CPT");
                        }
                    }
                } else {
                    if (filters.get(2).isEmpty() && filters.get(4).isEmpty() && filters.get(5).isEmpty()) {
                        if (filters.get(3).isEmpty()) {
                            camins.add(cc + "A");
                            camins.add(cc + "AP");
                            camins.add(cc + "APC");
                            camins.add(cc + "APT");
                        } else {
                            camins.add(cc + "TPA");
                            camins.add(cc + "APTP");
                            camins.add(cc + "APTPC");
                            camins.add(cc + "APT");
                        }
                    } else {
                        if (filters.get(3).isEmpty()) {
                            camins.add(cc + "CPA");
                            camins.add(cc + "APCP");
                            camins.add(cc + "APC");
                            camins.add(cc + "APCPT");
                        } else {
                            camins.add(cc + "CPTPA");
                            camins.add(cc + "APCPTP");
                            camins.add(cc + "APTPC");
                            camins.add(cc + "APCPT");
                        }
                    }
                }
            }
            setofpaths = camins;
            //precalculem matrix amb nomes filtres
            HashMap<String, Matrix> matriusbones = new HashMap<String, Matrix>();
            Matrix aux1 = new Matrix();
            Matrix aux2 = new Matrix();
            Matrix aux3 = new Matrix();
            ArrayList<Integer> filtrus = getpapersMVP(hc);// els papers del mvp info.
            ArrayList<String> filtaut = new ArrayList<>();
            ArrayList<String> filtpapers = new ArrayList<>();
            ArrayList<String> filtterms = new ArrayList<>();
            ArrayList<String> filtconfs = new ArrayList<>();
            ArrayList<String> filtconfspre = new ArrayList<>();
            ArrayList<String> filtconfsyears = new ArrayList<>();
            ArrayList<String> filtconfscontinents = new ArrayList<>();
            //autors
            if(!filters.get(0).isEmpty()){
                for (String ss : filters.get(0)) {
                    orfiltrus(filtaut, hc.getAuthor(ss),hc);
                }
                andfiltrus(filtrus,filtaut,hc);
            }
            //papers
            if (!filters.get(1).isEmpty()) {
                orfiltrus(filtpapers, filters.get(1));
                andfiltrus(filtrus,filtpapers,hc);
            }
            //conferencies
            if(!filters.get(2).isEmpty()){
                   filtconfspre = filters.get(2);
            }
            else{
                for(String s : hc.getConferencesByName().keySet()){
                    filtconfspre.add(s);
                }
            }

            //anys
            if(!filters.get(4).isEmpty()){

                filtconfsyears = filters.get(4);
            }
            else{
                for(String s : hc.getConferencesByName().keySet()){
                    filtconfsyears.add(s);
                }
            }

            //continents
            if(!filters.get(5).isEmpty()){
                    filtconfscontinents = filters.get(5);
            }
            else{
                for(String s : hc.getConferencesByName().keySet()){
                    filtconfscontinents.add(s);
                }
            }
            andfiltrus(filtconfsyears,filtconfscontinents);
            andfiltrus(filtconfspre,filtconfsyears);

            if(!filtconfspre.isEmpty()) {
                for (String ss : filtconfspre) {
                    orfiltrus(filtconfs, hc.getConference(ss), hc);
                }
            }
            andfiltrus(filtrus,filtconfs,hc);
            if(!filters.get(3).isEmpty()){
                for (String ss : filters.get(3)) {
                    orfiltrus(filtterms, hc.getTerm(ss),hc);
                }
                andfiltrus(filtrus,filtterms,hc);
            }
            else{
                for(String s : hc.getConferencesByName().keySet()){
                    filtterms.add(s);
                }
            }

            //ara tenim un ArrayList amb nomes els papers que farem servir, s'ha fet un and entre tots els possibles
            //montarem es matrius
            for(int pp : filtrus){
                Paper pap = hc.getPapersById().get(pp);
                for(int aa:pap.getRelatedAuthors()){
                    aux1.addValue(pp,aa,1.0);
                }
                aux3.addValue(pp,pap.getConference().getId(),1.0);
                for(int tt:pap.getRelationesTerms()){
                    if(!hc.getBadTerms().contains(tt))aux2.addValue(pp,tt,1.0);
                }
            }
            Matrix AP = aux1.transpose();
            Matrix TP = aux2.transpose();
            Matrix CP = aux3.transpose();
            aux1 = aux1.normalize_bin();
            aux2 = aux2.normalize_bin();
            aux3 = aux3.normalize_bin();
            AP = AP.normalize_bin();
            TP = TP.normalize_bin();
            CP = CP.normalize_bin();
            matriusbones.put("PA", aux1);
            matriusbones.put("PT", aux2);
            matriusbones.put("PC", aux3);
            matriusbones.put("AP", AP);
            matriusbones.put("TP", TP);
            matriusbones.put("CP", CP);
            summon_hetesim(setofpaths, id, matriusbones,hc);
            AP.print();
        }
            //make_profiles2(h);
            //ControllerGUI_Domini presentacio = new ControllerGUI_Domini();
            cgd.make_profilesGUI(hc, perfil_info,MVPinfo,path,setofpaths);
    }


    private ArrayList<Integer> getpapersMVP(HINController hc) {
        ArrayList<Integer> ret = new ArrayList<>();
        try {
            Author a;
            a = hc.getAuthor(MVPinfo);
            for(int n : a.getAuthorRelations()){
                ret.add(n);
            }
        } catch (NullPointerException ex) {
            try {
                Paper p;
                p = hc.getPaper(MVPinfo);
                //suposem que sempre hi ha minim un autor
                for(int m : hc.getAuthorsById().get(p.getRelatedAuthors().get(0)).getAuthorRelations()) {
                    ret.add(m);
                }
                for(int aa : p.getRelatedAuthors()){
                    andfiltrus(ret,hc.getAuthor(aa));
                }
                andfiltrus(ret,p.getConference());
                for(int tt : p.getRelationesTerms()){
                    andfiltrus(ret,hc.getTermsById().get(tt));
                }
            } catch (NullPointerException ex2) {
                try {
                    Conference c;
                    c = hc.getConference(MVPinfo);
                    for(int o : c.getExposedPapers()){
                        ret.add(o);
                    }
                } catch (NullPointerException ex3) {
                    try {
                        Term t;
                        t = hc.getTerm(MVPinfo);
                        for(int q : t.getPapersWhichTalkAboutThis()){
                            ret.add(q);
                        }
                    } catch (NullPointerException ex4) {

                    }
                }

            }
        }
        return ret;
    }

    private void andfiltrus(ArrayList<Integer> filtrus, Author a){
        ArrayList<Integer> aux = (ArrayList<Integer>) filtrus.clone();
        ArrayList<Integer> aaux = a.getAuthorRelations();
        for(int n : aux){
            if(!aaux.contains(n)) filtrus.remove(Integer.valueOf(n));
        }
    }
    private void andfiltrus(ArrayList<Integer> filtrus,ArrayList<String> pname, HINController hc){
        ArrayList<Integer> aux = (ArrayList<Integer>) filtrus.clone();
        for(int pp:aux){
            String n = hc.getPaper(pp).getName();
            if(!pname.contains(n)){

                filtrus.remove(Integer.valueOf(pp));
            }
        }
    }
    private void andfiltrus(ArrayList<String> filtrus,ArrayList<String> filtrus2){
        ArrayList<String> aux = (ArrayList<String>) filtrus.clone();
        for(String ss:aux){
            if(!filtrus2.contains(ss)){
                filtrus.remove(ss);
            }
        }
    }
    private void andfiltrus(ArrayList<Integer> filtrus,Conference c){
        ArrayList<Integer> aux = (ArrayList<Integer>) filtrus.clone();
        ArrayList<Integer> caux = c.getExposedPapers();
        for(int n : aux){
            if(!caux.contains(n)) filtrus.remove(Integer.valueOf(n));
        }
    }
    private void andfiltrus(ArrayList<Integer> filtrus,Term t){
        ArrayList<Integer> aux = (ArrayList<Integer>) filtrus.clone();
        ArrayList<Integer> taux = t.getPapersWhichTalkAboutThis();
        for(int n : aux){
            if(!taux.contains(n)) filtrus.remove(Integer.valueOf(n));
        }
    }

    private void orfiltrus(ArrayList<String> filtrus2, Author a, HINController hc){
        for(String n : a.getPapersByName(hc.getPapersById()).keySet()){
            if(!filtrus2.contains(n)) filtrus2.add(n);
        }
    }
    private void orfiltrus(ArrayList<String> filtrus2,ArrayList<String> pname){
        for(String n:pname){
            if(!filtrus2.contains(n))filtrus2.add(n);
        }
    }
    private void orfiltrus(ArrayList<String> filtrus2,Conference c,HINController hc){
        for(String n : c.getExposedPapersByName(hc.getPapersByName()).keySet()){
            if(!filtrus2.contains(n)) filtrus2.add(n);
        }
    }
    private void orfiltrus(ArrayList<String> filtrus2,Term t,HINController hc){
        for(String n : t.getPapersWhichTalkAboutThisByName(hc.getPapersById()).keySet()){
            if(!filtrus2.contains(n)) filtrus2.add(n);
        }
    }

    private void summon_hetesim(ArrayList<String> setOfPaths,int id,HashMap<String,Matrix> k,HINController hc){
        PerfilHetesim phet = new PerfilHetesim(setOfPaths);
        /*
         * k es el hash que toqui (depen de si hi ha filtres o no
         */
        this.perfil_info = phet.calcul(id,k,tefiltres,hc);
    }


    private boolean iguals(String s1, String s2){
        int n1 = s1.length();
        int n2 = s2.length();
        if(n1 != n2)return false;
        for(int i=0;i<n1;++i){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(c1 != c2)return false;
        }
        return true;
    }

}
