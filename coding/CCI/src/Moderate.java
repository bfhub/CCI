import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Moderate {

	/*
	 * 16.1 Number Swapper: write a function to swap two numbers in place.
	 */
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
	
	// Operations : subtraction; Multiplication; division
	// write methods to implements the multiply, divide and subtract for integers, use only add operator.
	// implement negate method which flip the negative number to positive, and positive number to negative
	static int negate ( int num ) {
		
		int neg = 0;
		int newNum = (num < 0) ? 1 : -1;
		while ( num != 0 ) {
			neg += newNum;
			num += newNum;
		}
		
		return neg;
	}
	
	static int abs ( int num ) {
		
		if ( num < 0 ) {
			return negate(num);
		}
		
		return num;
	}
	
	static // subtraction
	int minus ( int a, int b ) {
		return a + negate(b);
	}
	
	static int multiply ( int a, int b ) {
		if ( a < b ) {
			return multiply(b,a);
		}
		
		// for loop
		int sum = 0;
		for ( int i = abs(a); i > 0; i =  minus(i,1)) {
			sum += b;
		}
		
		if ( a < 0 ) {
			return negate(sum);
		}
		return sum;
	}
	
	static int divide ( int a, int b ) {
		
		int a_abs = abs(a);
		int b_abs = abs(b);
		
		if ( a_abs < b_abs ) {
			return divide(b,a);
		}
		
		int count = 0;
		while (a_abs > 0 ) {
			count++;
			a_abs -= b_abs;
		}
		
		if ( (a < 0 && b > 0) || (a > 0 && b < 0) ) {
			return negate(count);
		}
		
		return count;
	}
	
	/*
	 * 16.21 Sum Swap Given two arrays, find a pair of values (one value from each array) that you can
	 * swap to give the two arrays the same sum.
	 * 
	 * 		1. the target sum is (sum1 + sum2 / 2 (which sumx is the sum of each array)
	 * 			1.1. getTargetSum(array1, array2)
	 * 
	 * 		2. use a HashSet to drop the duplicates of one of the arrays
	 * 			2.1 getContents(arrayx)
	 * 
	 */
	public static int[] sumSwap (int[] array1, int[] array2) {
		
		int[] result = new int[2];
		
		Integer target = getTarget(array1, array2);
		if ( target == null ) {
			return null;
		}
		
		HashSet<Integer> set = getContents(array2);
		
		for ( int one = 0; one < array1.length; one++ ) {
			int two = target - sum(array1) + array1[one];
			if (set.contains(two)) {
				result[0] = array1[one];
				result[1] = two;
			}		
		}	
		return result != null ? result : null ;
	}
	
	private static HashSet<Integer> getContents(int[] array2) {
		HashSet<Integer> set = new HashSet<>();
		
		for ( int i = 0; i < array2.length; i++) {
			set.add(array2[i]);
		}
		return set;
	}

	private static Integer getTarget(int[] array1, int[] array2) {
		int sum1 = sum(array1);
		int sum2 = sum(array2);
		
		if (( sum1 + sum2) % 2 != 0 )
			return null;
		
		return ( sum1 + sum2) / 2;
	}
	

	private static Integer sum(int[] array1) {
		Integer sum = 0;
		
		for ( int i = 0; i < array1.length; i++ ) {
			sum += array1[i];
		}
		
		return sum;
	}

	/*
	 * 16.24 Pairs with Sum: 
	 * Design an algorithm to find all pairs of integers within an array which sum to a specified value.
	 * 
	 */
	public static ArrayList<ArrayList<Integer>> printPairSums ( int[] array, int sum ) {
		Arrays.sort(array);
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		ArrayList<Integer> temp = new ArrayList<>();
		
		int low = 0;
		int high = array.length - 1;
		
		while ( low < high ) {
			
			if ( array[low] + array[high] == sum ) {
				temp.add(array[low]);
				temp.add(array[high]);
				result.add(temp);
				temp = new ArrayList<>();
				high--;
				low++;
			} else if ( array[low] + array[high] < sum ) {
				low++;
			} else {
				high--;
			}
		}
		return result;
	}

	public static void main ( String[] args ) {
		
		// number swapper
		//int[] input = {4, 9};
		//int[] output = numberSwapper(input);
		//System.out.println(output[0]);
		//System.out.println(output[1]);
		
		// Factorial zeros
		//System.out.println("factorialZeros: " + factorialZeros(19));
		
		// smallest difference
		//int[] array1 = {1,3,15,11,2};
		//int[] array2 = {23,127,235,19,8};
		//System.out.println("smallestDifference: " + smallestDifference(array1,array2));
		
		// test negate
		//System.out.println("negate(-9) is " + negate(-9) + ", " + "negate(2) is " + negate(2)); 
		//System.out.println("minus(-3,-4) is " + minus(-3,-4) + ", " + "minus(2, -9) is " + minus(2, -9) + ", " + "minus(-6, 9) is " + minus(-6, 9)); 
		//System.out.println("multiply(-3,-4) is " + multiply(-3,-4) + ", " + "multiply(2, -9) is " + multiply(2, -9) + ", " + "multiply(6, 9) is " + multiply(6, 9));
		//System.out.println("divide(-2,-4) is " + divide(-2,-4) + ", " + "divide(3, -9) is " + divide(3, -9) + ", " + "divide(18, 9) is " + divide(18, 9)); 
		
		// test pairs with sum
		//int[] arrayPS = {-2, -1, 0, 3, 5, 6, 7, 9, 13, 14};
		//System.out.println(printPairSums(arrayPS, 7));
		
		/*
		 *  16.21 Sum Swao
		 */
		int[] array1 = {4, 1, 2, 1, 1, 2};
		int[] array2 = {3, 6, 3, 3};
		
		// sum 
		System.out.println("sum1 is: " + sum(array1));
		System.out.println("sum2 is: " + sum(array2));
		
		// getTarget
		Integer target = getTarget(array1, array2);
		System.out.println("target is: " + target);
		
		// getContents
		System.out.println("set is: " + getContents(array2));
		int[] result = sumSwap(array1, array2);
		if (result == null ) {
			System.out.println("result is null.");
		} else {
			for ( int i = 0; i < result.length; i++ ) {
				System.out.println(result[i] + " ");
			}
		}
		
		
	}
}
