package com.firstonline.module.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.firstonline.R;
import com.firstonline.net.NetUtil;

public class MainActivity extends BaseActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
        NetUtil.test("http://www.autohome.com.cn/",this,tv);
    }
}
