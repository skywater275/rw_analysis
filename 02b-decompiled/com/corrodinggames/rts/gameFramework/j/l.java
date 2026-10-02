package com.corrodinggames.rts.gameFramework.j;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class l {

   public String a;
   public ByteArrayInputStream b;
   public DataInputStream c;


   public strictfp l(byte[] var1, boolean var2, boolean var3) {
      this.b = new ByteArrayInputStream(var1);
      Object var4;
      if(var2) {
         var4 = new BufferedInputStream(new GZIPInputStream(this.b));
      } else {
         var4 = this.b;
      }

      this.c = new DataInputStream((InputStream)var4);
   }
}
