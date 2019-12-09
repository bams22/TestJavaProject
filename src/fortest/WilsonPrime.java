package fortest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class WilsonPrime {
	final static int NUM_OF_THREADS = Runtime.getRuntime().availableProcessors();
	@SuppressWarnings("deprecation")
	public static boolean am_i_wilson(int n) {
		BigDecimal devident = new BigDecimal((factorial(n-1).add(new BigInteger("1"))).toString());
		devident.setScale(4);
		BigDecimal resultA = devident.divide(new BigDecimal(n * n), 2, BigDecimal.ROUND_HALF_UP);
		BigDecimal resultB = new BigDecimal(resultA.toString()).setScale(0, BigDecimal.ROUND_HALF_UP);
		
		double eps = resultA.subtract(resultB).doubleValue();
		return (Math.abs(eps) < 0.001);
	}
	
	static BigInteger factorial (int d) {
		BigInteger result = new BigInteger("1");
		for (int i = 2; i <= d; i++) {
			result = result.multiply(new BigInteger(Integer.toString(i)));
		}
		return result;
	}
	
	public static void writeOutFile(Object obj, File file) {
		FileWriter fileWriter = null;
		try {
			file.createNewFile();
			fileWriter = new FileWriter(file);
			fileWriter.write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
