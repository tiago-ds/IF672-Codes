import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int n, m, number;
	static int[] cds;
	static List<Integer> solution, answer;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder str = new StringBuilder();
		
		while(in.hasNext()) {
			n = in.nextInt();
			m = in.nextInt();
			cds = new int[m];
			for(int x = 0; x < cds.length; x++) {
				cds[x] = in.nextInt();
			}
			in.nextLine();
			solution = new ArrayList<Integer>();
			answer = new ArrayList<Integer>();
			number = 0;
			calculate(0,0);
			
			for(int i : answer) {
				str.append(i).append(" ");
			}
			str.append("sum:").append(number);
			System.out.println(str);
			str.delete(0, str.length());
		}
		in.close();
	}
	
	static void calculate(int rec, int curr) {
		if(rec == m) {
			if(curr > number) {
				number = curr;
				answer.clear();
				for(int i : solution) {
					answer.add(i);
				}
			}
			return;
		}
		calculate(rec + 1, curr);
		if(cds[rec] + curr <= n) {
			solution.add(cds[rec]);
			calculate(rec+1, curr+ cds[rec]);
			solution.remove(solution.size() - 1);
		}
	}
}