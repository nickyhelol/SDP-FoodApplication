package com.nickhe.reciperescue;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainLoginActivityTest {

    @Test
    public void isEmailValid() {
        String email= "dineshkrk46@gmail.com";

        MainLoginActivity loginActivity= new MainLoginActivity();
        assertTrue( loginActivity.isEmailValid(email));

    }

    @Test
    public void isPasswordValid() {
        String password="123456";

        MainLoginActivity loginActivity= new MainLoginActivity();

        assertTrue(loginActivity.isPasswordValid(password));
    }
}