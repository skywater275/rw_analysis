/*
 * v19.115i 新建: 02b gameFramework.j.l.java 27 行直译 (网络块读取器)
 * InputNetStream.a(boolean,boolean)/d(String) 使用 (03 InputNetStream 引用 com.corrodinggames.rts.gameFramework.network.l)
 */
package com.corrodinggames.rts.gameFramework.network;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.IOException;

public class l {
   public String a;
   public ByteArrayInputStream b;
   public DataInputStream c;

   /* 02b j/l.java: GZIPInputStream 构造抛 IOException (R8 移除 throws) */
   public l(byte[] var1, boolean var2, boolean var3) throws IOException {
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
