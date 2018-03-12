package com.codebay.vam.commoncomponentsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codebay.vam.utils.AppToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent intent = new Intent();
        intent.setClass(MainActivity.this, QrCodeCapturer.class);
        startActivity(intent);*/

        String s = null;
        AppToast.init(this.getApplication());
        for (int i = 0; i < 1; i++) {
            AppToast.showToast("123 "+s.toString());
        }

        /*Toast toast = MyToast.getToast();
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
        toast.setText("自定义Toast");
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();*/
    }
}
