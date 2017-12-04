import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Token implements Serializable {
    public String inf ;
    Integer[] listos;

    Token(String info, int total){
        inf ="Informacion Token";
        listos = new Integer[total+1];
        listos[0]=1;
    }

    public void usar(int id){
        listos[id]=1;
    }


}
