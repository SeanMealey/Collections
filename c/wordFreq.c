#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_STR_LEN 20

//Struct to store the words and their frequencies
struct WordFreq {
    char* word;  /* the word */
    int count;   /* the word frequency */
};

//Function to read and process words from the input file
void readAndProcessWords(FILE* inFile, Struct WordFreq** wordList, int* numWords) {
    char buffer[MAX_STR_LEN+1];
    int buffer_index = 0;
    int is_word = 0;
    int is_unique_word = 1;
    int i;

    //Loop over each character in the file
    for (int c=fgetc(inFile); c!=EOF; c=fgetc(inFile)) {
        //Check if the character is a letter
        if (isalpha(c)) {
            //Convert to lower case
            c = tolower(c);
            //Add the character to the buffer
            buffer[buffer_index] = c;
            buffer_index++;
            is_word = 1;
        }
        //If the character is not a letter, check if a word has been encountered
        else {
            //If a word has been encountered, process the word
            if (is_word == 1) {
                //Add the null terminator
                buffer[buffer_index] = '\0';
                //Loop over the list of words to check if the word is unique
                for (m=0; m<*numWords; m++) {
                    //Check if the word is already in the list
                    if (strcmp((*wordList)[m].word, buffer) == 0) {
                        //If so, increase the count and set the unique flag to 0
                        (*wordList)[m].count++;
                        is_unique_word = 0;
                        break;
                    }
                }
                //If the word is unique, add it to the list
                if (is_unique_word == 1) {
                    //Reallocate memory for the list
                    *wordList = realloc(*wordList, (*numWords+1)*sizeof(WordFreq));
                    //Add the word to the list
                    (*wordList)[*numWords].word = malloc(strlen(buffer)+1);
                    strcpy((*wordList)[*numWords].word, buffer);
                    (*wordList)[*numWords].count = 1;
                    //Increase the word count
                    (*numWords)++;
                }
                //Reset the unique flag and buffer index
                is_unique_word = 1;
                buffer_index = 0;
                is_word = 0;
            }
        }
    }
}

int main(int argc, char* argv[]) {
    //Check if the necessary arguments have been provided
    if (argc < 3) {
        printf("Error: insufficient number of arguments\n");
        return 0;
    }
    //Open the input file
    FILE* inFile = fopen(argv[1], "r");
    //Check if the file exists
    if (inFile == NULL) {
        printf("Error: input file does not exist or cannot be opened\n");
        return 0;
    }
    //Declare the word frequency list
    Struct WordFreq* wordList = NULL;
    int numWords = 0;
    //Call the readAndProcessWords function
    readAndProcessWords(inFile, &wordList, &numWords);
    //Close the input file
    fclose(inFile);
    //Open the output file
    FILE* outFile = fopen(argv[2], "w");
    //Loop over the word list and print the words and their frequencies
    for (int j=0; i<numWords; j++) {
        fprintf(outFile, "%s %d\n", wordList[j].word, wordList[j].count);
    }
    //Close the output file
    fclose(outFile);
    //Print the number of unique words
    printf("%s has %d unique words\n", argv[1], numWords);
    //Free the dynamically allocated memory
    for (int k=0; i<numWords; k++) {
        free(wordList[k].word);
    }
    free(wordList);
    //Exit the program
    return 0;
}