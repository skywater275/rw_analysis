package com.corrodinggames.rts.gameFramework.utility;

import android.content.Context;
import android.content.res.AssetManager;
import com.corrodinggames.rts.gameFramework.utility.i$1;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public final class i {

   private ArrayList c;
   public Context a;
   boolean b = true;


   public i(Context var1) {
      this.a = var1;
      this.a();
   }

   public void a() {
      i$1 var1 = new i$1(this);
      var1.start();
   }

   public void b() {
      synchronized(this) {
         if(this.c == null) {
            AssetManager var2 = this.a.d();
            ArrayList var3 = new ArrayList();

            try {
               com.corrodinggames.rts.gameFramework.l.e("------- createIndex -------");
               var3.addAll(this.a(var2, "", 1));
            } catch (IOException var6) {
               throw new RuntimeException(var6);
            }

            this.c = var3;
         }
      }
   }

   public ArrayList a(AssetManager var1, String var2, int var3) {
      ArrayList var4 = new ArrayList();
      String[] var5 = var1.c(var2);
      if(var2.length() > 0) {
         var2 = var2 + "/";
      }

      if(var3 > 140) {
         throw new RuntimeException("dirLevel>140 for: " + var2);
      } else {
         com.corrodinggames.rts.gameFramework.l.e("c:" + var2);
         String[] var6 = var5;
         int var7 = var5.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            String var9 = var6[var8];
            String var10 = var2 + var9;
            boolean var11 = false;
            if(!var9.contains(".")) {
               var11 = true;
            }

            if(!var9.equals(".") && !var9.equals("..") && !var9.equals("")) {
               var4.add(var10);
               if(var11) {
                  var4.addAll(this.a(var1, var10, var3 + 1));
               }
            }
         }

         return var4;
      }
   }

   public ArrayList c() {
      if(this.c != null) {
         if(this.b) {
            com.corrodinggames.rts.gameFramework.l.e("assetIndex: getFile was not blocked on load");
            this.b = false;
         }

         return this.c;
      } else {
         long var1 = com.corrodinggames.rts.gameFramework.l.V();
         this.b();
         if(this.b) {
            com.corrodinggames.rts.gameFramework.l.e("assetIndex: getFile is BLOCKED on load");
            this.b = false;
         }

         return this.c;
      }
   }

   public boolean a(String var1) {
      if(var1.endsWith(File.separator)) {
         var1 = var1.substring(0, var1.length() - 1);
      }

      var1 = var1.replace("//", "/");
      Iterator var2 = this.c().iterator();

      String var3;
      do {
         if(!var2.hasNext()) {
            return false;
         }

         var3 = (String)var2.next();
      } while(!var3.equals(var1));

      return true;
   }

   public String[] b(String var1) {
      ArrayList var2 = new ArrayList();
      String var3 = var1;
      if(var1.endsWith(File.separator)) {
         var3 = var1.substring(0, var1.length() - 1);
      }

      int var4 = 0;
      Iterator var5 = this.c().iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         if(var6.startsWith(var3)) {
            String var7 = var6.substring(var3.length());
            if(var7.length() != 0 && var7.charAt(0) == File.separatorChar && var7.indexOf(File.separator, 1) == -1) {
               ++var4;
               String var8 = var6.substring((var3 + "/").length());
               var2.add(var8);
            }
         }
      }

      return (String[])var2.toArray(new String[0]);
   }
}
