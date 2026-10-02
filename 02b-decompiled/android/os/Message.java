package android.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message$1;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Parcelable.Creator;

public final class Message implements Parcelable {

   public int a;
   public int b;
   public int c;
   public Object d;
   public Messenger e;
   public int f = -1;
   int g;
   long h;
   Bundle i;
   Handler j;
   Runnable k;
   Message l;
   private static final Object n = new Object();
   private static Message o;
   private static int p = 0;
   private static boolean q = true;
   public static final Creator m = new Message$1();


   public static Message a() {
      Object var0 = n;
      synchronized(n) {
         if(o != null) {
            Message var1 = o;
            o = var1.l;
            var1.l = null;
            var1.g = 0;
            --p;
            return var1;
         }
      }

      return new Message();
   }

   public static Message a(Handler var0) {
      Message var1 = a();
      var1.j = var0;
      return var1;
   }

   public void b() {
      if(this.f()) {
         if(q) {
            throw new IllegalStateException("This message cannot be recycled because it is still in use.");
         }
      } else {
         this.c();
      }
   }

   void c() {
      this.g = 1;
      this.a = 0;
      this.b = 0;
      this.c = 0;
      this.d = null;
      this.e = null;
      this.f = -1;
      this.h = 0L;
      this.j = null;
      this.k = null;
      this.i = null;
      Object var1 = n;
      synchronized(n) {
         if(p < 50) {
            this.l = o;
            o = this;
            ++p;
         }

      }
   }

   public Bundle d() {
      if(this.i == null) {
         this.i = new Bundle();
      }

      return this.i;
   }

   public boolean e() {
      return (this.g & 2) != 0;
   }

   public void a(boolean var1) {
      if(var1) {
         this.g |= 2;
      } else {
         this.g &= -3;
      }

   }

   boolean f() {
      return (this.g & 1) == 1;
   }

   void g() {
      this.g |= 1;
   }

   public String toString() {
      return this.a(SystemClock.a());
   }

   String a(long var1) {
      StringBuilder var3 = new StringBuilder();
      var3.append("{ when=");
      var3.append("corrodinggames-unsupported");
      if(this.j != null) {
         if(this.k != null) {
            var3.append(" callback=");
            var3.append(this.k.getClass().getName());
         } else {
            var3.append(" what=");
            var3.append(this.a);
         }

         if(this.b != 0) {
            var3.append(" arg1=");
            var3.append(this.b);
         }

         if(this.c != 0) {
            var3.append(" arg2=");
            var3.append(this.c);
         }

         if(this.d != null) {
            var3.append(" obj=");
            var3.append(this.d);
         }

         var3.append(" target=");
         var3.append(this.j.getClass().getName());
      } else {
         var3.append(" barrier=");
         var3.append(this.b);
      }

      var3.append(" }");
      return var3.toString();
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      if(this.k != null) {
         throw new RuntimeException("Can\'t marshal callbacks across processes.");
      } else {
         var1.writeInt(this.a);
         var1.writeInt(this.b);
         var1.writeInt(this.c);
         if(this.d != null) {
            try {
               Parcelable var3 = (Parcelable)this.d;
               var1.writeInt(1);
               var1.writeParcelable(var3, var2);
            } catch (ClassCastException var4) {
               throw new RuntimeException("Can\'t marshal non-Parcelable objects across processes.");
            }
         } else {
            var1.writeInt(0);
         }

         var1.writeLong(this.h);
         var1.writeBundle(this.i);
         Messenger.writeMessengerOrNullToParcel(this.e, var1);
         var1.writeInt(this.f);
      }
   }

   private void a(Parcel var1) {
      this.a = var1.readInt();
      this.b = var1.readInt();
      this.c = var1.readInt();
      if(var1.readInt() != 0) {
         this.d = var1.readParcelable(this.getClass().getClassLoader());
      }

      this.h = var1.readLong();
      this.i = var1.readBundle();
      this.e = Messenger.readMessengerOrNullFromParcel(var1);
      this.f = var1.readInt();
   }

   // $FF: synthetic method
   static void a(Message var0, Parcel var1) {
      var0.a(var1);
   }

}
