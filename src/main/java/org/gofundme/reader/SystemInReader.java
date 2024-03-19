package org.gofundme.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SystemInReader {

    public static List<String> readCommandsFromSystemIn() {
        List<String> commands = new ArrayList<>();
        Scanner scanner =  new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null) break;
            commands.add(line);
        }

        return commands;
    }
}
