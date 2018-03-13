package com.codebay.vam.commoncomponentsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codebay.vam.utils.AppToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s = null;
        AppToast.init(this.getApplication());
        //AppToast.showToast("123 "+s.toString());
    }
}
