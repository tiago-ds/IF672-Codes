#include <iostream>
#include <bits/stdc++.h>

using namespace std;

//first int is price second int is weight
vector < pair < int, int > > Products;
vector < vector < int > > matrix;

int pesoPessoa(int maxW, int start){
    if(start < 0)
        return 0;
    else if(matrix.at(maxW).at(start) != 0)
        return matrix.at(maxW).at(start);
    else{
        if(maxW >= Products.at(start).second)
            return max(pesoPessoa(maxW, start - 1), pesoPessoa(maxW - Products.at(start).second, start - 1) + Products.at(start).first);
        return pesoPessoa(maxW, start - 1);
    }
}

int main(){
    int testCases, objects, price, weight, groupSize;
    cin >> testCases;
    while(testCases != 0){
        cin.tie(0);
        cin >> objects;
        matrix.resize(50, vector < int >(objects, 0));
        for(int i = 0; i < objects; i++){
            cin >> price;
            cin >> weight;
            Products.push_back(make_pair(price, weight));
        }
        cin >> groupSize;
        //vector< int > groupWeight;
        int ans = 0;
        for(int i = 0; i < groupSize; i++) {
            int t;
            cin >> t;
            ans = ans + pesoPessoa(t, objects - 1);
        //    groupWeight.push_back(t);
        }
        cout << ans << endl;
        Products.clear();
        testCases--;
    }
    return 0;
}