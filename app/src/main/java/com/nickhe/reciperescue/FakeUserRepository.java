package com.nickhe.reciperescue;

import android.app.Activity;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class FakeUserRepository {

    private static FakeUserRepository fakeUserRepository;
    private ArrayList<User> fakeUserRepo = new ArrayList<>();

    private FakeUserRepository(){
        initializeRepo();
    }

    private void initializeRepo() {

        User u1 = new User("James", 100);
        User u2 = new User("Alex", 90);
        User u3 = new User("Tony", 70);
        User u4 = new User("Jackson", 50);
        User u5 = new User("Carl", 60);
        User u6 = new User("Johnny", 75);
        User u7 = new User("Leon", 40);
        User u8 = new User("Micheal", 60);

        fakeUserRepo.add(u1);
        fakeUserRepo.add(u2);
        fakeUserRepo.add(u3);
        fakeUserRepo.add(u4);
        fakeUserRepo.add(u5);
        fakeUserRepo.add(u6);
        fakeUserRepo.add(u7);
        fakeUserRepo.add(u8);
    }

    public static synchronized FakeUserRepository getFakeUserRepository(){
        if(fakeUserRepository == null){
            fakeUserRepository = new FakeUserRepository();
        }

        return fakeUserRepository;
    }

    public ArrayList<User> getFakeUserRepo() {
        return fakeUserRepo;
    }


}