package Propies.Domini.Controladors.Drivers;

import Propies.Domini.Controladors.DomainPersistanceController;
import Propies.Domini.Controladors.HINController;
import cluster.*;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by MarcBotet on 25/04/2016.
 */
public class HIN_Driver {

    private static void llegirMatrius(HINController graph) {
        Scanner capt = new Scanner(System.in);
        graph.generar_matrix();
        HashMap<String,Matrix> m = graph.getMatrius();
        System.out.println("Quina matriu vols mostrar? (AP,CP,TP,PA,PC,PT)");
        String d = capt.nextLine();
        switch (d) {
            case "AP":
                m.get("AP").print();
                break;
            case "CP":
                m.get("CP").print();
                break;
            case "TP":
                m.get("TP").print();
                break;
            case "PA":
                m.get("PA").print();
                break;
            case "PC":
                m.get("PC").print();
                break;
            case "PT":
                m.get("PT").print();
                break;
            default:
                System.err.println("S'ha produït un error");
                System.exit(0);
        }
    }

    private static void llegirHashes(HINController graph) {
        System.out.println("Quin tipus vols mostrar? (Autor,Article,Conferencie,Terme)");
        Scanner capt = new Scanner(System.in);
        String t = capt.nextLine();
        switch(t) {
            case "Autor":
                System.out.println("Autors:");
                HashMap<Integer,Author> a = graph.getAuthorsById();
                for (int i : a.keySet()) {
                    System.out.println("Nom: "+a.get(i).getName()+" id: "+i);
                }
                break;
            case "Article":
                System.out.println("Articles:");
                HashMap<Integer,Paper> p = graph.getPapersById();
                for (int i : p.keySet()) {
                    System.out.println("Nom: "+p.get(i).getName()+" id: "+i);
                }
                break;
            case "Conferencia":
                System.out.println("Conferencies:");
                HashMap<Integer,Conference> c = graph.getConferencesById();
                for (int i : c.keySet()) {
                    System.out.println("Nom: "+c.get(i).getName()+" id: "+i);
                }
                break;
            case "Terme":
                System.out.println("Termes:");
                HashMap<Integer,Term> te = graph.getTermsById();
                for (int i : te.keySet()) {
                    System.out.println("Nom: "+te.get(i).getName()+" id: "+i);
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
        dpcd.readAll(graph,"/Data/datasetGran");
        Scanner capt = new Scanner(System.in);
        System.out.println("1- Mostrar Matrius\n2- Mostrar entitats");
        int d = capt.nextInt();
        if (d != 1) llegirHashes(graph);
        else llegirMatrius(graph);

    }
}
