package com.codebay.vam.commoncomponentsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codebay.vam.utils.AppToast;
import com.codebay.vam.widgets.CustDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 6; i++) {
            AppToast.showToast("123 "+i);

        }

        initView();
    }

    private void initView() {
        CustDialog.newInstance().show(getSupportFragmentManager(),"asdf");
    }
}
