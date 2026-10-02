package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.m;
import com.corrodinggames.rts.java.audio.a.p;
import java.util.Iterator;

public class o implements Iterable {

   public int a;
   Object[] b;
   Object[] c;
   int d;
   int e;
   private float f;
   private int g;
   private int h;
   private int i;
   private int j;
   private int k;
   private p l;
   private p m;


   public o() {
      this(51, 0.8F);
   }

   public o(int var1, float var2) {
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
               this.f = var2;
               this.i = (int)((float)this.d * var2);
               this.h = this.d - 1;
               this.g = 31 - Integer.numberOfTrailingZeros(this.d);
               this.j = Math.max(3, (int)Math.ceil(Math.log((double)this.d)) * 2);
               this.k = Math.max(Math.min(this.d, 8), (int)Math.sqrt((double)this.d) / 8);
               this.b = (Object[])(new Object[this.d + this.j]);
               this.c = (Object[])(new Object[this.b.length]);
            }
         }
      }
   }

   public Object a(Object var1, Object var2) {
      if(var1 == null) {
         throw new IllegalArgumentException("key cannot be null.");
      } else {
         return this.b(var1, var2);
      }
   }

   private Object b(Object var1, Object var2) {
      Object[] var3 = this.b;
      int var4 = var1.hashCode();
      int var5 = var4 & this.h;
      Object var6 = var3[var5];
      if(var1.equals(var6)) {
         Object var14 = this.c[var5];
         this.c[var5] = var2;
         return var14;
      } else {
         int var7 = this.c(var4);
         Object var8 = var3[var7];
         if(var1.equals(var8)) {
            Object var15 = this.c[var7];
            this.c[var7] = var2;
            return var15;
         } else {
            int var9 = this.d(var4);
            Object var10 = var3[var9];
            if(var1.equals(var10)) {
               Object var16 = this.c[var9];
               this.c[var9] = var2;
               return var16;
            } else {
               int var11 = this.d;

               for(int var12 = var11 + this.e; var11 < var12; ++var11) {
                  if(var1.equals(var3[var11])) {
                     Object var13 = this.c[var11];
                     this.c[var11] = var2;
                     return var13;
                  }
               }

               if(var6 == null) {
                  var3[var5] = var1;
                  this.c[var5] = var2;
                  if(this.a++ >= this.i) {
                     this.b(this.d << 1);
                  }

                  return null;
               } else if(var8 == null) {
                  var3[var7] = var1;
                  this.c[var7] = var2;
                  if(this.a++ >= this.i) {
                     this.b(this.d << 1);
                  }

                  return null;
               } else if(var10 == null) {
                  var3[var9] = var1;
                  this.c[var9] = var2;
                  if(this.a++ >= this.i) {
                     this.b(this.d << 1);
                  }

                  return null;
               } else {
                  this.a(var1, var2, var5, var6, var7, var8, var9, var10);
                  return null;
               }
            }
         }
      }
   }

   private void c(Object var1, Object var2) {
      int var3 = var1.hashCode();
      int var4 = var3 & this.h;
      Object var5 = this.b[var4];
      if(var5 == null) {
         this.b[var4] = var1;
         this.c[var4] = var2;
         if(this.a++ >= this.i) {
            this.b(this.d << 1);
         }

      } else {
         int var6 = this.c(var3);
         Object var7 = this.b[var6];
         if(var7 == null) {
            this.b[var6] = var1;
            this.c[var6] = var2;
            if(this.a++ >= this.i) {
               this.b(this.d << 1);
            }

         } else {
            int var8 = this.d(var3);
            Object var9 = this.b[var8];
            if(var9 == null) {
               this.b[var8] = var1;
               this.c[var8] = var2;
               if(this.a++ >= this.i) {
                  this.b(this.d << 1);
               }

            } else {
               this.a(var1, var2, var4, var5, var6, var7, var8, var9);
            }
         }
      }
   }

   private void a(Object var1, Object var2, int var3, Object var4, int var5, Object var6, int var7, Object var8) {
      Object[] var9 = this.b;
      Object[] var10 = this.c;
      int var11 = this.h;
      int var14 = 0;
      int var15 = this.k;

      while(true) {
         Object var12;
         Object var13;
         switch(m.a(2)) {
         case 0:
            var12 = var4;
            var13 = var10[var3];
            var9[var3] = var1;
            var10[var3] = var2;
            break;
         case 1:
            var12 = var6;
            var13 = var10[var5];
            var9[var5] = var1;
            var10[var5] = var2;
            break;
         default:
            var12 = var8;
            var13 = var10[var7];
            var9[var7] = var1;
            var10[var7] = var2;
         }

         int var16 = var12.hashCode();
         var3 = var16 & var11;
         var4 = var9[var3];
         if(var4 == null) {
            var9[var3] = var12;
            var10[var3] = var13;
            if(this.a++ >= this.i) {
               this.b(this.d << 1);
            }

            return;
         }

         var5 = this.c(var16);
         var6 = var9[var5];
         if(var6 == null) {
            var9[var5] = var12;
            var10[var5] = var13;
            if(this.a++ >= this.i) {
               this.b(this.d << 1);
            }

            return;
         }

         var7 = this.d(var16);
         var8 = var9[var7];
         if(var8 == null) {
            var9[var7] = var12;
            var10[var7] = var13;
            if(this.a++ >= this.i) {
               this.b(this.d << 1);
            }

            return;
         }

         ++var14;
         if(var14 == var15) {
            this.d(var12, var13);
            return;
         }

         var1 = var12;
         var2 = var13;
      }
   }

   private void d(Object var1, Object var2) {
      if(this.e == this.j) {
         this.b(this.d << 1);
         this.b(var1, var2);
      } else {
         int var3 = this.d + this.e;
         this.b[var3] = var1;
         this.c[var3] = var2;
         ++this.e;
         ++this.a;
      }
   }

   public Object a(Object var1) {
      int var2 = var1.hashCode();
      int var3 = var2 & this.h;
      if(!var1.equals(this.b[var3])) {
         var3 = this.c(var2);
         if(!var1.equals(this.b[var3])) {
            var3 = this.d(var2);
            if(!var1.equals(this.b[var3])) {
               return this.e(var1, (Object)null);
            }
         }
      }

      return this.c[var3];
   }

   private Object e(Object var1, Object var2) {
      Object[] var3 = this.b;
      int var4 = this.d;

      for(int var5 = var4 + this.e; var4 < var5; ++var4) {
         if(var1.equals(var3[var4])) {
            return this.c[var4];
         }
      }

      return var2;
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

   public boolean b(Object var1) {
      int var2 = var1.hashCode();
      int var3 = var2 & this.h;
      if(!var1.equals(this.b[var3])) {
         var3 = this.c(var2);
         if(!var1.equals(this.b[var3])) {
            var3 = this.d(var2);
            if(!var1.equals(this.b[var3])) {
               return this.c(var1);
            }
         }
      }

      return true;
   }

   private boolean c(Object var1) {
      Object[] var2 = this.b;
      int var3 = this.d;

      for(int var4 = var3 + this.e; var3 < var4; ++var3) {
         if(var1.equals(var2[var3])) {
            return true;
         }
      }

      return false;
   }

   private void b(int var1) {
      int var2 = this.d + this.e;
      this.d = var1;
      this.i = (int)((float)var1 * this.f);
      this.h = var1 - 1;
      this.g = 31 - Integer.numberOfTrailingZeros(var1);
      this.j = Math.max(3, (int)Math.ceil(Math.log((double)var1)) * 2);
      this.k = Math.max(Math.min(var1, 8), (int)Math.sqrt((double)var1) / 8);
      Object[] var3 = this.b;
      Object[] var4 = this.c;
      this.b = (Object[])(new Object[var1 + this.j]);
      this.c = (Object[])(new Object[var1 + this.j]);
      int var5 = this.a;
      this.a = 0;
      this.e = 0;
      if(var5 > 0) {
         for(int var6 = 0; var6 < var2; ++var6) {
            Object var7 = var3[var6];
            if(var7 != null) {
               this.c(var7, var4[var6]);
            }
         }
      }

   }

   private int c(int var1) {
      var1 *= -1262997959;
      return (var1 ^ var1 >>> this.g) & this.h;
   }

   private int d(int var1) {
      var1 *= -825114047;
      return (var1 ^ var1 >>> this.g) & this.h;
   }

   public int hashCode() {
      int var1 = 0;
      Object[] var2 = this.b;
      Object[] var3 = this.c;
      int var4 = 0;

      for(int var5 = this.d + this.e; var4 < var5; ++var4) {
         Object var6 = var2[var4];
         if(var6 != null) {
            var1 += var6.hashCode() * 31;
            Object var7 = var3[var4];
            if(var7 != null) {
               var1 += var7.hashCode();
            }
         }
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if(var1 == this) {
         return true;
      } else if(!(var1 instanceof o)) {
         return false;
      } else {
         o var2 = (o)var1;
         if(var2.a != this.a) {
            return false;
         } else {
            Object[] var3 = this.b;
            Object[] var4 = this.c;
            int var5 = 0;

            for(int var6 = this.d + this.e; var5 < var6; ++var5) {
               Object var7 = var3[var5];
               if(var7 != null) {
                  Object var8 = var4[var5];
                  if(var8 == null) {
                     if(!var2.b(var7) || var2.a(var7) != null) {
                        return false;
                     }
                  } else if(!var8.equals(var2.a(var7))) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   public String toString() {
      return this.a(", ", true);
   }

   private String a(String var1, boolean var2) {
      if(this.a == 0) {
         return var2?"{}":"";
      } else {
         StringBuilder var3 = new StringBuilder(32);
         if(var2) {
            var3.append('{');
         }

         Object[] var4 = this.b;
         Object[] var5 = this.c;
         int var6 = var4.length;

         Object var7;
         while(var6-- > 0) {
            var7 = var4[var6];
            if(var7 != null) {
               var3.append(var7);
               var3.append('=');
               var3.append(var5[var6]);
               break;
            }
         }

         while(var6-- > 0) {
            var7 = var4[var6];
            if(var7 != null) {
               var3.append(var1);
               var3.append(var7);
               var3.append('=');
               var3.append(var5[var6]);
            }
         }

         if(var2) {
            var3.append('}');
         }

         return var3.toString();
      }
   }

   public p a() {
      return this.b();
   }

   public p b() {
      if(this.l == null) {
         this.l = new p(this);
         this.m = new p(this);
      }

      if(!this.l.f) {
         this.l.c();
         this.l.f = true;
         this.m.f = false;
         return this.l;
      } else {
         this.m.c();
         this.m.f = true;
         this.l.f = false;
         return this.m;
      }
   }

   // $FF: synthetic method
   public Iterator iterator() {
      return this.a();
   }
}
