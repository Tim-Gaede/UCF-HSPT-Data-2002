// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Your Mileage May Vary
// Filename: mileage.c
// Input File: mileage.in

#include <stdio.h>

main()
{
   FILE   *infile;
   char   line[256];
   int    numDays;
   int    i;
   int    start;
   int    end;

   /* Open the input file */
   infile = fopen("mileage.in", "r");
   
   /* Get the number of days */
   fgets(line, sizeof(line), infile);
   sscanf(line, "%d", &numDays);

   /* Loop for each day */
   for (i=0; i < numDays; i++)
   {
      /* Get the starting and ending odometer mileage */
      fgets(line, sizeof(line), infile);
      sscanf(line, "%d %d", &start, &end);

      /* Print the answer (end - start = distance travelled) */
      printf("The team drove %d miles today.\n", end-start);
   }

   /* Close the input file */
   fclose(infile);
}

