package cluster.Drivers;

import Propies.Domini.Controladors.HINController;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.util.Set;

public class Paper_driver {

    public static void main(String[] args) {

        HINController hc = new HINController();
        //Crea un objecte de la classe Paper.
        Paper p = new Paper("The NP problem",321);

        //Escriu el Id: 321.
        System.out.println(p.getId());


        //Escriu el nom: "The NP problem".
        System.out.println(p.getName());

        //Assigna una conferencia al Paper i la mostra per pantalla el seu nom
        Conference c = new Conference("CSS",3);
        p.setConference(c);
        System.out.println(p.getConference().getName());

        //Cas 1: 1 unic autor
        //Afegeix un autor al Paper i el mostra per pantalla el seu nom i id
        Author a1 = new Author("Muhamed Lee",123);
        p.addAuthor(a1);
        System.out.println(p.getAuthorsByName(hc.getAuthorsByName()).get("Muhamed Lee").getName());
        System.out.println(p.getAuthorsById(hc.getAuthorsById()).get(123).getName());

        //Cas 2: mes d'un autor
        //Afegeix un altre autor i mostra ambos autors tant per nom com per id
        Author a2 = new Author("Jose Garcia",456);
        p.addAuthor(a2);
        Set <Integer> keyMap = p.getAuthorsById(hc.getAuthorsById()).keySet();
        for (Integer i : keyMap){
            System.out.println(p.getAuthorsById(hc.getAuthorsById()).get(i).getName());
        }
        Set<String> keyMap2 = p.getAuthorsByName(hc.getAuthorsByName()).keySet();
        for (String s : keyMap2){
            System.out.println(p.getAuthorsByName(hc.getAuthorsByName()).get("Muhamed Lee").getName());
        }



        //Afegeix un terme i el mostra per pantalla, tant per nom com per id
        Term t1 = new Term("The",1);
        p.addTerm(t1);
        Term t2 = new Term("NP",2);
        p.addTerm(t2);
        Term t3 = new Term("problem",3);
        p.addTerm(t3);
        System.out.println(p.getTermsByName(hc.getTermsByName()).get("NP").getName());
        System.out.println(p.getTermsById(hc.getTermsById()).get(2).getName());

        //Tot seguit mostra tots els termes associats al Paper,tant per nom com per id
        System.out.println("Termes de l'article");
        Set<Integer> keyMap3 = p.getTermsById(hc.getTermsById()).keySet();
        for (Integer i : keyMap3){
            System.out.println(p.getTermsById(hc.getTermsById()).get(i).getName());
        }
        Set<String> keyMap4 = p.getTermsByName(hc.getTermsByName()).keySet();
        for (String s : keyMap4){
            System.out.println(p.getTermsByName(hc.getTermsByName()).get(s).getName());
        }

    }
}