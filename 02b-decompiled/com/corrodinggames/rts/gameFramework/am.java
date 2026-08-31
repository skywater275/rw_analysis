package com.corrodinggames.rts.gameFramework;

import android.content.Context;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.am$1;
import com.corrodinggames.rts.gameFramework.am$2;
import com.corrodinggames.rts.gameFramework.an;
import com.corrodinggames.rts.gameFramework.aq;
import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.gameFramework.as;
import com.corrodinggames.rts.gameFramework.at;
import com.corrodinggames.rts.gameFramework.au;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class am {

   public static aq a = new an();
   Object b = new Object();
   Object c = new Object();
   volatile float d = 1.0F;
   au e;
   volatile boolean f = false;
   volatile boolean g = true;
   float h = 0.0F;
   int i = 0;
   boolean j = false;
   as k;
   boolean l;
   String m;
   boolean n;
   boolean o;
   float p;
   float q;
   float r;
   public boolean s;
   public String t;
   public boolean u;
   String v;
   Context w;
   boolean x;
   boolean y;
   int z;
   as A;
   boolean B;
   boolean C;
   float D;
   boolean E = false;
   public boolean F = false;
   boolean G = false;
   float H;
   ArrayList I = new ArrayList();
   static HashMap J = new HashMap();
   static int K = 0;
   boolean L;
   boolean M;
   long N = -1L;


   public float a() {
      l var1 = l.B();
      return var1.bQ.musicVolume * var1.bQ.masterVolume;
   }

   public boolean b() {
      return l.ax()?false:(this.u?false:this.a() > 0.01F);
   }

   public void a(Context var1) {
      this.w = var1;
      if(!l.ax()) {
         a.a(this);
         this.k = a.a();
         this.A = a.a();
         at.c();
         if(a.d()) {
            this.e = new au(this);
            this.e.start();
         }

      }
   }

   public void c() {
      if(!l.av()) {
         this.l = false;
         this.m = null;
         this.x = true;
         this.B = false;
      }

      this.y = true;
      this.u = false;
   }

   static ar a(String var0, boolean var1) {
      ar var2 = (ar)J.get(var0);
      if(var2 != null) {
         return var2;
      } else {
         ar var3;
         try {
            var3 = a.a(var0);
         } catch (ArithmeticException var5) {
            ++K;
            l.a("Error loading:" + var0, (Throwable)var5);
            if(K > 2 && K <= 4) {
               l.B().i("Failed to load music track:" + var0 + ". Music track skipped.");
            }

            if(!var1) {
               throw new RuntimeException(var5);
            }

            return null;
         } catch (OutOfMemoryError var6) {
            ++K;
            l.a("OutOfMemoryError loading:" + var0, (Throwable)var6);
            l.aC();
            System.gc();
            l.aC();
            if(K < 3) {
               l.B().i("Ran out of memory loading music track:" + var0 + ". Music track skipped.");
            }

            if(!var1) {
               throw new RuntimeException(var6);
            }

            return null;
         } catch (Exception var7) {
            ++K;
            l.a("Exception loading:" + var0, (Throwable)var7);
            if(K > 2 && K <= 4) {
               l.B().i("Unknown error loading music track:" + var0 + ". Music track skipped.");
            }

            if(!var1) {
               throw new RuntimeException(var7);
            }

            return null;
         }

         if(var1) {
            J.put(var0, var3);
         }

         return var3;
      }
   }

   public ArrayList d() {
      ArrayList var1 = new ArrayList();
      String[] var2 = at.a.b();
      int var3 = var2.length;

      int var4;
      String var5;
      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         var1.add(var5);
      }

      var2 = at.b.b();
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         var1.add(var5);
      }

      var2 = at.a.b();
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         var1.add(var5);
      }

      return var1;
   }

   public String a(at var1) {
      return this.a(var1, var1);
   }

   public String a(at var1, at var2) {
      l var5 = l.B();
      at var4;
      if(f.c(var1.b().length + var2.b().length) < var1.b().length) {
         var4 = var1;
      } else {
         var4 = var2;
      }

      String[] var3 = var4.b();
      return var4.a(var3[f.c(var3.length)]);
   }

   public synchronized void e() {
      this.s = true;
      this.u = false;
      this.t = null;
   }

   public synchronized void a(String var1) {
      this.s = true;
      this.u = false;
      this.t = var1;
   }

   public synchronized void a(float var1) {
      if(!l.ax()) {
         if(!a.d()) {
            if(!this.L) {
               this.b(var1);
            }

            this.g = true;
         }

         this.N = l.V();
         l var2 = l.B();
         if(var2.bT.H.a()) {
            this.e();
         }

         if(this.v != null) {
            com.corrodinggames.rts.gameFramework.j.ad.a((String)null, this.v);
            this.v = null;
         }

         if(this.p != this.a()) {
            this.p = this.a();
            this.o = true;
         }

         Object var3 = this.c;
         synchronized(this.c) {
            this.d = var1;
            if(this.L) {
               if(!this.M) {
                  this.M = true;
                  l.n("Music subsystem crashed, music has been disabled to keep your game running. Please send your logs.");
               }

            } else {
               if(!this.g) {
                  this.h += var1;
                  ++this.i;
                  if(this.h > 320.0F && this.i > 80 && !this.j) {
                     this.j = true;
                     l.n("Lockup detected in music subsystem");
                  }
               } else {
                  this.h = 0.0F;
                  this.i = 0;
               }

               this.g = false;
               this.f = true;
               this.c.notifyAll();
            }
         }
      }
   }

   public String b(String var1) {
      var1 = f.k(var1);
      var1 = f.g(var1);
      var1 = var1.replace("[noloop]", "");
      var1 = var1.replace("_", " ");
      return var1;
   }

   public boolean b(float var1) {
      try {
         this.c(var1);
         return true;
      } catch (Exception var5) {
         l.a("Music system crashed", (Throwable)var5);
         this.L = true;
         l.e("Stopping music");

         try {
            this.g();
         } catch (Exception var4) {
            l.a("crash stopping music", (Throwable)var4);
         }

         return false;
      }
   }

   public void c(float var1) {
      if(!l.ax()) {
         a.a(var1);
         if(!this.b()) {
            if(this.l && this.k.c()) {
               this.g();
               this.l = false;
               this.B = false;
            }

         } else {
            boolean var2 = false;
            if(!this.l) {
               var2 = true;
            }

            if(this.n) {
               if(!this.C) {
                  this.q += var1;
               }

               if(this.q > 600.0F) {
                  this.r += var1;
                  if(this.r > 100.0F) {
                     this.r = 0.0F;
                     if(!this.l || !this.k.c()) {
                        var2 = true;
                        this.q = 0.0F;
                     }
                  }
               }
            } else {
               this.q += var1;
               if(this.q > 3600.0F) {
                  l.e("Next music track, timer:" + this.q);
                  var2 = true;
                  this.q = 0.0F;
               }
            }

            if(this.y) {
               com.corrodinggames.rts.gameFramework.i.b var3 = com.corrodinggames.rts.gameFramework.f.g.z();
               if(var3 != null && var3.N) {
                  var2 = true;
               }

               this.y = false;
            }

            boolean var15;
            if(var2 || this.s) {
               var15 = this.s;
               String var4 = this.t;
               if(this.s) {
                  l.e("Next music track requested");
                  this.s = false;
                  this.q = 0.0F;
                  this.t = null;
               }

               String var5 = null;
               boolean var6 = false;
               com.corrodinggames.rts.gameFramework.i.b var7 = null;
               if(var4 != null) {
                  ArrayList var8 = l.B().bZ.i();
                  var8.addAll(this.d());
                  String var9 = var4;
                  if(var4.endsWith(".ogg") || var4.endsWith(".wav")) {
                     this.b(var4);
                  }

                  Iterator var10 = var8.iterator();

                  while(var10.hasNext()) {
                     String var11 = (String)var10.next();
                     String var12 = this.b(var11);
                     if(var12.equalsIgnoreCase(var9)) {
                        var6 = true;
                        var5 = var11;
                        break;
                     }
                  }

                  if(var5 == null) {
                     l.e("Failed to find requested music: " + var9);
                  }
               }

               com.corrodinggames.rts.gameFramework.i.b var17 = com.corrodinggames.rts.gameFramework.f.g.z();
               if(var5 == null && var17 != null && var17.P < 10 && var17.N) {
                  ArrayList var19 = var17.q();
                  if(var19.size() > 0) {
                     var6 = true;
                     var7 = var17;
                     var5 = (String)var19.get(f.a(0, var19.size() - 1));
                     if(var15 || this.I.contains(var5)) {
                        for(int var21 = 0; var21 < 30 && (var5.equals(this.m) || this.I.contains(var5)); ++var21) {
                           var5 = (String)var19.get(f.a(0, var19.size() - 1));
                           if(var21 > 20) {
                              this.I.clear();
                           }
                        }
                     }

                     l.e("Playing music from mod:" + var17.a() + " - \'" + var5 + "\'");
                  }
               }

               if(var5 == null) {
                  if(this.x) {
                     var5 = this.a(at.a);
                  } else {
                     var5 = this.a(at.b, at.a);
                  }

                  if(var15 || this.I.contains(var5)) {
                     for(int var20 = 0; var20 < 30 && (var5.equals(this.m) || this.I.contains(var5)); ++var20) {
                        var5 = this.a(at.b, at.a);
                        if(var20 > 20) {
                           this.I.clear();
                        }
                     }
                  }
               }

               if(!var5.equals(this.m)) {
                  this.m = var5;
                  this.x = false;
                  this.q = 0.0F;
                  this.n = var6 || var5.contains("[noloop]");
                  this.I.add(var5);
                  if(this.I.size() > 4) {
                     this.I.remove(0);
                  }

                  if(var15) {
                     this.v = "Now playing: " + this.b(var5);
                  }

                  as var22 = this.k;
                  this.k = this.A;
                  this.A = var22;

                  ar var23;
                  try {
                     var23 = a(var5, false);
                  } catch (RuntimeException var13) {
                     var13.printStackTrace();
                     if(this.z < 3) {
                        this.v = "Failed to open music track: " + var5;
                        ++this.z;
                     }

                     if(var7 != null) {
                        ++var7.P;
                     }

                     return;
                  }

                  try {
                     this.k.a(var23);
                     this.k.a(!this.n);
                  } catch (RuntimeException var14) {
                     var14.printStackTrace();
                     if(this.z < 3) {
                        this.v = "Failed to play music track: " + var5;
                        ++this.z;
                     }

                     if(var7 != null) {
                        ++var7.P;
                     }

                     return;
                  }

                  this.E = false;
                  if(!var15 && this.B) {
                     this.E = true;
                  }

                  if(this.l) {
                     this.B = true;
                  }

                  this.C = true;
                  this.G = false;
                  this.D = 1.0F;
                  this.l = true;
               } else if(var15) {
                  l.e("Same music found");
               }
            }

            if(this.C || this.o) {
               var15 = a.c();
               if(!var15) {
                  if(this.F) {
                     this.D -= var1 * 0.1F;
                  } else {
                     this.D -= var1 * 0.006F;
                  }
               } else if(this.F) {
                  this.D -= var1 * 0.1F;
               } else if(this.E) {
                  this.D -= var1 * 0.003F;
               } else {
                  this.D -= var1 * 0.008F;
               }

               float var16;
               float var18;
               if(!var15) {
                  var16 = this.D * this.a();
                  var18 = (1.0F - this.D) * this.a();
               } else {
                  var16 = (this.D * 2.0F - 1.0F) * this.a();
                  var18 = (1.0F - this.D * 2.0F) * this.a();
               }

               var16 = f.b(var16, 0.0F, 1.0F);
               var18 = f.b(var18, 0.0F, 1.0F);
               if(this.C) {
                  if(this.D <= 0.0F) {
                     this.C = false;
                     this.E = false;
                     if(this.B && !this.G) {
                        this.G = true;
                        this.A.d();
                     }

                     if(this.l) {
                        this.k.a(this.a(), this.a());
                     }
                  } else {
                     this.H += var1;
                     if(this.H > 10.0F) {
                        this.H = 0.0F;
                        if(this.B && !this.G) {
                           this.A.a(var16, var16);
                           if(var16 < 0.02F) {
                              this.G = true;
                              this.A.d();
                           }
                        }

                        if(this.l) {
                           this.k.a(var18, var18);
                        }
                     }
                  }
               } else if(this.l) {
                  this.k.a(var18, var18);
               }
            }

            this.o = false;
         }
      }
   }

   public void f() {
      Log.a("RustedWarfare", "Music:pause()");
      am$1 var1 = new am$1(this);
      var1.start();
   }

   public void g() {
      if(this.l) {
         this.k.a();
      }

      if(this.B) {
         this.A.a();
      }

   }

   public void h() {
      am$2 var1 = new am$2(this);
      var1.start();
   }

   public void i() {
      a.b();
      if(this.B) {
         this.A.d();
         this.A.e();
      }

      if(this.k != null) {
         this.k.d();
         this.k.e();
      }

      this.k = null;
      this.m = null;
      this.l = false;
   }

   public boolean j() {
      return this.C;
   }

}
