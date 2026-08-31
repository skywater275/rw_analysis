/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.net.SSLCertificateSocketFactory
 *  android.net.SSLSessionCache
 *  android.util.Base64
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.ResponseHandler
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.client.params.HttpClientParams
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.scheme.PlainSocketFactory
 *  org.apache.http.conn.scheme.Scheme
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.conn.scheme.SocketFactory
 *  org.apache.http.impl.client.RequestWrapper
 *  org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpProtocolParams
 *  org.apache.http.protocol.HttpContext
 */
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
import java.io.OutputStream;
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
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public final class AndroidHttpClient
implements HttpClient {
    public static long a = 256L;
    private static String[] b = new String[]{"text/", "application/xml", "application/json"};
    private static final HttpRequestInterceptor c = new AndroidHttpClient$1();
    private final HttpClient d;
    private RuntimeException e = new IllegalStateException("AndroidHttpClient created and never closed");
    private volatile b f;

    public static AndroidHttpClient a(String string, Context context) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled((HttpParams)basicHttpParams, (boolean)false);
        HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, (int)60000);
        HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, (int)60000);
        HttpConnectionParams.setSocketBufferSize((HttpParams)basicHttpParams, (int)8192);
        HttpClientParams.setRedirecting((HttpParams)basicHttpParams, (boolean)false);
        SSLSessionCache sSLSessionCache = context == null ? null : new SSLSessionCache(context);
        HttpProtocolParams.setUserAgent((HttpParams)basicHttpParams, (String)string);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", (SocketFactory)SSLCertificateSocketFactory.getHttpSocketFactory((int)60000, (SSLSessionCache)sSLSessionCache), 443));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager((HttpParams)basicHttpParams, schemeRegistry);
        return new AndroidHttpClient((ClientConnectionManager)threadSafeClientConnManager, (HttpParams)basicHttpParams);
    }

    public static AndroidHttpClient a(String string) {
        return AndroidHttpClient.a(string, null);
    }

    private AndroidHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.d = new AndroidHttpClient$2(this, clientConnectionManager, httpParams);
    }

    protected void finalize() {
        super.finalize();
        if (this.e != null) {
            Log.b("AndroidHttpClient", "Leak found", this.e);
            this.e = null;
        }
    }

    public void a() {
        if (this.e != null) {
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

    public HttpResponse execute(HttpUriRequest httpUriRequest) {
        return this.d.execute(httpUriRequest);
    }

    public HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        return this.d.execute(httpUriRequest, httpContext);
    }

    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) {
        return this.d.execute(httpHost, httpRequest);
    }

    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return this.d.execute(httpHost, httpRequest, httpContext);
    }

    public Object execute(HttpUriRequest httpUriRequest, ResponseHandler responseHandler) {
        return this.d.execute(httpUriRequest, responseHandler);
    }

    public Object execute(HttpUriRequest httpUriRequest, ResponseHandler responseHandler, HttpContext httpContext) {
        return this.d.execute(httpUriRequest, responseHandler, httpContext);
    }

    public Object execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler responseHandler) {
        return this.d.execute(httpHost, httpRequest, responseHandler);
    }

    public Object execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler responseHandler, HttpContext httpContext) {
        return this.d.execute(httpHost, httpRequest, responseHandler, httpContext);
    }

    /*
     * WARNING - void declaration
     */
    private static String b(HttpUriRequest httpUriRequest, boolean bl) {
        HttpEntityEnclosingRequest httpEntityEnclosingRequest;
        HttpEntity httpEntity;
        void var3_6;
        HttpRequest httpRequest;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("curl ");
        stringBuilder.append("-X ");
        stringBuilder.append(httpUriRequest.getMethod());
        stringBuilder.append(" ");
        for (Header header : httpUriRequest.getAllHeaders()) {
            if (!bl && (header.getName().equals("Authorization") || header.getName().equals("Cookie"))) continue;
            stringBuilder.append("--header \"");
            stringBuilder.append(header.toString().trim());
            stringBuilder.append("\" ");
        }
        URI uRI = httpUriRequest.getURI();
        if (httpUriRequest instanceof RequestWrapper && (httpRequest = ((RequestWrapper)httpUriRequest).getOriginal()) instanceof HttpUriRequest) {
            URI uRI2 = ((HttpUriRequest)httpRequest).getURI();
        }
        stringBuilder.append("\"");
        stringBuilder.append(var3_6);
        stringBuilder.append("\"");
        if (httpUriRequest instanceof HttpEntityEnclosingRequest && (httpEntity = (httpEntityEnclosingRequest = (HttpEntityEnclosingRequest)httpUriRequest).getEntity()) != null && httpEntity.isRepeatable()) {
            if (httpEntity.getContentLength() < 1024L) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                httpEntity.writeTo((OutputStream)byteArrayOutputStream);
                if (AndroidHttpClient.a(httpUriRequest)) {
                    String string = Base64.encodeToString((byte[])byteArrayOutputStream.toByteArray(), (int)2);
                    stringBuilder.insert(0, "echo '" + string + "' | base64 -d > /tmp/$$.bin; ");
                    stringBuilder.append(" --data-binary @/tmp/$$.bin");
                } else {
                    String string = byteArrayOutputStream.toString();
                    stringBuilder.append(" --data-ascii \"").append(string).append("\"");
                }
            } else {
                stringBuilder.append(" [TOO MUCH DATA TO INCLUDE]");
            }
        }
        return stringBuilder.toString();
    }

    private static boolean a(HttpUriRequest httpUriRequest) {
        Header[] headerArray = httpUriRequest.getHeaders("content-encoding");
        if (headerArray != null) {
            for (Header header : headerArray) {
                if (!"gzip".equalsIgnoreCase(header.getValue())) continue;
                return true;
            }
        }
        if ((headerArray = httpUriRequest.getHeaders("content-type")) != null) {
            for (Header header : headerArray) {
                for (String string : b) {
                    if (!header.getValue().startsWith(string)) continue;
                    return false;
                }
            }
        }
        return true;
    }

    static /* synthetic */ HttpRequestInterceptor b() {
        return c;
    }

    static /* synthetic */ b a(AndroidHttpClient androidHttpClient) {
        return androidHttpClient.f;
    }

    static /* synthetic */ String a(HttpUriRequest httpUriRequest, boolean bl) {
        return AndroidHttpClient.b(httpUriRequest, bl);
    }
}
