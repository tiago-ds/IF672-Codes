import java.util.Scanner;

public class Main {
	
	static String str1, str2;
	
	static int LCS(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(!(i == 0 || j == 0)) {
					if(str1.charAt(i - 1) == str2.charAt(j - 1))
						matrix[i][j] = 1 + matrix[i - 1][j - 1];
					else
						matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
				}
			}
		}
		return matrix[str1.length()][str2.length()];
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()) {
			
			str1 = in.nextLine();
			str2 = in.nextLine();
			
			int[][] matrix = new int[str1.length() + 1][str2.length() + 1];
			System.out.println(LCS(matrix));
			
		}
	}
}