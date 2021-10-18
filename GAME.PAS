{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Meta-Game
 Filename: game.pas
 Input File: game.in
}

program game;

var infile:text;
    line:string;
    numSets:integer;
    i:integer;
    boardSize:integer;
    numPlayers:integer;
    board:string;
    j:integer;
    numRolls:integer;
    ch:char;
    rolls:string;
    currentPos:integer;
    k:integer;

begin
   (* Open the file *)
   assign(infile, 'game.in');
   reset(infile);

   (* Get the number of data sets *)
   readln(infile, numSets);

   (* Loop for each data set *)
   for i:=1 to numSets do
   begin
      (* Get the board size and number of players *)
      readln(infile, boardSize, numPlayers);

      (* Get the board *)
      readln(infile, board);

      (* Print the header and then loop through each player *)
      writeln('Board #', i, ':');
      for j:=1 to numPlayers do
      begin
         (* Get the number of rolls and the rolls themselves *)
         readln(infile, numRolls, ch, rolls);

         (* Everybody starts at the first position.  We keep this zero-based
            because it makes doing the mod operator easier.  However, our
            board string is one-based so we have to be careful *)
         currentPos:=0;

         (* Process each roll depending on the spot you are on *)
         for k:=1 to numRolls do
         begin
            (* Look at the current spot *)
            if ( (board[currentPos+1] >= '1') and (board[currentPos+1] <= '9') ) then
               (* Move forward this many extra spaces *)
               currentPos:=(currentPos + (ord(rolls[k]) - ord('0')) +
                            (ord(board[currentPos+1]) - ord('0'))) mod boardSize
            else
            begin
               case board[currentPos+1] of
                  '>':
                     (* Move forward normally.  The modulo operator (mod) will let
                        us wrap around the board easily *)
                     currentPos:=(currentPos + (ord(rolls[k]) - ord('0'))) mod boardSize;

                  '<':
                     (* Move backwards normally *)
                     currentPos:=(currentPos - (ord(rolls[k]) - ord('0'))) mod boardSize;

                  ')':
                     (* Move forward at half speed *)
                     currentPos:=(currentPos + (ord(rolls[k]) - ord('0')) div 2) mod boardSize;

                  '(':
                     (* Move backwards at half speed *)
                     currentPos:=(currentPos - (ord(rolls[k]) - ord('0')) div 2) mod boardSize;

                  '!':
                     (* Move forward at double speed *)
                     currentPos:=(currentPos + (ord(rolls[k]) - ord('0')) * 2) mod boardSize;

                  '_':
                     (* Only move forward on an even roll *)
                     if ( ((ord(rolls[k]) - ord('0')) mod 2) = 0 ) then
                        currentPos:=(currentPos + 1) mod boardSize;

                  '|':
                     (* Ignore roll and move backwards one spot *)
                     currentPos:=(currentPos - 1) mod boardSize;
               end;
            end;

            (* Fix problem when wrapping backwards *)
            while (currentPos < 0) do
               currentPos:=currentPos + boardSize;
         end;

         (* Output ending position for player *)
         writeln('Player #', j, ': Ended at position ', currentPos);
      end;

      (* Blank lines between boards *)
      writeln;
   end;

   (* Close the input file *)
   close(infile);
end.

