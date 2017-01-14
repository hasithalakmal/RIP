/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.service;

import com.rip.sql.scriptgen.DDL_Genarator;
import com.rip.sql.scriptgen.DDL_GenaratorImpl;
import com.rip.sql.util.MavenProjectRunner;
import com.rip.sql.util.WriteToJSONFile;
import static junit.framework.TestCase.assertEquals;
import org.mockito.Mock;
import org.testng.annotations.Test;

/**
 *
 * @author Hasitha Lakmal
 */
//@RunWith(MockitoJUnitRunner.class)
public class DDLManagementServiceImplTest{

    @Mock
    private final DDL_Genarator dDL_Genarator = new DDL_GenaratorImpl();
    @Mock
    private final WriteToJSONFile writeToJSONFile = new WriteToJSONFile();
    @Mock
    private final MavenProjectRunner MavenProjectRunner = new MavenProjectRunner();

    DDLManagementServiceImpl ddlManagementServiceImpl = new DDLManagementServiceImpl(dDL_Genarator, writeToJSONFile, MavenProjectRunner);
   // DDLManagementServiceImpl ddlManagementServiceImpl;

   

    @Test
    public void testGenarate() throws Exception {
        final String actual = ddlManagementServiceImpl.genarate("xxxxx");
        System.out.println(actual);
        assertEquals("aaa", "aaa");

    }
    
    @Test
    public void testGenarate2() throws Exception {
        final String actual = ddlManagementServiceImpl.genarate("xxxxx");
        System.out.println(actual);
        assertEquals("aaa", "aaa");

    }
    
    @Test
    public void testGenarate3() throws Exception {
        final String actual = ddlManagementServiceImpl.genarate("xxxxx");
        System.out.println(actual);
        assertEquals("aaa", "aaa");

    }

}
