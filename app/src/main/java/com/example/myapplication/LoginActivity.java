package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                Loginuser();
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
    //volleylogin
    public void Loginuser(){
        StringRequest sr = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            String message = jsonObject.getString("message");
                            String userEmail = jsonObject.getString("user");
                            if (code == 1){
                                Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                                i.putExtra("loggedEmail", userEmail);
                                startActivity(i);
                                finish();
                            }
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("function", "login");
                params.put("email", bind.email.getText().toString());
                params.put("password", bind.password.getText().toString());

                return params;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);
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