package com.corrodinggames.rts.java.d;

import com.corrodinggames.librocket.c;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.u;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.java.e;
import com.corrodinggames.rts.java.d.a;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.PNGImageData;

public class b extends c {

   Image h;
   boolean i;
   ImageBuffer j;
   // $FF: synthetic field
   final a k;


   public b(a var1) {
      super(var1);
      this.k = var1;
   }

   public boolean a() {
      af var2 = ae.a(this.a);
      Object var1;
      if(var2 != null) {
         var1 = var2.b(this.a, true);
         if(var1 == null) {
            l.g("Failed to open zipped file: " + this.a);
            return false;
         }
      } else {
         try {
            var1 = new FileInputStream(this.a);
         } catch (IOException var15) {
            var15.printStackTrace();
            return false;
         }
      }

      try {
         BufferedInputStream var3 = new BufferedInputStream((InputStream)var1);

         PNGImageData var4;
         try {
            var4 = new PNGImageData();
            var4.loadImage(var3);
         } finally {
            var3.close();
         }

         this.h = new Image(var4);
      } catch (OutOfMemoryError var12) {
         l.a(u.g, (Throwable)var12);
         this.h = e.r.C();
         this.i = true;
      } catch (IOException var13) {
         l.a("Exception loading image: " + this.a, (Throwable)var13);
         this.h = e.s.C();
         this.i = true;
      } catch (UnsupportedOperationException var14) {
         var14.printStackTrace();
         l.a("Exception loading image: " + this.a, (Throwable)var14);
         this.h = e.s.C();
         this.i = true;
      }

      this.width = this.h.getWidth();
      this.height = this.h.getHeight();
      if(this.c && (this.width > 500 || this.height > 500)) {
         l.e("Map thumbnail is too large. Size:(" + this.width + "," + this.height + ") (max:500 pixels)");
         this.h = e.t.C();
         this.i = true;
         this.width = this.h.getWidth();
         this.height = this.h.getHeight();
      }

      return true;
   }

   public void remove() {
      if(this.h != null && !this.i) {
         try {
            this.h.destroy();
         } catch (SlickException var2) {
            var2.printStackTrace();
         }
      }

      this.a = null;
      this.j = null;
      this.h = null;
      this.i = false;
   }
}
