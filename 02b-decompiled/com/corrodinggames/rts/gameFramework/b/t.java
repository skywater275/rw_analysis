package com.corrodinggames.rts.gameFramework.b;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.b.n;
import com.corrodinggames.rts.gameFramework.b.u;

public class t implements u {

   private final int[] a = new int[1];


   public int a() {
      GLES20.glGenTextures(1, this.a, 0);
      n.q();
      return this.a[0];
   }
}
