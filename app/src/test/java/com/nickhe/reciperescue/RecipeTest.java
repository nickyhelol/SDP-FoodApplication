package com.nickhe.reciperescue;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeTest {

    @Test
    public void getRecipeTitleTest() {

        Recipe recipe = new Recipe("Grilled Chicken", "Nick", "30mins");
        String expected = "Grilled Chicken";
        String result = recipe.getRecipeTitle();
        assertEquals(expected, result);
    }

    @Test
    public void getRecipePublisherTest() {

        Recipe recipe = new Recipe("Grilled Chicken", "Nick", "30mins");
        String expected = "Nick";
        String result = recipe.getRecipePublisher();
        assertEquals(expected, result);
    }

    @Test
    public void getTimeTest() {

        Recipe recipe = new Recipe("Grilled Chicken", "Nick", "30mins");
        String expected = "30mins";
        String result = recipe.getTime();
        assertEquals(expected, result);
    }
}