package Propies.Persistencia;

import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by roger.vila.munoz on 01/06/2016.
 */
public class writerobj {
    public writerobj(){   
    }
    public void writeAuthorsToFile(HashMap<Integer, Author> authorsById, String path) {
        String p = new String();
        File outputFile = new File(p.concat(path + "/author.txt"));
        try (BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            String line;
            for(Integer i : authorsById.keySet()){
                Author a = authorsById.get(i);
                line = i + "\t" + a.getName() + "\n";
                bufferwriter.write(line,0,line.length());
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public void writePapersToFile(HashMap<Integer, Paper> papersById, String path) {
        String p = new String();
        File outputFile = new File(p.concat(path + "/paper.txt"));
        try (BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            String line;
            for(Integer i : papersById.keySet()){
                Paper pa = papersById.get(i);
                line = i + "\t" + pa.getName() + "\n";
                bufferwriter.write(line,0,line.length());
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        writeRelationsToFile(papersById,path);
    }

    public void writeConferencesToFile(HashMap<Integer, Conference> conferencesById, String path) {
        String p = new String();
        File outputFile = new File(p.concat(path + "/conf.txt"));
        try (BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            String line;
            for(Integer i : conferencesById.keySet()){
                Conference c = conferencesById.get(i);
                line = i + "\t" + c.getName() + "\t" + c.getYear() + "\t" + c.getContinent() + "\n";
                bufferwriter.write(line,0,line.length());
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }


    public void writeTermsToFile(HashMap<Integer, Term> termsById, String path) {
        String p = new String();
        File outputFile = new File(p.concat(path + "/term.txt"));
        try (BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            String line;
            for(Integer i : termsById.keySet()){
                Term t = termsById.get(i);
                line = i + "\t" + t.getName() + "\n";
                bufferwriter.write(line,0,line.length());
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public void writeRelationsToFile(HashMap<Integer, Paper> papersById,String path) {
        String p = new String();
        File outputFile1 = new File(p.concat(path + "/paper_author.txt"));
        p = new String();
        File outputFile2 = new File(p.concat(path + "/paper_term.txt"));
        p = new String();
        File outputFile3 = new File(p.concat(path + "/paper_conf.txt"));
        try (BufferedWriter bufferwriter1 = new BufferedWriter(new FileWriter(outputFile1, true))) {
            try (BufferedWriter bufferwriter2 = new BufferedWriter(new FileWriter(outputFile2, true))) {
                try (BufferedWriter bufferwriter3 = new BufferedWriter(new FileWriter(outputFile3, true))) {
                    String line;
                    for (Integer i : papersById.keySet()) {
                        Paper pa = papersById.get(i);
                        for(Integer j : pa.getRelatedAuthors()) {
                            line = i + "\t" + j + "\n";
                            bufferwriter1.write(line, 0, line.length());
                        }
                        for(Integer j : pa.getRelationesTerms()){
                            line = i + "\t" + j + "\n";
                            bufferwriter2.write(line, 0,line.length());
                        }
                        line = i + "\t" + pa.getConference().getId() + "\n";
                        bufferwriter3.write(line,0,line.length());
                    }
                } catch (IOException x) {
                    System.err.format("IOException: %s%n", x);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

}
