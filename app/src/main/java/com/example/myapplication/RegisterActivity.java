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
import com.example.myapplication.databinding.ActivityRegisterBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.one.animate().alpha(1.0f).setDuration(5000);
        binding.toLog.setPaintFlags(binding.toLog.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        binding.toLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        binding.reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.nama.getText().toString();
                String email = binding.email.getText().toString();
                String pass = binding.password.getText().toString();
                String passconf = binding.passconf.getText().toString();
                if (pass.equals(passconf)){
                    User user = new User(name,email,pass);
                    adduser(user);
                }else{
                    makeText("Konfirmasi password tidak sesuai");
                }
//                if(name.length() > 0 && email.length() > 0 && pass.length() > 0 && passconf.length() > 0)
//                {
//                    if(pass.equals(passconf))
//                    {
//                        User user = new User(name, email, pass);
//                        insertmySQL(name,email,pass);
//                        new AddUserSync(user, getApplicationContext(), new AddUserSync.AddUpdateNoteCallback() {
//                            @Override
//                            public void preExecute() {
//
//                            }
//
//                            @Override
//                            public void postExecute(String message) {
//                                makeText(message);
//                            }
//                        }).execute();
//
//                    }
//                    else
//                    {
//                        makeText("Konfirmasi password tidak sesuai");
//                    }
//                }
//                else
//                {
//                    makeText("Data tidak boleh kosong");
//                }

            }
        });
    }
    public void adduser(User user){
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
                            if (code == 1){
                                Intent i = new Intent(RegisterActivity.this,HomeActivity.class);
                                startActivity(i);
                                finish();
                            }
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
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
                Map<String,String> params = new HashMap<>();
                params.put("function","adduser");
                params.put("username",user.getName());
                params.put("email", user.getEmail());
                params.put("password", user.getPassword());
                return params;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);
    }
//    public void insertmySQL(String name,String email,String pass){
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                "http://localhost/myapi/insert.php"
//                , new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String success = jsonObject.getString("success");
//                    if(success.equals("1")){
//                        System.out.println("Register Berhasil");
//                    }else{
//                        System.out.println("gagal");
//                    }
//                }catch (JSONException e){
//                    System.out.println("gagal");
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams()  {
//                Map<String,String>parms=new HashMap<String, String>();
//                parms.put("name",name);
//                parms.put("email",email);
//                parms.put("password",pass);
//                return parms;
//            }
//        };
//        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(stringRequest);
//    }
    public void makeText(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

class AddUserSync{
    private final WeakReference<Context> weakContext;
    private final WeakReference<AddUpdateNoteCallback> weakCallback;
    private User user;

    public AddUserSync(User user,Context context, AddUpdateNoteCallback callback) {
        this.weakContext = new WeakReference<>(context);
        this.weakCallback = new WeakReference<>(callback);
        this.user = user;
    }
    Boolean found = false;
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
                    if(user.getEmail().matches(listUser.get(i).getEmail())){
                        found = true;
                    }
                }
                if (found){
                    handler.post(()->{
                        String succesMessage ="Email sudah pernah dipakai! Mohon gunakan email yang lain";
                        weakCallback.get().postExecute(succesMessage);
                    });
                }else{
                    userDatabase.userDao().insertUser(user);
                    handler.post(()->{
                        String succesMessage = "User berhasil terdaftar";
                        weakCallback.get().postExecute(succesMessage);
                    });
                }
            }
        });
    }


    interface AddUpdateNoteCallback{
        void preExecute();
        void postExecute(String message);
    }
}