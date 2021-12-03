package com.tpnet.tpbluetooth.net;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpManager {
    public static final String TAG = "OkhttpManager";
    private static OkhttpManager instance;
    private String mUrl;

    private OkhttpManager() {
    }

    public static OkhttpManager getInstance() {
        if (instance == null) {
            instance = new OkhttpManager();
        }
        return instance;
    }

    public void init(String url) {
        this.mUrl = url;
    }

    public void upload(String requestBody) {
        Log.e(TAG, "upload: " + requestBody);
        if (TextUtils.isEmpty(mUrl)) {
            return;
        }
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        Request request = new Request.Builder()
                .url(mUrl)
                .post(RequestBody.create(mediaType, requestBody))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size() && headers != null; i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

}