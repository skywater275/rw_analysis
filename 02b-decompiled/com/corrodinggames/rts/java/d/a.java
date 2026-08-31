package com.corrodinggames.rts.java.d;

import android.graphics.Rect;
import android.graphics.RectF;
import com.LibRocket$CompiledGeometry;
import com.LibRocket$TextureHolder;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.u;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.java.d.b;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class a extends com.corrodinggames.librocket.b {

   private static SGL k = Renderer.get();
   Graphics j;


   public void a() {
      throw new RuntimeException("startNewFrame() not supported on SlickLibRocket");
   }

   public void a(Graphics var1) {
      this.j = var1;
      super.a();
   }

   public boolean GenerateTexture(int var1, byte[] var2) {
      try {
         b var3 = (b)this.findTextureHolder(var1);
         ImageBuffer var4 = new ImageBuffer(var3.width, var3.height);
         byte[] var5 = var4.getRGBA();

         for(int var6 = 0; var6 < var2.length; ++var6) {
            var5[var6] = var2[var6];
         }

         var3.h = new Image(var4);
         if(var3.h == null) {
            throw new RuntimeException("slickTextureHolder.image==null");
         } else {
            return true;
         }
      } catch (OutOfMemoryError var7) {
         l.a(u.g, (Throwable)var7);
         return false;
      } catch (Throwable var8) {
         ScriptEngine.throwDelayedException("GenerateTexture Failed", var8);
         return true;
      }
   }

   public void RenderGeometryPossiblyCompiled(float[] var1, float[] var2, int[] var3, int[] var4, int var5, float var6, float var7, LibRocket$CompiledGeometry var8) {
      try {
         if(this.debug) {
            System.out.println("SlickLibRocket:RenderGeometry(" + var1.length + "," + var5 + ")");
            System.out.println("indices.length=" + var4.length + "");
         }

         b var9 = null;
         if(var5 != 0) {
            var9 = (b)this.findTextureHolder(var5);
         }

         RectF var10 = null;
         if(var8 != null) {
            var10 = (RectF)var8.bbox;
         }

         float var15;
         if(var10 == null) {
            var10 = new RectF();

            for(int var11 = 0; var11 < var4.length; var11 += 3) {
               int var10000 = var4[var11];

               for(int var13 = 0; var13 <= 2; ++var13) {
                  int var12 = var4[var11 + var13];
                  float var14 = var1[var12 * 2 + 0];
                  var15 = var1[var12 * 2 + 1];
                  if(var10.a()) {
                     var10.a(var14, var15, var14 + 1.0F, var15 + 1.0F);
                  } else {
                     var10.c(var14, var15);
                  }
               }
            }

            var10.g();
            if(var8 != null) {
               var8.bbox = var10;
            }
         }

         RectF var23 = new RectF(var10);
         var23.a(var6, var7);
         if(this.h && !f.a(var23, this.g)) {
            boolean var24 = true;
            if(var9 != null && var9.h == null && var9.b && var9.f == null) {
               var24 = false;
            }

            if(var24) {
               return;
            }
         }

         if(var9 != null && var9.j != null) {
            System.out.println("Loading image for: " + var9.index);
            var9.h = new Image(var9.j);
            if(var9.h == null) {
               throw new RuntimeException("slickTextureHolder.image==null");
            }

            var9.j = null;
         }

         this.j.pushTransform();
         this.j.setDrawMode(Graphics.MODE_NORMAL);
         this.j.translate(var6, var7);
         float var25 = 1.0F;
         float var26 = 1.0F;
         boolean var27 = false;
         var15 = 1.0F;
         boolean var16 = false;
         Texture var17 = null;
         if(var9 != null) {
            var27 = var9.d;
            var15 = var9.e;
            var17 = TextureImpl.getLastBind();
            if(var9.h == null && var9.b) {
               if(var9.f != null) {
                  y var18 = l.B().bO;
                  this.j.pushTransform();
                  var18.i();
                  float var19 = (float)l.B().bA / 1000.0F / 10.0F * 360.0F % 360.0F;
                  this.j.translate(-var6, -var7);
                  Rect var20 = new Rect(this.f.a, this.f.b, this.f.c, this.f.d);
                  this.j.setClip((Rectangle)null);
                  this.j.setWorldClip((Rectangle)null);
                  var18.a(var20);
                  n var21 = n.k(0);
                  if(var21 == null) {
                     var21 = n.i;
                  }

                  ar.a(var9.f, var23.d(), var23.e(), var19, 3.0F, var21, var23.c() * 0.6F, var23.c(), false, false, 1, (am)null);
                  var18.p();
                  var15 = 0.0F;
                  var18.j();
                  this.j.popTransform();
               } else if(this.d < 1) {
                  var9.a();
                  ++this.d;
               }
            }

            if(var9.h != null) {
               var9.h.getTexture().bind();
               var25 = var9.h.getTextureWidth();
               var26 = var9.h.getTextureHeight();
               var16 = true;
            } else if(var9.b) {
               var15 = 0.1F;
            }
         }

         if(!var16) {
            TextureImpl.bindNone();
         }

         Color var28 = new Color(1.0F, 1.0F, 1.0F, var15);
         this.j.setColor(var28);
         k.glBegin(4);

         for(int var29 = 0; var29 < var4.length; var29 += 3) {
            int var30 = var4[var29];
            int var31;
            if(!var27) {
               var31 = var3[var30];
               var28.r = (float)(var31 >> 24 & 255) / 255.0F;
               var28.g = (float)(var31 >> 16 & 255) / 255.0F;
               var28.b = (float)(var31 >> 8 & 255) / 255.0F;
               var28.a = (float)(var31 & 255) / 255.0F;
               var28.a *= var15;
               var28.bind();
            }

            for(var31 = 0; var31 <= 2; ++var31) {
               var30 = var4[var29 + var31];
               k.glTexCoord2f(var2[var30 * 2 + 0] * var25, var2[var30 * 2 + 1] * var26);
               k.glVertex2f(var1[var30 * 2 + 0], var1[var30 * 2 + 1]);
            }
         }

         k.glEnd();
         this.j.popTransform();
      } catch (Throwable var22) {
         ScriptEngine.throwDelayedException("UI Render Failed", var22);
      }

   }

   public LibRocket$TextureHolder getFromTextureHolderFactory() {
      return new b(this);
   }

   public void EnableScissorRegion(boolean var1) {
      if(var1) {
         this.j.setWorldClip((float)this.f.a, (float)this.f.b, (float)this.f.b(), (float)this.f.c());
         this.h = true;
      } else {
         this.j.clearWorldClip();
         this.h = false;
      }

   }

}
