/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 */
package android.graphics;

import android.graphics.Point$1;
import android.os.Parcel;
import android.os.Parcelable;

public class Point
implements Parcelable {
    public int a;
    public int b;
    public static final Parcelable.Creator c = new Point$1();

    public Point() {
    }

    public Point(int n, int n2) {
        this.a = n;
        this.b = n2;
    }

    public void a(int n, int n2) {
        this.a = n;
        this.b = n2;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Point point = (Point)object;
        if (this.a != point.a) {
            return false;
        }
        return this.b == point.b;
    }

    public int hashCode() {
        int n = this.a;
        n = 31 * n + this.b;
        return n;
    }

    public String toString() {
        return "Point(" + this.a + ", " + this.b + ")";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
    }

    public void a(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
    }
}
