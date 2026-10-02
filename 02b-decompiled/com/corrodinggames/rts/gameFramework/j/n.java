package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.br;
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
import java.util.concurrent.Future;
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
   public static Object f = new Object();
   public static String g;


   public static void a(String var0) {
      if(b) {
         com.corrodinggames.rts.gameFramework.l.e(var0);
      }

   }

   static void a(List var0, boolean var1, s var2) {
      a(var0, var1, var2, c);
   }

   static void a(List var0, boolean var1, s var2, String[] var3) {
      var2.f = var3.length;
      int var4 = 0;
      String[] var5 = var3;
      int var6 = var3.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         String var8 = var5[var7];
         ++var4;
         u var9 = new u(var0, var2, var8, var1, var4);
         (new Thread(var9)).start();
         if(a) {
            com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", var4 + ": Started RequestsParallelRunnable thread");
         }
      }

   }

   public static String a(List var0, String var1) {
      if(var0 != null) {
         Iterator var2 = var0.iterator();

         while(var2.hasNext()) {
            NameValuePair var3 = (NameValuePair)var2.next();
            if(var1.equals(var3.getName())) {
               return var3.getValue();
            }
         }
      }

      return null;
   }

   public static BufferedReader a(List var0) {
      return a(var0, true, c, 10, true);
   }

   public static BufferedReader a(List var0, int var1) {
      return a(var0, true, c, var1, true);
   }

   public static BufferedReader a(List var0, boolean var1, String[] var2, int var3, boolean var4) {
      String var5 = a(var0, "action");
      ExecutorService var6 = Executors.newFixedThreadPool(var2.length);
      boolean var7 = var4;

      BufferedReader var28;
      try {
         ExecutorCompletionService var8 = new ExecutorCompletionService(var6);
         ArrayList var9 = new ArrayList();
         String[] var10 = var2;
         int var11 = var2.length;

         for(int var12 = 0; var12 < var11; ++var12) {
            String var13 = var10[var12];
            n$1 var15 = new n$1(var13, var0, var1, var7);
            Future var16 = var8.submit(var15);
            var9.add(var16);
         }

         int var24 = var2.length;
         t var25 = null;
         t var26 = null;
         t var27 = null;

         for(int var14 = 0; var14 < var24; ++var14) {
            try {
               Future var29 = var8.poll(10L, TimeUnit.SECONDS);
               if(var29 == null) {
                  com.corrodinggames.rts.gameFramework.l.b("MULTI_MASTERSERVERS: poll timed out (" + var5 + ")");
                  break;
               }

               t var30 = (t)var29.get();
               if(var30 != null) {
                  var25 = var30;
                  if(var30.b) {
                     if(!var30.c) {
                        var26 = var30;
                        break;
                     }

                     var27 = var30;
                  }
               }
            } catch (ExecutionException var21) {
               var21.printStackTrace();
               if(var21.getCause() != null) {
                  var21.getCause().printStackTrace();
               }
            } catch (InterruptedException var22) {
               ;
            }
         }

         if(var26 == null && var27 != null) {
            com.corrodinggames.rts.gameFramework.l.b("All masterserver results included an error message (" + var5 + ")");
            var26 = var27;
         }

         if(var26 == null) {
            com.corrodinggames.rts.gameFramework.l.b("No valid result found on any masterserver (" + var5 + ")");
            var26 = var25;
         }

         if(var26 == null) {
            throw new IOException("No results found (" + var5 + ")");
         }

         var28 = var26.a;
      } finally {
         var6.shutdown();
      }

      return var28;
   }

   public static t a(List var0, String var1, boolean var2) {
      String var3 = a(var0, "action");
      long var4 = br.a();
      String var7 = var1 + "/interface";
      Object var6;
      if(var2) {
         HttpPost var8 = new HttpPost(var7);
         var8.setEntity(new UrlEncodedFormEntity(var0));
         var6 = var8;
      } else {
         var7 = var7 + "?" + URLEncodedUtils.format(var0, "utf-8");
         HttpGet var26 = new HttpGet(var7);
         var6 = var26;
      }

      String var27 = "rw ";
      if(com.corrodinggames.rts.gameFramework.l.ax()) {
         var27 = var27 + "server";
      } else {
         var27 = var27 + (com.corrodinggames.rts.gameFramework.l.av()?"pc":"android");
      }

      String var9 = com.corrodinggames.rts.gameFramework.h.a.c();
      com.corrodinggames.rts.gameFramework.l var10 = com.corrodinggames.rts.gameFramework.l.B();
      if(var10 != null) {
         var27 = var27 + " " + var10.c(true) + " " + var9;
      }

      ((HttpUriRequest)var6).setHeader("User-Agent", var27);
      ((HttpUriRequest)var6).setHeader("Language", var9);
      HttpClient var28 = d.a();

      HttpResponse var11;
      try {
         var11 = var28.execute((HttpUriRequest)var6);
      } catch (NullPointerException var24) {
         com.corrodinggames.rts.gameFramework.l.b("doRequest: httpclient.execute threw NullPointerException, running workaround");
         var28 = d.b();
         var11 = var28.execute((HttpUriRequest)var6);
      }

      float var12 = br.a(var4);
      HttpEntity var13 = var11.getEntity();
      InputStream var14 = var13.getContent();
      ByteArrayOutputStream var15 = new ByteArrayOutputStream();
      byte[] var17 = new byte[16384];

      int var16;
      while((var16 = var14.read(var17, 0, var17.length)) != -1) {
         var15.write(var17, 0, var16);
      }

      var15.flush();
      var14.close();
      var13.consumeContent();
      byte[] var18 = var15.toByteArray();
      String var19 = "CORRODINGGAMES";
      t var20 = new t();
      String var21 = a(var18);
      var20.b = var21.startsWith(var19);
      var20.c = var21.contains("[FAILED]");
      if(!var20.b || var20.c) {
         String var22 = var7 + (var3 != null?"?action=" + var3:"") + " (" + var12 + "ms)";
         if(!"list".equals(var3)) {
            var22 = var22 + ":\n" + new String(var18);
         }

         com.corrodinggames.rts.gameFramework.l.e(var22);
      }

      ByteArrayInputStream var25 = new ByteArrayInputStream(var18);
      BufferedReader var23 = new BufferedReader(new InputStreamReader(var25));
      d.a(var28);
      var20.a = var23;
      return var20;
   }

   public static String a(byte[] var0) {
      int var1 = var0.length;

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if(var0[var2] == 10 || var0[var2] == 13) {
            var1 = var2;
            break;
         }
      }

      String var3 = new String(var0, 0, var1);
      return var3;
   }

   public static g b(String var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var0 == null) {
         throw new IOException("findOrCreateServer id cannot be null");
      } else {
         Iterator var2 = var1.bX.bi.iterator();

         g var3;
         do {
            if(!var2.hasNext()) {
               return null;
            }

            var3 = (g)var2.next();
         } while(!var0.equals(var3.b));

         return var3;
      }
   }

   public static g c(String var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var0 == null) {
         throw new IOException("findOrCreateServer id cannot be null");
      } else {
         g var2 = b(var0);
         if(var2 != null) {
            return var2;
         } else {
            g var3 = new g();
            var3.b = var0;
            var3.a = false;
            var3.o = var1.bX.p();
            return var3;
         }
      }
   }

   public static void a(Runnable var0) {
      com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", "Load requested");
      q var1 = new q(var0);
      Thread var2 = new Thread(var1);
      var2.start();
   }

   static void a(int var0, int var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var3 = false;
      Object var4 = f;
      synchronized(f) {
         Iterator var5 = var2.bX.bi.iterator();

         while(true) {
            if(!var5.hasNext()) {
               break;
            }

            g var6 = (g)var5.next();
            if(var6.p < var0) {
               com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", var1 + ": Removing stale server with id:" + var6.b);
               var5.remove();
               var3 = true;
            }
         }
      }

      if(var3) {
         com.corrodinggames.rts.appFramework.p.l();
      }

   }

   public static void a() {
      com.corrodinggames.rts.gameFramework.l.b("GetOwnInfoRunnable", "getOwnInfoFromMasterServer");
      aq.e = 6;
      p var0 = new p();
      Thread var1 = new Thread(var0);
      var1.start();
   }

   static void a(List var0, String var1, String var2) {
      var0.add(new BasicNameValuePair(var1, var2));
   }

   static void b(List var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      a(var0, "password_required", com.corrodinggames.rts.gameFramework.f.a(var1.bX.n != null));
      a(var0, "created_by", var1.bX.y);
      a(var0, "private_ip", var1.bX.ah());
      a(var0, "port_number", Integer.toString(var1.bX.m));
      if(var1.bX.u != null) {
         a(var0, "game_map", com.corrodinggames.rts.gameFramework.e.a.o(var1.bX.u));
      } else {
         a(var0, "game_map", com.corrodinggames.rts.gameFramework.e.a.o(var1.bX.ay.b));
      }

      ai var2 = var1.bX.ay.a;
      if(var2 == null) {
         var2 = ai.a;
      }

      a(var0, "game_mode", var2.name());
      String var3;
      if(!var1.bX.v) {
         if(var1.bX.aW) {
            var3 = "ingame";
         } else if(var1.bX.ay.p) {
            var3 = "locked";
         } else {
            var3 = "battleroom";
         }

         a(var0, "game_status", var3);
      } else {
         a(var0, "game_status", "chat");
      }

      a(var0, "player_count", Integer.toString(var1.bX.E()));
      var3 = Integer.toString(com.corrodinggames.rts.game.n.c);
      if(var1.bX.v) {
         ;
      }

      a(var0, "max_player_count", var3);
   }

   public static void b() {
      com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "Create requested");
      aq.b = 5;
      y var0 = new y();
      Thread var1 = new Thread(var0);
      var1.start();
   }

   public static void c() {
      aa var0 = new aa();
      Thread var1 = new Thread(var0);
      var1.start();
   }

   public static void d() {
      com.corrodinggames.rts.gameFramework.l.b("startRemoveOnMasterServer", "Remove requested");
      z var0 = new z();
      Thread var1 = new Thread(var0);
      var1.start();
   }

   public static void a(String var0, String var1) {
      com.corrodinggames.rts.gameFramework.l.b("startErrorReport", "ErrorReport requested");
      v var2 = new v();
      var2.b = var1;
      var2.a = var0;
      Thread var3 = new Thread(var2);
      var3.start();
   }

   public static String a(int var0) {
      if(var0 == 0) {
         return "";
      } else {
         if(var0 > 0) {
            if(var0 < 100000) {
               return com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.f.b("x" + var0), (int)10);
            }

            if(var0 < 200000) {
               return com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.f.b("y" + var0), (int)11);
            }

            if(var0 < 300000) {
               return com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.f.b("z" + var0), (int)12);
            }

            if(var0 < 1000000) {
               return com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.f.b("xx" + var0), (int)13) + "-" + com.corrodinggames.rts.gameFramework.l.B().bX.g(var0 - 300000);
            }

            if(var0 < 2000000) {
               return com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.f.b("yy" + var0), (int)14) + "-" + com.corrodinggames.rts.gameFramework.l.B().bX.g(var0 - 1000000);
            }
         }

         return "NA";
      }
   }

   public static void a(w var0, String var1, int var2, String var3) {
      com.corrodinggames.rts.gameFramework.l.e("getGameServerInfoFromMasterServer");
      ab var4 = new ab();
      var4.a = var0;
      var4.b = var1;
      var4.c = var2;
      var4.d = var3;
      Thread var5 = new Thread(var4);
      var5.start();
   }

}
