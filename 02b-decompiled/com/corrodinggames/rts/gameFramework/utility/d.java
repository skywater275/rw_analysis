package com.corrodinggames.rts.gameFramework.utility;

import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.utility.a;
import com.corrodinggames.rts.gameFramework.utility.d$1;
import com.corrodinggames.rts.gameFramework.utility.d$2;
import com.corrodinggames.rts.gameFramework.utility.d$3;
import com.corrodinggames.rts.gameFramework.utility.e;
import com.corrodinggames.rts.gameFramework.utility.f;

public class d extends Thread {

   private static final e a = new d$1();
   private static final f b = new d$2();
   private e c;
   private f d;
   private final Handler e;
   private final int f;
   private String g;
   private boolean h;
   private boolean i;
   private volatile int j;
   private final Runnable k;


   public d() {
      this(5000);
   }

   public d(int var1) {
      this.c = a;
      this.d = b;
      this.e = new Handler(Looper.b());
      this.g = "";
      this.h = false;
      this.i = false;
      this.j = 0;
      this.k = new d$3(this);
      this.f = var1;
   }

   public d a(e var1) {
      if(var1 == null) {
         this.c = a;
      } else {
         this.c = var1;
      }

      return this;
   }

   public void run() {
      this.setName("|ANR-WatchDog|");
      int var2 = -1;

      while(true) {
         if(!this.isInterrupted()) {
            int var1 = this.j;
            this.e.a(this.k);

            try {
               Thread.sleep((long)this.f);
            } catch (InterruptedException var4) {
               this.d.a(var4);
               return;
            }

            if(this.j != var1) {
               continue;
            }

            if(!this.i && Debug.isDebuggerConnected()) {
               if(this.j != var2) {
                  Log.c("ANRWatchdog", "An ANR was detected but ignored because the debugger is connected (you can prevent this with setIgnoreDebugger(true))");
               }

               var2 = this.j;
               continue;
            }

            a var3;
            if(this.g != null) {
               var3 = a.a(this.g, this.h);
            } else {
               var3 = a.a();
            }

            this.c.a(var3);
            return;
         }

         return;
      }
   }

   // $FF: synthetic method
   static int a(d var0, int var1) {
      return var0.j = var1;
   }

   // $FF: synthetic method
   static int a(d var0) {
      return var0.j;
   }

}
