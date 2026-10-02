package com.corrodinggames.rts.game;

import android.graphics.Bitmap;
import android.graphics.Bitmap$CompressFormat;
import com.corrodinggames.rts.game.i;
import java.io.File;
import java.io.FileOutputStream;

class k implements Runnable {

   public Bitmap a;
   public int b;
   // $FF: synthetic field
   final i c;


   public strictfp void run() {
      synchronized(this) {
         try {
            File var2 = new File(this.c.h + "image_" + String.format("%07d", new Object[]{Integer.valueOf(this.b)}) + ".jpg");
            FileOutputStream var3 = new FileOutputStream(var2);
            this.a.a(Bitmap$CompressFormat.a, 85, var3);
            var3.close();
         } catch (Exception var5) {
            var5.printStackTrace();
            this.c.bo = false;
            this.c.a("Error saving jpg, recording has stopped. Is there free space remaining on the SD card?", 1);
         }

      }
   }
}
