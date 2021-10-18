// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Once More Around the Bends
// Filename: diving.java
// Input File: diving.in

import java.io.*;

class diving
{
 public static void main (String[] args) throws Exception
  {
   int nCases, length, depth, i;
   BufferedReader br = new BufferedReader(new FileReader("diving.in"));
   nCases = Integer.parseInt(br.readLine());
   for (i=0;i<nCases;i++)
    {
     depth = Integer.parseInt(br.readLine());
     length = (depth / 10) * 2; //time to ascend and descend
     if (depth > 30)
       length += (depth-30) / 10; //time to decompress
     length = 60-length;
     if (length < 0)
       length = 0;
     System.out.println(length + " minute(s) at " + depth + " feet\n");
    }
  }
}
