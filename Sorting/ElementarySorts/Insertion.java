package elementarySorts;

import java.util.Comparator;
import java.util.Scanner;

/**
 * The Insertion class provides static methods for sorting an array using
 * insertion sort. The sorting algorithm is stable and uses O(1) extra memory.
 * 
 * The best case input is an array that is already sorted. In this case
 * insertion sort has a linear running time (i.e., O(n)). During each iteration,
 * the first remaining element of the input is only compared with the right-most
 * element of the sorted subsection of the array.
 * 
 * The worst case is an array sorted in reverse order. In these cases every iteration of the inner loop
 * will scan and shift the entire sorted subsection of the array before
 * inserting the next element. This gives insertion sort a quadratic running
 * time (i.e., O(n2)).
 * 
 * The average case is also quadratic, which makes insertion
 * sort impractical for sorting large arrays. However, insertion sort is one of
 * the fastest algorithms for sorting very small arrays, even faster than
 * quicksort;
 * 
 * indeed, good quicksort implementations use insertion sort for
 * arrays smaller than a certain threshold, also when arising as subproblems;
 * the exact threshold must be determined experimentally and depends on the
 * machine, but is commonly around ten.
 */
public class Insertion {

	// This class should not be instantiated.
	private Insertion() {
	}

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 * 
	 * @param a
	 *            the array to be sorted
	 */
	public static void sort(Comparable[] a) {
		int n = a.length;

		for (int i = 0; i < n; i++) {

			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {

				exch(a, j, j - 1);
			}
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
	}

	/**
	 * Rearranges the subarray a[lo..hi] in ascending order, using the natural
	 * order.
	 * 
	 * @param a
	 *            the array to be sorted
	 * @param lo
	 *            left endpoint
	 * @param hi
	 *            right endpoint
	 */
	public static void sort(Comparable[] a, int lo, int hi) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
			}
		}
		assert isSorted(a, lo, hi);
	}

	/**
	 * Rearranges the array in ascending order, using a comparator.
	 * 
	 * @param a
	 *            the array
	 * @param comparator
	 *            the comparator specifying the order
	 */
	public static void sort(Object[] a, Comparator comparator) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j > 0 && less(a[j], a[j - 1], comparator); j--) {
				exch(a, j, j - 1);
			}
			assert isSorted(a, 0, i, comparator);
		}
		assert isSorted(a, comparator);
	}

	/**
	 * Rearranges the subarray a[lo..hi] in ascending order, using a comparator.
	 * 
	 * @param a
	 *            the array
	 * @param lo
	 *            left endpoint
	 * @param hi
	 *            right endpoint
	 * @param comparator
	 *            the comparator specifying the order
	 */
	public static void sort(Object[] a, int lo, int hi, Comparator comparator) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j - 1], comparator); j--) {
				exch(a, j, j - 1);
			}
		}
		assert isSorted(a, lo, hi, comparator);
	}

	// return a permutation that gives the elements in a[] in ascending order
	// do not change the original array a[]
	/**
	 * Returns a permutation that gives the elements in the array in ascending
	 * order.
	 * 
	 * @param a
	 *            the array
	 * @return a permutation {@code p[]} such that {@code a[p[0]]},
	 *         {@code a[p[1]]}, ..., {@code a[p[n-1]]} are in ascending order
	 */
	public static int[] indexSort(Comparable[] a) {
		int n = a.length;
		int[] index = new int[n];
		for (int i = 0; i < n; i++)
			index[i] = i;

		for (int i = 0; i < n; i++)
			for (int j = i; j > 0 && less(a[index[j]], a[index[j - 1]]); j--)
				exch(index, j, j - 1);

		return index;
	}

	/***************************************************************************
	 * Helper sorting functions.
	 ***************************************************************************/

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	// is v < w ?
	private static boolean less(Object v, Object w, Comparator comparator) {
		return comparator.compare(v, w) < 0;
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	// exchange a[i] and a[j] (for indirect sort)
	private static void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/***************************************************************************
	 * Check if array is sorted - useful for debugging.
	 ***************************************************************************/
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	// is the array sorted from a[lo] to a[hi]
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	private static boolean isSorted(Object[] a, Comparator comparator) {
		return isSorted(a, 0, a.length - 1, comparator);
	}

	// is the array sorted from a[lo] to a[hi]
	private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1], comparator))
				return false;
		return true;
	}

	// print array to standard output
	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String[] a = sc.nextLine().split(" ");
		System.out.println("Unsorted: ");
		show(a);
		Insertion.sort(a);
		System.out.println("\nSorted: ");
		show(a);

	}
}
