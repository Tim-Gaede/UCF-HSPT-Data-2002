{
 University of Central Florida
 16th Annual High School Programming Tournament
 May 3rd, 2002

 Problem Name: Secretly Lost
 Filename: secret.pas
 Input File: secret.in
}

program secret;

{Declare a 255-character string as SentenceType (we'll use this a lot)}
type
   SentenceType = string[255];

var
   fp:text;                   {The input file}
   numMessages:integer;       {The number of messages in the input file}
   messageNum:integer;        {The number of the current message}
   numSentences:integer;      {The number of sentences in the message}
   sentenceNum:integer;       {The number of the current sentence}
   codeIndex:integer;         {The index used to decode the sentence}
   codeSentence:SentenceType; {The current sentence that we're decoding}


{This function decodes the given sentence using the given index}
function decode(sentence:SentenceType; index:integer):SentenceType;

var
    i:integer;
    substring:SentenceType;
    spacePos:integer;
    tailLength:integer;

 begin

   {Copy the sentence to the substring variable}
   substring := sentence;

   {First, we remove the words leading up to the index'th word}
   i := 1;
   while ((i < index) AND (length(substring) > 0)) do
    begin

      {Get the position of the first space character in the string}
      spacePos := pos(' ', substring);

      {Check the position of the next space}
      if (spacePos <= 0) then
       begin
         {If there are no spaces, we've run out of words, so this message
          must be a full stop.  Clear the substring}
         substring := '';
       end
      else
       begin
         {Calculate the length of the string after the space character}
         tailLength := length(substring) - spacePos + 1;

         {Copy the string from the character just after the space character,
          to the end of the string.  This effectively cuts off the first
          word in the string}
         substring := copy(substring, spacePos+1, tailLength);

         {Increment i}
         inc(i);
       end;
    end;

   {If the substring is empty, this sentence represents a full stop}
   if (length(substring) <= 0) then
      decode := 'STOP'
   else
    begin

      {Now, the important word is at the front of substring, so we just
       need to cut off the rest of the string}

      {Get the position of the first space again}
      spacePos := pos(' ', substring);

      {If there is no space (we've gotten to the last word in the
       sentence) set the spacePos variable to one greater than the
       length of the string, to make the calculation below work out}
      if (spacePos <= 0) then
         spacePos := length(substring) + 1;

      {Now, just copy from the beginning of the string to the character just
       before the space}
      substring := copy(substring, 1, spacePos - 1);

      {Return this substring}
      decode := substring;
    end;
 end;

begin {main}

  {First, open the input file}
  assign(fp, 'secret.in');
  reset(fp);

  {Get the number of messages in the file}
  readln(fp, numMessages);

  {Decode each message}
  for messageNum := 1 to numMessages do
   begin

      {Get the number of sentences in the file and the code index}
      readln(fp, numSentences, codeIndex);

      {For each sentence in the message...}
      for sentenceNum := 1 to numSentences do
       begin

         {...read the sentence from the file and decode it, then...}
         readln(fp, codeSentence);

         {...write the decoded word to the output line}
         write(decode(codeSentence, codeIndex), ' ');
       end;

       {Terminate the current line}
       writeln;
   end;

  {Close the input file}
  close(fp);

end.
