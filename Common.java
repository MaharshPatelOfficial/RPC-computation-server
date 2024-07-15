import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * Implementation of the ICommon interface methods for remote computation.
 */
public class Common implements ICommon {
    
    public Common() {}

    /**
     * Adds two integers.
     * 
     * @param x The first integer.
     * @param y The second integer.
     * @return The sum of x and y.
     * @throws RemoteException if a remote communication error occurs.
     */
    @Override
    public int add(int x, int y) throws RemoteException {
        return x + y;
    }

    /**
     * Sorts an array of integers in ascending order.
     * 
     * @param ar The array to be sorted.
     * @return The sorted array.
     * @throws RemoteException if a remote communication error occurs.
     */
    @Override
    public int[] sort(int[] ar) throws RemoteException {
        Arrays.sort(ar);
        return ar;
    }
}
