//University of Central Florida
//16th Annual High School Programming Tournament
//May 3rd, 2002
//
//Problem Name: Parking is Serious Business
//Filename: parking.java
//Input File: parking.in

import java.io.*;
import java.util.*;
import java.lang.*;

class parking {

public static void main (String [] args )
{
	int dataset, num_of_datasets;

	//the buffer to store the "I"/"O" data in
	String line;

	try {
		//open the files for input
		FileReader fd = new FileReader("parking.in");
		BufferedReader in = new BufferedReader( fd );

		//read in the number of datasets to parse
		num_of_datasets = Integer.parseInt( in.readLine() );

		//loop over that number of datasets
		for( dataset=1; dataset<=num_of_datasets; dataset++ )
		{
			int tot_spaces;
			int usage=0;
			int cur_pos=0;

			//print out the dataset header
			System.out.println("Day #" + dataset + ":" );

			//read in the total # of datasets
			tot_spaces = Integer.parseInt( in.readLine() );

			//read in the "I"/"O" data line
			line = in.readLine();

			//parse the "I"/"O" line
			while( cur_pos < line.length() && (line.charAt(cur_pos)=='I' || line.charAt(cur_pos)=='O' ) )
			{
				//if it's a car coming into the lot...
				if( line.charAt(cur_pos)=='I' )
				{
					//is the lot full?
					if( usage>=tot_spaces )
						System.out.println("Parking is Serious Business");

					//if the lot is not full,..
					else
					{
						usage++;
						System.out.println("That will be $1.00");
					}
				}
				else
					//if there's a car leaving the lot...
					usage--;

				//look at the next command
				cur_pos++;
			}

			System.out.println( );
		}
	}	
	//Misc. exception catching...
	catch( FileNotFoundException e )
	{
		System.out.println("couldn't open input");
	}
	catch( IOException e )
	{
		System.out.println("some io exception");
	}

}

}
