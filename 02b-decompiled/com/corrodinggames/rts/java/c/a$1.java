package com.corrodinggames.rts.java.c;

import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.java.c.a;

class a$1 implements Runnable {

   // $FF: synthetic field
   final a a;


   a$1(a var1) {
      this.a = var1;
   }

   public void run() {
      Root var1 = ScriptEngine.getInstance().getRoot();
      com.corrodinggames.librocket.e var2 = new com.corrodinggames.librocket.e("Join", this.a);
      var1.showPopupWithButtons("Invite", "\'" + this.a.b + "\' has invited you to join a game", true, var2, (com.corrodinggames.librocket.e)null);
   }
}
