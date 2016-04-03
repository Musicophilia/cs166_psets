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
    private String cartesianHashCode(float[] elems){
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
    * A function to test components of this file
    */
    public void test(){
        System.out.print("Testing Fischer-Heun\n");
        float[] elems = {32, 45, 16, 18, 9, 33};
        String res = this.cartesianHashCode(elems);
        System.out.format("hash code: %s\n", res);

        elems[0] = 50;
        res = this.cartesianHashCode(elems);
        System.out.format("hash code: %s\n", res);
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
