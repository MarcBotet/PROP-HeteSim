package cluster.Nodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Term extends Node {
    public ArrayList<Integer> papersWhichTalkAboutThisById;
    public ArrayList<String> papersWhichTalkAboutThisByName;

    private static int maxId;


    public Term(String name, int id) {
        super(name, id);
        papersWhichTalkAboutThisById = new ArrayList<Integer>();
        papersWhichTalkAboutThisByName = new ArrayList<String>();

        if (maxId < id) maxId = id;

    }

    //Pre: Cert.
    //Post: Retorna el valor de maxId del parametre implicit.
    public static int getMaxId() {
        return maxId;
    }

    public HashMap<Integer, Paper> getPapersWhichTalkAboutThisById(HashMap<Integer,Paper> papers) {

        HashMap<Integer,Paper> papersWhichTalkAboutThisById = new HashMap<Integer,Paper>();
        for(int i = 0; i < this.papersWhichTalkAboutThisById.size(); i++){
            int keyId = this.papersWhichTalkAboutThisById.get(i);
            papersWhichTalkAboutThisById.put(keyId, papers.get(keyId));
        }
        return papersWhichTalkAboutThisById;
    }

    public HashMap<String, Paper> getPapersWhichTalkAboutThisByName(HashMap<Integer,Paper> papers) {
        HashMap<String,Paper> papersWhichTalkAboutThisByNam = new HashMap<String,Paper>();
        for(int i = 0; i < this.papersWhichTalkAboutThisByName.size(); i++){
            String keyName = this.papersWhichTalkAboutThisByName.get(i);
            papersWhichTalkAboutThisByNam.put(keyName, papers.get(keyName));
        }
        return papersWhichTalkAboutThisByNam;
    }

    public void addPaperWhichTalkAboutIt(Paper paper) {
        papersWhichTalkAboutThisById.add(paper.getId());
        papersWhichTalkAboutThisByName.add(paper.getName());
    }

    public void removePaperWhichTalkAboutIt(Paper paper) {
        for(int i = 0; i < papersWhichTalkAboutThisById.size(); i++){
            if(papersWhichTalkAboutThisById.get(i) == paper.getId()){
                papersWhichTalkAboutThisById.remove(i);
                papersWhichTalkAboutThisByName.remove(i);
                break;
            }
        }
    }

    public ArrayList<Integer> getPapersWhichTalkAboutThis(){
        return papersWhichTalkAboutThisById;
    }

    public void sortPapersWhichTalkAboutThis(){
        Collections.sort(papersWhichTalkAboutThisById);
        Collections.sort(papersWhichTalkAboutThisByName);
    }
}
