{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Spider-Man's Diamond Head Dilemma
 Filename: spidey.pas
 Input File: spidey.in
}

program Spidey;

var
   fin: Text;
   nSets, V, E, s, i, j, a, b: longint;
   C: ARRAY [1 .. 255] OF longint;
   isOK: boolean;

begin
   assign(fin, 'spidey.in');
   reset(fin);

   readln(fin, nSets);

   for s := 1 to nSets do begin
      {V - number of vertices in web
       E - number of interconnections}
      readln(fin, V, E);

      { C stores an array of "connected components"; if C[i] == C[j], then
        vertices i and j are connected by some path.  We start with C[i] = i,
        so every vertex is unconnected to every other.  As we see edjes, we
        change every vertex known to be connected to either end of the edje
        to have the same value.  When all the edjes are checked, the array
        will describe a set of connected subgraphs. }
      for i := 1 to V do
         C[i] := i;

      isOK := true;

      for i := 1 to E do begin
         readln(fin, a, b);
         { The mod operator gives the remainder when the first operand is divided by the
           second.  So (X mod 2) is 0 if X is even, and 1 if X is odd.
           This test makes sure that the edges are not either both even (0=0) or
           both odd (1=1), since that would be a same-side connection. }
         if ((a mod 2)=(b mod 2)) then
            isOK := false;

         inc(a); inc(b); { Pascal 1-based arrays... }
         a := C[a];
         b := C[b];

         { this updates the connected components array. }
         for j := 1 to V do
           if (C[j]=a) then
              C[j] := b;
      end;

      { This goes through and makes sure that everything has ended up in the same
        component - ie. that C[x] is the same for all x. }
      for i := 1 to V do begin
         if (C[i] <> C[1]) then begin
            isOK := false;
            break;
         end;
      end;

      if (isOK) then begin
         writeln('Way to go, Spider-Man!');
         writeln;
      end else begin
         writeln('It''s the end of the world!');
         writeln;
      end;
   end;

   close(fin);
end.
