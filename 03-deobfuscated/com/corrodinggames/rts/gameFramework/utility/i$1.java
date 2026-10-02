/*
 * v19.133f67 补建: 02b utility/i$1.java 直译 (AssetIndex 建索引线程)
 */
package com.corrodinggames.rts.gameFramework.utility;

class i$1 extends Thread {

    final i a;

    i$1(i var1) {
        this.a = var1;
    }

    public void run() {
        this.a.b();
    }
}
