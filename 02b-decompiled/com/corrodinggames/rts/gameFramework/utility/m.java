package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.m$1;
import com.corrodinggames.rts.gameFramework.utility.n;
import com.corrodinggames.rts.gameFramework.utility.x;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class m extends AbstractList implements Serializable, Cloneable, RandomAccess {

   public int a;
   transient Object[] b;


   public m(int var1) {
      if(var1 < 0) {
         throw new IllegalArgumentException("capacity < 0: " + var1);
      } else {
         this.b = var1 == 0?x.g:new Object[var1];
      }
   }

   public m() {
      this.b = x.g;
   }

   public Object[] a() {
      return this.b;
   }

   public m(Collection var1) {
      Object[] var2 = var1.toArray();
      if(var2.getClass() != Object[].class) {
         Object[] var3 = new Object[var2.length];
         System.arraycopy(var2, 0, var3, 0, var2.length);
         var2 = var3;
      }

      this.b = var2;
      this.a = var2.length;
   }

   public boolean add(Object var1) {
      Object[] var2 = this.b;
      int var3 = this.a;
      if(var3 == var2.length) {
         Object[] var4 = new Object[var3 + (var3 < 6?12:var3 >> 1)];
         System.arraycopy(var2, 0, var4, 0, var3);
         var2 = var4;
         this.b = var4;
      }

      var2[var3] = var1;
      this.a = var3 + 1;
      ++this.modCount;
      return true;
   }

   public void add(int var1, Object var2) {
      Object[] var3 = this.b;
      int var4 = this.a;
      if(var1 > var4 || var1 < 0) {
         a(var1, var4);
      }

      if(var4 < var3.length) {
         System.arraycopy(var3, var1, var3, var1 + 1, var4 - var1);
      } else {
         Object[] var5 = new Object[b(var4)];
         System.arraycopy(var3, 0, var5, 0, var1);
         System.arraycopy(var3, var1, var5, var1 + 1, var4 - var1);
         var3 = var5;
         this.b = var5;
      }

      var3[var1] = var2;
      this.a = var4 + 1;
      ++this.modCount;
   }

   private static int b(int var0) {
      int var1 = var0 < 6?12:var0 >> 1;
      return var0 + var1;
   }

   public boolean addAll(Collection var1) {
      Object[] var2 = var1.toArray();
      int var3 = var2.length;
      if(var3 == 0) {
         return false;
      } else {
         Object[] var4 = this.b;
         int var5 = this.a;
         int var6 = var5 + var3;
         if(var6 > var4.length) {
            int var7 = b(var6 - 1);
            Object[] var8 = new Object[var7];
            System.arraycopy(var4, 0, var8, 0, var5);
            var4 = var8;
            this.b = var8;
         }

         System.arraycopy(var2, 0, var4, var5, var3);
         this.a = var6;
         ++this.modCount;
         return true;
      }
   }

   public boolean addAll(int var1, Collection var2) {
      int var3 = this.a;
      if(var1 > var3 || var1 < 0) {
         a(var1, var3);
      }

      Object[] var4 = var2.toArray();
      int var5 = var4.length;
      if(var5 == 0) {
         return false;
      } else {
         Object[] var6 = this.b;
         int var7 = var3 + var5;
         if(var7 <= var6.length) {
            System.arraycopy(var6, var1, var6, var1 + var5, var3 - var1);
         } else {
            int var8 = b(var7 - 1);
            Object[] var9 = new Object[var8];
            System.arraycopy(var6, 0, var9, 0, var1);
            System.arraycopy(var6, var1, var9, var1 + var5, var3 - var1);
            var6 = var9;
            this.b = var9;
         }

         System.arraycopy(var4, 0, var6, var1, var5);
         this.a = var7;
         ++this.modCount;
         return true;
      }
   }

   static IndexOutOfBoundsException a(int var0, int var1) {
      throw new IndexOutOfBoundsException("Invalid index " + var0 + ", size is " + var1);
   }

   public void clear() {
      if(this.a != 0) {
         Arrays.fill(this.b, 0, this.a, (Object)null);
         this.a = 0;
         ++this.modCount;
      }

   }

   public Object clone() {
      try {
         m var1 = (m)super.clone();
         var1.b = (Object[])this.b.clone();
         return var1;
      } catch (CloneNotSupportedException var2) {
         throw new AssertionError();
      }
   }

   public Object get(int var1) {
      if(var1 >= this.a) {
         a(var1, this.a);
      }

      return this.b[var1];
   }

   public final Object a(int var1) {
      return this.b[var1];
   }

   public int size() {
      return this.a;
   }

   public boolean isEmpty() {
      return this.a == 0;
   }

   public boolean contains(Object var1) {
      Object[] var2 = this.b;
      int var3 = this.a;
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
      Object[] var2 = this.b;
      int var3 = this.a;
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
      Object[] var2 = this.b;
      int var3;
      if(var1 != null) {
         for(var3 = this.a - 1; var3 >= 0; --var3) {
            if(var1.equals(var2[var3])) {
               return var3;
            }
         }
      } else {
         for(var3 = this.a - 1; var3 >= 0; --var3) {
            if(var2[var3] == null) {
               return var3;
            }
         }
      }

      return -1;
   }

   public Object remove(int var1) {
      Object[] var2 = this.b;
      int var3 = this.a;
      if(var1 >= var3) {
         a(var1, var3);
      }

      Object var4 = var2[var1];
      int var10001 = var1 + 1;
      --var3;
      System.arraycopy(var2, var10001, var2, var1, var3 - var1);
      var2[var3] = null;
      this.a = var3;
      ++this.modCount;
      return var4;
   }

   public Object b() {
      Object[] var1 = this.b;
      int var2 = this.a;
      int var3 = var2 - 1;
      if(var2 == 0) {
         a(var3, var2);
      }

      Object var4 = var1[var3];
      --var2;
      var1[var2] = null;
      this.a = var2;
      ++this.modCount;
      return var4;
   }

   public Object c() {
      Object[] var1 = this.b;
      int var2 = this.a;
      int var3 = var2 - 1;
      Object var4 = var1[var3];
      --var2;
      var1[var2] = null;
      this.a = var2;
      return var4;
   }

   public boolean remove(Object var1) {
      Object[] var2 = this.b;
      int var3 = this.a;
      int var10001;
      int var4;
      if(var1 != null) {
         for(var4 = 0; var4 < var3; ++var4) {
            if(var1.equals(var2[var4])) {
               var10001 = var4 + 1;
               --var3;
               System.arraycopy(var2, var10001, var2, var4, var3 - var4);
               var2[var3] = null;
               this.a = var3;
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
               this.a = var3;
               ++this.modCount;
               return true;
            }
         }
      }

      return false;
   }

   protected void removeRange(int var1, int var2) {
      if(var1 != var2) {
         Object[] var3 = this.b;
         int var4 = this.a;
         if(var1 >= var4) {
            throw new IndexOutOfBoundsException("fromIndex " + var1 + " >= size " + this.a);
         } else if(var2 > var4) {
            throw new IndexOutOfBoundsException("toIndex " + var2 + " > size " + this.a);
         } else if(var1 > var2) {
            throw new IndexOutOfBoundsException("fromIndex " + var1 + " > toIndex " + var2);
         } else {
            System.arraycopy(var3, var2, var3, var1, var4 - var2);
            int var5 = var2 - var1;
            Arrays.fill(var3, var4 - var5, var4, (Object)null);
            this.a = var4 - var5;
            ++this.modCount;
         }
      }
   }

   public Object set(int var1, Object var2) {
      Object[] var3 = this.b;
      if(var1 >= this.a) {
         a(var1, this.a);
      }

      Object var4 = var3[var1];
      var3[var1] = var2;
      return var4;
   }

   public Object[] toArray() {
      int var1 = this.a;
      Object[] var2 = new Object[var1];
      System.arraycopy(this.b, 0, var2, 0, var1);
      return var2;
   }

   public Object[] toArray(Object[] var1) {
      int var2 = this.a;
      if(var1.length < var2) {
         Object[] var3 = (Object[])((Object[])Array.newInstance(var1.getClass().getComponentType(), var2));
         var1 = var3;
      }

      System.arraycopy(this.b, 0, var1, 0, var2);
      if(var1.length > var2) {
         var1[var2] = null;
      }

      return var1;
   }

   public Iterator iterator() {
      return new n(this, (m$1)null);
   }

   public int hashCode() {
      Object[] var1 = this.b;
      int var2 = 1;
      int var3 = 0;

      for(int var4 = this.a; var3 < var4; ++var3) {
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
         int var3 = this.a;
         if(var2.size() != var3) {
            return false;
         } else {
            Object[] var4 = this.b;
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
   static int a(m var0) {
      return var0.modCount;
   }

   // $FF: synthetic method
   static int b(m var0) {
      return var0.modCount;
   }

   // $FF: synthetic method
   static int c(m var0) {
      return var0.modCount;
   }

   // $FF: synthetic method
   static int d(m var0) {
      return ++var0.modCount;
   }
}
