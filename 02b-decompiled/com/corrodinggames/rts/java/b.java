package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.java.c;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;

public class b extends AppGameContainer {

   boolean a = false;
   Object b = new Object();
   c c;


   public b(Game var1, int var2, int var3, boolean var4) {
      super(var1, var2, var3, var4);
   }

   public Graphics a() {
      Graphics var1 = this.getGraphics();
      this.input.poll(this.width, this.height);
      Music.poll(1);
      if(am.a != null) {
         am.a.a(1);
      }

      GL.glClear(16640);
      GL.glLoadIdentity();
      var1.resetTransform();
      var1.resetFont();
      var1.resetLineWidth();
      var1.setAntiAlias(false);
      return var1;
   }

   public void a(Graphics var1) {
      var1.resetTransform();
      GL.flush();
      Display.update();
   }

   protected void gameLoop() {
      int var1 = this.getDelta();
      if(!Display.isVisible() && this.updateOnlyOnVisible) {
         try {
            Thread.sleep(100L);
         } catch (Exception var3) {
            ;
         }
      } else {
         this.updateAndRender(var1);
      }

      this.updateFPS();
      Display.update(false);
      if(!this.a) {
         Display.processMessages();
      } else if(this.c == null) {
         this.c = new c(this);
         this.c.start();
      }

      if(Display.isCloseRequested() && this.game.closeRequested()) {
         this.running = false;
      }

   }

   protected void updateAndRender(int var1) {
      if(this.smoothDeltas && this.getFPS() != 0) {
         var1 = 1000 / this.getFPS();
      }

      this.input.poll(this.width, this.height);
      Music.poll(var1);
      if(am.a != null) {
         am.a.a(var1);
      }

      if(!this.paused) {
         this.storedDelta += (long)var1;
         if(this.storedDelta >= this.minimumLogicInterval) {
            if(this.maximumLogicInterval != 0L) {
               long var2 = this.storedDelta / this.maximumLogicInterval;

               int var4;
               for(var4 = 0; (long)var4 < var2; ++var4) {
                  this.game.update(this, (int)this.maximumLogicInterval);
               }

               var4 = (int)(this.storedDelta % this.maximumLogicInterval);
               if((long)var4 > this.minimumLogicInterval) {
                  this.game.update(this, (int)((long)var4 % this.maximumLogicInterval));
                  this.storedDelta = 0L;
               } else {
                  this.storedDelta = (long)var4;
               }
            } else {
               this.game.update(this, (int)this.storedDelta);
               this.storedDelta = 0L;
            }
         }
      } else {
         this.game.update(this, 0);
      }

      if(this.hasFocus() || this.getAlwaysRender()) {
         if(this.clearEachFrame) {
            GL.glClear(16640);
         }

         GL.glLoadIdentity();
         Graphics var5 = this.getGraphics();
         var5.resetTransform();
         var5.resetFont();
         var5.resetLineWidth();
         var5.setAntiAlias(false);
         this.game.render(this, var5);
         var5.resetTransform();
         if(this.isShowingFPS()) {
            this.getDefaultFont().drawString(10.0F, 10.0F, "FPS: " + this.recordedFPS);
         }

         GL.flush();
      }

      if(this.targetFPS != -1) {
         Display.sync(this.targetFPS);
      }

   }

   public void destroy() {
      try {
         Display.destroy();
      } catch (Exception var2) {
         com.corrodinggames.rts.gameFramework.l.a("Error on Display.destroy in destroy", (Throwable)var2);
      }

   }
}
