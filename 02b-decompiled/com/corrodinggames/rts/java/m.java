package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.java.l;
import com.corrodinggames.rts.java.audio.Music;

public class m extends ar {

   l a;
   Music c;


   public m(String var1, l var2) {
      super(var1, var2);
      this.a = var2;
      synchronized(var2.f()) {
         this.a = var2;
         String var4 = com.corrodinggames.rts.gameFramework.e.a.e(var1);
         if(var4.contains(".rwmod")) {
            this.c = var2.b.newMusic(new com.corrodinggames.rts.java.audio.a.a(com.corrodinggames.rts.gameFramework.e.a.k(var1), var4));
         } else {
            this.c = var2.b.newMusic(new com.corrodinggames.rts.java.audio.a.a(var4));
         }

      }
   }
}
