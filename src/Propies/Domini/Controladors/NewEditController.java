package Propies.Domini.Controladors;

import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.util.*;

/**
 * Created by MarcBotet on 26/05/2016.
 */
public class NewEditController {
    private HashMap<Integer, Author> authorsById;
    private HashMap<Integer, Paper> papersById;
    private HashMap<Integer, Conference> conferencesById;
    private HashMap<Integer, Term> termsById;
    private HashMap<String, Author> authorsByName;
    private HashMap<String, Paper> papersByName;
    private HashMap<String, Conference> conferencesByName;
    private HashMap<String, Term> termsByName;
    private HashMap<Integer, LinkedList<Conference>> anyConf;
    private HINController hc;


    public NewEditController(HINController hc) {
        authorsById = hc.getAuthorsById();
        papersById = hc.getPapersById();
        conferencesById = hc.getConferencesById();
        termsById = hc.getTermsById();
        authorsByName = hc.getAuthorsByName();
        papersByName = hc.getPapersByName();
        conferencesByName = hc.getConferencesByName();
        termsByName = hc.getTermsByName();
        anyConf = hc.getConfYear();
        this.hc = hc;

    }

    public void confirmChange() {
        hc.setAuthorsById(authorsById);
        hc.setPapersById(papersById);
        hc.setConferencesById(conferencesById);
        hc.setTermsById(termsById);
        hc.setAuthorsByName(authorsByName);
        hc.setPapersByName(papersByName);
        hc.setConferencesByName(conferencesByName);
        hc.setTermsByName(termsByName);
        hc.setConfYear(anyConf);
        hc.generar_matrix();
    }

    private List<Paper> checkPapers(ArrayList<String> papers) {
        List<Paper> save = new ArrayList<>();
        for (String s : papers) {
            Paper p = papersByName.get(s);
            if (p != null) {
                save.add(p);
            } else return new ArrayList<>();
        }
        return save;
    }

    // np = nom paper, na = nom autor
    public int addAuthor(String na, ArrayList<String> papers) {
        Author a = authorsByName.get(na);
        if (a != null) {
            System.err.println("Aquest autor ja existeix");
            return -1;
        }
        List<Paper> save = checkPapers(papers);
        if (save.isEmpty()) {
            System.err.println("Aquest article no existeix");;
            return -2;
        }
        a = new Author(na, Author.getMaxId() + 1);
        // només s'afegeix si tots els papers existeixen
        for (Paper p : save) {
            a.addPaper(p);
            p.addAuthor(a);
            papersById.put(p.getId(),p);
            papersByName.put(p.getName(),p);
        }
        authorsByName.put(a.getName(), a);
        authorsById.put(a.getId(), a);
        return 0;
    }

    public int addPaper(String namePaper, ArrayList<String> autors, String nameConf, ArrayList<String> terms) {
        if (papersByName.get(namePaper) != null) {
            System.out.println("Aquest article ja existeix");
            return -1;
        }
        Paper p = new Paper(namePaper, Paper.getMaxId() + 1);
        nameConf.concat(",");
        String conf[] = nameConf.split(",");
        Conference c = conferencesByName.get(conf[0]);
        if (c == null) {
            c = new Conference(nameConf, Conference.getMaxId() + 1);
            try {
                hc.addContinentConf(c, conf[2]);
                c.setContinent(conf[2]);
            } catch (ArrayIndexOutOfBoundsException e) {
                hc.addContinentConf(c,"Europe");
                c.setContinent("Europe");
            }
            try {
                hc.addYearConf(c, Integer.parseInt(conf[1]));
                c.setYear(Integer.parseInt(conf[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                hc.addYearConf(c, 2016);
                c.setYear(2016);
            }
        }
        p.setConference(c);
        c.addExposedPaper(p);
        conferencesByName.put(c.getName(), c);
        conferencesById.put(c.getId(), c);

        for (String s : autors) {
            Author a = authorsByName.get(s);
            if (a == null) a = new Author(s,Author.getMaxId()+1);
            a.addPaper(p);
            p.addAuthor(a);
            authorsByName.put(s,a);
            authorsById.put(a.getId(),a);
        }

        for (String s : terms) {
            Term t = termsByName.get(s);
            if (t == null) t = new Term(s,Term.getMaxId()+1);
            t.addPaperWhichTalkAboutIt(p);
            p.addTerm(t);
            termsByName.put(s,t);
            termsById.put(t.getId(),t);
        }
        papersByName.put(p.getName(), p);
        papersById.put(p.getId(), p);
        return 0;
    }

    public int addTerm(String n, ArrayList<String> papers) {
        Term t = termsByName.get(n);
        if (t != null) {
            System.err.println("Aquest terme ja existeix");
            return -5;
        }
        List<Paper> save = checkPapers(papers);
        if (save.isEmpty()) {
            System.err.println("Aquest article no existeix");
            return -2;
        }
        t = new Term(n, Term.getMaxId() + 1);
        // només s'afegeix si tots els papers existeixen
        for (Paper p : save) {
            t.addPaperWhichTalkAboutIt(p);
            p.addTerm(t);
            papersById.put(p.getId(),p);
            papersByName.put(p.getName(),p);
        }
        termsByName.put(t.getName(), t);
        termsById.put(t.getId(), t);

        return 0;
    }

    public int addConf(String n, ArrayList<String> papers) {
        n.concat(",");
        String[] conf = n.split(",");
        Conference c = conferencesByName.get(conf[0]);
        if (c != null) {
            System.err.println("Aquesta conferència ja existeix");
            return -3;
        }
        List<Paper> save = checkPapers(papers);
        if (save.isEmpty()) {
            System.err.println("Aquest article no existeix");
            return -2;
        }
        c = new Conference(n, Conference.getMaxId() + 1);
        /* només s'afegeix si tots els papers existeixen
         * S'esborra el paper de la conf que estava abans
         */
        for (Paper p : save) {
            c.addExposedPaper(p);
            Conference old = p.getConference();
            p.setConference(c);
            old.removeExposedPaperBy(p);
            conferencesByName.put(old.getName(),old);
            conferencesById.put(old.getId(),old);
            papersById.put(p.getId(),p);
            papersByName.put(p.getName(),p);
        }
        try {
            hc.addContinentConf(c, conf[2]);
            c.setContinent(conf[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            hc.addContinentConf(c,"Europe");
            c.setContinent("Europe");
        }
        try {
            hc.addYearConf(c, Integer.parseInt(conf[1]));
            c.setYear(Integer.parseInt(conf[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            hc.addYearConf(c, 2016);
            c.setYear(2016);
        }
        conferencesByName.put(c.getName(), c);
        conferencesById.put(c.getId(), c);

        return 0;
    }

    public int editName(String oldName, String newName) {
        int ret,id;
        if (authorsByName.get(oldName) != null) {
            if (authorsByName.get(newName) != null) {
                System.out.println("Aquest element ja existeix");
                return -1;
            }
            Author a;
            a = hc.getAuthorsByName().get(oldName);
            id = a.getId();
            a.setName(newName);
            authorsById.replace(id,a);
            authorsByName.remove(oldName);
            authorsByName.put(newName,a);
            ret = 0;
        } else if (papersByName.get(oldName) != null) {
            if (papersByName.get(newName) != null) {
                System.out.println("Aquest element ja existeix");
                return -1;
            }
            Paper p;
            p = hc.getPapersByName().get(oldName);
            id = p.getId();
            p.setName(newName);
            papersById.replace(id,p);
            papersByName.remove(oldName);
            papersByName.put(newName,p);
            ret = 0;
        } else if (termsByName.get(oldName) != null) {
            if (termsByName.get(newName) != null) {
                System.out.println("Aquest element ja existeix");
                return -1;
            }
            Term t;
            t = hc.getTermsByName().get(oldName);
            id = t.getId();
            termsById.replace(id,t);
            termsByName.remove(oldName);
            termsByName.put(newName,t);
            ret = 0;
        } else if (conferencesByName.get(oldName) != null) {
            if (conferencesByName.get(newName) != null) {
                System.out.println("Aquest element ja existeix");
                return -1;
            }
            Conference c;
            c = hc.getConferencesByName().get(oldName);
            id = c.getId();
            c.setName(newName);
            conferencesById.replace(id,c);
            conferencesByName.remove(oldName);
            conferencesByName.put(newName,c);
            ret = 0;
        } else {
            System.err.println("L'element no existeix.");
            ret = -6;
        }
        return ret;
    }

    private int confRelation(String nc, String np) {
        Paper p = papersByName.get(np);
        if (p == null) {
            System.err.println("Aquest article no existeix");
            return -2;
        }
        Conference c = conferencesByName.get(nc);
        if (c == null) {
            System.err.println("Aquesta conferència no existeix");
            return -3;
        }
        c.addExposedPaper(p);
        Conference old = p.getConference();
        p.setConference(c);
        old.removeExposedPaperBy(p);
        conferencesByName.put(nc,c);
        conferencesById.put(c.getId(),c);
        conferencesByName.put(old.getName(),old);
        conferencesById.put(old.getId(),old);
        papersByName.put(p.getName(),p);
        papersById.put(p.getId(),p);
        if (old.getExposedPapers().isEmpty()) deleteConf(old.getName());
        return 0;
    }

    private int addTermRelation(String name, String np) {
        Paper p = papersByName.get(np);
        if (p == null) {
            System.err.println("Aquest article no existeix");
            return -2;
        }
        Term t = termsByName.get(name);
        if (t == null) {
            System.err.println("Aquesta terme no existeix");
            return -8;
        }
        t.addPaperWhichTalkAboutIt(p);
        p.addTerm(t);
        termsByName.replace(name,t);
        termsById.replace(t.getId(),t);
        papersByName.replace(p.getName(),p);
        papersById.replace(p.getId(),p);
        return 0;
    }

    private int delTermRelation(String name, String np) {
        Paper p = papersByName.get(np);
        if (p == null) {
            System.err.println("Aquest article no existeix");
            return -2;
        }
        Term t = termsByName.get(name);
        if (t == null) {
            System.err.println("Aquesta terme no existeix");
            return -8;
        }
        t.removePaperWhichTalkAboutIt(p);
        p.removeTerm(t);
        termsByName.replace(name,t);
        termsById.replace(t.getId(),t);
        papersByName.replace(p.getName(),p);
        papersById.replace(p.getId(),p);
        if (t.getPapersWhichTalkAboutThis().isEmpty()) deleteTerm(t.getName());
        return 0;
    }

    private int addAuthorRelation(String name, String np) {
        Paper p = papersByName.get(np);
        if (p == null) {
            System.err.println("Aquest article no existeix");
            return -2;
        }
        Author a = authorsByName.get(name);
        if (a == null) {
            System.err.println("Aquest autor no existeix");
            return -7;
        }
        a.addPaper(p);
        p.addAuthor(a);
        authorsByName.replace(name,a);
        authorsById.replace(a.getId(),a);
        papersByName.replace(p.getName(),p);
        papersById.replace(p.getId(),p);
        return 0;
    }

    private int delAuthorRelation(String name, String np) {
        Paper p = papersByName.get(np);
        if (p == null) {
            System.err.println("Aquest article no existeix");
            return -2;
        }
        Author a = authorsByName.get(name);
        if (a == null) {
            System.err.println("Aquest autor no existeix");
            return -7;
        }
        a.removePaper(p);
        p.removeAuthor(a);
        authorsByName.replace(name,a);
        authorsById.replace(a.getId(),a);
        papersByName.replace(p.getName(),p);
        papersById.replace(p.getId(),p);
        if (a.getAuthorRelations().isEmpty()) deleteAuthor(name);
        if (p.getRelatedAuthors().isEmpty()) deletePaper(np);
        return 0;
    }

    public int addRelation(String name, String np) {
        int ret;
        if (authorsByName.get(name) != null) ret = addAuthorRelation(name,np);
        else if (termsByName.get(name) != null) ret = addTermRelation(name,np);
        else if (conferencesByName.get(name) != null) ret = confRelation(name,np);
        else {
            System.err.println("L'element no existeix.");
            ret = -6;
        }
        return ret;
    }

    public int delRelation(String name, String np) {
        int ret;
        if (authorsByName.get(name) != null) ret = delAuthorRelation(name,np);
        else if (termsByName.get(name) != null) ret = delTermRelation(name,np);
        else if (conferencesByName.get(name) != null) ret = confRelation(name,np);
        else {
            System.err.println("L'element no existeix.");
            ret = -6;
        }
        return ret;
    }

    public int delete(String name) {
        int ret;
        if (authorsByName.get(name) != null) ret = deleteAuthor(name);
        else if (papersByName.get(name) != null) ret = deletePaper(name);
        else if (termsByName.get(name) != null) ret = deleteTerm(name);
        else if (conferencesByName.get(name) != null) ret = deleteConf(name);
        else {
            System.err.println("L'element no existeix.");
            ret = -6;
        }
        return ret;
    }

    private int deleteAuthor(String name) {
        Author a = authorsByName.get(name);
        if (a == null) {
            System.err.println("Aquest autor no existeix");
            return -7;
        }
        //eliminem relacions
        HashMap<Integer,Paper> aux = a.getPapersById(papersById);
        for (Paper p : aux.values()) {
            p.removeAuthor(a);
            papersById.replace(p.getId(), p);
            papersByName.replace(p.getName(), p);
            if (p.getAuthorsById(authorsById).isEmpty()) deletePaper(p.getName());
        }
        //eliminem l'autor
        int id = a.getId();
        authorsByName.remove(name);
        authorsById.remove(id);
        return 0;
    }

    private int deletePaper(String name) {
        Paper p = papersByName.get(name);
        if (p == null) {
            System.err.println("Aquest article no existeix");
            return -2;
        }
        int id = p.getId();
        HashMap<Integer,Author> autors = p.getAuthorsById(authorsById);
        for (Author a : autors.values()) {
            a.removePaper(p);
            authorsById.replace(a.getId(), a);
            authorsByName.replace(a.getName(), a);
            if (a.getAuthorRelations().isEmpty()) deleteAuthor(a.getName());
        }
        HashMap<Integer,Term> terms = p.getTermsById(termsById);
        for (Term t : terms.values()) {
            t.removePaperWhichTalkAboutIt(p);
            termsById.replace(t.getId(), t);
            termsByName.replace(t.getName(), t);
            if (t.getPapersWhichTalkAboutThis().isEmpty()) deleteTerm(t.getName());
        }
        Conference c = p.getConference();
        //si ho crido desde deleteConf entrarem en un bucle infinit (amb el if no)
        if (!c.getName().equals("deletingConf") && c.getId() != -1) {
            c.removeExposedPaperBy(p);
            conferencesById.replace(c.getId(), c);
            conferencesByName.replace(c.getName(), c);
            if (c.getExposedPapers().isEmpty()) deleteConf(c.getName());
        }
        papersByName.remove(name);
        papersById.remove(id);
        return 0;
    }

    private int deleteConf(String name) {
        Conference c = conferencesByName.get(name);
        if (c == null) {
            System.err.println("Aquesta conferència no existeix");
            return -3;
        }
        HashMap<Integer,Paper> papers = c.getExposedPapersById(papersById);
        //si ho crido desde deleteConf entrarem en un bucle infinit (amb això no)
        Conference aux = new Conference("deletingConf",-1);
        for (Paper p : papers.values()) {
            p.setConference(aux);
            papersByName.put(p.getName(),p);
            papersById.put(p.getId(),p);
            deletePaper(p.getName());
        }
        hc.delYearConf(c,c.getYear());
        hc.delContinentConf(c,c.getContinent());
        conferencesById.remove(c.getId(),c);
        conferencesByName.remove(c.getName(),c);

        return 0;
    }

    private int deleteTerm(String name) {
        Term t = termsByName.get(name);
        if (t == null) {
            System.err.println("Aquest terme no existeix");
            return -8;
        }
        HashMap<Integer,Paper> aux = t.getPapersWhichTalkAboutThisById(papersById);
        for (Paper p : aux.values()) {
            p.removeTerm(t);
            //if (p.getRelationesTerms().isEmpty()) deletePaper(p.getName());
            papersByName.replace(p.getName(),p);
            papersById.replace(p.getId(),p);
        }
        int id = t.getId();
        termsByName.remove(name);
        termsById.remove(id);

        return 0;
    }
}
