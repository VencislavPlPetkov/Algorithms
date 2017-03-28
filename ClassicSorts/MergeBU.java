package classicSorts;

import java.util.Scanner;

/**
 * Bottom-up mergesort - The recursive implementation of mergesort is prototypical
 * of the divide-and-conquer algorithm design paradigm, where we solve a large
 * problem by dividing it into pieces, solving the subproblems, then using the
 * solutions for the pieces to solve the whole problem. Even though we are
 * thinking in terms of merging together two large subarrays, the fact is that
 * most merges are merging together tiny subarrays. Another way to implement
 * mergesort is to organize the merges so that we do all the merges of tiny
 * subarrays on one pass, then do a second pass to merge those subarrays in
 * pairs, and so forth, continuing until we do a merge that encompasses the
 * whole array. This method requires even less code than the standard recursive
 * implementation. We start by doing a pass of 1-by-1 merges (considering
 * individual items as subarrays of size 1), then a pass of 2-by-2 merges (merge
 * subarrays of size 2 to make subarrays of size 4), then 4-by-4 merges, and so
 * forth. The second subarray may be smaller than the first in the last merge on
 * each pass (which is no problem for merge()), but otherwise all merges involve
 * subarrays of equal size, doubling the sorted subarray size for the next pass.
 */
public class MergeBU {

	// This class should not be instantiated.
	private MergeBU() {
	}

	// stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

		// copy to aux[]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		// merge back to a[]
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++]; // this copying is unneccessary
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}

	}

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 * 
	 * @param a
	 *            the array to be sorted
	 */
	public static void sort(Comparable[] a) {
		int n = a.length;
		Comparable[] aux = new Comparable[n];
		
		for (int len = 1; len < n; len *= 2) {
			
			for (int lo = 0; lo < n - len; lo += len + len) {
				
				int mid = lo + len - 1;
				int hi = Math.min(lo + len + len - 1, n - 1);
				merge(a, aux, lo, mid, hi);
			}
		}
		assert isSorted(a);
	}

	/***********************************************************************
	 * Helper sorting functions.
	 ***************************************************************************/

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	/***************************************************************************
	 * Check if array is sorted - useful for debugging.
	 ***************************************************************************/
	private static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1]))
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
		MergeBU.sort(a);
		System.out.println("\nSorted: ");
		show(a);

	}
}
