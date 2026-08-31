/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 */
package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

public class PointF
implements Parcelable {
    public float a;
    public float b;

    public PointF() {
    }

    public PointF(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public final void a(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public final void a(PointF pointF) {
        this.a = pointF.a;
        this.b = pointF.b;
    }

    public final void b(float f, float f2) {
        this.a += f;
        this.b += f2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeFloat(this.a);
        parcel.writeFloat(this.b);
    }
}
