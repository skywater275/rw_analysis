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
package com.corrodinggames.rts.gameFramework.network;


import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.MasterServerUpdate;
import com.corrodinggames.rts.gameFramework.network.MasterServerClient;
import com.corrodinggames.rts.gameFramework.network.GameModeEnum;
import com.corrodinggames.rts.gameFramework.network.SecurityHasher;
import com.corrodinggames.rts.gameFramework.network.GameServerInfo;
import com.corrodinggames.rts.gameFramework.network.UnitTrait$1;
import com.corrodinggames.rts.gameFramework.network.SelfInfoFetcher;
import com.corrodinggames.rts.gameFramework.network.ServerListLoader;
import com.corrodinggames.rts.gameFramework.network.HttpClientPool;
import com.corrodinggames.rts.gameFramework.network.PacketSerializer;
import com.corrodinggames.rts.gameFramework.network.TextStreamReader;
import com.corrodinggames.rts.gameFramework.network.ServerListFetcher;
import com.corrodinggames.rts.gameFramework.network.ErrorReporter;
import com.corrodinggames.rts.gameFramework.network.ServerResult;
import com.corrodinggames.rts.gameFramework.network.MasterServerCreate;
import com.corrodinggames.rts.gameFramework.network.MasterServerRemove;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

public class WebAPIClient {
    public static boolean a = true;
    public static boolean b = true;
    public static String[] c = new String[]{"http://gs1.corrodinggames.com/masterserver/1.4", "http://gs4.corrodinggames.net/masterserver/1.4"};
    public static HttpClientPool d = new HttpClientPool();  // 02b j/n.java L51: r d (j/r=HttpClientPool)
    static int e;
    public static Object f = new Object();  // 02b L53
    public static String g;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */

    public static void a(String string) {
        if (b) {
            GlobalState.e(string);
        }
    }

    static void a(List list, boolean bl, ServerListCallback s2) {  // 02b j/s
        WebAPIClient.a(list, bl, s2, c);
    }

    static void a(List list, boolean bl, ServerListCallback s2, String[] stringArray) {
        s2.f = stringArray.length;
        int n2 = 0;
        for (String string : stringArray) {
            RequestsParallelRunnable u2 = new RequestsParallelRunnable(list, s2, string, bl, ++n2);  // 02b j/u
            new Thread(u2).start();
            if (!a) continue;
            GlobalState.b("LoadFromMasterServer", n2 + ": Started RequestsParallelRunnable thread");
        }
    }

    public static String a(List list, String string) {
        if (list != null) {
            for (NameValuePair nameValuePair : (java.util.Collection<NameValuePair>) (java.util.Collection) list) {
                if (!string.equals(nameValuePair.getName())) continue;
                return nameValuePair.getValue();
            }
        }
        return null;
    }

    public static BufferedReader a(List list) throws IOException {
        return WebAPIClient.a(list, true, c, 10, true);
    }

    public static BufferedReader a(List list, int n2) throws IOException {
        return WebAPIClient.a(list, true, c, n2, true);
    }

    public static BufferedReader a(List list, boolean bl, String[] stringArray, int n2, boolean bl2) throws IOException {
        String string = WebAPIClient.a(list, "action");
        ExecutorService executorService = Executors.newFixedThreadPool(stringArray.length);
        boolean bl3 = bl2;
        try {
            Object var13_18 = null;
            Object object;
            ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(executorService);
            ArrayList arrayList = new ArrayList();
            for (String object32 : stringArray) {
                List list2 = list;
                WebAPIClient$1 webAPIClient$1 = new WebAPIClient$1(object32, list2, bl, bl3);  // 02b L123: n$1 var15
                object = executorCompletionService.submit(webAPIClient$1);  // 02b L124: submit(var15)
                arrayList.add(object);
            }
            int n3 = stringArray.length;
            Object object4 = null;
            Object object5 = null;
            Object var13_17 = null;
            for (int i = 0; i < n3; ++i) {
                try {
                    java.util.concurrent.Future future = executorCompletionService.poll(10L, TimeUnit.SECONDS);  // 02b L135: Future var29
                    if (future == null) {
                        GlobalState.b("MULTI_MASTERSERVERS: poll timed out (" + string + ")");
                        break;
                    }
                    object = (MasterServerResult)future.get();
                    if (object == null) continue;
                    object4 = object;
                    if (!((MasterServerResult)object).b) continue;
                    if (((MasterServerResult)object).c) {
                        var13_18 = object;
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
                GlobalState.b("All masterserver results included an error message (" + string + ")");
                object5 = var13_18;
            }
            if (object5 == null) {
                GlobalState.b("No valid result found on any masterserver (" + string + ")");
                object5 = object4;
            }
            if (object5 != null) {
                BufferedReader bufferedReader = ((MasterServerResult)object5).a;
                return bufferedReader;
            }
            throw new IOException("No results found (" + string + ")");
        }
        finally {
            executorService.shutdown();
        }
    }

    public static MasterServerResult a(List list, String string, boolean bl) throws IOException, UnsupportedEncodingException {  // 02b j/t
        Object object;
        int n2;
        HttpResponse httpResponse;
        HttpPost httpPost;
        Object object2;
        String string2 = WebAPIClient.a(list, "action");
        long l2 = ExtraManager.a();
        String string3 = string + "/interface";
        if (bl) {
            object2 = new HttpPost(string3);
            ((HttpPost)object2).setEntity(new UrlEncodedFormEntity(list));  // 02b L192: var8.setEntity
        } else {
            string3 = string3 + "?" + URLEncodedUtils.format((List)list, (String)"utf-8");
            object2 = new HttpGet(string3);
        }
        String userAgent = "rw ";  // 02b L200: var27
        userAgent = GlobalState.ax() ? userAgent + "server" : userAgent + (GlobalState.av() ? "pc" : "android");
        String string4 = com.corrodinggames.rts.gameFramework.steam.Localization.c();  // 02b h/a.c()
        GlobalState l3 = GlobalState.B();
        if (l3 != null) {
            userAgent = userAgent + " " + l3.c(true) + " " + string4;
        }
        ((HttpUriRequest)object2).setHeader("User-Agent", userAgent);  // 02b L213: ((HttpUriRequest)var6)
        ((HttpUriRequest)object2).setHeader("Language", string4);
        HttpClient httpClient = d.a();  // 02b L215: var28
        try {
            httpResponse = httpClient.execute((HttpUriRequest)object2);
        }
        catch (NullPointerException nullPointerException) {
            GlobalState.b("doRequest: httpclient.execute threw NullPointerException, running workaround");
            httpClient = d.b();
            httpResponse = httpClient.execute((HttpUriRequest)object2);
        }
        float f2 = ExtraManager.a(l2);
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
        MasterServerResult t2 = new MasterServerResult();  // 02b j/t
        String string6 = WebAPIClient.a(byArray2);
        t2.b = string6.startsWith(string5);
        t2.c = string6.contains("[FAILED]");
        if (!t2.b || t2.c) {
            object = string3 + (string2 != null ? "?action=" + string2 : "") + " (" + f2 + "ms)";
            if (!"list".equals(string2)) {
                object = (String)object + ":\n" + new String(byArray2);
            }
            GlobalState.e((String)object);
        }
        object = new ByteArrayInputStream(byArray2);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream)object));
        d.a(httpClient);
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

    public static GameServerInfo b(String string) throws IOException {  // 02b j/g
        GlobalState l2 = GlobalState.B();  // l 为幻觉类型
        if (string == null) {
            throw new IOException("findOrCreateServer id cannot be null");
        }
        for (GameServerInfo g2 : l2.bX.bi) {
            if (!string.equals(g2.b)) continue;
            return g2;
        }
        return null;
    }

    public static GameServerInfo c(String string) throws IOException {
        GlobalState l2 = GlobalState.B();  // l 为幻觉类型
        if (string == null) {
            throw new IOException("findOrCreateServer id cannot be null");
        }
        GameServerInfo g2 = WebAPIClient.b(string);
        if (g2 != null) {
            return g2;
        }
        GameServerInfo g3 = new GameServerInfo();
        g3.b = string;
        g3.a = false;
        g3.o = l2.bX.p();
        return g3;
    }

    public static void a(Runnable runnable) {
        GlobalState.b("LoadFromMasterServer", "Load requested");
        ServerListLoader q2 = new ServerListLoader();
        q2.q(runnable);  // 02b j/q (03 用 q() 方法)
        Thread thread = new Thread(q2);
        thread.start();
    }

    static void a(int n2, int n3) {
        GlobalState l2 = GlobalState.B();  // l 为幻觉类型
        boolean bl = false;
        Object object = f;
        synchronized (object) {
            Iterator iterator = l2.bX.bi.iterator();
            while (iterator.hasNext()) {
                GameServerInfo g2 = (GameServerInfo)iterator.next();
                if (g2.p >= n2) continue;
                GlobalState.b("LoadFromMasterServer", n3 + ": Removing stale server with id:" + g2.b);
                iterator.remove();
                bl = true;
            }
        }
        if (bl) {
            com.corrodinggames.rts.appFramework.p.l();
        }
    }

    public static void a() {
        GlobalState.b("GetOwnInfoRunnable", "getOwnInfoFromMasterServer");
        MasterServerAuth.e = 6;  // 02b j/aq
        SelfInfoFetcher p2 = new SelfInfoFetcher();  // 02b j/p
        Thread thread = new Thread(p2);
        thread.start();
    }

    static void a(List list, String string, String string2) {
        list.add(new BasicNameValuePair(string, string2));
    }

    static void b(List list) throws IOException, UnsupportedEncodingException {
        String string;
        GlobalState l2 = GlobalState.B();  // l 为幻觉类型
        WebAPIClient.a(list, "password_required", l2.bX.n != null);  // ActionPanel 为幻觉方法
        WebAPIClient.a(list, "created_by", l2.bX.y);
        WebAPIClient.a(list, "private_ip", l2.bX.ah());
        WebAPIClient.a(list, "port_number", Integer.toString(l2.bX.m));
        if (l2.bX.u != null) {
            WebAPIClient.a(list, "game_map", com.corrodinggames.rts.gameFramework.filesystem.FileLoader.n(l2.bX.u));  // 02b e/a.b.n (e/c=FileLoader)
        } else {
            WebAPIClient.a(list, "game_map", com.corrodinggames.rts.gameFramework.filesystem.FileLoader.n(l2.bX.ay.b));
        }
        GameModeEnum ai2 = l2.bX.ay.a;  // 02b j/ai
        if (ai2 == null) {
            ai2 = GameModeEnum.a;
        }
        WebAPIClient.a(list, "game_mode", ai2.name());
        if (!l2.bX.v) {
            string = l2.bX.aW ? "ingame" : (l2.bX.ay.tournamentMode ? "locked" : "battleroom");  // 03 MatchConfig 字段名 (02b p)
            WebAPIClient.a(list, "game_status", string);
        } else {
            WebAPIClient.a(list, "game_status", "chat");
        }
        WebAPIClient.a(list, "player_count", Integer.toString(l2.bX.E()));
        string = Integer.toString(com.corrodinggames.rts.game.n.c);
        if (l2.bX.v) {
            // empty if block
        }
        WebAPIClient.a(list, "max_player_count", string);
    }

    public static void b() {
        GlobalState.b("StartCreateOnMasterServer", "Create requested");
        MasterServerAuth.b = 5;
        MasterServerCreate y2 = new MasterServerCreate();  // 02b j/y
        Thread thread = new Thread(y2);
        thread.start();
    }

    public static void c() {
        MasterServerUpdate aa2 = new MasterServerUpdate();  // 02b j/aa
        Thread thread = new Thread(aa2);
        thread.start();
    }

    public static void d() {
        GlobalState.b("startRemoveOnMasterServer", "Remove requested");
        MasterServerRemove z2 = new MasterServerRemove();  // 02b j/z
        Thread thread = new Thread(z2);
        thread.start();
    }

    public static void a(String string, String string2) {
        GlobalState.b("startErrorReport", "ErrorReport requested");
        ErrorReporter v2 = new ErrorReporter();  // 02b j/v
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
                return com.corrodinggames.rts.gameFramework.GameUtils.a(com.corrodinggames.rts.gameFramework.GameUtils.b("x" + n2), 10);  // 02b L438: f.a(f.b(...),10)
            }
            if (n2 < 200000) {
                return com.corrodinggames.rts.gameFramework.GameUtils.a(com.corrodinggames.rts.gameFramework.GameUtils.b("y" + n2), 11);
            }
            if (n2 < 300000) {
                return com.corrodinggames.rts.gameFramework.GameUtils.a(com.corrodinggames.rts.gameFramework.GameUtils.b("z" + n2), 12);
            }
            if (n2 < 1000000) {
                return com.corrodinggames.rts.gameFramework.GameUtils.a(com.corrodinggames.rts.gameFramework.GameUtils.b("xx" + n2), 13) + "-" + GlobalState.B().bX.g(n2 - 300000);
            }
            if (n2 < 2000000) {
                return com.corrodinggames.rts.gameFramework.GameUtils.a(com.corrodinggames.rts.gameFramework.GameUtils.b("yy" + n2), 14) + "-" + GlobalState.B().bX.g(n2 - 1000000);
            }
        }
        return "NA";
    }

    public static void a(ServerResult w2, String string, int n2, String string2) {  // 02b j/w
        GlobalState.e("getGameServerInfoFromMasterServer");
        MasterServerClient ab2 = new MasterServerClient();  // 02b j/ab
        ab2.a = w2;
        ab2.b = string;
        ab2.c = n2;
        ab2.d = string2;
        Thread thread = new Thread(ab2);
        thread.start();
    }
}
