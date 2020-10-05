#include <stdio.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

typedef struct E{
    int v, w, wt;
};

int trees[1002];
int parent(int i){
    if(i == trees[i])
        return i;
    return (trees[i] = parent(trees[i]));
}

int Link(int x, int y){
    return parent(x) == parent(y);
}

void quickUnion(int x, int y){
    trees[parent(x)] = parent(y);
}

int compareFunc(const void *a, const void *b){
    if((*(E*)a).wt < (*(E*)b).wt)
        return -1;
    else
        return 1;
}


int main(){
    E tree[100002];
    int visited[100002];
    int testCases;
    cin >> testCases;
    int cont = 1;
    int minorTree, distance;
    while(testCases != 0){
        int locationsN, pRoads, aCost;
        cin >> locationsN >> pRoads >> aCost;
        for(int x = 0; x < pRoads; x++){
            int s, e, wt;
            cin >> s >> e >> wt;
            tree[x].v = s;
            tree[x].w = e;
            tree[x].wt = wt;
        }
        qsort(tree, pRoads, sizeof(E), &compareFunc);
        for(int x = 1; x <= locationsN; x++){
            trees[x] = x;
        }
        minorTree = distance = 0;
        for(int x = 0; x < pRoads && distance < locationsN - 1 && tree[x].wt < aCost; x++){
            if(!Link(tree[x].v , tree[x].w)){
                distance++;
                quickUnion(tree[x].v, tree[x].w);
                minorTree = minorTree + tree[x].wt;
            }
        }
        int c = 0;
        for(int x = 1; x <= locationsN; x++){
            if(trees[x] == x)
                c++;
        }
        aCost = minorTree + aCost*c;
        cout << "Case #" << cont++ << ": " << aCost << " " << c << endl;
    }
    return 0;
}