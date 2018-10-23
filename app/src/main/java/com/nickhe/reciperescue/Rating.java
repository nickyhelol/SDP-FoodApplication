package com.nickhe.reciperescue;

public enum Rating {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
    private int rating;

    private Rating(int rating) {
        this.rating = rating;

    }
}
