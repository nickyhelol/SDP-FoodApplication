package com.nickhe.reciperescue;

import android.app.Activity;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;

import junit.extensions.ActiveTestSuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RecipeListAdapterFilterableTest {

    RecipeListAdapterFilterable recipeListAdapterFilterable;
    Activity activity;
    Recipe r1;
    Recipe r2;

    public RecipeListAdapterFilterableTest() {
        if (Looper.myLooper()==null) {
            Looper.prepare();
        }
    }

    /**
     * Setup to create the recipes and initialise the objects that are going to be tested, in this
     * case the recipeListAdapterFilterable object
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        r1 = new Recipe(
                0,
                "Chicken And Lemon Kebabs",
                new String[]{
                        "460 grams chicken thigh fillets",
                        "1 lemon",
                        "3 tablespoons olive oil",
                        "1 clove garlic",
                        "1 1/2 red onions",
                        "1 yellow pepper",
                        "15 grams coriander leaves",
                        "4 tortilla wraps",
                        "4 lettuce leaves",
                        "115 grams soured cream "
                },
                "Nick He",
                "25 Mins",
                "640 Calories",
                new String[]{
                        "Put the chicken, lemon zest, all but 1 tbsp of the lemon juice, half the olive oil and the garlic in a bowl. Season with freshly ground black pepper and mix well.",
                        "Heat a non-stick griddle pan until very hot. Thread the chicken, onion wedges and pepper on to pre-soaked wooden or metal skewers. Cook for 10 minutes, turning once, until lightly charred and cooked through with no pink remaining.",
                        "Meanwhile, make the lemon dressing: put the remaining lemon juice, oil and half the coriander in a small bowl and whisk together. Set aside. Heat the tortilla wraps all together in the microwave for 1 minute (or place under a hot grill for 30 seconds each side). ",
                        "Remove the chicken and vegetables from the skewers and pile on to the tortilla wraps, along with the lettuce, soured cream and chive dip and the remaining coriander. Serve with the lemon dressing."
                },
                null
        );

        r2 = new Recipe(
                1,
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

        List<Recipe> testRecipes = new ArrayList<>();
        testRecipes.add(r1);
        testRecipes.add(r2);


        activity = new Activity();
        recipeListAdapterFilterable = new RecipeListAdapterFilterable(activity, testRecipes);
    }

    /**
     * Tear down to reset the objects.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        recipeListAdapterFilterable = null;
        activity = null;
    }

    /**
     * Tests filtering for ingredient "tortilla"
     * Should only show r2
     */
    @Test
    public void getRecipeIngredientsFilter() {
        assertNotNull(recipeListAdapterFilterable.getRecipeIngredientsFilter());
        recipeListAdapterFilterable.getRecipeIngredientsFilter().filter("tortilla");
        assertEquals(r2, recipeListAdapterFilterable.getFilteredRecipes().get(1));
    }

    /**
     * Tests filtering for name "Kebabs"
     * Should only result in r1
     */
    @Test
    public void getNameFilter() {
        assertNotNull(recipeListAdapterFilterable.getNameFilter());
        recipeListAdapterFilterable.getNameFilter().filter("Kebabs");
        assertEquals(r1, recipeListAdapterFilterable.getFilteredRecipes().get(0));
    }

    /**
     * Tests item count of the resulting item, should result in 2 since there are only 2 recipes
     */
    @Test
    public void getItemCount() {
        assertEquals(2, recipeListAdapterFilterable.getItemCount());
    }

    /**
     * Refer to getNameFilter test since they should result in the same object
     */
    @Test
    public void getFilter() {
        assertNotNull(recipeListAdapterFilterable.getFilter());
        recipeListAdapterFilterable.getFilter().filter("Kebabs");
        assertEquals(r1, recipeListAdapterFilterable.getFilteredRecipes().get(0));
    }
}