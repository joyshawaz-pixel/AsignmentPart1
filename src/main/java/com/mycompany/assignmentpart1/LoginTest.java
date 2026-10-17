/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;

/**
 *
 * @author Student
 */
 import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class LoginTest {
   
    @Test
    public void testCheckUserNameValid() {
        Main login = new Main();
        assertTrue(login.checkUserName("kyl_1_"), "Username should be valid");
    }

    @Test
    public void testCheckUserNameInvalid() {
        Main login = new Main();
        assertFalse(login.checkUserName("kyle!!!!!"), "Username should be invalid");
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        Main login = new Main();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"), "Password should meet complexity requirements");
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        Main login = new Main();
        assertFalse(login.checkPasswordComplexity("password"), "Password should fail complexity requirements");
    }

    @Test
    public void testRegisterUserSuccess() {
        Main login = new Main();
        String result = login.registerUser("kyl_1_", "Ch&&sec@ke99!", "+2782123456", "Kyle", "Smith");
        assertTrue(result.contains("User has been registered successfully."));
    }

    @Test
    public void testLoginSuccess() {
        Main login = new Main();
        login.registerUser("kyl_1_", "Ch&&sec@ke99!", "+2782123456", "Kyle", "Smith");
        boolean loginResult = login.loginUser("kyl_1_", "Ch&&sec@ke99!");
        assertTrue(loginResult, "Login should be successful");
    }

    @Test
    public void testReturnLoginStatus() {
        Main login = new Main();
        login.registerUser("kyl_1_", "Ch&&sec@ke99!", "+2782123456", "Kyle", "Smith");
        String status = login.returnLoginStatus(true);
        assertEquals("Welcome Kyle, Smith it is great to see you again.", status);
    }
}
    
}
