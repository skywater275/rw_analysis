package com.corrodinggames.rts.gameFramework.j;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class at {

   public GZIPOutputStream a;
   public BufferedOutputStream b;
   public String c;
   public ByteArrayOutputStream d = new ByteArrayOutputStream();
   public DataOutputStream e;
   public boolean f = false;


   public strictfp void a() {
      this.e.flush();
      if(this.b != null) {
         this.b.flush();
      }

      if(this.a != null) {
         this.a.finish();
      }

   }

   public strictfp void b() {
      if(!this.f) {
         this.e.close();
      } else {
         com.corrodinggames.rts.gameFramework.l.g("TODO: Cannot yet close wrapped stream");
      }

   }

   public strictfp at(boolean var1) {
      Object var2;
      if(var1) {
         this.a = new GZIPOutputStream(this.d);
         this.b = new BufferedOutputStream(this.a);
         var2 = this.b;
      } else {
         var2 = this.d;
      }

      this.e = new DataOutputStream((OutputStream)var2);
   }
}
