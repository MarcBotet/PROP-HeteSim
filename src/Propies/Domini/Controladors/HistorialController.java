package Propies.Domini.Controladors;

import Propies.Domini.Controladors.HINController;
import Propies.Persistencia.Historial;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by roger.vila.munoz on 31/05/2016.
 */
public class HistorialController {

    public void HistorialController(){
    }

    public void getHistorial(Integer i, HINController hc){
        Historial hist =  new Historial();
        hc.setHIN(hist.getHistorial(i));
    }

    public void getHistorialInfo(Integer i,ArrayList<String> ret){
        Historial hist =  new Historial();
        hist.getHistorialInfo(i,ret);
    }
    public void printHistorial(HINController hc){
        Historial hist =  new Historial();
        hist.printHistorial(hc);
    }


}
