import java.lang.instrument.Instrumentation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

/** 存档 NPE 定位 agent — 游戏线程投递 try-catch 存档任务 (完整栈捕获). */
public class PlaceAgent24 {
    public static void agentmain(String args, Instrumentation inst) {
        try {
            Class<?> libA = Class.forName("com.corrodinggames.librocket.a");
            Object gui = libA.getMethod("a").invoke(null);
            Object libB = gui.getClass().getField("b").get(gui);
            Object se = libB.getClass().getField("c").get(libB);
            Field qf = null;
            for (Field f : se.getClass().getDeclaredFields()) {
                if (List.class.isAssignableFrom(f.getType())) { qf = f; break; }
            }
            qf.setAccessible(true);
            @SuppressWarnings("unchecked")
            List<Object> q = (List<Object>) qf.get(se);
            Class<?> ra = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine$RunnableAction");
            Constructor<?> c = ra.getDeclaredConstructor(Runnable.class);
            c.setAccessible(true);
            q.add(c.newInstance(new PlaceTask3()));
            System.out.println("[IND18] 存档任务已投递");
        } catch (Throwable t) { t.printStackTrace(); }
    }
}
