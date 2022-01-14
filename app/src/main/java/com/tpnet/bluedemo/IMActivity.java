package com.tpnet.bluedemo;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tpnet.bluedemo.adapter.MessageListAdapter;
import com.tpnet.bluedemo.bean.IMMessage;
import com.tpnet.bluedemo.util.ToastUtil;
import com.tpnet.tpbluetooth.EIBackHaulBluetooth;
import com.tpnet.tpbluetooth.inter.BlueClientListener;
import com.tpnet.tpbluetooth.inter.BlueMessageListener;
import com.tpnet.tpbluetooth.inter.BlueServerListener;
import com.tpnet.tpbluetooth.inter.connect.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by litp on 2017/6/1.
 */

public class IMActivity extends Activity implements View.OnClickListener, BlueMessageListener {
    public static final String TAG = "IMActivity";
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private EditText mEtMessage;
    private Button mBtnSend;
    private List<IMMessage> mDataList;
    private MessageListAdapter mAdapter;
    private EIBackHaulBluetooth mBlueControl;
    private BluetoothDevice mDevice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);

        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_message);
        mEtMessage = (EditText) findViewById(R.id.et_message);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mDataList = new ArrayList<>();
        mAdapter = new MessageListAdapter(mDataList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mBtnSend.setOnClickListener(this);
        mDevice = getIntent().getParcelableExtra(Constant.INTENT_DEVICE);
        connect(mDevice);
    }

    /**
     * 连接
     */
    private void connect(BluetoothDevice device){
        mBlueControl = EIBackHaulBluetooth.getInstance();
        mBlueControl.setOnMessageListener(this);
        if (device != null) {
            //连接服务器
            mBlueControl.connect(device);
        } else {
            //服务器开始聊天
            mToolbar.setTitle("当前连接设备:" + mBlueControl.getBluetoothServer().getClientNum());
        }
    }

    private void classicBt() {
        mBlueControl.setOnClientListener(new BlueClientListener() {

            @Override
            public void onStartConnect() {
                super.onStartConnect();
                Log.e(TAG, "onStartConnect: ");
                ToastUtil.show("正在连接..." + mDevice.getName());
            }

            @Override
            public void onFinishConnect() {
                super.onFinishConnect();
                Log.e(TAG, "onFinishConnect: ");

            }

            @Override
            public void onConnecting() {
                super.onConnecting();
                ToastUtil.show("已连接");
                Log.e(TAG, "onConnecting: 已连接");
                mToolbar.setTitle(mDevice.getName());
            }

            @Override
            public void onClientError(Exception e) {
                super.onClientError(e);
                ToastUtil.show("连接失败:" + e.getMessage());
                Log.e(TAG, "onClientError: " + e.getMessage());
            }
        });
    }

    private void bleBt() {
        mBlueControl.setOnServerListener(new BlueServerListener() {
            @Override
            public void onStartListener() {
            }

            @Override
            public void onListenering() {
            }

            @Override
            public void onFinishListener() {
            }

            @Override
            public void onServerError(Exception e) {
            }

            @Override
            public void onGetClient(BluetoothDevice device) {
                //有客户端进来了
                ToastUtil.show("有一设备进来了");
                mToolbar.setTitle("当前连接设备:" + mBlueControl.getBluetoothServer().getClientNum());

            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        classicBt();
        bleBt();

    }

    @Override
    public void onClick(View v) {
        //发送消息
        String text = mEtMessage.getText().toString().trim();
        Log.e(TAG, "onClick: " + text);
        mBlueControl.sendMessage(text);
        IMMessage message = new IMMessage();
        message.setContent(text);
        message.setMac(mBlueControl.getAdapter().getAddress());
        message.setName(mBlueControl.getAdapter().getName());
        message.setSender(true);
        message.setTime(System.currentTimeMillis());
        insertMessage(message);
        mEtMessage.setText("");

    }

    @Override
    public void onReceiveMessage(BluetoothDevice device, byte[] mess) {
        Log.e(TAG, "onReceiveMessage: " + mess);
        //收到消息
        IMMessage message = new IMMessage();
        message.setContent(new String(mess));
        if (!TextUtils.isEmpty(device.getName())) {
            message.setName(device.getName());
        }
        message.setMac(device.getAddress());
        message.setSender(false);
        message.setTime(System.currentTimeMillis());
        insertMessage(message);

    }


    private void insertMessage(IMMessage message) {
        mDataList.add(message);
        Log.e(TAG, "insertMessage: " + mDataList.toString());
        mAdapter.notifyItemInserted(mDataList.size() - 1);
        moveToPosition(mDataList.size() - 1);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭连接
        if (mDevice != null) {
            //关闭客户端连接
            //mBlueControl.
        }
    }
    //

    /**
     * 缓慢滑动
     * <p>
     * 当指定位置位于第一个可见位置之上时，可以滚动，利用smoothScrollToPosition实现
     * 当指定位置位于可视位置之间时，得到距离顶部的距离，然后smoothScrollBy向上滚动固定的距离
     * 当指定的位置位于最后一个可见位置之下时，可以滚动，利用利用smoothScrollToPosition实现实现
     */
    public void moveToPosition(int position) {
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem || position > lastItem) {
            mRecyclerView.smoothScrollToPosition(position);
        } else {
            int movePosition = position - firstItem;
            int top = mRecyclerView.getChildAt(movePosition).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }
    }

    //    另一种获得可见位置的方法
    public void moveToPosition(RecyclerView recyclerView, int position) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //因为只有LinearLayoutManager 才有获得可见位置的方法
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int firstItem = linearLayoutManager.findFirstVisibleItemPosition();
            int lastItem = linearLayoutManager.findLastVisibleItemPosition();
            if (position < firstItem || position > lastItem) {
                mRecyclerView.smoothScrollToPosition(position);
            } else {
                int movePosition = position - firstItem;
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        }
    }
}
