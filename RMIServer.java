/*
 * @auther - Aditya Sonone, 1002040669
 * Project 1 - Part 3
 * this is a server application using java RMI RPC
 * before running the server app please run "start rmiregistry 1234" on command prompt
 */
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class RMIServer{

    public RMIServer() {}

    public static void main(String[] args) {
        System.out.println("Server started");
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        try{
            // Object which is going to provide service
            Common op = new Common();
            // Export remote object to Java RMI runtime so that it can receive incoming calls
            ICommon obj = (ICommon)UnicastRemoteObject.exportObject(op, 0);
            // create a java RMI registry and bind the remote object with a name which client can refer 
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",1234);
            reg.rebind("op", obj);
            System.err.println("Server ready");
        }catch(Exception e){
            System.err.println("Exception on server: " + e.toString());
            e.printStackTrace();
        }
    }
}
