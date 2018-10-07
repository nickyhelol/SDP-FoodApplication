package com.nickhe.reciperescue;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class IngredientTokensAdapter extends RecyclerView.Adapter {

    private List<String> tokens;

    public IngredientTokensAdapter(Activity context) {
        tokens = new ArrayList<>();
        tokens.add("Chicken");
        tokens.add("Mushroom");
        tokens.add("Garlic");
    }

    public void addToken(String string) {
        tokens.add(string);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_token, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setFullSpan(true);
        final String ingredient = tokens.get(position);
        viewHolder.button.setText(ingredient);
        viewHolder.button.setTextOn(ingredient);
        viewHolder.button.setTextOff(ingredient);
        viewHolder.itemView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return tokens.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ToggleButton button;

        public ViewHolder(View view) {
            super(view);
            button = view.findViewById(R.id.ingredientToken);
        }
    }
}
