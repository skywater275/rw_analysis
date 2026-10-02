package com.corrodinggames.rts.java;

import android.content.Context;
import com.corrodinggames.rts.gameFramework.utility.ad;
import com.corrodinggames.rts.java.p;
import com.corrodinggames.rts.java.q;
import com.corrodinggames.rts.java.r;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import java.util.concurrent.LinkedBlockingQueue;

public class o extends com.corrodinggames.rts.gameFramework.a.h {

   final int a = 15;
   LinkedBlockingQueue b = new LinkedBlockingQueue();
   ad c = new ad(15);
   r d;
   Context e;
   public OpenALAudio f;


   public Object b() {
      return this.f;
   }

   public o(OpenALAudio var1) {
      for(int var2 = 0; var2 < 15; ++var2) {
         this.c.a(new p());
      }

      this.f = var1;
   }

   public void a(Context var1) {
      if(this.e != null) {
         com.corrodinggames.rts.gameFramework.l.e("SlickSoundFactory:setContext context already set");
      } else {
         this.e = var1;
      }
   }

   public com.corrodinggames.rts.gameFramework.a.i a(int var1) {
      String var2 = com.corrodinggames.rts.gameFramework.f.f(var1);
      q var3 = new q(this, var2, this);
      String var4 = com.corrodinggames.rts.gameFramework.f.f(var1);
      if(var4 == null) {
         throw new RuntimeException("Failed to find sound for res id:" + var1);
      } else {
         synchronized(this.b()) {
            com.corrodinggames.rts.java.audio.a.a var6 = new com.corrodinggames.rts.java.audio.a.a(var4);
            var3.a = this.f.newSound(var6);
            return var3;
         }
      }
   }

   public com.corrodinggames.rts.gameFramework.a.i a(String var1, com.corrodinggames.rts.gameFramework.utility.j var2, boolean var3) {
      o var4 = this;
      if(!var3) {
         var4 = null;
      }

      q var5 = new q(this, var1, var4);

      try {
         synchronized(this.b()) {
            var5.a = this.f.newSound(new com.corrodinggames.rts.java.audio.a.a(var2, var2.d()));
            return var5;
         }
      } catch (com.corrodinggames.rts.java.audio.a.c var9) {
         var9.printStackTrace();
         return null;
      }
   }

   public void a() {
      if(this.d != null) {
         throw new RuntimeException("startThreads: soundThread!=null");
      } else {
         this.d = new r(this);
         this.d.start();
      }
   }
}
