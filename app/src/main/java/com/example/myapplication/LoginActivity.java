package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

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
        bind.toReg.setPaintFlags(bind.toReg.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        bind.log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = bind.email.getText().toString();
                String password = bind.password.getText().toString();
                if(email.length() > 0 && password.length() > 0)
                {
                    new LoginSync(email, password, getApplicationContext(), new LoginSync.loginCallback() {
                        @Override
                        public void preExecute() {

                        }

                        @Override
                        public void postExecute(List<User> listUser) {
                            boolean isFound = false;
                            for(User data: listUser)
                            {
                                if(data.getEmail().equals(email))
                                {
                                    isFound = true;
                                    if(data.getPassword().equals(password))
                                    {
                                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                        i.putExtra("loggedUser", data);
                                        startActivity(i);
                                        finish();
                                    }
                                    else
                                    {
                                        makeText("Password salah");
                                    }
                                }
                            }

                            if(!isFound)
                            {
                                makeText("User tidak ditemukan");
                            }
                        }
                    }).execute();
                }
                else
                {
                    makeText("Data tidak boleh kosong");
                }


            }
        });

        bind.toReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void makeText(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
    Boolean isFound = false;
    Boolean isLogged = false;
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

                handler.post(()->{
                    String succesMessage ="Login Gagal";
                    weakCallback.get().postExecute(listUser);
                });


            }
        });
    }


    interface loginCallback{
        void preExecute();
        void postExecute(List<User> listUser);
    }
}