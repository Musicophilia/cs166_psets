import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Random;

import rmq.*;
import driver.*;

public class testDriver {
	public static void main(String[] args) {
		/* We should either get 1 or 2 arguments. The arguments will be the
		 * name of the RMQ class to run and (optionally) a random seed.
		 */

		float[] elems = {32, 45, 16, 18, 9, 33};
		FischerHeunRMQ fh = new FischerHeunRMQ(elems);
		fh.test();
	}
}
