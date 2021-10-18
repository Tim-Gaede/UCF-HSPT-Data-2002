// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: On With The Countdown
// Filename: playlist.cpp
// Input File: playlist.in

#include <fstream.h>

int main(void) {
	ifstream input("PLAYLIST.in");

	int cases;		// The amount of test cases
	input >> cases;

	for (int curcase = 0; curcase < cases; curcase++) { // loop through each case
		int totalsongs, songstooutput;
		input >> songstooutput >> totalsongs; // Read in input data
		cout << "Case #" << curcase + 1 << ":\n";

		char song[100][1000];	// The name of the song
		int amt[100];			// The amount of votes for the song
		bool used[100] = {0};	// Whether we have already outputted the song

		for (int i = 0; i < totalsongs; i++)
			input >> song[i] >> amt[i];	// Get info about each song
		
		for (i = 0; i < songstooutput; i++) {
			int bestamt = -1, bestnum = 0;	/* bestamt will be set to -1 so that it will change
											upon the first valid song it finds */
			for (int j = 0; j < totalsongs; j++) {
				if (amt[j] > bestamt && !used[j]) {	/* If this song is valid and more popular
													than what we have, make this the most
													popular one */
					bestamt = amt[j];
					bestnum = j;
				}
			}
			cout << song[bestnum] << '\n'; // Output the song and mark that it's been used
			used[bestnum] = true;
		}
		cout << '\n';			
	}

	return 0;
}