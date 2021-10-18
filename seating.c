//University of Central Florida
//16th Annual High School Programming Tournament
//May 3rd, 2002
//
//Problem Name: Hawaiian Fire Drill!
//Filename: seating.c
//Input File: seating.in

#include <stdio.h>

FILE * input;

int main()
{
	unsigned long num_of_datasets, dataset, total_people, drivers;
	unsigned long i;
	
	//open up the file for input (checking for errors!)
	if( (input=fopen("seating.in","r"))==NULL )
	{
		printf("bad file!\n");
		exit(1);
	}

	//read in the total number of datasets
	fscanf( input, "%d\n",&num_of_datasets );

	//loop over those datasets
	for( dataset=1; dataset<=num_of_datasets; dataset++ )
	{
		unsigned long comb; //store the total # of combinations so far

		//read in the data
		fscanf(input, " %d %d", &total_people, &drivers );

		//To find out how many combinations there are, 
		//take the number of drivers and multiply it 
		//by (total # of people-1)! (the ! indicates a factorial)
		//for example, if the number of drivers is 2 and the total
		// number of people is 5, then the answer would be:
		// 2*4*3*2*1

		//first, start off with the number of drivers
		comb=drivers;

		//then, while there are still people , multiply to find the number of possible combinations
		while( --total_people > 0 )
			comb*=total_people;
		
		//print the output (using %u to handle unsigned longs)
		printf("Data set #%u: %u combination(s) possible.\n", dataset, comb);

	}
}
