package cluster.Nodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Author extends Node{
    private ArrayList<Integer> papersById;
    private ArrayList<String> papersByName;

    private static int maxId;

    public Author(String name, int id) {
        super(name, id);
        papersById = new ArrayList<Integer>();
        papersByName = new ArrayList<String>();

        if (maxId < id) maxId = id;

    }

    //Pre: Cert.
    //Post: Retorna el valor de maxId del parametre implicit.
    public static int getMaxId() {
        return maxId;
    }

    public HashMap<Integer, Paper> getPapersById(HashMap<Integer,Paper> papers) {
        HashMap<Integer,Paper> papersById = new HashMap<Integer,Paper>();
        for(int i = 0; i < this.papersById.size(); i++){
            int keyId = this.papersById.get(i);
            papersById.put(keyId, papers.get(keyId));
        }
        return papersById;
    }

    public HashMap<String, Paper> getPapersByName(HashMap<Integer,Paper> papers) {
        HashMap<String,Paper> papersByName = new HashMap<String,Paper>();
        for(int i = 0; i < this.papersByName.size(); i++){
            String keyName = this.papersByName.get(i);
            papersByName.put(keyName, papers.get(keyName));
        }
        return papersByName;
    }

    public ArrayList<Integer> getAuthorRelations(){
        return papersById;
    }

    public void addPaper(Paper paper) {
        papersById.add(paper.getId());
        papersByName.add(paper.getName());
    }

    public void removePaper(Paper paper) {
        for(int i = 0; i < papersById.size(); i++){
            if(papersById.get(i) == paper.getId()){
                papersById.remove(i);
                papersByName.remove(i);
                break;
            }
        }
    }

    public void sortPapers(){
        Collections.sort(papersById);
        Collections.sort(papersByName);
    }

}
