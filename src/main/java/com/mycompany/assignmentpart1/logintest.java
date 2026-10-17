/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Student
 */
public class logintest {

    @Test
    public void testCheckUserNameValid() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"), "Username should be valid");
    }

    @Test
    public void testCheckUserNameInvalid() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!"), "Username should be invalid");
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"), "Password should meet complexity requirements");
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"), "Password should fail complexity requirements");
    }

    @Test
    public void testRegisterUserSuccess() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+2782123456", "Kyle", "Smith");
        assertTrue(result.contains("User has been registered successfully."));
    }

    @Test
    public void testLoginSuccess() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+2782123456", "Kyle", "Smith");
        boolean loginResult = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(loginResult, "Login should be successful");
    }

    @Test
    public void testReturnLoginStatus() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+2782123456", "Kyle", "Smith");
        String status = login.returnLoginStatus(true);
        assertEquals("Welcome Kyle, Smith it is great to see you again.", status);
    }
}


    

