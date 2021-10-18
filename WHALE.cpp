// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Parawhaling!
// Filename: whale.cpp
// Input File: whale.in

#include <math.h>
#include <fstream.h>
#include <stdio.h>

int main(void) {
	ifstream input("WHALE.in");

	int datasets;
	input >> datasets;

	for (int i = 1; i <= datasets; i++) {	// Loop through every data set
		//cout << "Day #" << i << ":\n0, 0\n";
        printf("Day #%d:\n  0,  0\n", i);

		int numpoints;
		input >> numpoints;

		double coordinates[100][2]; /* An array of coordinates; [?][0] stores the x location;
									[?][1] stores the y location */
		bool used[1000] = {0};		/* Marks whether the points are used */
		int amtleft = numpoints;

		for (int j = 0; j < numpoints; j++)
			input >> coordinates[j][0] >> coordinates[j][1];

		double curx = 0, cury = 0;

		while (amtleft) {
			double bestdist = 1e300;	/* Stores the minimum distance between the current
										location and the next point; I initialized it with a
										large value so that it will definitely be set to the
										next unused point */
			int bestloc;
			for (int j = 0; j < numpoints; j++) {
				/* Calculate the distance between the where we are and the point we are
				   evaluating (subtract 3 since we can observe the point from 3 miles away) */
				double curdist = sqrt(pow(curx - coordinates[j][0], 2) + pow(cury - coordinates[j][1], 2)) - 3;
				if (bestdist > curdist && !used[j]) {
					bestdist = curdist;
					bestloc = j;
				}
			}

			if (bestdist > 0) { /* bestdist will only be less than 0 if we don't have to move
								from where we are to observe the next point */

				// Calculate the angle and compute distance we must move along the x and y-axes
				double angle = atan2(coordinates[bestloc][1] - cury, coordinates[bestloc][0] - curx);
				curx += bestdist * cos(angle);
				cury += bestdist * sin(angle);
                printf("%3.0f,%3.0f\n", coordinates[bestloc][0], coordinates[bestloc][1]);
   			    //cout << coordinates[bestloc][0] << ", " << coordinates[bestloc][1] << '\n';

			}

			used[bestloc] = true;
			amtleft--;
		}
//		cout << '\n';
        printf("\n");
	}

	return 0;
}