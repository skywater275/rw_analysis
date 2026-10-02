package com.corrodinggames.rts.gameFramework;

import android.util.Log;
import com.corrodinggames.rts.gameFramework.am;

class am$1 extends Thread {

   // $FF: synthetic field
   final am a;


   am$1(am var1) {
      this.a = var1;
   }

   public void run() {
      if(this.a.j) {
         Log.a("RustedWarfare", "Music:pause() unsynchronized");
         this.a.g();
      } else {
         Object var1 = this.a.b;
         synchronized(this.a.b) {
            Log.a("RustedWarfare", "Music:pause() synchronized");
            this.a.g();
         }
      }

   }
}
