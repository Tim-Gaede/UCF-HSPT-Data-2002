{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Conflicting Conferences
 Filename: seating.pas
 Input File: seating.in
}

program seating;
var
    input : text;
    num_of_datasets, dataset, total_people, drivers : longint;

begin
    (* Assign and open the input file. *)
    assign(input, 'seating.in');
    reset(input);

    (* Read the header which specifies how many data sets. *)
    readln(input, num_of_datasets);

    (* Read each dataset entry. *)
    for dataset:=1 to num_of_datasets do
    begin
        (* Read in the number of people and how many can drive. *)
        readln(input, total_people, drivers);

        (* Calculate the amount of combinations. *)
        for total_people := total_people - 1 downto 1 do
        begin
            drivers := drivers * total_people;
        end;

        (* Write out the result. *)
        writeln('Data set #', dataset, ': ', drivers,
                ' combination(s) possible.');
    end;

    (* Close the input file. *)
    close(input);
end.
