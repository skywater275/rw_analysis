package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.am$1;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

enum at {

   a("starting", 0),
   b("buildup", 1),
   c("attacked", 2);
   String[] d;
   // $FF: synthetic field
   private static final at[] e = new at[]{a, b, c};


   private at(String var1, int var2) {}

   void a() {
      this.d = com.corrodinggames.rts.gameFramework.e.a.a(this.d(), false);
      if(this.d == null) {
         this.d = new String[0];
         l.n("Failed to open music folder: " + this.d());
      } else {
         l var1 = l.B();
         ArrayList var2 = new ArrayList();
         String[] var3 = this.d;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            var6 = com.corrodinggames.rts.gameFramework.e.a.o(var6);
            if(am.a(this.a(var6), true) != null) {
               l.e("Loaded track:" + var6);
               var2.add(var6);
            } else {
               l.b("Skipping track:" + var6);
            }

            var1.a("music", false);
         }

         this.d = (String[])var2.toArray(new String[0]);
      }
   }

   String[] b() {
      return this.d;
   }

   static void c() {
      a.a();
      b.a();
      c.a();
   }

   abstract String d();

   String a(String var1) {
      return this.d() + "/" + var1;
   }

   // $FF: synthetic method
   at(String var1, int var2, am$1 var3) {
      this(var1, var2);
   }

}
