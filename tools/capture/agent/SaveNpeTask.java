import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 对局态存档 NPE 定位任务 — 游戏线程 try-catch 捕获完整栈 (绕过日志系统吞栈). */
public class SaveNpeTask implements Runnable {
    public void run() {
        try {
            // 1. 打开纯文本存档开关 (GameSaver.a 静态 boolean — 02b y.java:23 铁证, 重启后复位)
            Class<?> saverCls = Class.forName("com.corrodinggames.rts.gameFramework.y");
            Field pf = saverCls.getDeclaredField("a");
            pf.setAccessible(true);
            pf.setBoolean(null, true);
            System.out.println("[SNP] plainTextDebugSave 已开启");
            // 2. l.B() → GlobalState, ca → GameSaver, b(name,false) → saveGame
            Class<?> lCls = Class.forName("com.corrodinggames.rts.gameFramework.l");
            Object gs = lCls.getMethod("B").invoke(null);
            Object saver = gs.getClass().getField("ca").get(gs);
            Method save = null;
            for (Method m : saver.getClass().getDeclaredMethods()) {
                if (m.getName().equals("b") && m.getParameterTypes().length == 2
                        && m.getParameterTypes()[0] == String.class && m.getParameterTypes()[1] == boolean.class) {
                    save = m; break;
                }
            }
            System.out.println("[SNP] 调用存档: " + saver.getClass().getName() + "." + (save == null ? "?" : save.getName()));
            try {
                save.invoke(saver, "npeloc", false);
                System.out.println("[SNP] 存档成功 (无异常)");
            } catch (Throwable t) {
                System.out.println("[SNP] 存档异常 — 完整栈:");
                t.printStackTrace(System.out);
                System.out.println("[SNP] 根因链: " + rootChain(t));
            }
        } catch (Throwable t) {
            System.out.println("[SNP] 任务失败:");
            t.printStackTrace(System.out);
        }
    }

    static String rootChain(Throwable t) {
        StringBuilder sb = new StringBuilder();
        while (t != null) {
            sb.append(t.getClass().getName()).append(": ").append(t.getMessage());
            t = t.getCause();
            if (t != null) sb.append(" <- ");
        }
        return sb.toString();
    }
}
