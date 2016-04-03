package rmq;
import java.util.*;
/**
 * An &lt;O(n), O(1)&gt; implementation of the Fischer-Heun RMQ data structure.
 *
 * You will implement this class for problem 3.iv of Problem Set One.
 */
public class FischerHeunRMQ implements RMQ {
    /**
     * Creates a new FischerHeunRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public FischerHeunRMQ(float[] elems) {
        // TODO: Implement this!
    }

    /**
    * gets the hashcode for the cartesian tree associated with the ArrayList associated with elems.
    */
    public String cartesianHashCode(float[] elems){
        BitSet result = new BitSet(elems.length);
        Integer hashCodeIndex = 0;
        Deque<Float> stack = new ArrayDeque<Float>();

        for (Float elem:elems){
            while (!stack.isEmpty() && stack.peek() > elem){
                stack.pop();
                hashCodeIndex++; //advance the cursor (implicitly setting this bit to 0)
            }
            stack.push(elem);
            result.set(hashCodeIndex); //set this bit to 1
            hashCodeIndex++;   
        }
        return result.toString();

    }
    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */
    @Override
    public int rmq(int i, int j) {
        // TODO: Implement this!
        return -1;
    }
}
