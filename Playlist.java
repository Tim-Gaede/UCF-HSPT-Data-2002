//University of Central Florida
//16th Annual High School Programming Tournament
//May 3rd, 2002
//
//Problem Name: On With The Countdown
//Filename: playlist.java
//Input File: playlist.in

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class playlist {
  public static void main(String args[]) {
    try {
      BufferedReader inputbuf = new BufferedReader(new FileReader("PLAYLIST.in"));

      int cases; 	// The amount of test cases

      StringTokenizer input = new StringTokenizer(inputbuf.readLine());
      cases = Integer.parseInt(input.nextToken());

      for (int curcase = 0; curcase < cases; curcase++) { // loop through each case
        int totalsongs, songstooutput;

        input = new StringTokenizer(inputbuf.readLine());
        songstooutput = Integer.parseInt(input.nextToken());
        input = new StringTokenizer(inputbuf.readLine());
        totalsongs = Integer.parseInt(input.nextToken());

        System.out.println("Case #" + (curcase + 1) + ":");

        String song[] = new String[100];	// The name of the song
        int amt[] = new int[100];		// The amount of votes for the song
        boolean used[] = new boolean[100];	// Whether we have already outputted the song

        for (int i = 0; i < totalsongs; i++) {
          input = new StringTokenizer(inputbuf.readLine());
          song[i] = input.nextToken();		// Get info about each song
          amt[i] = Integer.parseInt(input.nextToken());
        }
		
        for (int i = 0; i < songstooutput; i++) {
          int bestamt = -1, bestnum = 0;	/* bestamt will be set to -1 so that it will
						   change upon the first valid song it finds */
          for (int j = 0; j < totalsongs; j++) {
            if (amt[j] > bestamt && !used[j]) {	/* If this song is valid and more popular than
						   what we have, make this the most popular one */
              bestamt = amt[j];
              bestnum = j;
            }
          }
          System.out.println(song[bestnum]); // Output the song and mark that it's been used
          used[bestnum] = true;
        }
        System.out.println();
      }
    }
    catch (Exception e) {System.out.println(e);}
  }
}
