/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.MotionEvent
 */
package com.corrodinggames.rts.appFramework;

import android.util.Log;
import android.view.MotionEvent;
import java.lang.reflect.Method;

public class MultiTouchHandler {  // v19.133f2: 02b appFramework/l.java (MultiTouchController) 类名修正 (原 GlobalState 误名)
    public static final boolean activePointers;
    public static final boolean pointerX;
    private static Method pointerY;
    private static Method pointerDownX;
    private static Method pointerDownY;
    private static Method pointerDownTime;
    private static Method pointerState;
    private static Method maxPointers;
    private static Method activePointerCount;
    private static Method lastTouchX;
    private static Method lastTouchY;
    private static int lastTouchTime;
    private static int gestureDetector;
    private static final float[] doubleTapActive;
    private static final float[] pinchDistance;
    private static final float[] pinchStartDistance;
    private static final int[] pinchActive;
    private static final int[] scrollVelocityX;

    static /* synthetic */ int[] a() {
        return scrollVelocityX;
    }

    static {
        lastTouchTime = 6;
        gestureDetector = 8;
        boolean bl = false;
        try {
            pointerDownX = MotionEvent.class.getMethod("getPointerCount", new Class[0]);
            pointerDownY = MotionEvent.class.getMethod("findPointerIndex", Integer.TYPE);
            pointerDownTime = MotionEvent.class.getMethod("getPressure", Integer.TYPE);
            pointerState = MotionEvent.class.getMethod("getHistoricalX", Integer.TYPE, Integer.TYPE);
            maxPointers = MotionEvent.class.getMethod("getHistoricalY", Integer.TYPE, Integer.TYPE);
            activePointerCount = MotionEvent.class.getMethod("getHistoricalPressure", Integer.TYPE, Integer.TYPE);
            lastTouchX = MotionEvent.class.getMethod("getX", Integer.TYPE);
            lastTouchY = MotionEvent.class.getMethod("getY", Integer.TYPE);
            bl = true;
        }
        catch (Exception exception) {
            Log.b("MultiTouchController", "static initializer failed", exception);
        }
        activePointers = bl;
        if (activePointers) {  // 02b: if(a) (v19.133f2 修正)
            try {
                lastTouchTime = MotionEvent.class.getField("ACTION_POINTER_UP").getInt(null);
                gestureDetector = MotionEvent.class.getField("ACTION_POINTER_INDEX_SHIFT").getInt(null);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        boolean bl2 = false;
        try {
            pointerY = MotionEvent.class.getMethod("getButtonState", new Class[0]);
            bl2 = true;
            Log.b("MultiTouchController", "--- Mouse API succeeded");
        }
        catch (Exception exception) {
            Log.b("MultiTouchController", "static initializer for mouse failed", exception);
        }
        pointerX = bl2;
        doubleTapActive = new float[10];
        pinchDistance = new float[10];
        pinchStartDistance = new float[10];
        pinchActive = new int[10];
        scrollVelocityX = new int[10];
    }
}
