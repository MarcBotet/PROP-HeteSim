package cluster.Nodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Conference extends Node {
    private int year;
    private String continent;
    private ArrayList<Integer> exposedPapersById;
    private ArrayList<String> exposedPapersByName;

    private static int maxId;

    public Conference(String name, int id) {
        super(name, id);
        exposedPapersById = new ArrayList<Integer>();
        exposedPapersByName = new ArrayList<String>();

        if (maxId < id) maxId = id;

    }

    //Pre: Cert.
    //Post: Retorna el valor de maxId del parametre implicit.
    public static int getMaxId() {
        return maxId;
    }

    public HashMap<Integer, Paper> getExposedPapersById(HashMap<Integer, Paper> papers) {
        HashMap<Integer,Paper> papersById = new HashMap<Integer,Paper>();
        for(int i = 0; i < this.exposedPapersById.size(); i++){
            int keyId = this.exposedPapersById.get(i);
            papersById.put(keyId, papers.get(keyId));
        }
        return papersById;
    }

    public HashMap<String, Paper> getExposedPapersByName(HashMap<String, Paper> papers) {
        HashMap<String,Paper> papersByName = new HashMap<String,Paper>();
        for(int i = 0; i < this.exposedPapersByName.size(); i++){
            String keyName = this.exposedPapersByName.get(i);
            papersByName.put(keyName, papers.get(keyName));
        }
        return papersByName;
    }

    public ArrayList<Integer> getExposedPapers(){
        return exposedPapersById;
    }

    public void addExposedPaper(Paper paper) {
        exposedPapersById.add(paper.getId());
        exposedPapersByName.add(paper.getName());
    }

    public void removeExposedPaperBy(Paper paper) {
        for(int i = 0; i < exposedPapersById.size(); i++){
            if(exposedPapersById.get(i) == paper.getId()){
                exposedPapersById.remove(i);
                exposedPapersByName.remove(i);
                break;
            }
        }
    }

    public void sortExposedPapers(){
        Collections.sort(exposedPapersById);
        Collections.sort(exposedPapersByName);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getContinent() {
        return continent;
    }
}
