import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        //Asignacion de parametros
        Integer id  =  Integer.parseInt(args[0]);
        Integer total = Integer.parseInt(args[1]);
        Integer delay = Integer.parseInt(args[2]);
        boolean bearer = Boolean.getBoolean(args[3]);

        //Declaracion de variables
        String entrada = "";
        Scanner entradaEscaner = new Scanner (System.in);

        try {
            //Coneccion con servidor
            Registry registry = LocateRegistry.getRegistry();
            Inter serv = (Inter) registry.lookup("Test");

            // peticion de id incremental al server
            int check_id = serv.loginRed(id, total);

            if (check_id == -1){
                System.out.println("id repetido");
                return;
            }
            else {
                System.out.println("id registrado: " + check_id);
            }

            //Se solicita token
            Thread.sleep(delay);
            System.out.print("Solicitando Token");
            serv.request(id,1);//hay que cambiar ese 1

            do{

                //Hacer algo hasta que se obtenga el token

            }while (true);

            //Usar Token (Seccion Critica)

            //Avisar que ya se uso el token

            //Comprobar si ya todos usaron el token

            // Matar el proceso remoto con kill()

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}