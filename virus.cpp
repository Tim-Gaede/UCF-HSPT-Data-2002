// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Illegal Hawaiian Virus Collection
// Filename: virus.cpp
// Input File: virus.in

#include <iostream>
#include <fstream>
#include <string>

using namespace std;

bool match(string virus, string pattern) {
  int lstart = ((int)virus.size()) - ((int)pattern.size());
  for (int start = 0; start <= lstart; start++) {
    bool ok = true;
    for (int pos = 0; pos < pattern.size(); pos++) {
      if (pattern[pos] == '*') continue;
      if (pattern[pos] != virus[pos+start]) {
        ok = false;
        break;
      }
    }
    if (ok) {
      /*cout << "\n" << virus << "\n";
      for (int i = 0; i < start; i++) cout << ' ';
      cout << pattern << "\n";*/
      return true;
    }
  }
  return false;
}

void main() {
  ifstream fin("virus.in");
  int nSets, nPatterns, nVirii;
  fin >> nSets;

  for (int s = 1; s <= nSets; s++) {
    cout << "Data set #" << s << ":\n";

    string patterns[30];
    fin >> nPatterns;
    for (int p = 0; p < nPatterns; p++) {
      fin >> patterns[p];
    }

    fin >> nVirii;
    for (int v = 1; v <= nVirii; v++) {
      cout << "Virus #" << v << ": ";
      string virus;
      fin >> virus;

      bool matched = false;
      for (int p = 0; p < nPatterns; p++) {
        if (match(virus, patterns[p])) {
          cout << "Nuts. This virus is illegal in Hawaii!\n";
          matched = true;
          break;
        }
      }
      if (!matched) cout << "Cool! Victor can take it with him!\n";
    }

    cout << "\n";
  }
}