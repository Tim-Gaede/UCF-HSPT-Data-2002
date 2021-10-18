// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Spider-Man's Diamond Head Dilemma
// Filename: spidey.java
// Input File: spidey.in

import java.util.StringTokenizer;
import java.io.*;

public class spidey
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader fin = new BufferedReader(new FileReader("spidey.in"));

        int nSets = Integer.parseInt(fin.readLine());
        for (int i = 1; i <= nSets; i++) {

            StringTokenizer tok = new StringTokenizer(fin.readLine());
            // V - number of vertices in web
            int V = Integer.parseInt(tok.nextToken());
            // E - number of interconnections
            int E = Integer.parseInt(tok.nextToken());

            // C stores an array of "connected components"; if C[i] == C[j], then
            // vertices i and j are connected by some path.  We start with C[i] = i,
            // so every vertex is unconnected to every other.  As we see edjes, we
            // change every vertex known to be connected to either end of the edje
            // to have the same value.  When all the edjes are checked, the array
            // will describe a set of connected subgraphs.
            int[] C = new int[V]; // connected components
            for (int v = 0; v < V; v++) {
                C[v] = v;
            }

            boolean isOK = true;
            for (int e = 0; e < E; e++) {
                // StringTokenizer splits a string into whitespace-separated words, or "tokens"
                tok = new StringTokenizer(fin.readLine());

                // read the two sides of the connection from the line tokenizer we made
                int a = Integer.parseInt(tok.nextToken());
                int b = Integer.parseInt(tok.nextToken());

                // the % operator gives the remainder when the first operand is divided by the
                // second.  So (X % 2) is 0 if X is even, and 1 if X is odd.
                // This test makes sure that the edges are not either both even (0==0) or
                // both odd (1==1), since that would be a same-side connection.
                if ((a%2)==(b%2)) {
                    isOK = false; // same-side connection
                }

		// this updates the connected components array
                int Ca = C[a]; // store this because C[a] will change to C[b] when the
                               // for loop gets there
                for (int v = 0; v < V; v++) {
                    if (C[v] == Ca) { // change everything in the connected component
                        C[v] = C[b];  // of a to be in the one for b
                    }
                }
            }

            // This goes through and makes sure that everything has ended up in the same
            // component - ie. that C[x] is the same for all x.
            for (int v = 0; v < V; v++) {
                if (C[v] != C[0]) { // if they're all the same, they're all the same as the first one
                    isOK = false; // graph not connected
                    break;
                }
            }

            //if (V == 1) isOK = false; // special case
            // ^^ can't occur without E == 0, which is not allowed

            if (!isOK) {
                System.out.println("It's the end of the world!\n");
            } else {
                System.out.println("Way to go, Spider-Man!\n");
            }
        }
    }
}
