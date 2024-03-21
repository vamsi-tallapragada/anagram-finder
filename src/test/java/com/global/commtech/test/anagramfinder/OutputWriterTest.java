package com.global.commtech.test.anagramfinder;

import com.global.commtech.anagramfinder.writer.OutputWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OutputWriterTest {

    private HashMap<String,StringBuffer> anagrams;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @InjectMocks
    OutputWriter outputWriter;

    @BeforeEach
    public void setUp(){
        anagrams=new HashMap<>();
        anagrams.put("abc",new StringBuffer("bac,cab,abc,"));
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void ShouldWriteDataToConsoleWhenAnagramDataIsProvided() {
        outputWriter.writeAnagrams(anagrams);
        assertEquals("bac,cab,abc\n", outContent.toString());
    }

}
