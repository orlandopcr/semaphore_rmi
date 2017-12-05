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

    Server(int n){
        while(last.size() < n) last.add(0);
        while(req.size() < n) req.add(0);
    }

    public int loginRed(int id, int total) throws RemoteException {
        if (nodos.size()!=0){
            for(int i = 0; i < nodos.size(); i = i+1){
                if(id == nodos.get(i)){
                    return -1;
                }
            }
            nodos.add(id);
        }
        else {
            nodos.add(id);
        }



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

    public Token getToken(int id) throws RemoteException {
        last.set(id, 1);
        return token;
    }

    public void request(int id,int seq) throws RemoteException{
        if (!requests.contains(id))requests.add(id);
        System.out.println("cola de peticiones: "+ requests);
    }

    //Verifica si se debe esperar por el token
    public boolean waitToken() throws RemoteException{
        System.out.println("quien tiene el toke:" + last);
        //Si debe esperar por el token se retorna true
        for (int i = 0 ; i<last.size();i=i+1){
            if (last.get(i)!=0){
                return true;
            }
        }

        if (token==null) return true;

        //Si el token puede ser recibido se retorna false

        return false;
    }

    //Recive token actualizado desde un nodo
    public void takeToken(Token tok, int id) throws RemoteException{
        token=tok;
        last.set(id,0);
        requests.remove(id);
    }

    //Termina proceso servidor
    public void kill() throws RemoteException {}

    //revisa si todos los nodos terminaron su SC para poder terminar el algoritmo
    public boolean terminar() throws RemoteException{
        for(int i=0;i<token.listos.size();i++){
            System.out.println(i);
            if (token.listos.get(i)==0) return false;
        }
        return true;
    }


    public static void main(String[] args) throws RemoteException, AlreadyBoundException {


        System.out.println("Iniciando Servidor...");

        Inter serv= (Inter) new Server(Integer.parseInt(args[0])) ;
        Remote stub = UnicastRemoteObject.exportObject(serv, 0);

        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.bind("Test", stub);

        System.out.println("Servidor iniciado Correctamente.");



    }

}