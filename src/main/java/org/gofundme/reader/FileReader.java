package org.gofundme.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<String> readCommandsFromFile(File file) {
        try {
            List<String> commands = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            String line;
            while((line = reader.readLine()) != null) {
                commands.add(line);
            }

            return commands;
        } catch(FileNotFoundException ex) {
            System.out.println("The file does not exist. Check that it is in the same level as the jar application");
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();

        }
    }
}
