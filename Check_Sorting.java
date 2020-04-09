import java.util.Scanner;

public class Check_Sorting {

	static boolean isSorted(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] < a[i + 1]) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] inpValue = new int[6];
		Scanner inpScan = new Scanner(System.in);
		System.out.println("Please enter the list of 6 numbers: ");
		for (int i = 0; i < 6; i++) {
			inpValue[i] = inpScan.nextInt();
		}

		boolean result = isSorted(inpValue);
		if (result) {
			System.out.println("It is sorted");
		} else {
			System.out.println("It is not sorted");
		}
		inpScan.close();
	}

}
