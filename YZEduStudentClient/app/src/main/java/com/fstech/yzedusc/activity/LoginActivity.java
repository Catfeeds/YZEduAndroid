package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.util.CacheActivityUtil;
import com.fstech.yzedusc.view.ClearEditText;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundLinearLayout;

/**
 * Created by shaoxin on 18-5-23.
 * 登录主界面
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    // 定义UI对象
    private ClearEditText etAccount;
    private ClearEditText etPassword;
    private TextView tvForget;
    private TextView tvRegister;
    private QMUIRoundLinearLayout qrlLogin;
    private TextView tvSchoolLogin;
    private ProgressBar progressBar;
    private Handler handler;
    SharedPreferences perPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    // 数据加载完成
                    case 1:
                        progressBar.setVisibility(View.GONE);
                        qrlLogin.setClickable(true);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initView() {
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        CacheActivityUtil.addActivity(LoginActivity.this);
        etAccount = (ClearEditText) findViewById(R.id.et_account);
        etPassword = (ClearEditText) findViewById(R.id.et_password);
        tvForget = (TextView) findViewById(R.id.tv_forget);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        qrlLogin = (QMUIRoundLinearLayout) findViewById(R.id.qrl_login);
        tvSchoolLogin = (TextView) findViewById(R.id.tv_school_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        tvForget.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        qrlLogin.setOnClickListener(this);
        tvSchoolLogin.setOnClickListener(this);
    }

    private void initData() {
        perPreferences = getSharedPreferences("loginmsg", MODE_PRIVATE);
        editor = perPreferences.edit();
        if (perPreferences != null) {
            String mphone = perPreferences.getString("mphone", "");
            String mpwd = perPreferences.getString("pass", "");
            etAccount.setText(mphone);
            etPassword.setText(mpwd);
        }
    }

    // 监听点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_forget:
                // TODO 忘记密码的业务
                break;
            case R.id.qrl_login:
                // TODO 登录
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                if (checkFormit(account, password) == true) {
                    // 登录验证
                    doLogin(account, password);
                }
                break;
            case R.id.tv_register:
                // 进入注册页
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_school_login:
                // 进入院校登录页
                Intent intent3 = new Intent(LoginActivity.this, SchoolSelectActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    /*
    * 登录的方法
    * */
    private void doLogin(String account, String password) {
        progressBar.setVisibility(View.VISIBLE);
        qrlLogin.setClickable(false);
        // 模拟登录
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    handler.sendMessage(handler.obtainMessage(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /*
    * 验证输入格式合法的方法
    * */
    private boolean checkFormit(String account, String password) {
        if (account.equals("") || password.equals("")) {
            Toast.makeText(LoginActivity.this, R.string.account_password_not_null, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            // TODO 其他输入格式判断
            return true;
        }
    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }
}

