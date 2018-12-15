package Propies.Persistencia;

import Propies.Domini.Controladors.HINController;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by roger.vila.munoz on 01/06/2016.
 */
public class readerobj {
        public readerobj(){

        }
        public void readPapersFromFile(HashMap<Integer, Paper> papersById, HashMap<String, Paper> papersByName, String path) {
            String p = new String();
            File inputFile = new File(p.concat(path + "/paper.txt"));
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] aux = line.split("\\t");
                    int id = Integer.parseInt(aux[0]);
                    Paper paper = new Paper(aux[1], id);
                    papersById.put(id, paper);
                    papersByName.put(aux[1], paper);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        public void readConferencesFromFile(HINController hc, HashMap<Integer, Conference> conferencesById, HashMap<String, Conference> conferencesByName, String path) {
            String p = new String();
            File inputFile = new File(p.concat(path + "/conf.txt"));
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] aux = line.split("\\t");
                    int id = Integer.parseInt(aux[0]);
                    Conference conf = new Conference(aux[1], id);
                    try {
                        conf.setYear(Integer.parseInt(aux[2]));
                        conf.setContinent(aux[3]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        conf.setYear(2016);
                        conf.setContinent("Europe");
                    }
                    hc.addYearConf(conf,conf.getYear());
                    hc.addContinentConf(conf,conf.getContinent());
                    conferencesById.put(id, conf);
                    conferencesByName.put(aux[1], conf);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        public void readTermsFromFile(HashMap<Integer, Term> termsById, HashMap<String, Term> termsByName, String path) {
            String p = new String();
            File inputFile = new File(p.concat(path + "/term.txt"));
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] aux = line.split("\\t");
                    int id = Integer.parseInt(aux[0]);
                    Term term = new Term(aux[1], id);
                    termsById.put(id, term);
                    termsByName.put(aux[1], term);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        public void readPaperAuthorRelations(HashMap<Integer, Paper> papersById, HashMap<Integer, Author> authorsById, String path) {
            String p1 = new String();
            File inputFile = new File(p1.concat(path + "/paper_author.txt"));
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                Paper p;
                Author a;
                while ((line = reader.readLine()) != null) {
                    String aux[] = line.split("\\t");
                    p = papersById.get(Integer.parseInt(aux[0]));
                    a = authorsById.get(Integer.parseInt(aux[1]));

                    p.addAuthor(a);
                    a.addPaper(p);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }

        public void readConferenceRelations(HashMap<Integer, Paper> papersById, HashMap<Integer, Conference> conferencesById,String path) {
            String p1 = new String();
            File inputFile = new File(p1.concat(path + "/paper_conf.txt"));
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                Paper p;
                Conference c;
                while ((line = reader.readLine()) != null) {
                    String aux[] = line.split("\\t");
                    p = papersById.get(Integer.parseInt(aux[0]));
                    c = conferencesById.get(Integer.parseInt(aux[1]));

                    p.setConference(c);
                    c.addExposedPaper(p);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }

        public void readTermRelations(HashMap<Integer, Paper> papersById, HashMap<Integer, Term> termsById,String path) {
            String p1 = new String();
            File inputFile = new File(p1.concat(path + "/paper_term.txt"));
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                Paper p;
                Term t;
                while ((line = reader.readLine()) != null) {
                    String aux[] = line.split("\\t");
                    p = papersById.get(Integer.parseInt(aux[0]));
                    t = termsById.get(Integer.parseInt(aux[1]));

                    p.addTerm(t);
                    t.addPaperWhichTalkAboutIt(p);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        public void readAuthorsFromFile(HashMap<Integer, Author> authorsById, HashMap<String, Author> authorsByName,String path) {
            String p = new String();
            File inputFile = new File(p.concat(path + "/author.txt"));
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] aux = line.split("\\t");
                    int id = Integer.parseInt(aux[0]);
                    Author author = new Author(aux[1], id);
                    authorsById.put(id, author);
                    authorsByName.put(aux[1], author);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    public void readBadTerms(HINController hc){
        Set<String> bt = new HashSet<>();
        String path = new File("").getAbsolutePath();
        File inputFile = new File(path.concat("/Data/BadTerms/badterms.txt"));
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bt.add(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        hc.setNameBadTerms(bt);
    }

}
