import java.lang.reflect.Method;

/** hostStart 后 battleroom 态立即存档 — NPE 复现 (完整栈捕获). */
public class HostStartSaveTask implements Runnable {
    public void run() {
        try {
            Class<?> libA = Class.forName("com.corrodinggames.librocket.a");
            Object gui = libA.getMethod("a").invoke(null);
            Object libB = gui.getClass().getField("b").get(gui);
            Object se = libB.getClass().getField("c").get(libB);
            Object root = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine")
                    .getMethod("getRoot").invoke(se);
            // 1. 回主菜单
            try {
                root.getClass().getMethod("showMainMenu").invoke(root);
                System.out.println("[HSS] showMainMenu OK");
            } catch (Throwable t) { System.out.println("[HSS] showMainMenu 异常:"); t.printStackTrace(System.out); }
            Thread.sleep(1500);
            // 2. 开局 → battleroom
            try {
                root.getClass().getMethod("hostStart", boolean.class).invoke(root, false);
                System.out.println("[HSS] hostStart(false) OK");
            } catch (Throwable t) { System.out.println("[HSS] hostStart 异常:"); t.printStackTrace(System.out); }
            Thread.sleep(2000);
            try {
                Object doc = root.getClass().getMethod("getCurrentDocumentPath").invoke(root);
                System.out.println("[HSS] 当前文档: " + doc);
            } catch (Throwable t) { System.out.println("[HSS] 文档查询异常:"); t.printStackTrace(System.out); }
            // 3. battleroom 态存档
            try {
                root.getClass().getMethod("saveGame", String.class).invoke(root, "broomtest2");
                System.out.println("[HSS] battleroom 存档成功 (无异常)");
            } catch (Throwable t) {
                System.out.println("[HSS] battleroom 存档异常 — 完整栈:");
                t.printStackTrace(System.out);
            }
        } catch (Throwable t) {
            System.out.println("[HSS] 任务失败:"); t.printStackTrace(System.out);
        }
    }
}
