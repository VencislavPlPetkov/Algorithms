package classicSorts;

import java.util.Random;
import java.util.Scanner;

/**
 * Sorting an array and selecting the ith smallest element in an array using
 * Quicksort.
 * 
 * Quicksort: inplace, not stable, worst - n2 / 2, average - 2 n log n, best - n log n
 * 
 * Quicksort is a divide-and-conquer method for sorting. It works by
 * partitioning an array into two subarrays, then sorting the subarrays
 * independently. Quicksort is complementary to mergesort: for mergesort, we
 * break the array into two subarrays to be sorted and then combine the ordered
 * subarrays to make the whole ordered array; for quicksort, we rearrange the
 * array such that, when the two subarrays are sorted, the whole array is
 * ordered.
 * 
 * In merge, we do the two recursive calls before working on the whole array; in
 * quick, we do the two recursive calls after working on the whole array. For
 * mergesort, the array is divided in half; for quicksort, the position of the
 * partition depends on the contents of the array.
 */
public class Quick {

	// This class should not be instantiated.
	private Quick() {
	}

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 * 
	 * @param a
	 *            the array to be sorted
	 */
	public static void sort(Comparable[] a) {
		shuffleArray(a); // Eliminate dependence on input
		sort(a, 0, a.length - 1);
		assert isSorted(a);
	}

	// Fisher–Yates shuffle
	private static void shuffleArray(Comparable[] a) {
		int index;
		Comparable temp;
		Random random = new Random();
		for (int i = a.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = a[index];
			a[index] = a[i];
			a[i] = temp;
		}
	}

	// quicksort the subarray from a[lo] to a[hi]
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}

		int j = partition(a, lo, hi); // Partition
		sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
		sort(a, j + 1, hi); // Sort right part a[j+1 .. hi].
		assert isSorted(a, lo, hi);
	}

	/**
	 * Partitioning:
	 * First, we arbitrarily choose a[lo] to be the partitioning item—the one
	 * that will go into its final position. Next, we scan from the left end of
	 * the array until we find an entry greater than (or equal to) the
	 * partitioning item, and we scan from the right end of the array until we
	 * find an entry less than (or equal to) the partitioning item. The two
	 * items that stopped the scans are out of place in the final partitioned
	 * array, so we exchange them. Continuing in this way, we ensure that no
	 * array entries to the left of the left index i are greater than the
	 * partitioning item, and no array entries to the right of the right index j
	 * are less than the partitioning item. When the scan indices cross, all
	 * that we need to do to complete the partitioning process is to exchange
	 * the partitioning item a[lo] with the rightmost entry of the left subarray
	 * (a[j]) and return its index j.
	 * 
	 */

	// partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
	// and return the index j.
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable v = a[lo]; // partitioning item
		while (true) {

			// find item on lo to swap
			while (less(a[++i], v)) {
				if (i == hi) {
					break;
				}
			}

			// find item on hi to swap
			while (less(v, a[--j])) {
				if (j == lo) {
					break; // redundant since a[lo] acts as sentinel
				}
			}

			// check if pointers cross
			if (i >= j) {
				break;
			}

			exch(a, i, j);
		}
		// when i and j cross we exchange lo and j and put the element in the
		// middle
		// (put partitioning item v at a[j])
		exch(a, lo, j);

		// we return the index of the item that is already in place
		// (now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi])
		return j;
	}

	/**
	 * Rearranges the array so that {@code a[k]} contains the kth smallest key;
	 * {@code a[0]} through {@code a[k-1]} are less than (or equal to)
	 * {@code a[k]}; and {@code a[k+1]} through {@code a[n-1]} are greater than
	 * (or equal to) {@code a[k]}.
	 *
	 * @param a
	 *            the array
	 * @param k
	 *            the rank of the key
	 * @return the key of rank {@code k}
	 */
	public static Comparable select(Comparable[] a, int k) {
		if (k < 0 || k >= a.length) {
			throw new IndexOutOfBoundsException("Selected element out of bounds");
		}
		shuffleArray(a);
		int lo = 0, hi = a.length - 1;
		while (hi > lo) {
			int i = partition(a, lo, hi);
			if (i > k)
				hi = i - 1;
			else if (i < k)
				lo = i + 1;
			else
				return a[i];
		}
		return a[lo];
	}

	/***************************************************************************
	 * Helper sorting functions.
	 ***************************************************************************/

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/***************************************************************************
	 * Check if array is sorted - useful for debugging.
	 ***************************************************************************/
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	/**
	 * Reads in a sequence of strings from standard input; quicksorts them; and
	 * prints them to standard output in ascending order. Shuffles the array and
	 * then prints the strings again to standard output, but this time, using
	 * the select method.
	 *
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String[] a = sc.nextLine().split(" ");
		System.out.println("Unsorted: ");
		show(a);
		Quick.sort(a);
		System.out.println();
		System.out.println("\nSorted: ");
		show(a);
		assert isSorted(a);

		System.out.println("\n***************************");
		System.out.println("\nShuffle the Array");
		System.out.println();

		// shuffle
		shuffleArray(a);
		System.out.println("Unsorted: ");
		show(a);

		System.out.println();
		// display results again using select
		System.out.println("\nDisplay results again using select");

		for (int i = 0; i < a.length; i++) {
			String ith = (String) Quick.select(a, i);
			System.out.print(ith + " ");
		}
	}

}
