package com.corrodinggames.rts.gameFramework.utility;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class j extends InputStream {

   InputStream a;
   String b;
   String c;
   boolean d;
   String e;


   public boolean a() {
      return this.a instanceof FileInputStream?true:!com.corrodinggames.rts.gameFramework.l.av() && this.c != null;
   }

   public FileDescriptor b() {
      if(this.a instanceof FileInputStream) {
         FileInputStream var4 = (FileInputStream)this.a;
         return var4.getFD();
      } else if(!com.corrodinggames.rts.gameFramework.l.av() && this.c != null) {
         Context var1 = com.corrodinggames.rts.appFramework.c.a();
         AssetManager var2 = var1.d();
         AssetFileDescriptor var3 = var2.b(this.c);
         return var3.getFileDescriptor();
      } else {
         throw new RuntimeException("AssetInputStream: unexpected stream for: " + this.b);
      }
   }

   private j() {}

   public j(InputStream var1, String var2, String var3) {
      if(var1 == null) {
         throw new FileNotFoundException();
      } else {
         this.a = var1;
         this.b = var2;
         this.c = var3;
         this.e = com.corrodinggames.rts.gameFramework.l.U();
      }
   }

   public j(FileInputStream var1, String var2) {
      if(var1 == null) {
         throw new FileNotFoundException();
      } else {
         this.a = var1;
         this.b = var2;
         this.e = com.corrodinggames.rts.gameFramework.l.U();
      }
   }

   public j(InputStream var1, String var2) {
      if(var1 == null) {
         throw new FileNotFoundException();
      } else {
         this.a = var1;
         this.b = var2;
         this.e = com.corrodinggames.rts.gameFramework.l.U();
      }
   }

   public long c() {
      if(!com.corrodinggames.rts.gameFramework.l.av()) {
         return -1L;
      } else if(this.b == null) {
         return -2L;
      } else {
         File var1 = new File(this.b);
         return var1.lastModified();
      }
   }

   public String d() {
      return this.b;
   }

   public int available() {
      return this.a.available();
   }

   public void close() {
      this.d = true;
      this.a.close();
   }

   protected void finalize() {
      if(!this.d) {
         com.corrodinggames.rts.gameFramework.l.b("AssetInputStream was finalized with being closed");
         com.corrodinggames.rts.gameFramework.l.b(this.e);
      }

   }

   public boolean equals(Object var1) {
      return this.a.equals(var1);
   }

   public int hashCode() {
      return this.a.hashCode();
   }

   public void mark(int var1) {
      this.a.mark(var1);
   }

   public boolean markSupported() {
      return this.a.markSupported();
   }

   public int read() {
      return this.a.read();
   }

   public int read(byte[] var1, int var2, int var3) {
      return this.a.read(var1, var2, var3);
   }

   public int read(byte[] var1) {
      return this.a.read(var1);
   }

   public void reset() {
      this.a.reset();
   }

   public long skip(long var1) {
      return this.a.skip(var1);
   }

   public String toString() {
      return this.a.toString();
   }
}
