package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.utility.o$1;
import com.corrodinggames.rts.gameFramework.utility.p;
import com.corrodinggames.rts.gameFramework.utility.q;
import com.corrodinggames.rts.gameFramework.utility.r;
import com.corrodinggames.rts.gameFramework.utility.x;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class o extends AbstractList implements Serializable, Cloneable, RandomAccess {

   public m a = new m();
   public m b = new m();
   public int c;
   transient Object[] d;


   public void a(Object var1) {
      r var2;
      if(this.b.a != 0) {
         var2 = (r)this.b.b();
      } else {
         var2 = new r();
      }

      var2.a = q.a;
      var2.b = var1;
      this.a.add(var2);
   }

   public void b(Object var1) {
      r var2;
      if(this.b.a != 0) {
         var2 = (r)this.b.b();
      } else {
         var2 = new r();
      }

      var2.a = q.b;
      var2.b = var1;
      this.a.add(var2);
   }

   public void a() {
      ++this.modCount;
      if(this.a.a != 0) {
         r var2;
         for(Iterator var1 = this.a.iterator(); var1.hasNext(); var2.b = null) {
            var2 = (r)var1.next();
            if(var2.a == q.a) {
               Object var3 = var2.b;
               if(var3 == null) {
                  throw new RuntimeException("Trying to insert null into array");
               }

               this.add(var3);
            } else {
               if(var2.a != q.b) {
                  throw new RuntimeException("Unknown operationType:" + var2.a);
               }

               this.remove(var2.b);
            }
         }

         if(this.a.size() < 100) {
            this.b.addAll(this.a);
         }

         this.a.clear();
      }

   }

   public o() {
      this.d = x.g;
   }

   public Object[] b() {
      return this.d;
   }

   public boolean add(Object var1) {
      Object[] var2 = this.d;
      int var3 = this.c;
      if(var3 == var2.length) {
         Object[] var4 = new Object[var3 + (var3 < 6?12:var3 >> 1)];
         System.arraycopy(var2, 0, var4, 0, var3);
         var2 = var4;
         this.d = var4;
      }

      var2[var3] = var1;
      this.c = var3 + 1;
      ++this.modCount;
      return true;
   }

   public void add(int var1, Object var2) {
      Object[] var3 = this.d;
      int var4 = this.c;
      if(var1 > var4 || var1 < 0) {
         a(var1, var4);
      }

      if(var4 < var3.length) {
         System.arraycopy(var3, var1, var3, var1 + 1, var4 - var1);
      } else {
         Object[] var5 = new Object[a(var4)];
         System.arraycopy(var3, 0, var5, 0, var1);
         System.arraycopy(var3, var1, var5, var1 + 1, var4 - var1);
         var3 = var5;
         this.d = var5;
      }

      var3[var1] = var2;
      this.c = var4 + 1;
      ++this.modCount;
   }

   private static int a(int var0) {
      int var1 = var0 < 6?12:var0 >> 1;
      return var0 + var1;
   }

   public boolean addAll(Collection var1) {
      Object[] var2 = var1.toArray();
      int var3 = var2.length;
      if(var3 == 0) {
         return false;
      } else {
         Object[] var4 = this.d;
         int var5 = this.c;
         int var6 = var5 + var3;
         if(var6 > var4.length) {
            int var7 = a(var6 - 1);
            Object[] var8 = new Object[var7];
            System.arraycopy(var4, 0, var8, 0, var5);
            var4 = var8;
            this.d = var8;
         }

         System.arraycopy(var2, 0, var4, var5, var3);
         this.c = var6;
         ++this.modCount;
         return true;
      }
   }

   public boolean addAll(int var1, Collection var2) {
      int var3 = this.c;
      if(var1 > var3 || var1 < 0) {
         a(var1, var3);
      }

      Object[] var4 = var2.toArray();
      int var5 = var4.length;
      if(var5 == 0) {
         return false;
      } else {
         Object[] var6 = this.d;
         int var7 = var3 + var5;
         if(var7 <= var6.length) {
            System.arraycopy(var6, var1, var6, var1 + var5, var3 - var1);
         } else {
            int var8 = a(var7 - 1);
            Object[] var9 = new Object[var8];
            System.arraycopy(var6, 0, var9, 0, var1);
            System.arraycopy(var6, var1, var9, var1 + var5, var3 - var1);
            var6 = var9;
            this.d = var9;
         }

         System.arraycopy(var4, 0, var6, var1, var5);
         this.c = var7;
         ++this.modCount;
         return true;
      }
   }

   static IndexOutOfBoundsException a(int var0, int var1) {
      throw new IndexOutOfBoundsException("Invalid index " + var0 + ", size is " + var1);
   }

   public synchronized void clear() {
      this.a.clear();
      if(this.c != 0) {
         Arrays.fill(this.d, 0, this.c, (Object)null);
         this.c = 0;
         ++this.modCount;
      }

   }

   public Object clone() {
      try {
         o var1 = (o)super.clone();
         var1.d = (Object[])this.d.clone();
         return var1;
      } catch (CloneNotSupportedException var2) {
         throw new AssertionError();
      }
   }

   public Object get(int var1) {
      if(var1 >= this.c) {
         a(var1, this.c);
      }

      return this.d[var1];
   }

   public int size() {
      return this.c;
   }

   public boolean isEmpty() {
      return this.c == 0;
   }

   public boolean contains(Object var1) {
      Object[] var2 = this.d;
      int var3 = this.c;
      int var4;
      if(var1 != null) {
         for(var4 = 0; var4 < var3; ++var4) {
            if(var1.equals(var2[var4])) {
               return true;
            }
         }
      } else {
         for(var4 = 0; var4 < var3; ++var4) {
            if(var2[var4] == null) {
               return true;
            }
         }
      }

      return false;
   }

   public int indexOf(Object var1) {
      Object[] var2 = this.d;
      int var3 = this.c;
      int var4;
      if(var1 != null) {
         for(var4 = 0; var4 < var3; ++var4) {
            if(var1.equals(var2[var4])) {
               return var4;
            }
         }
      } else {
         for(var4 = 0; var4 < var3; ++var4) {
            if(var2[var4] == null) {
               return var4;
            }
         }
      }

      return -1;
   }

   public int lastIndexOf(Object var1) {
      Object[] var2 = this.d;
      int var3;
      if(var1 != null) {
         for(var3 = this.c - 1; var3 >= 0; --var3) {
            if(var1.equals(var2[var3])) {
               return var3;
            }
         }
      } else {
         for(var3 = this.c - 1; var3 >= 0; --var3) {
            if(var2[var3] == null) {
               return var3;
            }
         }
      }

      return -1;
   }

   public Object remove(int var1) {
      Object[] var2 = this.d;
      int var3 = this.c;
      if(var1 >= var3) {
         a(var1, var3);
      }

      Object var4 = var2[var1];
      int var10001 = var1 + 1;
      --var3;
      System.arraycopy(var2, var10001, var2, var1, var3 - var1);
      var2[var3] = null;
      this.c = var3;
      ++this.modCount;
      return var4;
   }

   public boolean remove(Object var1) {
      Object[] var2 = this.d;
      int var3 = this.c;
      int var10001;
      int var4;
      if(var1 != null) {
         for(var4 = 0; var4 < var3; ++var4) {
            if(var1.equals(var2[var4])) {
               var10001 = var4 + 1;
               --var3;
               System.arraycopy(var2, var10001, var2, var4, var3 - var4);
               var2[var3] = null;
               this.c = var3;
               ++this.modCount;
               return true;
            }
         }
      } else {
         for(var4 = 0; var4 < var3; ++var4) {
            if(var2[var4] == null) {
               var10001 = var4 + 1;
               --var3;
               System.arraycopy(var2, var10001, var2, var4, var3 - var4);
               var2[var3] = null;
               this.c = var3;
               ++this.modCount;
               return true;
            }
         }
      }

      return false;
   }

   protected void removeRange(int var1, int var2) {
      if(var1 != var2) {
         Object[] var3 = this.d;
         int var4 = this.c;
         if(var1 >= var4) {
            throw new IndexOutOfBoundsException("fromIndex " + var1 + " >= size " + this.c);
         } else if(var2 > var4) {
            throw new IndexOutOfBoundsException("toIndex " + var2 + " > size " + this.c);
         } else if(var1 > var2) {
            throw new IndexOutOfBoundsException("fromIndex " + var1 + " > toIndex " + var2);
         } else {
            System.arraycopy(var3, var2, var3, var1, var4 - var2);
            int var5 = var2 - var1;
            Arrays.fill(var3, var4 - var5, var4, (Object)null);
            this.c = var4 - var5;
            ++this.modCount;
         }
      }
   }

   public Object set(int var1, Object var2) {
      Object[] var3 = this.d;
      if(var1 >= this.c) {
         a(var1, this.c);
      }

      Object var4 = var3[var1];
      var3[var1] = var2;
      return var4;
   }

   public Object[] toArray() {
      int var1 = this.c;
      Object[] var2 = new Object[var1];
      System.arraycopy(this.d, 0, var2, 0, var1);
      return var2;
   }

   public Object[] toArray(Object[] var1) {
      int var2 = this.c;
      if(var1.length < var2) {
         Object[] var3 = (Object[])((Object[])Array.newInstance(var1.getClass().getComponentType(), var2));
         var1 = var3;
      }

      System.arraycopy(this.d, 0, var1, 0, var2);
      if(var1.length > var2) {
         var1[var2] = null;
      }

      return var1;
   }

   public Iterator iterator() {
      return new p(this, (o$1)null);
   }

   public int hashCode() {
      Object[] var1 = this.d;
      int var2 = 1;
      int var3 = 0;

      for(int var4 = this.c; var3 < var4; ++var3) {
         Object var5 = var1[var3];
         var2 = 31 * var2 + (var5 == null?0:var5.hashCode());
      }

      return var2;
   }

   public boolean equals(Object var1) {
      if(var1 == this) {
         return true;
      } else if(!(var1 instanceof List)) {
         return false;
      } else {
         List var2 = (List)var1;
         int var3 = this.c;
         if(var2.size() != var3) {
            return false;
         } else {
            Object[] var4 = this.d;
            Object var7;
            if(var2 instanceof RandomAccess) {
               for(int var5 = 0; var5 < var3; ++var5) {
                  Object var6 = var4[var5];
                  var7 = var2.get(var5);
                  if(var6 == null) {
                     if(var7 != null) {
                        return false;
                     }
                  } else if(!var6.equals(var7)) {
                     return false;
                  }
               }
            } else {
               Iterator var9 = var2.iterator();

               for(int var10 = 0; var10 < var3; ++var10) {
                  var7 = var4[var10];
                  Object var8 = var9.next();
                  if(var7 == null) {
                     if(var8 != null) {
                        return false;
                     }
                  } else if(!var7.equals(var8)) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   // $FF: synthetic method
   static int a(o var0) {
      return var0.modCount;
   }

   // $FF: synthetic method
   static int b(o var0) {
      return var0.modCount;
   }

   // $FF: synthetic method
   static int c(o var0) {
      return var0.modCount;
   }

   // $FF: synthetic method
   static int d(o var0) {
      return ++var0.modCount;
   }
}
