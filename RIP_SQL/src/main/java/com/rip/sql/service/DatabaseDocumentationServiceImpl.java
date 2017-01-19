package com.rip.sql.service;

import com.rip.sql.util.WordFileGenarator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DatabaseDocumentationService")
public class DatabaseDocumentationServiceImpl implements DatabaseDocumentationService {

    @Autowired 
    WordFileGenarator wordFileGenarator;
    
    @Override
    public void createDatabaseDoc(String pimJSON) {
        
       wordFileGenarator.createWordFile(pimJSON);
    }

}
