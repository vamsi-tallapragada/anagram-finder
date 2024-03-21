package com.global.commtech.anagramfinder.runner;

import java.io.File;
import java.io.IOException;

import com.global.commtech.anagramfinder.processor.AnagramProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AnagramCommandLineRunner implements CommandLineRunner {

    AnagramProcessor anagramProcessor;

    public AnagramCommandLineRunner(AnagramProcessor anagramProcessor) {
        this.anagramProcessor = anagramProcessor;
    }

    @Override
    public void run(final String... args) throws IOException {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");
        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");
        anagramProcessor.processAnagrams(file);
    }
}
