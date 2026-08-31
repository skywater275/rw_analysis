package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.g$1;
import com.corrodinggames.rts.gameFramework.utility.h;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class g extends AbstractCollection implements Serializable, Cloneable {

   private transient Object[] b = (Object[])(new Object[16]);
   private transient int c;
   private transient int d;


   private void c() {
      assert this.c == this.d;

      int var1 = this.c;
      int var2 = this.b.length;
      int var3 = var2 - var1;
      int var4 = var2 << 1;
      if(var4 < 0) {
         throw new IllegalStateException("Sorry, deque too big");
      } else {
         Object[] var5 = new Object[var4];
         System.arraycopy(this.b, var1, var5, 0, var3);
         System.arraycopy(this.b, 0, var5, var3, var1);
         this.b = (Object[])var5;
         this.c = 0;
         this.d = var2;
      }
   }

   private Object[] a(Object[] var1) {
      if(this.c < this.d) {
         System.arraycopy(this.b, this.c, var1, 0, this.size());
      } else if(this.c > this.d) {
         int var2 = this.b.length - this.c;
         System.arraycopy(this.b, this.c, var1, 0, var2);
         System.arraycopy(this.b, 0, var1, var2, this.d);
      }

      return var1;
   }

   public void a(Object var1) {
      if(var1 == null) {
         throw new NullPointerException();
      } else {
         this.b[this.d] = var1;
         if((this.d = this.d + 1 & this.b.length - 1) == this.c) {
            this.c();
         }

      }
   }

   public Object a() {
      int var1 = this.c;
      Object var2 = this.b[var1];
      if(var2 == null) {
         return null;
      } else {
         this.b[var1] = null;
         this.c = var1 + 1 & this.b.length - 1;
         return var2;
      }
   }

   public boolean b(Object var1) {
      if(var1 == null) {
         return false;
      } else {
         int var2 = this.b.length - 1;

         Object var4;
         for(int var3 = this.c; (var4 = this.b[var3]) != null; var3 = var3 + 1 & var2) {
            if(var1.equals(var4)) {
               this.a(var3);
               return true;
            }
         }

         return false;
      }
   }

   public boolean add(Object var1) {
      this.a(var1);
      return true;
   }

   private void d() {
      assert this.b[this.d] == null;

      if(!a) {
         label29: {
            if(this.c == this.d) {
               if(this.b[this.c] == null) {
                  break label29;
               }
            } else if(this.b[this.c] != null && this.b[this.d - 1 & this.b.length - 1] != null) {
               break label29;
            }

            throw new AssertionError();
         }
      }

      assert this.b[this.c - 1 & this.b.length - 1] == null;

   }

   private boolean a(int var1) {
      this.d();
      Object[] var2 = this.b;
      int var3 = var2.length - 1;
      int var4 = this.c;
      int var5 = this.d;
      int var6 = var1 - var4 & var3;
      int var7 = var5 - var1 & var3;
      if(var6 >= (var5 - var4 & var3)) {
         throw new ConcurrentModificationException();
      } else if(var6 < var7) {
         if(var4 <= var1) {
            System.arraycopy(var2, var4, var2, var4 + 1, var6);
         } else {
            System.arraycopy(var2, 0, var2, 1, var1);
            var2[0] = var2[var3];
            System.arraycopy(var2, var4, var2, var4 + 1, var3 - var4);
         }

         var2[var4] = null;
         this.c = var4 + 1 & var3;
         return false;
      } else {
         if(var1 < var5) {
            System.arraycopy(var2, var1 + 1, var2, var1, var7);
            this.d = var5 - 1;
         } else {
            System.arraycopy(var2, var1 + 1, var2, var1, var3 - var1);
            var2[var3] = var2[0];
            System.arraycopy(var2, 1, var2, 0, var5);
            this.d = var5 - 1 & var3;
         }

         return true;
      }
   }

   public int size() {
      return this.d - this.c & this.b.length - 1;
   }

   public boolean isEmpty() {
      return this.c == this.d;
   }

   public Iterator iterator() {
      return new h(this, (g$1)null);
   }

   public boolean contains(Object var1) {
      if(var1 == null) {
         return false;
      } else {
         int var2 = this.b.length - 1;

         Object var4;
         for(int var3 = this.c; (var4 = this.b[var3]) != null; var3 = var3 + 1 & var2) {
            if(var1.equals(var4)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean remove(Object var1) {
      return this.b(var1);
   }

   public void clear() {
      int var1 = this.c;
      int var2 = this.d;
      if(var1 != var2) {
         this.c = this.d = 0;
         int var3 = var1;
         int var4 = this.b.length - 1;

         do {
            this.b[var3] = null;
            var3 = var3 + 1 & var4;
         } while(var3 != var2);
      }

   }

   public Object[] toArray() {
      return this.a(new Object[this.size()]);
   }

   public Object[] toArray(Object[] var1) {
      int var2 = this.size();
      if(var1.length < var2) {
         var1 = (Object[])((Object[])Array.newInstance(var1.getClass().getComponentType(), var2));
      }

      this.a(var1);
      if(var1.length > var2) {
         var1[var2] = null;
      }

      return var1;
   }

   public g b() {
      try {
         g var1 = (g)super.clone();
         var1.b = (Object[])this.b.clone();
         return var1;
      } catch (CloneNotSupportedException var2) {
         throw new AssertionError();
      }
   }

   // $FF: synthetic method
   public Object clone() {
      return this.b();
   }

   // $FF: synthetic method
   static int a(g var0) {
      return var0.c;
   }

   // $FF: synthetic method
   static int b(g var0) {
      return var0.d;
   }

   // $FF: synthetic method
   static Object[] c(g var0) {
      return var0.b;
   }

   // $FF: synthetic method
   static boolean a(g var0, int var1) {
      return var0.a(var1);
   }

}
