package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import java.io.IOException;

public class ae {

   public String b;
   public int c;
   public boolean d;
   public String e;
   public String f;
   public String g;


   public strictfp void a(String var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.d) {
         as var3;
         try {
            var3 = new as();
            var3.c(1);
            var3.a(this.c);
            var3.c(var1);
         } catch (IOException var5) {
            throw new RuntimeException(var5);
         }

         au var4 = var3.b(118);
         var2.bX.d(var4);
      } else if(var2.bX.C) {
         com.corrodinggames.rts.gameFramework.l.a("Cannot enter a password when we are a server");
      } else {
         var2.bX.n = var1;
         var2.bX.X();
      }
   }

   public strictfp void a() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bX.b("exited password");
      var1.bX.K();
   }
}
