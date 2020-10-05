#include <iostream>
using namespace std;

void insertionSort(int a[], int n){
    for(int i = 1; i <= n - 1; i++){
        int v = a[i];
        int j = i - 1;
        while(j >= 0 && a[j] > v){
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = v;
    }
}

int bookSearch(int a[], int n, int value){
    int left = 0;
    int right = n - 1;
    int value1 = 0;
    while(left < right){
        if(a[left] + a[right] < value){
            left++;
        }else if(a[left] + a[right] == value){
            value1 = a[left];
            left++;
            right--;
        }else{
            right--;
        }
    }
    return value1;
}

int main() {
    int nBooks;
    while(cin >> nBooks){
        int books[nBooks];
        for(int i = 0; i < nBooks; i++){
            cin >> books[i];
        }
        int money;
        cin >> money;
        insertionSort(books, nBooks);
        int value1 = bookSearch(books, nBooks, money);
        cout << "Peter should buy books whose prices are " << value1 << " and " << money - value1 << "." << endl;
        cout << endl;
    }
    return 0;
}