import java.util.Scanner;

public class Main {
	//variável global porque não to sabendo lidar com recursão :(
	
	/* static porque foi o único jeito que funcionou e eu nunca aprendi
	como que isso funciona */
	
	public static int curr;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			curr = -1;
			String preOrd = in.next();
			String inOrd = in.nextLine().trim();
			printPostOrd(preOrd, inOrd, 0, preOrd.length() - 1);
			System.out.println("");
		}
		in.close();
	}
	//auxiliar pra não deixar a função principal grandona
	public static int search(String inOrd, char v, int length) {
		for(int x = 0; x < length; x++) {
			if(inOrd.charAt(x) == v) {
				return x;
			}
		}
		return -1;
	}
	//posso afirmar que essa é a função mais difícil que pensei em toda minha vida
	public static void printPostOrd(String preOrd, String inOrd, int i, int j) {
	    //quando i > j é porque ele chegou no fim da arvre
		if(i <= j) {
	        curr++;
	        //essa variável "splita" a arvore(ou subarvore) em elementos da esquerda e da direita"
	        int pos = search(inOrd, preOrd.charAt(curr), inOrd.length());
	        //essa chamada vai recursivamente até não ter mais elementos à esquerda
	        printPostOrd(preOrd, inOrd, i, pos - 1);
	        //e essa vai até não ter mais elementos à direita
	        printPostOrd(preOrd, inOrd, pos + 1, j);
	        //Sérgio me mata se souber disso
	        System.out.print(inOrd.charAt(pos));
	    }
	}
}