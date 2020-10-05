#include <iostream>
#include <cmath>
using namespace std;

void swap(int a[], int x, int y){
    int aux = a[x];
    a[x] = a[y];
    a[y] = aux;
}

int lomutoP(int a[], int l, int r){
    int pivot = a[l];
    int split = l;
    for(int i = l + 1; i <= r; i++){
        if(abs(a[i]) < abs(pivot)){
            split++;
            swap(a, split, i);
        }
    }
    swap(a, l, split);
    return split;
}

void quickSort(int a[], int l, int r){
    if(l < r){
        int split = lomutoP(a, l, r);
        quickSort(a, l, split - 1);
        quickSort(a, split + 1, r);
    }
}

int main() {
    int cases;
    cin >> cases;
    while(cases != 0){
        int nFloors;
        cin >> nFloors;
        int size = 0;
        int build[nFloors];
        for(int x = 0; x < nFloors; x++){
            cin >> build[x];
        }
        quickSort(build, 0, nFloors - 1);
        /*for(int x = 0; x < nFloors; x++){
            cout << build[x] << " ";
        }*/
        bool color;
        int aS;
        for(int i = nFloors - 1; i >= 0; i--){
            if(i != nFloors - 1){
                if(build[i] > 0 && color == false || build[i] < 0 && color == true){
                    aS++;
                }
            }else{
                aS = 1;
            }
            if(build[i] > 0){
                color = true;
            }else{
                color = false;
            }
            if(aS > size){
                size = aS;
            }
        }
        cout << size << endl;
        size = 0;
        cases--;
    }
    return 0;
}