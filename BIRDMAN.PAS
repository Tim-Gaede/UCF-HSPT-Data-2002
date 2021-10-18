{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Birdman of Waikiki
 Filename: birdman.pas
 Input File: birdman.in
}

program birdman(input, output);

type
  TPoint = record
             x : longint;
             y : longint;
           end;

var
  B, S, P1, P2 : TPoint;
  inFile : text;
  nCases, i : longint;

(*
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
//is zero, then the segments are either parallel or collinear.*)

function SegIntersect(var A, B, M, N : TPoint) : boolean;
var
  dx1, dx2, dx3, dy1, dy2, dy3 : longint;
  t1, t2, determ : real;
begin
 dx1 := B.x-A.x;
 dx2 := M.x-N.x;
 dx3 := M.x-A.x;
 dy1 := B.y-A.y;
 dy2 := M.y-N.y;
 dy3 := M.y-A.y;
 determ := dx1*dy2-dx2*dy1;
 if (determ = 0) then
   SegIntersect := false
 else
  begin
   t1 := (dx3*dy2-dx2*dy3)/determ;
   t2 := (dx1*dy3-dx3*dy1)/determ;
   SegIntersect := ((t1 <=1) and (t1 >= 0) and (t2 >= 0) and (t2 <= 1));
  end
end;

begin
  assign(inFile, 'birdman.in');
  reset(inFile);
  readln(inFile, nCases);
  for i := 1 to nCases do
   begin
    readln(inFile, B.x, B.y, S.x, S.y, P1.x, P1.y, P2.x, P2.y);
    if (SegIntersect(B, S, P1, P2)) then
      writeln('Move to the left or right!')
    else
      writeln('Good picture, Birdman!');
   end;
  close(inFile);
end.