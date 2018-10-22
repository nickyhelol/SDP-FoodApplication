package com.nickhe.reciperescue;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaExtractor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    TextView updateButton;
    ImageView profileImageView;
    ListView listView;
    TextView name;
    FakeRecipeRepository fakeRecipeRepository;
    public final int READ_IMAGE_PERMISSION = 0;
    public final int PICK_IMAGE_RESULT = 1;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;

    private FirebaseStorage firebaseStorage;
    private static int CHOOSE_IMAGE = 123;
    Uri profilePicPath;

    private FirebaseDatabase firebaseDatabase;

    RecyclerView re;
    View v;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_IMAGE_PERMISSION);
        }

        name = view.findViewById(R.id.nameEditText);
        updateButton = view.findViewById(R.id.updateButton);
        profileImageView = view.findViewById(R.id.profileImageView);
        listView = view.findViewById(R.id.profile_recipeList);
        fakeRecipeRepository = FakeRecipeRepository.getFakeRecipeRepository(getActivity());
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(getActivity(), fakeRecipeRepository.getFakeRepo());
        listView.setAdapter(recipeListAdapter);

        //setListViewHeightBasedOnChildren(listView);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseStorage= FirebaseStorage.getInstance();

        storageReference= firebaseStorage.getReference();
//        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        StorageReference storageReference1 = firebaseStorage.getReference();
        storageReference1.child(firebaseAuth.getUid()).child("Images").child("Profile Picture").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(profileImageView);
               // Glide.with(getContext()).load(upload.getUrl()).into(holder.imageView);
                //Picasso.get().load(uri.toString()).into(profileImageView);
            }
        });

        updateViews();


        ListViewProcessor.setListViewHeightBasedOnChildren(listView);


        //Set clickListener to allow users to select image from their phone as the profile image
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, 1);
                //sendUserDataToDatabase();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserDataToDatabase();
            }
        });

        //To allow users to be able to open a recipe and review that
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Recipe recipe = fakeRecipeRepository.getFakeRepo().get(position);

                startRecipeViewActivity(recipe);
            }
        });

        TextView newRecipeButton = view.findViewById(R.id.newRecipeButton);
        newRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateRecipeActivity.class));
            }
        });


    }



    public void updateViews() {
        System.out.println(UserDataManager.getUser().getName());
        name.setText(UserDataManager.getUser().getName().toString());
    }

    /**
     *
     * @param recipe
     */
    private void startRecipeViewActivity(Recipe recipe)
    {
        Intent i = new Intent(getActivity().getBaseContext(), RecipeViewActivity.class);
        i.putExtra("recipe", recipe);
        startActivity(i);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case READ_IMAGE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Permission denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PICK_IMAGE_RESULT:
                if (resultCode == Activity.RESULT_OK) {
                    profilePicPath = data.getData();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver()
                            .query(profilePicPath, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    profileImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                }

        }
    }


    /**
     * Make sure the listView will be set by the correct height based on
     * the number of the items it has.
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * This method creates the database reference per user and sends it to the firebase database.
     */
    private void sendUserDataToDatabase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());//getting the UID of the user from the firebase console.

        /**since the storageReference is the parent reference of the user reference, we are creating image reference as a child of the
         *of the storagereference. This is creating folder inside the storage in firebase for each user
         *we're creating image folder in the storage of firebase for each user. It will be in the form of User Id/Images/profile Picture
         */
        StorageReference profilePicReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Picture");

        /**
         * once we created folder we need to upload that reference to the database
         *it takes the reference and put the Uri path to the reference.
         */

        UploadTask uploadTask = profilePicReference.putFile(profilePicPath);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Profile Pic uploading failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getActivity(), "Profile picture successfully uploaded", Toast.LENGTH_SHORT).show();
            }
        });


    }

}










//    /**
//     * When user is done with the activity which can be image,audio,video,documents activity then we need to get result
//     * and convert that into the required type which can be image, this is image here.
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        //checking if the request to choose image has been successfull
//        if (requestCode==CHOOSE_IMAGE && resultCode ==RESULT_OK && data.getData()!=null){
//            profilePicPath= data.getData();//the data is in the form of a path so we need to convert it to image
//
//            //Bitmap will convert the path into the image form
//            //https://developer.android.com/reference/android/graphics/Bitmap
//            try {
//                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),profilePicPath);
//                //now setting the bitmap to the profile pic,sets the content of the bitmap as imageView.
//                profileImageView.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//}

