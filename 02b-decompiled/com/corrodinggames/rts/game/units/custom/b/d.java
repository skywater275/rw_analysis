package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.custom.b.e;
import com.corrodinggames.rts.game.units.custom.b.f;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.m.ag;

public class d implements Comparable {

   String a;
   boolean b = false;
   boolean c;
   boolean d;
   boolean e;
   boolean f;
   boolean g;
   q h;
   boolean i;
   public float j;
   public boolean k;
   public boolean l;
   public boolean m;
   public boolean n;
   public int o;
   public float p;
   public boolean q;
   public LogicBoolean r;
   public LogicBoolean s;
   public LogicBoolean t;
   public boolean u;
   public e v;
   public e[] w;
   public float x;
   public int y;
   public boolean z;
   LogicBoolean A;
   LogicBoolean B;
   public com.corrodinggames.rts.gameFramework.m.e C;
   public float D;
   public float E;
   public LogicBoolean F;
   public f G;
   public float H;
   public boolean I;
   public int J;
   public int K;
   public int L;
   public boolean M;
   public LogicBoolean N;
   public int O;
   public int P;
   public float Q;
   public float R;
   public float S;
   public float T;
   public float U;
   public float V;
   public LogicBoolean W;
   public LogicBoolean X;
   public boolean Y;
   public boolean Z;
   public float aa;
   public float ab;
   public LogicBoolean ac;
   public LogicBoolean ad;
   public int ae;
   public boolean af;
   public int ag;
   public ag ah;
   public LogicBoolean ai;


   public strictfp d() {
      this.h = q.f;
      this.o = -1;
      this.p = 1.0F;
      this.J = -1;
      this.K = -1;
      this.L = -1;
      this.ae = -1;
      this.ag = -1;
   }

   public strictfp int a(d var1) {
      if(var1 == null) {
         return 0;
      } else {
         float var2 = this.H - var1.H;
         return var2 < 0.0F?-1:(var2 > 0.0F?1:0);
      }
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.a((d)var1);
   }
}
