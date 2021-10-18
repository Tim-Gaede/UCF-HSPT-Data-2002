// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Once More Around the Bends
// Filename: diving.cpp
// Input File: diving.in

#include <stdio.h>
#include <stdlib.h>

void main ()
{
 freopen("diving.in", "r", stdin);
 int nCases, depth, length;
 scanf("%d", &nCases);
 for (int i=0;i<nCases;i++)
  {
   scanf("%d", &depth);
   length = (depth/10)*2; //time spent ascending and descending
   if (depth > 30)
     length += (depth-30)/10; //time spent decompressing
   length = 60 - length; //you only have an hour, and we print the time left after travel
   if (length < 0)
     length = 0; //negative times are silly
   printf("%d minute(s) at %d feet\n\n", length, depth);
  }
}