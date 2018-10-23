package com.nickhe.reciperescue;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
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
        super(context, R.layout.user_row, R.id.userNameTextView, users);
        this.users = users;
        this.context = context;
        sortUserList();
    }

    private void sortUserList() {
        int n = users.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (users.get(j).getScore() < users.get(j + 1).getScore()) {
                    User temp = users.get(j);
                    users.set(j, users.get(j + 1));
                    users.set(j + 1, temp);
                }
            }
        }
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

        }

        if (position == 2) {
            viewHolder.medalIamgeView.setImageResource(R.drawable.medal3);
        }

        switch (position) {
            case 0:
                viewHolder.medalIamgeView.setImageResource(R.drawable.medal1);
                break;
            case 1:
                viewHolder.medalIamgeView.setImageResource(R.drawable.medal2);
                break;
            default:
                break;
        }

        viewHolder.userNametextView.setText(users.get(position).getName());
        viewHolder.scoreTextView.setText(String.valueOf(users.get(position).getScore()));

        return row;
    }

    class ViewHolder {
        TextView userNametextView;
        TextView scoreTextView;
        ImageView medalIamgeView;

        public ViewHolder(View view) {
            userNametextView = view.findViewById(R.id.userNameTextView);
            scoreTextView = view.findViewById(R.id.scoreTextView);
            medalIamgeView = view.findViewById(R.id.medalImageView);
        }
    }
}
