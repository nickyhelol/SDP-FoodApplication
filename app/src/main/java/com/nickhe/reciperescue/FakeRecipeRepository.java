package com.nickhe.reciperescue;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class FakeRecipeRepository {

    //Singleton fake repository
    private static FakeRecipeRepository fakeRecipeRepository;
    private List<Recipe> fakeRepo;
    Activity context;
    int[] images = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4
            , R.drawable.food5, R.drawable.food6, R.drawable.food7, R.drawable.food8};

    public FakeRecipeRepository(Activity context)
    {
        fakeRepo = new ArrayList<>();
        this.context = context;
        initializeRepo();
    }

    /**
     * Return and initialize a FakeRecipeRepository and make sure
     * that there will be only one instance of FakeRecipeRepository.
     *
     * @param context
     * @return
     */
    public static synchronized FakeRecipeRepository getFakeRecipeRepository(Activity context)
    {
        if (fakeRecipeRepository == null) {
            fakeRecipeRepository = new FakeRecipeRepository(context);
        }

        return fakeRecipeRepository;
    }

    /**
     * Update the FakeRecipeRepository
     */
    public void initializeRepo() {
        Uri food1 = Uri.parse("android.resource://com.nickhe.reciperescue/drawable/"+images[0]);
        Uri food2 = Uri.parse("android.resource://com.nickhe.reciperescue/drawable/"+images[1]);
        Uri food3 = Uri.parse("android.resource://com.nickhe.reciperescue/drawable/"+images[2]);
        Uri food4 = Uri.parse("android.resource://com.nickhe.reciperescue/drawable/"+images[3]);
        Uri food5 = Uri.parse("android.resource://com.nickhe.reciperescue/drawable/"+images[4]);
        Uri food6 = Uri.parse("android.resource://com.nickhe.reciperescue/drawable/"+images[5]);
        Uri food7 = Uri.parse("android.resource://com.nickhe.reciperescue/drawable/"+images[6]);
        Uri food8 = Uri.parse("android.resource://com.nickhe.reciperescue/drawable/"+images[7]);

        Recipe r1 = new Recipe(
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
                "25",
                "640",
                new String[]{
                        "Put the chicken, lemon zest, all but 1 tbsp of the lemon juice, half the olive oil and the garlic in a bowl. Season with freshly ground black pepper and mix well.",
                        "Heat a non-stick griddle pan until very hot. Thread the chicken, onion wedges and pepper on to pre-soaked wooden or metal skewers. Cook for 10 minutes, turning once, until lightly charred and cooked through with no pink remaining.",
                        "Meanwhile, make the lemon dressing: put the remaining lemon juice, oil and half the coriander in a small bowl and whisk together. Set aside. Heat the tortilla wraps all together in the microwave for 1 minute (or place under a hot grill for 30 seconds each side). ",
                        "Remove the chicken and vegetables from the skewers and pile on to the tortilla wraps, along with the lettuce, soured cream and chive dip and the remaining coriander. Serve with the lemon dressing."
                },
                food1
        );

        Recipe r2 = new Recipe(
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
                "30",
                "350",
                new String[]{
                        "Combine all salad dressing ingredients, except for sesame seeds, and using only 1 tablespoon of soy sauce. Whisk until emulsified. Taste your dressing and add another 1 tablespoon of soy sauce, if desired, to make it saltier. Add 2 tablespoons sesame seeds to the dressing and mix them in.",
                        "Combine all salad ingredients in a large bowl, except for 1 tablespoon of sesame seeds and chicken. Add the salad dressing to the salad and toss - do not add all salad dressing at once: you might not need all of it. Add just enough salad dressing to coat the salad ingredients.",
                        "Serve the salad in the individual serving bowls and top with the remaining 1 tablespoon of sesame seeds and sliced chicken."
                },
                food2
        );

        Recipe r3 = new Recipe(
                "Grilled Salmon",
                new String[]{
                        "salt",
                        "pepper",
                        "butter",
                        "1/4 pound butter",
                        "2 teaspoons lemon juice",
                        "1 teaspoon chopped parsley",
                        "1 pinch black pepper",
                        "1 pinch white pepper",
                        "1/2 teaspoon salt"},
                "Nick He",
                "25",
                "320",
                new String[]{
                        "Melt butter in a non stick skillet until the butter is smokey hot",
                        "Season salmon filet lightly with salt and pepper on both sides.  Place salmon filet into skillet and cook for 3 to 4 minutes per side",
                        " Remove from skillet and spoon lemon pepper butter over filet.  Serve with a lemon wedge."
                },
                food3
        );

        Recipe r4 = new Recipe(
                "Spicy Lime Grilled Chicken",
                new String[]{
                        "6 boneless, skinless chicken breast halves",
                        "2 cloves garlic ",
                        "2 tablespoons extra-virgin olive oil",
                        "2 tablespoons lime juice",
                        "2 teaspoons onion powder",
                        "2 teaspoons lime zest",
                        "1 teaspoon oregano",
                        "1 teaspoon red pepper flakes",
                        "kosher salt",
                        "freshly ground pepper"
                },
                "Nick He",
                "45",
                "50",
                new String[]{
                        "Generously season both sides of chicken with salt and pepper and transfer to a large, re-sealable bag.",
                        "In a small bowl, whisk together olive oil, lime juice, garlic, onion powder, lime zest, oregano and red pepper flakes.",
                        "Pour marinade into plastic bag, seal and move bag around so that chicken is completely coated.",
                        "Refrigerate chicken for 1-4 hours.",
                        "When ready, preheat grill (or griddle) to medium-high and cook chicken breasts, flipping in the middle, until no longer pink. 6-8 minutes total."
                },
                food4
        );

        Recipe r5 = new Recipe(
                "Lightened Up Curry Egg Salad",
                new String[]{
                        "8 large eggs",
                        "3 tablespoons chopped onion",
                        "1 red pepper",
                        "1 teaspoon salt",
                        "2 teaspoons curry powder",
                        "1/4 cup light mayo",
                        "1 mango"
                },
                "Nick He",
                "35",
                "240",
                new String[]{
                        "Hard boil the eggs by dropping them carefully into a pot of boiling water. Boil for 8 minutes, covered, and allow to cool. Peel the shells off and cut the eggs in half to remove the hardened yolk from the center. Finely chop the whites and set aside.",
                        "Chop the red pepper and the onion as finely as you like – I prefer to chop them pretty small. Combine egg whites, vegetables, salt, curry, and mayo in a bowl. Stir until well combined. Season with pepper to taste.",
                        "Stir the mango in with the egg salad just before serving."
                },
                food5
        );

        Recipe r6 = new Recipe(
                "Barbecue Chicken Pizza",
                new String[]{
                        "1 pizza crust",
                        "1/2 cup barbecue sauce",
                        "1 cup chicken",
                        "1/2 cup red onion",
                        "1 orange bell pepper",
                        "1 3/4 cups shredded cheese",
                        "chopped cilantro"
                },
                "Nick He",
                "30",
                "630",
                new String[]{
                        "Preheat oven to 475 degrees F. Place pizza crust on foil-lined baking sheet.",
                        "Spread barbecue sauce on pizza crust. Add shredded chicken, onion, and bell peppers on top of the barbecue sauce. Top with cheese.",
                        "Bake 12 to 15 minutes or until crust is deep golden brown. Cut into squares; garnish with cilantro."
                },
                food6
        );

        Recipe r7 = new Recipe(
                "Thai Pineapple Fried Rice",
                new String[]{
                        "1 cup quinoa",
                        "2 tablespoons reduced sodium soy sauce",
                        "2 tablespoons oyster sauce",
                        "1 tablespoon fish sauce",
                        "2 teaspoons sugar",
                        "1 teaspoon chili paste with garlic",
                        "20 ounces coconut ",
                        "2 cups pineapple",
                        "1/4 cup thai basil leaves"
                },
                "Nick He",
                "15",
                "480",
                new String[]{
                        "In a large saucepan of 2 cups water, cook quinoa according to package instructions; set aside.",
                        "In a small bowl, whisk together soy sauce, oyster sauce, fish sauce, sugar and red chili paste; set aside.",
                        "Cook Green Giant™ Sautés Thai Coconut according to package instructions.",
                        "Stir in pineapple, basil, quinoa and soy sauce mixture. Cook, stirring constantly, until heated through, about 2 minutes.",
                        "Serve immediately, garnished with peanuts, if desired."
                },
                food7
        );

        Recipe r8 = new Recipe(
                "Very Veggie Fried Rice",
                new String[]{
                        "3 cups brown rice",
                        "2 tablespoons canola oil",
                        "1 cup carrots ",
                        "1 cup yellow onions",
                        "4 cloves garlic",
                        "1 tablespoon fresh ginger",
                        "1 1/2 cups broccoli florets",
                        "3/4 cup red bell pepper",
                        "4 large eggs",
                        "3/4 cup petite peas",
                        "3/4 cup corn",
                        "4 tablespoons soy sauce",
                        "1 tablespoon sesame oil"
                },
                "Nick He",
                "30",
                "520",
                new String[]{
                        "Heat oil in a large non-stick wok (or large, deep skillet) over medium-high heat. Add carrots, onions, garlic and ginger and saute 3 minutes.",
                        "Add broccoli and bell pepper and saute until veggies are soft, about 3 - 4 minutes. Move veggies over to one side of the pan, crack eggs into opposite side and scramble and cook through.",
                        "Stir rice, peas, corn, soy sauce and sesame oil and cook and toss 2 minutes. Serve warm.",
                        "I like to cook the brown rice with a little less water than listed on the package so it's not so moist and absorbs the soy sauce and moisture from the veggies better. It also works best if you can prepare the rice a day in advance.",
                        "Recipe source: Cooking Classy"
                },
                food8
        );

        getFakeRepo().add(r1);
        getFakeRepo().add(r2);
        getFakeRepo().add(r3);
        getFakeRepo().add(r4);
        getFakeRepo().add(r5);
        getFakeRepo().add(r6);
        getFakeRepo().add(r7);
        getFakeRepo().add(r8);
    }

    public List<Recipe> getFakeRepo() {
        return fakeRepo;
    }
}