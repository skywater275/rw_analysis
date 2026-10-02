package com.corrodinggames.rts.gameFramework.j;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ax {

   public BufferedOutputStream a;
   public String b;
   public ByteArrayOutputStream c = new ByteArrayOutputStream();
   public PrintStream d;
   public boolean e = false;


   public strictfp void a() {
      this.d.flush();
      if(this.a != null) {
         this.a.flush();
      }

   }

   public strictfp void b() {
      if(!this.e) {
         this.d.close();
      } else {
         com.corrodinggames.rts.gameFramework.l.g("TODO: Cannot yet close wrapped stream");
      }

   }

   public strictfp ax(boolean var1) {
      Object var2;
      if(var1) {
         this.a = new BufferedOutputStream(this.c);
         var2 = this.a;
      } else {
         var2 = this.c;
      }

      this.d = new PrintStream((OutputStream)var2);
   }
}
