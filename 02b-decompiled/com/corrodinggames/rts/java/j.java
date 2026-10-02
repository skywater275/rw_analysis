package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.Main;
import java.awt.Toolkit;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;

class j implements Runnable {

   // $FF: synthetic field
   final Main a;


   j(Main var1) {
      this.a = var1;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l.aq();

      try {
         this.a.k.start();
      } catch (SlickException var5) {
         if(!"Failed to initialise the LWJGL display".equals(var5.getMessage())) {
            throw new RuntimeException(var5);
         }

         com.corrodinggames.rts.gameFramework.l.a("Error starting display", (Throwable)var5);
         String var2 = "\nFailed to get opengl version";

         try {
            System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
            Display.setDisplayMode(new DisplayMode(100, 100));
            Display.create();
            String var3 = GL11.glGetString(7938);
            Display.destroy();
            com.corrodinggames.rts.gameFramework.l.e("OpenGL version: " + var3);
            var2 = "\n";
            var2 = var2 + "OpenGL version detected: " + var3;
            if(var3.startsWith("1.0") || var3.startsWith("1.1")) {
               var2 = var2 + "\n---\nOpenGL 1.1 is over 20 years old you might be using a fallback microsoft driver. Try updating your graphics drivers from the manufacturer.";
            }
         } catch (Exception var4) {
            com.corrodinggames.rts.gameFramework.l.a("Failed to get opengl info", (Throwable)var4);
         }

         Toolkit.getDefaultToolkit();
         Sys.alert("Error", "Failed to create display." + var2);
         System.exit(1);
      }

      com.corrodinggames.rts.gameFramework.l.e("Game stopped running shutting down");
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.ca.b("lastgame", false);
      this.a.a(true);
   }
}
