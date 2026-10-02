package com.corrodinggames.rts.gameFramework.c;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.c.b;
import com.corrodinggames.rts.gameFramework.c.c;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;

public class a implements Runnable {

   public static boolean a = false;
   public static boolean b = false;
   public static boolean c = false;
   public static boolean d = false;
   public static float e;
   boolean f = true;
   public static boolean g = true;
   public ServerSocket h;
   public boolean i = true;
   static ArrayList j = new ArrayList();


   public static void a() {
      if(a) {
         l.e("-----");
         l.e("-----");
         l.e("----- Debug Active ----");
         l.e("-----");
         l.e("-----");
         l.aV = true;
         l.B().s();
         a var0 = new a();
         var0.b();
      }
   }

   public void b() {
      if(b) {
         this.a(5677, "");
      }

      l.B().eb.a(new c(this));
   }

   public void a(int var1, String var2) {
      try {
         g = true;
         l.aT = true;
         l.e("");
         l.e("----- createDebugSocket ----");
         l.e("port: " + var1);
         l.e("password: " + var2);
         l.e("------------------");
         l.e("");
         if(var1 != -1) {
            this.h = new ServerSocket(var1);
            Thread var3 = new Thread(this);
            var3.start();
         }

      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }
   }

   public void run() {
      try {
         while(this.i) {
            Socket var1 = this.h.accept();

            try {
               var1.setTcpNoDelay(true);
               b var2 = new b(this, var1);
               Thread var3 = new Thread(var2);
               var3.run();
            } catch (IOException var4) {
               l.e("Got IOException on debug connection");
               var4.printStackTrace();
               throw new RuntimeException(var4);
            }
         }

      } catch (IOException var5) {
         throw new RuntimeException(var5);
      }
   }

   public static String a(String var0) {
      String var2 = null;
      String[] var3 = null;
      int var4 = var0.indexOf(" ");
      if(var4 == -1) {
         var4 = var0.length();
      }

      String var1 = var0.substring(0, var4).toLowerCase(Locale.ENGLISH);
      if(var4 != -1 && var0.length() >= var4 + 1) {
         var2 = var0.substring(var4 + 1);
         var3 = var2.split(" ");
      }

      return var1.equalsIgnoreCase("ping")?"pong":(var1.equalsIgnoreCase("script")?"todo":(!var1.equalsIgnoreCase("function") && !var1.equalsIgnoreCase("functionNoTimeout")?"unknown command":"todo"));
   }

}
