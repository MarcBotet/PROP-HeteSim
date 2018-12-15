package cluster.Drivers;

import Propies.Domini.Controladors.HINController;
import cluster.Nodes.Author;
import cluster.Nodes.Paper;

import java.util.Set;

public class Author_driver {

    public static void main(String[] args) {
        HINController hc = new HINController();
        //Crea un objecte de la classe Author.
        Author a = new Author("Muhamed Lee",123);

        //Escriu el Id: 123.
        System.out.println(a.getId());


        //Escriu el nom: "Muhamed Lee".
        System.out.println(a.getName());

        //Afegeix un article i el mostra per pantalla, tant per nom com per id
        Paper p1 = new Paper("The NP Problem",1);
        a.addPaper(p1);
        hc.addPaper(p1);

        System.out.println(a.getPapersByName(hc.getPapersById()).get("The NP Problem").getName());
        System.out.println(a.getPapersById(hc.getPapersById()).get(1).getName());

        //Tot seguit mostra tots els articles associats al autor,tant per nom com per id
        Paper p2 = new Paper("P equals NP",2);
        a.addPaper(p2);
        hc.addPaper(p1);
        System.out.println("Articles de l'autor");
        Set<Integer> keyMap = a.getPapersById(hc.getPapersById()).keySet();
        for (Integer i : keyMap){
            System.out.println(a.getPapersById(hc.getPapersById()).get(i).getName());
        }
        Set<String> keyMap2 = a.getPapersByName(hc.getPapersById()).keySet();
        for (String s : keyMap2){
            System.out.println(a.getPapersByName(hc.getPapersById()).get(s).getName());
        }

        //Borra un dels articles
        a.removePaper(a.getPapersById(hc.getPapersById()).get(1));
        hc.getPapersById().remove(a.getPapersById(hc.getPapersById()).get(1));
        hc.getPapersByName().remove(p1);
        System.out.println("Esborrem l'article 1 amb nom The NP Problem i comprovem que no hi es");
        if(a.getPapersByName(hc.getPapersById()).get("The NP Problem") == null)System.out.println("Aquest autor no es d'aquest article");
        if(a.getPapersById(hc.getPapersById()).get(1) == null)System.out.println("Aquest autor no es d'aquest article");
    }

}
