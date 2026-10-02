/*
 * v19.132y 创建: 02b gameFramework/bb.java 直译 (184 行)
 * 回放后台写入线程 (bb=ReplayWriter; 之前 ReplayWriter 为幻觉名无实体文件)
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

class ReplayWriter
implements Runnable {

   volatile boolean a;
   volatile int b;
   int c;
   int d;
   int e;
   int f;
   int g;
   boolean h;
   public ConcurrentLinkedQueue i;
   public long j;
   // $FF: synthetic field
   final ReplayEngine k;


   ReplayWriter(ReplayEngine var1) {
      this.k = var1;
      this.a = true;
      this.h = false;
      this.i = new ConcurrentLinkedQueue();
      this.j = 0L;
   }

   public synchronized strictfp void a(ReplayFrame var1) {  // 02b bb.a(bd) L33-49
      if(this.h) {
         GlobalState.e("Replay:addCommand skipped due to stopped recording");
      }

      this.i.add(var1);
      this.f = var1.a;
      if(var1.e != null) {
         ++this.k.A;
      }

      if(var1.f != null) {
         ++this.k.B;
      }

      this.notifyAll();
   }

   public synchronized strictfp void a() {  // 02b bb.a() L51-74
      this.a = false;
      GlobalState var1 = GlobalState.B();
      ReplayEngine.a("stop requested at:" + var1.bx);
      if(!ReplayEngine.a(this.k)) {
         ReplayEngine.a("Replay stop: warning: active==false");
      }

      if(this.k.u) {
         ReplayEngine.a("Replay stop: warning: replaying==true");
      }

      this.b = var1.bx;
      this.c = var1.by;
      this.d = this.k.A;
      this.e = this.k.B;
      if(this.b < this.f) {
         GlobalState.e("Replay: stoppedFrame<lastCommandFrame: " + this.b + "<" + this.f);
         this.b = this.f;
      }

      this.j = 0L;
      this.notifyAll();
   }

   private synchronized strictfp void b() {  // 02b bb.b() L76-85
      try {
         if(this.a) {
            this.wait();
         }
      } catch (InterruptedException var2) {
         ;
      }

   }

   public strictfp void run() {  // 02b bb.run() L87-183
      GlobalState.initIntegrityAndCrashHandler();  // 02b l.aq()

      while(this.a) {
         if(this.i.size() > 0) {
            ReplayFrame var1 = (ReplayFrame)this.i.remove();

            try {
               if(var1.e != null) {
                  this.k.J.e("rc");
                  this.k.J.a(var1.a);
                  var1.e.a(this.k.J);
                  this.k.J.a("rc");
                  this.g = var1.a;
               } else if(var1.c != null) {
                  this.k.J.e("cs");
                  this.k.J.a(var1.a);
                  this.k.J.a(var1.c.longValue());
                  this.k.J.a("cs");
               } else if(var1.d != null) {
                  this.k.J.e("wait");
                  this.k.J.a(var1.a);
                  this.k.J.a("wait");
                  this.k.J.e("es");
                  this.k.J.a(var1.a);
                  this.k.J.a(var1.d);
                  this.k.J.a("es");
               } else if(var1.f != null) {
                  this.k.J.e("wait");
                  this.k.J.a(var1.a);
                  this.k.J.a("wait");
                  this.k.J.e("resync");
                  this.k.J.a(var1.a);
                  this.k.J.a(var1.h);
                  this.k.J.a(var1.i);
                  this.k.J.a(var1.j);
                  this.k.J.a(var1.k);
                  this.k.J.a(var1.f);
                  this.k.J.a("resync");
               } else {
                  if(var1.g == null) {
                     throw new RuntimeException("Unknown saved command");
                  }

                  this.k.J.e("chat");
                  this.k.J.a(var1.a);
                  this.k.J.a(var1.g.a);
                  this.k.J.b(var1.g.b);
                  this.k.J.b(var1.g.c);
                  this.k.J.a("chat");
               }

               if(this.j == 0L || this.j + 3000L < System.currentTimeMillis()) {
                  this.j = System.currentTimeMillis();
                  this.k.J.a();
               }
            } catch (IOException var5) {
               GlobalState var3 = GlobalState.B();
               GlobalState.a("Replay error", (Throwable)var5);
               var3.bS.h.a("", "IO error recording replay, disabling record");
               ReplayEngine.a(this.k, false);
               this.h = true;
               return;
            }
         }

         if(this.i.size() == 0) {
            this.b();
         }
      }

      try {
         this.k.J.e("wait");
         this.k.J.a(this.b);
         this.k.J.a("wait");
         this.k.J.e("end");
         this.k.J.a("end");
         this.k.J.e("endReplayMetaData");
         this.k.J.c(0);
         this.k.J.a(this.b);
         this.k.J.a(this.c);
         this.k.J.a(this.d);
         this.k.J.a(this.e);
         this.k.J.c("{frames:" + this.b + ",time:" + this.c + ",commandCount:" + this.d + ",resyncCount:" + this.e + "}");
         this.k.J.a("endReplayMetaData");
         this.k.J.a();
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }

      ReplayEngine.a("Background writer stopping");
      ReplayEngine.a("Remainding commands: " + this.i.size());
      ReplayEngine.a("last command: " + this.f);
      ReplayEngine.a("last command write: " + this.g);
      ReplayEngine.a("Commands issued: " + this.d);
      this.h = true;
   }
}
