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

    private void processInputFile(final BufferedReader reader) throws IOException {
        String currentLine = reader.readLine();
        while (!endOfFile(currentLine)) {
            int wordSize = currentLine.length();
            while (!endOfFile(currentLine) && validateWord(currentLine, wordSize)) {
                String hashKey = createHashKey(currentLine);
                StringBuffer value = anagramGroups.getOrDefault(hashKey, new StringBuffer());
                value.append(currentLine).append(DELIMITER);
                anagramGroups.put(hashKey, value);
                currentLine = reader.readLine();
            }
            anagramsWriter.writeAnagrams(anagramGroups);
            anagramGroups.clear();
        }
    }

    private boolean validateWord(final String inputWord, final int wordSize) {
        return inputWord.length() == wordSize;
    }

    private boolean endOfFile(final String inputLine) {
        return inputLine == null;
    }


    private String createHashKey(final String inputLine) {
        char[] chars = inputLine.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
