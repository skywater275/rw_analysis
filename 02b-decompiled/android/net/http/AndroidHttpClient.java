package android.net.http;

import android.content.Context;
import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.net.http.AndroidHttpClient$1;
import android.net.http.AndroidHttpClient$2;
import android.net.http.b;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public final class AndroidHttpClient implements HttpClient {

   public static long a = 256L;
   private static String[] b = new String[]{"text/", "application/xml", "application/json"};
   private static final HttpRequestInterceptor c = new AndroidHttpClient$1();
   private final HttpClient d;
   private RuntimeException e = new IllegalStateException("AndroidHttpClient created and never closed");
   private volatile b f;


   public static AndroidHttpClient a(String var0, Context var1) {
      BasicHttpParams var2 = new BasicHttpParams();
      HttpConnectionParams.setStaleCheckingEnabled(var2, false);
      HttpConnectionParams.setConnectionTimeout(var2, '\uea60');
      HttpConnectionParams.setSoTimeout(var2, '\uea60');
      HttpConnectionParams.setSocketBufferSize(var2, 8192);
      HttpClientParams.setRedirecting(var2, false);
      SSLSessionCache var3 = var1 == null?null:new SSLSessionCache(var1);
      HttpProtocolParams.setUserAgent(var2, var0);
      SchemeRegistry var4 = new SchemeRegistry();
      var4.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      var4.register(new Scheme("https", SSLCertificateSocketFactory.getHttpSocketFactory('\uea60', var3), 443));
      ThreadSafeClientConnManager var5 = new ThreadSafeClientConnManager(var2, var4);
      return new AndroidHttpClient(var5, var2);
   }

   public static AndroidHttpClient a(String var0) {
      return a(var0, (Context)null);
   }

   private AndroidHttpClient(ClientConnectionManager var1, HttpParams var2) {
      this.d = new AndroidHttpClient$2(this, var1, var2);
   }

   protected void finalize() {
      super.finalize();
      if(this.e != null) {
         Log.b("AndroidHttpClient", "Leak found", this.e);
         this.e = null;
      }

   }

   public void a() {
      if(this.e != null) {
         this.getConnectionManager().shutdown();
         this.e = null;
      }

   }

   public HttpParams getParams() {
      return this.d.getParams();
   }

   public ClientConnectionManager getConnectionManager() {
      return this.d.getConnectionManager();
   }

   public HttpResponse execute(HttpUriRequest var1) {
      return this.d.execute(var1);
   }

   public HttpResponse execute(HttpUriRequest var1, HttpContext var2) {
      return this.d.execute(var1, var2);
   }

   public HttpResponse execute(HttpHost var1, HttpRequest var2) {
      return this.d.execute(var1, var2);
   }

   public HttpResponse execute(HttpHost var1, HttpRequest var2, HttpContext var3) {
      return this.d.execute(var1, var2, var3);
   }

   public Object execute(HttpUriRequest var1, ResponseHandler var2) {
      return this.d.execute(var1, var2);
   }

   public Object execute(HttpUriRequest var1, ResponseHandler var2, HttpContext var3) {
      return this.d.execute(var1, var2, var3);
   }

   public Object execute(HttpHost var1, HttpRequest var2, ResponseHandler var3) {
      return this.d.execute(var1, var2, var3);
   }

   public Object execute(HttpHost var1, HttpRequest var2, ResponseHandler var3, HttpContext var4) {
      return this.d.execute(var1, var2, var3, var4);
   }

   private static String b(HttpUriRequest var0, boolean var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("curl ");
      var2.append("-X ");
      var2.append(var0.getMethod());
      var2.append(" ");
      Header[] var3 = var0.getAllHeaders();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Header var6 = var3[var5];
         if(var1 || !var6.getName().equals("Authorization") && !var6.getName().equals("Cookie")) {
            var2.append("--header \"");
            var2.append(var6.toString().trim());
            var2.append("\" ");
         }
      }

      URI var8 = var0.getURI();
      if(var0 instanceof RequestWrapper) {
         HttpRequest var9 = ((RequestWrapper)var0).getOriginal();
         if(var9 instanceof HttpUriRequest) {
            var8 = ((HttpUriRequest)var9).getURI();
         }
      }

      var2.append("\"");
      var2.append(var8);
      var2.append("\"");
      if(var0 instanceof HttpEntityEnclosingRequest) {
         HttpEntityEnclosingRequest var10 = (HttpEntityEnclosingRequest)var0;
         HttpEntity var11 = var10.getEntity();
         if(var11 != null && var11.isRepeatable()) {
            if(var11.getContentLength() < 1024L) {
               ByteArrayOutputStream var12 = new ByteArrayOutputStream();
               var11.writeTo(var12);
               String var7;
               if(a(var0)) {
                  var7 = Base64.encodeToString(var12.toByteArray(), 2);
                  var2.insert(0, "echo \'" + var7 + "\' | base64 -d > /tmp/$$.bin; ");
                  var2.append(" --data-binary @/tmp/$$.bin");
               } else {
                  var7 = var12.toString();
                  var2.append(" --data-ascii \"").append(var7).append("\"");
               }
            } else {
               var2.append(" [TOO MUCH DATA TO INCLUDE]");
            }
         }
      }

      return var2.toString();
   }

   private static boolean a(HttpUriRequest var0) {
      Header[] var1 = var0.getHeaders("content-encoding");
      Header[] var2;
      int var3;
      int var4;
      Header var5;
      if(var1 != null) {
         var2 = var1;
         var3 = var1.length;

         for(var4 = 0; var4 < var3; ++var4) {
            var5 = var2[var4];
            if("gzip".equalsIgnoreCase(var5.getValue())) {
               return true;
            }
         }
      }

      var1 = var0.getHeaders("content-type");
      if(var1 != null) {
         var2 = var1;
         var3 = var1.length;

         for(var4 = 0; var4 < var3; ++var4) {
            var5 = var2[var4];
            String[] var6 = b;
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
               String var9 = var6[var8];
               if(var5.getValue().startsWith(var9)) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   // $FF: synthetic method
   static HttpRequestInterceptor b() {
      return c;
   }

   // $FF: synthetic method
   static b a(AndroidHttpClient var0) {
      return var0.f;
   }

   // $FF: synthetic method
   static String a(HttpUriRequest var0, boolean var1) {
      return b(var0, var1);
   }

}
