package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.j;
import com.corrodinggames.rts.java.audio.a.m;
import java.util.Iterator;

public class i implements Iterable {

   public int a;
   long[] b;
   Object[] c;
   int d;
   int e;
   Object f;
   boolean g;
   private float h;
   private int i;
   private int j;
   private int k;
   private int l;
   private int m;
   private j n;
   private j o;


   public i() {
      this(51, 0.8F);
   }

   public i(int var1, float var2) {
      if(var1 < 0) {
         throw new IllegalArgumentException("initialCapacity must be >= 0: " + var1);
      } else {
         var1 = m.b((int)Math.ceil((double)((float)var1 / var2)));
         if(var1 > 1073741824) {
            throw new IllegalArgumentException("initialCapacity is too large: " + var1);
         } else {
            this.d = var1;
            if(var2 <= 0.0F) {
               throw new IllegalArgumentException("loadFactor must be > 0: " + var2);
            } else {
               this.h = var2;
               this.k = (int)((float)this.d * var2);
               this.j = this.d - 1;
               this.i = 63 - Long.numberOfTrailingZeros((long)this.d);
               this.l = Math.max(3, (int)Math.ceil(Math.log((double)this.d)) * 2);
               this.m = Math.max(Math.min(this.d, 8), (int)Math.sqrt((double)this.d) / 8);
               this.b = new long[this.d + this.l];
               this.c = (Object[])(new Object[this.b.length]);
            }
         }
      }
   }

   public Object a(long var1, Object var3) {
      if(var1 == 0L) {
         Object var17 = this.f;
         this.f = var3;
         if(!this.g) {
            this.g = true;
            ++this.a;
         }

         return var17;
      } else {
         long[] var4 = this.b;
         int var5 = (int)(var1 & (long)this.j);
         long var6 = var4[var5];
         if(var6 == var1) {
            Object var18 = this.c[var5];
            this.c[var5] = var3;
            return var18;
         } else {
            int var8 = this.f(var1);
            long var9 = var4[var8];
            if(var9 == var1) {
               Object var19 = this.c[var8];
               this.c[var8] = var3;
               return var19;
            } else {
               int var11 = this.g(var1);
               long var12 = var4[var11];
               if(var12 == var1) {
                  Object var20 = this.c[var11];
                  this.c[var11] = var3;
                  return var20;
               } else {
                  int var14 = this.d;

                  for(int var15 = var14 + this.e; var14 < var15; ++var14) {
                     if(var4[var14] == var1) {
                        Object var16 = this.c[var14];
                        this.c[var14] = var3;
                        return var16;
                     }
                  }

                  if(var6 == 0L) {
                     var4[var5] = var1;
                     this.c[var5] = var3;
                     if(this.a++ >= this.k) {
                        this.b(this.d << 1);
                     }

                     return null;
                  } else if(var9 == 0L) {
                     var4[var8] = var1;
                     this.c[var8] = var3;
                     if(this.a++ >= this.k) {
                        this.b(this.d << 1);
                     }

                     return null;
                  } else if(var12 == 0L) {
                     var4[var11] = var1;
                     this.c[var11] = var3;
                     if(this.a++ >= this.k) {
                        this.b(this.d << 1);
                     }

                     return null;
                  } else {
                     this.a(var1, var3, var5, var6, var8, var9, var11, var12);
                     return null;
                  }
               }
            }
         }
      }
   }

   private void b(long var1, Object var3) {
      if(var1 == 0L) {
         this.f = var3;
         this.g = true;
      } else {
         int var4 = (int)(var1 & (long)this.j);
         long var5 = this.b[var4];
         if(var5 == 0L) {
            this.b[var4] = var1;
            this.c[var4] = var3;
            if(this.a++ >= this.k) {
               this.b(this.d << 1);
            }

         } else {
            int var7 = this.f(var1);
            long var8 = this.b[var7];
            if(var8 == 0L) {
               this.b[var7] = var1;
               this.c[var7] = var3;
               if(this.a++ >= this.k) {
                  this.b(this.d << 1);
               }

            } else {
               int var10 = this.g(var1);
               long var11 = this.b[var10];
               if(var11 == 0L) {
                  this.b[var10] = var1;
                  this.c[var10] = var3;
                  if(this.a++ >= this.k) {
                     this.b(this.d << 1);
                  }

               } else {
                  this.a(var1, var3, var4, var5, var7, var8, var10, var11);
               }
            }
         }
      }
   }

   private void a(long var1, Object var3, int var4, long var5, int var7, long var8, int var10, long var11) {
      long[] var13 = this.b;
      Object[] var14 = this.c;
      int var15 = this.j;
      int var19 = 0;
      int var20 = this.m;

      while(true) {
         long var16;
         Object var18;
         switch(m.a(2)) {
         case 0:
            var16 = var5;
            var18 = var14[var4];
            var13[var4] = var1;
            var14[var4] = var3;
            break;
         case 1:
            var16 = var8;
            var18 = var14[var7];
            var13[var7] = var1;
            var14[var7] = var3;
            break;
         default:
            var16 = var11;
            var18 = var14[var10];
            var13[var10] = var1;
            var14[var10] = var3;
         }

         var4 = (int)(var16 & (long)var15);
         var5 = var13[var4];
         if(var5 == 0L) {
            var13[var4] = var16;
            var14[var4] = var18;
            if(this.a++ >= this.k) {
               this.b(this.d << 1);
            }

            return;
         }

         var7 = this.f(var16);
         var8 = var13[var7];
         if(var8 == 0L) {
            var13[var7] = var16;
            var14[var7] = var18;
            if(this.a++ >= this.k) {
               this.b(this.d << 1);
            }

            return;
         }

         var10 = this.g(var16);
         var11 = var13[var10];
         if(var11 == 0L) {
            var13[var10] = var16;
            var14[var10] = var18;
            if(this.a++ >= this.k) {
               this.b(this.d << 1);
            }

            return;
         }

         ++var19;
         if(var19 == var20) {
            this.c(var16, var18);
            return;
         }

         var1 = var16;
         var3 = var18;
      }
   }

   private void c(long var1, Object var3) {
      if(this.e == this.l) {
         this.b(this.d << 1);
         this.a(var1, var3);
      } else {
         int var4 = this.d + this.e;
         this.b[var4] = var1;
         this.c[var4] = var3;
         ++this.e;
         ++this.a;
      }
   }

   public Object a(long var1) {
      if(var1 == 0L) {
         return !this.g?null:this.f;
      } else {
         int var3 = (int)(var1 & (long)this.j);
         if(this.b[var3] != var1) {
            var3 = this.f(var1);
            if(this.b[var3] != var1) {
               var3 = this.g(var1);
               if(this.b[var3] != var1) {
                  return this.d(var1, (Object)null);
               }
            }
         }

         return this.c[var3];
      }
   }

   private Object d(long var1, Object var3) {
      long[] var4 = this.b;
      int var5 = this.d;

      for(int var6 = var5 + this.e; var5 < var6; ++var5) {
         if(var4[var5] == var1) {
            return this.c[var5];
         }
      }

      return var3;
   }

   public Object b(long var1) {
      if(var1 == 0L) {
         if(!this.g) {
            return null;
         } else {
            Object var5 = this.f;
            this.f = null;
            this.g = false;
            --this.a;
            return var5;
         }
      } else {
         int var3 = (int)(var1 & (long)this.j);
         Object var4;
         if(this.b[var3] == var1) {
            this.b[var3] = 0L;
            var4 = this.c[var3];
            this.c[var3] = null;
            --this.a;
            return var4;
         } else {
            var3 = this.f(var1);
            if(this.b[var3] == var1) {
               this.b[var3] = 0L;
               var4 = this.c[var3];
               this.c[var3] = null;
               --this.a;
               return var4;
            } else {
               var3 = this.g(var1);
               if(this.b[var3] == var1) {
                  this.b[var3] = 0L;
                  var4 = this.c[var3];
                  this.c[var3] = null;
                  --this.a;
                  return var4;
               } else {
                  return this.c(var1);
               }
            }
         }
      }
   }

   Object c(long var1) {
      long[] var3 = this.b;
      int var4 = this.d;

      for(int var5 = var4 + this.e; var4 < var5; ++var4) {
         if(var3[var4] == var1) {
            Object var6 = this.c[var4];
            this.a(var4);
            --this.a;
            return var6;
         }
      }

      return null;
   }

   void a(int var1) {
      --this.e;
      int var2 = this.d + this.e;
      if(var1 < var2) {
         this.b[var1] = this.b[var2];
         this.c[var1] = this.c[var2];
         this.c[var2] = null;
      } else {
         this.c[var1] = null;
      }

   }

   public void a() {
      if(this.a != 0) {
         long[] var1 = this.b;
         Object[] var2 = this.c;

         for(int var3 = this.d + this.e; var3-- > 0; var2[var3] = null) {
            var1[var3] = 0L;
         }

         this.a = 0;
         this.e = 0;
         this.f = null;
         this.g = false;
      }
   }

   public boolean d(long var1) {
      if(var1 == 0L) {
         return this.g;
      } else {
         int var3 = (int)(var1 & (long)this.j);
         if(this.b[var3] != var1) {
            var3 = this.f(var1);
            if(this.b[var3] != var1) {
               var3 = this.g(var1);
               if(this.b[var3] != var1) {
                  return this.e(var1);
               }
            }
         }

         return true;
      }
   }

   private boolean e(long var1) {
      long[] var3 = this.b;
      int var4 = this.d;

      for(int var5 = var4 + this.e; var4 < var5; ++var4) {
         if(var3[var4] == var1) {
            return true;
         }
      }

      return false;
   }

   private void b(int var1) {
      int var2 = this.d + this.e;
      this.d = var1;
      this.k = (int)((float)var1 * this.h);
      this.j = var1 - 1;
      this.i = 63 - Long.numberOfTrailingZeros((long)var1);
      this.l = Math.max(3, (int)Math.ceil(Math.log((double)var1)) * 2);
      this.m = Math.max(Math.min(var1, 8), (int)Math.sqrt((double)var1) / 8);
      long[] var3 = this.b;
      Object[] var4 = this.c;
      this.b = new long[var1 + this.l];
      this.c = (Object[])(new Object[var1 + this.l]);
      int var5 = this.a;
      this.a = this.g?1:0;
      this.e = 0;
      if(var5 > 0) {
         for(int var6 = 0; var6 < var2; ++var6) {
            long var7 = var3[var6];
            if(var7 != 0L) {
               this.b(var7, var4[var6]);
            }
         }
      }

   }

   private int f(long var1) {
      var1 *= -1262997959L;
      return (int)((var1 ^ var1 >>> this.i) & (long)this.j);
   }

   private int g(long var1) {
      var1 *= -825114047L;
      return (int)((var1 ^ var1 >>> this.i) & (long)this.j);
   }

   public int hashCode() {
      int var1 = 0;
      if(this.g && this.f != null) {
         var1 += this.f.hashCode();
      }

      long[] var2 = this.b;
      Object[] var3 = this.c;
      int var4 = 0;

      for(int var5 = this.d + this.e; var4 < var5; ++var4) {
         long var6 = var2[var4];
         if(var6 != 0L) {
            var1 += (int)(var6 ^ var6 >>> 32) * 31;
            Object var8 = var3[var4];
            if(var8 != null) {
               var1 += var8.hashCode();
            }
         }
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if(var1 == this) {
         return true;
      } else if(!(var1 instanceof i)) {
         return false;
      } else {
         i var2 = (i)var1;
         if(var2.a != this.a) {
            return false;
         } else if(var2.g != this.g) {
            return false;
         } else {
            if(this.g) {
               if(var2.f == null) {
                  if(this.f != null) {
                     return false;
                  }
               } else if(!var2.f.equals(this.f)) {
                  return false;
               }
            }

            long[] var3 = this.b;
            Object[] var4 = this.c;
            int var5 = 0;

            for(int var6 = this.d + this.e; var5 < var6; ++var5) {
               long var7 = var3[var5];
               if(var7 != 0L) {
                  Object var9 = var4[var5];
                  if(var9 == null) {
                     if(!var2.d(var7) || var2.a(var7) != null) {
                        return false;
                     }
                  } else if(!var9.equals(var2.a(var7))) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   public String toString() {
      if(this.a == 0) {
         return "[]";
      } else {
         StringBuilder var1 = new StringBuilder(32);
         var1.append('[');
         long[] var2 = this.b;
         Object[] var3 = this.c;
         int var4 = var2.length;

         long var5;
         while(var4-- > 0) {
            var5 = var2[var4];
            if(var5 != 0L) {
               var1.append(var5);
               var1.append('=');
               var1.append(var3[var4]);
               break;
            }
         }

         while(var4-- > 0) {
            var5 = var2[var4];
            if(var5 != 0L) {
               var1.append(", ");
               var1.append(var5);
               var1.append('=');
               var1.append(var3[var4]);
            }
         }

         var1.append(']');
         return var1.toString();
      }
   }

   public Iterator iterator() {
      return this.b();
   }

   public j b() {
      if(this.n == null) {
         this.n = new j(this);
         this.o = new j(this);
      }

      if(!this.n.e) {
         this.n.b();
         this.n.e = true;
         this.o.e = false;
         return this.n;
      } else {
         this.o.b();
         this.o.e = true;
         this.n.e = false;
         return this.o;
      }
   }
}
