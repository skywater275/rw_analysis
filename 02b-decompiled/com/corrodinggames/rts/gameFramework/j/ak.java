package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.al;
import java.util.ArrayList;
import java.util.Iterator;

public class ak {

   public long a;
   public ArrayList b = new ArrayList();
   public al c = new al(this, "Unit Pos");
   public al d = new al(this, "Unit Dir", false);
   public al e = new al(this, "Unit Hp");
   public al f = new al(this, "Unit Id");
   public al g = new al(this, "Waypoints");
   public al h = new al(this, "Waypoints Pos");
   public al i = new al(this, "Team Credits");
   public al j = new al(this, "UnitPaths");
   public al k = new al(this, "Unit Count");
   public al l = new al(this, "Team Info", false);
   public al m = new al(this, "Team 1 Credits", false);
   public al n = new al(this, "Team 2 Credits", false);
   public al o = new al(this, "Team 3 Credits", false);
   public al p = new al(this, "Command center2", false);
   public al q = new al(this, "Command center3", false);


   public strictfp void a() {
      al var2;
      for(Iterator var1 = this.b.iterator(); var1.hasNext(); var2.b = 0L) {
         var2 = (al)var1.next();
      }

   }

   public strictfp void b() {
      this.a = 0L;
      this.a();
      Iterator var1 = com.corrodinggames.rts.gameFramework.w.er.iterator();

      while(var1.hasNext()) {
         com.corrodinggames.rts.gameFramework.w var2 = (com.corrodinggames.rts.gameFramework.w)var1.next();
         if(var2 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2;
            this.a = (long)((float)this.a + var3.eo * 1000.0F);
            this.a = (long)((float)this.a + var3.ep * 1000.0F);
            this.a = (long)((float)this.a + var3.cu * 1.0F);
            this.a += var3.eh;
            this.c.b += (long)Float.floatToRawIntBits(var3.eo);
            this.c.b += (long)Float.floatToRawIntBits(var3.ep);
            this.d.b += (long)Float.floatToRawIntBits(var3.cg);
            this.e.b = (long)((float)this.e.b + var3.cu);
            this.f.b += var3.eh;
            if(var2 instanceof com.corrodinggames.rts.game.units.d.e) {
               com.corrodinggames.rts.game.units.d.e var4 = (com.corrodinggames.rts.game.units.d.e)var3;
               this.p.b = (long)((float)this.p.b + var4.f * 2.0F);
               this.q.b += (long)var4.h;
            }

            com.corrodinggames.rts.game.units.au var7 = var3.ar();
            if(var7 != null) {
               this.g.b += var7.j();
               this.h.b = (long)((float)this.h.b + var7.g() * 1000.0F);
            }

            this.j.b += var3.aL();
         }
      }

      for(int var5 = 0; var5 < com.corrodinggames.rts.game.n.c; ++var5) {
         com.corrodinggames.rts.game.n var6 = com.corrodinggames.rts.game.n.k(var5);
         if(var6 != null) {
            this.i.b += (long)((int)var6.o);
            if(var5 == 0) {
               this.m.b += (long)((int)var6.o);
            }

            if(var5 == 1) {
               this.n.b += (long)((int)var6.o);
            }

            if(var5 == 2) {
               this.o.b += (long)((int)var6.o);
            }

            this.k.b += (long)var6.w();
            this.l.b += (long)(var5 + var6.x * 100 + var6.r * 1000 + (var6.w?var5:0) * 10000);
         }
      }

   }
}
