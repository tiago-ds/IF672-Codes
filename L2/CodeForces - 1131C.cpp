#include <iostream>
#include <cmath>
using namespace std;

void swap(int a[], int x, int y){
    int aux = a[x];
    a[x] = a[y];
    a[y] = aux;
}

int lomutoPart(int a[], int l, int r){
    int pivot = a[l];
    int split = l;
    for(int i = l + 1; i <= r; i++){
        if(a[i] < pivot){
            split++;
            swap(a, split, i);
        }
    }
    swap(a, l, split);
    return split;
}


void quickSort(int a[], int l, int r){
    if(l < r){
        int split = lomutoPart(a, l, r);
        quickSort(a, l, split - 1);
        quickSort(a, split + 1, r);
    }
}

int main() {
    int n;
    cin >> n;
    int children[n];
    for(int i = 0; i < n; i++){
        cin >> children[i];
    }
    quickSort(children, 0, n - 1);
    int rOne[n];
    int i = 1;
    int middle = ceil((n)/2);
    rOne[middle] = children[n - 1];
    for(int x = n - 1; x/2 >= 0; x--){
        rOne[middle - i] = children[x - i];
        rOne[middle + i] = children[x - (i + 1)];
        i++;
    }
    for(int x = 0; x < n; x++){
        cout << rOne[x] << " ";
    }
    return 0;
}