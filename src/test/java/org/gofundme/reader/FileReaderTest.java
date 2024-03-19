package org.gofundme.reader;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
public class FileReaderTest {

    private final String FILE_CONTENTS = "Add Donor Greg $1000";

    @Test
    void should_returnCommandList_when_passingValidFile() throws IOException {
        File file = new File("test.txt");
        FileWriter writer = new FileWriter(file);
        writer.write(FILE_CONTENTS);
        writer.close();

        List<String> commands = FileReader.readCommandsFromFile(file);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, commands.size());
    }

    @Test
    void should_returnEmptyList_when_fileDoesNotExist() {
        File file = new File("test.txt", "test");

        List<String> commands = FileReader.readCommandsFromFile(file);

        Assertions.assertTrue(commands.isEmpty());
    }

}
