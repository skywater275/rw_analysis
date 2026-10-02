package com.corrodinggames.rts.gameFramework.m;

import android.graphics.BitmapFactory.Options;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.m.ac;
import com.corrodinggames.rts.gameFramework.m.b;
import com.corrodinggames.rts.gameFramework.m.c;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class a implements Renderer {

   private static Options q = new Options();
   public boolean a;
   GL10 b;
   float c;
   float d;
   int e;
   int f;
   c[] g;
   ac h;
   int i;
   int j;
   int k;
   int l;
   int m;
   int n;
   String o;
   long p;


   public void onDrawFrame(GL10 var1) {
      if(this.e == -1) {
         Log.d("RustedWarfare", "---- render: no buffer is ready!");
      } else {
         long var2 = System.currentTimeMillis();
         int var4 = (int)(var2 - this.p);
         this.p = var2;
         this.l += var4;
         ++this.m;
         if(this.m == 10) {
            this.n = 10000 / this.l;
            this.l = 0;
            this.m = 0;
            this.o = this.n + "fps";
            Log.d("RustedWarfare", "render:" + this.o + ", this renders has " + this.g[this.e].b + " draws");
         }

         this.f = this.e;
         var1.glClear(16640);
         var1.glMatrixMode(5888);
         ac.a(var1, true, false);
         c var5 = this.g[this.f];
         this.i = -1;
         this.j = -1;
         this.k = -1;

         for(int var6 = 0; var6 < var5.b; ++var6) {
            b var7 = var5.a[var6];
            var7.a(var1);
         }

         ac.a(var1);
         this.f = -1;
      }
   }

   public void onSurfaceChanged(GL10 var1, int var2, int var3) {
      Log.d("RustedWarfare", "2d gl onSurfaceChanged:" + var2 + "," + var3);
      this.b = var1;
      this.c = (float)var3;
      this.d = (float)var2;
      var1.glViewport(0, 0, var2, var3);
      var1.glMatrixMode(5889);
      var1.glLoadIdentity();
      var1.glOrthof(0.0F, (float)var2, 0.0F, (float)var3, 0.0F, 1.0F);
      var1.glShadeModel(7424);
      var1.glEnable(3042);
      var1.glBlendFunc(770, 771);
      var1.glColor4x(65536, 65536, 65536, 65536);
      var1.glEnable(3553);
   }

   public void onSurfaceCreated(GL10 var1, EGLConfig var2) {
      Log.d("RustedWarfare", "2d gl onSurfaceCreated");
      this.b = var1;
      var1.glHint(3152, 4353);
      var1.glClearColor(0.3F, 0.3F, 0.5F, 1.0F);
      var1.glShadeModel(7424);
      var1.glDisable(2929);
      var1.glEnable(3553);
      var1.glDisable(3024);
      var1.glDisable(2896);
      var1.glClear(16640);
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3 != null) {
         ;
      }

      this.a = true;
   }

}
