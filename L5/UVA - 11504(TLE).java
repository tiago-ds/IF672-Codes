import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner in = new Scanner(br); 
		int testCases = Integer.parseInt(in.nextLine());
		for(int x = 0; x < testCases; x++) {
			int tiles = in.nextInt();
			int cases = Integer.parseInt(in.nextLine().trim());
			
			ListGraph dominos = new ListGraph(tiles);
			for(int y = 0; y < cases; y++) {
				int d1 = in.nextInt();
				int d2 = in.nextInt();
				dominos.setEdge(d1-1, d2-1);
			}
			Stack s = new Stack();
			dominos.fullTopoSort(s);
			int minor = dominos.traverse(s);
			System.out.println(minor);
		}
		in.close();
	}

}
class Node {
	public int value;
	public Node next;
	
	public Node() {
		this.value = -2;
		this.next = null;
	}
	
	public Node(int v) {
		this.value = v;
		this.next = null;
	}
	
	public void add(int v) {
		if(this.next == null) {
			this.value = v;
			this.next = new Node();
		}else {
			if(this.value < v) {
				this.next.add(v);
			}else {
				Node Naux = this.next;
				int aux = this.value;
				this.value = v;
				this.next = new Node(aux);
				this.next.next = Naux;
			}
		}
	}
	
	public int nextV(int v) {
		if(this.value == -2)
			return -1;
		if(this.value > v)
			return -1;
		if(this.value < v)
			return this.next.nextV(v);
		return this.next.value;
	}
	
}
class Stack {
	public Node top;
	public int size;
	
	public Stack() {
		this.top = null;
		this.size = 0;
	}
	
	public void push(int v) {
		Node aux = new Node(v);
		if(this.size == 0) {
			this.top = aux;
			this.size++;
		}
		else {
			aux.next = this.top;
			this.top = aux;
			this.size++;
		}
	}
	
	public int pop() {
		int aux = -1;
		if(this.top != null) {
			aux = this.top.value;
			this.top = this.top.next;
			this.size--;
		}
		return aux;
	}
	
	public void clear() {
		this.top = null;
		this.size = 0;
	}
}
class LinkedList {
	public Node start;
	public int length;
	
	public LinkedList() {
		this.start = null;
		this.length = 0;
	}
	
	public void add(int v) {
		if(this.start == null) {
			this.start = new Node();
			this.start.add(v);
			this.length++;
		}else
			this.start.add(v);
	}
	
	public int nextVa(int v) {
		if(this.start == null)
			return -1;
		return start.nextV(v);
	}
}
class ListGraph {
	private LinkedList[] list;
	private int[] mark;
	public int cont;
	
	public ListGraph(int n) {
		this.list = new LinkedList[n];
		this.mark = new int[n];
		this.cont = 0;
	}
	
	private int length() {
		return this.mark.length;
	}
	
	private void setMark(int i, int v) {
		this.mark[i] = v;
	}
	
	private int getMark(int i) {
		return this.mark[i];
	}
	
	private int first(int v) {
		if(this.list[v] != null) {
			if(this.list[v].start.value != -2) {
				return this.list[v].start.value;
			}
			return length();
		}
		return length();
	}
	
	private int nextV(int v, int w) {
		if(this.list[v] != null) {
			int aux = this.list[v].nextVa(w);
			if(aux != -1 && aux != -2)
				return aux;
		}
		return length();
	}
	
	public void setEdge(int v, int w) {
		if(this.list[v] != null) {
			this.list[v].add(w);
		}else {
			this.list[v] = new LinkedList();
			this.list[v].add(w);
		}
	}
	
	public void unvisit() {
		for(int x = 0; x < length(); x++) {
			this.mark[x] = 0;
		}
	}
	
	public void topoSort(int start, Stack s) {
		setMark(start, 1);
		int w = first(start);
		while(w < length()) {
			if(getMark(w) == 0) {
				topoSort(w, s);
			}
			w = nextV(start, w);
		}
		s.push(start);
	}
	
	public void fullTopoSort(Stack s) {
		for(int x = 0; x < length(); x++) {
			if(this.mark[x] == 0)
				topoSort(x, s);
		}
	}
	
	public int traverse(Stack s) {
		unvisit();
		int cont = 0;
		while(s.size != 0) {
			int v = s.pop();
			if(getMark(v) == 0) {
				cont++;
				dfs(v);
			}
		}
		return cont;
	}
	
	private void dfs(int start) {
		setMark(start, 1);
		int w = first(start);
		while(w < length()) {
			if(getMark(w) == 0)
				dfs(w);
			w = nextV(start, w);
		}
	}
}