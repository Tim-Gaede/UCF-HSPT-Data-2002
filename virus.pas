{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Illegal Hawaiian Virus Collection
 Filename: virus.pas
 Input File: virus.in
}

program virusChecker;

var
   fin: Text;
   nSets, nVirii, nPatterns: longint;
   s, v, p: longint;
   patterns: ARRAY [ 1 .. 30 ] of String;
   virus: String;
   matched: Boolean;

function match(str, pattern: String):Boolean;

var
   start, pos, lstart: longint;
   ok, retval: Boolean;

begin
   lstart := Length(str) - Length(pattern) + 1;
   retval := false;
   for start := 1 to lstart do begin
      ok := true;
      for pos := 1 to Length(pattern) do begin
         if (pattern[pos] = '*') then continue;
         if (pattern[pos] <> str[pos+start-1]) then begin
            ok := false;
            break;
         end;
      end;
      if ok then begin
        retval := true;
        break;
      end;
    end;
    match := retval;
end;

begin
   assign(fin, 'virus.in');
   reset(fin);

   readln(fin, nSets);

   for s := 1 to nSets do begin
      writeln('Data set #', s, ':');

      readln(fin, nPatterns);
      for p := 1 to nPatterns do
         readln(fin, patterns[p]);

      readln(fin, nVirii);
      for v := 1 to nVirii do begin
         write('Virus #', v, ': ');
         readln(fin, virus);
         matched := False;
         for p := 1 to nPatterns do begin
            if match(virus, patterns[p]) then begin
               matched := True;
               writeln('Nuts. This virus is illegal in Hawaii!');
               break;
            end;
         end;

         if not matched then writeln('Cool! Victor can take it with him!');
      end;

      writeln;

   end;

   close(fin);
end.
