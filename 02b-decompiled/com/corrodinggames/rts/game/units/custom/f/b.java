package com.corrodinggames.rts.game.units.custom.f;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.f.a;
import com.corrodinggames.rts.game.units.custom.f.b$1;
import com.corrodinggames.rts.game.units.custom.f.d;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.regex.Matcher;

public class b {

   public d a;
   public d b;


   public b() {
      this.a = new d();
      this.b = new d();
   }

   public b(d var1, d var2) {
      this.a = var1;
      this.b = var2;
   }

   public void a() {
      this.a.a();
      this.b.a();
   }

   public b b() {
      b var1 = new b(this.a, new d());
      return var1;
   }

   public static double a(String var0) {
      return (new b$1(var0)).b();
   }

   public boolean b(String var1) {
      return var1.contains("*")?true:(var1.contains("/")?true:(var1.contains("+")?true:(var1.contains("-")?true:(var1.contains("(")?true:(var1.contains(")")?true:(var1.contains("^")?true:var1.contains("%")))))));
   }

   public String a(l var1, ab var2, String var3, String var4) {
      var4 = var4.trim();
      boolean var5 = this.b(var4);
      int var6 = 0;
      StringBuffer var7 = new StringBuffer();
      Matcher var8 = a.b.matcher(var4);

      while(var8.find()) {
         ++var6;
         if(var6 > 100) {
            throw new bo("Too many loops while parsing");
         }

         String var9 = var8.group(0);
         if(!f.r(var9) && !var9.equals("int") && !var9.equals("cos") && !var9.equals("sin") && !var9.equals("sqrt")) {
            String var10 = this.b(var1, var2, var3, var9);
            if(var5 && !f.r(var10)) {
               throw new bo("Cannot do maths on \'" + var10 + "\' from " + var9 + " (not a number)");
            }

            var8.appendReplacement(var7, Matcher.quoteReplacement(var10));
         }
      }

      var8.appendTail(var7);
      var4 = var7.toString();
      if(var5) {
         var4 = f.b(a(var4));
      }

      return var4;
   }

   public String b(l var1, ab var2, String var3, String var4) {
      if(var4.contains(".")) {
         String[] var9 = f.c(var4, '.');
         if(var9.length != 2) {
            throw new bo("Unexpected key format: " + var4);
         } else {
            String var6 = var9[0];
            String var7 = var9[1];
            if(var6.equals("section")) {
               var6 = var3;
            }

            String var8 = var2.b(var6, var7, (String)null);
            if(var8 == null) {
               if(var6.equalsIgnoreCase("self")) {
                  throw new bo("Static $ block: Could not find: [" + var6 + "]" + var7 + " in this conf file. Hint: You might have wanted % instead of $ for a dynamic string");
               } else {
                  throw new bo("Static $ block: Could not find: [" + var6 + "]" + var7 + " in this conf file");
               }
            } else if(var8.contains("${")) {
               throw new bo("Reference [" + var6 + "]" + var7 + " is dynamic, chaining is not yet supported");
            } else {
               return var8;
            }
         }
      } else {
         String var5 = this.b.a(var4);
         if(var5 != null) {
            return var5;
         } else {
            var5 = this.a.a(var4);
            if(var5 != null) {
               return var5;
            } else {
               throw new bo("Could not find variable with name: " + var4);
            }
         }
      }
   }
}
