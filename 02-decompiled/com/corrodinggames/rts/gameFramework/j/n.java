/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.NameValuePair
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.entity.UrlEncodedFormEntity
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.client.utils.URLEncodedUtils
 *  org.apache.http.message.BasicNameValuePair
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.aa;
import com.corrodinggames.rts.gameFramework.j.ab;
import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.aq;
import com.corrodinggames.rts.gameFramework.j.g;
import com.corrodinggames.rts.gameFramework.j.n$1;
import com.corrodinggames.rts.gameFramework.j.p;
import com.corrodinggames.rts.gameFramework.j.q;
import com.corrodinggames.rts.gameFramework.j.r;
import com.corrodinggames.rts.gameFramework.j.s;
import com.corrodinggames.rts.gameFramework.j.t;
import com.corrodinggames.rts.gameFramework.j.u;
import com.corrodinggames.rts.gameFramework.j.v;
import com.corrodinggames.rts.gameFramework.j.w;
import com.corrodinggames.rts.gameFramework.j.y;
import com.corrodinggames.rts.gameFramework.j.z;
import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class n {
    public static boolean a = true;
    public static boolean b = true;
    public static String[] c = new String[]{"http://gs1.corrodinggames.com/masterserver/1.4", "http://gs4.corrodinggames.net/masterserver/1.4"};
    public static r d = new r();
    static int e;
    public static Object f;
    public static String g;

    public static void a(String string) {
        if (b) {
            l.e(string);
        }
    }

    static void a(List list, boolean bl, s s2) {
        n.a(list, bl, s2, c);
    }

    static void a(List list, boolean bl, s s2, String[] stringArray) {
        s2.f = stringArray.length;
        int n2 = 0;
        for (String string : stringArray) {
            u u2 = new u(list, s2, string, bl, ++n2);
            new Thread(u2).start();
            if (!a) continue;
            l.b("LoadFromMasterServer", n2 + ": Started RequestsParallelRunnable thread");
        }
    }

    public static String a(List list, String string) {
        if (list != null) {
            for (NameValuePair nameValuePair : list) {
                if (!string.equals(nameValuePair.getName())) continue;
                return nameValuePair.getValue();
            }
        }
        return null;
    }

    public static BufferedReader a(List list) {
        return n.a(list, true, c, 10, true);
    }

    public static BufferedReader a(List list, int n2) {
        return n.a(list, true, c, n2, true);
    }

    /*
     * WARNING - void declaration
     */
    public static BufferedReader a(List list, boolean bl, String[] stringArray, int n2, boolean bl2) {
        String string = n.a(list, "action");
        ExecutorService executorService = Executors.newFixedThreadPool(stringArray.length);
        boolean bl3 = bl2;
        try {
            void var13_18;
            Object object;
            Object object2;
            ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(executorService);
            ArrayList arrayList = new ArrayList();
            for (String object32 : stringArray) {
                List list2 = list;
                object2 = new n$1(object32, list2, bl, bl3);
                object = executorCompletionService.submit(object2);
                arrayList.add(object);
            }
            int n3 = stringArray.length;
            Object object4 = null;
            Object object5 = null;
            Object var13_17 = null;
            for (int i = 0; i < n3; ++i) {
                try {
                    object2 = executorCompletionService.poll(10L, TimeUnit.SECONDS);
                    if (object2 == null) {
                        l.b("MULTI_MASTERSERVERS: poll timed out (" + string + ")");
                        break;
                    }
                    object = (t)object2.get();
                    if (object == null) continue;
                    object4 = object;
                    if (!((t)object).b) continue;
                    if (((t)object).c) {
                        Object object3 = object;
                        continue;
                    }
                    object5 = object;
                    break;
                }
                catch (ExecutionException executionException) {
                    executionException.printStackTrace();
                    if (executionException.getCause() == null) continue;
                    executionException.getCause().printStackTrace();
                    continue;
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }
            if (object5 == null && var13_18 != null) {
                l.b("All masterserver results included an error message (" + string + ")");
                object5 = var13_18;
            }
            if (object5 == null) {
                l.b("No valid result found on any masterserver (" + string + ")");
                object5 = object4;
            }
            if (object5 != null) {
                BufferedReader bufferedReader = ((t)object5).a;
                return bufferedReader;
            }
            throw new IOException("No results found (" + string + ")");
        }
        finally {
            executorService.shutdown();
        }
    }

    public static t a(List list, String string, boolean bl) {
        Object object;
        int n2;
        HttpResponse httpResponse;
        HttpPost httpPost;
        Object object2;
        String string2 = n.a(list, "action");
        long l2 = br.a();
        String string3 = string + "/interface";
        if (bl) {
            object2 = new HttpPost(string3);
            object2.setEntity((HttpEntity)new UrlEncodedFormEntity(list));
            httpPost = object2;
        } else {
            string3 = string3 + "?" + URLEncodedUtils.format((List)list, (String)"utf-8");
            httpPost = object2 = new HttpGet(string3);
        }
        object2 = "rw ";
        object2 = l.ax() ? (String)object2 + "server" : (String)object2 + (l.av() ? "pc" : "android");
        String string4 = com.corrodinggames.rts.gameFramework.h.a.c();
        l l3 = l.B();
        if (l3 != null) {
            object2 = (String)object2 + " " + l3.c(true) + " " + string4;
        }
        httpPost.setHeader("User-Agent", (String)object2);
        httpPost.setHeader("Language", string4);
        l3 = d.a();
        try {
            httpResponse = l3.execute((HttpUriRequest)httpPost);
        }
        catch (NullPointerException nullPointerException) {
            l.b("doRequest: httpclient.execute threw NullPointerException, running workaround");
            l3 = d.b();
            httpResponse = l3.execute((HttpUriRequest)httpPost);
        }
        float f2 = br.a(l2);
        HttpEntity httpEntity = httpResponse.getEntity();
        InputStream inputStream = httpEntity.getContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byArray = new byte[16384];
        while ((n2 = inputStream.read(byArray, 0, byArray.length)) != -1) {
            byteArrayOutputStream.write(byArray, 0, n2);
        }
        byteArrayOutputStream.flush();
        inputStream.close();
        httpEntity.consumeContent();
        byte[] byArray2 = byteArrayOutputStream.toByteArray();
        String string5 = "CORRODINGGAMES";
        t t2 = new t();
        String string6 = n.a(byArray2);
        t2.b = string6.startsWith(string5);
        t2.c = string6.contains("[FAILED]");
        if (!t2.b || t2.c) {
            object = string3 + (string2 != null ? "?action=" + string2 : "") + " (" + f2 + "ms)";
            if (!"list".equals(string2)) {
                object = (String)object + ":\n" + new String(byArray2);
            }
            l.e((String)object);
        }
        object = new ByteArrayInputStream(byArray2);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream)object));
        d.a((HttpClient)l3);
        t2.a = bufferedReader;
        return t2;
    }

    public static String a(byte[] byArray) {
        int n2 = byArray.length;
        for (int i = 0; i < byArray.length; ++i) {
            if (byArray[i] != 10 && byArray[i] != 13) continue;
            n2 = i;
            break;
        }
        String string = new String(byArray, 0, n2);
        return string;
    }

    public static g b(String string) {
        l l2 = l.B();
        if (string == null) {
            throw new IOException("findOrCreateServer id cannot be null");
        }
        for (g g2 : l2.bX.bi) {
            if (!string.equals(g2.b)) continue;
            return g2;
        }
        return null;
    }

    public static g c(String string) {
        l l2 = l.B();
        if (string == null) {
            throw new IOException("findOrCreateServer id cannot be null");
        }
        g g2 = n.b(string);
        if (g2 != null) {
            return g2;
        }
        g g3 = new g();
        g3.b = string;
        g3.a = false;
        g3.o = l2.bX.p();
        return g3;
    }

    public static void a(Runnable runnable) {
        l.b("LoadFromMasterServer", "Load requested");
        q q2 = new q(runnable);
        Thread thread = new Thread(q2);
        thread.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void a(int n2, int n3) {
        l l2 = l.B();
        boolean bl = false;
        Object object = f;
        synchronized (object) {
            Iterator iterator = l2.bX.bi.iterator();
            while (iterator.hasNext()) {
                g g2 = (g)iterator.next();
                if (g2.p >= n2) continue;
                l.b("LoadFromMasterServer", n3 + ": Removing stale server with id:" + g2.b);
                iterator.remove();
                bl = true;
            }
        }
        if (bl) {
            com.corrodinggames.rts.appFramework.p.l();
        }
    }

    public static void a() {
        l.b("GetOwnInfoRunnable", "getOwnInfoFromMasterServer");
        aq.e = 6;
        p p2 = new p();
        Thread thread = new Thread(p2);
        thread.start();
    }

    static void a(List list, String string, String string2) {
        list.add(new BasicNameValuePair(string, string2));
    }

    static void b(List list) {
        String string;
        l l2 = l.B();
        n.a(list, "password_required", com.corrodinggames.rts.gameFramework.f.a(l2.bX.n != null));
        n.a(list, "created_by", l2.bX.y);
        n.a(list, "private_ip", l2.bX.ah());
        n.a(list, "port_number", Integer.toString(l2.bX.m));
        if (l2.bX.u != null) {
            n.a(list, "game_map", com.corrodinggames.rts.gameFramework.e.a.o(l2.bX.u));
        } else {
            n.a(list, "game_map", com.corrodinggames.rts.gameFramework.e.a.o(l2.bX.ay.b));
        }
        ai ai2 = l2.bX.ay.a;
        if (ai2 == null) {
            ai2 = ai.a;
        }
        n.a(list, "game_mode", ai2.name());
        if (!l2.bX.v) {
            string = l2.bX.aW ? "ingame" : (l2.bX.ay.p ? "locked" : "battleroom");
            n.a(list, "game_status", string);
        } else {
            n.a(list, "game_status", "chat");
        }
        n.a(list, "player_count", Integer.toString(l2.bX.E()));
        string = Integer.toString(com.corrodinggames.rts.game.n.c);
        if (l2.bX.v) {
            // empty if block
        }
        n.a(list, "max_player_count", string);
    }

    public static void b() {
        l.b("StartCreateOnMasterServer", "Create requested");
        aq.b = 5;
        y y2 = new y();
        Thread thread = new Thread(y2);
        thread.start();
    }

    public static void c() {
        aa aa2 = new aa();
        Thread thread = new Thread(aa2);
        thread.start();
    }

    public static void d() {
        l.b("startRemoveOnMasterServer", "Remove requested");
        z z2 = new z();
        Thread thread = new Thread(z2);
        thread.start();
    }

    public static void a(String string, String string2) {
        l.b("startErrorReport", "ErrorReport requested");
        v v2 = new v();
        v2.b = string2;
        v2.a = string;
        Thread thread = new Thread(v2);
        thread.start();
    }

    public static String a(int n2) {
        if (n2 == 0) {
            return "";
        }
        if (n2 > 0) {
            if (n2 < 100000) {
                return com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.f.b("x" + n2), 10);
            }
            if (n2 < 200000) {
                return com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.f.b("y" + n2), 11);
            }
            if (n2 < 300000) {
                return com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.f.b("z" + n2), 12);
            }
            if (n2 < 1000000) {
                return com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.f.b("xx" + n2), 13) + "-" + l.B().bX.g(n2 - 300000);
            }
            if (n2 < 2000000) {
                return com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.f.b("yy" + n2), 14) + "-" + l.B().bX.g(n2 - 1000000);
            }
        }
        return "NA";
    }

    public static void a(w w2, String string, int n2, String string2) {
        l.e("getGameServerInfoFromMasterServer");
        ab ab2 = new ab();
        ab2.a = w2;
        ab2.b = string;
        ab2.c = n2;
        ab2.d = string2;
        Thread thread = new Thread(ab2);
        thread.start();
    }

    static {
        f = new Object();
    }
}
