import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        Inter serv = (Inter) registry.lookup("Test");
        //System.out.println(testRemote.sayHello("JavaMexico"));

        String id  =  args[0];
        String total = args[1];
        String delay = args[2];
        String bearer = args[3];



        // peticion de id incremental al server, despues hay que tomar la de la linea de comandos
        serv.loginRed(id, total);

        System.out.println("id registrado al proceso:  " + id);
        System.out.println(serv.getToken().inf);

        //para que el proceso quede abierto
        String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in);
        entradaTeclado = entradaEscaner.nextLine ();
    }

}