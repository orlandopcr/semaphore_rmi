

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Inter extends Remote {

    void request(int id,int seq) throws RemoteException;
    boolean waitToken() throws RemoteException;
    void takeToken(Token token, int id) throws RemoteException;
    void kill() throws RemoteException;

    int loginRed(int id, int total) throws RemoteException;
    Token getToken(int id) throws RemoteException;
    boolean terminar() throws RemoteException;

}