package Propies.Presentacio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import Propies.Domini.Classes.*;
import Propies.Domini.Controladors.*;
import cluster.*;

/**
 * Created by Riqui on 06/04/2016.
 */
public class Presentacio {
   /*
    private int iterator_menu;
    Scanner capt = new Scanner(System.in);
    Scanner capt1 = new Scanner(System.in);
    String MVPinfo;

    public void readInfo(){
        System.out.println("Escriu que vols buscar:");
        MVPinfo =  capt1.nextLine();
    }

    public void Inici (HIN h) {
        System.out.println("PROP: Creador de perfils");
        System.out.println("Menu Principal");
        System.out.println("Tecleja el numero corresponent a la funcionalitat que vols utilitzar");
        System.out.println("1- Cercar");
        System.out.println("2- Administració de dades");
        System.out.println("3- Rankings");
        System.out.println("4- Historial de cerca");
        System.out.println("5- Sortir");
        Scanner capt = new Scanner(System.in);
        iterator_menu = capt.nextInt();
        switch(iterator_menu) {
            case 1:
                gotoCerca(h);
                break;
            case 2:
                gotoAdminDades(h);
                break;
            case 3:
                gotoRankings();
                break;
            case 4:
                gotoHistorial();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("\u001B[31m"+"S'ha produït un error"+"\u001B[0m");
                System.out.println();
                Inici(h);
                System.exit(0);

        }
    }

    public void gotoCerca(HIN h) {
        System.out.println("CERCAR");
        iterator_menu = capt.nextInt();
        String path = new String();
        boolean b = false;
        Scanner capt1 = new Scanner(System.in);
        ControllerPresentacioDomini cdp = new ControllerPresentacioDomini();
        readInfo();
        cdp.PresentationControler(MVPinfo,h);
        ArrayList<String> setofpaths = cdp.getSetofPaths();
        ArrayList<ArrayList<String> > filters = cdp.getFilters();
        int id = cdp.getId();
        System.out.println("Quants elements vols mostrar com a maxim? (Si vols que es mostrin tots posa -1)");
        int max = capt.nextInt();
        Perfil per = new Perfil(path,MVPinfo,id,max,setofpaths,filters,b);
        per.main(h);
    }
    public void gotoAdminDades(HIN h) {
        System.out.println("Administrar Dades");
        HashMap<Integer, Author> authorsById = h.getAuthorsById();
        HashMap<Integer, Paper> papersById = h.getPapersById();
        HashMap<Integer, Conference> conferencesById = h.getConferencesById();
        HashMap<Integer, Term> termsById = h.getTermsById();
        HashMap<String, Author> authorsByName = h.getAuthorsByName();
        HashMap<String, Paper> papersByName = h.getPapersByName();
        HashMap<String, Conference> conferencesByName = h.getConferencesByName();
        HashMap<String, Term> termsByName = h.getTermsByName();
        DomainPersistanceController dpc = new DomainPersistanceController();
        Author a = new Author("",-1);
        Conference c = new Conference("",-1);
        Term t = new Term("",-1);
        Paper p = new Paper("",-1);
        int authorMaxId = a.getMaxId();
        int paperMaxId = p.getMaxId();
        int termMaxId = t.getMaxId();
        int conferenceMaxId = c.getMaxId();;

        dpc.newEdit(authorsById,papersById,conferencesById,termsById,authorsByName,papersByName,conferencesByName,termsByName);
        dpc.readAll(authorsById, papersById, conferencesById, termsById, authorsByName, papersByName, conferencesByName, termsByName);
        HIN graph = new HIN();
        graph.setAuthorsById(authorsById);
        graph.setAuthorsByName(authorsByName);
        graph.setConferencesById(conferencesById);
        graph.setConferencesByName(conferencesByName);
        graph.setPapersById(papersById);
        graph.setPapersByName(papersByName);
        graph.setTermsById(termsById);
        graph.setTermsByName(termsByName);

        graph.generar_matrix();

        Inici(graph);
        }



    public void gotoRankings(){
        System.out.println("Funcio avançada buida de moment");

    }

    public void gotoHistorial() {
        System.out.println("Funcio avançada buida de moment");
    }
*/
}
