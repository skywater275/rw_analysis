/*
 * Decompiled with CFR 0.152.
 */
package android.graphics;

import android.graphics.Matrix$1;

public class Matrix {
    public static Matrix a = new Matrix$1();
    public int b = Matrix.a(0);

    public boolean equals(Object object) {
        if (!(object instanceof Matrix)) {
            return false;
        }
        return Matrix.a(this.b, ((Matrix)object).b);
    }

    public int hashCode() {
        return 44;
    }

    public void a(float[] fArray) {
        if (fArray.length < 9) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Matrix.a(this.b, fArray);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        stringBuilder.append("Matrix{");
        this.a(stringBuilder);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void a(StringBuilder stringBuilder) {
        float[] fArray = new float[9];
        this.a(fArray);
        stringBuilder.append('[');
        stringBuilder.append(fArray[0]);
        stringBuilder.append(", ");
        stringBuilder.append(fArray[1]);
        stringBuilder.append(", ");
        stringBuilder.append(fArray[2]);
        stringBuilder.append("][");
        stringBuilder.append(fArray[3]);
        stringBuilder.append(", ");
        stringBuilder.append(fArray[4]);
        stringBuilder.append(", ");
        stringBuilder.append(fArray[5]);
        stringBuilder.append("][");
        stringBuilder.append(fArray[6]);
        stringBuilder.append(", ");
        stringBuilder.append(fArray[7]);
        stringBuilder.append(", ");
        stringBuilder.append(fArray[8]);
        stringBuilder.append(']');
    }

    protected void finalize() {
        try {
            Matrix.b(this.b);
        }
        finally {
            super.finalize();
        }
    }

    private static int a(int n) {
        return 0;
    }

    private static void a(int n, float[] fArray) {
    }

    private static boolean a(int n, int n2) {
        return false;
    }

    private static void b(int n) {
    }
}
