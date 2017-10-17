package elementarySorts.selectionSort;

public class SelectionSort {

	public static void main(String[] args) {

		int[] a = { 2, 4, 1, 8, 3 };
		System.out.print("Unsorted: ");
		printArray(a);

		for (int i = 0; i < a.length; i++) {

			int min = i;

			for (int j = i + 1; j < a.length; j++) {

				if (a[j] <= a[min]) {
					min = j;
				}

			}

			int temp = a[i];
			a[i] = a[min];
			a[min] = temp;

		}

		System.out.println();
		System.out.print("Sorted: ");
		printArray(a);

	}

	private static void printArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}

}
