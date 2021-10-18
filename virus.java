// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Illegal Hawaiian Virus Collection
// Filename: virus.java
// Input File: virus.in

import java.io.*;

public class virus
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader fin = new BufferedReader(new FileReader("virus.in"));
        int nSets = Integer.parseInt(fin.readLine());

        for (int i = 0; i < nSets; i++)
        {
            System.out.println("Data set #" + (i+1) + ":");

            int nPatterns = Integer.parseInt(fin.readLine());
            String[] patterns = new String[nPatterns];
            for (int p = 0; p < patterns.length; p++)
            {
                patterns[p] = fin.readLine();
            }

            int nVirii = Integer.parseInt(fin.readLine());
            for (int v = 0; v < nVirii; v++)
            {
                String virus = fin.readLine();
                System.out.print("Virus #" + (v+1) + ": ");

                boolean matched = false;
                for (int p = 0; p < patterns.length; p++)
                    if (match(virus, patterns[p]))
                    {
                        System.out.println("Nuts. This virus is illegal in Hawaii!");
                        matched = true;
                        break;
                    }
                if (!matched)
                    System.out.println("Cool! Victor can take it with him!");
            }

            System.out.println();
        }
    }

    private static boolean match( String virus, String pattern )
    {
        for (int start = 0; start <= virus.length()-pattern.length(); start++) {
            boolean ok = true;
            for (int pos = 0; pos < pattern.length(); pos++) {
                if (pattern.charAt(pos) == '*') continue;
                if (pattern.charAt(pos) != virus.charAt(pos+start)) {
                    ok = false;
                    break;
                }
            }
            if (ok) return true;
        }
        return false;
    }
}
