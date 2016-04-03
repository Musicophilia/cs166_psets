package rmq;
import java.util.*;
/**
 * An &lt;O(n<sup>2</sup>), O(1)&gt; implementation of RMQ that precomputes the
 * value of RMQ_A(i, j) for all possible i and j.
 *
 * You will implement this class for problem 3.i of Problem Set One.
 */
public class PrecomputedRMQ implements RMQ {
	
    // private ArrayList<ArrayList<Float>> dpTable = new ArrayList<ArrayList<Float>>();
    private int[][] dpTable;
    /**
     * Creates a new PrecomputedRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    public PrecomputedRMQ(float[] elems) {
    	// TODO: Implement this! 
        int numElems = elems.length;
        dpTable = new int[numElems][numElems];
        // i is the offset
        // j is the start index for a given range
        // j + i is the end index
        for (int i=0; i < numElems; i++) {
            for (int j=0; j < numElems; j++) {
                if (i==0) {
                    dpTable[j][j+i] = j;
                } else if (j+1 < numElems && j+i < numElems) {
                    if (elems[dpTable[j][j+i-1]] <= elems[dpTable[j+1][j+i]]) {
                        dpTable[j][j+i] = dpTable[j][j+i-1];
                    } else {
                        dpTable[j][j+i] = dpTable[j+1][j+i];
                    }
                }
                // dpTable[j][j+i] = 0;
            }
        }
    }

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */
    @Override
    public int rmq(int i, int j) {
    	// TODO: Implement this!
    	return dpTable[i][j];
    }
}
