package Propies.Domini.Controladors;

import Propies.Persistencia.readerobj;
import Propies.Persistencia.writerobj;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.io.*;
import java.util.*;


public class DomainPersistanceController {

    public DomainPersistanceController() {}
    public void readAll(HINController hc, String path) {
        hc.clearHIN();
        readerobj reading = new readerobj();
        HashMap<Integer, Author> authorsById = new HashMap<>();
        HashMap<Integer, Paper> papersById = new HashMap<>();
        HashMap<Integer, Conference> conferencesById = new HashMap<>();
        HashMap<Integer, Term> termsById = new HashMap<>();
        HashMap<String, Author> authorsByName = new HashMap<>();
        HashMap<String, Paper> papersByName = new HashMap<>();
        HashMap<String, Conference> conferencesByName = new HashMap<>();
        HashMap<String, Term> termsByName = new HashMap<>();
        reading.readAuthorsFromFile(authorsById, authorsByName, path);
        reading.readPapersFromFile(papersById, papersByName, path);
        reading.readConferencesFromFile(hc, conferencesById, conferencesByName, path);
        reading.readTermsFromFile(termsById, termsByName, path);
        reading.readPaperAuthorRelations(papersById, authorsById, path);
        reading.readTermRelations(papersById, termsById, path);
        reading.readConferenceRelations(papersById, conferencesById, path);
        hc.setAuthorsById(authorsById);
        hc.setAuthorsByName(authorsByName);
        hc.setPapersById(papersById);
        hc.setPapersByName(papersByName);
        hc.setTermsById(termsById);
        hc.setTermsByName(termsByName);
        hc.setConferencesById(conferencesById);
        hc.setConferencesByName(conferencesByName);
        reading.readBadTerms(hc);
        hc.generar_badTerms();
        hc.generar_matrix();
    }
    public void writeAll(HINController hc, String path) {
        File dir = new File(path);
        writerobj writing = new writerobj();
        for (File f : dir.listFiles()) {
            if (f.getName().equals("author.txt")) f.delete();
            if (f.getName().equals("paper.txt")) f.delete();
            if (f.getName().equals("term.txt")) f.delete();
            if (f.getName().equals("conf.txt")) f.delete();
            if (f.getName().equals("paper_author.txt")) f.delete();
            if (f.getName().equals("paper_term.txt")) f.delete();
            if (f.getName().equals("paper_conf.txt")) f.delete();
        }
        writing.writeAuthorsToFile(hc.getAuthorsById(), path);
        writing.writePapersToFile(hc.getPapersById(), path);
        writing.writeConferencesToFile(hc.getConferencesById(), path);
        writing.writeTermsToFile(hc.getTermsById(), path);
    }
}
