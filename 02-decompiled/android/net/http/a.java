/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.protocol.HttpContext
 */
package android.net.http;

import android.net.http.AndroidHttpClient;
import android.net.http.b;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

class a
implements HttpRequestInterceptor {
    final /* synthetic */ AndroidHttpClient a;

    private a(AndroidHttpClient androidHttpClient) {
        this.a = androidHttpClient;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        b b2 = AndroidHttpClient.a(this.a);
        if (b2 != null && b.a(b2) && httpRequest instanceof HttpUriRequest) {
            b.a(b2, AndroidHttpClient.a((HttpUriRequest)httpRequest, false));
        }
    }
}
