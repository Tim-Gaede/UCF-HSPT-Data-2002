// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Secretly Lost
// Filename: secret.cpp
// Input File: secret.in

#include <stdio.h>
#include <string.h>

// This function decodes the given sentence using the given index
void decode(char *sentence, int index, char *decodeWord)
{
    int  i;
    char substring[1000];
    char *word;
 
    // Copy the sentence to the substring variable
    strcpy(substring, sentence);

    // First, we remove the words leading up to the index'th word

	// Get the first token (word) of the sentence.
	// The first call to strtok() must have the actual string to be
	// tokenized for the first argument.  After the first call, 
	// strtok(NULL, " ") is called to get the rest of the tokens.
	word = strtok(substring, " ");

	// Initialize i to 1 (we've got the first word already)
	i = 1;
    while ((i < index) && (word != NULL))
    {
		// Get the next word in the sentence
		word = strtok(NULL, " ");

		// Increment i
		i++;
    }

    // If we ran out of words in the string, this sentence is a full stop
    if (word == NULL)
	{
        sprintf(decodeWord, "STOP");
	}
    else
	{
		strcpy(decodeWord, word);
    }
}

int main(void)
{
    // Global variables
    FILE *fp;                // The input file
    int  numMessages;        // The number of messages in the input file
    int  messageNum;         // The number of the current message
    int  numSentences;       // The number of sentences in the message
    int  sentenceNum;        // The number of the current sentence
    int  codeIndex;          // The index used to decode the sentence
    char codeSentence[1000]; // The current sentence that we're decoding
	char decodedWord[1000];  // The decoded word for the current sentence

	char *ch;                // Character pointer, used to null-terminate
	                         // the strings read from the file.

	char tempStr[50];        // A temporary string for reading numSentences
	                         // and codeIndex from the file

    // First, open the input file
	fp = fopen("secret.in", "r");

    // Get the number of messages in the file
    fscanf(fp, "%d\n", &numMessages);

    // Decode each message
    for (messageNum = 0; messageNum < numMessages; messageNum++)
    {
        // Get the number of sentences in the file and the code index.
		// Using fscanf() isn't safe here, because the line immediately
		// after this one could be empty, which should be decoded as
		// STOP.  However, if we use fscanf(), it will skip over any 
		// blank lines following the values for numSentences and 
		// codeIndex, which will confuse the program.  
		// Intead, we use fgets() to get only one line (in a string) from 
		// the file, and then use sscanf to read the values from the string.
		fgets(tempStr, sizeof(tempStr), fp);
        sscanf(tempStr, "%d %d", &numSentences, &codeIndex);

        // For each sentence in the message...
        for (sentenceNum = 0; sentenceNum < numSentences; sentenceNum++)
		{
            // ...read the sentence from the file and decode it, then...
            fgets(codeSentence, sizeof(codeSentence), fp);

	        // Make sure the string is null-terminated
	        ch = strchr(codeSentence, '\n');
		    if (ch != NULL)
			    *ch = NULL;

            // ...write the decoded word to the output line
			decode(codeSentence, codeIndex, decodedWord);
            printf("%s ", decodedWord);
		}

        // Terminate the current line
		printf("\n");
    }

    // Close the input file
    fclose(fp);

	return 0;
}
