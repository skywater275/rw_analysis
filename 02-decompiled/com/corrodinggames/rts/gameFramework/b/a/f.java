/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.b.a;

import android.opengl.GLES20;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.b.a.a;

public class f {
    public static int a(int n, int n2, a[] aArray) {
        int n3 = GLES20.glCreateProgram();
        if (n3 != 0) {
            GLES20.glAttachShader((int)n3, (int)n);
            GLES20.glAttachShader((int)n3, (int)n2);
            for (a a2 : aArray) {
                GLES20.glBindAttribLocation((int)n3, (int)a2.a(), (String)a2.b());
            }
            GLES20.glLinkProgram((int)n3);
            int[] nArray = new int[1];
            GLES20.glGetProgramiv((int)n3, (int)35714, (int[])nArray, (int)0);
            if (nArray[0] == 0) {
                Log.a("Utilities", GLES20.glGetProgramInfoLog((int)n3));
                GLES20.glDeleteProgram((int)n3);
                n3 = 0;
            }
        }
        if (n3 == 0) {
            throw new RuntimeException("Error creating program.");
        }
        return n3;
    }

    public static int a(int n, String string) {
        int n2 = GLES20.glCreateShader((int)n);
        if (n2 != 0) {
            GLES20.glShaderSource((int)n2, (String)string);
            GLES20.glCompileShader((int)n2);
            int[] nArray = new int[1];
            GLES20.glGetShaderiv((int)n2, (int)35713, (int[])nArray, (int)0);
            if (nArray[0] == 0) {
                Log.a("Utilities", "Shader fail info: " + GLES20.glGetShaderInfoLog((int)n2));
                GLES20.glDeleteShader((int)n2);
                n2 = 0;
            }
        }
        if (n2 == 0) {
            throw new RuntimeException("Error creating shader " + n);
        }
        return n2;
    }
}
