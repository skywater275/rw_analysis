package com.corrodinggames.rts.gameFramework.k;

import android.graphics.Paint;
import android.os.Debug;
import android.util.Log;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.gameFramework.k.f;
import com.corrodinggames.rts.gameFramework.k.g;
import com.corrodinggames.rts.gameFramework.k.i;
import com.corrodinggames.rts.gameFramework.k.k;
import com.corrodinggames.rts.gameFramework.k.l;
import com.corrodinggames.rts.gameFramework.k.m;
import com.corrodinggames.rts.gameFramework.k.n;
import com.corrodinggames.rts.gameFramework.k.p;
import java.util.Iterator;
import java.util.LinkedList;

public final class o implements Runnable {

   private final l C;
   boolean a = true;
   public byte[] b;
   public byte[] c;
   public byte[] d;
   public short[] e;
   public byte[] f;
   private k D;
   int g;
   int h;
   int i = 5;
   int j = 0;
   int k = 0;
   int[][] l;
   byte[][] m;
   i n;
   final m o = new m();
   final m p = new m();
   final p q = new p();
   final p r = new p();
   volatile boolean s = true;
   static LinkedList t = new LinkedList();
   static int u;
   int v;
   Thread w;
   Object x = new Object();
   long y;
   long z;
   Object A = new Object();
   Paint B = new Paint();


   public strictfp void a(k var1) {
      if(!this.s) {
         throw new RuntimeException("setupNewPath: last path not yet finished");
      } else {
         this.s = false;
         this.a(var1.o, var1);
         var1.v = true;
         this.D = var1;
      }
   }

   public strictfp void a() {
      if(this.w == null) {
         throw new RuntimeException("thread==null");
      } else {
         Object var1 = this.x;
         synchronized(this.x) {
            this.x.notifyAll();
         }
      }
   }

   public strictfp void run() {
      com.corrodinggames.rts.gameFramework.l.aq();

      while(this.a) {
         Object var1 = this.x;
         synchronized(this.x) {
            if(this.D == null) {
               try {
                  this.x.wait();
               } catch (InterruptedException var4) {
                  var4.printStackTrace();
               }
            }
         }

         if(this.D != null) {
            this.b();
         }
      }

   }

   public strictfp void b() {
      LinkedList var1;
      if(this.D instanceof f) {
         this.f();
         var1 = t;
      } else {
         var1 = this.e();
      }

      Object var2 = this.C.G;
      synchronized(this.C.G) {
         this.D.f();
         this.b = null;
         this.c = null;
         this.d = null;
         this.e = null;
         this.f = null;
         this.D.a(var1);
         this.D = null;
         this.s = true;
         this.C.G.notifyAll();
      }
   }

   strictfp o(l var1) {
      this.C = var1;
      this.v = u++;
   }

   public synchronized strictfp void c() {
      if(this.w != null) {
         throw new RuntimeException("thread!=null");
      } else {
         this.w = new Thread(this);
         this.w.setName("PathSolver-" + this.v);
         this.w.setPriority(10);
         this.w.setDaemon(true);
         this.w.start();
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.b.b var1) {
      this.g = var1.u.n;
      this.h = var1.u.o;
      this.l = new int[2][this.g * this.h];
      this.m = new byte[2][this.g * this.h];
      this.d();
   }

   public strictfp void d() {
      int var1 = this.g * this.h * 42 + 1;
      this.i += var1;
      boolean var2 = false;
      if(this.i > Integer.MAX_VALUE - var1 || this.i < 0 || var2) {
         this.i = 5;

         for(int var3 = 0; var3 < 2; ++var3) {
            for(int var4 = 0; var4 < this.g; ++var4) {
               for(int var5 = 0; var5 < this.h; ++var5) {
                  this.l[var3][var4 * this.h + var5] = -1;
               }
            }
         }
      }

   }

   public final strictfp int a(int var1, int var2) {
      return this.b[var1 * this.h + var2] != -1 && this.c[var1 * this.h + var2] != -1 && this.d[var1 * this.h + var2] != -1?this.b[var1 * this.h + var2] + this.c[var1 * this.h + var2] + this.d[var1 * this.h + var2] * 10:-1;
   }

   public strictfp void a(ao var1, k var2) {
      if(var1 == null) {
         throw new RuntimeException("MovementType is null");
      } else {
         i var3 = this.C.a(var1);
         if(var3 == null) {
            throw new RuntimeException("Could not get costs for:" + var1.toString());
         } else {
            this.n = var3;
            this.b = var2.y;
            this.c = var2.z;
            this.d = var2.A;
            this.e = var2.B;
            this.f = var2.C;
            if(this.b == null) {
               throw new RuntimeException("linkToPath failed mapCosts_mapCosts is null");
            } else if(this.c == null) {
               throw new RuntimeException("linkToPath failed mapCosts_buildingCosts is null");
            } else if(this.d == null) {
               throw new RuntimeException("linkToPath failed mapCosts_objectCosts is null");
            }
         }
      }
   }

   public strictfp void a(ao var1, short var2, short var3, Float var4) {
      if(l.a) {
         Log.d("RustedWarfare", "path start is:" + var2 + "," + var3);
      }

      p var5 = new p(var2, var3);
      var5.a(this, (byte)0, this.i);
      if(var4 == null) {
         var5.a(this, (byte)0, (byte)0);
         var5.b(this, (byte)0, true);
      } else {
         var5.a(this, (byte)0, var4.floatValue());
         var5.b(this, (byte)0, true);
      }

      var5.a(this, (byte)0, false);
   }

   public strictfp void a(short var1, short var2, short var3) {
      if(l.a) {
         Log.d("RustedWarfare", "path end is:" + var1 + "," + var2 + " size:" + var3);
      }

      p var4 = new p(var1, var2);
      var4.a(this, (byte)1, (byte)0);
      var4.b(this, (byte)1, true);
      var4.a(this, (byte)1, this.i);
      var4.a(this, (byte)1, false);
   }

   static strictfp int b(int var0, int var1) {
      if(var0 == var1) {
         return 0;
      } else {
         int var2 = var0 - var1;
         if(var2 < 0) {
            var2 = -var2;
         }

         if(var2 > 4) {
            var2 = 8 - var2;
         }

         return var2 == 1?4:(var2 == 2?21:25);
      }
   }

   static strictfp int c(int var0, int var1) {
      if(var0 == var1) {
         return 0;
      } else {
         int var2 = Math.abs(var0 - var1);
         if(var2 > 4) {
            var2 = 8 - var2;
         }

         return var2 == 1?4:(var2 == 2?21:(var2 == 3?4:(var2 == 4?0:(var2 == 5?0:25))));
      }
   }

   public strictfp LinkedList e() {
      if(l.l && !com.corrodinggames.rts.gameFramework.l.B().bH) {
         Debug.startMethodTracing("pathTrace", 110000000);
      }

      k var1 = this.D;
      int var2 = var1.p?7:1;
      int var3 = var1.q;
      if(l.a) {
         Log.d("RustedWarfare", "starting path for:" + this.n.a.toString());
      }

      p var4 = this.r;
      p var5 = this.q;
      int var6 = this.h;
      int var7 = this.g;
      this.y = com.corrodinggames.rts.gameFramework.l.V();
      short var8 = var1.h;
      short var9 = var1.i;
      boolean var10 = var1.k;
      this.d();
      this.a(var1.o, var1.h, var1.i, var1.j);
      short var11 = var1.l;
      short var12 = var1.m;
      short var13 = var1.n;
      LinkedList var14 = new LinkedList();
      if(var8 == var11 && var9 == var12) {
         if(l.a) {
            Log.d("RustedWarfare", "no point pathing when start=end");
         }

         var14.clear();
         var14.add(new p(var11, var12));
         return var14;
      } else if(this.n.a.equals(ao.a)) {
         if(l.a) {
            Log.d("RustedWarfare", "no point pathing for none");
         }

         var14.clear();
         return var14;
      } else {
         int var15 = 0;
         short var16 = var11;
         short var17 = var12;
         short var18 = var13;
         short var20;
         short var22;
         short var23;
         if(this.e != null) {
            short[] var19 = this.e;
            var20 = var19[var8 * var6 + var9];
            boolean var21 = true;
            if(var20 == -1) {
               var21 = false;
            } else {
               for(var22 = (short)(var11 - var13); var22 <= var11 + var13; ++var22) {
                  for(var23 = (short)(var12 - var13); var23 <= var12 + var13; ++var23) {
                     if(var22 >= 0 && var22 < var7 && var23 >= 0 && var23 < var6 && var20 == var19[var22 * var6 + var23]) {
                        var21 = false;
                     }
                  }
               }
            }

            if(var21) {
               if(l.a) {
                  Log.d("RustedWarfare", "end is blocked on isolated groups");
               }

               var22 = var11;
               var23 = var12;
               float var24 = -1.0F;

               short var25;
               short var26;
               float var27;
               for(var25 = (short)(var11 - 25); var25 <= var11 + 25; ++var25) {
                  for(var26 = (short)(var12 - 25); var26 <= var12 + 25; ++var26) {
                     if(var25 >= 0 && var25 < var7 && var26 >= 0 && var26 < var6 && (var20 == var19[var25 * var6 + var26] || var19[var25 * var6 + var26] == 0)) {
                        var27 = com.corrodinggames.rts.gameFramework.f.a((float)var25, (float)var26, (float)var11, (float)var12);
                        if(var24 == -1.0F || var27 < var24) {
                           var24 = var27;
                           var22 = var25;
                           var23 = var26;
                           var18 = 0;
                        }
                     }
                  }
               }

               if(var24 == -1.0F) {
                  for(var25 = 0; var25 < var7; ++var25) {
                     for(var26 = 0; var26 < var6; ++var26) {
                        if(var20 == var19[var25 * var6 + var26] || var19[var25 * var6 + var26] == 0) {
                           var27 = com.corrodinggames.rts.gameFramework.f.a((float)var25, (float)var26, (float)var11, (float)var12);
                           if(var24 == -1.0F || var27 < var24) {
                              var24 = var27;
                              var22 = var25;
                              var23 = var26;
                              var18 = 0;
                           }
                        }
                     }
                  }
               }

               var16 = var22;
               var17 = var23;
               if(l.a) {
                  long var50 = System.currentTimeMillis() - this.y;
                  Log.d("RustedWarfare", "fakeNode search was:" + var50);
               }
            }
         }

         boolean var44 = true;

         short var46;
         label487:
         for(var20 = (short)(var16 - var18); var20 <= var16 + var18; ++var20) {
            for(var46 = (short)(var17 - var18); var46 <= var17 + var18; ++var46) {
               if(var20 >= 0 && var20 < var7 && var46 >= 0 && var46 < var6 && this.a(var20, var46) != -1) {
                  var44 = false;
                  break label487;
               }
            }
         }

         short var48;
         if(var44) {
            var20 = var16;
            var46 = var17;
            float var47 = -1.0F;
            if(l.a) {
               Log.d("RustedWarfare", "end is blocked on non isolated groups");
            }

            float var51;
            for(var23 = (short)(var16 - 9); var23 <= var16 + 9; ++var23) {
               for(var48 = (short)(var17 - 9); var48 <= var17 + 9; ++var48) {
                  if(var23 >= 0 && var23 < var7 && var48 >= 0 && var48 < var6 && this.a(var23, var48) != -1) {
                     var51 = com.corrodinggames.rts.gameFramework.f.a((float)var23, (float)var48, (float)var16, (float)var17);
                     if(var47 == -1.0F || var51 < var47) {
                        var47 = var51;
                        var20 = var23;
                        var46 = var48;
                        var18 = 0;
                     }
                  }
               }
            }

            if(var47 == -1.0F) {
               for(var23 = 0; var23 < var7; ++var23) {
                  for(var48 = 0; var48 < var6; ++var48) {
                     if(this.a(var23, var48) != -1) {
                        var51 = com.corrodinggames.rts.gameFramework.f.a((float)var23, (float)var48, (float)var16, (float)var17);
                        if(var47 == -1.0F || var51 < var47) {
                           var47 = var51;
                           var20 = var23;
                           var46 = var48;
                           var18 = 0;
                        }
                     }
                  }
               }
            }

            var16 = var20;
            var17 = var46;
            if(l.a) {
               long var49 = System.currentTimeMillis() - this.y;
               Log.d("RustedWarfare", "fakeNode search was:" + var49);
            }
         }

         if(l.a && (var16 != var11 || var17 != var12)) {
            Log.d("RustedWarfare", "Moved end to fakeEndX:" + var16 + " fakeEndY:" + var17);
         }

         this.o.a(var16, var17);
         this.o.a(0, var8, var9);
         this.p.a(var8, var9);
         this.a(var16, var17, var18);
         this.p.a(0, var16, var17);
         var44 = false;
         boolean var45 = false;
         var46 = -1;
         var22 = -1;
         var23 = -1;
         var48 = -1;
         int var52 = 400;
         int var53 = 0;

         label427:
         while(true) {
            if(!var44) {
               ++var53;
               if(var1.w) {
                  var14.clear();
                  return var14;
               }

               ++var15;
               if(var52 > 0) {
                  --var52;
               } else {
                  var45 = !var45;
               }

               byte var54 = 0;
               if(var45) {
                  var54 = 1;
               }

               m var29;
               if(!var45) {
                  var29 = this.o;
               } else {
                  var29 = this.p;
               }

               n var30 = var29.c();
               if(var30 == null) {
                  if(var45) {
                     continue;
                  }

                  if(l.a) {
                     Log.d("RustedWarfare", "listNode==null for normal side ending path");
                  }
               } else {
                  p var28 = var5.a(var30);
                  int var31 = var28.a(this, var54);
                  byte var32 = var28.c(this, var54);
                  boolean var33 = var28.d(this, var54);
                  boolean var34 = false;
                  if(!var45) {
                     if(var28.a == var16 && var28.b == var17) {
                        if(l.a) {
                           Log.d("RustedWarfare", "Over goal: fakeEnd");
                        }

                        var34 = true;
                     }

                     if(com.corrodinggames.rts.gameFramework.f.d(var28.a - var11) <= var13 && com.corrodinggames.rts.gameFramework.f.d(var28.b - var12) <= var13) {
                        if(l.a) {
                           Log.d("RustedWarfare", "Over goal: real end");
                        }

                        var34 = true;
                     }
                  }

                  boolean var35 = var28.b(this, (byte)(1 - var54));
                  if(!var35 && !var34) {
                     var28.a(this, var54, true);
                     boolean var64 = false;
                     boolean var37 = true;
                     byte var65;
                     byte var66;
                     if(var33) {
                        var65 = 0;
                        var66 = 7;
                     } else {
                        byte var38 = 2;
                        if(this.f != null && this.f[var28.a * var6 + var28.b] > 1) {
                           var38 = 1;
                        }

                        var65 = (byte)(var32 - var38);
                        var66 = (byte)(var32 + var38);
                     }

                     byte var67 = var65;

                     while(true) {
                        if(var67 > var66) {
                           continue label427;
                        }

                        var4.a(var28);
                        byte var39 = var67;
                        if(var67 > 7) {
                           var39 = (byte)(var67 - 8);
                        }

                        if(var39 < 0) {
                           var39 = (byte)(var39 + 8);
                        }

                        if(var39 == 0) {
                           ++var4.a;
                        }

                        if(var39 == 1) {
                           ++var4.a;
                           ++var4.b;
                        }

                        if(var39 == 2) {
                           ++var4.b;
                        }

                        if(var39 == 3) {
                           ++var4.b;
                           --var4.a;
                        }

                        if(var39 == 4) {
                           --var4.a;
                        }

                        if(var39 == 5) {
                           --var4.a;
                           --var4.b;
                        }

                        if(var39 == 6) {
                           --var4.b;
                        }

                        if(var39 == 7) {
                           --var4.b;
                           ++var4.a;
                        }

                        if(var4.a >= 0 && var4.a < var7 && var4.b >= 0 && var4.b < var6) {
                           int var40 = var4.a(this);
                           if(var40 != -1) {
                              int var41 = var4.a(this, var54);
                              if(!var4.b(this, var54)) {
                                 label588: {
                                    int var42;
                                    if(var4.a != var28.a && var4.b != var28.b) {
                                       if(this.a(var4.a, var28.b) == -1 || this.a(var28.a, var4.b) == -1) {
                                          break label588;
                                       }

                                       var42 = var31 + 14 + var40 + 1;
                                    } else {
                                       var42 = var31 + 10 + var40 + 1;
                                    }

                                    if(var32 != var39) {
                                       if(!var33) {
                                          var42 += b(var32, var39);
                                       } else if(!var45) {
                                          if(var10) {
                                             var42 += c(var32, var39);
                                          } else {
                                             var42 += b(var32, var39);
                                          }
                                       }
                                    }

                                    if(this.f != null) {
                                       var42 += (4 - this.f[var4.a * var6 + var4.b]) * var2;
                                    }

                                    if(var3 > 0 && this.f != null) {
                                       byte var43 = this.f[var4.a * var6 + var4.b];
                                       if(var43 <= var3) {
                                          var42 += 100;
                                       }
                                    }

                                    if(var41 < this.i || var42 < var41) {
                                       var4.a(this, var54, var39);
                                       var4.a(this, var54, false);
                                       var4.a(this, var54, var42);
                                       var29.a(var42 - this.i, var4.a, var4.b);
                                    }
                                 }
                              }
                           }
                        }

                        ++var67;
                     }
                  }

                  var4.a(var28);
                  if(!var35) {
                     if(l.a) {
                        Log.d("RustedWarfare", "Not closedOnOtherSide");
                     }

                     var46 = var5.a;
                     var22 = var5.b;
                     var44 = true;
                  } else {
                     p var36 = var4.f(this, var54);
                     if(var36 == null) {
                        Log.d("RustedWarfare", "findPath: otherConnection==null");
                        Log.d("RustedWarfare", "currentNode:" + var5.a + "," + var5.b);
                        Log.d("RustedWarfare", "currentNode cost normal:" + var5.a(this, (byte)0));
                        Log.d("RustedWarfare", "currentNode cost opposite:" + var5.a(this, (byte)1));
                        var14.clear();
                        return var14;
                     }

                     if(!var45) {
                        if(l.a) {
                           Log.d("RustedWarfare", "closing path runFromOppositeSide=false");
                        }

                        var23 = var5.a;
                        var48 = var5.b;
                        var46 = var36.a;
                        var22 = var36.b;
                     } else {
                        if(l.a) {
                           Log.d("RustedWarfare", "closing path runFromOppositeSide=true");
                        }

                        var23 = var36.a;
                        var48 = var36.b;
                        var46 = var5.a;
                        var22 = var5.b;
                     }

                     var44 = true;
                  }
               }
            }

            long var55;
            if(l.a) {
               Log.d("RustedWarfare", "grid path finshed in :" + var53 + " ticks");
               var55 = System.currentTimeMillis() - this.y;
               Log.d("RustedWarfare", "grid path done in:" + var55);
            }

            var55 = System.currentTimeMillis();
            long var56;
            if(!var44) {
               if(l.a) {
                  Log.d("RustedWarfare", "could not find end node");
               }

               var56 = System.currentTimeMillis();
               float var58 = -1.0F;
               p var59 = new p();

               for(short var60 = 0; var60 < var7; ++var60) {
                  for(short var62 = 0; var62 < var6; ++var62) {
                     var59.a(var60, var62);
                     if(var59.e(this, (byte)0)) {
                        float var63 = com.corrodinggames.rts.gameFramework.f.a((float)var60, (float)var62, (float)var11, (float)var12);
                        if(var58 == -1.0F || var63 < var58) {
                           var58 = var63;
                           var46 = var60;
                           var22 = var62;
                        }
                     }
                  }
               }

               if(l.a) {
                  long var61 = System.currentTimeMillis() - var56;
                  Log.d("RustedWarfare", "got closest node in:" + var61);
               }
            }

            var14.clear();
            LinkedList var57;
            if(var46 != -1 && var22 != -1) {
               var57 = this.a((byte)0, var46, var22);
               var14.addAll(this.a(var57));
            }

            if(var23 != -1 && var48 != -1) {
               var57 = this.a((byte)1, var23, var48);
               var14.addAll(var57);
            }

            if(var14.size() > 1) {
               var14.remove(0);
            }

            this.z = com.corrodinggames.rts.gameFramework.l.V();
            if(l.a) {
               var56 = this.z - this.y;
               Log.d("RustedWarfare", "path(" + var1.e + ") finished in:" + var56);
            }

            if(l.l && !com.corrodinggames.rts.gameFramework.l.B().bH) {
               Debug.stopMethodTracing();
               l.l = false;
            }

            return var14;
         }
      }
   }

   public strictfp LinkedList a(byte var1, short var2, short var3) {
      LinkedList var4 = new LinkedList();
      p var5 = new p(var2, var3);
      var4.add(var5);

      while(true) {
         p var6 = var5.f(this, var1);
         if(var6 == null) {
            return var4;
         }

         var4.add(var6);
         var5 = var6;
      }
   }

   public strictfp LinkedList a(LinkedList var1) {
      LinkedList var2 = new LinkedList();
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         p var4 = (p)var3.next();
         var2.addFirst(var4);
      }

      return var2;
   }

   public strictfp void f() {
      this.g();
   }

   public strictfp void g() {
      f var1 = (f)this.D;
      g var2 = new g(this.g, this.h);
      if(l.l && !com.corrodinggames.rts.gameFramework.l.B().bH) {
         Debug.startMethodTracing("pathTrace", 110000000);
      }

      byte var3 = 7;
      byte var4 = 0;
      if(l.a) {
         Log.d("RustedWarfare", "starting path for:" + this.n.a.toString());
      }

      p var5 = this.r;
      p var6 = this.q;
      int var7 = this.h;
      int var8 = this.g;
      this.y = com.corrodinggames.rts.gameFramework.l.V();
      short var9 = var1.h;
      short var10 = var1.i;
      boolean var11 = var1.k;
      this.d();
      this.a(var1.o, var1.h, var1.i, var1.j);
      short var12 = var1.l;
      short var13 = var1.m;
      short var14 = var1.n;
      new LinkedList();
      if(this.n.a.equals(ao.a)) {
         if(l.a) {
            Log.d("RustedWarfare", "no point pathing for none");
         }

      } else {
         int var16 = 0;
         short var17 = var12;
         short var18 = var13;
         short var19 = var14;
         short var21;
         boolean var22;
         short var24;
         if(this.e != null) {
            short[] var20 = this.e;
            var21 = var20[var9 * var7 + var10];
            var22 = true;
            short var23;
            if(var21 == -1) {
               var22 = false;
            } else {
               for(var23 = (short)(var12 - var14); var23 <= var12 + var14; ++var23) {
                  for(var24 = (short)(var13 - var14); var24 <= var13 + var14; ++var24) {
                     if(var23 >= 0 && var23 < var8 && var24 >= 0 && var24 < var7 && var21 == var20[var23 * var7 + var24]) {
                        var22 = false;
                     }
                  }
               }
            }

            if(var22) {
               if(l.a) {
                  Log.d("RustedWarfare", "end is blocked on isolated groups");
               }

               var23 = var12;
               var24 = var13;
               float var25 = -1.0F;

               short var26;
               short var27;
               float var28;
               for(var26 = (short)(var12 - 25); var26 <= var12 + 25; ++var26) {
                  for(var27 = (short)(var13 - 25); var27 <= var13 + 25; ++var27) {
                     if(var26 >= 0 && var26 < var8 && var27 >= 0 && var27 < var7 && (var21 == var20[var26 * var7 + var27] || var20[var26 * var7 + var27] == 0)) {
                        var28 = com.corrodinggames.rts.gameFramework.f.a((float)var26, (float)var27, (float)var12, (float)var13);
                        if(var25 == -1.0F || var28 < var25) {
                           var25 = var28;
                           var23 = var26;
                           var24 = var27;
                           var19 = 0;
                        }
                     }
                  }
               }

               if(var25 == -1.0F) {
                  for(var26 = 0; var26 < var8; ++var26) {
                     for(var27 = 0; var27 < var7; ++var27) {
                        if(var21 == var20[var26 * var7 + var27] || var20[var26 * var7 + var27] == 0) {
                           var28 = com.corrodinggames.rts.gameFramework.f.a((float)var26, (float)var27, (float)var12, (float)var13);
                           if(var25 == -1.0F || var28 < var25) {
                              var25 = var28;
                              var23 = var26;
                              var24 = var27;
                              var19 = 0;
                           }
                        }
                     }
                  }
               }

               var17 = var23;
               var18 = var24;
               if(l.a) {
                  long var52 = System.currentTimeMillis() - this.y;
                  Log.d("RustedWarfare", "fakeNode search was:" + var52);
               }
            }
         }

         boolean var43 = true;

         short var45;
         label341:
         for(var21 = (short)(var17 - var19); var21 <= var17 + var19; ++var21) {
            for(var45 = (short)(var18 - var19); var45 <= var18 + var19; ++var45) {
               if(var21 >= 0 && var21 < var8 && var45 >= 0 && var45 < var7 && this.a(var21, var45) != -1) {
                  var43 = false;
                  break label341;
               }
            }
         }

         if(var43) {
            var21 = var17;
            var45 = var18;
            float var46 = -1.0F;
            if(l.a) {
               Log.d("RustedWarfare", "end is blocked on non isolated groups");
            }

            short var48;
            float var53;
            for(var24 = (short)(var17 - 9); var24 <= var17 + 9; ++var24) {
               for(var48 = (short)(var18 - 9); var48 <= var18 + 9; ++var48) {
                  if(var24 >= 0 && var24 < var8 && var48 >= 0 && var48 < var7 && this.a(var24, var48) != -1) {
                     var53 = com.corrodinggames.rts.gameFramework.f.a((float)var24, (float)var48, (float)var17, (float)var18);
                     if(var46 == -1.0F || var53 < var46) {
                        var46 = var53;
                        var21 = var24;
                        var45 = var48;
                        var19 = 0;
                     }
                  }
               }
            }

            if(var46 == -1.0F) {
               for(var24 = 0; var24 < var8; ++var24) {
                  for(var48 = 0; var48 < var7; ++var48) {
                     if(this.a(var24, var48) != -1) {
                        var53 = com.corrodinggames.rts.gameFramework.f.a((float)var24, (float)var48, (float)var17, (float)var18);
                        if(var46 == -1.0F || var53 < var46) {
                           var46 = var53;
                           var21 = var24;
                           var45 = var48;
                           var19 = 0;
                        }
                     }
                  }
               }
            }

            var17 = var21;
            var18 = var45;
            if(l.a) {
               long var49 = System.currentTimeMillis() - this.y;
               Log.d("RustedWarfare", "fakeNode search was:" + var49);
            }
         }

         if(l.a && (var17 != var12 || var18 != var13)) {
            Log.d("RustedWarfare", "Moved end to fakeEndX:" + var17 + " fakeEndY:" + var18);
         }

         this.o.a(var17, var18);
         this.o.a(0, var9, var10);
         this.p.a(var9, var10);
         this.a(var17, var18, var19);
         this.p.a(0, var17, var18);
         var43 = false;
         boolean var44 = true;
         var22 = true;
         boolean var47 = true;
         boolean var50 = true;
         boolean var51 = true;
         int var54 = 0;

         while(!var43) {
            ++var54;
            if(var1.w) {
               return;
            }

            ++var16;
            boolean var55 = false;
            byte var56 = 1;
            m var29 = this.p;
            n var30 = var29.c();
            if(var30 == null) {
               break;
            }

            p var57 = var6.a(var30);
            int var31 = var57.a(this, var56);
            byte var32 = (byte)(var2.a(var57) - 1);
            boolean var33 = var2.b(var57);
            boolean var34 = false;
            var2.a(var57, true);
            boolean var35 = false;
            boolean var36 = true;
            byte var60;
            byte var61;
            if(var33) {
               var60 = 0;
               var61 = 7;
            } else {
               byte var37 = 2;
               if(this.f != null && this.f[var57.a * var7 + var57.b] > 1) {
                  var37 = 1;
               }

               var60 = (byte)(var32 - var37);
               var61 = (byte)(var32 + var37);
            }

            for(byte var62 = var60; var62 <= var61; ++var62) {
               var5.a(var57);
               byte var38 = var62;
               if(var62 > 7) {
                  var38 = (byte)(var62 - 8);
               }

               if(var38 < 0) {
                  var38 = (byte)(var38 + 8);
               }

               if(var38 == 0) {
                  ++var5.a;
               }

               if(var38 == 1) {
                  ++var5.a;
                  ++var5.b;
               }

               if(var38 == 2) {
                  ++var5.b;
               }

               if(var38 == 3) {
                  ++var5.b;
                  --var5.a;
               }

               if(var38 == 4) {
                  --var5.a;
               }

               if(var38 == 5) {
                  --var5.a;
                  --var5.b;
               }

               if(var38 == 6) {
                  --var5.b;
               }

               if(var38 == 7) {
                  --var5.b;
                  ++var5.a;
               }

               if(var5.a >= 0 && var5.a < var8 && var5.b >= 0 && var5.b < var7) {
                  int var39 = var5.a(this);
                  if(var39 != -1) {
                     int var40 = var5.a(this, var56);
                     if(!var2.c(var5)) {
                        int var41;
                        if(var5.a != var57.a && var5.b != var57.b) {
                           if(this.a(var5.a, var57.b) == -1 || this.a(var57.a, var5.b) == -1) {
                              continue;
                           }

                           var41 = var31 + 14 + var39 + 1;
                        } else {
                           var41 = var31 + 10 + var39 + 1;
                        }

                        if(var32 != var38 && !var33) {
                           var41 += b(var32, var38);
                        }

                        if(this.f != null) {
                           var41 += (4 - this.f[var5.a * var7 + var5.b]) * var3;
                        }

                        if(var4 > 0 && this.f != null) {
                           byte var42 = this.f[var5.a * var7 + var5.b];
                           if(var42 <= var4) {
                              var41 += 100;
                           }
                        }

                        if(var40 < this.i || var41 < var40) {
                           var2.a(var5, (byte)(var38 + 1));
                           var2.a(var5, false);
                           var5.a(this, var56, var41);
                           var29.a(var41 - this.i, var5.a, var5.b);
                        }
                     }
                  }
               }
            }
         }

         long var58;
         if(l.a) {
            Log.d("RustedWarfare", "grid path finshed in :" + var54 + " ticks");
            var58 = System.currentTimeMillis() - this.y;
            Log.d("RustedWarfare", "grid path done in:" + var58);
         }

         var58 = System.currentTimeMillis();
         var2.c = var17;
         var2.d = var18;
         var1.b = var2;
         l.e = var1;
         this.z = com.corrodinggames.rts.gameFramework.l.V();
         if(l.a) {
            long var59 = this.z - this.y;
            Log.d("RustedWarfare", "path(" + var1.e + ") finished in:" + var59);
         }

         if(l.l && !com.corrodinggames.rts.gameFramework.l.B().bH) {
            Debug.stopMethodTracing();
            l.l = false;
         }

      }
   }

   static {
      t.add(new p((short)-9, (short)-9));
      t.add(new p((short)-9, (short)-9));
      t.add(new p((short)-9, (short)-9));
      u = 0;
   }
}
