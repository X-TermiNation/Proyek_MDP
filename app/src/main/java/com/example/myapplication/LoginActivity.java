package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        bind.one.animate().alpha(1.0f).setDuration(5000);
        bind.log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}

class LoginSync{
    private final WeakReference<Context> weakContext;
    private final WeakReference<loginCallback> weakCallback;
    private String Username;
    private String password;
    public LoginSync(String username, String password,Context context, loginCallback callback) {
        this.Username = username;
        this.password = password;
        this.weakContext = new WeakReference<>(context);
        this.weakCallback = new WeakReference<>(callback);
    }
    Boolean IsFound = false;
    void execute(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        weakCallback.get().preExecute();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Context context = weakContext.get();
                UserDatabase userDatabase = UserDatabase.getUserDatabase(context);
                List<User> listUser;
                listUser = userDatabase.userDao().gettAllUser();
                for (int i = 0; i < listUser.size(); i++) {
                    if(Username.equals(listUser.get(i).getEmail())){
                        if(password.equals(listUser.get(i).getPassword())){
                            IsFound = true;
                        }
                    }
                }
                if(IsFound){
                    handler.post(()->{
                        String succesMessage ="Login Berhasil";
                        weakCallback.get().postExecute(succesMessage);
                    });
                    IsFound = false;
                }else{
                    handler.post(()->{
                        String succesMessage ="Login Gagal";
                        weakCallback.get().postExecute(succesMessage);
                    });
                }

            }
        });
    }


    interface loginCallback{
        void preExecute();
        void postExecute(String message);
    }
}