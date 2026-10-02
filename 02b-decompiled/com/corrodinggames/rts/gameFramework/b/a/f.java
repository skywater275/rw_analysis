package com.corrodinggames.rts.gameFramework.b.a;

import android.opengl.GLES20;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.b.a.a;

public class f {

   public static int a(int var0, int var1, a[] var2) {
      int var3 = GLES20.glCreateProgram();
      if(var3 != 0) {
         GLES20.glAttachShader(var3, var0);
         GLES20.glAttachShader(var3, var1);
         a[] var4 = var2;
         int var5 = var2.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            a var7 = var4[var6];
            GLES20.glBindAttribLocation(var3, var7.a(), var7.b());
         }

         GLES20.glLinkProgram(var3);
         int[] var8 = new int[1];
         GLES20.glGetProgramiv(var3, '\u8b82', var8, 0);
         if(var8[0] == 0) {
            Log.a("Utilities", GLES20.glGetProgramInfoLog(var3));
            GLES20.glDeleteProgram(var3);
            var3 = 0;
         }
      }

      if(var3 == 0) {
         throw new RuntimeException("Error creating program.");
      } else {
         return var3;
      }
   }

   public static int a(int var0, String var1) {
      int var2 = GLES20.glCreateShader(var0);
      if(var2 != 0) {
         GLES20.glShaderSource(var2, var1);
         GLES20.glCompileShader(var2);
         int[] var3 = new int[1];
         GLES20.glGetShaderiv(var2, '\u8b81', var3, 0);
         if(var3[0] == 0) {
            Log.a("Utilities", "Shader fail info: " + GLES20.glGetShaderInfoLog(var2));
            GLES20.glDeleteShader(var2);
            var2 = 0;
         }
      }

      if(var2 == 0) {
         throw new RuntimeException("Error creating shader " + var0);
      } else {
         return var2;
      }
   }
}
