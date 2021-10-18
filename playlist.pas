{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Meta-Game
 Filename: playlist.pas
 Input File: playlist.in
}

program PLAYLIST;

var
   i             : longint;
   j             : longint;
   curcase       : longint;
   cases         : longint; {The amount of test cases}
   totalsongs    : longint;
   songstooutput : longint;
   song          : ARRAY [0 .. 100, 0 .. 300] of char;  {The name of the song}
   amt           : ARRAY [0 .. 100] of longint; {The amount of votes for the song}
   used          : ARRAY [0 .. 100] of boolean; {Whether we have already outputted the song}
   bestamt       : longint;
   bestnum       : longint;
   nextchar      : char;
   fin           : Text;


begin
     assign(fin, 'PLAYLIST.IN');
     reset(fin);

     readln(fin, cases);

     for curcase := 0 to cases - 1 do {loop through each case}
     begin
        readln(fin, songstooutput);
	readln(fin, totalsongs);

        writeln('Case #', curcase + 1, ':');

	for i := 0 to totalsongs - 1 do
        begin
            read(fin, nextchar);

            used[i] := false; {Mark this song as unused (since it hasn't been output yet}

            j := 0;

            while (nextchar <> ' ') do
            begin
                 song[i][j] := nextchar;
                 j := j + 1;
                 read(fin, nextchar);
            end;
            song[i][j] := chr(0);

            readln(fin, amt[i]);            {Get info about each song}
        end;

	for i := 0 to songstooutput - 1 do
        begin
             {bestamt will be set to -1 so that it will change upon the
              first valid song it finds}

             bestamt := -1;
             bestnum := 0;

	     for j := 0 to totalsongs - 1 do
             begin
                  {If this song is valid and more popular than what we have, make
                   this the most popular one}

                  if ((amt[j] > bestamt) AND (used[j] = false)) then
                  begin
		       bestamt := amt[j];
		       bestnum := j;
		  end;
             end;

	     writeln(song[bestnum]); {Output the song and mark that it's been used}
             used[bestnum] := true;
	end;

        writeln('');
     end;
end.