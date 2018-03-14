package com.codebay.vam.widgets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.codebay.vam.utils.AppToast;
import com.codebay.vam.utils.R;

/**
 * Created by RuanJian-GuoYong on 2018/3/14.
 */

public class CustDialog extends DialogFragment implements View.OnClickListener{

    public static CustDialog newInstance() {
        CustDialog custDialog = new CustDialog();
        return custDialog;
    }

    /*public void showPromptDialog(FragmentManager fragmentManager){
        String text = "";
        DialogFragment promptDialog = new DialogFragment() {
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
                    savedInstanceState) {
                getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
                View view = inflater.inflate(R.layout.fragment_prompt, container);
                return view;
            }
        };
        promptDialog.show(fragmentManager, text);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.fragment_prompt, container, false); //引入布局
        Button cancel = (Button) v.findViewById(R.id.cancel); //找到控件
        Button call = (Button) v.findViewById(R.id.ok);
        cancel.setOnClickListener(this); //设置监听
        call.setOnClickListener(this);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { //此处可以设置Dialog的style等等
        super.onCreate(savedInstanceState);
        setCancelable(true);//无法直接点击外部取消dialog
        //setStyle(DialogFragment.STYLE_NO_FRAME,0); //NO_FRAME就是dialog无边框，0指的是默认系统Theme
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cancel) {
            dismiss();

        } else if (i == R.id.ok) {
            AppToast.init(this.getActivity().getApplication());
            AppToast.showToast("OK！！！！！！");

        } else {
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
