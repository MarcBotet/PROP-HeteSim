/**
 * Created by Riqui on 07/04/2016.
 */
package Propies.Domini.Controladors;
import Propies.Presentacio.MainFrame;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        HINController hc = new HINController();
        HistorialController histc = new HistorialController();
        histc.getHistorial(1, hc);
        if(hc.getAuthorsById().isEmpty()){
            DomainPersistanceController dpcd = new DomainPersistanceController();
            String p = new File("").getAbsolutePath();
            dpcd.readAll(hc, p.concat("/Data/datasetGran"));
        }
        MainFrame empieza = new MainFrame(hc);
        empieza.begin();
    }
}
