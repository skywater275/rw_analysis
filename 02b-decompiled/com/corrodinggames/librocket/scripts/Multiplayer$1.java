package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Multiplayer;

class Multiplayer$1 implements Runnable {

   // $FF: synthetic field
   final String val$teamIdString;
   // $FF: synthetic field
   final Multiplayer this$0;


   Multiplayer$1(Multiplayer var1, String var2) {
      this.this$0 = var1;
      this.val$teamIdString = var2;
   }

   public void run() {
      this.this$0.showPlayerConfigNow(this.val$teamIdString);
   }
}
