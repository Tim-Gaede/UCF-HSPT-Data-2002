// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Spider-Man's Diamond Head Dilemma
// Filename: spidey.cpp
// Input File: spidey.in

#include <string>
#include <fstream>
#include <iostream>

using namespace std;

void main() {
  ifstream fin("spidey.in");

  int nSets;
  fin >> nSets;

  for (int s = 0; s < nSets; s++) {
    int V, E, v, e;
    fin >> V >> E;
    //cout << "Case " << s << " - (" << V << ", " << E << "): ";
    
    int C[255];
    for (v = 0; v < V; v++) {
      C[v] = v;
    }

    bool isOK = true;
    for (e = 0; e < E; e++) {
      int a, b;
      fin >> a >> b;

      if ((a%2)==(b%2)) isOK = false;
      int Ca = C[a];
      for (v = 0; v < V; v++) {
        if (C[v] == Ca) C[v] = C[b];
      }
    }

    for (v = 0; v < V; v++) {
      if (C[v] != C[0]) {
        isOK = false;
        break;
      }
    }

    if (isOK) {
      cout << "Way to go, Spider-Man!\n\n";
    } else {
      cout << "It's the end of the world!\n\n";
    }
  }
  fin.close();
}