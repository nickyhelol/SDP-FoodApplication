package com.nickhe.reciperescue;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class FakeUserRepository {

    //Singleton fake repository
    private static FakeUserRepository fakeUserRepository;
    private List<User> fakeRepo;
    Activity context;
    int[] images = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4
            , R.drawable.food5, R.drawable.food6, R.drawable.food7, R.drawable.food8};

    public FakeUserRepository(Activity context)
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
    public static synchronized FakeUserRepository getFakeUserRepository(Activity context)
    {
        if (fakeUserRepository == null) {
            fakeUserRepository = new FakeUserRepository(context);
        }

        return fakeUserRepository;
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

        User u1 = new User("User 1", 10000, food8);
        User u2 = new User("User 2", 9500, food7);
        User u3 = new User("User 3", 8000, food6);
        User u4 = new User("User 4", 6000, food5);
        User u5 = new User("User 5", 6500, food4);
        User u6 = new User("User 6", 3500, food2);
        User u7 = new User("User 7", 2000, food3);
        User u8 = new User("User 8", 1000, food1);

        getFakeRepo().add(u1);
        getFakeRepo().add(u2);
        getFakeRepo().add(u3);
        getFakeRepo().add(u4);
        getFakeRepo().add(u5);
        getFakeRepo().add(u6);
        getFakeRepo().add(u7);
        getFakeRepo().add(u8);
    }

    public List<User> getFakeRepo() {
        return fakeRepo;
    }
}