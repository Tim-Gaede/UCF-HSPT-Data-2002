//University of Central Florida
//16th Annual High School Programming Tournament
//May 3rd, 2002
//
//Problem Name: Conflicting Conferences
//Filename: schedule.c
//Input File: schedule.in

#include <stdio.h>

int main()
{
	//we store how many rentals are occuring at any given hour
	//if the number is greater than 1, that means a conflict has
	//occured
	int roomused[24];

	//this is used as a buffer for the input data
	char line[300];

	int i;
	int num_of_datasets;

	//open the input file and redirect is as keyboard input
	freopen("schedule.in","r",stdin);

	//read in the number of datasets (WARNING: gets is VERY DANGEROUS in
	// production software! but there aren't any hackers here...)
	gets(line);
	sscanf(line,"%d",&num_of_datasets);

	//loop over the number of datasets
	while( num_of_datasets-- )
	{
		int r;
		int conflicts=0;

		//read in and then print out the room name
		gets(line);	//gets strips the "\n" for us
		printf("%s\n",line);

		//initialize the detect conflicts table
		for(i=0;i<24;i++)
			roomused[i]=0;

		//find out how many rentals there are
		gets(line);
		sscanf(line,"%d",&r);

		//loop over that number of rentals (will loop until r==0)
		while(r--)
		{
			int start,end;

			//read in the start and end times
			gets(line);
			sscanf(line,"%d %d", &start, &end );

			//mark the initialize table that the room is
			//rented at these times
			for(i=start;i<end;i++)
			{
				roomused[i]++;
				if(roomused[i]>1) 
					//set a flag that conflicts exist
					conflicts=1;
			}
		}

		//if there are conflicts, print them out
		if( conflicts )
		{
			printf("CONFLICTS AT ");
			for(i=0;i<24;i++)
			{
				if( roomused[i]>1 )
					//if the room is rented out more than once for this time period...
					printf("%d-%d ", i, i+1 );	//end letter
			}
			printf("\n\n");
		}	
		else	//if there are no conflicts...
			printf("SCHEDULE OK!\n\n");
	}
}
