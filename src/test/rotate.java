package test;


public class rotate {

	public static void main(String[] args) {

		int[] nums = {1,2,3,4,5,6,7,8,9};
		nums = rotateMe(nums);
		int n = 1;
		for (int i : nums) {
			if (n++ % 3 == 0)
				System.out.println(i);
			else
				System.out.print(i + " ");
		}


	}

	public static int[] rotateMe(int[] nums){
		int[] numsCopy = new int[9];
		for (int i = 0; i < nums.length; i++){
			int idx = (((3-1)*nums[i])+nums[i]-1) % nums.length;
			while (numsCopy[idx] != 0)
				idx--;
			numsCopy[idx] = nums[i];
		}
		return numsCopy;
	}
	
}
