{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Once More Around the Bends
 Filename: diving.pas
 Input File: diving.in
}

program diving(input, output);

var
  nCases, depth, length, i : longint;
  inFile : text;

begin
  assign(inFile, 'diving.in');
  reset(inFile);
  readln(inFile, nCases);
  for i := 1 to nCases do
   begin
    readln(inFile, depth);
    length := (depth div 10) * 2; {time to ascend/descend}
    if (depth > 30) then
      length := length + (depth-30) div 10; {time to decompress}
    length := 60-length;
    if (length < 0) then
      length := 0;
    writeln(length, ' minute(s) at ', depth, ' feet');
    writeln;
   end;
  close(inFile);
end.