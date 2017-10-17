package elementarySorts.insertionSort;

public class InsertionSort {

	public static void main(String[] args) {

		int[] nums = { 54, 2, 9, 8, 3, 1 };

		System.out.print("Initial: ");
		printArray(nums);

		int temp;
		for (int i = 0; i < nums.length; i++) {

			for (int j = i; j > 0; j--) {

				if (nums[j] < nums[j - 1]) {

					temp = nums[j];
					nums[j] = nums[j - 1];
					nums[j - 1] = temp;
				}

			}

		}

		System.out.println();
		System.out.print("Sorted: ");
		printArray(nums);

	}

	private static void printArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}

}
