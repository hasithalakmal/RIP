package com.rip.sql.service;

import com.rip.sql.util.WordFileGenarator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

@Service("DatabaseDocumentationService")
public class DatabaseDocumentationServiceImpl implements DatabaseDocumentationService {

    @Override
    public void createDatabaseDoc(String pimJSON) {
        FileOutputStream out = null;
        try {
            //Blank Document
            XWPFDocument document = new XWPFDocument();

            //Write the Document in file system
            out = new FileOutputStream(new File("D:\\Accademic\\4thYearProject\\Dev\\Output\\createdocument.docx"));
            document.write(out);
            out.close();
            System.out.println("createdocument.docx written successully");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordFileGenarator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseDocumentationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(WordFileGenarator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
