// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Meta-Game
// Filename: game.c
// Input File: game.in

#include <stdio.h>

main()
{
   FILE   *infile;
   char   line[256];
   int    numSets;
   int    i;
   int    boardSize;
   int    numPlayers;
   char   board[256];
   int    j;
   int    numRolls;
   char   rolls[256];
   int    currentPos;
   int    k;

   /* Open the file */
   infile = fopen("game.in", "r");

   /* Get the number of data sets */
   fgets(line, sizeof(line), infile);
   sscanf(line, "%d", &numSets);

   /* Loop for each data set */
   for (i=0; i < numSets; i++)
   {
      /* Get the board size and number of players */
      fgets(line, sizeof(line), infile);
      sscanf(line, "%d %d", &boardSize, &numPlayers);

      /* Get the board */
      fgets(board, sizeof(board), infile);

      /* Print the header and then loop through each player */
      printf("Board #%d:\n", i+1);
      for (j=0; j < numPlayers; j++)
      {
         /* Get the number of rolls and the rolls themselves */
         fgets(line, sizeof(line), infile);
         sscanf(line, "%d %s", &numRolls, rolls);

         /* Everybody starts at the first position */
         currentPos = 0;

         /* Process each roll depending on the spot you are on */
         for (k=0; k < numRolls; k++)
         {
            /* Look at the current spot */
            switch (board[currentPos])
            {
               case '>':
                  /* Move forward normally.  The modulo operator (%) will let
                     us wrap around the board easily */
                  currentPos = (currentPos + (rolls[k] - '0')) % boardSize;
                  break;

               case '<':
                  /* Move backwards normally */
                  currentPos = (currentPos - (rolls[k] - '0')) % boardSize;
                  break;

               case ')':
                  /* Move forward at half speed */
                  currentPos = (currentPos + (rolls[k] - '0') / 2) % boardSize;
                  break;

               case '(':
                  /* Move backwards at half speed */
                  currentPos = (currentPos - (rolls[k] - '0') / 2) % boardSize;
                  break;

               case '!':
                  /* Move forward at double speed */
                  currentPos = (currentPos + (rolls[k] - '0') * 2) % boardSize;
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
                  /* Move forward this many extra spaces */
                  currentPos = (currentPos + (rolls[k] - '0') + 
                                (board[currentPos] - '0')) % boardSize;
                  break;

               case '_':
                  /* Only move forward on an even roll */
                  if ( ((rolls[k] - '0') % 2) == 0 )
                     currentPos = (currentPos + 1) % boardSize;
                  break;

               case '|':
                  /* Ignore roll and move backwards one spot */
                  currentPos = (currentPos - 1) % boardSize;
                  break;
            }

            /* Cover the case when we wrap backwards (and multiple times) */
            while (currentPos < 0)
               currentPos += boardSize;
         }

         /* Output ending position for player */
         printf("Player #%d: Ended at position %d\n", j+1, currentPos);
      }

      /* Blank lines between boards */
      printf("\n");
   }

   /* Close the input file */
   fclose(infile);
}

