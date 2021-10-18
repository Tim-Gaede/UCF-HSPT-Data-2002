//University of Central Florida
//16th Annual High School Programming Tournament
//May 3rd, 2002
//
//Problem Name: Parking is Serious Business
//Filename: parking.c
//Input File: parking.in

#include <stdio.h>

int main()
{
	int num_of_datasets, dataset;
	char line[500];	//buffer for lines that have been read in

	//open up the input file and use it as keyboard input
	freopen("parking.in","r",stdin);

	//gets is DANGEROUS in production use but there's no hackers here...
	//read in the first line of the file to find out how many datasets
	//there are
	gets(line);
	sscanf(line,"%d",&num_of_datasets );

	//loop over each dataset
	for( dataset=1; dataset<=num_of_datasets; dataset++ )
	{
		int tot_spaces, usage=0, cur_pos=0;

		//print out the dataset number
		printf("Day #%d:\n", dataset );
		
		//find out the total number of parking spaces
		gets(line);
		sscanf(line,"%d",&tot_spaces);

		//read in the in/out data
		gets(line);
		//loop over that data, only valid data is "I" and "O"
		while( line[cur_pos]=='I' || line[cur_pos]=='O' )
		{
			if( line[cur_pos]=='I' && usage==tot_spaces )
				//parking lot is full because there are
				//as many used spaces as total spaces
				printf("Parking is Serious Business\n");
			else if( line[cur_pos]=='I' && usage<tot_spaces )
			{
				//still room available in the parking lot
				usage++;
				printf("That will be $1.00\n");
			}
			else	//else, somebody left (the "O") so remove them from the lot
				usage--;

			//look at the next "I"/"O" now
			cur_pos++;
		}

		//each line gets a newline
		printf("\n");
		
	}
}
