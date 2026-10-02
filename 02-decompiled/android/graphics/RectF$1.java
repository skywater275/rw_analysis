/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable$Creator
 */
package android.graphics;

import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;

final class RectF$1
implements Parcelable.Creator {
    RectF$1() {
    }

    public RectF a(Parcel parcel) {
        RectF rectF = new RectF();
        rectF.a(parcel);
        return rectF;
    }

    public RectF[] a(int n) {
        return new RectF[n];
    }

    public /* synthetic */ Object[] newArray(int n) {
        return this.a(n);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }
}
