package com.example.seventh_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String temppasswd;
    private ImageView first_password_img;
    private ImageView second_password_img;
    private ImageView third_password_img;
    private ImageView forth_password_img;
    private ImageView fifth_password_img;
    private ImageView sixth_password_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //数据集合
        final String[] numbers = new String[]{"1","2","3","4","5","6","7","8","9","紧急呼救","0","清空"};
        String[] alters = new String[]{"","ABC","DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ"," ","+",""};
        final String passwd = "666666";
        temppasswd = "";

        //图片组
        first_password_img = findViewById(R.id.first_password_img);
        second_password_img = findViewById(R.id.second_password_img);
        third_password_img = findViewById(R.id.third_password_img);
        forth_password_img = findViewById(R.id.forth_password_img);
        fifth_password_img = findViewById(R.id.fifth_password_img);
        sixth_password_img = findViewById(R.id.sixth_password_img);

        //密码键盘
        GridView password_pad = findViewById(R.id.password_pad);

        //初始化列表
        ArrayList<Map<String,String>> listview = new ArrayList<>();
        for(int i = 0; i < numbers.length; i ++){
            Map<String,String> map = new HashMap<>();
            map.put("number",numbers[i]);
            map.put("alter",alters[i]);
            listview.add(map);
        }

        //初始化视图
        MyAdapter adapter = new MyAdapter(getApplicationContext(),listview,R.layout.password_pad_style,new String[]{"number","alter"},new int[]{R.id.pad_number,R.id.pad_alter});
        password_pad.setAdapter(adapter);
        password_pad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position != 9 && position != 11){
                    temppasswd += numbers[position];
                }
                if(position == 11){
                    temppasswd = "";
                }
                if(position == 9){
                    startActivity(new Intent(getApplicationContext(),Sos.class));
                }
                passwd_status(temppasswd.length());
                if(temppasswd.length() == 6){
                    checkpasswd(temppasswd);
                }
            }

            private void checkpasswd(String temppasswd) {
                if(temppasswd.equals(passwd)){
                    Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    passwd_status(7);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(getApplicationContext(),AppList.class));
                            finish();
                        }
                    },1500);

                }else {
                    Toast.makeText(getApplicationContext(),"密码错误，请重试",Toast.LENGTH_SHORT).show();
                    passwd_status(7);
                }
            }
        });

    }

    private void passwd_status(int length) {
        if(length == 1){
            Glide.with(this).load(R.drawable.clickable).into(first_password_img);
        }else if(length == 2){
            Glide.with(this).load(R.drawable.clickable).into(second_password_img);
        }else if(length == 3){
            Glide.with(this).load(R.drawable.clickable).into(third_password_img);
        }else if(length == 4){
            Glide.with(this).load(R.drawable.clickable).into(forth_password_img);
        }else if(length == 5){
            Glide.with(this).load(R.drawable.clickable).into(fifth_password_img);
        }else if(length == 6){
            Glide.with(this).load(R.drawable.clickable).into(sixth_password_img);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Glide.with(MainActivity.this).load(R.drawable.unclickable).into(first_password_img);
                    Glide.with(MainActivity.this).load(R.drawable.unclickable).into(second_password_img);
                    Glide.with(MainActivity.this).load(R.drawable.unclickable).into(third_password_img);
                    Glide.with(MainActivity.this).load(R.drawable.unclickable).into(forth_password_img);
                    Glide.with(MainActivity.this).load(R.drawable.unclickable).into(fifth_password_img);
                    Glide.with(MainActivity.this).load(R.drawable.unclickable).into(sixth_password_img);
                    temppasswd = "";
                }
            },1000);
        }
    }

    private class MyAdapter extends SimpleAdapter {
        /**
         * Constructor
         *
         * @param context  The context where the View associated with this SimpleAdapter is running
         * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
         *                 Maps contain the data for each row, and should include all the entries specified in
         *                 "from"
         * @param resource Resource identifier of a view layout that defines the views for this list
         *                 item. The layout file should include at least those named views defined in "to"
         * @param from     A list of column names that will be added to the Map associated with each
         *                 item.
         * @param to       The views that should display column in the "from" parameter. These should all be
         *                 TextViews. The first N views in this list are given the values of the first N columns
         */
        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);


            return view;
        }
    }
}
