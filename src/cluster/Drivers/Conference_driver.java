package cluster.Drivers;



import Propies.Domini.Controladors.HINController;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;

import java.util.HashMap;
import java.util.Set;

public class Conference_driver {

    public static void main(String[] args) {
        HINController hc = new HINController();

        Paper p2 = new Paper("Hi", 1);
        Paper p1 = new Paper("Hola", 2);
        Conference c = new Conference("CSS", 3);

        c.addExposedPaper(p1);
        c.addExposedPaper(p2);


        System.out.println("Working with the conference: " + c.getName());

        System.out.println("Get the papers exposed at the confernce by id:");
        HashMap<Integer,Paper> exposedPapersById = c.getExposedPapersById(hc.getPapersById());
        Set<Integer> keyMap = exposedPapersById.keySet();
        for (Integer i : keyMap){
            System.out.println(exposedPapersById.get(i).getId() +") "+ exposedPapersById.get(i).getName());
        }

        System.out.println("Get paper `Hola´ by his id(2).");
        Paper aux = c.getExposedPapersById(hc.getPapersById()).get(2);
        System.out.println("id/name: " + aux.getId() + " " + aux.getName());
        System.out.println("Get paper with the id 5 by his name(`Hi´).");
        aux = c.getExposedPapersByName(hc.getPapersByName()).get("Hi");
        System.out.println("id/name: " + aux.getId() + " " + aux.getName() +".");

        System.out.println("Set the conference year and continent and change the name of the confernce.");
        c.setContinent("EU");
        c.setYear(2016);
        c.setName("UPC");
        System.out.println("the conference is now named `" + c.getName() +"´, takes place in the continent "
                + c.getContinent() + " the year " + c.getYear() +".");


    }

}
