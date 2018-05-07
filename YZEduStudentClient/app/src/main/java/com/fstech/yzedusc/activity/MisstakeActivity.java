package com.fstech.yzedusc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fstech.yzedusc.R;

/**
 * Created by shaoxin on 18-5-5.
 * 错题的主界面
 */

public class MisstakeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misstake);

    }

    /*
    * 初始化视图
    * */
    private void initView(){

    }

    /*
    * 初始化数据
    * */
    private void initData(){

    }

    /*
   * 返回上一级
   * xml布局文件里面调用
   * */
    public void back(View view) {
        finish();
    }

}
