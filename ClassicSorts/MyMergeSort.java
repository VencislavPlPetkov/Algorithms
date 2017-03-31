package classicSorts;

/**
 * 
 * Merge sort is a fast, stable sorting routine with guaranteed O(n*log(n))
 * efficiency. When sorting arrays, merge sort requires additional space
 * proportional to the size of the input array. Merge sort is offers performance
 * typically only slightly below that of quicksort.
 * 
 * 
 */
public class MyMergeSort {

	private int[] array;
	private int[] tempMergArr;
	private int length;

	public static void main(String a[]) {

		int[] inputArr = { 45, 23, 11, 89, 77, 98, 4, 28, 65, 43 };
		MyMergeSort mms = new MyMergeSort();
		mms.sort(inputArr);
		for (int i : inputArr) {
			System.out.print(i);
			System.out.print(" ");
		}
	}

	public void sort(int inputArr[]) {
		this.array = inputArr;
		this.length = inputArr.length;
		this.tempMergArr = new int[length];
		doMergeSort(0, length - 1);
	}

	private void doMergeSort(int lowerIndex, int higherIndex) {

		if (lowerIndex < higherIndex) {

			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;

			// Sorts the left side of the array
			doMergeSort(lowerIndex, middle);

			// Sorts the right side of the array
			doMergeSort(middle + 1, higherIndex);

			// Now merge both sides
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	private void mergeParts(int lowerIndex, int middle, int higherIndex) {

		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempMergArr[i] = array[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;

		while (i <= middle && j <= higherIndex) {
			if (tempMergArr[i] <= tempMergArr[j]) {
				array[k] = tempMergArr[i];
				i++;
			} else {
				array[k] = tempMergArr[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			array[k] = tempMergArr[i];
			k++;
			i++;
		}

	}
}
