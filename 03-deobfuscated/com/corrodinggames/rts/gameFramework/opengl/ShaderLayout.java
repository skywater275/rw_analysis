/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import com.corrodinggames.rts.gameFramework.opengl.AttributeLocation;
import com.corrodinggames.rts.gameFramework.opengl.GLUniform;
import com.corrodinggames.rts.gameFramework.opengl.UniformLocation;

public class ShaderLayout {
    // 02b b/z.java: a/b/c/d/e 均为 q (GLUniform) 类型, 03 误标 KeyCodeMapper/ProjectileType2
    GLUniform a = new AttributeLocation("aPosition");
    GLUniform b = new AttributeLocation("aTextureCoordinate");
    GLUniform c = new AttributeLocation("aColor");
    GLUniform d = new UniformLocation("uProjection");
    GLUniform e = new UniformLocation("u_texture");
    GLUniform[] f = new GLUniform[]{this.a, this.b, this.c, this.d, this.e};
}
