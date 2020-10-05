import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tamanho = Integer.parseInt(in.nextLine());
		if(tamanho != 0) {
			do {
				Queue deck = new Queue();
				Queue discarded = new Queue();
		 		for(int x = 1; x < tamanho + 1; x++) {
					deck.enqueue(x);
				}
				while(deck.size != 1) {
					int nextCard = deck.tradeCard();
					discarded.enqueue(nextCard);
				}
				
				if(discarded.size != 0) {
					System.out.print("Discarded cards: ");
					System.out.println(discarded.printCards());
				}else {
					System.out.print("Discarded cards:");
					System.out.println();
				}
				System.out.print("Remaining card: ");
				System.out.println(deck.printCards());
				tamanho = Integer.parseInt(in.nextLine());
			}while(tamanho != 0);
		}
	}
}
class LinkedList {
	public int card;
	public LinkedList next;
	
	public LinkedList() {
		this.card = 0;
		this.next = null;
	}
	
	public void addCard(int card) {
		if(this.card == 0) {
			this.card = card;
			this.next = new LinkedList();
		}else {
			this.next.addCard(card);
		}
	}
	
	public String returnDeck() {
		if(this.card == 0) {
			return "";
		}	
		else if(this.next.card == 0) {
			return Integer.toString(this.card);
		}
		else {
			return Integer.toString(this.card) + ", " + this.next.returnDeck();
		}
	}
}
class Queue {
	public LinkedList entrance;
	public int size;

	public Queue() {
		this.entrance = null;
		this.size = 0;
	}
	public void enqueue(int card) {
		if(this.entrance != null) {
			this.entrance.addCard(card);
			this.size++;
		}else {
			this.entrance = new LinkedList();
			this.entrance.addCard(card);
			this.size++;
		}
	}
	public int dequeue() {
		if(this.entrance != null) {
			int card = this.entrance.card;
			this.entrance = this.entrance.next;
			this.size--;
			return card;
		}else {
			System.err.println("Error");
			return -1;
		}
	}
	public int tradeCard() {
		if(this.size == 0) {
			System.err.println("Error");
			return -1;
		}else {
			int discarded = this.dequeue();
			int card = this.dequeue();
			this.enqueue(card);
			return discarded;
		}
	}
	public String printCards() {
		return this.entrance.returnDeck();
	}
}