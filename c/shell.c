#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <signal.h>

int main(){
    void signalHandler(int sigNum);
    char inputString[100];
    int concurrencyFlag = 0;

    
    //Will call signalHandler when ^C is entered
    signal(SIGINT, signalHandler);

    //Begin shell instance
    while(1){
        printf("%s", "$ ");
        fgets(inputString, sizeof(inputString), stdin);
        
        // Add exit command check
        if (strncmp(inputString, "exit", 4) == 0) {
            exit(0);
        }

        char* commands[3] = {NULL};
        char* token;
        int i = 0;

        char *rest = inputString;
        while ((token = strtok_r(rest, "|", &rest)) != NULL && i < 3) {
            commands[i] = strdup(token);
            i++;
        }

        // Create pipes
        int fd[2];
        int prev_fd[2] = {0, 0};

        for (int j = 0; j < i; j++) {
            char* args[10];
            char* token_cmd = strtok(commands[j], " \n");
            int k = 0;
            while (token_cmd != NULL) {
                args[k] = token_cmd;
                token_cmd = strtok(NULL, " \n");
                k++;
            }
            args[k] = NULL;

            if (pipe(fd) == -1) {
                perror("Pipe");
                exit(EXIT_FAILURE);
            }

            int pid = fork();
            if (pid == -1) {
                perror("Fork");
                exit(EXIT_FAILURE);
            } else if (pid == 0) {
                if (j > 0) {
                    dup2(prev_fd[0], STDIN_FILENO);
                    close(prev_fd[0]);
                    close(prev_fd[1]);
                }
                if (j < i - 1) {
                    dup2(fd[1], STDOUT_FILENO);
                    close(fd[0]);
                    close(fd[1]);
                }

                execvp(args[0], args);
                perror("Exec");
                exit(EXIT_FAILURE);
            } else {
                // Parent process
                if (j > 0) {
                    close(prev_fd[0]);
                    close(prev_fd[1]);
                }
                if (j < i - 1) {
                    close(fd[1]);
                }
                prev_fd[0] = fd[0];
                prev_fd[1] = fd[1];
                //Parent process waits for child to terminate
                if(concurrencyFlag==0){
                    wait(NULL);
                }
                concurrencyFlag = 0;
            }
        }

    }
    return 0;
}

// Signal handling function
void signalHandler(int sigNum){
    signal(SIGINT, signalHandler);
    printf("Type exit to quit\n");
}
