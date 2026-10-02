package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import com.corrodinggames.rts.appFramework.c$1;
import com.corrodinggames.rts.appFramework.c$2;
import com.corrodinggames.rts.appFramework.e;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class c {

   static Handler a;
   static volatile Context b;
   public static final e c = e.f;
   public static e d = c;


   public static int a(String var0) {
      String var1 = null;
      if(var0 != null) {
         var1 = com.corrodinggames.rts.gameFramework.f.k(var0);
      }

      if(var1 != null) {
         Pattern var2 = Pattern.compile("^ *\\[([^\\]]*)\\].*");
         Matcher var3 = var2.matcher(var1);
         if(var3.matches()) {
            String var4 = var3.group(1);
            String[] var5 = var4.split(";");
            String[] var6 = var5;
            int var7 = var5.length;

            for(int var8 = 0; var8 < var7; ++var8) {
               String var9 = var6[var8];
               if(var9.startsWith("p") && var9.length() >= 2) {
                  String var10 = var9.substring(1);

                  try {
                     int var11 = Integer.parseInt(var10);
                     return var11;
                  } catch (NumberFormatException var13) {
                     com.corrodinggames.rts.gameFramework.l.e("getNumberOfPlayersInMap: NumberFormatException:" + var10);
                     return -1;
                  }
               }
            }
         }
      }

      com.corrodinggames.rts.gameFramework.l.e("getNumberOfPlayersInMap: fail to match:" + var1);
      return -1;
   }

   public static String b(String var0) {
      if(var0 == null) {
         return null;
      } else {
         String[] var1;
         if(var0.contains(File.separator)) {
            var1 = var0.split(Pattern.quote(File.separator));
            var0 = var1[var1.length - 1];
         }

         if(var0.contains("/")) {
            var1 = var0.split("/");
            var0 = var1[var1.length - 1];
         }

         String var4 = null;
         Pattern var2;
         Matcher var3;
         if(var4 == null) {
            var2 = Pattern.compile("^l\\d*;\\[.*\\](.+)\\.tmx");
            var3 = var2.matcher(var0);
            if(var3.matches()) {
               var4 = var3.group(1);
               if(var4.length() >= 1) {
                  var4 = var4.substring(0, 1).toUpperCase() + var4.substring(1);
               }
            }
         }

         if(var4 == null) {
            var2 = Pattern.compile("^l\\d*;(.+)\\.tmx");
            var3 = var2.matcher(var0);
            if(var3.matches()) {
               var4 = var3.group(1);
               if(var4.length() >= 1) {
                  var4 = var4.substring(0, 1).toUpperCase() + var4.substring(1);
               }
            }
         }

         if(var4 == null) {
            var2 = Pattern.compile("^ *\\[.*\\](.+)\\.tmx");
            var3 = var2.matcher(var0);
            if(var3.matches()) {
               var4 = var3.group(1);
               if(var4.length() >= 1) {
                  var4 = var4.substring(0, 1).toUpperCase() + var4.substring(1);
               }
            }
         }

         if(var4 == null) {
            var2 = Pattern.compile("(.*)\\.tmx");
            var3 = var2.matcher(var0);
            if(var3.matches()) {
               var4 = var3.group(1);
               if(var4.length() >= 1) {
                  var4 = var4.substring(0, 1).toUpperCase() + var4.substring(1);
               }
            }
         }

         if(var4 == null) {
            var4 = var0;
         }

         var4 = var4.replace('_', ' ');
         if(var4.endsWith(".rwsave")) {
            var4 = var4.replace(".rwsave", "");
         }

         return var4;
      }
   }

   public static String c(String var0) {
      String var1 = var0.replace(".tmx", "");
      var1 = var1 + "_map.png";
      return var1;
   }

   private static void c(Activity var0) {
      if(VERSION.SDK_INT >= 19) {
         var0.a().getDecorView().setSystemUiVisibility(5894);
      }

   }

   private static void d(Activity var0) {}

   public static void a(Runnable var0) {
      if(a == null) {
         a = new Handler(Looper.b());
      }

      a.a(var0);
   }

   public static Context a() {
      if(b == null) {
         throw new RuntimeException("ApplicationContext==null");
      } else {
         return b;
      }
   }

   public static void a(Activity var0) {
      if(b == null) {
         b = var0.g();
      }

   }

   public static void a(Context var0) {
      if(b == null) {
         b = var0.g();
      }

   }

   public static void a(Activity var0, boolean var1, boolean var2) {
      a(var0);
      com.corrodinggames.rts.gameFramework.l var3;
      if(var2) {
         var3 = com.corrodinggames.rts.gameFramework.l.B();
         if(var3 != null && var3.bQ.immersiveFullScreen) {
            c(var0);
         }
      } else {
         d(var0);
      }

      var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3 != null) {
         var3.ab();
      }

      if(var1) {
         var0.a().setBackgroundDrawable((Drawable)null);
      }

   }

   public static void a(Activity var0, boolean var1) {
      if(var1) {
         var0.a(0, 0);
      }

   }

   public static boolean a(Activity var0, Runnable var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var3 = a(var0, var1, false);
      return var3;
   }

   public static boolean a(Activity var0, Runnable var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(!var2 && var3.bQ.hasSelectedAStorageType) {
         return false;
      } else if(VERSION.SDK_INT < 19) {
         return false;
      } else {
         c$1 var4 = new c$1(var3, var1);
         c$2 var5 = new c$2(var0, var3, var1);
         String var6 = com.corrodinggames.rts.gameFramework.h.a.a("menus.mods.androidStorageSetupTitle", new Object[0]);
         String var7 = com.corrodinggames.rts.gameFramework.h.a.a("menus.mods.androidStorageSetupMessage", new Object[0]);
         String var8 = com.corrodinggames.rts.gameFramework.h.a.a("menus.mods.androidStorageSetupInternal", new Object[0]);
         String var9 = com.corrodinggames.rts.gameFramework.h.a.a("menus.mods.androidStorageSetupExternal", new Object[0]);
         (new Builder(var0)).setIcon(17301543).setTitle(var6).setMessage(var7).setPositiveButton(var8, var4).setNeutralButton(var9, var5).show();
         com.corrodinggames.rts.gameFramework.l.e("Showing storage setup");
         return true;
      }
   }

   public static boolean b(Context var0) {
      return com.corrodinggames.rts.gameFramework.l.aU?true:(!com.corrodinggames.rts.gameFramework.e.a.f()?true:(VERSION.SDK_INT >= 23?ContextCompat.a(var0, "android.permission.WRITE_EXTERNAL_STORAGE") == 0:true));
   }

   public static boolean b(Activity var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(com.corrodinggames.rts.gameFramework.l.aU) {
         return true;
      } else if(!com.corrodinggames.rts.gameFramework.e.a.f()) {
         return true;
      } else if(VERSION.SDK_INT >= 23) {
         if(var1.aD() == null) {
            ;
         }

         if(ContextCompat.a(var0, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            var1.bQ.hadStoragePermissionInPast = true;
            com.corrodinggames.rts.gameFramework.l.e("File Permission is granted");
            return true;
         } else {
            com.corrodinggames.rts.gameFramework.l.e("Permission is revoked");
            ActivityCompat.a(var0, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
            return false;
         }
      } else {
         return true;
      }
   }

   public static void a(Intent var0) {
      var0.addFlags(65536);
   }

   public static void a(Activity var0, int var1, boolean var2, String var3, Uri var4) {
      com.corrodinggames.rts.gameFramework.l.e("Show folder chooser. Write:" + var2);
      Intent var5 = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
      var5.addFlags(64);
      var5.addFlags(1);
      if(var2) {
         var5.addFlags(2);
      }

      if(var4 != null) {
         var5.putExtra("android.provider.extra.INITIAL_URI", var4);
      }

      var5.putExtra("android.content.extra.SHOW_ADVANCED", true);

      try {
         var0.a(Intent.createChooser(var5, var3), var1);
      } catch (ActivityNotFoundException var7) {
         Toast.makeText(var0, "Failed to open file list. Please install a File Manager.", 0).show();
      }

   }

}
