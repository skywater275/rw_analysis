package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public final class g {

   final String a;
   public static ArrayList b = new ArrayList();
   public static final g[] c = new g[0];
   public static final h d = new h(c);


   private g(String var1) {
      this.a = var1;
   }

   public String toString() {
      return this.a;
   }

   public static h a(String var0) {
      return a(var0, (h)null);
   }

   public static h a(String var0, h var1) {
      if(var0 == null) {
         return var1;
      } else if(var0.trim().equals("")) {
         return var1;
      } else {
         ArrayList var2 = new ArrayList();
         String[] var3 = var0.split(",");
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            var6 = var6.trim();
            if(!var6.equals("")) {
               g var7 = c(var6);
               if(!var2.contains(var7)) {
                  var2.add(var7);
               }
            }
         }

         if(var2.size() == 0) {
            return var1;
         } else {
            h var8 = new h((g[])var2.toArray(new g[0]));
            return var8;
         }
      }
   }

   public static g b(String var0) {
      var0 = var0.trim();
      if(var0.contains(",")) {
         throw new bo("Expected single tag, got:" + var0);
      } else {
         return c(var0);
      }
   }

   public static g c(String var0) {
      var0 = var0.trim();
      var0 = var0.toLowerCase(Locale.ROOT);
      Iterator var1 = b.iterator();

      g var2;
      do {
         if(!var1.hasNext()) {
            g var3 = new g(var0);
            b.add(var3);
            return var3;
         }

         var2 = (g)var1.next();
      } while(!var2.a.equals(var0));

      return var2;
   }

   public static void a(h var0, com.corrodinggames.rts.gameFramework.j.as var1) {
      if(var0 == null) {
         var1.b((String)null);
      } else if(var0.a.length == 0) {
         var1.b("");
      } else {
         StringBuilder var2 = new StringBuilder();
         boolean var3 = true;
         g[] var4 = var0.a;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            g var7 = var4[var6];
            if(!var3) {
               var2.append(",");
            }

            var3 = false;
            var2.append(var7.a);
         }

         var1.b(var2.toString());
      }

   }

   public static h a(com.corrodinggames.rts.gameFramework.j.k var0) {
      String var1 = var0.j();
      if(var1 == null) {
         return null;
      } else {
         h var2 = a(var1, d);
         return var2;
      }
   }

   public static boolean a(h var0, h var1) {
      if(var1 == null) {
         return false;
      } else {
         g[] var2 = var0.a;
         int var3 = var2.length;
         g[] var4 = var1.a;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var3; ++var6) {
            for(int var7 = 0; var7 < var5; ++var7) {
               if(var2[var6] == var4[var7]) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public static boolean a(g var0, h var1) {
      if(var1 == null) {
         return false;
      } else {
         g[] var2 = var1.a;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            if(var2[var4] == var0) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean b(h var0, h var1) {
      if(var1 == null) {
         return var0 == null || var0.b() == 0;
      } else {
         g[] var2 = var0.a;
         int var3 = var2.length;
         g[] var4 = var1.a;
         int var5 = var4.length;
         int var6 = 0;

         while(var6 < var3) {
            boolean var7 = false;
            int var8 = 0;

            while(true) {
               if(var8 < var5) {
                  if(var2[var6] != var4[var8]) {
                     ++var8;
                     continue;
                  }

                  var7 = true;
               }

               if(!var7) {
                  return false;
               }

               ++var6;
               break;
            }
         }

         return true;
      }
   }

}
