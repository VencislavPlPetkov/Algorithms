package searching;

/**
 * Binary search using divide and conquer technique.
 * A binary search or half-interval search algorithm finds the position of a
 * specified value (the input "key") within a sorted array. Binary search
 * requires a sorted collection. Also, binary searching can only be applied to a
 * collection that allows random access (indexing).
 * 
 * Worst case performance: O(log n) Best case performance: O(1)
 */
public class BinarySearch {

	public int binarySearch(int[] inputArr, int key) {

		int start = 0;
		int end = inputArr.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (key == inputArr[mid]) {
				return mid;
			}
			if (key < inputArr[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		BinarySearch bs = new BinarySearch();
		int[] arr = { 2, 4, 6, 8, 10, 12, 14, 16 };
		System.out.println("Key 8's position: " + bs.binarySearch(arr, 8));
		int[] arr1 = { 6, 34, 78, 123, 432, 900 };
		System.out.println("Key 34's position: " + bs.binarySearch(arr1, 34));
	}
}
