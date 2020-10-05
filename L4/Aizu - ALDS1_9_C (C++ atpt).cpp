#include <iostream>
#include <cstring>

using namespace std;

struct Heap{
    int arr[2000001];
    int size;
    int maxSize;

    Heap(){
        this->maxSize = 2000001;
        this->size = 0;
        this->arr[0] = 200000001;
    }
    void swap(int pos1, int pos2){
        int aux = this->arr[pos1];
        this->arr[pos1] = this->arr[pos2];
        this->arr[pos2] = aux;
    }
    
    void insert(int it) {
        this->arr[++this->size] = it;
        int pos = this->size;
        while(this->arr[pos] > this->arr[pos/2] && pos != 1){
            int aux = this->arr[pos/2];
            this->arr[pos/2] = this->arr[pos];
            this->arr[pos] = aux;
            pos = pos/2;
        }
    }
    int extract() {
        if(this->size == 0)
            return 2000000001;
        int ext = this->arr[1];
        swap(1, size);
        this->arr[size--] = 0;
        int pos = 1;
        bool heap = false;
        while(!heap || pos*2 <= size) {
            if (this->arr[pos] < this->arr[pos * 2] || this->arr[pos * 2 + 1]) {
                if (this->arr[pos * 2] > this->arr[pos * 2 + 1]) {
                    swap(pos, pos * 2);
                } else {
                    swap(pos, pos * 2 + 1);
                }
            } else {
                heap = true;
            }
        }
        return ext;
    }
};

int main(){
    Heap* elements = new Heap();
    string action;
    cin >> action;
    if(action != "end"){
        while(action != "end"){
            if(action == "insert"){
                int n;
                cin >> n;
                elements->insert(n);
            }else{
                int sys = elements->extract();
                if(sys != 2000000001){
                    cout << sys;
                }
            }
            cin >> action;
        }
    }
    return 0;
}