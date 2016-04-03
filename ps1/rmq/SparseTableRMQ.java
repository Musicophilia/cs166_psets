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
    private int[] logUpMap; // rounds up
    private int[] logDownMap; // rounds down
    private int[] powerMap;
    private int[][] sparseTable;
    private float[] elements;


    private void initLogPowerMaps(int numElems) {
        logUpMap = new int[numElems+1];
        logDownMap = new int[numElems+1];
        powerMap = new int[numElems+1]; // to store the powers of 2. initialized too big, just to be safe
        
        int curPowTwo = 1;
        int pow = 0;
        for (int i=0; i <= numElems; i++) {
           if (i == 0) {
            logUpMap[i] = pow;
            powerMap[pow] = curPowTwo;
           } else if (i == curPowTwo * 2) {
                curPowTwo *= 2;
                pow++;
                powerMap[pow] = curPowTwo;
                logUpMap[i] = pow;
            } else {
                logUpMap[i] = pow + 1;
            }
            logDownMap[i] = pow;
        }
    }

    public SparseTableRMQ(float[] elems) {
        // TODO: Implement this!
        elements = elems.clone();
        int numElems = elems.length;
        // System.out.println("Number of elements " + Integer.toString(numElems));
        initLogPowerMaps(numElems);

        int highestPow = logDownMap[numElems];
        // System.out.print(numCols);
        int numCols = highestPow + 1;
        sparseTable = new int[numElems][numCols];
        // System.out.println("numCols " + numCols);
        for (int col=0; col < numCols; col++) {
            int lastRow = numElems - powerMap[col];
            // System.out.println("last row " + Integer.toString(lastRow));
            for (int row=0; row <= lastRow; row++) {
                if (col==0) {
                    // dp base case
                    // System.out.println("row " + row);
                    sparseTable[row][col] = row;
                } else {
                    int rangeTwoRow = row+powerMap[col-1];
                    // System.out.println("range 2 row " + rangeTwoRow);
                    if (elems[sparseTable[row][col-1]] <= elems[sparseTable[rangeTwoRow][col-1]]) {
                        sparseTable[row][col] = sparseTable[row][col-1];
                    } else {
                        sparseTable[row][col] = sparseTable[rangeTwoRow][col-1];
                    }
                }
            }
        }
    }

    private void printTable() {
        int numRows = sparseTable.length;
        int numCols = sparseTable[0].length;
        System.out.format("numRows=%s, numCols=%s\n", numRows, numCols);

        for (int row=0; row<numRows; row++) {
            for (int col=0; col<numCols; col++) {
                System.out.print(sparseTable[row][col] + " ");
            }
            System.out.print("\n");
        }
    }

    private void printElements() {
        int numElems = elements.length;
        for (int i=0; i<numElems; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.print("\n");

    }

    /**
     * Evaluates RMQ(i, j) over the array stored by the constructor, returning
     * the index of the minimum value in that range.
     */
    @Override
    public int rmq(int i, int j) {
        // TODO: Implement this!
        int k = logDownMap[j-i+1];

        // debug code
        int numElems = elements.length;
        // System.out.format("i=%s, j=%s, k=%s\n", i, j, k);
        // printElements();
        // printTable();
        // for (int col=0; col < numCols; col++) {
        //     int lastRow = numElems - powerMap[col] -1;
        //     for (int row=0; row <= lastRow; row++) {
        //         System.out.format("%s \n", sparseTable[row][col]);
        //     }
        // }

        int firstRangeMinIndex = sparseTable[i][k];
        int secRangeMinIndex = sparseTable[j - powerMap[k] + 1][k];
        // System.out.format("elements size=%s, sparseTable=%s, firstIndex=%s \n", elements.length, sparseTable.length, firstIndex);
    
        float firstRangeMin = elements[firstRangeMinIndex];
        float secRangeMin = elements[secRangeMinIndex];

        // System.out.print("\n\n");
        if (firstRangeMin <= secRangeMin) {
            return firstRangeMinIndex;
        } else {
            return secRangeMinIndex;
        }

    }
}
