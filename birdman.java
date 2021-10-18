// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Birdman of Waikiki
// Filename: birdman.java
// Input File: birdman.in

import java.io.*;
import java.util.*;

class birdman
{
 static class TPoint
 {
  int x, y;
  TPoint(StringTokenizer st)
   {
    x = Integer.parseInt(st.nextToken());
    y = Integer.parseInt(st.nextToken());
   }
 };
//The idea here is to set up a system of parametric equations for the segments.
//For the first segment, we have:
//  x = A.x + t1*(B.x-A.x)
//  y = A.y + t1*(B.y-A.y)
//For the second segment:
//  x = M.x + t2*(N.x-M.x)
//  y = M.y + t2*(N.y-M.y)
//Setting them equal to each other and rearranging, we get the following system:
//  t1(B.x-A.x) + t2(M.x-N.x) = M.x-A.x
//  t1(B.y-A.y) + t2(M.y-N.y) = M.y-A.y
//The simplest way to solve a system of two equations and two unknowns is to use
//Cramer's Rule.  Cramer's Rule is pretty simple, but I'm going to use a different
//system to explain it.  Suppose we had a system:
//  A11x + A12y = B1
//  A21x + A22y = B2
//Cramer's Rule simply states that:
//       |B1  A12|
//       |B2  A22|
//   x = ---------
//       |A11 A12|
//       |A21 A22|
//and
//       |A11  B1|
//       |A21  B2|
//   y = ---------
//       |A11 A12|
//       |A21 A22|
//So, anyway, solve for t1 and t2 using Cramer's Rule.  Then, you just have to
//make sure that t1 and t2 are both in the interval [0,1].  If they are, the segments
//intersect.  If they're not, the lines that the segments are on intersect, but at some 
//place past the segments.  And if the determinant that is the denominator of the equations
//is zero, then the segments are either parallel or collinear.
 static boolean SegIntersect(TPoint A, TPoint B, TPoint M, TPoint N)
  {
   int dx1, dx2, dx3, dy1, dy2, dy3;
   dx1 = B.x-A.x;
   dx2 = M.x-N.x;
   dx3 = M.x-A.x;
   dy1 = B.y-A.y;
   dy2 = M.y-N.y;
   dy3 = M.y-A.y;
   float determ = dx1*dy2-dx2*dy1;
   if (determ == 0)
     return false;
   float t1, t2;
   t1 = (dx3*dy2-dx2*dy3)/determ;
   t2 = (dx1*dy3-dx3*dy1)/determ;
   return (t1 <=1) && (t1 >= 0) && (t2 >= 0) && (t2 <= 1);
  }
 public static void main (String[] args) throws Exception
  {
   int nCases;
   BufferedReader br = new BufferedReader(new FileReader("birdman.in"));
   nCases = Integer.parseInt(br.readLine());
   
   for (int i=0;i<nCases;i++)
    {
     StringTokenizer st = new StringTokenizer(br.readLine());
     TPoint B = new TPoint(st);
     TPoint S = new TPoint(st);
     TPoint P1 = new TPoint(st);
     TPoint P2 = new TPoint(st);
     if (SegIntersect(B, S, P1, P2))
       System.out.println("Move to the left or right!");
     else
       System.out.println("Good picture, Birdman!");
    }
  }
};
