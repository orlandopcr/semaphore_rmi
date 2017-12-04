import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Token implements Serializable {
    public String inf ;
    ArrayList<Integer> listos;

    Token(String info, int total){
        inf =info;
        listos = new ArrayList<>(total);
        while(listos.size() < total) listos.add(0);

    }

    public void usar(int id){
        listos.set(id,1);
    }

    public String getInf() {
        return inf;
    }

}