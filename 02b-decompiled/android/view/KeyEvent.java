package android.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import android.view.InputEvent;
import android.view.KeyEvent$1;

public class KeyEvent extends InputEvent implements Parcelable {

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
   public static final Creator a;


   private static void b() {
      SparseArray var0 = b;
      var0.c(0, "KEYCODE_UNKNOWN");
      var0.c(1, "KEYCODE_SOFT_LEFT");
      var0.c(2, "KEYCODE_SOFT_RIGHT");
      var0.c(3, "KEYCODE_HOME");
      var0.c(4, "KEYCODE_BACK");
      var0.c(5, "KEYCODE_CALL");
      var0.c(6, "KEYCODE_ENDCALL");
      var0.c(7, "KEYCODE_0");
      var0.c(8, "KEYCODE_1");
      var0.c(9, "KEYCODE_2");
      var0.c(10, "KEYCODE_3");
      var0.c(11, "KEYCODE_4");
      var0.c(12, "KEYCODE_5");
      var0.c(13, "KEYCODE_6");
      var0.c(14, "KEYCODE_7");
      var0.c(15, "KEYCODE_8");
      var0.c(16, "KEYCODE_9");
      var0.c(17, "KEYCODE_STAR");
      var0.c(18, "KEYCODE_POUND");
      var0.c(19, "KEYCODE_DPAD_UP");
      var0.c(20, "KEYCODE_DPAD_DOWN");
      var0.c(21, "KEYCODE_DPAD_LEFT");
      var0.c(22, "KEYCODE_DPAD_RIGHT");
      var0.c(23, "KEYCODE_DPAD_CENTER");
      var0.c(24, "KEYCODE_VOLUME_UP");
      var0.c(25, "KEYCODE_VOLUME_DOWN");
      var0.c(26, "KEYCODE_POWER");
      var0.c(27, "KEYCODE_CAMERA");
      var0.c(28, "KEYCODE_CLEAR");
      var0.c(29, "KEYCODE_A");
      var0.c(30, "KEYCODE_B");
      var0.c(31, "KEYCODE_C");
      var0.c(32, "KEYCODE_D");
      var0.c(33, "KEYCODE_E");
      var0.c(34, "KEYCODE_F");
      var0.c(35, "KEYCODE_G");
      var0.c(36, "KEYCODE_H");
      var0.c(37, "KEYCODE_I");
      var0.c(38, "KEYCODE_J");
      var0.c(39, "KEYCODE_K");
      var0.c(40, "KEYCODE_L");
      var0.c(41, "KEYCODE_M");
      var0.c(42, "KEYCODE_N");
      var0.c(43, "KEYCODE_O");
      var0.c(44, "KEYCODE_P");
      var0.c(45, "KEYCODE_Q");
      var0.c(46, "KEYCODE_R");
      var0.c(47, "KEYCODE_S");
      var0.c(48, "KEYCODE_T");
      var0.c(49, "KEYCODE_U");
      var0.c(50, "KEYCODE_V");
      var0.c(51, "KEYCODE_W");
      var0.c(52, "KEYCODE_X");
      var0.c(53, "KEYCODE_Y");
      var0.c(54, "KEYCODE_Z");
      var0.c(55, "KEYCODE_COMMA");
      var0.c(56, "KEYCODE_PERIOD");
      var0.c(57, "KEYCODE_ALT_LEFT");
      var0.c(58, "KEYCODE_ALT_RIGHT");
      var0.c(59, "KEYCODE_SHIFT_LEFT");
      var0.c(60, "KEYCODE_SHIFT_RIGHT");
      var0.c(61, "KEYCODE_TAB");
      var0.c(62, "KEYCODE_SPACE");
      var0.c(63, "KEYCODE_SYM");
      var0.c(64, "KEYCODE_EXPLORER");
      var0.c(65, "KEYCODE_ENVELOPE");
      var0.c(66, "KEYCODE_ENTER");
      var0.c(67, "KEYCODE_DEL");
      var0.c(68, "KEYCODE_GRAVE");
      var0.c(69, "KEYCODE_MINUS");
      var0.c(70, "KEYCODE_EQUALS");
      var0.c(71, "KEYCODE_LEFT_BRACKET");
      var0.c(72, "KEYCODE_RIGHT_BRACKET");
      var0.c(73, "KEYCODE_BACKSLASH");
      var0.c(74, "KEYCODE_SEMICOLON");
      var0.c(75, "KEYCODE_APOSTROPHE");
      var0.c(76, "KEYCODE_SLASH");
      var0.c(77, "KEYCODE_AT");
      var0.c(78, "KEYCODE_NUM");
      var0.c(79, "KEYCODE_HEADSETHOOK");
      var0.c(80, "KEYCODE_FOCUS");
      var0.c(81, "KEYCODE_PLUS");
      var0.c(82, "KEYCODE_MENU");
      var0.c(83, "KEYCODE_NOTIFICATION");
      var0.c(84, "KEYCODE_SEARCH");
      var0.c(85, "KEYCODE_MEDIA_PLAY_PAUSE");
      var0.c(86, "KEYCODE_MEDIA_STOP");
      var0.c(87, "KEYCODE_MEDIA_NEXT");
      var0.c(88, "KEYCODE_MEDIA_PREVIOUS");
      var0.c(89, "KEYCODE_MEDIA_REWIND");
      var0.c(90, "KEYCODE_MEDIA_FAST_FORWARD");
      var0.c(91, "KEYCODE_MUTE");
      var0.c(92, "KEYCODE_PAGE_UP");
      var0.c(93, "KEYCODE_PAGE_DOWN");
      var0.c(94, "KEYCODE_PICTSYMBOLS");
      var0.c(95, "KEYCODE_SWITCH_CHARSET");
      var0.c(96, "KEYCODE_BUTTON_A");
      var0.c(97, "KEYCODE_BUTTON_B");
      var0.c(98, "KEYCODE_BUTTON_C");
      var0.c(99, "KEYCODE_BUTTON_X");
      var0.c(100, "KEYCODE_BUTTON_Y");
      var0.c(101, "KEYCODE_BUTTON_Z");
      var0.c(102, "KEYCODE_BUTTON_L1");
      var0.c(103, "KEYCODE_BUTTON_R1");
      var0.c(104, "KEYCODE_BUTTON_L2");
      var0.c(105, "KEYCODE_BUTTON_R2");
      var0.c(106, "KEYCODE_BUTTON_THUMBL");
      var0.c(107, "KEYCODE_BUTTON_THUMBR");
      var0.c(108, "KEYCODE_BUTTON_START");
      var0.c(109, "KEYCODE_BUTTON_SELECT");
      var0.c(110, "KEYCODE_BUTTON_MODE");
      var0.c(111, "KEYCODE_ESCAPE");
      var0.c(112, "KEYCODE_FORWARD_DEL");
      var0.c(113, "KEYCODE_CTRL_LEFT");
      var0.c(114, "KEYCODE_CTRL_RIGHT");
      var0.c(115, "KEYCODE_CAPS_LOCK");
      var0.c(116, "KEYCODE_SCROLL_LOCK");
      var0.c(117, "KEYCODE_META_LEFT");
      var0.c(118, "KEYCODE_META_RIGHT");
      var0.c(119, "KEYCODE_FUNCTION");
      var0.c(120, "KEYCODE_SYSRQ");
      var0.c(121, "KEYCODE_BREAK");
      var0.c(122, "KEYCODE_MOVE_HOME");
      var0.c(123, "KEYCODE_MOVE_END");
      var0.c(124, "KEYCODE_INSERT");
      var0.c(125, "KEYCODE_FORWARD");
      var0.c(126, "KEYCODE_MEDIA_PLAY");
      var0.c(127, "KEYCODE_MEDIA_PAUSE");
      var0.c(128, "KEYCODE_MEDIA_CLOSE");
      var0.c(129, "KEYCODE_MEDIA_EJECT");
      var0.c(130, "KEYCODE_MEDIA_RECORD");
      var0.c(131, "KEYCODE_F1");
      var0.c(132, "KEYCODE_F2");
      var0.c(133, "KEYCODE_F3");
      var0.c(134, "KEYCODE_F4");
      var0.c(135, "KEYCODE_F5");
      var0.c(136, "KEYCODE_F6");
      var0.c(137, "KEYCODE_F7");
      var0.c(138, "KEYCODE_F8");
      var0.c(139, "KEYCODE_F9");
      var0.c(140, "KEYCODE_F10");
      var0.c(141, "KEYCODE_F11");
      var0.c(142, "KEYCODE_F12");
      var0.c(143, "KEYCODE_NUM_LOCK");
      var0.c(144, "KEYCODE_NUMPAD_0");
      var0.c(145, "KEYCODE_NUMPAD_1");
      var0.c(146, "KEYCODE_NUMPAD_2");
      var0.c(147, "KEYCODE_NUMPAD_3");
      var0.c(148, "KEYCODE_NUMPAD_4");
      var0.c(149, "KEYCODE_NUMPAD_5");
      var0.c(150, "KEYCODE_NUMPAD_6");
      var0.c(151, "KEYCODE_NUMPAD_7");
      var0.c(152, "KEYCODE_NUMPAD_8");
      var0.c(153, "KEYCODE_NUMPAD_9");
      var0.c(154, "KEYCODE_NUMPAD_DIVIDE");
      var0.c(155, "KEYCODE_NUMPAD_MULTIPLY");
      var0.c(156, "KEYCODE_NUMPAD_SUBTRACT");
      var0.c(157, "KEYCODE_NUMPAD_ADD");
      var0.c(158, "KEYCODE_NUMPAD_DOT");
      var0.c(159, "KEYCODE_NUMPAD_COMMA");
      var0.c(160, "KEYCODE_NUMPAD_ENTER");
      var0.c(161, "KEYCODE_NUMPAD_EQUALS");
      var0.c(162, "KEYCODE_NUMPAD_LEFT_PAREN");
      var0.c(163, "KEYCODE_NUMPAD_RIGHT_PAREN");
      var0.c(164, "KEYCODE_VOLUME_MUTE");
      var0.c(165, "KEYCODE_INFO");
      var0.c(166, "KEYCODE_CHANNEL_UP");
      var0.c(167, "KEYCODE_CHANNEL_DOWN");
      var0.c(168, "KEYCODE_ZOOM_IN");
      var0.c(169, "KEYCODE_ZOOM_OUT");
      var0.c(170, "KEYCODE_TV");
      var0.c(171, "KEYCODE_WINDOW");
      var0.c(172, "KEYCODE_GUIDE");
      var0.c(173, "KEYCODE_DVR");
      var0.c(174, "KEYCODE_BOOKMARK");
      var0.c(175, "KEYCODE_CAPTIONS");
      var0.c(176, "KEYCODE_SETTINGS");
      var0.c(177, "KEYCODE_TV_POWER");
      var0.c(178, "KEYCODE_TV_INPUT");
      var0.c(180, "KEYCODE_STB_INPUT");
      var0.c(179, "KEYCODE_STB_POWER");
      var0.c(181, "KEYCODE_AVR_POWER");
      var0.c(182, "KEYCODE_AVR_INPUT");
      var0.c(183, "KEYCODE_PROG_RED");
      var0.c(184, "KEYCODE_PROG_GREEN");
      var0.c(185, "KEYCODE_PROG_YELLOW");
      var0.c(186, "KEYCODE_PROG_BLUE");
      var0.c(187, "KEYCODE_APP_SWITCH");
      var0.c(188, "KEYCODE_BUTTON_1");
      var0.c(189, "KEYCODE_BUTTON_2");
      var0.c(190, "KEYCODE_BUTTON_3");
      var0.c(191, "KEYCODE_BUTTON_4");
      var0.c(192, "KEYCODE_BUTTON_5");
      var0.c(193, "KEYCODE_BUTTON_6");
      var0.c(194, "KEYCODE_BUTTON_7");
      var0.c(195, "KEYCODE_BUTTON_8");
      var0.c(196, "KEYCODE_BUTTON_9");
      var0.c(197, "KEYCODE_BUTTON_10");
      var0.c(198, "KEYCODE_BUTTON_11");
      var0.c(199, "KEYCODE_BUTTON_12");
      var0.c(200, "KEYCODE_BUTTON_13");
      var0.c(201, "KEYCODE_BUTTON_14");
      var0.c(202, "KEYCODE_BUTTON_15");
      var0.c(203, "KEYCODE_BUTTON_16");
      var0.c(204, "KEYCODE_LANGUAGE_SWITCH");
      var0.c(205, "KEYCODE_MANNER_MODE");
      var0.c(206, "KEYCODE_3D_MODE");
      var0.c(207, "KEYCODE_CONTACTS");
      var0.c(208, "KEYCODE_CALENDAR");
      var0.c(209, "KEYCODE_MUSIC");
      var0.c(210, "KEYCODE_CALCULATOR");
      var0.c(211, "KEYCODE_ZENKAKU_HANKAKU");
      var0.c(212, "KEYCODE_EISU");
      var0.c(213, "KEYCODE_MUHENKAN");
      var0.c(214, "KEYCODE_HENKAN");
      var0.c(215, "KEYCODE_KATAKANA_HIRAGANA");
      var0.c(216, "KEYCODE_YEN");
      var0.c(217, "KEYCODE_RO");
      var0.c(218, "KEYCODE_KANA");
      var0.c(219, "KEYCODE_ASSIST");
      var0.c(220, "KEYCODE_BRIGHTNESS_DOWN");
      var0.c(221, "KEYCODE_BRIGHTNESS_UP");
      var0.c(222, "KEYCODE_MEDIA_AUDIO_TRACK");
   }

   public static int a() {
      return 222;
   }

   private KeyEvent() {}

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
      StringBuilder var1 = new StringBuilder();
      var1.append("KeyEvent { action=").append(a(this.h));
      var1.append(", keyCode=").append(b(this.i));
      var1.append(", scanCode=").append(this.j);
      if(this.o != null) {
         var1.append(", characters=\"").append(this.o).append("\"");
      }

      var1.append(", metaState=").append(c(this.g));
      var1.append(", flags=0x").append(Integer.toHexString(this.l));
      var1.append(", repeatCount=").append(this.k);
      var1.append(", eventTime=").append(this.n);
      var1.append(", downTime=").append(this.m);
      var1.append(", deviceId=").append(this.e);
      var1.append(", source=0x").append(Integer.toHexString(this.f));
      var1.append(" }");
      return var1.toString();
   }

   public static String a(int var0) {
      switch(var0) {
      case 0:
         return "ACTION_DOWN";
      case 1:
         return "ACTION_UP";
      case 2:
         return "ACTION_MULTIPLE";
      default:
         return Integer.toString(var0);
      }
   }

   public static String b(int var0) {
      String var1 = (String)b.a(var0);
      return var1 != null?var1:Integer.toString(var0);
   }

   public static String c(int var0) {
      if(var0 == 0) {
         return "0";
      } else {
         StringBuilder var1 = null;

         for(int var2 = 0; var0 != 0; ++var2) {
            boolean var3 = (var0 & 1) != 0;
            var0 >>>= 1;
            if(var3) {
               String var4 = c[var2];
               if(var1 == null) {
                  if(var0 == 0) {
                     return var4;
                  }

                  var1 = new StringBuilder(var4);
               } else {
                  var1.append('|');
                  var1.append(var4);
               }
            }
         }

         return var1.toString();
      }
   }

   public static KeyEvent a(Parcel var0) {
      return new KeyEvent(var0);
   }

   private KeyEvent(Parcel var1) {
      this.e = var1.readInt();
      this.f = var1.readInt();
      this.h = var1.readInt();
      this.i = var1.readInt();
      this.k = var1.readInt();
      this.g = var1.readInt();
      this.j = var1.readInt();
      this.l = var1.readInt();
      this.m = var1.readLong();
      this.n = var1.readLong();
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeInt(this.e);
      var1.writeInt(this.f);
      var1.writeInt(this.h);
      var1.writeInt(this.i);
      var1.writeInt(this.k);
      var1.writeInt(this.g);
      var1.writeInt(this.j);
      var1.writeInt(this.l);
      var1.writeLong(this.m);
      var1.writeLong(this.n);
   }

   static {
      b();
      a = new KeyEvent$1();
   }
}
