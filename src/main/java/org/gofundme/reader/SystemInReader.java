package org.gofundme.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SystemInReader {

    public static List<String> readCommandsFromSystemIn() {
        List<String> commands = new ArrayList<>();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String line;
        try {
            while( (line = input.readLine()) != null ) {
                commands.add(line);
            }
            return commands;
        } catch(IOException ex) {
            System.out.println("There was an error reading the command: " + ex.getMessage());
            return new ArrayList<>();
        }

    }
}
