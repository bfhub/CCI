import java.util.Arrays;

public class Moderate {

	// Number Swapper
	// swap a number in place
	static int[] numberSwapper (int[] array) {
		
		array[0] = array[0]^array[1];
		array[1] = array[0]^array[1];
		array[0] = array[0]^array[1];
		
		return array;
	}
	 
	 // Factorial zeros
	static int factorialZeros (int num) {
			
		// to count the number of trailing zeros, we need to count the pairs of multiples of 5 and 2; there will be more multiples of 2 than 5, 
		// so we can count the number of multiples of 5
		int count = 0;
		for ( int i = 2; i <= num; i++ ) {
			count += countfactOf5 (i);
		}
		
		return count;
	}
	
	
	static int countfactOf5(int i) {
		// TODO Auto-generated method stub
		int count = 0;
		while ( i % 5 == 0 ) {
				count++;
				i /= 5;
			}
		return count;
	}
	
	// smallest difference
	public static int smallestDifference (int[] array1, int[] array2) {
		
		Arrays.sort(array1);
		Arrays.sort(array2);
		
		int a = 0;
		int b = 0;
		int diff = Integer.MAX_VALUE;
		
		while ( a < array1.length && b < array2.length ) {
			if ( Math.abs(array1[a] - array2[b]) < diff ) {
				diff = Math.abs(array1[a] - array2[b]);
			}
			
			if ( array1[a] < array2[b] ) {
				a++;
			} else {
				b++;
			}
		}
		return diff;
	}

	public static void main ( String[] args ) {
		
		// number swapper
		int[] input = {4, 9};
		int[] output = numberSwapper(input);
		System.out.println(output[0]);
		System.out.println(output[1]);
		
		// Factorial zeros
		System.out.println("factorialZeros: " + factorialZeros(19));
		
		// smallest difference
		int[] array1 = {1,3,15,11,2};
		int[] array2 = {23,127,235,19,8};
		System.out.println("smallestDifference: " + smallestDifference(array1,array2));
		
	}
}
