

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestRemote extends Remote {

    String sayHello(String name) throws RemoteException;

    int loginRed(String id, String total) throws RemoteException;

    int requestToken(String id) throws  RemoteException;

}