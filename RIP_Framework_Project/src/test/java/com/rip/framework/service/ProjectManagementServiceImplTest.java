/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.service;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.testng.annotations.Test;

/**
 *
 * @author Hasitha Lakmal
 */
public class ProjectManagementServiceImplTest extends TestCase {
    
    public ProjectManagementServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createProject method, of class ProjectManagementServiceImpl.
     */
    @Test
    public void testCreateProject() {
        System.out.println("createProject");
        String project = "";
        ProjectManagementServiceImpl instance = new ProjectManagementServiceImpl();
        String expResult = "";
        String result = instance.createProject(project);
        assertEquals("aaaaa", "aaaaa");
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProject method, of class ProjectManagementServiceImpl.
     */
    @Test
    public void testDeleteProject() {
        System.out.println("deleteProject");
        String projectName = "";
        String version = "";
        ProjectManagementServiceImpl instance = new ProjectManagementServiceImpl();
        String expResult = "";
        String result = instance.deleteProject(projectName, version);
        assertEquals("aaaaa", "aaaaa");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of selectProject method, of class ProjectManagementServiceImpl.
     */
    @Test
    public void testSelectProject() {
        System.out.println("selectProject");
        String projectName = "";
        String version = "";
        ProjectManagementServiceImpl instance = new ProjectManagementServiceImpl();
        String expResult = "";
        String result = instance.selectProject(projectName, version);
        assertEquals("aaaaa", "aaaaa");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of selectProjectByName method, of class ProjectManagementServiceImpl.
     */
    public void testSelectProjectByName() {
        System.out.println("selectProjectByName");
        String projectName = "";
        ProjectManagementServiceImpl instance = new ProjectManagementServiceImpl();
        ArrayList expResult = null;
        ArrayList result = instance.selectProjectByName(projectName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectProjectByUser method, of class ProjectManagementServiceImpl.
     */
    public void testSelectProjectByUser() {
        System.out.println("selectProjectByUser");
        String userName = "";
        ProjectManagementServiceImpl instance = new ProjectManagementServiceImpl();
        ArrayList expResult = null;
        ArrayList result = instance.selectProjectByUser(userName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectAllProjects method, of class ProjectManagementServiceImpl.
     */
    public void testSelectAllProjects() {
        System.out.println("selectAllProjects");
        ProjectManagementServiceImpl instance = new ProjectManagementServiceImpl();
        ArrayList expResult = null;
        ArrayList result = instance.selectAllProjects();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
