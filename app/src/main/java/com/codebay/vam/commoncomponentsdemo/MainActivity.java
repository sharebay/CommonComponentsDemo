package com.codebay.vam.commoncomponentsdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.codebay.vam.base.BaseActivity;
import com.codebay.vam.commoncomponentsdemo.user_mgr.UserMgrActivity;
import com.codebay.vam.utils.AppToast;
import com.codebay.vam.utils.NetUilts;
import com.codebay.vam.widgets.dialog.ConfirmDialogFragment;
import com.codebay.vam.widgets.dialog.ListDialogFragment;

import java.io.UnsupportedEncodingException;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button btn_login_get;
    private Button btn_login_post,btn_to_login,btn_to_user_mgr;
    String username = "user";
    String password = "pwd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams. FLAG_SECURE);//防止截屏



        initView();

        init();


    }
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    ConfirmDialogFragment.ConfirmDialogListener confirmDialogListener;
    private void initView() {
        //CustDialog.newInstance().show(getSupportFragmentManager(),"asdf");

        confirmDialogListener =  new ConfirmDialogFragment.ConfirmDialogListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == DialogInterface.BUTTON_POSITIVE){
                    AppToast.showToast("您点击了确定按钮");
                }
                if (which == DialogInterface.BUTTON_NEGATIVE){
                    AppToast.showToast("您点击了取消按钮");
                }
            }
        };

        findViewById(R.id.btn_show_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogFactory.showProgressDialog("Activity调起的进度条...",true);
            }
        });
        final String[] single_list=new String[]{"123","333"};
        findViewById(R.id.btn_show_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mDialogFactory.showConfirmDialog("标题", "内容", true, confirmDialogListener);
                mDialogFactory.showListDialog(single_list,false,new ListDialogFragment.ListDialogListener(){
                    @Override
                    public void onItemClick(int position) {
                        AppToast.showToast("您点击了:"+single_list[position]);
                    }

                    @Override
                    public String[] getmItemContents() {
                        return new String[0];
                    }
                });
            }
        });
    }

    private void showSingleChoiceDialog() {
        final String[] single_list={""};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("单选对话框");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(single_list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String str = single_list[which];
                Toast.makeText(MainActivity.this, str + "被点击了", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void init(){
        btn_login_get=(Button) findViewById(R.id.btn_login_get);
        btn_login_post=(Button) findViewById(R.id.btn_login_post);
        btn_to_login=(Button) findViewById(R.id.btn_to_login);
        btn_to_user_mgr=(Button) findViewById(R.id.btn_to_user_mgr);
        btn_login_get.setOnClickListener(this);
        btn_login_post.setOnClickListener(this);
        btn_to_login.setOnClickListener(this);
        btn_to_user_mgr.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_login_get:
                doget(username,password);
                if(!isNetworkConnected(this)){
                    AppToast.showToast("网络未连接");
                }
                break;
            case R.id.btn_login_post:
                dopost(username,password);
                break;
            case R.id.btn_to_login:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,UserLoginActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_to_user_mgr:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this,UserMgrActivity.class);
                MainActivity.this.startActivity(intent2);
                break;
        }
    }
    private void doget(final String username, final String password) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                final String state= NetUilts.loginOfGet(username, password);
                String str = null;
                str = state;
                /*try {
                    str = new String(state.getBytes("gbk"), "gbk");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }*/
                final String finalStr = str;
                runOnUiThread(new Runnable() {//执行任务在主线程中
                    @Override
                    public void run() {//就是在主线程中操作
                        AppToast.showToast(finalStr);
                    }
                });
            }
        }).start();
    }
    private void dopost(final String username, final String password) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                final String state=NetUilts.loginofPost(username, password);
                runOnUiThread(new Runnable() {//执行任务在主线程中
                    @Override
                    public void run() {//就是在主线程中操作
                        Toast.makeText(MainActivity.this, state, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }).start();
    }
}
