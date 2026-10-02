package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.librocket.scripts.ScriptEngine$Action;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;

public class ScriptEngine$RunnableAction extends ScriptEngine$Action {

   Runnable runnable;


   ScriptEngine$RunnableAction(Runnable var1) {
      this.runnable = var1;
   }

   public void run(ScriptEngine var1) {
      try {
         this.runnable.run();
      } catch (Exception var6) {
         if(!this.tryToCatchCrash) {
            throw new RuntimeException(var6);
         }

         l.a("caught script crash", (Throwable)var6);
         this.caughtCrash = f.a(var6);
      } finally {
         this.completed = true;
      }

   }
}
