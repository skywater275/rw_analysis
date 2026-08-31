package com.corrodinggames.rts.java.a;

import java.nio.ByteBuffer;
import org.newdawn.slick.opengl.ImageData;

public class a implements ImageData {

   ImageData a;
   ByteBuffer b;


   public a(ImageData var1, ByteBuffer var2) {
      this.a = var1;
      this.b = var2;
   }

   public int getDepth() {
      return this.a.getDepth();
   }

   public int getHeight() {
      return this.a.getHeight();
   }

   public ByteBuffer getImageBufferData() {
      return this.b;
   }

   public int getTexHeight() {
      return this.a.getTexHeight();
   }

   public int getTexWidth() {
      return this.a.getTexWidth();
   }

   public int getWidth() {
      return this.a.getWidth();
   }
}
