package com.tpnet.bluedemo;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tpnet.bluedemo.util.ToastUtil;
import com.tpnet.tpbluetooth.TPBluetooth;
import com.tpnet.tpbluetooth.event.ReveiverEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by litp on 2017/6/1.
 */

public class IndexActivity extends AppCompatActivity {
    public static final String TAG = "IndexActivity";
    private CheckBox mCbOpen;
    private TPBluetooth mBlueControl;
    private TextView tv_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_index);
        mCbOpen = (CheckBox) findViewById(R.id.cb_open);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());

        //请求定位权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                //申请
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_FINE_LOCATION);
            }
        }


        mBlueControl = TPBluetooth.getInstance();

        if (mBlueControl.isBluetoothEnable()) {
            mCbOpen.setChecked(true);
        } else {
            // 1：开启蓝牙
//            BluetoothAdapter.getDefaultAdapter().enable();
            // 2：开启蓝牙
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        mCbOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isPressed()) {
                    if (isChecked) {
                        mBlueControl.openBlueTooth(IndexActivity.this);
                    } else {
                        mBlueControl.closeBlueTooth();
                    }

                }


            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 权限被用户同意，可以做你要做的事情了。
                } else {
                    // 权限被用户拒绝了，可以提示用户,关闭界面等等。
                    ToastUtil.show("获取权限失败，即将退出");
                    finish();
                }


            }
        }
    }

    public static final int REQUEST_ENABLE_BT = 100;
    public static final int REQUEST_ACCESS_FINE_LOCATION = 666;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: requestCode==" + requestCode + ",resultCode==" + resultCode);
        if (Activity.RESULT_OK == resultCode) {
            Toast.makeText(this, "accept ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "refuse", Toast.LENGTH_SHORT).show();
        }
    }

    public void classicBluetooth(View view) {
        if (mBlueControl.isBluetoothEnable()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            ToastUtil.show("请先开启蓝牙");
        }
    }

    public void BleBluetooth(View view) {
        if (mBlueControl.isBluetoothEnable()) {
            startActivity(new Intent(this, BleActivity.class));
        } else {
            ToastUtil.show("请先开启蓝牙");
        }
    }


    /**
     * 断开蓝牙连接监听
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReveiverEvent event) {
        Log.e(TAG, "onEventMainThread: "+event.getMsg() );
        content.add(event.getMsg());
        tv_content.setText(content.toString());
    }

    List<String> content = new ArrayList<>();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}
