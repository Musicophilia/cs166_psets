package rmq;

/**
 * An &lt;O(n), O(log n)&gt; implementation of the RMQ as a hybrid between
 * the sparse table (on top) and no-precomputation structure (on bottom)
 *
 * You will implement this class for problem 3.iii of Problem Set One.
 */
public class HybridRMQ implements RMQ {
    /**
     * Creates a new HybridRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */

	// Instance Variables
    private SparseTableRMQ topRMQ;
    private float[] elements; // to store a copy of the elements
    private int n = 0; // number of elements being passed into this RMQ
    private int b = 0; // block size
    
    /**
     * Computes the minima for each block and returns the top level
     * elements (block minima) as an array of floats.
     */
    private float[] computeBlockMins() {
    	int numTopElems = (int) n/b;
    	if (n%b != 0) numTopElems++; // if n is not multiple of b, there is one more block
    	float[] topElems = new float[numTopElems];
    	float curMin = Float.MAX_VALUE;
    	for (int i = 0; i < n; i++) {
    		if (elements[i] < curMin) {
    			curMin = elements[i];
    		}
    		if ((i+1) % b == 0) {
    			topElems[i/b] = curMin;
    			curMin = Float.MAX_VALUE;
    		}
    	}
    	if (n%b != 0) topElems[numTopElems-1] = curMin;
    	return topElems;
    }
    
    public HybridRMQ(float[] elems) {
        elements = elems.clone();
        n = elems.length;
        b = (int) (Math.log(n)/Math.log(2)) + 1; // compute blocksize using log base 2, add 1 to avoid b = 0
        float[] topElems = computeBlockMins();
        topRMQ = new SparseTableRMQ(topElems);
    }
    
    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range, by using a linear search.
     * No pre-computation, used for bottom structure.
     */
    private int findMinIndex(int i, int j) {
    	float minValue = Float.MAX_VALUE;
        int minIndex = 0;
    	for (int k = i; k <= j; k++) {
            if (elements[k] < minValue) {
        		minValue = elements[k];
        		minIndex = k;
        	}
    	}
    	return minIndex;
    }

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */
    @Override
    public int rmq(int i, int j) {
//    	System.out.println("n=" + Integer.toString(n) + "\tb=" + Integer.toString(b) + "\ti=" + Integer.toString(i) +  "\tj=" + Integer.toString(j));
    	if (n == 0) return 0;
    	
    	int iTop = i/b + 1;
    	int jTop = j/b - 1;
    	boolean useTopRMQ = (iTop <= jTop);

        if (useTopRMQ) {
        	int iLeft = i;
        	int jLeft = i - i%b + b - 1;
        	if (jLeft < iLeft) jLeft = iLeft;
        	
        	int iRight = j - j%b;
        	int jRight = j;
        	if (iRight > jRight) iRight = jRight;
        	
        	int topMinIndexRaw = topRMQ.rmq(iTop, jTop) ;
        	int topMinIndex = findMinIndex(topMinIndexRaw * b, topMinIndexRaw * b + b - 1);
            int leftMinIndex = findMinIndex(iLeft, jLeft);
            int rightMinIndex = findMinIndex(iRight, jRight);

        	int[] allMinIndices = {topMinIndex, leftMinIndex, rightMinIndex};
            
            float minValue = Float.MAX_VALUE;
            int minIndex = 0;
            for (int k = 0; k < allMinIndices.length; k++) {
            	if (elements[allMinIndices[k]] < minValue) {
            		minValue = elements[allMinIndices[k]];
            		minIndex = allMinIndices[k];
            	}
            }
//            System.out.println("iTop=" + iTop + "\tjTop=" + jTop + "\tiLeft=" +iLeft+  "\tjLeft=" + jLeft + "\tiRight=" + iRight +"\tjRight=" + jRight);           
//            System.out.println("topMinIndex=" + topMinIndex + "\tleftMinIndex=" + leftMinIndex + "\trightMinIndex=" + rightMinIndex);
            return minIndex;
        }
        else {
        	return findMinIndex(i, j);
        }
    }
}
