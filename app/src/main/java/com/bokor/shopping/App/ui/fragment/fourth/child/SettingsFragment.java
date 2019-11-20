package com.bokor.shopping.App.ui.fragment.fourth.child;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bokor.shopping.App.entity.Article;
import com.bokor.shopping.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import me.bokor.fragmentation.SupportFragment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created on 9/10/2019.
 */
public class SettingsFragment extends SupportFragment {
    private Toolbar mToolbar;
    private Button btn;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseDatabase database; //Database Reference
    private int num_child;
    private EditText etName,etAge,etEmail;
    private ImageView imageView;

    private Uri filePath;
    private StorageReference storageRef;
    FirebaseStorage storage;

    long timeInMillis;
    SimpleDateFormat dateFormat;
    Calendar cal1;

    private final int PICK_IMAGE_REQUEST = 71;

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_fourth_settings, container, false);
        database = FirebaseDatabase.getInstance(); //Get Database Instance
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = view.findViewById(R.id.toolbarSettings);

        mToolbar.setNavigationIcon(R.drawable.icon_back_left_arrow);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

        timeInMillis = System.currentTimeMillis();
        cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

        etName = view.findViewById(R.id.etName);
        etAge = view.findViewById(R.id.etAge);
        etEmail = view.findViewById(R.id.etEmail);
        imageView = view.findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DatabaseReference refUsers = database.getReference().child("Users").push(); //Adds a new child under "Users" key. Push Function adds a unique id.
//                DatabaseReference refName = refUsers.child("Name");
//                refName.setValue("Sen Thou");
//                DatabaseReference refAge = refUsers.child("Age");
//                refAge.setValue("20");
//                DatabaseReference refEmail = refUsers.child("Email");
//                refEmail.setValue("Senthou168@gmail.com");
//                saveGL_withProducts(null,null,);
                uploadImage();

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void chooseImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_REQUEST);
    }
    private void uploadImage() {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref = storageRef.child("images/"+ dateFormat.format(cal1.getTime()));
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            if (taskSnapshot.getMetadata() != null) {
                                if (taskSnapshot.getMetadata().getReference() != null) {
                                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imageUrl = uri.toString();
                                            //createNewPost(imageUrl);
                                            Log.d("URL1212121",imageUrl);
                                            saveGL_withProducts(null,null,imageUrl);
                                        }
                                    });
                                }
                            }
                            Toast.makeText(getActivity(),"Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(),"Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("12121",""+e.getMessage());
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded"+(int)progress+"%");
                        }
                    });
        }
    }

    private void saveGL_withProducts(Article groceryListsModel, List<Article> groceryListProductsModels,String urlimage){
        if (firebaseDatabase == null)
            firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference().child("ProductList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                num_child = (int) dataSnapshot.getChildrenCount();
                Log.d("5454545",String.valueOf(num_child));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        DatabaseReference pushRef = ref.child(dateFormat.format(cal1.getTime()));
//        DatabaseReference pushRef = ref.push().setValue(num_child);
        DatabaseReference refName = pushRef.child("Name");
        refName.setValue(etName.getText().toString());
        DatabaseReference refAge = pushRef.child("Age");
        refAge.setValue(etAge.getText().toString());
        DatabaseReference refEmail = pushRef.child("Email");
        refEmail.setValue(etEmail.getText().toString());
        DatabaseReference refImage = pushRef.child("Image");
        refImage.setValue(urlimage);
        String generated_GL_key = pushRef.getKey();

//        if(!isNullOrBlank(generated_GL_key)){
//            //TO DO ADDING IN GROCERYLISTPRODUCTS NODE
//        }
//        else{
//            //TO DO
//        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }
}
