# Anagram Finder
A simple command line utility for finding anagrams in a specified file

## Software required to run this
* Java 17

## Building and Running the tests
```
./gradlew clean build
```

## Running the program
```
./gradlew bootRun --args="example2.txt" 
```
where example2.txt is the text file that we want to search for anagrams

## Implementation

The application reads all words with same size into memory using a hashmap and group them into anagrams. All anagrams that are grouped together, will be written to system output. In memory data will be cleared after each write to the output file.

## Given More Time

If given more time, I would be adding more tests to the application to make sure it is running fine.