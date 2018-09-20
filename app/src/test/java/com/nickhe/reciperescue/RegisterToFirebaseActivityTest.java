package com.nickhe.reciperescue;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterToFirebaseActivityTest {

    @Test
    public void isEmailValid() {
        String email="dineshkrk46@gmail.com";
        RegisterToFirebaseActivity register= new RegisterToFirebaseActivity();

        assertTrue(register.isEmailValid(email));

    }

    @Test
    public void isPasswordValid() {
        String password="123456";

        RegisterToFirebaseActivity register= new RegisterToFirebaseActivity();

        assertTrue(register.isPasswordValid(password));
    }
}