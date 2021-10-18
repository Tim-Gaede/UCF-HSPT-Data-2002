//University of Central Florida
//16th Annual High School Programming Tournament
//May 3rd, 2002
//
//Problem Name: Conflicting Conferences
//Filename: schedule.java
//Input File: schedule.in

import java.io.*;
import java.lang.*;
import java.util.*;

class schedule {

public static void main (String [] args)
{
	int num_of_datasets;
	String line;

	//we use this array to keep track of any conflicts. If the number
	//at any given index i is greater than 1, then that means at hour i,
	//the room has a conflict.
	int[] roomused = new int[24];

	try
	{
		//open the input files.
		FileReader fr = new FileReader("schedule.in");
		BufferedReader in = new BufferedReader(fr);

		//read in the number of datasets
		num_of_datasets = Integer.parseInt( in.readLine() );

		//loop over those datasets
		while( num_of_datasets-- >0 )
		{
			int r,conflicts,i;
			int start,end;

			conflicts=0;

			//print out the name of the room
			System.out.println( in.readLine() );

			//initialize the roomsused array so that no rentals
			//have yet occured
			for(i=0;i<24;i++)
				roomused[i]=0;

			//find out how many reservations there are to read in
			r = Integer.parseInt( in.readLine() );

			//loop over that number of reservations
			while(r-->0)
			{
				//we need to tokenize out the starting and
				//ending times
				StringTokenizer tk = new StringTokenizer( in.readLine() );
				start = Integer.parseInt( tk.nextToken() );

				end = Integer.parseInt( tk.nextToken() );

				//mark the room as rented for these times...
				for(i=start;i<end;i++)
				{
					roomused[i]++;
					if(roomused[i]>1)
						//conflicts is a flag to let
						//us know if any conflicts exist
						conflicts=1;
				}
			}

			//if there are conflicts...
			if( conflicts>0 )
			{
				System.out.print("CONFLICTS AT ");
				for(i=0;i<24;i++)
				{
					if( roomused[i]>1)
					{
						//if the room is rented out more
						//than once at a given hour...

						System.out.print( "" + i + "-" + (i+1) + " " );
					}
				}
				System.out.println();
				System.out.println();
			}
			else
			{
				//if there are no conflicts...
				System.out.println("SCHEDULE OK!");
				System.out.println();
			}
		}

	}
	//misc. exception stuff...
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
