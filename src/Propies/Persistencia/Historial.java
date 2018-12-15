package Propies.Persistencia;

import Propies.Domini.Controladors.HINController;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by roger.vila.munoz on 31/05/2016.
 */
public class Historial {
    public void Historial(){}
    public HIN getHistorial(Integer i){
        HIN h = new HIN();
        String path = new File("").getAbsolutePath();
        ObjectInputStream inputStream = null;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(path.concat("/Data/Historial/Historial" + i + "/binary.dat"));
            BufferedInputStream bis = new BufferedInputStream(fis);
            inputStream = new ObjectInputStream(bis);
        }catch(IOException e){
            return h;
        }
        try{
            h = (HIN)inputStream.readObject();
            inputStream.close();
        }catch(Exception e){
        }
        return h;

    }


    public void getHistorialInfo(Integer i,ArrayList<String> ret){
        String p = new String();
        String path = new File("").getAbsolutePath();
        File inputFile = new File(p.concat(path + "/Data/Historial/Historial" + i + "/info.txt"));
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                ret.add(line);
            }
        }catch(IOException e){
            System.err.format("IOException: %s%n", e);
        }
    }

    public void printHistorial(HINController hc){
        String path = new File("").getAbsolutePath();
        File dir = new File(path.concat("/Data/Historial/Historial10"));
        try {
            File[] files = dir.listFiles();
            for (File f : files) {
                f.delete();
            }
            dir.delete();
        }catch (NullPointerException e){
            //no es fa res perque es com si la carpeta ja estès esborrada
        }
        for(int i = 9; i > 0; --i){
            dir = new File(path.concat("/Data/Historial/Historial" + i));
            ++i;
            try {
                File newName = new File(path.concat("/Data/Historial/Historial" + i));
                dir.renameTo(newName);
                --i;
            }catch(NullPointerException e){
                //Si dir no existeix no cal moure'l
            }
        }
        dir = new File(path.concat("/Data/Historial/Historial1"));
        dir.mkdir();
        File info = new File(path.concat("/Data/Historial/Historial1/info.txt"));
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(info))){
            String line = new String();
            DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
            DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            line = dateFormat.format(date);
            writer.write(line,0,line.length());
            writer.newLine();
            line = dateFormat1.format(date);
            writer.write(line,0,line.length());
            writer.newLine();
            int n = hc.getAuthorsById().size();
            line = "a: " + n;
            writer.write(line,0,line.length());
            writer.newLine();
            n = hc.getPapersById().size();
            line = "p: " + n;
            writer.write(line,0,line.length());
            writer.newLine();
            n = hc.getTermsById().size();
            line = "t: " + n;
            writer.write(line,0,line.length());
            writer.newLine();
            n = hc.getConferencesById().size();
            line = "c: " + n;
            writer.write(line,0,line.length());
        }catch(IOException e){
            System.err.format("IOException: %s%n", e);
        }
        File binary = new File(path.concat("/Data/Historial/Historial1/binary.dat"));
        ObjectOutputStream  outputStream = null;
        FileOutputStream fos = null;
        try{
            //outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            fos = new FileOutputStream(binary);
            BufferedOutputStream bis = new BufferedOutputStream(fos);
            outputStream = new ObjectOutputStream(bis);
        }catch(IOException e){
            System.out.println("Could not open the file." + e);
            System.exit(0);
        }
        try{
            outputStream.writeObject(hc.getHIN());
            outputStream.close();
        }catch(IOException e){
        }

    }
}
