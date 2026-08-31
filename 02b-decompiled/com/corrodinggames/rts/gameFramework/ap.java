package com.corrodinggames.rts.gameFramework;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import com.corrodinggames.rts.gameFramework.an;
import com.corrodinggames.rts.gameFramework.ao;
import com.corrodinggames.rts.gameFramework.ap$1;
import com.corrodinggames.rts.gameFramework.ap$2;
import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.gameFramework.as;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ap extends as {

   MediaPlayer a;
   ao b;
   an c;


   public ap(an var1) {
      this.c = var1;
      MediaPlayer var2 = null;
      if(var1.b.size() == 0) {
         throw new RuntimeException("Music player pool empty");
      } else {
         var2 = (MediaPlayer)var1.b.remove(0);
         var1.c.add(this);
         this.a = var2;
      }
   }

   public void a(ar var1) {
      this.b = (ao)var1;
   }

   public void a(boolean var1) {
      try {
         MediaPlayer var2 = this.a;
         var2.reset();
         AssetFileDescriptor var3 = null;
         String var4;
         if(this.b.b.startsWith("music")) {
            var4 = com.corrodinggames.rts.gameFramework.e.a.e(this.b.b);

            try {
               var3 = this.c.e.w.d().b(var4);
            } catch (IOException var21) {
               throw new RuntimeException(var21);
            }

            var2.setDataSource(var3.getFileDescriptor(), var3.getStartOffset(), var3.getLength());
         } else {
            var4 = com.corrodinggames.rts.gameFramework.e.a.e(this.b.b);
            if(com.corrodinggames.rts.gameFramework.utility.ae.a(var4) == null) {
               var2.setDataSource(var4);
            } else {
               com.corrodinggames.rts.gameFramework.utility.j var5 = com.corrodinggames.rts.gameFramework.e.a.k(var4);
               if(var5 == null) {
                  throw new RuntimeException("openAssetSteam() null for \'" + var4 + "\'");
               }

               File var6 = com.corrodinggames.rts.gameFramework.e.a.a(this.c.e.w, "music", "ogg");
               l.e("Temp file needed for this music from zipped/abstract mod file");

               try {
                  FileOutputStream var7 = new FileOutputStream(var6);
                  f.a((InputStream)var5, (OutputStream)var7);
                  var7.close();
                  var5.close();
                  FileInputStream var8 = new FileInputStream(var6);

                  try {
                     var2.setDataSource(var8.getFD(), 0L, (long)var8.available());
                  } finally {
                     var8.close();
                  }
               } finally {
                  var6.delete();
               }
            }
         }

         if(var1) {
            var2.setLooping(true);
         }

         var2.setVolume(0.0F, 0.0F);
         var2.setOnInfoListener(new ap$1(this));
         var2.setOnPreparedListener(new ap$2(this));
         var2.prepareAsync();
         if(var3 != null) {
            var3.close();
         }

      } catch (Exception var22) {
         throw new RuntimeException(var22);
      }
   }

   public void a() {
      this.a.pause();
   }

   public void b() {
      this.a.start();
   }

   public boolean c() {
      return this.a.isPlaying();
   }

   public void d() {
      if(this.a != null) {
         this.a.stop();
      }

   }

   public void e() {
      if(this.a != null) {
         this.a.stop();
      }

      this.a = null;
      this.c.c.remove(this);
      this.c.b.add(this.a);
   }

   public void a(float var1) {
      this.a.setVolume(var1, var1);
   }
}
