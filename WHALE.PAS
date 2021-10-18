{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Parawhaling!
 Filename: whale.pas
 Input File: whale.in
}

program WHALE;

function atan2(a, b : real):real;

begin
     if ((a < 0) AND (b < 0)) then begin
        atan2 := arctan(a / b) - Pi;
     end
     else if (b < 0) then begin
        atan2 := arctan(a / b) + Pi;
     end
     else
        atan2 := arctan(a/b);
end;

var
   fin         : Text;
   i           : longint;
   input       : longint;
   datasets    : longint;
   numpoints   : longint;
   coordinates : Array[0 .. 1000, 0 .. 1] of real; {An array of coordinates;
                                                    [?][0] stores the x location;
                                                    [?][1] stores the y location}
   used        : Array[0 .. 1000] of boolean;      {Marks whether the points are used}
   amtleft     : longint;
   j           : longint;
   curx        : real;
   cury        : real;
   bestloc     : longint;
   bestdist    : real;
   curdist     : real;
   angle       : real;

begin
     assign(fin, 'WHALE.IN');
     reset(fin);

     readln(fin, datasets);

     for i := 1 to datasets do {Loop through every data set}
     begin
         writeln('Day #', i, ':');
         writeln('  0,  0');

         readln(fin, numpoints);

         amtleft := numpoints;

         for j := 0 to numpoints - 1 do
         begin
              readln(fin, coordinates[j][0], coordinates[j][1]);
              used[j] := false;
         end;

         curx := 0;
         cury := 0;

         while (amtleft <> 0) do
         begin
              bestdist := 1e38; {Stores the minimum distance between the current
                                  location and the next point; I initialized it with a
                                  large value so that it will definitely be set to the
                                  next unused point}
              for j := 0 to numpoints - 1 do {Calculate the distance between the where we are and the point we are
                                              evaluating (subtract 3 since we can observe the point from 3 miles away)}
              begin
                   curdist := sqrt(sqr(curx - coordinates[j][0]) + sqr(cury - coordinates[j][1])) - 3;
                   if ((bestdist > curdist) AND (used[j] = false)) then
                   begin
                        bestdist := curdist;
                        bestloc := j;
                   end;
              end;


              if (bestdist > 0) then {bestdist will only be less than 0 if we don't have to move
                                      from where we are to observe the next point}
              begin
                   {Calculate the angle and compute distance we must move along the x and y-axes}
                   angle := atan2(coordinates[bestloc][1] - cury, coordinates[bestloc][0] - curx);
                   curx := curx + bestdist * cos(angle);
                   cury := cury + bestdist * sin(angle);
                   writeln(coordinates[bestloc][0]:3:0, ',', coordinates[bestloc][1]:3:0);

              end;

              used[bestloc] := true;
              amtleft := amtleft - 1;
         end;

         writeln('');
     end;
end.