/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Propies.Domini.Controladors;

import java.util.ArrayList;

/**
 *
 * @author 1193765
 */
public class ControllerUtility {
    private HINController hc;
    private DomainPersistanceController dpc = new DomainPersistanceController();
    private HistorialController histc = new HistorialController();
    public ControllerUtility(HINController hc) {
        this.hc = hc;
    }
 
       public void import_menu(String filename){
           dpc.readAll(hc, filename);
       }
       
       public void export_menu(String filename) {
           dpc.writeAll(hc, filename);
       }
       
       public void getHistorialInfo(Integer i, ArrayList<String> ret){
           //histc.getHistorialInfo(i,ret);
           histc.getHistorialInfo(i,ret);
       }

    public void carregaHist(Integer i){
        boolean read = true;
        histc.getHistorial(i, hc);
    }
    
    public void printHistorial(){
        histc.printHistorial(hc);
    }

}
