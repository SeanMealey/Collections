#include <sys/time.h>
#include<unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>

// Compare the time costs of function calls vs a few different system calls



//Sample function to call later. Simple returns an integer.
int foo(){
    int num;
    num = 7;
    return num;
}


//Main function where all other code is executed
int main(){
    //Create two structures to store the time when using gettimeofday(). Defined as below:
    // struct timeval {
    //  long tv_sec;    /* seconds */
    //  long tv_usec;   /* microseconds */
    // };
    struct timeval start;	
	struct timeval end;	
	

    //Function call
    unsigned long fCall;	
	gettimeofday(&start, 0);	
    for(int i=0; i<10000;i++){
        foo();
    }
	gettimeofday(&end, 0);		
	fCall = (end.tv_usec) - ( start.tv_usec);

    //getuid()
    unsigned long getUID;	
    gettimeofday(&start, 0);	
    for(int i=0; i<10000;i++){
        getuid();
    }
	gettimeofday(&end, 0);		
	getUID = (end.tv_usec) - ( start.tv_usec);

    //getppid()
    unsigned long getPPID;	
    gettimeofday(&start, 0);	
    for(int i=0; i<10000;i++){
        getppid();
    }
	gettimeofday(&end, 0);		
	getPPID = (end.tv_usec) - ( start.tv_usec);

    //getcwd()
    unsigned long getCWD;
    int size;
    size = 10;
    char buffer[size];
    gettimeofday(&start, 0);	
    for(int i=0; i<10000;i++){
        getcwd(buffer, size);
    }
	gettimeofday(&end, 0);		
	getCWD = (end.tv_usec) - ( start.tv_usec);




    //exception handling and set up variables for read/write
    unsigned long writeTime;
    int fd;
    char* fileName = "text.txt";
    fd = open(fileName, O_RDWR);
    if(fd == -1){
        printf("\nError Opening File!!\n");
        exit(1);
    }
    else{
        printf("\nFile %s opened sucessfully!\n", fileName);
    }
    gettimeofday(&start, 0);	

    //write()
    size = sizeof(fileName);
    for(int i=0; i<10000;i++){
        write(fd, fileName, size);
    }
	gettimeofday(&end, 0);		
	writeTime = (end.tv_usec) - ( start.tv_usec);

    //read()
    unsigned long readTime;	
    char* outputBuff[1024];
    gettimeofday(&start, 0);
    size = sizeof(outputBuff);	
    for(int i=0; i<10000;i++){
        read(fd, outputBuff, size);
    }
	gettimeofday(&end, 0);		
	readTime = (end.tv_usec) - ( start.tv_usec);



    //Print output to terminal in correct format
    printf("COSTS FOR 10000 CALLS OF EACH OF THE FOLLOWING\n");
    printf("Function Call: %lu microseconds\n", fCall);
    printf("getuid(): %lu microseconds\n", getUID);
    printf("getppid(): %lu microseconds\n", getPPID);
    printf("getcwd(): %lu microseconds\n", getCWD);
    printf("write(): %lu microseconds\n", writeTime);
    printf("read(): %lu microseconds\n", readTime);

    return 0;
}
