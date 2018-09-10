package com.nickhe.reciperescue;

public class User {

    public String age;
    public String email;
    public String name;

    public User(){

    }

    public User(String name, String age, String email) {
        this.age = age;
        this.email = email;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
