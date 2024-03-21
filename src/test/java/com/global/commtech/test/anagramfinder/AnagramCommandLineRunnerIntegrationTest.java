package com.global.commtech.test.anagramfinder;

import static org.assertj.core.api.Assertions.assertThat;

import com.global.commtech.anagramfinder.processor.AnagramProcessor;
import com.global.commtech.anagramfinder.runner.AnagramCommandLineRunner;
import com.global.commtech.anagramfinder.writer.OutputWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest(args = {"src/test/resources/example1.txt"}, classes = {AnagramCommandLineRunner.class, AnagramProcessor.class, OutputWriter.class})
@ExtendWith(OutputCaptureExtension.class)
class AnagramCommandLineRunnerIntegrationTest {

    @Autowired
    AnagramCommandLineRunner anagramCommandLineRunner;

    @Autowired
    ApplicationArguments args;

    @Test
    void shouldFindAnagrams(final CapturedOutput capturedOutput) throws Exception {
        anagramCommandLineRunner.run(args.getSourceArgs()[0]);
        assertThat(capturedOutput.getOut()).contains("abc,bac,cba");
        assertThat(capturedOutput.getOut()).contains("fun,unf");
        assertThat(capturedOutput.getOut()).contains("hello");
    }


}