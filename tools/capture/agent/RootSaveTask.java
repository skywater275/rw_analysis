import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 完整模拟 Root.saveGame 链路分步定位 — closePopup/hideKeyboard/GameSaver.b 三步独立 try-catch. */
public class RootSaveTask implements Runnable {
    public void run() {
        try {
            Class<?> seCls = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine");
            Class<?> libA = Class.forName("com.corrodinggames.librocket.a");
            Object gui = libA.getMethod("a").invoke(null);
            Object libB = gui.getClass().getField("b").get(gui);
            Object se = libB.getClass().getField("c").get(libB);
            Object root = seCls.getMethod("getRoot").invoke(se);
            System.out.println("[RTS] root=" + root.getClass().getName());

            // 步1: closePopup() → libRocket.h()
            try {
                Object r = root.getClass().getMethod("closePopup").invoke(root);
                System.out.println("[RTS] closePopup 返回 " + r);
            } catch (Throwable t) {
                System.out.println("[RTS] closePopup 异常:");
                t.printStackTrace(System.out);
            }
            // 步2: hideKeyboard()
            try {
                root.getClass().getMethod("hideKeyboard").invoke(root);
                System.out.println("[RTS] hideKeyboard OK");
            } catch (Throwable t) {
                System.out.println("[RTS] hideKeyboard 异常:");
                t.printStackTrace(System.out);
            }
            // 步3: saveGame 本体 (Root.saveGame 全链等价)
            try {
                root.getClass().getMethod("saveGame", String.class).invoke(root, "rtstest");
                System.out.println("[RTS] Root.saveGame 成功 (无异常)");
            } catch (Throwable t) {
                System.out.println("[RTS] Root.saveGame 异常 — 完整栈:");
                t.printStackTrace(System.out);
            }
        } catch (Throwable t) {
            System.out.println("[RTS] 任务失败:");
            t.printStackTrace(System.out);
        }
    }
}
