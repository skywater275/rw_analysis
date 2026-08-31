package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.c;
import com.corrodinggames.rts.game.units.custom.d;
import com.corrodinggames.rts.game.units.custom.f;
import com.corrodinggames.rts.game.units.custom.j;

public final class e {

   public f a;
   float b;
   float c;
   float d = 1.0F;
   boolean e = false;
   boolean f = false;
   boolean g;
   boolean h;
   boolean i;
   int j;
   float k = 0.0F;
   float l = 0.05F;
   j m;
   float[] n;


   public strictfp e(j var1) {
      this.m = var1;
   }

   public strictfp void a(f var1, int var2) {
      this.a(var1, var2, false);
   }

   public strictfp void a(f var1, int var2, boolean var3) {
      if(var1 != null && var1.a()) {
         if(!this.i && (!this.f || !this.e) || var2 > this.j || var3 && var1 == this.a) {
            this.i = true;
            if(var1 != this.a || var3 || this.g) {
               float var4 = 0.0F;
               if(this.a != null && this.e) {
                  var4 = this.a.i;
               }

               this.a = var1;
               this.j = var2;
               this.c();
               this.f = var3;
               if(var3) {
                  this.h = false;
               } else {
                  this.h = true;
               }

               this.b = -1.0F;
               this.c = -1.0F;
               this.d = 1.0F;
               this.g = false;
               float var5 = var1.h;
               if(var4 > var5) {
                  var5 = var4;
               }

               if(var5 > 0.0F) {
                  this.k = 1.0F;
                  this.l = var5;
               } else {
                  this.k = 0.0F;
               }
            }

            this.e = true;
         }
      }
   }

   public strictfp void a() {
      if(this.a != null) {
         boolean var1 = true;
         this.b(var1);
      }

      this.e = false;
      this.a = null;
      this.j = -1;
   }

   public strictfp void b() {
      if(this.a != null) {
         if(!this.g) {
            float var1 = this.a.i;
            if(var1 > 0.0F) {
               this.g = true;
               this.c();
               this.h = false;
               this.j = -1;
               this.k = 1.0F;
               this.l = var1;
               return;
            }
         }

         boolean var2 = true;
         this.b(var2);
      }

      this.e = false;
      this.a = null;
      this.j = -1;
   }

   public strictfp void a(float var1) {
      if(this.e) {
         this.c = this.b;
         if(this.b < 0.0F) {
            this.b = 0.0F;
         }

         float var2 = this.d;
         if(this.a != null && this.a.j != null) {
            var2 *= this.a.j.readNumber(this.m);
         }

         this.b += var2 * var1;
         if(this.h && !this.i) {
            this.b();
         }

         this.i = false;
         if(this.e) {
            if(this.k > 0.0F) {
               this.k -= this.l * var1;
            } else if(this.g) {
               this.b();
               return;
            }

            if(!this.g && this.a != null) {
               if(this.a.g) {
                  if(this.b > this.a.n) {
                     this.a(false);
                     this.b = this.a.n;
                     this.d = -1.0F;
                  } else if(this.b < 0.0F) {
                     this.b = 0.0F;
                     this.d = 1.0F;
                     if(this.f) {
                        this.b();
                        if(!this.g) {
                           return;
                        }
                     }
                  }
               } else {
                  if(this.b > this.a.n) {
                     if(this.f) {
                        this.a(false);
                        this.b();
                        if(!this.g) {
                           return;
                        }
                     } else {
                        this.a(false);
                        this.b = 0.0F;
                        this.d = 1.0F;
                     }
                  }

                  if(this.b < 0.0F && !this.f && var2 < 0.0F) {
                     this.b = this.a.n;
                  }
               }
            }

            boolean var3 = false;
            if(this.g) {
               var3 = true;
            }

            this.b(var3);
         }

      }
   }

   strictfp void c() {
      com.corrodinggames.rts.gameFramework.utility.m var1 = this.a.l;
      if(this.n == null || this.n.length < var1.size()) {
         this.n = new float[var1.size()];
      }

      for(int var2 = 0; var2 < var1.size(); ++var2) {
         c var3 = (c)var1.get(var2);
         d var4 = var3.a;
         if(var4 == d.b) {
            this.n[var2] = this.m.c;
         } else if(var4 == d.a) {
            this.n[var2] = -99.0F;
         } else {
            com.corrodinggames.rts.game.units.custom.b.i var5;
            if(var4 == d.c) {
               if(this.m.dT != null && var3.b < this.m.dT.length) {
                  var5 = this.m.dT[var3.b];
                  this.n[var2] = var5.p;
               } else {
                  this.n[var2] = 0.0F;
                  com.corrodinggames.rts.gameFramework.l.b("setBaseBlendValues: Target leg out of range for: " + this.m.dt().i());
               }
            } else if(var4 == d.d) {
               if(this.m.dT != null && var3.b < this.m.dT.length) {
                  var5 = this.m.dT[var3.b];
                  this.n[var2] = var5.q;
               }
            } else if(var4 == d.e) {
               if(this.m.dT != null && var3.b < this.m.dT.length) {
                  this.m.dT[var3.b].r = com.corrodinggames.rts.gameFramework.f.a(this.m.dT[var3.b].r, false);
                  this.n[var2] = this.m.dT[var3.b].r;
               }
            } else if(var4 == d.f) {
               if(this.m.dT != null && var3.b < this.m.dT.length) {
                  this.n[var2] = this.m.dT[var3.b].d;
               }
            } else if(var4 == d.j) {
               if(this.m.dT != null && var3.b < this.m.dT.length) {
                  this.n[var2] = this.m.dT[var3.b].s;
               }
            } else if(var4 != d.i) {
               this.n[var2] = 0.0F;
               com.corrodinggames.rts.gameFramework.l.b("Unsupported blend type:" + var4);
            }
         }
      }

   }

   strictfp void a(boolean var1) {
      com.corrodinggames.rts.gameFramework.utility.m var2 = this.a.l;

      for(int var3 = 0; var3 < var2.size(); ++var3) {
         c var4 = (c)var2.get(var3);
         d var5 = var4.a;
         if(var5 == d.i) {
            var4.a(this.m, this.c, this.b, var1);
         }
      }

   }

   strictfp void b(boolean var1) {
      com.corrodinggames.rts.gameFramework.utility.m var2 = this.a.l;

      for(int var3 = 0; var3 < var2.size(); ++var3) {
         c var4 = (c)var2.get(var3);
         d var5 = var4.a;
         if(var5 != d.a || this.m.el || var1) {
            float var6;
            if(var1) {
               var6 = 0.0F;
               if(var5 == d.b) {
                  var6 = 1.0F;
               } else if(var5 == d.a) {
                  var6 = (float)this.m.x.Y;
               } else if(var5 == d.j) {
                  var6 = 1.0F;
                  ba[] var7 = this.m.x.ax;
                  if(var7 != null && var4.b < var7.length) {
                     var6 = var7[var4.b].r;
                  }
               }
            } else {
               var6 = var4.b(this.b);
            }

            if(this.k > 0.0F && var5 != d.a) {
               var6 = var6 * (1.0F - this.k) + this.n[var3] * this.k;
            }

            if(var5 == d.a) {
               this.m.a = (int)var6;
            } else if(var5 == d.b) {
               this.m.c = var6;
            } else {
               com.corrodinggames.rts.game.units.custom.b.i var8;
               if(var5 == d.c) {
                  if(this.m.dT != null && var4.b < this.m.dT.length) {
                     var8 = this.m.dT[var4.b];
                     var8.p = var6;
                     var8.k = true;
                     var8.o = true;
                  }
               } else if(var5 == d.d) {
                  if(this.m.dT != null && var4.b < this.m.dT.length) {
                     var8 = this.m.dT[var4.b];
                     var8.q = var6;
                     var8.k = true;
                     var8.o = true;
                  }
               } else if(var5 == d.e) {
                  if(this.m.dT != null && var4.b < this.m.dT.length) {
                     this.m.dT[var4.b].r = var6;
                  }
               } else if(var5 == d.f) {
                  if(this.m.dT != null && var4.b < this.m.dT.length) {
                     this.m.dT[var4.b].d = var6;
                  }
               } else if(var5 == d.j) {
                  com.corrodinggames.rts.game.units.custom.b.i[] var9 = this.m.dT;
                  if(var9 != null && var4.b < var9.length) {
                     var9[var4.b].s = var6;
                  }
               } else if(var5 != d.g && var5 == d.i) {
                  var4.a(this.m, this.c, this.b, var1);
               }
            }
         }
      }

   }

   public strictfp boolean a(f var1) {
      return this.e && this.a == var1;
   }
}
