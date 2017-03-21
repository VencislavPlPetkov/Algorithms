package elementarySorts;

import java.util.Comparator;
import java.util.Scanner;

/**
 * The Selection class provides static methods for sorting an array using
 * selection sort.
 * 
 * Selection sort is not difficult to analyze compared to other sorting
 * algorithms since none of the loops depend on the data in the array. Selecting
 * the lowest element requires scanning all n elements (this takes n − 1
 * comparisons) and then swapping it into the first position. Finding the next
 * lowest element requires scanning the remaining n − 1 elements and so on, for
 * (n − 1) + (n − 2) + ... + 2 + 1 = n(n - 1) / 2 ∈ Θ(n2) comparisons (see
 * arithmetic progression). Each of these scans requires one swap for n − 1
 * elements (the final element is already in place).
 * 
 * Among simple average-case Θ(n2) algorithms, selection sort almost always
 * outperforms bubble sort and gnome sort. Insertion sort is very similar in
 * that after the kth iteration, the first k elements in the array are in sorted
 * order. Insertion sort's advantage is that it only scans as many elements as
 * it needs in order to place the k + 1st element, while selection sort must
 * scan all remaining elements to find the k + 1st element.
 * 
 * Simple calculation shows that insertion sort will therefore usually perform
 * about half as many comparisons as selection sort, although it can perform
 * just as many or far fewer depending on the order the array was in prior to
 * sorting.
 */
public class Selection {

	// This class should not be instantiated.
	private Selection() {
	}

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 */
	public static void sort(Comparable[] a) {

		int n = a.length;
		for (int i = 0; i < n; i++) {

			int min = i;

			for (int j = i + 1; j < n; j++) {

				if (less(a[j], a[min])) {
					min = j;
				}
			}

			exch(a, i, min);
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
	}

	/**
	 * Rearranges the array in ascending order, using a comparator.
	 */
	public static void sort(Object[] a, Comparator comparator) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (less(comparator, a[j], a[min])) {
					min = j;
				}
			}
			exch(a, i, min);
			assert isSorted(a, comparator, 0, i);
		}
		assert isSorted(a, comparator);
	}

	/***************************************************************************
	 * Helper sorting functions.
	 ***************************************************************************/

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	// is v < w ?
	private static boolean less(Comparator comparator, Object v, Object w) {
		return comparator.compare(v, w) < 0;
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

	// is the array a[] sorted?
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	// is the array sorted from a[lo] to a[hi]
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1])) {
				return false;
			}
		return true;
	}

	// is the array a[] sorted? - using Comparator
	private static boolean isSorted(Object[] a, Comparator comparator) {
		return isSorted(a, comparator, 0, a.length - 1);
	}

	// is the array sorted from a[lo] to a[hi] - using Comparator
	private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(comparator, a[i], a[i - 1]))
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
		Selection.sort(a);
		System.out.println("\nSorted: ");
		show(a);
	}
}
