package Propies.Persistencia;
import java.io.Serializable;
import java.util.HashMap;

import Propies.Domini.Classes.Pair;
import cluster.*;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.util.*;

/**
 * Created by Rvila21 on 20/04/2016.
 */

public class HIN implements Serializable{
    private HashMap<Integer, Author> authorsById;
    private HashMap<Integer, Paper> papersById;
    private HashMap<Integer, Conference> conferencesById;
    private HashMap<Integer, Term> termsById;
    private HashMap<String, Author> authorsByName;
    private HashMap<String, Paper> papersByName;
    private HashMap<String, Conference> conferencesByName;
    private HashMap<String, Term> termsByName;
    private HashMap<String,Matrix> matrius;
    private HashMap<String,Pair<Matrix,Matrix>> matriusIntermitges; //AP: first A* second P*
    private Set<String> nameBadTerms;
    private Set<Integer> badTerms;
    private HashMap<Integer,LinkedList<Conference>> confYear;

    public HashMap<String, LinkedList<Conference>> getConfContinent() {
        return confContinent;
    }

    public void setConfContinent(HashMap<String, LinkedList<Conference>> confContinent) {
        this.confContinent = confContinent;
    }

    private HashMap<String,LinkedList<Conference>> confContinent;

    public HashMap<Integer, LinkedList<Conference>> getConfYear() {
        return confYear;
    }

    public void setConfYear(HashMap<Integer, LinkedList<Conference>> confYear) {
        this.confYear = confYear;
    }

    public HIN() {
        authorsById = new HashMap<Integer, Author>();
        papersById = new HashMap<Integer, Paper>();
        conferencesById = new HashMap<Integer, Conference>();
        termsById = new HashMap<Integer, Term>();
        authorsByName = new HashMap<String, Author>();
        papersByName = new HashMap<String, Paper>();
        conferencesByName = new HashMap<String, Conference>();
        termsByName = new HashMap<String, Term>();
        matrius = new HashMap<String,Matrix>();
        matriusIntermitges = new HashMap<String,Pair<Matrix,Matrix>>();
        badTerms = new HashSet<Integer>();
        nameBadTerms = new HashSet<String>();
        confYear = new HashMap<Integer,LinkedList<Conference>>();
        confContinent = new HashMap<String,LinkedList<Conference>>();

    }

    public HashMap<Integer, Author> getAuthorsById(){return new HashMap<Integer,Author>(this.authorsById);}
    public HashMap<String, Author> getAuthorsByName(){
        return new HashMap<String,Author>(this.authorsByName);
    }
    public HashMap<Integer, Paper> getPapersById(){return (HashMap<Integer,Paper>)this.papersById.clone();}
    public HashMap<String, Paper> getPapersByName(){
        return (HashMap<String,Paper>)this.papersByName.clone();
    }
    public HashMap<Integer, Term> getTermsById(){
        return (HashMap<Integer,Term>)this.termsById.clone();
    }
    public HashMap<String, Term> getTermsByName(){
        return (HashMap<String,Term>)this.termsByName.clone();
    }
    public HashMap<Integer, Conference> getConferencesById(){return (HashMap<Integer,Conference>)this.conferencesById.clone();}
    public HashMap<String, Conference> getConferencesByName(){return (HashMap<String,Conference>)this.conferencesByName.clone();}
    public HashMap<String,Matrix> getMatrius() {
        return (HashMap<String,Matrix>)this.matrius.clone();
    }
    public HashMap<String,Pair<Matrix,Matrix>> getMatriusIntermitges() {return (HashMap<String,Pair<Matrix,Matrix>>)this.matriusIntermitges.clone();}
    public Set<Integer> getBadTerms() {
        return badTerms;
    }

    public Set<String> getNameBadTerms() {
        return nameBadTerms;
    }

    public void setNameBadTerms(Set<String> nameBadTerms) {
        this.nameBadTerms = nameBadTerms;
    }

    public void setAuthorsById(HashMap<Integer, Author> aid){
        this.authorsById = aid;
    }
    public void setAuthorsByName(HashMap<String, Author> an){
        this.authorsByName = an;
    }
    public void setPapersById(HashMap<Integer, Paper>  pid){this.papersById = pid;}
    public void setPapersByName(HashMap<String, Paper> pn){
        this.papersByName = pn;
    }
    public void setTermsById(HashMap<Integer, Term> tid){
        this.termsById = tid;
    }
    public void setTermsByName(HashMap<String, Term> tn){
        this.termsByName = tn;
    }
    public void setConferencesById(HashMap<Integer, Conference> cid){
        this.conferencesById = cid;
    }
    public void setConferencesByName(HashMap<String, Conference> cn){
        this.conferencesByName = cn;
    }
    public void setMatrius (HashMap<String,Matrix> mx){this.matrius = mx;}
    public void setMatriusIntermitges(HashMap<String,Pair<Matrix,Matrix>> m){this.matriusIntermitges = m;}

    public void setBadTerms(Set<Integer> badTerms) {
        this.badTerms = badTerms;
    }

    public Author getAuthor(int i){return authorsById.get(i);}
    public Author getAuthor(String s){return authorsByName.get(s);}
    public Paper getPaper(Integer i){return papersById.get(i);}
    public Paper getPaper(String s){return papersByName.get(s);}
    public Term getTerm (Integer i){return termsById.get(i);}
    public Term getTerm(String s){return termsByName.get(s);}
    public Conference getConference (Integer i){return conferencesById.get(i);}
    public Conference getConference (String s){return conferencesByName.get(s);}
    public void addAuthor(Author a){
        authorsById.put(a.getId(),a);
        authorsByName.put(a.getName(),a);
    }
    public void addPaper(Paper p){
        papersById.put(p.getId(),p);
        papersByName.put(p.getName(),p);
    }
    public void addTerm(Term t){
        termsById.put(t.getId(),t);
        termsByName.put(t.getName(),t);
    }
    public void addConference(Conference c){
        conferencesById.put(c.getId(),c);
        conferencesByName.put(c.getName(),c);
    }

    public void addIdBadTerm(int id) {
        badTerms.add(id);
    }

    public void delYearConf(Conference c,int year) {
        if (confYear.containsKey(year)) {
            LinkedList<Conference> aux = confYear.get(year);
            aux.remove(c);
            confYear.replace(year, aux);
            if (aux.isEmpty()) confYear.remove(year);
        }
    }

    public void delContinentConf(Conference c,String continent) {
        if (confContinent.containsKey(continent)) {
            LinkedList<Conference> aux = confContinent.get(continent);
            aux.remove(c);
            confContinent.replace(continent, aux);
            if (aux.isEmpty()) confContinent.remove(continent);
        }
    }


    public void addYearConf(Conference c,int year) {
        if (confYear.containsKey(year)) {
            LinkedList<Conference> aux = confYear.get(year);
            aux.add(c);
            confYear.replace(year,aux);
        } else {
            LinkedList<Conference> aux = new LinkedList<>();
            aux.add(c);
            confYear.put(year,aux);
        }
    }

    public void addContinentConf(Conference c,String continent) {
        if (confContinent.containsKey(continent)) {
            LinkedList<Conference> aux = confContinent.get(continent);
            aux.add(c);
            confContinent.replace(continent,aux);
        } else {
            LinkedList<Conference> aux = new LinkedList<>();
            aux.add(c);
            confContinent.put(continent,aux);
        }
    }

    public void clearHIN() {
        authorsById = new HashMap<Integer, Author>();
        papersById = new HashMap<Integer, Paper>();
        conferencesById = new HashMap<Integer, Conference>();
        termsById = new HashMap<Integer, Term>();
        authorsByName = new HashMap<String, Author>();
        papersByName = new HashMap<String, Paper>();
        conferencesByName = new HashMap<String, Conference>();
        termsByName = new HashMap<String, Term>();
        matrius = new HashMap<String,Matrix>();
        matriusIntermitges = new HashMap<String,Pair<Matrix,Matrix>>();
        badTerms = new HashSet<Integer>();
        nameBadTerms = new HashSet<String>();
    }
}
