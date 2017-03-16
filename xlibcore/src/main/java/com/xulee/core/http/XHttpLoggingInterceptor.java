package com.xulee.core.http;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Desc: http请求拦截器，定义统一请求头信息，以及打印数据信息
 * Created by xulee on 2017/3/13
 */

public class XHttpLoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder();
        requestBuilder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        requestBuilder.addHeader("Api-Version", "2.0");
//        requestBuilder.addHeader("Accept", "application/json");
        request = requestBuilder.build();

        final Response response = chain.proceed(request);
        Logger.e("请求网址: \n" + request.url() + " \n " + "请求头部信息：\n" + request.headers() + "响应头部信息：\n" + response.headers());
        final ResponseBody responseBody = response.body();
        final long contentLength = responseBody.contentLength();

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();

        Charset charset = Charset.forName("UTF-8");
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(charset);
            } catch (UnsupportedCharsetException e) {
                Logger.e("");
                Logger.e("Couldn't decode the response body; charset is likely malformed.");
                return response;
            }
        }

        if (contentLength != 0) {
            Logger.v("--------------------------------------------开始打印返回数据----------------------------------------------------");
            Logger.json(buffer.clone().readString(charset));
            Logger.v("--------------------------------------------结束打印返回数据----------------------------------------------------");
        }
        return response;
    }
}
