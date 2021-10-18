// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Meta-Game
// Filename: game.java
// Input File: game.in

import java.io.*;
import java.util.StringTokenizer;

public class game
{
   public static void main(String[] args)
   {
      try
      {
         BufferedReader   inputFile;
         String           line;
         int              numSets;
         int              i;
         int              boardSize;
         int              numPlayers;
         String           board;
         int              j;
         int              numRolls;
         String           rolls;
         int              currentPos;
         int              k;

         inputFile = new BufferedReader(new FileReader(new File("game.in")));
 
         // Read the number of cases from the file
         numSets = Integer.parseInt(inputFile.readLine());

         // Process each of the cases
         for(i=0; i < numSets; i++)
         {
            StringTokenizer   st;

            // Get the board size and number of players
            st = new StringTokenizer(inputFile.readLine());
            boardSize = Integer.parseInt(st.nextToken());
            numPlayers = Integer.parseInt(st.nextToken());

            // Get the board
            board = inputFile.readLine();

            // Print the header and then loop through each player
            System.out.println("Board #" + (i+1) + ":");
            for (j=0; j < numPlayers; j++)
            {
               // Get the number of rolls and the rolls themselves
               st = new StringTokenizer(inputFile.readLine());
               numRolls = Integer.parseInt(st.nextToken());
               rolls = st.nextToken();

               // Everybody starts at the first position
               currentPos = 0;

               // Process each roll depending on the spot you are on
               for (k=0; k < numRolls; k++)
               {
                  // Look at the current spot
                  switch (board.charAt(currentPos))
                  {
                     case '>':
                        // Move forward normally.  The modulo operator (%) will
                        // let us wrap around the board easily
                        currentPos = (currentPos + (rolls.charAt(k) - '0')) % boardSize;
                        break;

                     case '<':
                        // Move backwards normally
                        currentPos = (currentPos - (rolls.charAt(k) - '0')) % boardSize;
                        break;

                     case ')':
                        // Move forward at half speed
                        currentPos = (currentPos + (rolls.charAt(k) - '0') / 2) % boardSize;
                        break;

                     case '(':
                        // Move backwards at half speed
                        currentPos = (currentPos - (rolls.charAt(k) - '0') / 2) % boardSize;
                        break;

                     case '!':
                        // Move forward at double speed
                        currentPos = (currentPos + (rolls.charAt(k) - '0') * 2) % boardSize;
                        break;

                     case '1':
                     case '2':
                     case '3':
                     case '4':
                     case '5':
                     case '6':
                     case '7':
                     case '8':
                     case '9':
                        // Move forward this many extra spaces
                        currentPos = (currentPos + (rolls.charAt(k) - '0') +
                                      (board.charAt(currentPos) - '0')) % boardSize;
                        break;

                     case '_':
                        // Only move forward on an even roll
                        if ( ((rolls.charAt(k) - '0') % 2) == 0 )
                           currentPos = (currentPos + 1) % boardSize;
                        break;

                     case '|':
                        // Ignore roll and move backwards one spot
                        currentPos = (currentPos - 1) % boardSize;
                        break;
                  }

                  // Fix the case when wrapping backwards
                  while (currentPos < 0)
                     currentPos += boardSize;
               }

               // Output ending position for player
               System.out.println("Player #" + (j+1) + ": Ended at position " +
                                  currentPos);
            }

            // Blank lines between boards
            System.out.println();
         }

         // Close the input file
         inputFile.close();
      }
      catch(IOException e)
      {
      }
   }
}
