package com.corrodinggames.librocket;

import com.LibRocket$TextureHolder;
import com.corrodinggames.librocket.b;
import com.corrodinggames.rts.game.units.as;

public abstract class c extends LibRocket$TextureHolder {

   public String a;
   public boolean b;
   public boolean c;
   public boolean d;
   public float e;
   public as f;
   // $FF: synthetic field
   final b g;


   public c(b var1) {
      super(var1);
      this.g = var1;
      this.e = 1.0F;
   }

   public abstract boolean a();
}
