package org.gofundme.reader;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
public class FileReaderTest {

    public static final String FILE_NAME = "unit_test.txt";
    private final String FILE_CONTENTS = "Add Donor Greg $1000";

    @AfterEach
    public void cleanUp() {
        File file = new File(FILE_NAME);
        file.delete();
    }

    @Test
    void should_returnCommandList_when_passingValidFile() throws IOException {
        File file = new File(FILE_NAME);
        FileWriter writer = new FileWriter(file);
        writer.write(FILE_CONTENTS);
        writer.close();

        List<String> commands = FileReader.readCommandsFromFile(file);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, commands.size());
    }

    @Test
    void should_returnEmptyList_when_fileDoesNotExist() {
        File file = new File(FILE_NAME, "test");

        List<String> commands = FileReader.readCommandsFromFile(file);

        Assertions.assertTrue(commands.isEmpty());
    }

}
