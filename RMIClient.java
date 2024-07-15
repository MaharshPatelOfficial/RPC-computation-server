import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Client application for performing remote method invocations (RMI) on a computation server.
 * 
 * Before running the client application, ensure the RMI registry is started on the server:
 * Run "start rmiregistry 1234" on the command prompt, where 1234 is the port number.
 * 
 * This application provides options to:
 * 1. Add two numbers synchronously.
 * 2. Sort an array of 5 elements synchronously.
 * 3. Add two numbers asynchronously using a thread pool.
 * 4. Sort an array of 5 elements asynchronously using a thread pool.
 *
 */
public class RMIClient {

    private RMIClient() {}

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            
            // Get the registry on the server's host
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",1234);
            
            // Look for the remote object by name
            ICommon obj = (ICommon) reg.lookup("op");
            
            int choice;
            do {
                System.out.println("1. Add two numbers (Synchronously).");
                System.out.println("2. Sort array of 5 elements (Synchronously).");
                System.out.println("3. Add two numbers (Asynchronously).");
                System.out.println("4. Sort array of 5 elements (Asynchronously).");
                System.out.println("Anything else to Exit");
                choice = sc.nextInt();
                
                switch (choice) {
                    // Section to perform addition on the server side synchronously
                    case 1:
                        System.out.println("Enter two numbers:");
                        int x = sc.nextInt();
                        int y = sc.nextInt();
                        int r1 = obj.add(x, y);
                        System.out.println("Addition: " + r1);
                        break;
                    
                    // Section to perform sorting on the server side synchronously
                    case 2:
                        System.out.println("Enter 5 elements:");
                        int[] ar = new int[5];
                        for(int i=0; i<5; i++) {
                            ar[i] = sc.nextInt();
                        }
                        int[] r2 = obj.sort(ar);
                        System.out.println("Sorted array:");
                        for (int j = 0; j < 5; j++) {
                            System.out.print(r2[j] + " ");
                        }
                        System.out.println();
                        break;
                    
                    // Section to perform addition on the server side asynchronously
                    case 3:
                        System.out.println("Enter two numbers:");
                        int a = sc.nextInt();
                        int b = sc.nextInt();
                        
                        // Create a thread pool to invoke the remote operation
                        ExecutorService threadPool = Executors.newCachedThreadPool();
                        Future<Integer> ft = threadPool.submit(() -> obj.add(a, b));
                        
                        // Check if response received right away
                        if(ft.isDone()) {
                            System.out.println("Addition: " + ft.get());
                        } else {
                            System.out.println("Some other work is going on");
                            Thread.sleep(5000);
                            
                            // Check again after completing other work 
                            if(ft.isDone()) {
                                System.out.println("Addition: " + ft.get());
                            }
                        }          
                        break;
                    
                    // Section to perform sorting on the server side asynchronously
                    case 4:
                        System.out.println("Enter 5 elements:");
                        int[] ar2 = new int[5];
                        for(int i=0; i<5; i++) {
                            ar2[i] = sc.nextInt();
                        }
                        
                        // Create a thread pool to invoke the remote operation
                        ExecutorService threadPool1 = Executors.newCachedThreadPool();
                        Future<int[]> ft1 = threadPool1.submit(() -> obj.sort(ar2));
                        
                        // Check if response received right away
                        if(ft1.isDone()) {
                            System.out.println("Sorted array:");
                            for (int j = 0; j < 5; j++) {
                                System.out.print(ft1.get()[j] + " ");
                            }
                            System.out.println();
                        } else {
                            System.out.println("Some other work is going on");
                            Thread.sleep(5000);
                            
                            // Check again after completing other work 
                            if(ft1.isDone()) {
                                System.out.println("Sorted array:");
                                for (int j = 0; j < 5; j++) {
                                    System.out.print(ft1.get()[j] + " ");
                                }
                                System.out.println();
                            }
                        }     
                        break;
                    
                    default:
                        choice = 0;
                        break;
                }
            } while (choice != 0);
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
