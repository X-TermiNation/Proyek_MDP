package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.myapplication.databinding.ActivityRegisterBinding;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
                        String succesMessage ="User Sudah Terdaftar";
                        weakCallback.get().postExecute(succesMessage);
                    });
                }else{
                    userDatabase.userDao().insertUser(user);
                    handler.post(()->{
                        String succesMessage ="New User Added";
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