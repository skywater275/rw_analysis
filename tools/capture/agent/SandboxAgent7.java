import java.lang.instrument.Instrumentation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

/** v19.113c 沙盒启动 agent v7 — 完整序列任务经 RunnableAction 投递游戏线程. */
public class SandboxAgent7 {
    public static void agentmain(String args, Instrumentation inst) {
        try {
            String map = (args != null && !args.trim().isEmpty())
                    ? args.trim() : "maps/skirmish/[p2]Beach landing (2p) [by hxyy].tmx";
            Class<?> libA = Class.forName("com.corrodinggames.librocket.a");
            Object gui = libA.getMethod("a").invoke(null);
            Object libB = gui.getClass().getField("b").get(gui);
            Object scriptEngine = libB.getClass().getField("c").get(libB);
            Field qf = null;
            for (Field f : scriptEngine.getClass().getDeclaredFields()) {
                if (List.class.isAssignableFrom(f.getType())) { qf = f; break; }
            }
            if (qf == null) throw new RuntimeException("未找到脚本队列字段");
            qf.setAccessible(true);
            @SuppressWarnings("unchecked")
            List<Object> queue = (List<Object>) qf.get(scriptEngine);
            SandboxTask2 task = new SandboxTask2();
            task.map = map;
            task.gui = gui;
            task.libB = libB;
            Class<?> raCls = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine$RunnableAction");
            Constructor<?> ctor = raCls.getDeclaredConstructor(Runnable.class);
            ctor.setAccessible(true);
            queue.add(ctor.newInstance(task));
            System.out.println("[SA7] 已投递完整沙盒任务到游戏线程队列");
        } catch (Throwable t) {
            System.out.println("[SA7] attach 失败:");
            t.printStackTrace();
        }
    }
}
