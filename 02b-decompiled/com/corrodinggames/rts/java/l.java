package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.aq;
import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.gameFramework.as;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.java.m;
import com.corrodinggames.rts.java.n;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;

public class l extends aq {

   volatile boolean a;
   public OpenALAudio b;
   boolean c = false;


   public Object f() {
      return this.b;
   }

   public void a(float var1) {
      synchronized(this.f()) {
         if(this.a) {
            return;
         }

         long var3 = br.a();
         this.b.update();
         double var5 = (double)br.a(var3);
         if(var5 > 16.0D) {
            com.corrodinggames.rts.gameFramework.l.e("music poll took:" + br.a(var5));
         }
      }

      super.a(var1);
   }

   public void a(int var1) {}

   public l(OpenALAudio var1) {
      this.b = var1;
   }

   public ar a(String var1) {
      return new m(var1, this);
   }

   public as a() {
      n var1 = new n(this);
      return var1;
   }

   public void a(am var1) {
      this.e = var1;
   }

   public void b() {
      synchronized(this.f()) {
         this.a = true;
      }
   }

   public boolean c() {
      return true;
   }

   public boolean d() {
      return true;
   }

   public int e() {
      return 100;
   }
}
