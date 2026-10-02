package android.net.http;

import android.net.http.AndroidHttpClient;
import android.net.http.AndroidHttpClient$1;
import android.net.http.b;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

class a implements HttpRequestInterceptor {

   // $FF: synthetic field
   final AndroidHttpClient a;


   private a(AndroidHttpClient var1) {
      this.a = var1;
   }

   public void process(HttpRequest var1, HttpContext var2) {
      b var3 = AndroidHttpClient.a(this.a);
      if(var3 != null && b.a(var3) && var1 instanceof HttpUriRequest) {
         b.a(var3, AndroidHttpClient.a((HttpUriRequest)var1, false));
      }

   }

   // $FF: synthetic method
   a(AndroidHttpClient var1, AndroidHttpClient$1 var2) {
      this(var1);
   }
}
