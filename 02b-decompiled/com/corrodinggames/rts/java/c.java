package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.b;
import org.lwjgl.opengl.Display;

public class c extends Thread {

   // $FF: synthetic field
   final b a;


   public c(b var1) {
      this.a = var1;
   }

   public void run() {
      while(true) {
         try {
            Thread.sleep(1L);
         } catch (InterruptedException var2) {
            ;
         }

         Display.processMessages();
      }
   }
}
