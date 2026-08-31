package com.corrodinggames.rts.appFramework;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import com.corrodinggames.rts.appFramework.b;
import com.corrodinggames.rts.appFramework.c;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class i extends b {

   boolean c;
   String d;


   public void b() {
      super.b();
      c.a(this, true);
   }

   public static String d(String var0) {
      if(var0 == null) {
         return null;
      } else {
         int var2;
         if(var0.contains("/MOD|")) {
            var2 = var0.indexOf("/MOD|");
            return var0.substring(var2);
         } else if(var0.contains("/NEW_PATH|")) {
            var2 = var0.indexOf("/NEW_PATH|");
            return var0.substring(var2);
         } else {
            String[] var1 = var0.split("/");
            return var1[var1.length - 1];
         }
      }
   }

   public static boolean a(String var0, String var1) {
      Pattern var2 = Pattern.compile(".*\\[(.*)\\].*");
      Matcher var3 = var2.matcher(var0);
      String var4;
      if(var3.matches()) {
         var4 = var3.group(1);
         if((var4.toLowerCase(Locale.ENGLISH) + "|").contains("demo|")) {
            return true;
         }
      }

      var4 = var1.replace(".tmx", "");
      var4 = var4 + "_demo";
      return com.corrodinggames.rts.gameFramework.e.a.i(var4);
   }

   public static String e(String var0) {
      return c.b(var0);
   }

   public static boolean f(String var0) {
      return var0.contains("skirmish/");
   }

   public static boolean g(String var0) {
      return var0.contains("SD/");
   }

   public static void a(String var0, boolean var1, int var2, int var3, boolean var4, boolean var5) {
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      var6.bS.g();
      if(!var1 && !var5) {
         var6.L();
         synchronized(var6) {
            var6.dm = null;
            var6.dl = var0;
         }

         if(!var5) {
            var6.a(true, com.corrodinggames.rts.gameFramework.s.b);
         }
      } else {
         int var7 = 0;
         var6.L();
         synchronized(var6) {
            var6.dm = null;
            var6.dl = var0;
            int var9 = com.corrodinggames.rts.game.n.c - 1;
            int var10 = c.a(var0);
            com.corrodinggames.rts.gameFramework.l.e("Max teams on map: " + var0 + " = " + var10);
            if(var10 > 0 && var10 - 1 < var9) {
               var9 = var10 - 1;
            }

            com.corrodinggames.rts.game.n.F();
            var6.bs = new com.corrodinggames.rts.game.e(0);
            var6.bs.v = "Player";

            int var11;
            int var12;
            for(var11 = 0; var11 <= 1; ++var11) {
               for(var12 = 1; var12 <= var9; ++var12) {
                  boolean var13 = var12 % 2 == 0 || var11 == 1;
                  if(var7 < var3 && var13) {
                     com.corrodinggames.rts.game.n var14 = com.corrodinggames.rts.game.n.k(var12);
                     if(var14 == null) {
                        com.corrodinggames.rts.game.a.a var22 = new com.corrodinggames.rts.game.a.a(var12);
                        var22.v = "AI";
                        var22.r = 0;
                        ++var7;
                     }
                  }
               }
            }

            com.corrodinggames.rts.gameFramework.l.e("Allies: " + var7 + "/" + var3);
            var11 = 0;
            var12 = var2 - var3;

            for(int var21 = 0; var21 <= 1; ++var21) {
               for(int var23 = 1; var23 <= var9; ++var23) {
                  boolean var15 = var23 % 2 == 1 || var21 == 1;
                  if(!var4) {
                     var15 = true;
                  }

                  if(var11 < var12 && var15) {
                     com.corrodinggames.rts.game.n var16 = com.corrodinggames.rts.game.n.k(var23);
                     if(var16 == null) {
                        com.corrodinggames.rts.game.a.a var24 = new com.corrodinggames.rts.game.a.a(var23);
                        var24.v = "AI";
                        ++var11;
                        if(var4) {
                           var24.r = 1;
                        }
                     }
                  }
               }
            }

            var6.bX.aq();
            if(!var5) {
               var6.a(false, com.corrodinggames.rts.gameFramework.s.b);
            }
         }
      }

   }

   public void onCreateContextMenu(ContextMenu var1, View var2, ContextMenuInfo var3) {
      super.onCreateContextMenu(var1, var2, var3);
      AdapterContextMenuInfo var4 = (AdapterContextMenuInfo)var3;
      View var5 = var4.targetView;
      String var6 = (String)var5.getTag();
      com.corrodinggames.rts.gameFramework.l var7 = com.corrodinggames.rts.gameFramework.l.B();
      String var8 = e(var6);
      com.corrodinggames.rts.gameFramework.i.b var9;
      if(var6 != null) {
         var9 = var7.bZ.h(var6);
      } else {
         var9 = null;
      }

      this.d = var6;
      var1.setHeaderTitle(var8);
      MenuItem var10 = var1.add(0, var5.getId(), 0, "Export");
      if(var9 != null) {
         var10.setTitle("Export (Standalone maps only)");
         var10.setEnabled(false);
      }

      MenuItem var11 = var1.add(2, var5.getId(), 0, "Delete");
      if(var9 != null) {
         var11.setTitle("Delete (Standalone maps only)");
         var11.setEnabled(false);
      }

      if(var9 != null) {
         MenuItem var12 = var1.add(4, var5.getId(), 0, "From Mod: " + var9.b());
         var12.setEnabled(false);
      }

      if(var9 == null && this.c) {
         String var14 = com.corrodinggames.rts.gameFramework.e.a.n(var6);
         MenuItem var13 = var1.add(3, var2.getId(), 0, "Storage: " + var14);
         if(var13 != null) {
            var13.setEnabled(false);
         }
      }

   }
}
