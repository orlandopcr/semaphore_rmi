import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Server implements Inter{

    ArrayList<Integer> nodos = new ArrayList();
    ArrayList<Integer> req = new ArrayList();
    ArrayList<Integer> last = new ArrayList();
    Queue<Integer> requests = new LinkedList();

    public int loginRed(int id, int total) throws RemoteException {

        for(int i = 0; i < nodos.size(); i = i+1){
            if(id == nodos.get(i)){
                return -1;
            }
        }

        nodos.add(id);

        if (nodos.size()== total){

            for (int i = 0 ; i < total; i = i+1){
                req.add(0);

            }
            for (int i = 0 ; i < total; i = i+1){
                last.add(0);
            }
            System.out.println("req:  " + req);
            System.out.println("last:  "+ last);

        }

        return id;
    }
    public Token getToken() throws RemoteException {

        return new Token("aaa",111);
    }
    public void request(int id,int seq) throws RemoteException{
        requests.add(id);
        System.out.println("cola de peticiones: "+ requests);
    }
    public void waitToken() throws RemoteException{}
    public void takeToken(int token) throws RemoteException{}
    public void kill() throws RemoteException {}


    public static void main(String[] args) throws RemoteException, AlreadyBoundException {


        System.out.println("Iniciando Servidor...");

        Inter serv= (Inter) new Server() ;
        Remote stub = UnicastRemoteObject.exportObject(serv, 0);

        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.bind("Test", stub);

        System.out.println("Servidor iniciado Correctamente.");



    }

}