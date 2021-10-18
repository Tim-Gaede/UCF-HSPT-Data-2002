{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Conflicting Conferences
 Filename: schedule.pas
 Input File: schedule.in
}

program scheduler;

var
    input: text;
    conflicts: boolean;
    line: string[255];
    roomused : array[0..23] of longint;
    i, num_of_datasets, rental, start_time, end_time: longint;

begin
    (* Open the input file. *)
    assign(input, 'schedule.in');
    reset(input);

    (* Read in the number of datasets. *)
    readln(input, num_of_datasets);

    (* Process each dataset. *)
    for num_of_datasets := num_of_datasets downto 1 do
    begin

        (* Initialize the conflicts variable to false per dataset. *)
        conflicts := false;

        (* Read in and print the title line for the current dataset. *)
        readln(input, line);
        writeln(line);

        (* Reset the roomused array to all 0s to mean nothing is in use. *)
        for i := 0 to 23 do
            roomused[i] := 0;

        (* Read in the number of rental times. *)
        readln(input, rental);

        (* Process each rental time. *)
        for rental := rental downto 1 do
        begin
            (* Read in the start and end times of the current rental. *)
            readln(input, start_time, end_time);

            (* Process each hour. *)
            end_time := end_time - 1;
            for i := start_time to end_time do
            begin
                roomused[i] := roomused[i] + 1;
                (* If the room was used more than once per hour, conflict. *)
                if (roomused[i] > 1) then
                    conflicts := true;
            end;
        end;

        (* If there was a conflict, print the hour(s). *)
        if (conflicts) then
        begin
            write('CONFLICTS AT ');
            for i := 0 to 23 do
            begin
                if (roomused[i] > 1) then
                    write(i, '-', i+1, ' ');
            end;
            writeln;writeln;
        end
        else
        begin
            (* Else. it's all good. *)
            writeln('SCHEDULE OK!');
            writeln;
        end;
    end;
end.

