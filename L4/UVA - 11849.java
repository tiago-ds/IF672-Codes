import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int jackCDS = in.nextInt();
		int jillCDS = in.nextInt();
		in.nextLine();
		if(jackCDS != 0 || jillCDS != 0) {
			while(jackCDS != 0 || jillCDS != 0) {
				AVL jackTree = new AVL();
				for(int x = 0; x < jackCDS; x++) {
					int cd = Integer.parseInt(in.nextLine());
					jackTree.root = jackTree.insert(jackTree.root, cd);
				}
				int cont = 0;
				for(int y = 0; y < jillCDS; y++) {
					int cd = Integer.parseInt(in.nextLine());
					if(jackTree.search(jackTree.root, cd)){
						cont++;
					}
				}
				System.out.println(cont);
				jackCDS = in.nextInt();
				jillCDS = in.nextInt();
				in.nextLine();
			}
			
			
		}
		in.close();
	}
}
class Node {
	public int key;
	public Node left;
	public Node right;
	public int height;
	
	public Node(int key) {
		this.key = key;
		this.left = this.right = null;
		this.height = 1;
	}
}
class AVL {
	public Node root;
	
	private int height(Node n) {
		if(n == null) {
			return 0;
		}else {
			return n.height;
		}
	}
	
	private int max(int a, int b) {
		if(a > b) {
			return a;
		}
		return b;
	}
	
	private int balance(Node x) {
		if (x == null) { 
            return 0; 
		}
        return height(x.left) - height(x.right); 
	}
	
	private Node rightRot(Node y) {
		Node x = y.left;
		Node tree2 = x.right;
		//rotação
		x.right = y;
		y.left = tree2;
		//mudança de alturas
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;
		return x;
	}
	
	private Node leftRot(Node x) {
		Node y = x.right;
		Node tree2 = y.left;
		//rotação
		y.left = x; 
        x.right = tree2;
        //alturas
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        
        return y; 
	}
	
	public Node insert(Node n, int key) {
		if(n == null) {
			return new Node(key);
		}
		if(key < n.key) {
			n.left = insert(n.left, key);
		}
		else {
			n.right = insert(n.right, key);
		}
		n.height = 1 + max(height(n.left), height(n.right));
		int balance = balance(n);
		//LL rot
		if(balance > 1 && key < n.left.key) { 
            return rightRot(n);
		}
		//RR rot
		if(balance < -1 && key > n.right.key) {
			return leftRot(n);
		}
		//LR rot
		if(balance > 1 && key > n.left.key) { 
            n.left = leftRot(n.left);
            return rightRot(n); 
        }
		//RL rot
		 if (balance < -1 && key < n.right.key) { 
	        n.right = rightRot(n.right); 
	        return leftRot(n); 
	     }
		 //sem rotações
		 return n;
	}
	
	public boolean search(Node n, int key) {
		if(n != null) {
			if(n.key == key)
				return true;
			if(n.key > key)
				return search(n.left, key);
			if(n.key < key)
				return search(n.right, key);
		}
		return false;
	}
}