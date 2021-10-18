{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Parking is Serious Business
 Filename: parking.pas
 Input File: parking.in
}

program parking;
var
    string_length, number_datasets, current_dataset, number_lots: longint;
    usage_string: string[200];
    index, usage_count: longint;
    input: text;
begin
    (* Open the input file. *)
    assign(input, 'parking.in');
    reset(input);

    (* Read in the number of datasets. *)
    readln(input, number_datasets);

    (* Process each dataset. *)
    for current_dataset := 1 to number_datasets do
    begin
        (* Print out a message on which dataset we are processing. *)
        writeln('Day #',current_dataset, ':');

        (* Read in the number of lots for this dataset. *)
        readln(input, number_lots);
        usage_count := number_lots;

        (* Read in the use string. *)
        readln(input, usage_string);

        (* Get the length of the string. *)
        string_length := length(usage_string);

        (* Parse the usage string. *)
        for index := 1 to string_length do
        begin
            (* If there is an insertion and space, charge. *)
            if (usage_string[index] = 'I') and (usage_count > 0) then
            begin
                usage_count := usage_count - 1;
                writeln('That will be $1.00');
            end
            else
            (* If there is an insertion and no sapce, excuse. *)
            if (usage_string[index] = 'I') and (usage_count = 0) then
            begin
                writeln('Parking is Serious Business');
            end
            else
            (* If there is an out event and enough lots, process. *)
            if (usage_string[index] = 'O')and(usage_count < number_lots) then
            begin
                usage_count := usage_count + 1;
            end;
        end;
        (* Write the separating empty line. *)
        writeln;
    end;

    (* Close the input file. *)
    close(input);
end.
