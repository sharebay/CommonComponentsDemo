package com.codebay.vam.commoncomponentsdemo.user_mgr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codebay.vam.commoncomponentsdemo.R;
import com.codebay.vam.utils.NetUilts;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserMgrActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String hostUrlPre = "http://192.168.253.1:8080/EPOM_Server";

    Button btn_get_user_pwd,btn_post_add_user,btn_post_update_user,btn_get_all_user;
    TextView tv_request,tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mgr);
        initView();
        initListner();
    }

    void test(){
        String sql="select username,realname from userinfo where username like \\\"%\\\"?\\\"%\\\";";
        String sss = "{\"sql\":\"select username,realname from userinfo where username like \\\"%\\\"?\\\"%\\\";\",\"sqlArguments\":[{\"value\":\"as\",\"type\":\"string\"}],\"pageIndex\":1,\"pagePerSize\":10}";

        String json = "{\n\t\"p\": \"{\\\"sql\\\":\\\"select username,realname from userinfo where username like \\\\\\\"%\\\\\\\"?\\\\\\\"%\\\\\\\"\\\",\\\"sqlArguments\\\":[{\\\"value\\\":\\\"1\\\",\\\"type\\\":\\\"string\\\"}],\\\"pageIndex\\\":1,\\\"pagePerSize\\\":6}\"\n}";
    }

    private void initView() {
        btn_get_user_pwd = (Button) findViewById(R.id.btn_get_user_pwd );
        btn_post_add_user = (Button) findViewById(R.id.btn_post_add_user );
        btn_post_update_user = (Button) findViewById(R.id.btn_post_update_user );
        btn_get_all_user = (Button) findViewById(R.id.btn_get_all_user );

        tv_request = (TextView) findViewById(R.id.tv_request );
        tv_result = (TextView) findViewById(R.id.tv_result );
    }

    private void initListner() {


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_user_pwd:
                break;
            case R.id.btn_post_add_user:
                break;
            case R.id.btn_post_update_user:
                break;
            case R.id.btn_get_all_user:
                break;
            default:
                break;
        }
    }


    public static String loginOfGet(String username,String password){
        HttpURLConnection conn=null;
        try {
            String data="username="+username+"&"+"pwd="+password;
            URL url=new URL(hostUrlPre+"/login?"+data);
            conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");//设置请求方式
            conn.setConnectTimeout(10000);//设置连接超时时间
            conn.setReadTimeout(5000);//设置读取超时时间
            conn.connect();//开始连接
            int responseCode=conn.getResponseCode();//获取响应吗
            if(responseCode==200){
                //访问成功
                InputStream is=conn.getInputStream();//得到InputStream输入流
                String state= NetUilts.getStateFromInputstream(is);
                return state;
            }else{
                //访问失败

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(conn!=null){//如果conn不等于空，则关闭连接
                conn.disconnect();
            }
        }
        return null;

    }

    public static String  loginOfPost(String username,String password){
        HttpURLConnection conn=null;
        try {
            URL url=new URL(hostUrlPre+"login?");
            conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");//设置请求方式
            conn.setConnectTimeout(10000);//设置连接超时时间
            conn.setReadTimeout(5000);//设置读取超时时间

            //POST请求的参数
            OutputStream out=conn.getOutputStream();//获得输出流对象，用于向服务器写数据
            String data="username="+username+"&"+"pwd="+password;
            out.write(data.getBytes());//向服务器写数据;
            out.close();//关闭输出流
            conn.connect();//开始连接
            int responseCode=conn.getResponseCode();//获取响应吗
            if(responseCode==200){
                //访问成功
                InputStream is=conn.getInputStream();//得到InputStream输入流
                String state= NetUilts.getStateFromInputstream(is);
                return state;
            }else{
                //访问失败

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(conn!=null){//如果conn不等于空，则关闭连接
                conn.disconnect();
            }
        }
        return null;

    }
}
