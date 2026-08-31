package com.corrodinggames.rts.gameFramework.f;

import java.util.Iterator;

class c {

   com.corrodinggames.rts.game.units.am a;
   com.corrodinggames.rts.game.units.a.s b;
   float c;
   boolean d;
   boolean e;
   static com.corrodinggames.rts.gameFramework.utility.m f = new com.corrodinggames.rts.gameFramework.utility.m();


   public static void a(com.corrodinggames.rts.game.units.am var0, com.corrodinggames.rts.game.units.a.s var1, boolean var2, boolean var3) {
      c var4 = a(var0, var1, var3);
      if(var4 == null) {
         var4 = new c();
         f.add(var4);
      }

      var4.a = var0;
      var4.b = var1;
      var4.c = 10.0F;
      var4.d = var2;
      var4.e = var3;
   }

   public static c a(com.corrodinggames.rts.game.units.am var0, com.corrodinggames.rts.game.units.a.s var1, boolean var2) {
      Iterator var3 = f.iterator();

      c var4;
      do {
         if(!var3.hasNext()) {
            return null;
         }

         var4 = (c)var3.next();
      } while(var4.a != var0 || var4.b != var1 || var4.e != var2);

      return var4;
   }

   public static float b(com.corrodinggames.rts.game.units.am var0, com.corrodinggames.rts.game.units.a.s var1, boolean var2) {
      c var3 = a(var0, var1, var2);
      if(var3 != null) {
         float var4 = var3.c / 10.0F;
         if(var3.d) {
            var4 = -var4;
         }

         return var4;
      } else {
         return 0.0F;
      }
   }

   public static void a(float var0) {
      for(int var1 = f.size() - 1; var1 >= 0; --var1) {
         c var2 = (c)f.get(var1);
         var2.c -= var0;
         if(var2.c <= 0.0F) {
            f.remove(var1);
         }
      }

   }

}
