#include<bits/stdc++.h>

using namespace std;
# define INF 2147483647

typedef pair<int, int> vertex;

void addEdge(vector <pair<int, int> > adj[], int v, int w, int weight){
    adj[v].push_back(make_pair(w, weight));
}

int djikstra(vector<pair<int,int> > adj[], int v, int s, int goal){
    priority_queue< vertex, vector <vertex> , greater<vertex> > heap;
    vector<int> dist(v, INF);
    heap.push(make_pair(0, s));
    dist[s] = 0;
    while (!heap.empty()){
        int u = heap.top().second;
        heap.pop();
        for (auto x : adj[u]){
            int v = x.first;
            int weight = x.second;
            if (dist[v] > dist[u] + weight){
                dist[v] = dist[u] + weight;
                heap.push(make_pair(dist[v], v));
            }
        }
    }
    return dist[goal];
}

int main(){
    int testCases, start, end, cont;
    cin >> testCases;
    cont = 1;
    for(int x = 0; x < testCases; x++){
        int vertices, connections;
        cin >> vertices;
        cin >> connections;
        cin >> start;
        cin >> end;
        vector<vertex> adj[vertices];
        for(int y = 0; y < connections; y++){
            int v, w, weight;
            cin >> v;
            cin >> w;
            cin >> weight;
            addEdge(adj, v, w, weight);
            addEdge(adj, w, v, weight);
        }
        int distance = djikstra(adj, vertices, start, end);
        if(distance != 2147483647)
            cout << "Case #" << cont << ": " << distance << "\n";
        else
            cout << "Case #" << cont << ": " << "unreachable" << "\n";
        cont ++;
    }
    return 0;
} 