package com.corrodinggames.librocket;

import com.corrodinggames.librocket.a;
import com.corrodinggames.librocket.a$2$1;
import com.corrodinggames.librocket.a$2$2;
import com.corrodinggames.librocket.d;
import com.corrodinggames.librocket.e;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.j.ae;
import com.corrodinggames.rts.gameFramework.utility.k;

class a$2 implements Runnable {

   final k a;
   // $FF: synthetic field
   final ScriptEngine b;
   // $FF: synthetic field
   final ae c;
   // $FF: synthetic field
   final a d;


   a$2(a var1, ScriptEngine var2, ae var3) {
      this.d = var1;
      this.b = var2;
      this.c = var3;
      this.a = new k(false);
   }

   public void run() {
      Root var1 = this.b.getRoot();
      e var2 = new e(this.c.f != null?this.c.f:"Join", new a$2$1(this, var1));
      var2.c = true;
      a$2$2 var3 = new a$2$2(this, var1);
      e var4 = new e(this.c.g != null?this.c.g:"Close", var3);
      String var5 = "Password Required";
      String var6 = "This server requires a password to join";
      if(this.c.b != null) {
         var5 = "Server Question";
         var6 = this.c.b;
         var6 = com.corrodinggames.rts.gameFramework.h.a.c(var6);
      }

      if(this.c.e != null) {
         var5 = this.c.e;
      }

      String var7 = "";
      d var8 = new d();
      var8.b = var5;
      var8.c = var6;
      var8.d = var7;
      var8.e = var4;
      var8.f = var2;
      var8.h = false;
      var8.i = var3;
      this.d.b.a(var8);
   }
}
