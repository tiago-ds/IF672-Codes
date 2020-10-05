import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = Integer.parseInt(in.nextLine());
		for(int i = 1; i <= cases; i++) {
			int op = Integer.parseInt(in.nextLine());
			Dictionary dict = new Dictionary();
			for(int j = 0; j < op; j++) {
				String toDo = in.nextLine();
				String operation = toDo.split(":")[0];
				String item = toDo.split(":")[1];
				if(operation.equals("ADD")) {
					dict.addElement(item);
				}else {
					dict.deleteElement(item);
				}
			}
			System.out.println(dict.getCount());
			System.out.println(dict.toString());
		}
		in.close();
	}
}
class Dictionary {
	private String[] dict;
	private int count;
	
	public Dictionary() {
		this.dict = new String[101];
		this.count = 0;
	}
	
	public int hashIt(String str) {
		int sum = 0;
		for(int i = 0; i < str.length(); i++) {
			sum = sum + (int) str.charAt(i)*(i+1);
		}
		return 19*sum;
	}
	
	public int newHashIt(String str, int hash ,int index) {
			return (hash + (index*index) + (23*index))%101;
	}
	
	public void addElement(String str) {
		int found = searchElement(str);
		if(found == -1) {
			int position = hashIt(str);
			//null position and no collision
			if(this.dict[position%101] == null) {
				this.dict[position%101] = str;
				this.count++;
			}else {
				//empty position and no collision
				if(this.dict[position%101].equals("empty")) {
					this.dict[position%101] = str;
					this.count++;
				}else {
					int j = 1;
					while(j<=19) {
						int newPos = newHashIt(str, position, j);
						if(this.dict[newPos] != null) {
							//empty position at newPosition
							if(this.dict[newPos].equals("empty")) {
								this.dict[newPos] = str;
								this.count++;
								break;
							}
						}else {
							//null at newPosition
							this.dict[newPos] = str;
							this.count++;
							break;
						}
						j++;
					}
				}
			}
		}
		
	}
	
	public void deleteElement(String str) {
		int position = searchElement(str);
		if(position != -1) {
			this.dict[position%101] = "empty";
			this.count--;
		}
	}
	
	public int searchElement(String str) {
		int position = hashIt(str);
		//null position and no collision
		if(this.dict[position%101] == null) {
			return -1;
		}else {
			if(this.dict[position%101].equals(str)) {
				return (position%101);
			}else {
				int j = 1;
				while(j<=19) {
					int newPos = newHashIt(str, position, j);
					if(this.dict[newPos] != null) {
						if(this.dict[newPos].equals(str)) {
							return newPos;
						}
					}else {
						//null at newPosition
						return -1;
					}
					j++;
				}
			}
		}
		return -1;
	}
	
	public String toString() {
		String syso = "";
		for(int x = 0; x < 101; x++) {
			if(this.dict[x] != (null)) {
				if(!this.dict[x].equals("empty")) {
					syso = syso + x + ":" + this.dict[x] + "\n";
				}
			}
		}
		return syso;
	}
	
	public int getCount() {
		return this.count;
	}
}