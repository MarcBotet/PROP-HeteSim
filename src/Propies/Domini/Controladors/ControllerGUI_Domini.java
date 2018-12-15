/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Propies.Domini.Controladors;
import Propies.Presentacio.BadTermsError;
import Propies.Presentacio.MainFrame;
import cluster.Nodes.Author;
import cluster.Nodes.Conference;
import cluster.Nodes.Paper;
import cluster.Nodes.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import javax.swing.DefaultListModel;

/**
 *
 * @author Riqui
 */
public class ControllerGUI_Domini {
        private ControllerPresentacioDomini cdp = new ControllerPresentacioDomini();
        private MainFrame vista;
        private String MVPinfo;
        private char c;
        private HINController hc;
        public void empezar_cerca(String MVPinfo,HINController hc,Boolean filt,MainFrame vista,ArrayList<String> filtres,Boolean andor){
         this.vista = vista;
         String path = new String();
         cdp.PresentationControler(MVPinfo,hc,filt,this,filtres,andor);
             
    }
           
           
       private boolean iguals(String s1, String s2){
        int n1 = s1.length();
        int n2 = s2.length();
        if(n1 != n2)return false;
        for(int i=0;i<n1;++i){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(c1 != c2)return false;
        }
        return true;
    }  
      
      public void call_error_terms(){
          BadTermsError bte = new BadTermsError();
          bte.setVisible(true);
      }
      
      public void error(String a){
          vista.call_error(a);
      } 
       
       
      public void make_profilesGUI(HINController hc,ArrayList<NavigableMap<Double,List<Integer> > > perfil_info,String MVPinfo,String path,ArrayList<String> setofpaths){
        this.MVPinfo = MVPinfo;
        c = path.charAt(0);
        switch (c){
            case 'A':
                terms_profileGUI(perfil_info.get(3),hc);
                conference_profileGUI(perfil_info.get(2),hc);
                author_profileGUI(perfil_info.get(0),hc);
                paper_profileGUI(perfil_info.get(1),hc);
                break;
            case 'P':
                author_profileGUI(perfil_info.get(0),hc);
                conference_profileGUI(perfil_info.get(2),hc);
                terms_profileGUI(perfil_info.get(3),hc);
                paper_profileGUI(perfil_info.get(1),hc);
                break;
            case 'C':
                author_profileGUI(perfil_info.get(0),hc);
                terms_profileGUI(perfil_info.get(3),hc);
                paper_profileGUI(perfil_info.get(1),hc);
                conference_profileGUI(perfil_info.get(2),hc);
                break;
            case 'T':
                paper_profileGUI(perfil_info.get(1),hc);
                author_profileGUI(perfil_info.get(0),hc);
                conference_profileGUI(perfil_info.get(2),hc);
                terms_profileGUI(perfil_info.get(3),hc);
                break;
        }
        vista.setTitol(MVPinfo);
        vista.setCamins(setofpaths);
        vista.setPerfilVisible(true);
        vista.setCercaVisible(false);
        vista.mouse();

    }
        
        
       private void author_profileGUI(NavigableMap<Double,List<Integer> > hetesim,HINController hc){
        int escritos = 0;
        DefaultListModel model1 = new DefaultListModel();
        for(double i : hetesim.keySet()){
            Iterator<Integer> it = hetesim.get(i).iterator();
           while(it.hasNext()){
               int pid = it.next();
               Author a;
               HashMap<Integer,Author> authorsById = hc.getAuthorsById();
               a = authorsById.get(pid);
               if(!iguals(a.getName(),MVPinfo)) {
                   String line = a.getName() + "; " + i;
                   model1.addElement(line);
                   ++escritos;
               }

               
           }
            
       }
        vista.setListaAutors(model1);
    }
       
     private void paper_profileGUI(NavigableMap<Double,List<Integer> > hetesim, HINController hc){
        int escritos = 0;
        DefaultListModel model2 = new DefaultListModel();
        for(double i : hetesim.keySet()){
            Iterator<Integer> it = hetesim.get(i).iterator();
            while(it.hasNext()){
                int pid = it.next();
                HashMap<Integer,Paper> papersById = hc.getPapersById();
                Paper p;
                p = papersById.get(pid);
                if(!iguals(p.getName(),MVPinfo)) {
                    String line = p.getName() + "; " + i;
                    model2.addElement(line);
                    ++escritos;
                }
               
            }
           
        }
        vista.setListaArticles(model2);
    }  

       private void terms_profileGUI(NavigableMap<Double,List<Integer> > hetesim,HINController hc){
        int escritos = 0;
        DefaultListModel model3 = new DefaultListModel();
        for(double i : hetesim.keySet()){
            Iterator<Integer> it = hetesim.get(i).iterator();
            while(it.hasNext()){
                int pid = it.next();
                HashMap<Integer,Term> termsById = hc.getTermsById();
                Term t;
                t = termsById.get(pid);
                if(!iguals(t.getName(),MVPinfo)) {
                    String line = t.getName() + "; " + i;
                    model3.addElement(line);
                    ++escritos;
                }
                
            }
           
        }
        vista.setListaTerms(model3);
    }
       
      private void conference_profileGUI(NavigableMap<Double,List<Integer> > hetesim,HINController hc){
        int escritos = 0;
        DefaultListModel model4 = new DefaultListModel();
        for(double i : hetesim.keySet()){
            Iterator<Integer> it = hetesim.get(i).iterator();
            while(it.hasNext()){
                int pid = it.next();
                Conference c;
                HashMap<Integer, Conference> conferencesById = hc.getConferencesById();
                c = conferencesById.get(pid);
                if(!iguals(c.getName(),MVPinfo)) {
                    String line = c.getName() + "; " + i;
                    model4.addElement(line);
                    ++escritos;
                }
               
            }
            
        }
        vista.setListaConferencia(model4);
        

    }    
        
        
        

}
