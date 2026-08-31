/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable$Creator
 */
package android.graphics;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;

final class Point$1
implements Parcelable.Creator {
    Point$1() {
    }

    public Point a(Parcel parcel) {
        Point point = new Point();
        point.a(parcel);
        return point;
    }

    public Point[] a(int n) {
        return new Point[n];
    }

    public /* synthetic */ Object[] newArray(int n) {
        return this.a(n);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }
}
