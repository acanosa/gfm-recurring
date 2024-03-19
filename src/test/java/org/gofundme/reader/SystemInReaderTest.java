package org.gofundme.reader;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SystemInReaderTest {

    private final String STDIN_CONTENT = "Add Donor Greg $1000";

    @Test
    void should_returnCommandList_when_passingCommands() {
        System.setIn(new ByteArrayInputStream(STDIN_CONTENT.getBytes()));

        List<String> commands = SystemInReader.readCommandsFromSystemIn();

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, commands.size());
    }

}
