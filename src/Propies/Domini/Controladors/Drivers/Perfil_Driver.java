package Propies.Domini.Controladors.Drivers;

import Propies.Domini.Controladors.DomainPersistanceController;
import Propies.Domini.Controladors.HINController;


import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

/**
 * Created by Riqui on 24/04/2016.
 */
public class Perfil_Driver {
   public static void main(String[] args) {
        HINController graph = new HINController();
        DomainPersistanceController dpcd = new DomainPersistanceController();
        dpcd.readAll(graph,"/Data/datasetGran");

        graph.generar_matrix();

        String path = "A";
        String MVPinfo = "Franz Baader";
        int id = 15138;
        ArrayList<String> setofpaths = new ArrayList<String>();
        setofpaths.add("APA");
        setofpaths.add("AP");
        setofpaths.add("APC");
        setofpaths.add("APT");
        ArrayList<NavigableMap<Double, List<Integer>>> perfil_info;
        int  max = 10;
        ArrayList<ArrayList<String>> filtrosvacios = new ArrayList<ArrayList<String>>();
        boolean tefiltres = false;
        //Perfil per = new Perfil(path,MVPinfo,id,max,setofpaths,filtrosvacios,tefiltres);
       // per.main(graph);
    }
}
