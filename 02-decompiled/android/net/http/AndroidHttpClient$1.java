/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.protocol.HttpContext
 */
package android.net.http;

import android.os.Looper;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

final class AndroidHttpClient$1
implements HttpRequestInterceptor {
    AndroidHttpClient$1() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        if (Looper.d() != null && Looper.d() == Looper.b()) {
            throw new RuntimeException("This thread forbids HTTP requests");
        }
    }
}
