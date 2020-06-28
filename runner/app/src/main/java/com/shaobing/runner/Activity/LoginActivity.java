package com.shaobing.runner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.SharePrefrenceHelper;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.DB.MySqlDBAccess;
import com.shaobing.runner.R;
import com.shaobing.runner.Tools.Tool;
import com.shaobing.runner.util.DemoResHelper;
import org.json.JSONObject;
import java.util.HashMap;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.UserInterruptException;
import cn.smssdk.gui.CountryPage;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LoginActivity";
    private static final String[] DEFAULT_COUNTRY = new String[]{"中国", "42", "86"};
    private static final int COUNTDOWN = 60;
    private static final String KEY_START_TIME = "start_time";
    private static final int REQUEST_CODE_VERIFY = 1001;
    private TextView tvSms02;
    private TextView tvCountry02;
    private EditText etPhone02;
    private EditText etCode02;
    private TextView tvCode02;
    private TextView tvVerify02;
    private TextView tv_login_login02;
    private TextView tv_login_register;
    private TextView tvToast;
    private String currentId;
    private String currentPrefix;
    private FakeActivity callback;
    private Toast toast02;
    private Handler handler02;
    private EventHandler eventHandler02;
    private int currentSecond;
    private SharePrefrenceHelper helper02;

    private UserBean user;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Tool.steep(this,true,true);

        initViews();
        initListener();

        //默认获取短信和验证按钮不可点击，输入达到规范后，可点击
        tvVerify02.setEnabled(false);
        tvCode02.setEnabled(false);
        //默认使用短信验证
        tvSms02.setSelected(true);
        //默认使用中国区号
        currentId = DEFAULT_COUNTRY[1];
        currentPrefix = DEFAULT_COUNTRY[2];
        tvCountry02.setText(getString(R.string.smssdk_default_country) + " +" + DEFAULT_COUNTRY[2]);
        helper02 = new SharePrefrenceHelper(this);
        helper02.open("sms_sp");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_VERIFY) {
            etCode02.setText("");
            etPhone02.setText("");
            // 重置"获取验证码"按钮
            tvCode02.setText(R.string.smssdk_get_code);
            tvCode02.setEnabled(true);
            if (handler02 != null) {
                handler02.removeCallbacksAndMessages(null);
            }
        }else if (requestCode==1&&resultCode==2){
            SMSSDK.registerEventHandler(eventHandler02);
            if (data!=null) {
                String backUN = data.getStringExtra("userName");
                //String backPW = data.getStringExtra("password");
                etPhone02.setText(backUN);
                //et_login_password.setText(backPW);
            }
        }
    }

    private void initViews() {
        tvSms02 = findViewById(R.id.tvSms02);
        tvCountry02 = findViewById(R.id.tvCountry02);
        etPhone02 = findViewById(R.id.etPhone02);
        etCode02 = findViewById(R.id.etCode02);
        tvCode02 = findViewById(R.id.tvCode02);
        tv_login_login02 = findViewById(R.id.tv_login_login02);
        tv_login_register = findViewById(R.id.tv_login_register);
    }

    private void initListener() {
        tvSms02.setOnClickListener(this);
        findViewById(R.id.ivSelectCountry02).setOnClickListener(this);
        tvCode02.setOnClickListener(this);
        tv_login_login02.setOnClickListener(this);
        tv_login_register.setOnClickListener(this);
        tvVerify02 = findViewById(R.id.tvVerify02);
        tvVerify02.setOnClickListener(this);
        etPhone02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //手机号输入大于5位，获取验证码按钮可点击
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCode02.setEnabled(etPhone02.getText() != null && etPhone02.getText().length() > 5);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etCode02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //验证码输入6位并且手机大于5位，验证按钮可点击
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvVerify02.setEnabled(etCode02.getText() != null && etCode02.getText().length() >= 6 && etPhone02.getText() != null && etPhone02.getText().length() > 5);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //回调选中的国家
        callback = new FakeActivity() {
            @Override
            public void onResult(HashMap<String, Object> data) {
                if (data != null) {
                    int page = (Integer) data.get("page");
                    if (page == 1) {
                        currentId = (String) data.get("id");
                        String[] country = SMSSDK.getCountry(currentId);
                        if (country != null) {
                            tvCountry02.setText(country[0] + " " + "+" + country[1]);
                            currentPrefix = country[1];
                        }
                    }
                }
            }
        };
        handler02 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (tvCode02 != null) {
                    if (currentSecond > 0) {
                        tvCode02.setText(getString(R.string.smssdk_get_code) + " (" + currentSecond + "s)");
                        tvCode02.setEnabled(false);
                        currentSecond--;
                        handler02.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        tvCode02.setText(R.string.smssdk_get_code);
                        tvCode02.setEnabled(true);
                    }
                }
            }
        };

        eventHandler02 = new EventHandler() {
            public void afterEvent(final int event, final int result, final Object data) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //提交验证成功，跳转成功页面，否则toast提示
                            if (result == SMSSDK.RESULT_COMPLETE) {
                                //判断手机号是否已经注册
                                userName = etPhone02.getText().toString().trim();
                                Thread t1 = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        user = MySqlDBAccess.queryUser(userName);
                                    }
                                });
                                t1.start();
                                try {
                                    t1.join(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (user !=null) {
                                    //写入配置文件
                                    putUserId(userName);
                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable("user",user);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                            finish();
                                        }
                                    },1000);
                                }else {
                                    Toast.makeText(LoginActivity.this, "手机号未注册", Toast.LENGTH_SHORT).show();
                                }
//                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                                startActivity(intent);
//                                finish();
                            } else {
                                processError(data);
                            }
                        }
                    });
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE || event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (result == SMSSDK.RESULT_COMPLETE) {
                                currentSecond = COUNTDOWN;
                                handler02.sendEmptyMessage(0);
                                helper02.putLong(KEY_START_TIME, System.currentTimeMillis());
                            } else {
                                if (data != null && (data instanceof UserInterruptException)) {
                                    // 由于此处是开发者自己决定要中断发送的，因此什么都不用做
                                    return;
                                }
                                processError(data);
                            }
                        }
                    });
                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler02);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSelectCountry02:
                //将当前国家带入跳转国家列表
                CountryPage countryPage = new CountryPage();
                countryPage.setCountryId(currentId);
                countryPage.showForResult(LoginActivity.this, null, callback);
                break;
            case R.id.tvVerify02:
                if (!isNetworkConnected()) {
                    Toast.makeText(LoginActivity.this, getString(R.string.smssdk_network_error), Toast.LENGTH_SHORT).show();
                    break;
                }
                SMSSDK.submitVerificationCode(currentPrefix, etPhone02.getText().toString().trim(), etCode02.getText().toString());
                break;
            case R.id.tvCode02:
                //获取验证码间隔时间小于1分钟，进行toast提示，在当前页面不会有这种情况，但是当点击验证码返回上级页面再进入会产生该情况
                long startTime = helper02.getLong(KEY_START_TIME);
                if (System.currentTimeMillis() - startTime < COUNTDOWN * 1000) {
                    showErrorToast(getString(R.string.smssdk_busy_hint));
                    break;
                }
                if (!isNetworkConnected()) {
                    Toast.makeText(LoginActivity.this, getString(R.string.smssdk_network_error), Toast.LENGTH_SHORT).show();
                    break;
                }
                SMSSDK.getVerificationCode(currentPrefix, etPhone02.getText().toString().trim());
                break;
            case R.id.tv_login_login02:
                Intent intent02 = new Intent(LoginActivity.this,LoginForPwdActivity.class);
                startActivity(intent02);
                finish();
                break;
            case R.id.tv_login_register:
                SMSSDK.unregisterEventHandler(eventHandler02);
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler02 != null) {
            handler02.removeCallbacksAndMessages(null);
        }
        SMSSDK.unregisterEventHandler(eventHandler02);
    }

    private void showErrorToast(String text) {
        if (toast02 == null) {
            toast02 = new Toast(this);
            View rootView = LayoutInflater.from(this).inflate(R.layout.smssdk_error_toast_layout, null);
            tvToast = rootView.findViewById(R.id.tvToast);
            toast02.setView(rootView);
            toast02.setGravity(Gravity.CENTER, 0, ResHelper.dipToPx(this, -100));
        }
        tvToast.setText(text);
        toast02.show();
    }


    private boolean isNetworkConnected() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    private void processError(Object data) {
        int status = 0;
        // 根据服务器返回的网络错误，给toast提示
        try {
            ((Throwable) data).printStackTrace();
            Throwable throwable = (Throwable) data;

            JSONObject object = new JSONObject(
                    throwable.getMessage());
            String des = object.optString("detail");
            status = object.optInt("status");
            if (!TextUtils.isEmpty(des)) {
                showErrorToast(des);
                return;
            }
        } catch (Exception e) {
            Log.w(TAG, "", e);
        }
        // 如果木有找到资源，默认提示
        int resId = DemoResHelper.getStringRes(getApplicationContext(),
                "smsdemo_network_error");
        String netErrMsg = getApplicationContext().getResources().getString(resId);
        showErrorToast(netErrMsg);
    }

    private void putUserId(String userId){
        SharedPreferences sp = getSharedPreferences("runnerConfig",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userId",userId);
        editor.apply();
    }
}
