package Propies.Domini.Controladors;

import Propies.Domini.Classes.Pair;
import Propies.Persistencia.HIN;
import cluster.*;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.util.*;

/**
 * Created by Rvila21 on 29/05/2016.
 */
public class HINController {
    HIN h = new HIN();

    public void HINController(){
        h = new HIN();
    }

    public HIN getHIN(){return h;}
    public void setHIN(HIN h){this.h = h;}

    public Author getAuthor(Integer i){return h.getAuthor(i);}
    public Author getAuthor(String s){return h.getAuthor(s);}
    public Paper getPaper(Integer i){return h.getPaper(i);}
    public Paper getPaper(String s){return h.getPaper(s);}
    public Term getTerm(Integer i){return h.getTerm(i);}
    public Term getTerm(String s){return h.getTerm(s);}
    public Conference getConference(Integer i){return h.getConference(i);}
    public Conference getConference(String s){return h.getConference(s);}
    public void addAuthor(Author a){h.addAuthor(a);}
    public void addPaper(Paper p){h.addPaper(p);}
    public void addTerm(Term t){h.addTerm(t);}
    public void addConference(Conference c){h.addConference(c);}
    public void addIdBadTerme(Integer id) {h.addIdBadTerm(id);}
    public void addYearConf(Conference c,int year) {h.addYearConf(c,year);}
    public void addContinentConf(Conference c,String conf){h.addContinentConf(c,conf);}
    public void delContinentConf(Conference c,String conf) {h.delContinentConf(c,conf);}
    public void delYearConf(Conference c,int year) {h.delYearConf(c,year);}

    public HashMap<Integer, Author> getAuthorsById(){return h.getAuthorsById();}
    public HashMap<String, Author> getAuthorsByName(){
        return h.getAuthorsByName();
    }
    public HashMap<Integer, Paper> getPapersById(){return h.getPapersById();}
    public HashMap<String, Paper> getPapersByName(){
        return h.getPapersByName();
    }
    public HashMap<Integer, Term> getTermsById(){
        return h.getTermsById();
    }
    public HashMap<String, Term> getTermsByName(){
        return h.getTermsByName();
    }
    public HashMap<Integer, Conference> getConferencesById(){return h.getConferencesById();}
    public HashMap<String, Conference> getConferencesByName(){return h.getConferencesByName();}
    public HashMap<String,Matrix> getMatrius() {
        return h.getMatrius();
    }
    public HashMap<String,Pair<Matrix,Matrix>> getMatriusIntermitges() {return h.getMatriusIntermitges();}
    public Set<Integer> getBadTerms() { return h.getBadTerms();}
    public Set<String> getNameBadTerms() { return h.getNameBadTerms();}
    public HashMap<Integer, LinkedList<Conference>> getConfYear() {return h.getConfYear();}
    public HashMap<String, LinkedList<Conference>> getConfContinent() {return h.getConfContinent();}

    public void setAuthorsById(HashMap<Integer, Author> aid){h.setAuthorsById(aid);}
    public void setAuthorsByName(HashMap<String, Author> an){
        h.setAuthorsByName(an);
    }
    public void setPapersById(HashMap<Integer, Paper>  pid){h.setPapersById(pid);}
    public void setPapersByName(HashMap<String, Paper> pn){
        h.setPapersByName(pn);
    }
    public void setTermsById(HashMap<Integer, Term> tid){h.setTermsById(tid);}
    public void setTermsByName(HashMap<String, Term> tn){h.setTermsByName(tn);}
    public void setConferencesById(HashMap<Integer, Conference> cid){
        h.setConferencesById(cid);
    }
    public void setConferencesByName(HashMap<String, Conference> cn){
        h.setConferencesByName(cn);
    }
    public void setMatrius (HashMap<String,Matrix> mx){h.setMatrius(mx);}
    public void setMatriusIntermitges(HashMap<String,Pair<Matrix,Matrix>> m){h.setMatriusIntermitges(m);}
    public void setBadTerms(Set<Integer> bt) {h.setBadTerms(bt);}
    public void setNameBadTerms(Set<String> bt){ h.setNameBadTerms(bt);}
    public void setConfYear(HashMap<Integer, LinkedList<Conference>> b) {h.getConfYear();}
    public void setConfContinent(HashMap<String, LinkedList<Conference>> b) {h.getConfContinent();}

    public void clearHIN() {
        h.clearHIN();
    }

    public void generar_matrix(){
        HashMap<String,Matrix> matrius = h.getMatrius();
        Matrix PA = new Matrix();
        Matrix PT = new Matrix();
        Matrix PC = new Matrix();
        //Montamos PX
        Set<Integer> ra = getPapersById().keySet();
        Set<Integer> badTerms = getBadTerms();
        for (int i : ra){
            Paper p1 = getPaper(i);
            //Rellenamos PA
            for(int j : p1.getRelatedAuthors()){
                PA.addValue(i,j,1);
            }
            //Rellenamos PT
            for(int k : p1.getRelationesTerms()){
                if (!h.getBadTerms().contains(k)) {
                    PT.addValue(i, k, 1);
                }
            }
            Conference c = p1.getConference();
            PC.addValue(i,c.getId(),1);
        }
        Matrix AP = PA.transpose();
        Matrix TP = PT.transpose();
        Matrix CP = PC.transpose();
        AP = AP.normalize_bin();
        TP = TP.normalize_bin();
        CP = CP.normalize_bin();
        PA = PA.normalize_bin();
        PT = PT.normalize_bin();
        PC = PC.normalize_bin();
        matrius.put("PA",PA);
        matrius.put("PT",PT);
        matrius.put("PC",PC);
        matrius.put("AP",AP);
        matrius.put("TP",TP);
        matrius.put("CP",CP);
        setMatrius(matrius);
        setMatriusIntermitges(new HashMap<>());
    }

    public void generar_badTerms() {
        Set<String> nameBT = h.getNameBadTerms();
        for (String s : nameBT) {
            Term t = getTerm(s);
            if (t != null) {
                addIdBadTerme(t.getId());
            }
        }

    }
}
