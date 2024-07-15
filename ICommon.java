import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 * Common interface for remote computation operations between client and server applications.
 * Defines methods for addition of two numbers and sorting of an integer array.
 * 
 * This interface extends Remote to support remote method invocation (RMI).
 * 
 */
public interface ICommon extends Remote {
    
    /**
     * Adds two integers.
     * 
     * @param x The first integer.
     * @param y The second integer.
     * @return The sum of x and y.
     * @throws RemoteException if a remote communication error occurs.
     */
    int add(int x, int y) throws RemoteException;

    /**
     * Sorts an array of integers in ascending order.
     * 
     * @param ar The array to be sorted.
     * @return The sorted array.
     * @throws RemoteException if a remote communication error occurs.
     */
    int[] sort(int[] ar) throws RemoteException; 
}
