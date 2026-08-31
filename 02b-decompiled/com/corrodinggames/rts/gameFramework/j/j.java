package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.h;
import java.io.OutputStream;

public class j extends OutputStream {

   boolean a;
   // $FF: synthetic field
   final h b;


   public j(h var1) {
      this.b = var1;
      this.a = true;
   }

   public void write(int var1) {
      com.corrodinggames.rts.gameFramework.l.g("SteamSocketOutputStream: Slow write: " + var1);
      byte[] var2 = new byte[]{(byte)var1};
      this.write(var2);
   }

   public void write(byte[] var1, int var2, int var3) {
      if(this.b.c) {
         com.corrodinggames.rts.gameFramework.l.e("cannot write steam socket closed");
      } else {
         com.corrodinggames.rts.gameFramework.l.e("Forwarded message to client: " + this.b.b + " with old method");
      }
   }

   public void write(byte[] var1) {
      this.write(var1, 0, var1.length);
   }
}
