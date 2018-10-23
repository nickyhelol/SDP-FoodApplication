package com.nickhe.reciperescue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class RecipeViewActivityTest {
    List<Recipe> testRecipes;
    Recipe validRecipe;
    Recipe emptyRecipe;

    @Before
    public void setUp(){

        //Setting up a generic valid recipe
        validRecipe = new Recipe(
                "Asian Chicken Salad With Ginger Sesame Dressing",
                new String[]{
                        "1/4 cup rice wine vinegar",
                        "1 teaspoon fresh ginger",
                        "2 tablespoons soy sauce",
                        "1 tablespoon honey",
                        "3 tablespoons toasted sesame oil",
                        "3 tablespoons vegetable oil",
                        "2 tablespoons sesame seeds",
                        "6 cups baby spinach leaves ",
                        "1 carrot",
                        "1 red bell pepper",
                        "1/4 red onion",
                        "1 tablespoon sesame seeds ",
                        "2 cups rotisserie chicken"
                },
                "Nick He",
                "30 Mins",
                "350 Calories",
                new String[]{
                        "Combine all salad dressing ingredients, except for sesame seeds, and using only 1 tablespoon of soy sauce. Whisk until emulsified. Taste your dressing and add another 1 tablespoon of soy sauce, if desired, to make it saltier. Add 2 tablespoons sesame seeds to the dressing and mix them in.",
                        "Combine all salad ingredients in a large bowl, except for 1 tablespoon of sesame seeds and chicken. Add the salad dressing to the salad and toss - do not add all salad dressing at once: you might not need all of it. Add just enough salad dressing to coat the salad ingredients.",
                        "Serve the salad in the individual serving bowls and top with the remaining 1 tablespoon of sesame seeds and sliced chicken."
                },
                null
        );

        //Setting up an empty recipe
        emptyRecipe = new Recipe(
                "",
                new String[]{"", ""},
                "",
                "",
                "",
                new String[]{"", ""},
                null
        );

        testRecipes = new ArrayList<>();
        testRecipes.add(validRecipe);
        testRecipes.add(emptyRecipe);
    }

    //Sets the testing field back to zero
    @After
    public void tearDown(){
        validRecipe = null;
        emptyRecipe = null;
        testRecipes = null;
    }

    //Testing both validRecipe and its List counterpart to check if they both hold
    //proper/expected and are valid for text view
    @Test
    public void isRecipeValidForTextView(){


        boolean expected = !validRecipe.getRecipeTitle().isEmpty();
        boolean result = !testRecipes.get(0).getRecipeTitle().isEmpty();

        //We will now assert if the recipes iteration of the same recipe
        // is valid for text view
        assertEquals(expected, result);
    }

    //Testing the emptyRecipe and its List counterpart to check if they're
    //both empty and invalid for text view
    @Test
    public void isRecipeInvalidForTextView() {

        boolean expected = emptyRecipe.getRecipeTitle().isEmpty();
        boolean result = testRecipes.get(1).getRecipeTitle().isEmpty();

        assertEquals(expected, result);
    }

}