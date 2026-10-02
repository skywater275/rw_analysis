/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable$Creator
 */
package android.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.KeyEvent;

final class KeyEvent$1
implements Parcelable.Creator {
    KeyEvent$1() {
    }

    public KeyEvent a(Parcel parcel) {
        parcel.readInt();
        return KeyEvent.a(parcel);
    }

    public KeyEvent[] a(int n) {
        return new KeyEvent[n];
    }

    public /* synthetic */ Object[] newArray(int n) {
        return this.a(n);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }
}
