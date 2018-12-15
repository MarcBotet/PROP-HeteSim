package cluster.Drivers;

import Propies.Domini.Controladors.HINController;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;

import java.util.Set;

/**
 * Created by Paualos3 on 19/04/2016.
 */
public class Term_driver {
    public static void main(String[] args) {

        HINController hc = new HINController();
        //Crea un objecte de la classe Author.
        Term t = new Term("Graph",123);

        //Escriu el Id: 123.
        System.out.println(t.getId());


        //Escriu el nom
        System.out.println(t.getName());

        //Afegeix un article i el mostra per pantalla, tant per nom com per id
        Paper p1 = new Paper("graph",1);
        t.addPaperWhichTalkAboutIt(p1);

        System.out.println(t.getPapersWhichTalkAboutThisByName(hc.getPapersById()).get("graph").getName());
        System.out.println(t.getPapersWhichTalkAboutThisById(hc.getPapersById()).get(1).getName());

        //Tot seguit mostra tots els articles associats
        Paper p2 = new Paper("heterogeneous graph",2);
        t.addPaperWhichTalkAboutIt(p2);
        System.out.println("Articles");
        Set<Integer> keyMap = t.getPapersWhichTalkAboutThisById(hc.getPapersById()).keySet();
        for (Integer i : keyMap){
            System.out.println(t.getPapersWhichTalkAboutThisById(hc.getPapersById()).get(i).getName());
        }
        Set<String> keyMap2 = t.getPapersWhichTalkAboutThisByName(hc.getPapersById()).keySet();
        for (String s : keyMap2){
            System.out.println(t.getPapersWhichTalkAboutThisByName(hc.getPapersById()).get(s).getName());
        }

    }
}
