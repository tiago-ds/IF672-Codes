import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BST tree = new BST();
		String test = "hi";
		while(!test.equals("")) {
			test = in.nextLine();
			if(!test.equals("")) {
				String name = test.split(" ")[0];
				String key = test.split(" ")[1];
				Item it = new Item(key, name);
				tree.addElement(it);
			}
		}
		while(in.hasNext()) {
			String key = in.nextLine();
			String printout = tree.findElement(key, generateCode(key));
			if( printout != null) {
				System.out.println(printout);
			}else {
				System.out.println("eh");
			}
		}
		in.close();
	}
	public static int generateCode(String key) {
		int length = key.length()/4;
		int sum = 0;
		for(int i = 0; i <= length - 1; i++) {
			String sub = key.substring(i*4, (i*4) + 4);
			int mult = 1;
			int subL = sub.length();
			for(int j = 0; j <= subL - 1; j++) {
				sum = sum + (((int) sub.charAt(j))*mult);
				mult = mult*256;
			}
		}
		return Math.abs(sum%100000);
	}
}
class BST {
	public Item root;
	public BST left;
	public BST right;
	
	public BST() {
		this.root = null;
		this.left = null;
		this.right = null;
	}
	public void addElement(Item it) {
		if(this.root == null) {
			this.root = it;
			this.left = new BST();
			this.right = new BST();
		}else {
			if(this.root.code < it.code) {
				this.left.addElement(it);
			}else {
				this.right.addElement(it);
			}
		}
	}
	
	public String findElement(String key, int code) {
		if(this.root != null) {
			if(this.root.code == code) {
				if(this.root.key.equals(key)) {
					return this.root.name;
				}else {
					return this.right.findElement(key, code);
				}
			}else if(this.root.code < code) {
				return this.left.findElement(key, code);
			}else {
				return this.right.findElement(key, code);
			}
		}
		else {
			return null;
		}
	}
}
class Item {
	public String key;
	public String name;
	public int code;
	
	public Item(String key, String name) {
		this.key = key;
		this.name = name;
		this.code = generateCode(key);
	}
	public int generateCode(String key) {
		int length = key.length()/4;
		int sum = 0;
		for(int i = 0; i <= length - 1; i++) {
			String sub = key.substring(i*4, (i*4) + 4);
			int mult = 1;
			int subL = sub.length();
			for(int j = 0; j <= subL - 1; j++) {
				sum = sum + (((int) sub.charAt(j))*mult);
				mult = mult*256;
			}
		}
		return Math.abs(sum%100000);
	}
}