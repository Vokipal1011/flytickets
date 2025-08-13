package ru.flytickets;

import java.io.*;

public class WriteInFile {

    private static String defaultPath = System.getProperty("user.dir");
    private static File directory = new File(defaultPath, "result.txt");


        public static void writeInFile(StringBuilder string, boolean append) throws IOException {

            BufferedWriter writerInFile = new BufferedWriter(new FileWriter(directory, append));

            writerInFile.write(String.valueOf(string));

            writerInFile.close();
        }
    }

