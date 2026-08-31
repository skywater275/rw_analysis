package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.ay;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.h;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class e implements Runnable {

   Boolean a;
   OutputStream b;
   BufferedOutputStream c;
   DataOutputStream d;
   com.corrodinggames.rts.gameFramework.utility.w e;
   // $FF: synthetic field
   final c f;


   public synchronized strictfp void a(au var1) {
      if(!this.f.a) {
         this.f.f.add(var1);
         this.notifyAll();
      }
   }

   public synchronized strictfp void a() {
      this.notifyAll();
   }

   public synchronized strictfp void b() {
      try {
         if(this.f.f.isEmpty() && !this.f.a && !this.f.b) {
            this.wait(10000L);
         }
      } catch (InterruptedException var2) {
         ;
      }

   }

   strictfp e(c var1) {
      this.f = var1;
      this.a = Boolean.valueOf(true);
      this.e = new com.corrodinggames.rts.gameFramework.utility.w();
      this.b = var1.d.getOutputStream();
      this.c = new BufferedOutputStream(this.b);
      this.d = new DataOutputStream(this.c);
   }

   public strictfp void run() {
      com.corrodinggames.rts.gameFramework.l.aq();
      Thread.currentThread().setName("SendWorker-" + this.f.g());

      try {
         while(this.a.booleanValue() && !this.f.a) {
            while(!this.f.f.isEmpty() && !this.f.a) {
               au var1 = (au)this.f.f.remove();
               if(var1 instanceof ay) {
                  ay var2 = (ay)var1;
                  as var3;
                  if(this.f.l == var2.f && this.f.r) {
                     var3 = new as();
                     var3.a(var2.g);
                     var1 = var3.b(176);
                  } else {
                     var3 = new as();
                     var3.a(var2.g);
                     var3.a(var2.f.b);
                     var3.a(var2.f.c);
                     var1 = var3.b(175);
                  }

                  this.f.l = var2.f;
               } else if(this.f.q) {
                  this.f.l = var1;
               }

               if(this.f.d instanceof h) {
                  h var9 = (h)this.f.d;
                  var9.a(var1);
               } else if(this.f.d instanceof a.a.h) {
                  boolean var10 = false;
                  com.corrodinggames.rts.gameFramework.utility.w var11;
                  if(var1.c.length > 500) {
                     var11 = new com.corrodinggames.rts.gameFramework.utility.w(8 + var1.c.length);
                     var10 = true;
                  } else {
                     var11 = this.e;
                     var11.a();
                  }

                  boolean var4 = var1.e;
                  DataOutputStream var5 = new DataOutputStream(var11);
                  var5.writeInt(var1.c.length);
                  var5.writeInt(var1.b);
                  var5.write(var1.c);
                  var5.flush();
                  var5.close();
                  a.a.h var6 = (a.a.h)this.f.d;
                  var6.a(var11.a, 0, var11.b(), var4);
                  if(var10) {
                     var11.close();
                  }
               } else {
                  this.d.writeInt(var1.c.length);
                  this.d.writeInt(var1.b);
                  this.d.write(var1.c);
                  this.d.flush();
               }

               if(var1.d != -1) {
                  try {
                     Thread.sleep((long)var1.d);
                  } catch (InterruptedException var7) {
                     ;
                  }
               }
            }

            if(this.f.b) {
               this.f.a = true;
               break;
            }

            this.b();
         }
      } catch (IOException var8) {
         var8.printStackTrace();
         com.corrodinggames.rts.gameFramework.l.b("network:SendWorker", var8.getMessage());
      }

      c.a(this.f, false, true);
   }
}
