//University of Central Florida
//16th Annual High School Programming Tournament
//May 3rd, 2002
//
//Problem Name: Hawaiian Fire Drill!
//Filename: seating.java
//Input File: seating.in

import java.io.*;
import java.lang.*;
import java.util.*;

class seating {

public static void main (String [] args)
{
	int drivers, comb, total, dataset, num_of_datasets;
	String line;

	try
	{
		//open up the files
		FileReader fr = new FileReader("seating.in");
		BufferedReader in = new BufferedReader(fr);

		//read in the number of datasets
		num_of_datasets = Integer.parseInt( in.readLine() );

		for(dataset=1; dataset<=num_of_datasets; dataset++ )
		{
			//first we need to split up the two numbers from
			//the input string that readLine() returns, so
			//we use a tokenizer
			StringTokenizer tk = new StringTokenizer( in.readLine());
			total = Integer.parseInt( tk.nextToken() );
			drivers = Integer.parseInt( tk.nextToken() );

			//to solve this problem, take the number of drivers
			//and multiply it by the factorial of the number of
			//drivers minus 1.
			//for example, if there are 5 people and 2 drivers,
			//the formula is: 2*4*3*2*1

			comb=drivers;
			while( --total > 0 )
				comb*=total;

			//output the answer
			System.out.println("Data set #" + dataset + ": " + comb + " combination(s) possible." );
		}

	}
	//Misc. exception catching stuff...
	catch ( FileNotFoundException e )
	{
		System.out.println( "couldn't open input" );
	}	
	catch( IOException e )
	{
		System.out.println( "some IO Exception");
	}

}

}
