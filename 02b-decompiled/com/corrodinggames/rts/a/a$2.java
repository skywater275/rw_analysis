package com.corrodinggames.rts.a;

import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.a.c;

final class a$2 extends c {

   // $FF: synthetic field
   final ScriptEngine a;
   // $FF: synthetic field
   final String b;


   strictfp a$2(ScriptEngine var1, String var2) {
      this.a = var1;
      this.b = var2;
   }

   public strictfp void run() {
      try {
         ScriptEngine.inDebugScript = true;
         this.c = this.a.processArg(this.b);
      } finally {
         ScriptEngine.inDebugScript = false;
      }

   }
}
