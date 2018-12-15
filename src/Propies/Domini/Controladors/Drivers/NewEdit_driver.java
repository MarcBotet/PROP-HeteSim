package Propies.Domini.Controladors.Drivers;

import Propies.Domini.Controladors.DomainPersistanceController;
import Propies.Domini.Controladors.HINController;
import Propies.Domini.Controladors.NewEditController;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by MarcBotet on 31/05/2016.
 */
public class NewEdit_driver {

    private static void llegirHashes(HINController graph) {
        System.out.println("Quin tipus vols mostrar? (Autor,Article,Conferencia,Terme)");
        Scanner capt = new Scanner(System.in);
        String t = capt.nextLine();
        switch (t) {
            case "Autor":
                System.out.println("Autors:");
                HashMap<Integer, Author> a = graph.getAuthorsById();
                for (int i : a.keySet()) {
                    System.out.println("Nom: " + a.get(i).getName() + " id: " + i);
                }
                break;
            case "Article":
                System.out.println("Articles:");
                HashMap<Integer, Paper> p = graph.getPapersById();
                for (int i : p.keySet()) {
                    System.out.println("Nom: " + p.get(i).getName() + " id: " + i);
                }
                break;
            case "Conferencia":
                System.out.println("Conferencies:");
                HashMap<Integer, Conference> c = graph.getConferencesById();
                for (int i : c.keySet()) {
                    System.out.println("Nom: " + c.get(i).getName() + " id: " + i);
                }
                break;
            case "Terme":
                System.out.println("Termes:");
                HashMap<Integer, Term> te = graph.getTermsById();
                for (int i : te.keySet()) {
                    System.out.println("Nom: " + te.get(i).getName() + " id: " + i);
                }
                break;
            default:
                System.err.println("S'ha produït un error");
                System.exit(0);

        }
    }

    public static void main(String[] args) {
        HINController graph = new HINController();
        DomainPersistanceController dpcd = new DomainPersistanceController();
        String f = new File("").getAbsolutePath();
        dpcd.readAll(graph,f.concat("/Data/datasetGran"));
        NewEditController nec = new NewEditController(graph);
        Scanner capt = new Scanner(System.in);
        System.out.println("Que vols fer?\n1-Afegir,2-esborrar,3-editar relacions,4-editar nom");
        int d = capt.nextInt();
        capt.nextLine();
        if (d==1) {
            System.out.println("Que vols afegir? (A -> Author, P -> Paper, T -> Term, C -> Conference");
            String s = capt.nextLine();
            if (s.equals("A")) {
                System.out.println("Escriu el nom de l'autor");
                s = capt.nextLine();
                System.out.println("Escriu el número de papers que ha escrit");
                d = capt.nextInt();
                capt.nextLine();
                ArrayList<String> p = new ArrayList<>();
                for (int i = 0; i < d; ++i) {
                    System.out.println("Escriu el nom de l'article");
                    p.add(capt.nextLine());
                }
                nec.addAuthor(s,p);
            } else if (s.equals("P")) {
                System.out.println("Escriu el nom de l'article");
                s = capt.nextLine();
                System.out.println("Escriu el número d'autors que té");
                d = capt.nextInt();
                capt.nextLine();
                ArrayList<String> a = new ArrayList<>();
                for (int i = 0; i < d; ++i) {
                    System.out.println("Escriu el nom de l'autor");
                    a.add(capt.nextLine());
                }
                System.out.println("Escriu el número de termes que té");
                d = capt.nextInt();
                capt.nextLine();
                ArrayList<String> t = new ArrayList<>();
                for (int i = 0; i < d; ++i) {
                    System.out.println("Escriu el nom del terme");
                    t.add(capt.nextLine());
                }
                System.out.println("Escriu el nom de la conferència");
                nec.addPaper(s,a,capt.nextLine(),t);
            } else if (s.equals("C")) {
                System.out.println("Escriu el nom de la conferència");
                s = capt.nextLine();
                System.out.println("Escriu el número de papers que té");
                d = capt.nextInt();
                capt.nextLine();
                ArrayList<String> p = new ArrayList<>();
                for (int i = 0; i < d; ++i) {
                    System.out.println("Escriu el nom de l'article");
                    p.add(capt.nextLine());
                }
                nec.addConf(s,p);
            } else if (s.equals("T")) {
                System.out.println("Escriu el nom del terme");
                s = capt.nextLine();
                System.out.println("Escriu el número de papers que té");
                d = capt.nextInt();
                capt.nextLine();
                ArrayList<String> p = new ArrayList<>();
                for (int i = 0; i < d; ++i) {
                    System.out.println("Escriu el nom de l'article");
                    p.add(capt.nextLine());
                }
                nec.addTerm(s,p);
            }
        } else if (d==2) {
            System.out.println("Escriu el nom de l'element a esborrar");
            nec.delete(capt.nextLine());
        } else if (d==3) {
            System.out.println("Vols: 1-afegir, 2-eliminar");
            int c = capt.nextInt();
            capt.nextLine();
            System.out.println("Escriu el nom del paper");
            String s = capt.nextLine();
            System.out.println("Escriu el nom de l'element");
            if (c == 1) nec.addRelation(capt.nextLine(),s);
            else if (c==2) nec.delRelation(capt.nextLine(),s);
        } else if (d==4) {
            System.out.println("Escriu el nom antic");
            String s = capt.nextLine();
            System.out.println("Escriu el nou nom");
            nec.editName(s,capt.nextLine());
        }
        nec.confirmChange();
        llegirHashes(graph);
    }
}
