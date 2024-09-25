package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;

public class ReceiptTable {
    private BufferedWriter writer;
    private int[] colWidths;
    private int cellHeight;
    private int yPosition;
    private int xPosition;
    private int colPosition = 0;
    private int xInitialPosition;
    private String font;
    private float fontSize;
    private Color fontColor;

    public ReceiptTable(String filePath) throws IOException {
        writer = new BufferedWriter(new FileWriter(filePath));
    }

    public void setTable(int[] colWidths, int cellHeight, int xPosition, int yPosition) {
        this.colWidths = colWidths;
        this.cellHeight = cellHeight;
        this.yPosition = yPosition;
        this.xPosition = xPosition;
        xInitialPosition = xPosition;
    }

    public void setTableFont(String font, float fontSize, Color fontColor) {
        this.font = font;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
    }

    public void addCell(String text, Color fillColor) throws IOException {
        String cellText = String.format("%-" + colWidths[colPosition] + "s", text);
        writer.write(cellText);
        xPosition += colWidths[colPosition];
        colPosition++;

        if (colPosition == colWidths.length) {
            colPosition = 0;
            xPosition = xInitialPosition;
            yPosition -= cellHeight;
            writer.newLine();
        }
    }

    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
