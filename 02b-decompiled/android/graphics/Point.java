package android.graphics;

import android.graphics.Point$1;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Point implements Parcelable {

   public int a;
   public int b;
   public static final Creator c = new Point$1();


   public Point() {}

   public Point(int var1, int var2) {
      this.a = var1;
      this.b = var2;
   }

   public void a(int var1, int var2) {
      this.a = var1;
      this.b = var2;
   }

   public boolean equals(Object var1) {
      if(this == var1) {
         return true;
      } else if(var1 != null && this.getClass() == var1.getClass()) {
         Point var2 = (Point)var1;
         return this.a != var2.a?false:this.b == var2.b;
      } else {
         return false;
      }
   }

   public int hashCode() {
      int var1 = this.a;
      var1 = 31 * var1 + this.b;
      return var1;
   }

   public String toString() {
      return "Point(" + this.a + ", " + this.b + ")";
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeInt(this.a);
      var1.writeInt(this.b);
   }

   public void a(Parcel var1) {
      this.a = var1.readInt();
      this.b = var1.readInt();
   }

}
