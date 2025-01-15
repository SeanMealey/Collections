This project is a DNA sequence database manager that:

Takes 3 command line arguments:
String myFile      // Input file with commands
int size          // Hash table size
String outputFile // Output file for sequences

Supports 4 commands:
insert <id> <length> - Add DNA sequence
remove <id> - Delete sequence
search <id> - Find sequence
print - Show all sequences

Key features:
Hash table for lookups
Memory management
DNA compression (A,C,G,T stored as 2 bits)
The main class DNAdbase reads the input file and passes it to DNAparser which processes the commands and manages the sequences.

Usage:
java DNAdbase input.txt 100 output.txt

