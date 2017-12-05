import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        //Asignacion de parametros
        Integer id  =  Integer.parseInt(args[0]);
        Integer total = Integer.parseInt(args[1]);
        Integer delay = Integer.parseInt(args[2]);
        boolean bearer = Boolean.parseBoolean(args[3]);

        //Declaracion de variables
        String entrada = "";
        Scanner entradaEscaner = new Scanner (System.in);


        int seq=0;
        Token token=null;


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

            entradaEscaner.nextLine();

            if (bearer){
                token=new Token("aaa",total);
            }
            System.out.println(bearer);
            while (token==null){

                //Se solicita token
                Thread.sleep(delay);
                seq++;
                System.out.println("Solicitando Token... peticion: "+seq);
                serv.request(id,seq);

                //Se verifica si el token puede ser recibido
                if(!serv.waitToken()){
                    token=serv.getToken(id);
                    System.out.println("Token recibido con exito!");
                    break;
                }

            }

            //Usar Token (Seccion Critica)
            System.out.print("Entrando en seccion critica...");
            System.out.println(token.getInf());//Imprime informacion del token
            token.usar(id);
            Thread.sleep(delay);
            System.out.print("Fin seccion critica");

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