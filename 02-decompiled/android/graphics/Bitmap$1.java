/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable$Creator
 */
package android.graphics;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

final class Bitmap$1
implements Parcelable.Creator {
    Bitmap$1() {
    }

    public Bitmap a(Parcel parcel) {
        Bitmap bitmap = Bitmap.a(parcel);
        if (bitmap == null) {
            throw new RuntimeException("Failed to unparcel Bitmap");
        }
        return bitmap;
    }

    public Bitmap[] a(int n) {
        return new Bitmap[n];
    }

    public /* synthetic */ Object[] newArray(int n) {
        return this.a(n);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }
}
