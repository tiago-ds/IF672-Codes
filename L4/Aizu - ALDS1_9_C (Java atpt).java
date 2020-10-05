import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Heap elem = new Heap(2000001);
        String action = in.next();
        if(!action.equals("end")) {
            while(!action.equals("end")) {
                if(action.equals("insert")){
                    int n = Integer.parseInt(in.nextLine().trim());
                    elem.insert(n);
                }else{
                    int syso = elem.extract();
                    if(syso != 2000000001){
                        System.out.println(syso);
                    }
                }
                action = in.next();
            }
        }
        in.close();
    }
}
class Heap {
    private int[] Heap;
    private int size;
    private int maxSize;

    public Heap(int maxSize) {
        this.Heap = new int[maxSize];
        this.maxSize = maxSize;
        this.size = 0;
        this.Heap[0] = 2000000001;
    }

    //aux
    private int parent(int pos) {
        return pos /2;
    }
    private int leftChild(int pos) {
        return pos*2;
    }
    private int rightChild(int pos) {
        return (pos*2)+1;
    }
    private boolean isLeaf(int pos) { 
        if(pos >= (Math.ceil(this.size/2.0)) && pos <= this.size) {
        	if(pos == 1 && size == 2) {
        		return false;
        	}
            return true;
        }
        return false;
    }
    private void swap(int fpos, int spos) {
        int aux = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = aux;
    }
    private void zoeivc(){
        int pos = 1;
        boolean heap = false;
        while(!heap && !isLeaf(pos)){
            if(this.Heap[leftChild(pos)] > this.Heap[pos] || this.Heap[rightChild(pos)] > this.Heap[pos]){
                if(this.Heap[leftChild(pos)] > this.Heap[rightChild(pos)]){
                    swap(pos, leftChild(pos));
                    pos = leftChild(pos);
                }else{
                    swap(pos, rightChild(pos));
                    pos = rightChild(pos);
                }
            }else {
                heap = true;
            }
        }
    }
    public void insert(int it) {
        Heap[++this.size] = it;
        int curr = this.size;
        while(Heap[curr] > Heap[parent(curr)]) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }
    public int extract() {
        if(this.size == 0)
            return 2000000001;
        int ext = this.Heap[1];
        swap(1, this.size);
        this.Heap[size--] = 0;
        zoeivc();
        return ext;
    }
}