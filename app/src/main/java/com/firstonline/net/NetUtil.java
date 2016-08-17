package com.firstonline.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.firstonline.net.bean.Contributor;
import com.firstonline.net.bean.GitHub;
import com.firstonline.net.bean.GithubConverter;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit.GsonConverterFactory;
import retrofit2.Retrofit;

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

               final String result=response.body().string();
               // Log.v("onResponse","response:"+response.body().string());
                conRef.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(conRef.get(), "onResponse:"+response, Toast.LENGTH_LONG).show();
                        tv.setText("response:"+result);

                    }
                });
            }
        });
    }

    public static void HttpGetByRetrofit(Activity activity,String baseUrl,String owner,String repo){
        conRef=new WeakReference(activity);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GithubConverter.factory)
                //.addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHub gitHub=retrofit.create(GitHub.class);
        retrofit2.Call<String> call=gitHub.contributors("square","retrofit");

        call.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
                final String result=response.toString();
                conRef.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(conRef.get(), "onResponse:"+result, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                Toast.makeText(conRef.get(), "onFailure: get msg failed", Toast.LENGTH_LONG).show();
            }
        });

        /*call.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, final retrofit2.Response<String> response) {
                final String result=response.toString();
                conRef.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(conRef.get(), "onResponse:"+result, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(retrofit2.Call<List<Contributor>> call, Throwable t) {
                Toast.makeText(conRef.get(), "onFailure: get msg failed", Toast.LENGTH_LONG).show();

            }
        });*/
    }
}
