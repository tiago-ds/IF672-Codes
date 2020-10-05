import java.util.Scanner;

public class Main {
	public static void bfs(int start) {
		Queue f = new Queue();
		f.enqueue(start);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cont = 1;
		int wLock = in.nextInt();
		int rLock = in.nextInt();
		int nButtons = in.nextInt();
		while(wLock != 0 || rLock != 0 || nButtons != 0) {
			
			DistancesArrGraph references = new DistancesArrGraph();
			int[] buttons = new int[10];
			
			in.nextLine();
			
			for(int x = 0; x < nButtons; x++)
				buttons[x] = in.nextInt();
			in.nextLine();
			
			references.bfs(wLock, nButtons, buttons);
			
			System.out.printf("Case %d: ", cont++);
			int distance = references.completed(rLock);
			if(distance == -1)
				System.out.println("Permanently Locked");
			else
				System.out.println(distance);
			
			wLock = in.nextInt();
			rLock = in.nextInt();
			nButtons = in.nextInt();
		}
		
		in.close();
	}

}
class Node {
	public int value;
	public Node next;
	
	public Node(int v) {
		this.value = v;
		this.next = null;
	}
}
class Queue {
	private int length;
	private Node front;
	private Node rear;
	
	public Queue() {
		this.front = this.rear = null;
		this.length = 0;
	}
	
	public void enqueue(int value) {
		if(this.front == null) {
			this.front = new Node(value);
			this.rear = this.front;
			this.length++;
		}else {
			this.rear.next = new Node(value);
			this.rear = this.rear.next;
			this.length++;
		}
	}
	
	public int dequeue() {
		if(this.length == 0) {
			return -1;
		}if(this.length == 1) {
			int v = this.front.value;
			this.front = this.rear = null;
			this.length--;
			return v;
		}
		int v = this.front.value;
		this.front = this.front.next;
		this.length--;
		return v;
	}
	
	public int getLength() {
		return this.length;
	}
}
class DistancesArrGraph {
	
	public int[] visited;
	public int[] distance;
	
	public DistancesArrGraph() {
		this.visited = new int[10000];
		this.distance = new int[10000];
	}
	
	public void bfs(int wLock, int nButtons, int buttons[]) {
		Queue f = new Queue();
		f.enqueue(wLock);
		this.visited[wLock] = 0;
		this.distance[wLock] = 0;
		while(f.getLength() != 0) {
			int v1 = f.dequeue();
			for(int x = 0; x < nButtons; x++) {
				int v = (v1 + buttons[x]) % 10000;
				if(visited[v] == 0) {
					visited[v] = 1;
					distance[v] = distance[v1] + 1;
					f.enqueue(v);
				}
			}
		}
	}
	
	public int completed(int rLock) {
		if(distance[rLock] == 0)
			return -1;
		return distance[rLock];
	}
}