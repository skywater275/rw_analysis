package com.corrodinggames.rts.gameFramework.b;

import android.opengl.GLES20;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.b.n;
import com.corrodinggames.rts.gameFramework.b.q;

class s extends q {

   public s(String var1) {
      super(var1);
   }

   public void a(int var1) {
      if(this.c != var1) {
         this.a = GLES20.glGetUniformLocation(var1, this.b);
         this.c = var1;
         n.r();
         if(this.a == -1) {
            Log.d(n.s(), "loadHandle: Failed to find: " + this.b);
         }
      }

   }
}
