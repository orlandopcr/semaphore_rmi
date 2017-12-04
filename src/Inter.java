

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Inter extends Remote {

    void request(int id,int seq) throws RemoteException;
    void waitToken() throws RemoteException;
    void takeToken(int token) throws RemoteException;
    void kill() throws RemoteException;

    int loginRed(String id, String total) throws RemoteException;
    Token getToken() throws RemoteException;

}