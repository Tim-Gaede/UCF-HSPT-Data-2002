// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Secretly Lost
// Filename: secret.java
// Input File: secret.in

import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;

public class secret
{
    // Basic constructor
    public secret()
    {
    }

    // This function decodes the given sentence using the given index
    protected String decode(String sentence, int index)
    {
        StringTokenizer words;
        String          word;
        int             i;

	// Initialize word (mainly to keep the compiler happy)
	word = new String("");

        // Create the string tokenizer to parse the sentence into
        // words
        words = new StringTokenizer(sentence);

        // First, we remove the words leading up to the index'th word
        // Get the first token (word) of the sentence.
        i = 0;
        while ((i < index) && (words.hasMoreElements()))
        {
            // Increment i
            i++;

            // Get the next word in the sentence
            word = words.nextToken();
        }

        // If we ran out of words in the string, this sentence is a full stop
        if (i < index)
        {
            return new String("STOP");
        }
        else
        {
            return word;
        }
    }

    public void solve() throws Exception
    {
        BufferedReader fp;    // The input file

        int    numMessages;   // The number of messages in the input file
        int    messageNum;    // The number of the current message
        int    numSentences;  // The number of sentences in the message
        int    sentenceNum;   // The number of the current sentence
        int    codeIndex;     // The index used to decode the sentence
        String codeSentence;  // The current sentence that we're decoding
        String decodedWord;   // The decoded word for the current sentence
        String tempStr, arg;  // Temporary strings for reading the
                              // numeric information from the file

        // First, open the input file
        fp = new BufferedReader(new FileReader("secret.in"));

        // Get the number of messages in the file
        tempStr = fp.readLine();
        numMessages = Integer.parseInt(tempStr);

        // Decode each message
        for (messageNum = 0; messageNum < numMessages; messageNum++)
        {
            // Get the number of sentences in the file
            tempStr = fp.readLine();
            arg = tempStr.substring(0, tempStr.indexOf(" "));
            numSentences = Integer.parseInt(arg);

            // Get the code index
            arg = tempStr.substring(tempStr.indexOf(" ")+1,
                                    tempStr.length());
            codeIndex = Integer.parseInt(arg);

            // For each sentence in the message...
            for (sentenceNum = 0; sentenceNum < numSentences; sentenceNum++)
            {
                // ...read the sentence from the file and decode it, then...
                codeSentence = fp.readLine();

                // ...write the decoded word to the output line
                decodedWord = decode(codeSentence, codeIndex);
                System.out.print(decodedWord + " ");
            }

            // Terminate the current line
            System.out.print("\n");
        }

        // Close the input file
        fp.close();
    }

    public static void main(String[] args) throws Exception
    {
        secret secretInst = new secret();
        secretInst.solve();
    }
}
