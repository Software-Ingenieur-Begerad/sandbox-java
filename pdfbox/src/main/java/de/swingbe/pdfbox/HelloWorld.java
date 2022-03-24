package de.swingbe.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

/**
 * Creates a "Hello World" PDF using the built-in Helvetica font.
 * <p>
 * The example is taken from the PDF file format specification.
 */
public class HelloWorld implements Runnable {

    private final String fileOutput;
    private final String msg;

    public HelloWorld(String fileOutput, String msg) {
        this.fileOutput = fileOutput;
        this.msg = msg;
    }

    @Override
    public void run() {
        System.out.println("inside : " + Thread.currentThread().getName());

        if (fileOutput == null || msg == null) {
            System.err.println("usage: " + HelloWorld.class.getName() + " <output-file> <Message>");
            System.exit(1);
        }

        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = null;
        try {
            contentStream = new PDPageContentStream(document, page);
            // Define a text content stream using the selected font, moving the cursor and drawing the msg
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText(msg);
            contentStream.endText();

            // Make sure that the content stream is closed:
            contentStream.close();

            // Save the results and ensure that the document is properly closed:
            document.save(fileOutput);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
