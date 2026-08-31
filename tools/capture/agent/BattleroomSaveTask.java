import java.lang.reflect.Method;

/** battleroom 态存档 NPE 复现 — back() 退出对局后存档 (完整栈捕获). */
public class BattleroomSaveTask implements Runnable {
    public void run() {
        try {
            Class<?> libA = Class.forName("com.corrodinggames.librocket.a");
            Object gui = libA.getMethod("a").invoke(null);
            Object libB = gui.getClass().getField("b").get(gui);
            Object se = libB.getClass().getField("c").get(libB);
            Object root = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine")
                    .getMethod("getRoot").invoke(se);
            // 1. 退出对局
            try {
                root.getClass().getMethod("back").invoke(root);
                System.out.println("[BRS] back() 已调用");
            } catch (Throwable t) {
                System.out.println("[BRS] back 异常:"); t.printStackTrace(System.out);
            }
            // 2. 等 UI 过渡 (游戏线程 sleep 调试可接受)
            Thread.sleep(3000);
            try {
                Object doc = root.getClass().getMethod("getCurrentDocumentPath").invoke(root);
                System.out.println("[BRS] 当前文档: " + doc);
            } catch (Throwable t) {
                System.out.println("[BRS] 文档查询异常:"); t.printStackTrace(System.out);
            }
            // 3. 存档 (完整链 Root.saveGame)
            try {
                root.getClass().getMethod("saveGame", String.class).invoke(root, "broomtest");
                System.out.println("[BRS] battleroom 存档成功 (无异常)");
            } catch (Throwable t) {
                System.out.println("[BRS] battleroom 存档异常 — 完整栈:");
                t.printStackTrace(System.out);
            }
        } catch (Throwable t) {
            System.out.println("[BRS] 任务失败:"); t.printStackTrace(System.out);
        }
    }
}
