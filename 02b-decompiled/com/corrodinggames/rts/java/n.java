package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.gameFramework.as;
import com.corrodinggames.rts.java.l;
import com.corrodinggames.rts.java.m;
import com.corrodinggames.rts.java.audio.Music;

public class n extends as {

   m a;
   l b;
   Music c;
   boolean d = false;
   boolean e = false;
   boolean f = false;


   public n(l var1) {
      this.b = var1;
   }

   public void a(ar var1) {
      this.a = (m)var1;
   }

   public void a(boolean var1) {
      synchronized(this.b.f()) {
         this.d = true;
         this.e = var1;
         this.f = false;
         com.corrodinggames.rts.gameFramework.l.e("Queued:" + this.a.b);
         if(this.c != null) {
            com.corrodinggames.rts.gameFramework.l.e("startPlaying: Stopping old music");
            this.c.stop();
         }

         this.c = this.a.c;
      }
   }

   public void f() {
      if(!this.f) {
         synchronized(this.b.f()) {
            if(this.c != null) {
               com.corrodinggames.rts.gameFramework.l.e("Now playing:" + this.a.b);
               if(this.e) {
                  this.c.setVolume(this.c.getVolume());
                  this.c.setLooping(true);
                  this.c.play();
               } else {
                  this.c.setVolume(this.c.getVolume());
                  this.c.play();
               }

               this.f = true;
            } else {
               com.corrodinggames.rts.gameFramework.l.e("realPlay: playingMusic==null");
            }

         }
      }
   }

   public void a() {
      synchronized(this.b.f()) {
         if(this.c != null) {
            this.c.pause();
         }

      }
   }

   public void b() {
      synchronized(this.b.f()) {
         if(this.c != null && !this.c.isPlaying()) {
            this.c.play();
         }

      }
   }

   public void d() {
      synchronized(this.b.f()) {
         if(this.c != null) {
            this.c.stop();
            this.f = false;
            this.d = false;
            this.c = null;
         }

      }
   }

   public void e() {
      synchronized(this.b.f()) {
         if(this.c != null) {
            this.c.stop();
         }

      }
   }

   public boolean c() {
      synchronized(this.b.f()) {
         return this.f && this.c != null?this.c.isPlaying():false;
      }
   }

   public void a(float var1) {
      synchronized(this.b.f()) {
         if(this.c != null) {
            if(var1 > 0.05F && !this.f && this.d) {
               this.f();
            }

            this.c.setVolume(var1);
         } else {
            com.corrodinggames.rts.gameFramework.l.e("setVolume: playingMusic==null");
         }

      }
   }
}
