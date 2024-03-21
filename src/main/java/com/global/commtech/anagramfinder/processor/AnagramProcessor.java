package com.global.commtech.anagramfinder.processor;

import com.global.commtech.anagramfinder.writer.OutputWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

@Component
@Slf4j
public class AnagramProcessor {
    public static final String DELIMITER = ",";
    private final HashMap<String, StringBuffer> anagramGroups;

    OutputWriter anagramsWriter;

    public AnagramProcessor(OutputWriter anagramsWriter) {
        this.anagramsWriter = anagramsWriter;
        anagramGroups = new HashMap<>();
    }

    public void processAnagrams(final File inputFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            processInputFile(reader);
        } catch (IOException exception) {
            log.error("Error Message:{}", exception.getMessage());
            throw exception;
        }
    }

    private void processInputFile(BufferedReader reader) throws IOException {
        String inputWord = reader.readLine();
        while (!endOfFile(inputWord)) {
            int wordSize = inputWord.length();
            while (!endOfFile(inputWord) && validateWord(inputWord, wordSize)) {
                String hashKey = createHashKey(inputWord);
                StringBuffer value = anagramGroups.getOrDefault(hashKey, new StringBuffer());
                value.append(inputWord).append(DELIMITER);
                anagramGroups.put(hashKey, value);
                inputWord = reader.readLine();
            }
            anagramsWriter.writeAnagrams(anagramGroups);
            anagramGroups.clear();
        }
    }

    private boolean validateWord(String inputWord, int wordSize) {
        return inputWord.length() == wordSize;
    }

    private boolean endOfFile(String inputLine) {
        return inputLine == null;
    }


    private String createHashKey(String inputLine) {
        char[] chars = inputLine.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
