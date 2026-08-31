package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.gameFramework.j.as;
import java.util.HashMap;

public class c {

   private static final HashMap c = new HashMap();
   public static final c a = a("-1");
   String b;


   public static c a(String var0) {
      c var1 = (c)c.get(var0);
      if(var1 != null) {
         return var1;
      } else {
         c var2 = new c(var0);
         c.put(var0, var2);
         return var2;
      }
   }

   public String a() {
      return this.b;
   }

   private c(String var1) {
      this.b = var1;
   }

   public static void a(as var0, c var1) {
      String var2 = null;
      if(var1 != null) {
         var2 = var1.b;
      }

      var0.b(var2);
   }

   public static c a(com.corrodinggames.rts.gameFramework.j.k var0) {
      String var1 = var0.j();
      return var1 != null?a(var1):null;
   }

   public boolean equals(Object var1) {
      return this == var1;
   }

   public int hashCode() {
      return this.b.hashCode();
   }

   public String toString() {
      return "ActionId(" + this.b + ")";
   }

   public final boolean a(c var1) {
      return this == var1;
   }

}
