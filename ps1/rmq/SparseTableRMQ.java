package rmq;
import java.util.*;
/**
 * An &lt;O(n log n), O(1)&gt; implementation of RMQ that uses a sparse table
 * to do lookups efficiently.
 *
 * You will implement this class for problem 3.ii of Problem Set One.
 */
public class SparseTableRMQ implements RMQ {
    /**
     * Creates a new SparseTableRMQ structure to answer queries about the
     * array given by elems.
     *
     * @elems The array over which RMQ should be computed.
     */
    private int[] logMap;
    private int[] powerMap;
    private int[][] sparseTable;
    private float[] elements;
    public SparseTableRMQ(float[] elems) {
        // TODO: Implement this!
        elements = elems.clone();
        int numElems = elems.length;
        logMap = new int[numElems+1];
        powerMap = new int[numElems+1]; // initialized too big, just to be safe
        
        int curPowTwo = 1;
        int pow = 0;
        for (int i=0; i <= numElems; i++) {
            logMap[i] = pow;
            if (i == curPowTwo * 2) {
                powerMap[pow] = curPowTwo;
                curPowTwo *= 2;
                pow++;

            }
        }

        int numCols = logMap[numElems];
        sparseTable = new int[numElems][numCols];

        for (int col=0; col < numCols; col++) {
            int lastRow = numElems - powerMap[col];
            for (int row=0; row <= lastRow; row++) {
                if (col ==0) {
                    sparseTable[row][col] = row;
                } else {
                    int rangeTwoRow = row+powerMap[col-1];
                    if (elems[sparseTable[row][col-1]] <= elems[sparseTable[rangeTwoRow][col-1]]) {
                        sparseTable[row][col] = sparseTable[row][col-1];
                    } else {
                        sparseTable[row][col] = sparseTable[rangeTwoRow][col-1];
                    }
                }
            }
        }


        for (int col=0; col < numCols; col++) {
            int lastRow = numElems - powerMap[col];
            for (int row=0; row <= lastRow; row++) {
                System.out.format("%s \n", sparseTable[row][col]);
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

        
        int k = logMap[j-i+1];
        System.out.format("i=%s, j=%s, k=%s\n", i, j, k);
        System.out.format(Arrays.toString(sparseTable));
        int firstIndex = sparseTable[i][i + k];
        System.out.format("elements size=%s, sparseTable=%s, firstIndex=%s \n", elements.length, sparseTable.length, firstIndex);
    
        float firstVal = elements[firstIndex];
        float secVal = elements[sparseTable[j - powerMap[k] + 1][j]];
        if (firstVal <= secVal) {
            return sparseTable[i][i + k];
        } else {
            return sparseTable[j - powerMap[k] + 1][j];
        }
    }
}
