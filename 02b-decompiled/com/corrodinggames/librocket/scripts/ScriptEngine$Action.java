package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;

public class ScriptEngine$Action {

   public String script;
   public boolean tryToCatchCrash;
   public String caughtCrash;
   public boolean completed;
   public int framesDelay;


   public void run(ScriptEngine var1) {
      try {
         var1.processScript(this.script);
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

   public String waitForCompletionOrCrash(boolean var1) {
      for(int var2 = 0; var2 < 3000; ++var2) {
         if(this.completed) {
            return this.caughtCrash;
         }

         try {
            Thread.sleep(10L);
         } catch (InterruptedException var4) {
            var4.printStackTrace();
         }

         if(var1) {
            var2 = 0;
         }
      }

      return "Time Out";
   }
}
