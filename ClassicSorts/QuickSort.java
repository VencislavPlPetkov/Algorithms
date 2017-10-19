package classicSorts;

public class QuickSort {

	public static void main(String[] args) {

		int[] nums = { -1111, 2, 9, 8, 3, 1 };

		System.out.print("Initial: ");
		printArray(nums);

		sort(nums, 0, nums.length - 1);

		System.out.print("Sorted: ");
		printArray(nums);

	}// END of Main

	private static void sort(int[] nums, int low, int high) {

		if (low < high) {

			int pivot = partition(nums, low, high);

			sort(nums, low, pivot - 1);
			sort(nums, pivot + 1, high);

		}

	}

	private static int partition(int[] nums, int low, int high) {
		int pivot = nums[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {

			if (nums[j] <= pivot) {
				i++;

				int temp = nums[j];
				nums[j] = nums[i];
				nums[i] = temp;
			}

		}

		int temp = nums[i + 1];
		nums[i + 1] = nums[high];
		nums[high] = temp;

		return i + 1;

	}

	private static void printArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

}// END of class
