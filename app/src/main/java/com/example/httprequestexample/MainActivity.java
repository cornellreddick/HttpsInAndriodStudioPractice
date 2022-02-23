package com.example.httprequestexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    final String TAG = "Demo";

    private final OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createContact("Maurice Jackson","mj@gmail.com","123-123-4455", "CELL");
//        String name = "rh";
//        String type = "HOME";
//// First approach to makeing a Get request.
//        HttpUrl url = HttpUrl.parse("https://www.theappsdr.com/contacts/search").newBuilder()
//                .addQueryParameter("name", name)
//                .addQueryParameter("type", type)
//                .build();
////Second approach
//        HttpUrl.Builder builder = new HttpUrl.Builder();
//       HttpUrl url1 = builder.scheme("https")
//                .host("www.theappsdr.com")
//                .addPathSegment("contacts")
//               .addPathSegment("search")
//                .addQueryParameter("name", name)
//                .addQueryParameter("type", type).build();
//
//
//        Log.d(TAG, "onCreate: " + url);
//        Log.d(TAG, "onCreate: " + url1);
//
//
//        Log.d(TAG, "onCreate: Main Thread Id" + Thread.currentThread().getId());
//        Request request = new Request.Builder()
//                .url(url)
//                .build();

//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                Log.d(TAG, "onResponse: Thread Id" + Thread.currentThread().getId());
//                if (response.isSuccessful()){
//                    ResponseBody responseBody = response.body();
//                    String body = responseBody.string();
//                    Log.d(TAG, "onResponse: " + body);
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
//            }
//        });

    }

    void createContact(String name, String email, String phone, String type){

      FormBody formBody =  new FormBody.Builder()
                .add("name", name)
                .add("email", email)
                .add("phone", phone)
                .add("type", type)
                .build();

        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contact/create")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Log.d(TAG, "onResponse: " + body);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });

    }

}