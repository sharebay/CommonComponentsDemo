package com.codebay.vam.commoncomponentsdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.codebay.vam.base.BaseActivity;
import com.codebay.vam.utils.AppToast;
import com.codebay.vam.widgets.dialog.ConfirmDialogFragment;
import com.codebay.vam.widgets.dialog.ListDialogFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 6; i++) {
            AppToast.showToast("123 "+i);

        }

        initView();
    }

    ConfirmDialogFragment.ConfirmDialogListener confirmDialogListener;
    ListDialogFragment.ListDialogListener listDialogListener;
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
        listDialogListener = new ListDialogFragment.ListDialogListener(){
            @Override
            public void onItemClick(int position) {
                AppToast.showToast("您点击了:"+position);
            }

            @Override
            public String[] getmItemContents() {
                return new String[0];
            }
        };

        findViewById(R.id.btn_show_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogFactory.showProgressDialog("Activity调起的进度条...",true);
            }
        });
        findViewById(R.id.btn_show_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mDialogFactory.showConfirmDialog("标题", "内容", true, confirmDialogListener);
                mDialogFactory.showListDialog(new String[]{"123","333"},false,listDialogListener);
            }
        });
    }
}
