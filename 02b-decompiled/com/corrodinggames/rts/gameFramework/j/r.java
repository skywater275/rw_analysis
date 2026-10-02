package com.corrodinggames.rts.gameFramework.j;

import android.net.http.AndroidHttpClient;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class r {

   ReentrantLock a = new ReentrantLock();


   public HttpClient a() {
      return this.a(30000, false);
   }

   public HttpClient b() {
      return this.a(30000, true);
   }

   public HttpClient a(int var1, boolean var2) {
      boolean var3 = false;

      try {
         var3 = this.a.tryLock(300L, TimeUnit.MILLISECONDS);
      } catch (InterruptedException var9) {
         var9.printStackTrace();
      }

      Object var4;
      try {
         if(!var3) {
            com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", "Could not get getNewHttpClient lock! another thread maybe stuck in getNewHttpClient!");
         }

         if(!var2) {
            var4 = AndroidHttpClient.a((String)null);
         } else {
            var4 = new DefaultHttpClient();
         }

         HttpParams var5 = ((HttpClient)var4).getParams();
         HttpConnectionParams.setConnectionTimeout(var5, var1);
         HttpConnectionParams.setSoTimeout(var5, 20000);
      } finally {
         if(var3) {
            this.a.unlock();
         }

      }

      return (HttpClient)var4;
   }

   public void a(HttpClient var1) {
      if(var1 instanceof AndroidHttpClient) {
         AndroidHttpClient var2 = (AndroidHttpClient)var1;
         var2.a();
      }

   }
}
