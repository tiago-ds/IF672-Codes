import java.util.Scanner;

public class Main {
	
	public static Product[] products;
	
	public static int knapSack(int maxWT, int[][] matrix, Product[] products) {
		for(int i = 0; i <= products.length; i++) {
			for(int j = 0; j <= maxWT; j++) {
				if(!(i == 0 || j == 0)) {
					if(products[i - 1].weight <= j)
						matrix[i][j] = Math.max(matrix[i - 1][j], products[i - 1].price + 
								matrix[i - 1][j - products[i - 1].weight]);
					else
						matrix[i][j] = matrix[i-1][j];
				}
			}
		}
		return matrix[products.length][maxWT];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testCases, objects, price, weight, groupSize, ans;
		testCases = Integer.parseInt(in.nextLine());
		while(testCases != 0) {
			objects = Integer.parseInt(in.nextLine());
			products = new Product[objects];
			for(int i = 0; i < objects; i++) {
				int p = in.nextInt();
				int w = in.nextInt();
				Product t = new Product(p, w);
				products[i] = t;
				in.nextLine();
			}
			
			groupSize = Integer.parseInt(in.nextLine());
			ans = 0;
			for(int i = 0; i < groupSize; i++) {
				int t = Integer.parseInt(in.nextLine());
				int[][] matrix = new int[objects + 1][t + 1];
				ans += knapSack(t, matrix, products);
			}
			testCases--;
			System.out.println(ans);
			
		}
	}

}

class Product{
	public int price;
	public int weight;
	
	public Product(int p, int w) {
		this.price = p;
		this.weight = w;
	}
}