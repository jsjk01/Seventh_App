package com.example.seventh_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppList extends AppCompatActivity {

    private String[] urls;
    private String[] appnames;
    private int[] appicons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        initView();
    }

    private void initView() {
        //初始化数据
        appnames = new String[]{"百度","哔哩哔哩","抖音","全民K歌","拼多多","QQ浏览器","喜马拉雅","今日头条","UC浏览器","百度网盘","滴答清单","酷狗音乐"};
        appicons = new int[]{R.drawable.baidu,R.drawable.bilibili,R.drawable.douyin,R.drawable.ktv,R.drawable.pinduoduo,R.drawable.qqbrowser,R.drawable.ting,R.drawable.toptitle,R.drawable.ucbrowser,R.drawable.baiduwangpan,R.drawable.tita,R.drawable.kugou};
        urls = new String[]{"https://www.baidu.com/","https://www.bilibili.com/","https://www.douyin.com/","https://kg.qq.com/",
        "https://youhui.pinduoduo.com/","https://browser.qq.com/","https://www.ximalaya.com/","https://www.toutiao.com/","https://www.uc.cn/","https://pan.baidu.com/","https://www.dida365.com/","http://www.kugou.com/"};
        ArrayList<Map<String, Object>> listview = new ArrayList<>();
        for(int i = 0; i < appnames.length; i ++){
            Map<String, Object> map = new HashMap<>();
            map.put("appname", appnames[i]);
            map.put("appicon", appicons[i]);
            map.put("url",urls[i]);
            listview.add(map);
        }

        //初始化视图
        MyAdapter adapter = new MyAdapter(getApplicationContext(),listview,R.layout.app_grid_style,new String[]{"appname","appicon"},new int[]{R.id.app_name,R.id.app_icon});
        GridView app_grid = findViewById(R.id.app_grid);
        app_grid.setAdapter(adapter);
        app_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    startActivity(new Intent(getApplicationContext(),BaiduSearch.class));
                }else {
                    openUrl(urls[position]);
                }

            }
        });
    }

    private void openUrl(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
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
