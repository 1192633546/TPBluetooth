package com.tpnet.tpbluetooth.net;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tpnet.tpbluetooth.net.bean.ServerUrlInfoBean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

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

    public void init() {
        getServerUrl(URL.LAUNCHER_TEST);
    }

    public void getServerUrl(String mUrl) {
        Log.e(TAG, "upload: " + mUrl);
        if (TextUtils.isEmpty(mUrl)) {
            return;
        }
        Request request = new Request.Builder()
                .url(mUrl)
                .get()
                .build();
        OkHttpClient okHttpClient = getClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "getServerUrl onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Log.d(TAG, "getServerUrl onResponse: " + body);
                getUrl(body);
            }
        });
    }

    private void getUrl(String body) {
        ServerUrlInfoBean bean = new Gson().fromJson(body, new TypeToken<ServerUrlInfoBean>() {
        }.getType());
        Log.e(TAG, "getUrl: " + bean);
        this.mUrl = bean.getData().getSrvAddress();

        String requestBody=NetManager.getInstance().uploadLog("");
        NetManager.getInstance().upLoadData(requestBody);
    }

    public void upload(String requestBody) {
        Log.e(TAG, "upload: mUrl==" + mUrl);
        if (TextUtils.isEmpty(mUrl)) {
            return;
        }
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Request request = new Request.Builder()
                .url(mUrl+URL.UPLOAD_PATH)
                .post(RequestBody.create(mediaType, requestBody))
                .build();
        OkHttpClient okHttpClient = getClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "upload onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "upload onResponse: " + response.body().string());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size() && headers != null; i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
            }
        });
    }

    private OkHttpClient getClient() {
        //log用拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //缓存
        //开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                //连接超时事件
                .connectTimeout(20, TimeUnit.SECONDS)
                //读取超时事件
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(logging)
                .addInterceptor(headInterceptor)
                //设置缓存目录
                .build();
    }

    Interceptor headInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            String Access_Control_Allow_Headers = "Content-type,X-Auth-Token,X-XSRF-TOKEN,PAGE-ID,OPERATION-TYPE,Access-Token";
            String Access_Control_Allow_Methods = "";
            String version = "1.0";
            Request builder = chain.request().newBuilder()
                    .addHeader("Accept-Encoding", "gzip,deflate")
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Access-Control-Allow-Headers", Access_Control_Allow_Headers)
                    .addHeader("Access-Control-Allow-Methods", Access_Control_Allow_Methods)
                    .addHeader("version", version)
                    .build();
            return chain.proceed(builder);
        }
    };


}