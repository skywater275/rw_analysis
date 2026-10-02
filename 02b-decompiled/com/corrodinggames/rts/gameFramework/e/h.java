package com.corrodinggames.rts.gameFramework.e;

import java.io.IOException;
import java.io.InputStream;

class h {

   public InputStream a;


   public h(InputStream var1) {
      this.a = var1;
   }

   public void a() {
      try {
         if(this.a != null) {
            this.a.close();
         }
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }
}
