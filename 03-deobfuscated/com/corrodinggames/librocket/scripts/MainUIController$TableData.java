/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.MainUIController$TableRow;
import java.util.ArrayList;

public class MainUIController$TableData {
    public ArrayList rows = new ArrayList();

    public boolean same(MainUIController$TableData root$TableData, boolean bl) {
        if (this.rows.size() != root$TableData.rows.size()) {
            return false;
        }
        for (int i = 0; i < this.rows.size(); ++i) {
            MainUIController$TableRow root$TableRow;
            MainUIController$TableRow root$TableRow2 = (MainUIController$TableRow)this.rows.get(i);
            if (root$TableRow2.same(root$TableRow = (MainUIController$TableRow)root$TableData.rows.get(i), bl)) continue;
            return false;
        }
        return true;
    }
}
