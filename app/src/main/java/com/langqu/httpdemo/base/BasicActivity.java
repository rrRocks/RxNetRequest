package com.langqu.httpdemo.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.langqu.httpdemo.MyApp;
import com.langqu.httpdemo.utils.BarTextColorUtils;
import com.langqu.httpdemo.utils.umeng.UmengUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * @author Ruanqi
 * @time 2019/7/29
 * 用于屏幕适配，activity跳转等
 */
public class BasicActivity  extends RxAppCompatActivity {

    public static final int NORESULTCODE = -1;
    protected final String TAG = this.getClass().getSimpleName();
    protected Bundle mBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (!isTaskRoot() && intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(intent.getAction())) {
            finish();
            return;
        }
        BarTextColorUtils.setLightStatusBar(this, true);
    }

    //跳转
    protected void skipActivity(Class cls, int ResultCode, Intent intent, Bundle bundle) {
        intent.setClass(this, cls);
        if (bundle != null) intent.putExtras(bundle);
        if (NORESULTCODE != ResultCode) {
            startActivityForResult(intent, ResultCode);
        } else {
            startActivity(intent);
        }
        mBundle = null;
    }

    //跳转 带返回参数
    protected void skipActivity(Class cls, int ResultCode) {
        skipActivity(cls, ResultCode, new Intent(), mBundle);
    }

    //普通跳转
    protected void skipActivity(Class cls) {
        skipActivity(cls, NORESULTCODE, new Intent(), mBundle);
    }

    //跳转
    protected void skipActivity(Class cls, Intent intent, Bundle bundle) {
        intent.setClass(this, cls);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
        mBundle = null;
    }

    //跳转并且finish
    protected void skipActivityFinish(Class cls) {
        skipActivity(cls);
        finish();
    }

    //跳转 带返回参数 并且finish
    protected void skipActivityFinish(Class cls, int ResultCode) {
        skipActivity(cls, ResultCode);
        finish();
    }

    //finish 带返回码
    protected void finish(int result) {
        Intent intent = new Intent();
        if (mBundle != null) intent.putExtras(mBundle);
        setResult(result, intent);
        super.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UmengUtils.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        UmengUtils.onPause(this);
    }

}
