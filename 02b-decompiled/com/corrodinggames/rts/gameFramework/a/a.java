package com.corrodinggames.rts.gameFramework.a;

import android.content.Context;
import android.media.SoundPool;
import com.corrodinggames.rts.R$raw;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.a.b;
import com.corrodinggames.rts.gameFramework.a.c;
import com.corrodinggames.rts.gameFramework.a.d;
import com.corrodinggames.rts.gameFramework.a.h;
import com.corrodinggames.rts.gameFramework.a.i;
import com.corrodinggames.rts.gameFramework.utility.ad;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

public class a extends h {

   LinkedBlockingQueue a = new LinkedBlockingQueue();
   final int b = 15;
   ad c = new ad(15);
   d d;
   int e = 1000;
   Context f;
   SoundPool g;


   public a() {
      for(int var1 = 0; var1 < 15; ++var1) {
         this.c.a(new c());
      }

   }

   public void a(Context var1) {
      if(this.f != null) {
         l.e("AndroidSoundFactory:setContext context already set");
      } else {
         this.f = var1;
         this.g = new SoundPool(16, 3, 0);
      }
   }

   public void a() {
      if(this.d != null) {
         throw new RuntimeException("soundThread!=null");
      } else {
         this.d = new d(this);
         this.d.start();
      }
   }

   public i a(int var1) {
      String var2 = com.corrodinggames.rts.gameFramework.f.a(R$raw.class, var1);
      b var3 = new b(this, var2, this);
      var3.a = this;
      var3.b = this.g.load(this.f, var1, 1);
      return var3;
   }

   public i a(String var1, j var2, boolean var3) {
      a var4 = this;
      if(!var3) {
         var4 = null;
      }

      FileDescriptor var5;
      long var6;
      long var8;
      int var10;
      if(!var2.a()) {
         try {
            File var11 = com.corrodinggames.rts.gameFramework.e.a.a(this.f, "audio", "ogg");

            try {
               FileOutputStream var12 = new FileOutputStream(var11);
               com.corrodinggames.rts.gameFramework.f.a((InputStream)var2, (OutputStream)var12);
               var12.close();

               try {
                  var2.close();
               } catch (Exception var31) {
                  var31.printStackTrace();
               }

               FileInputStream var13 = new FileInputStream(var11);

               try {
                  var5 = var13.getFD();
                  var6 = 0L;
                  var8 = (long)var13.available();
                  var10 = this.g.load(var5, var6, var8, 1);
               } finally {
                  var13.close();
               }
            } finally {
               var11.delete();
            }
         } catch (IOException var33) {
            var33.printStackTrace();
            return null;
         }
      } else {
         try {
            var5 = var2.b();
            var6 = 0L;
            var8 = (long)var2.available();
         } catch (IOException var29) {
            var29.printStackTrace();
            return null;
         }

         var10 = this.g.load(var5, var6, var8, 1);

         try {
            var2.close();
         } catch (Exception var28) {
            var28.printStackTrace();
         }
      }

      b var34 = new b(this, var1, var4);
      var34.a = this;
      var34.b = var10;
      return var34;
   }
}
