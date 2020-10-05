#include <iostream>
using namespace std;
int main() {
    string exp;
    cin >> exp;
    int counter, open;
    counter = open = 0;
    for(int i = 0; i < exp.length(); i++){
        if(exp[i] == '('){
            open++;
        }
        else if(exp[i] == ')' && open > 0){
            open--;
            counter+=2;
        }
    }
    cout << counter;
    return 0;
}