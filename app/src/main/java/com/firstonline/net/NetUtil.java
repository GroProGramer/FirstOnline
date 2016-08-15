package com.firstonline.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class NetUtil {
    private static final OkHttpClient client=new OkHttpClient();
    private static Response response;
    private static WeakReference<Activity> conRef;
    public static void test(String url, Activity activity, final TextView tv){
        conRef=new WeakReference<Activity>(activity);
        Request request=new Request.Builder()
                .url(url)
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\\n")
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Log.v("firstonline","test failed");

                conRef.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("test failed");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                NetUtil.response=response;
               // Log.v("onResponse","response:"+response.body().string());
                conRef.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(conRef.get(), "onResponse:"+response, Toast.LENGTH_LONG).show();
                        try {
                            tv.setText("response:"+response.body().string());
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
