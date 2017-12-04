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
    Token token;

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

        return token;
    }

    public void request(int id,int seq) throws RemoteException{
        requests.add(id);
        System.out.println("cola de peticiones: "+ requests);
    }

    //Verifica si se debe esperar por el token
    public boolean waitToken() throws RemoteException{
        //Si debe esperar por el token se retorna true
        //Si el token puede ser recibido se retorna false
        return false;
    }

    //Recive token actualizado desde un nodo
    public void takeToken(Token tok) throws RemoteException{
        token=tok;
    }

    //Termina proceso servidor
    public void kill() throws RemoteException {}

    //revisa si todos los nodos terminaron su SC para poder terminar el algoritmo
    public boolean terminar() throws RemoteException{
        for(int i=1;i<token.listos.length;i++){
            if (token.listos[i]==0) return false;
        }
        return true;
    }


    public static void main(String[] args) throws RemoteException, AlreadyBoundException {


        System.out.println("Iniciando Servidor...");

        Inter serv= (Inter) new Server() ;
        Remote stub = UnicastRemoteObject.exportObject(serv, 0);

        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.bind("Test", stub);

        System.out.println("Servidor iniciado Correctamente.");



    }

}