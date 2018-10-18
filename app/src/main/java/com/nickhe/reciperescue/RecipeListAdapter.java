package com.nickhe.reciperescue;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends ArrayAdapter<Recipe>
{
    private List<Recipe> recipes;
    private Activity context;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public RecipeListAdapter(Activity context, List<Recipe> recipes) {
        super(context, R.layout.recipe_row, R.id.recipeTextView, recipes);
        this.recipes = recipes;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ViewHolder viewHolder = null;

        if(row == null)
        {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            row = layoutInflater.inflate(R.layout.recipe_row, parent, false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)row.getTag();
        }

        Bitmap bitmap = ImageProcessor.convertUriToBitmap(context, recipes.get(position).getRecipeImage());
        viewHolder.imageView.setImageBitmap(bitmap);
        System.out.println("Set imageBitmap success!");
        viewHolder.textView.setText(recipes.get(position).getRecipeTitle());

        return row;
    }

    class ViewHolder
    {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View view)
        {
            textView = view.findViewById(R.id.recipeTextView);
            imageView = view.findViewById(R.id.recipeImageView);
        }
    }

}
