/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.BasicHttpContext
 *  org.apache.http.protocol.BasicHttpProcessor
 *  org.apache.http.protocol.HttpContext
 */
package android.net.http;

import android.net.http.AndroidHttpClient;
import android.net.http.a;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;

class AndroidHttpClient$2
extends DefaultHttpClient {
    final /* synthetic */ AndroidHttpClient a;

    AndroidHttpClient$2(AndroidHttpClient androidHttpClient, ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.a = androidHttpClient;
        super(clientConnectionManager, httpParams);
    }

    protected BasicHttpProcessor createHttpProcessor() {
        BasicHttpProcessor basicHttpProcessor = super.createHttpProcessor();
        basicHttpProcessor.addRequestInterceptor(AndroidHttpClient.b());
        basicHttpProcessor.addRequestInterceptor((HttpRequestInterceptor)new a(this.a, null));
        return basicHttpProcessor;
    }

    protected HttpContext createHttpContext() {
        BasicHttpContext basicHttpContext = new BasicHttpContext();
        basicHttpContext.setAttribute("http.authscheme-registry", (Object)this.getAuthSchemes());
        basicHttpContext.setAttribute("http.cookiespec-registry", (Object)this.getCookieSpecs());
        basicHttpContext.setAttribute("http.auth.credentials-provider", (Object)this.getCredentialsProvider());
        return basicHttpContext;
    }
}
