package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityCommentBinding;
import com.example.myapplication.databinding.ActivityHomeBinding;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommentActivity extends AppCompatActivity {
    private ActivityCommentBinding binding;
    private String loggedEmail;
    private String commentContent;
    ArrayAdapter<Comment> adapter;
    ArrayList<Comment> comment = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        binding= ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if(intent.hasExtra("loggedEmail"))
        {
            loggedEmail = intent.getStringExtra("loggedEmail");
        }
        if(intent.hasExtra("commentContent")){
            commentContent = intent.getStringExtra("commentContent");
        }
        new getData(commentContent,getApplicationContext(), new getData.getDataCallback() {
            @Override
            public void preExecute() {

            }

            @Override
            public void postExecute(List<Comment> arr_comment) {
                comment.addAll(arr_comment);
                adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1,comment);
                binding.lvItem.setAdapter(adapter);
            }
        }).execute();


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.EtComment.getText().toString().equals("")){
                    Comment comment = new Comment(loggedEmail,commentContent,binding.EtComment.getText().toString());
                    new AddCommentAsync(comment, getApplicationContext(), new AddCommentAsync.AddUpdateNoteCallback() {
                        @Override
                        public void preExecute() {

                        }

                        @Override
                        public void postExecute(String message) {
                            makeToast(message);
                            adapter.notifyDataSetChanged();
                            finish();
                            startActivity(getIntent());
                        }

                    }).execute();
                }
            }
        });
        binding.btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                i.putExtra("loggedEmail", loggedEmail);
                finish();
            }
        });
    }
    void makeToast(String pesan){
        Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_comment,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.keluar_btn:
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                i.putExtra("loggedEmail", loggedEmail);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}


class AddCommentAsync {
    private final WeakReference<Context> weakContext;
    private final WeakReference<AddUpdateNoteCallback> weakCallback;
    private Comment comment;


    public AddCommentAsync(Comment comment, Context context, AddUpdateNoteCallback callback) {
        this.weakContext = new WeakReference<>(context);
        this.weakCallback = new WeakReference<>(callback);
        this.comment = comment;
    }

    void execute() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        weakCallback.get().preExecute();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Context context = weakContext.get();
                UserDatabase userDatabase = UserDatabase.getUserDatabase(context);
                userDatabase.userDao().insertComment(comment);

                handler.post(() -> {
                    String succesMessage = "New Comment Added";
                    weakCallback.get().postExecute(succesMessage);
                });
            }
        });
    }

    interface AddUpdateNoteCallback {
        void preExecute();

        void postExecute(String message);
    }
}

class getData {
    private final WeakReference<Context> weakContext;
    private final WeakReference<getDataCallback> weakCallback;
    private String commentContent;

    public getData(String commentContent,Context context, getDataCallback callback) {
        this.commentContent = commentContent;
        this.weakContext = new WeakReference<>(context);
        this.weakCallback = new WeakReference<>(callback);
    }

    void execute() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        weakCallback.get().preExecute();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Comment> arr_comment;
                Context context = weakContext.get();
                UserDatabase userDatabase = UserDatabase.getUserDatabase(context);
                userDatabase.userDao().getComment(commentContent);
                arr_comment = userDatabase.userDao().getComment(commentContent);
                handler.post(() -> {
                    weakCallback.get().postExecute(arr_comment);
                });
            }
        });
    }

    interface getDataCallback {
        void preExecute();
        void postExecute(List<Comment> arr_comment);
    }
}