package com.example.jaery.javaproject;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class GetJson {

    private String url="http://106.10.58.28/";

    private OkHttpClient client;
    private static GetJson instance = new GetJson();
    public static GetJson getInstance() {
        return instance;
    }

    private GetJson(){ this.client = new OkHttpClient(); }

    /** 웹 서버로 요청을 한다. */
    public void requestWebServer(Callback callback,String php,String... param) {

        url += php + "?" + param[0];

        for (int i = 1; i < param.length; i++) {
            url += "&";
            url += param[i];
        }


        RequestBody body = new FormBody.Builder()

                .build();
        Request request = new Request.Builder()
                .url( url)
                .post(body)//받은 데이터
                .build();
        client.newCall(request).enqueue(callback); //통신후 콜백될 함수
    }


}

