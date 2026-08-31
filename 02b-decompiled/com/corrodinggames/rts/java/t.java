package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.s;
import java.nio.ByteBuffer;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.MiscUtils;

public class t implements ImageData {

   int a;
   private int c;
   private int d;
   private int e;
   private int f;
   private byte[] g;
   // $FF: synthetic field
   final s b;


   public t(s var1, Image var2) {
      this.b = var1;
      Texture var3 = var2.getTexture();
      this.g = var3.getTextureData();
      this.a = var3.hasAlpha()?32:24;
      this.c = var3.getImageWidth();
      this.d = var3.getImageHeight();
      this.e = var3.getTextureWidth();
      this.f = var3.getTextureHeight();
   }

   public int getDepth() {
      return this.a;
   }

   public int getWidth() {
      return this.c;
   }

   public int getHeight() {
      return this.d;
   }

   public int getTexWidth() {
      return this.e;
   }

   public int getTexHeight() {
      return this.f;
   }

   public ByteBuffer getImageBufferData() {
      ByteBuffer var1 = MiscUtils.createByteBuffer(this.g.length);
      var1.put(this.g);
      var1.flip();
      return var1;
   }

   public byte[] a() {
      return this.g;
   }
}
