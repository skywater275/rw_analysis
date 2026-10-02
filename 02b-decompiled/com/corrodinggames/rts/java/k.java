package com.corrodinggames.rts.java;

import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class k extends com.corrodinggames.rts.gameFramework.j.r {

   public HttpClient a(int var1, boolean var2) {
      Builder var3 = RequestConfig.custom();
      var3 = var3.setConnectTimeout(var1);
      var3 = var3.setConnectionRequestTimeout(var1);
      HttpClientBuilder var4 = HttpClientBuilder.create();
      var4.setDefaultRequestConfig(var3.build());
      CloseableHttpClient var5 = var4.build();
      return var5;
   }

   public void a(HttpClient var1) {
      if(var1 instanceof CloseableHttpClient) {
         CloseableHttpClient var2 = (CloseableHttpClient)var1;

         try {
            var2.close();
         } catch (IOException var4) {
            var4.printStackTrace();
         }
      } else {
         com.corrodinggames.rts.gameFramework.l.e("closeHttpClient: Didn\'t close");
      }

   }
}
