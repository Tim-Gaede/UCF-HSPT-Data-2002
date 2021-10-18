// University of Central Florida
// 16th Annual High School Programming Tournament
// May 3rd, 2002
//
// Problem Name: Your Mileage May Vary
// Filename: mileage.java
// Input File: mileage.in
 
import java.io.*;
import java.util.*;

class mileage {
  public static void main(String[] args) throws Exception
  {
    BufferedReader fin = new BufferedReader(new FileReader("mileage.in"));

    int numDays = Integer.parseInt(fin.readLine());

    for (int day = 0; day < numDays; day++) {
        StringTokenizer mileReader = new StringTokenizer(fin.readLine());
        int start = Integer.parseInt(mileReader.nextToken());
        int end = Integer.parseInt(mileReader.nextToken());

        System.out.println("The team drove " + (end-start) + " miles today.");
    }

    fin.close();
  }
}
