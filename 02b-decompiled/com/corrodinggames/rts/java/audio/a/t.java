package com.corrodinggames.rts.java.audio.a;

import java.io.ByteArrayOutputStream;

public class t extends ByteArrayOutputStream {

   public t(int var1) {
      super(var1);
   }

   public synchronized byte[] toByteArray() {
      return this.count == this.buf.length?this.buf:super.toByteArray();
   }
}
