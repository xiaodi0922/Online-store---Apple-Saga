package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;

public class ReceiptText {

    private BufferedWriter writer;

    public ReceiptText(String filePath) throws IOException {
        writer = new BufferedWriter(new FileWriter(filePath));
    }

    public void addSingleLineText(String text, int xPosition, int yPosition, Color color) throws IOException {
        writer.write(text);
        writer.newLine();
    }

    public void addMultipleLineText(String[] textArray, float leading, int xPosition, int yPosition, Color color) throws IOException {
        for (String text : textArray) {
            writer.write(text);
            writer.newLine();
        }
    }

    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
