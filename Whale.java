// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Parawhaling!
// Filename: whale.java
// Input File: whale.in

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

class whale {
  public static void main(String args[]) {
    try {
      int datasets;

      BufferedReader inputbuf = new BufferedReader(new FileReader("WHALE.in"));
      StringTokenizer input = new StringTokenizer(inputbuf.readLine());
      datasets = Integer.parseInt(input.nextToken());

      for (int i = 1; i <= datasets; i++) {	// Loop through every data set
        System.out.println("Day #" + i + ":\n  0,  0");

        int numpoints;
        input = new StringTokenizer(inputbuf.readLine());
        numpoints = Integer.parseInt(input.nextToken());

        /* An array of coordinates; [?][0] stores the x location; [?][1] stores the y location */
        double coordinates[][] = new double[100][2];
        boolean used[] = new boolean[100];	/* Marks whether the points are used (JAVA
						   initializes all elements to false) */
        int amtleft = numpoints;

        for (int j = 0; j < numpoints; j++) {
          input = new StringTokenizer(inputbuf.readLine());
          coordinates[j][0] = Integer.parseInt(input.nextToken());
          coordinates[j][1] = Integer.parseInt(input.nextToken());
        }

        double curx = 0, cury = 0;

        while (amtleft != 0) {
          double bestdist = 1e300;	/* Stores the minimum distance between the current
					   location and the next point; I initialized it with a
					   large value so that it will definitely be set to the
					   next unused point */
          int bestloc = 0;
          for (int j = 0; j < numpoints; j++) {
            /* Calculate the distance between the where we are and the point we are evaluating
	       (subtract 3 since we can observe the point from 3 miles away) */
            double curdist = Math.sqrt(Math.pow(curx - coordinates[j][0], 2) + Math.pow(cury - coordinates[j][1], 2)) - 3;
            if (bestdist > curdist && !used[j]) {
              bestdist = curdist;
              bestloc = j;
            }
          }

          if (bestdist > 0) { /* bestdist will only be less than 0 if we don't have to move
				 from where we are to observe the next point */

            // Calculate the angle and compute distance we must move along the x and y-axes
            double angle = Math.atan2(coordinates[bestloc][1] - cury, coordinates[bestloc][0] - curx);
            curx += bestdist * Math.cos(angle);
            cury += bestdist * Math.sin(angle);
            String x = new Integer( (int)coordinates[bestloc][0]).toString();
            while (x.length() < 3)
               x = " " + x;
            String y = new Integer((int)coordinates[bestloc][1]).toString();
            while (y.length() < 3)
               y = " " + y;
            System.out.println(x + "," + y);

          }

          used[bestloc] = true;
          amtleft--;
        }
        System.out.println();
      }
    }
    catch (Exception e) {System.out.println(e);}
  }
}
