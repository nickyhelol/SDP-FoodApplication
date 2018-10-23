package com.nickhe.reciperescue;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {
    private List<User> users;
    private Activity context;

    public UserListAdapter(Activity context, List<User> users) {
        super(context, R.layout.user_row, R.id.userTextView, users);
        this.users = users;
        this.context = context;
    }

    public List<User> getRecipes() {
        return users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ViewHolder viewHolder = null;

        if (row == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            row = layoutInflater.inflate(R.layout.user_row, parent, false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }

        Bitmap bitmap = ImageProcessor.convertUriToBitmap(context, users.get(position).getProfileImage());
        viewHolder.imageView.setImageBitmap(bitmap);
        viewHolder.textView.setText(users.get(position).getName());
        viewHolder.scoreView.setText(Integer.toString(users.get(position).getScore()));

        return row;
    }

    class ViewHolder {
        TextView textView;
        TextView scoreView;
        ImageView imageView;

        public ViewHolder(View view) {
            textView = view.findViewById(R.id.userTextView);
            scoreView = view.findViewById(R.id.scoreTextView);
            imageView = view.findViewById(R.id.userImageView);
        }
    }

}
