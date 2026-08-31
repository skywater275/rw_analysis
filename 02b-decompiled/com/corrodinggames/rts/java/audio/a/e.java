package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.f;
import com.corrodinggames.rts.java.audio.a.m;
import java.util.Iterator;

public class e implements Iterable {

   public int a;
   int[] b;
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
   private f n;
   private f o;


   public e() {
      this(51, 0.8F);
   }

   public e(int var1, float var2) {
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
               this.i = 31 - Integer.numberOfTrailingZeros(this.d);
               this.l = Math.max(3, (int)Math.ceil(Math.log((double)this.d)) * 2);
               this.m = Math.max(Math.min(this.d, 8), (int)Math.sqrt((double)this.d) / 8);
               this.b = new int[this.d + this.l];
               this.c = (Object[])(new Object[this.b.length]);
            }
         }
      }
   }

   public Object a(int var1, Object var2) {
      if(var1 == 0) {
         Object var13 = this.f;
         this.f = var2;
         if(!this.g) {
            this.g = true;
            ++this.a;
         }

         return var13;
      } else {
         int[] var3 = this.b;
         int var4 = var1 & this.j;
         int var5 = var3[var4];
         if(var5 == var1) {
            Object var14 = this.c[var4];
            this.c[var4] = var2;
            return var14;
         } else {
            int var6 = this.h(var1);
            int var7 = var3[var6];
            if(var7 == var1) {
               Object var15 = this.c[var6];
               this.c[var6] = var2;
               return var15;
            } else {
               int var8 = this.i(var1);
               int var9 = var3[var8];
               if(var9 == var1) {
                  Object var16 = this.c[var8];
                  this.c[var8] = var2;
                  return var16;
               } else {
                  int var10 = this.d;

                  for(int var11 = var10 + this.e; var10 < var11; ++var10) {
                     if(var3[var10] == var1) {
                        Object var12 = this.c[var10];
                        this.c[var10] = var2;
                        return var12;
                     }
                  }

                  if(var5 == 0) {
                     var3[var4] = var1;
                     this.c[var4] = var2;
                     if(this.a++ >= this.k) {
                        this.g(this.d << 1);
                     }

                     return null;
                  } else if(var7 == 0) {
                     var3[var6] = var1;
                     this.c[var6] = var2;
                     if(this.a++ >= this.k) {
                        this.g(this.d << 1);
                     }

                     return null;
                  } else if(var9 == 0) {
                     var3[var8] = var1;
                     this.c[var8] = var2;
                     if(this.a++ >= this.k) {
                        this.g(this.d << 1);
                     }

                     return null;
                  } else {
                     this.a(var1, var2, var4, var5, var6, var7, var8, var9);
                     return null;
                  }
               }
            }
         }
      }
   }

   private void b(int var1, Object var2) {
      if(var1 == 0) {
         this.f = var2;
         this.g = true;
      } else {
         int var3 = var1 & this.j;
         int var4 = this.b[var3];
         if(var4 == 0) {
            this.b[var3] = var1;
            this.c[var3] = var2;
            if(this.a++ >= this.k) {
               this.g(this.d << 1);
            }

         } else {
            int var5 = this.h(var1);
            int var6 = this.b[var5];
            if(var6 == 0) {
               this.b[var5] = var1;
               this.c[var5] = var2;
               if(this.a++ >= this.k) {
                  this.g(this.d << 1);
               }

            } else {
               int var7 = this.i(var1);
               int var8 = this.b[var7];
               if(var8 == 0) {
                  this.b[var7] = var1;
                  this.c[var7] = var2;
                  if(this.a++ >= this.k) {
                     this.g(this.d << 1);
                  }

               } else {
                  this.a(var1, var2, var3, var4, var5, var6, var7, var8);
               }
            }
         }
      }
   }

   private void a(int var1, Object var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      int[] var9 = this.b;
      Object[] var10 = this.c;
      int var11 = this.j;
      int var14 = 0;
      int var15 = this.m;

      while(true) {
         int var12;
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

         var3 = var12 & var11;
         var4 = var9[var3];
         if(var4 == 0) {
            var9[var3] = var12;
            var10[var3] = var13;
            if(this.a++ >= this.k) {
               this.g(this.d << 1);
            }

            return;
         }

         var5 = this.h(var12);
         var6 = var9[var5];
         if(var6 == 0) {
            var9[var5] = var12;
            var10[var5] = var13;
            if(this.a++ >= this.k) {
               this.g(this.d << 1);
            }

            return;
         }

         var7 = this.i(var12);
         var8 = var9[var7];
         if(var8 == 0) {
            var9[var7] = var12;
            var10[var7] = var13;
            if(this.a++ >= this.k) {
               this.g(this.d << 1);
            }

            return;
         }

         ++var14;
         if(var14 == var15) {
            this.c(var12, var13);
            return;
         }

         var1 = var12;
         var2 = var13;
      }
   }

   private void c(int var1, Object var2) {
      if(this.e == this.l) {
         this.g(this.d << 1);
         this.a(var1, var2);
      } else {
         int var3 = this.d + this.e;
         this.b[var3] = var1;
         this.c[var3] = var2;
         ++this.e;
         ++this.a;
      }
   }

   public Object a(int var1) {
      if(var1 == 0) {
         return !this.g?null:this.f;
      } else {
         int var2 = var1 & this.j;
         if(this.b[var2] != var1) {
            var2 = this.h(var1);
            if(this.b[var2] != var1) {
               var2 = this.i(var1);
               if(this.b[var2] != var1) {
                  return this.d(var1, (Object)null);
               }
            }
         }

         return this.c[var2];
      }
   }

   private Object d(int var1, Object var2) {
      int[] var3 = this.b;
      int var4 = this.d;

      for(int var5 = var4 + this.e; var4 < var5; ++var4) {
         if(var3[var4] == var1) {
            return this.c[var4];
         }
      }

      return var2;
   }

   public Object b(int var1) {
      if(var1 == 0) {
         if(!this.g) {
            return null;
         } else {
            Object var4 = this.f;
            this.f = null;
            this.g = false;
            --this.a;
            return var4;
         }
      } else {
         int var2 = var1 & this.j;
         Object var3;
         if(this.b[var2] == var1) {
            this.b[var2] = 0;
            var3 = this.c[var2];
            this.c[var2] = null;
            --this.a;
            return var3;
         } else {
            var2 = this.h(var1);
            if(this.b[var2] == var1) {
               this.b[var2] = 0;
               var3 = this.c[var2];
               this.c[var2] = null;
               --this.a;
               return var3;
            } else {
               var2 = this.i(var1);
               if(this.b[var2] == var1) {
                  this.b[var2] = 0;
                  var3 = this.c[var2];
                  this.c[var2] = null;
                  --this.a;
                  return var3;
               } else {
                  return this.c(var1);
               }
            }
         }
      }
   }

   Object c(int var1) {
      int[] var2 = this.b;
      int var3 = this.d;

      for(int var4 = var3 + this.e; var3 < var4; ++var3) {
         if(var2[var3] == var1) {
            Object var5 = this.c[var3];
            this.d(var3);
            --this.a;
            return var5;
         }
      }

      return null;
   }

   void d(int var1) {
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
         int[] var1 = this.b;
         Object[] var2 = this.c;

         for(int var3 = this.d + this.e; var3-- > 0; var2[var3] = null) {
            var1[var3] = 0;
         }

         this.a = 0;
         this.e = 0;
         this.f = null;
         this.g = false;
      }
   }

   public boolean e(int var1) {
      if(var1 == 0) {
         return this.g;
      } else {
         int var2 = var1 & this.j;
         if(this.b[var2] != var1) {
            var2 = this.h(var1);
            if(this.b[var2] != var1) {
               var2 = this.i(var1);
               if(this.b[var2] != var1) {
                  return this.f(var1);
               }
            }
         }

         return true;
      }
   }

   private boolean f(int var1) {
      int[] var2 = this.b;
      int var3 = this.d;

      for(int var4 = var3 + this.e; var3 < var4; ++var3) {
         if(var2[var3] == var1) {
            return true;
         }
      }

      return false;
   }

   private void g(int var1) {
      int var2 = this.d + this.e;
      this.d = var1;
      this.k = (int)((float)var1 * this.h);
      this.j = var1 - 1;
      this.i = 31 - Integer.numberOfTrailingZeros(var1);
      this.l = Math.max(3, (int)Math.ceil(Math.log((double)var1)) * 2);
      this.m = Math.max(Math.min(var1, 8), (int)Math.sqrt((double)var1) / 8);
      int[] var3 = this.b;
      Object[] var4 = this.c;
      this.b = new int[var1 + this.l];
      this.c = (Object[])(new Object[var1 + this.l]);
      int var5 = this.a;
      this.a = this.g?1:0;
      this.e = 0;
      if(var5 > 0) {
         for(int var6 = 0; var6 < var2; ++var6) {
            int var7 = var3[var6];
            if(var7 != 0) {
               this.b(var7, var4[var6]);
            }
         }
      }

   }

   private int h(int var1) {
      var1 *= -1262997959;
      return (var1 ^ var1 >>> this.i) & this.j;
   }

   private int i(int var1) {
      var1 *= -825114047;
      return (var1 ^ var1 >>> this.i) & this.j;
   }

   public int hashCode() {
      int var1 = 0;
      if(this.g && this.f != null) {
         var1 += this.f.hashCode();
      }

      int[] var2 = this.b;
      Object[] var3 = this.c;
      int var4 = 0;

      for(int var5 = this.d + this.e; var4 < var5; ++var4) {
         int var6 = var2[var4];
         if(var6 != 0) {
            var1 += var6 * 31;
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
      } else if(!(var1 instanceof e)) {
         return false;
      } else {
         e var2 = (e)var1;
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

            int[] var3 = this.b;
            Object[] var4 = this.c;
            int var5 = 0;

            for(int var6 = this.d + this.e; var5 < var6; ++var5) {
               int var7 = var3[var5];
               if(var7 != 0) {
                  Object var8 = var4[var5];
                  if(var8 == null) {
                     if(!var2.e(var7) || var2.a(var7) != null) {
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
      if(this.a == 0) {
         return "[]";
      } else {
         StringBuilder var1 = new StringBuilder(32);
         var1.append('[');
         int[] var2 = this.b;
         Object[] var3 = this.c;
         int var4 = var2.length;
         int var5;
         if(this.g) {
            var1.append("0=");
            var1.append(this.f);
         } else {
            while(var4-- > 0) {
               var5 = var2[var4];
               if(var5 != 0) {
                  var1.append(var5);
                  var1.append('=');
                  var1.append(var3[var4]);
                  break;
               }
            }
         }

         while(var4-- > 0) {
            var5 = var2[var4];
            if(var5 != 0) {
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

   public f b() {
      if(this.n == null) {
         this.n = new f(this);
         this.o = new f(this);
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
