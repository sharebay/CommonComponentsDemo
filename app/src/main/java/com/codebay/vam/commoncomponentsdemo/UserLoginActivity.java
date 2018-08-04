package com.codebay.vam.commoncomponentsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codebay.vam.utils.NetUilts;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserLoginActivity extends AppCompatActivity {
    EditText edt_Name,edt_Pwd;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        initView();
    }

    private void initView(){
        edt_Name = (EditText) findViewById(R.id.edt_userName);
        edt_Pwd = (EditText) findViewById(R.id.edt_userPwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(loginListener);
    }

    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = edt_Name.getText().toString();
            String pwd = edt_Pwd.getText().toString();
            doPost(name,pwd);
        }
    };

    private void doPost(final String username, final String password) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final String state=NetUilts.loginOfPostv2(username, password);

                runOnUiThread(new Runnable() {//执行任务在主线程中
                    @Override
                    public void run() {//就是在主线程中操作

                        if(state != null){
                            Toast.makeText(UserLoginActivity.this, state, Toast.LENGTH_SHORT).show();
                            if (state.equals("登录成功")){
                                Intent intent = new Intent();
                                intent.setClass(UserLoginActivity.this,MainActivity.class);
                                UserLoginActivity.this.startActivity(intent);
                            }
                        } else {
                            Toast.makeText(UserLoginActivity.this, "state is null", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        executor.execute(runnable);
        executor.shutdown();
    }
}
