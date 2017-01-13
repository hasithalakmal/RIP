/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *
 * @author Hasitha Lakmal
 */
public class WordFileGenarator {

    public static void main(String[] args) throws IOException {
        FileOutputStream out = null;
        try {
            //Blank Document
            XWPFDocument document = new XWPFDocument();

            //create Heading
            XWPFParagraph heading = document.createParagraph();
            XWPFRun paragraphOneRunOne = heading.createRun();
            paragraphOneRunOne.setBold(true);
            paragraphOneRunOne.setItalic(true);
            paragraphOneRunOne.setFontSize(20);
            paragraphOneRunOne.setText("Font Style");
            paragraphOneRunOne.addBreak();

            //create Paragraph
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("This is sapmple Text");

            //create table
            XWPFTable table = document.createTable();

            //create first row
            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("col one, row one");
            tableRowOne.addNewTableCell().setText("col two, row one");
            tableRowOne.addNewTableCell().setText("col three, row one");

            //create second row
            XWPFTableRow tableRowTwo = table.createRow();
            tableRowTwo.getCell(0).setText("col one, row two");
            tableRowTwo.getCell(1).setText("col two, row two");
            tableRowTwo.getCell(2).setText("col three, row two");

            //create third row
            XWPFTableRow tableRowThree = table.createRow();
            tableRowThree.getCell(0).setText("col one, row three");
            tableRowThree.getCell(1).setText("col two, row three");
            tableRowThree.getCell(2).setText("col three, row three");

            //Write the Document in file system
            out = new FileOutputStream(new File("D:\\Accademic\\4thYearProject\\Dev\\Output\\createdocument.docx"));
            document.write(out);
            out.close();
            System.out.println("createdocument.docx written successully");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordFileGenarator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(WordFileGenarator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
