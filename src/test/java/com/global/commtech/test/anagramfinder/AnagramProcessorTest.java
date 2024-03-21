package com.global.commtech.test.anagramfinder;

import com.global.commtech.anagramfinder.processor.AnagramProcessor;
import com.global.commtech.anagramfinder.writer.OutputWriter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnagramProcessorTest {
    @InjectMocks
    AnagramProcessor anagramProcessor;

    @Mock
    OutputWriter outputWriter;

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/example1.txt"})
    void ShouldCreateOutputFileWhenProperInputFileIsProvided(String fileName) throws IOException {
        System.out.println(fileName);
        anagramProcessor.processAnagrams(new File(fileName));
        verify(outputWriter, times(2)).writeAnagrams(any(HashMap.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/examp1.txt"})
    void ShouldThrowExceptionWhenProperInputFileIsNotProvided(String fileName) throws IOException {
        System.out.println(fileName);
        final var exception = assertThrows(Exception.class, () -> anagramProcessor.processAnagrams(new File(fileName)));
        assertEquals("src/test/resources/examp1.txt (No such file or directory)", exception.getLocalizedMessage());
    }
}
