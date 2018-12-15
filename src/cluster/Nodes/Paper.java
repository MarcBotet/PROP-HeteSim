package cluster.Nodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Paper extends Node {
    private Conference conference;
    private ArrayList<Integer> authorsById;
    private ArrayList<String> authorsByName;
    private ArrayList<Integer> termsById;
    private ArrayList<String> termsByName;

    private static int maxId;

    public Paper(String name, int id) {
        super(name, id);
        authorsById = new ArrayList<Integer>();
        authorsByName = new ArrayList<String>();
        termsById = new ArrayList<Integer>();
        termsByName = new ArrayList<String>();

        if (maxId < id) maxId = id;

    }

    public static int getMaxId() {
        return maxId;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public void addAuthor(Author author) {
        authorsById.add(author.getId());
        authorsByName.add(author.getName());
    }

    public void addTerm(Term term) {
        termsById.add(term.getId());
        termsByName.add(term.getName());
    }

    public void removeAuthor(Author author) {
        for(int i = 0; i < authorsById.size(); i++){
            if(authorsById.get(i) == author.getId()){
                authorsById.remove(i);
                authorsByName.remove(i);
                break;
            }
        }
    }

    public void removeTerm(Term term) {
        for(int i = 0; i < termsById.size(); i++){
            if(termsById.get(i) == term.getId()){
                termsById.remove(i);
                termsByName.remove(i);
                break;
            }
        }
    }


    public Conference getConference() {
        return conference;
    }

    public HashMap<Integer, Author> getAuthorsById(HashMap<Integer,Author> authors) {
        HashMap<Integer,Author> authorsById = new HashMap<Integer,Author>();
        for(int i = 0; i < this.authorsById.size(); i++){
            int keyId = this.authorsById.get(i);
            authorsById.put(keyId, authors.get(keyId));
        }
        return authorsById;
    }

    public HashMap<String,Author> getAuthorsByName(HashMap<String,Author> authors) {
        HashMap<String,Author> authorsByName = new HashMap<String,Author>();
        for(int i = 0; i < this.authorsByName.size(); i++){
            String keyName = this.authorsByName.get(i);
            authorsByName.put(keyName, authors.get(keyName));
        }
        return authorsByName;
    }

    public HashMap<Integer, Term> getTermsById(HashMap<Integer,Term> terms) {
        HashMap<Integer,Term> termsById = new HashMap<Integer,Term>();
        for(int i = 0; i < this.termsById.size(); i++){
            int keyId = this.termsById.get(i);
            termsById.put(keyId, terms.get(keyId));
        }
        return termsById;
    }

    public HashMap<String,Term> getTermsByName(HashMap<String,Term> terms) {
        HashMap<String,Term> termsByName = new HashMap<String,Term>();
        for(int i = 0; i < this.termsByName.size(); i++){
            String keyName = this.termsByName.get(i);
            termsByName.put(keyName, terms.get(keyName));
        }
        return termsByName;
    }

    public ArrayList<Integer> getRelatedAuthors(){
        return authorsById;
    }

    public ArrayList<Integer> getRelationesTerms(){
        return termsById;
    }

    public void sortAuthors(){
        Collections.sort(authorsById);
        Collections.sort(authorsByName);
    }

    public void sortTerms(){
        Collections.sort(termsById);
        Collections.sort(termsByName);
    }

}

