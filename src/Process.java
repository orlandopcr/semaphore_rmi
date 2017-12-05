import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Process {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        //Asignacion de parametros
        Integer id  =  Integer.parseInt(args[0]);
        Integer total = Integer.parseInt(args[1]);
        Integer delay = Integer.parseInt(args[2]);
        boolean bearer = Boolean.parseBoolean(args[3]);

        //Declaracion de variables
        Scanner entradaEscaner = new Scanner (System.in);


        int seq=0;
        Token token=null;


        try {
            //Coneccion con servido
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
            System.out.println("Estado Semaforo: Verde\n");

            if (bearer){
                token=new Token("Informacion Token",total);
            }
            while (token==null){

                //Se solicita token
                Thread.sleep(delay);
                seq++;
                System.out.println("Solicitando Token... peticion: "+seq);
                serv.request(id,seq);
                System.out.println("Estado Semaforo: Amarillo\n");

                //Se verifica si el token puede ser recibido
                if(!serv.waitToken()){
                    token=serv.getToken(id);
                    System.out.println("Token recibido con exito!");
                    break;
                }

            }

            //Usar Token (Seccion Critica)
            System.out.println("Entrando en seccion critica...");
            System.out.println("Estado Semaforo: Rojo\n");
            System.out.println(token.getInf()+"\n");//Imprime informacion del token
            token.usar(id);
            Thread.sleep(delay);
            System.out.println("Fin seccion critica");
            System.out.println("Estado Semaforo: Verde\n");

            //Avisar que ya se uso el token y se devuelve
            serv.takeToken(token,id);

            //Comprobar si ya todos usaron el token
            if (serv.terminar()) {
                // Matar el proceso remoto con kill()
                serv.kill();
            }
        }
        catch (UnmarshalException e){
            System.out.println("Proceso finalizado con exito!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}