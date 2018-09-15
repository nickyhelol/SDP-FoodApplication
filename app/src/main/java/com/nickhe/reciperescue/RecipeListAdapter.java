package com.nickhe.reciperescue;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RecipeListAdapter extends ArrayAdapter<String>
{
    private String[] titles;
    private int[] images;
    private Activity context;

    public RecipeListAdapter(Activity context, String[] titles, int[] images) {
        super(context, R.layout.recipe_row, titles);
        this.titles = titles;
        this.images = images;
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

        viewHolder.imageView.setImageResource(images[position]);
        viewHolder.textView.setText(titles[position]);

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
