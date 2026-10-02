/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.view.InputEvent
 */
package android.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.InputEvent;
import android.view.KeyEvent$1;

public class KeyEvent
extends InputEvent
implements Parcelable {
    private static final SparseArray b = new SparseArray();
    private static final String[] c = new String[]{"META_SHIFT_ON", "META_ALT_ON", "META_SYM_ON", "META_FUNCTION_ON", "META_ALT_LEFT_ON", "META_ALT_RIGHT_ON", "META_SHIFT_LEFT_ON", "META_SHIFT_RIGHT_ON", "META_CAP_LOCKED", "META_ALT_LOCKED", "META_SYM_LOCKED", "0x00000800", "META_CTRL_ON", "META_CTRL_LEFT_ON", "META_CTRL_RIGHT_ON", "0x00008000", "META_META_ON", "META_META_LEFT_ON", "META_META_RIGHT_ON", "0x00080000", "META_CAPS_LOCK_ON", "META_NUM_LOCK_ON", "META_SCROLL_LOCK_ON", "0x00800000", "0x01000000", "0x02000000", "0x04000000", "0x08000000", "0x10000000", "0x20000000", "0x40000000", "0x80000000"};
    private static final Object d = new Object();
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private long m;
    private long n;
    private String o;
    public static final Parcelable.Creator a;

    private static void b() {
        SparseArray sparseArray = b;
        sparseArray.c(0, "KEYCODE_UNKNOWN");
        sparseArray.c(1, "KEYCODE_SOFT_LEFT");
        sparseArray.c(2, "KEYCODE_SOFT_RIGHT");
        sparseArray.c(3, "KEYCODE_HOME");
        sparseArray.c(4, "KEYCODE_BACK");
        sparseArray.c(5, "KEYCODE_CALL");
        sparseArray.c(6, "KEYCODE_ENDCALL");
        sparseArray.c(7, "KEYCODE_0");
        sparseArray.c(8, "KEYCODE_1");
        sparseArray.c(9, "KEYCODE_2");
        sparseArray.c(10, "KEYCODE_3");
        sparseArray.c(11, "KEYCODE_4");
        sparseArray.c(12, "KEYCODE_5");
        sparseArray.c(13, "KEYCODE_6");
        sparseArray.c(14, "KEYCODE_7");
        sparseArray.c(15, "KEYCODE_8");
        sparseArray.c(16, "KEYCODE_9");
        sparseArray.c(17, "KEYCODE_STAR");
        sparseArray.c(18, "KEYCODE_POUND");
        sparseArray.c(19, "KEYCODE_DPAD_UP");
        sparseArray.c(20, "KEYCODE_DPAD_DOWN");
        sparseArray.c(21, "KEYCODE_DPAD_LEFT");
        sparseArray.c(22, "KEYCODE_DPAD_RIGHT");
        sparseArray.c(23, "KEYCODE_DPAD_CENTER");
        sparseArray.c(24, "KEYCODE_VOLUME_UP");
        sparseArray.c(25, "KEYCODE_VOLUME_DOWN");
        sparseArray.c(26, "KEYCODE_POWER");
        sparseArray.c(27, "KEYCODE_CAMERA");
        sparseArray.c(28, "KEYCODE_CLEAR");
        sparseArray.c(29, "KEYCODE_A");
        sparseArray.c(30, "KEYCODE_B");
        sparseArray.c(31, "KEYCODE_C");
        sparseArray.c(32, "KEYCODE_D");
        sparseArray.c(33, "KEYCODE_E");
        sparseArray.c(34, "KEYCODE_F");
        sparseArray.c(35, "KEYCODE_G");
        sparseArray.c(36, "KEYCODE_H");
        sparseArray.c(37, "KEYCODE_I");
        sparseArray.c(38, "KEYCODE_J");
        sparseArray.c(39, "KEYCODE_K");
        sparseArray.c(40, "KEYCODE_L");
        sparseArray.c(41, "KEYCODE_M");
        sparseArray.c(42, "KEYCODE_N");
        sparseArray.c(43, "KEYCODE_O");
        sparseArray.c(44, "KEYCODE_P");
        sparseArray.c(45, "KEYCODE_Q");
        sparseArray.c(46, "KEYCODE_R");
        sparseArray.c(47, "KEYCODE_S");
        sparseArray.c(48, "KEYCODE_T");
        sparseArray.c(49, "KEYCODE_U");
        sparseArray.c(50, "KEYCODE_V");
        sparseArray.c(51, "KEYCODE_W");
        sparseArray.c(52, "KEYCODE_X");
        sparseArray.c(53, "KEYCODE_Y");
        sparseArray.c(54, "KEYCODE_Z");
        sparseArray.c(55, "KEYCODE_COMMA");
        sparseArray.c(56, "KEYCODE_PERIOD");
        sparseArray.c(57, "KEYCODE_ALT_LEFT");
        sparseArray.c(58, "KEYCODE_ALT_RIGHT");
        sparseArray.c(59, "KEYCODE_SHIFT_LEFT");
        sparseArray.c(60, "KEYCODE_SHIFT_RIGHT");
        sparseArray.c(61, "KEYCODE_TAB");
        sparseArray.c(62, "KEYCODE_SPACE");
        sparseArray.c(63, "KEYCODE_SYM");
        sparseArray.c(64, "KEYCODE_EXPLORER");
        sparseArray.c(65, "KEYCODE_ENVELOPE");
        sparseArray.c(66, "KEYCODE_ENTER");
        sparseArray.c(67, "KEYCODE_DEL");
        sparseArray.c(68, "KEYCODE_GRAVE");
        sparseArray.c(69, "KEYCODE_MINUS");
        sparseArray.c(70, "KEYCODE_EQUALS");
        sparseArray.c(71, "KEYCODE_LEFT_BRACKET");
        sparseArray.c(72, "KEYCODE_RIGHT_BRACKET");
        sparseArray.c(73, "KEYCODE_BACKSLASH");
        sparseArray.c(74, "KEYCODE_SEMICOLON");
        sparseArray.c(75, "KEYCODE_APOSTROPHE");
        sparseArray.c(76, "KEYCODE_SLASH");
        sparseArray.c(77, "KEYCODE_AT");
        sparseArray.c(78, "KEYCODE_NUM");
        sparseArray.c(79, "KEYCODE_HEADSETHOOK");
        sparseArray.c(80, "KEYCODE_FOCUS");
        sparseArray.c(81, "KEYCODE_PLUS");
        sparseArray.c(82, "KEYCODE_MENU");
        sparseArray.c(83, "KEYCODE_NOTIFICATION");
        sparseArray.c(84, "KEYCODE_SEARCH");
        sparseArray.c(85, "KEYCODE_MEDIA_PLAY_PAUSE");
        sparseArray.c(86, "KEYCODE_MEDIA_STOP");
        sparseArray.c(87, "KEYCODE_MEDIA_NEXT");
        sparseArray.c(88, "KEYCODE_MEDIA_PREVIOUS");
        sparseArray.c(89, "KEYCODE_MEDIA_REWIND");
        sparseArray.c(90, "KEYCODE_MEDIA_FAST_FORWARD");
        sparseArray.c(91, "KEYCODE_MUTE");
        sparseArray.c(92, "KEYCODE_PAGE_UP");
        sparseArray.c(93, "KEYCODE_PAGE_DOWN");
        sparseArray.c(94, "KEYCODE_PICTSYMBOLS");
        sparseArray.c(95, "KEYCODE_SWITCH_CHARSET");
        sparseArray.c(96, "KEYCODE_BUTTON_A");
        sparseArray.c(97, "KEYCODE_BUTTON_B");
        sparseArray.c(98, "KEYCODE_BUTTON_C");
        sparseArray.c(99, "KEYCODE_BUTTON_X");
        sparseArray.c(100, "KEYCODE_BUTTON_Y");
        sparseArray.c(101, "KEYCODE_BUTTON_Z");
        sparseArray.c(102, "KEYCODE_BUTTON_L1");
        sparseArray.c(103, "KEYCODE_BUTTON_R1");
        sparseArray.c(104, "KEYCODE_BUTTON_L2");
        sparseArray.c(105, "KEYCODE_BUTTON_R2");
        sparseArray.c(106, "KEYCODE_BUTTON_THUMBL");
        sparseArray.c(107, "KEYCODE_BUTTON_THUMBR");
        sparseArray.c(108, "KEYCODE_BUTTON_START");
        sparseArray.c(109, "KEYCODE_BUTTON_SELECT");
        sparseArray.c(110, "KEYCODE_BUTTON_MODE");
        sparseArray.c(111, "KEYCODE_ESCAPE");
        sparseArray.c(112, "KEYCODE_FORWARD_DEL");
        sparseArray.c(113, "KEYCODE_CTRL_LEFT");
        sparseArray.c(114, "KEYCODE_CTRL_RIGHT");
        sparseArray.c(115, "KEYCODE_CAPS_LOCK");
        sparseArray.c(116, "KEYCODE_SCROLL_LOCK");
        sparseArray.c(117, "KEYCODE_META_LEFT");
        sparseArray.c(118, "KEYCODE_META_RIGHT");
        sparseArray.c(119, "KEYCODE_FUNCTION");
        sparseArray.c(120, "KEYCODE_SYSRQ");
        sparseArray.c(121, "KEYCODE_BREAK");
        sparseArray.c(122, "KEYCODE_MOVE_HOME");
        sparseArray.c(123, "KEYCODE_MOVE_END");
        sparseArray.c(124, "KEYCODE_INSERT");
        sparseArray.c(125, "KEYCODE_FORWARD");
        sparseArray.c(126, "KEYCODE_MEDIA_PLAY");
        sparseArray.c(127, "KEYCODE_MEDIA_PAUSE");
        sparseArray.c(128, "KEYCODE_MEDIA_CLOSE");
        sparseArray.c(129, "KEYCODE_MEDIA_EJECT");
        sparseArray.c(130, "KEYCODE_MEDIA_RECORD");
        sparseArray.c(131, "KEYCODE_F1");
        sparseArray.c(132, "KEYCODE_F2");
        sparseArray.c(133, "KEYCODE_F3");
        sparseArray.c(134, "KEYCODE_F4");
        sparseArray.c(135, "KEYCODE_F5");
        sparseArray.c(136, "KEYCODE_F6");
        sparseArray.c(137, "KEYCODE_F7");
        sparseArray.c(138, "KEYCODE_F8");
        sparseArray.c(139, "KEYCODE_F9");
        sparseArray.c(140, "KEYCODE_F10");
        sparseArray.c(141, "KEYCODE_F11");
        sparseArray.c(142, "KEYCODE_F12");
        sparseArray.c(143, "KEYCODE_NUM_LOCK");
        sparseArray.c(144, "KEYCODE_NUMPAD_0");
        sparseArray.c(145, "KEYCODE_NUMPAD_1");
        sparseArray.c(146, "KEYCODE_NUMPAD_2");
        sparseArray.c(147, "KEYCODE_NUMPAD_3");
        sparseArray.c(148, "KEYCODE_NUMPAD_4");
        sparseArray.c(149, "KEYCODE_NUMPAD_5");
        sparseArray.c(150, "KEYCODE_NUMPAD_6");
        sparseArray.c(151, "KEYCODE_NUMPAD_7");
        sparseArray.c(152, "KEYCODE_NUMPAD_8");
        sparseArray.c(153, "KEYCODE_NUMPAD_9");
        sparseArray.c(154, "KEYCODE_NUMPAD_DIVIDE");
        sparseArray.c(155, "KEYCODE_NUMPAD_MULTIPLY");
        sparseArray.c(156, "KEYCODE_NUMPAD_SUBTRACT");
        sparseArray.c(157, "KEYCODE_NUMPAD_ADD");
        sparseArray.c(158, "KEYCODE_NUMPAD_DOT");
        sparseArray.c(159, "KEYCODE_NUMPAD_COMMA");
        sparseArray.c(160, "KEYCODE_NUMPAD_ENTER");
        sparseArray.c(161, "KEYCODE_NUMPAD_EQUALS");
        sparseArray.c(162, "KEYCODE_NUMPAD_LEFT_PAREN");
        sparseArray.c(163, "KEYCODE_NUMPAD_RIGHT_PAREN");
        sparseArray.c(164, "KEYCODE_VOLUME_MUTE");
        sparseArray.c(165, "KEYCODE_INFO");
        sparseArray.c(166, "KEYCODE_CHANNEL_UP");
        sparseArray.c(167, "KEYCODE_CHANNEL_DOWN");
        sparseArray.c(168, "KEYCODE_ZOOM_IN");
        sparseArray.c(169, "KEYCODE_ZOOM_OUT");
        sparseArray.c(170, "KEYCODE_TV");
        sparseArray.c(171, "KEYCODE_WINDOW");
        sparseArray.c(172, "KEYCODE_GUIDE");
        sparseArray.c(173, "KEYCODE_DVR");
        sparseArray.c(174, "KEYCODE_BOOKMARK");
        sparseArray.c(175, "KEYCODE_CAPTIONS");
        sparseArray.c(176, "KEYCODE_SETTINGS");
        sparseArray.c(177, "KEYCODE_TV_POWER");
        sparseArray.c(178, "KEYCODE_TV_INPUT");
        sparseArray.c(180, "KEYCODE_STB_INPUT");
        sparseArray.c(179, "KEYCODE_STB_POWER");
        sparseArray.c(181, "KEYCODE_AVR_POWER");
        sparseArray.c(182, "KEYCODE_AVR_INPUT");
        sparseArray.c(183, "KEYCODE_PROG_RED");
        sparseArray.c(184, "KEYCODE_PROG_GREEN");
        sparseArray.c(185, "KEYCODE_PROG_YELLOW");
        sparseArray.c(186, "KEYCODE_PROG_BLUE");
        sparseArray.c(187, "KEYCODE_APP_SWITCH");
        sparseArray.c(188, "KEYCODE_BUTTON_1");
        sparseArray.c(189, "KEYCODE_BUTTON_2");
        sparseArray.c(190, "KEYCODE_BUTTON_3");
        sparseArray.c(191, "KEYCODE_BUTTON_4");
        sparseArray.c(192, "KEYCODE_BUTTON_5");
        sparseArray.c(193, "KEYCODE_BUTTON_6");
        sparseArray.c(194, "KEYCODE_BUTTON_7");
        sparseArray.c(195, "KEYCODE_BUTTON_8");
        sparseArray.c(196, "KEYCODE_BUTTON_9");
        sparseArray.c(197, "KEYCODE_BUTTON_10");
        sparseArray.c(198, "KEYCODE_BUTTON_11");
        sparseArray.c(199, "KEYCODE_BUTTON_12");
        sparseArray.c(200, "KEYCODE_BUTTON_13");
        sparseArray.c(201, "KEYCODE_BUTTON_14");
        sparseArray.c(202, "KEYCODE_BUTTON_15");
        sparseArray.c(203, "KEYCODE_BUTTON_16");
        sparseArray.c(204, "KEYCODE_LANGUAGE_SWITCH");
        sparseArray.c(205, "KEYCODE_MANNER_MODE");
        sparseArray.c(206, "KEYCODE_3D_MODE");
        sparseArray.c(207, "KEYCODE_CONTACTS");
        sparseArray.c(208, "KEYCODE_CALENDAR");
        sparseArray.c(209, "KEYCODE_MUSIC");
        sparseArray.c(210, "KEYCODE_CALCULATOR");
        sparseArray.c(211, "KEYCODE_ZENKAKU_HANKAKU");
        sparseArray.c(212, "KEYCODE_EISU");
        sparseArray.c(213, "KEYCODE_MUHENKAN");
        sparseArray.c(214, "KEYCODE_HENKAN");
        sparseArray.c(215, "KEYCODE_KATAKANA_HIRAGANA");
        sparseArray.c(216, "KEYCODE_YEN");
        sparseArray.c(217, "KEYCODE_RO");
        sparseArray.c(218, "KEYCODE_KANA");
        sparseArray.c(219, "KEYCODE_ASSIST");
        sparseArray.c(220, "KEYCODE_BRIGHTNESS_DOWN");
        sparseArray.c(221, "KEYCODE_BRIGHTNESS_UP");
        sparseArray.c(222, "KEYCODE_MEDIA_AUDIO_TRACK");
    }

    public static int a() {
        return 222;
    }

    private KeyEvent() {
    }

    public final int getDeviceId() {
        return this.e;
    }

    public final int getSource() {
        return this.f;
    }

    public final long getEventTime() {
        return this.n;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("KeyEvent { action=").append(KeyEvent.a(this.h));
        stringBuilder.append(", keyCode=").append(KeyEvent.b(this.i));
        stringBuilder.append(", scanCode=").append(this.j);
        if (this.o != null) {
            stringBuilder.append(", characters=\"").append(this.o).append("\"");
        }
        stringBuilder.append(", metaState=").append(KeyEvent.c(this.g));
        stringBuilder.append(", flags=0x").append(Integer.toHexString(this.l));
        stringBuilder.append(", repeatCount=").append(this.k);
        stringBuilder.append(", eventTime=").append(this.n);
        stringBuilder.append(", downTime=").append(this.m);
        stringBuilder.append(", deviceId=").append(this.e);
        stringBuilder.append(", source=0x").append(Integer.toHexString(this.f));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }

    public static String a(int n) {
        switch (n) {
            case 0: {
                return "ACTION_DOWN";
            }
            case 1: {
                return "ACTION_UP";
            }
            case 2: {
                return "ACTION_MULTIPLE";
            }
        }
        return Integer.toString(n);
    }

    public static String b(int n) {
        String string = (String)b.a(n);
        return string != null ? string : Integer.toString(n);
    }

    public static String c(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder stringBuilder = null;
        int n2 = 0;
        while (n != 0) {
            boolean bl = (n & 1) != 0;
            n >>>= 1;
            if (bl) {
                String string = c[n2];
                if (stringBuilder == null) {
                    if (n == 0) {
                        return string;
                    }
                    stringBuilder = new StringBuilder(string);
                } else {
                    stringBuilder.append('|');
                    stringBuilder.append(string);
                }
            }
            ++n2;
        }
        return stringBuilder.toString();
    }

    public static KeyEvent a(Parcel parcel) {
        return new KeyEvent(parcel);
    }

    private KeyEvent(Parcel parcel) {
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.k = parcel.readInt();
        this.g = parcel.readInt();
        this.j = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readLong();
        this.n = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.k);
        parcel.writeInt(this.g);
        parcel.writeInt(this.j);
        parcel.writeInt(this.l);
        parcel.writeLong(this.m);
        parcel.writeLong(this.n);
    }

    static {
        KeyEvent.b();
        a = new KeyEvent$1();
    }
}
