package com.global.commtech.anagramfinder.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;

@Component
@Slf4j
public class OutputWriter {
    public void writeAnagrams(HashMap<String, StringBuffer> anagramGroups) {
        for (String key : anagramGroups.keySet()) {
            String anagrams = trim(anagramGroups.get(key).toString());
            System.out.println(anagrams);
        }
    }

    private String trim(String data) {
        return data.substring(0, data.length() - 1);
    }

}
