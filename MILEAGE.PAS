{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Your Mileage May Vary
 Filename: mileage.pas
 Input File: mileage.in
}

program mileage;
var
   infile:  text;
   line:  string[255];
   numDays, i, start_mileage, end_mileage : longint;

begin
   (* Open the input file *)
   assign(infile, 'mileage.in');
   reset(infile);

   (* Get the number of days *)
   readln(infile, numDays);

   (* Loop for each day *)
   for i := 1 to numDays do
   begin
      (* Get the starting and ending odometer mileage *)
      readln(infile, start_mileage, end_mileage);

      (* Print the answer (end - start = distance travelled) *)
      writeln('The team drove ', (end_mileage-start_mileage),' miles today.');
   end;

   (* Close the input file *)
   close(infile);
end.

