/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.config.RequestConfig$Builder
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClientBuilder
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.network.HttpClientPool;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class GameConfig
extends com.corrodinggames.rts.gameFramework.network.HttpClientPool {

    public HttpClient a(int n, boolean bl) {
        RequestConfig.Builder builder = RequestConfig.custom();
        builder = builder.setConnectTimeout(n);
        builder = builder.setConnectionRequestTimeout(n);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setDefaultRequestConfig(builder.build());
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        return closeableHttpClient;
    }


    public void a(HttpClient httpClient) {
        if (httpClient instanceof CloseableHttpClient) {
            CloseableHttpClient closeableHttpClient = (CloseableHttpClient)httpClient;
            try {
                closeableHttpClient.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        } else {
            GlobalState.e("closeHttpClient: Didn't close");
        }
    }
}
