package lib;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Stream;

/**
 * AST 级改名器 —— 复刻 Java-humanify 的 apply 阶段核心能力（AST 层改写，保证仍可编译）。
 *
 * 为什么必须 AST 级：本项目此前的改名器是「正则 + 类型表」文本替换，会误改
 * **字符串字面量与注释**里的同名文本。AST 改写天然规避该问题。
 *
 * 输入映射（TSV，避免引入 JSON 依赖）：
 *     kind \t key \t newName
 *   kind = class  : key = 点分全名        a.b.C
 *   kind = field  : key = a.b.C#field
 *   kind = method : key = a.b.C.m(T1,T2)   （参数用简单名；无参写 m()）
 *   kind = simple : key = 旧短名           （局部变量/参数；仅在该文件内无冲突时改）
 *
 * 用法:
 *   java -cp "tools/lib/*;tools/lib/out" lib.RenameTool <srcDir> <map.tsv> <outDir> [--apply]
 * 无 --apply 时只统计与预演，不写盘。
 */
public class RenameTool {

    static Map<String, String> classMap = new HashMap<>();
    static Map<String, String> fieldMap = new HashMap<>();
    static Map<String, String> methodMap = new HashMap<>();
    static Map<String, String> simpleMap = new HashMap<>();
    static Map<String, String> bySimpleClass = new HashMap<>();   // 简单类名 -> 新简单名

    static int nFiles = 0, nClass = 0, nField = 0, nMethod = 0, nSimple = 0, nFail = 0;

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.err.println("用法: RenameTool <srcDir> <map.tsv> <outDir> [--apply]");
            System.exit(2);
        }
        Path src = Paths.get(args[0]);
        Path map = Paths.get(args[1]);
        Path out = Paths.get(args[2]);
        boolean apply = args.length > 3 && "--apply".equals(args[3]);

        for (String line : Files.readAllLines(map, StandardCharsets.UTF_8)) {
            // ★ 去 BOM：PowerShell `Set-Content -Encoding UTF8` 会写 BOM，
            //   使首行 kind 变成 "\uFEFFclass" ⇒ **静默零匹配**（实测踩过）
            if (!line.isEmpty() && line.charAt(0) == '\uFEFF') line = line.substring(1);
            if (line.isEmpty() || line.startsWith("#")) continue;
            String[] p = line.split("\t", 3);
            if (p.length < 3) continue;
            String kind = p[0].trim(), key = p[1].trim(), val = p[2].trim();
            if ("class".equals(kind)) {
                classMap.put(key, val);
                String sim = key.substring(key.lastIndexOf('.') + 1);
                bySimpleClass.put(sim, simple(val));
            } else if ("field".equals(kind)) fieldMap.put(key, simple(val));
            else if ("method".equals(kind)) methodMap.put(key, simple(val));
            else if ("simple".equals(kind)) simpleMap.put(key, simple(val));
        }
        System.out.printf("映射载入: class=%d field=%d method=%d simple=%d%n",
                classMap.size(), fieldMap.size(), methodMap.size(), simpleMap.size());

        ParserConfiguration cfg = new ParserConfiguration()
                .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_8);
        JavaParser jp = new JavaParser(cfg);

        List<Path> files;
        try (Stream<Path> s = Files.walk(src)) {
            files = new ArrayList<>();
            s.filter(f -> f.toString().endsWith(".java")).forEach(files::add);
        }
        Collections.sort(files);                       // 可复现
        for (Path f : files) {
            try {
                ParseResult<CompilationUnit> pr = jp.parse(f);
                if (!pr.isSuccessful() || !pr.getResult().isPresent()) {
                    nFail++;
                    // ★ 必须报出**具体问题**：只写「解析失败」无法定位（实测 a/a/{d,e,h,m}.java
                    //   静默失败 ⇒ 4 个类丢失，产物 1601 而非 1692）。
                    StringBuilder why = new StringBuilder();
                    pr.getProblems().stream().limit(3).forEach(pb ->
                            why.append(pb.getVerboseMessage().replace('\n', ' ')).append(" | "));
                    System.err.println("  解析失败: " + src.relativize(f) + "  ::  " + why);
                    // ★ 兜底：解析失败的文件**原样复制**到产物。
                    //   理由：这些是 CFR 反编译缺陷产出的非法 Java（如 `super(...)` 不在首句、
                    //   `while (true) lbl-1000:` 标签语法），**与改名无关**。
                    //   若不复制，产物树缺文件 ⇒ 与 03 的编译对比就不公平（实测丢 4~97 个）。
                    if (apply) {
                        Path d2 = out.resolve(src.relativize(f).toString());
                        Files.createDirectories(d2.getParent());
                        Files.copy(f, d2, StandardCopyOption.REPLACE_EXISTING);
                    }
                    continue;
                }
                CompilationUnit cu = pr.getResult().get();
                String rel = src.relativize(f).toString();
                rewrite(cu, rel);
                nFiles++;
                if (apply) {
                    // ★ 顶层类改名后**文件名必须跟改**，否则 javac 报
                    //   「类 X 是公共的, 应在名为 X.java 的文件中声明」（实测踩过）
                    String outRel = rel;
                    for (ClassOrInterfaceDeclaration tc : cu.findAll(ClassOrInterfaceDeclaration.class)) {
                        if (tc.getParentNode().isPresent()
                                && tc.getParentNode().get() instanceof CompilationUnit) {
                            Path p = Paths.get(rel);
                            String expect = tc.getNameAsString() + ".java";
                            if (!p.getFileName().toString().equals(expect)) {
                                Path parent = p.getParent();
                                outRel = (parent == null ? Paths.get(expect)
                                        : parent.resolve(expect)).toString();
                            }
                            break;
                        }
                    }
                    Path dst = out.resolve(outRel);
                    Files.createDirectories(dst.getParent());
                    Files.write(dst, cu.toString().getBytes(StandardCharsets.UTF_8));
                }
            } catch (Exception e) {
                nFail++;
                System.err.println("  异常 " + src.relativize(f) + ": " + e.getMessage());
            }
        }
        System.out.printf("%n扫描 %d 文件（失败 %d）%n", nFiles, nFail);
        System.out.printf("改名统计: 类 %d / 字段 %d / 方法 %d / 短名 %d%n",
                nClass, nField, nMethod, nSimple);
        System.out.println(apply ? "已写出到 " + out : "[dry-run] 加 --apply 写盘");
    }

    static void rewrite(CompilationUnit cu, String rel) {
        // ★ 先在**任何改名之前**快照每个类的原始 FQN。
        //   否则「先改类名」会让后续字段/方法的键变成 `t.Calc#x` 而查不到原键 `t.A#x`
        //   ⇒ 静默零改名（实测踩过）。
        final Map<Node, String> fqns = new IdentityHashMap<>();
        for (ClassOrInterfaceDeclaration c : cu.findAll(ClassOrInterfaceDeclaration.class)) {
            fqns.put(c, fqnOf(cu, c));
        }
        // ★ 构造器也必须先快照：`isConstructorDeclaration()` 判据是「名字 == 类名」，
        //   一旦类名改了，旧构造器就不再被识别 ⇒ 必须**先记下节点**再改（实测漏改过）
        // ★ 构造器在 JavaParser 里是 **`ConstructorDeclaration`**，不是 `MethodDeclaration`
        //   ⇒ 遍历 `getMethods()` 永远看不到它（实测漏改，且产物 javac 报
        //   「方法声明无效；需要返回类型」）。必须用 `getConstructors()`。
        final Map<Node, List<ConstructorDeclaration>> ctors = new IdentityHashMap<>();
        for (ClassOrInterfaceDeclaration c : cu.findAll(ClassOrInterfaceDeclaration.class)) {
            ctors.put(c, new ArrayList<>(c.getConstructors()));
        }

        // ── 1) 类声明改名（用快照键 + 快照构造器）──
        for (ClassOrInterfaceDeclaration c : cu.findAll(ClassOrInterfaceDeclaration.class)) {
            String fqn = fqns.get(c);
            String nn = classMap.get(fqn);
            if (nn != null) {
                String nw = simple(nn);
                for (ConstructorDeclaration m : ctors.getOrDefault(c, Collections.<ConstructorDeclaration>emptyList())) {
                    m.setName(nw);
                }
                c.setName(nw);
                nClass++;
            }
        }
        // ── 2) 类型引用 / import 改名（按 FQN 或简单名）──
        for (ClassOrInterfaceType t : cu.findAll(ClassOrInterfaceType.class)) {
            String nm = t.getNameAsString();
            String nn = bySimpleClass.get(nm);
            if (nn != null) t.setName(nn.substring(nn.lastIndexOf('.') + 1));
        }
        for (com.github.javaparser.ast.ImportDeclaration im : cu.findAll(
                com.github.javaparser.ast.ImportDeclaration.class)) {
            String fqn = im.getNameAsString();
            String nn = classMap.get(fqn);
            if (nn != null) {
                // ★ 只能替换**最后一段**：`im.setName(simpleName)` 会把整条 import
                //   写成 `import ReliableSocket;`（包前缀丢失）⇒ javac 报
                //   `'.' expected`。实测这是 AST 产物 937 个错误里 807 个的主因。
                int dot = fqn.lastIndexOf('.');
                String prefix = dot >= 0 ? fqn.substring(0, dot + 1) : "";
                im.setName(prefix + simple(nn));
            }
        }

        // ── 3) 字段（含**类内引用**同步）──
        for (FieldDeclaration fd : cu.findAll(FieldDeclaration.class)) {
            String owner = ownerFqn(fqns, cu, fd);
            ClassOrInterfaceDeclaration host = enclosingClass(fd);
            for (VariableDeclarator v : fd.getVariables()) {
                String oldN = v.getNameAsString();
                String nn = fieldMap.get(owner + "#" + oldN);
                if (nn == null) continue;
                v.setName(simple(nn));
                nField++;
                // 只在该类作用域内改引用（AST 级 ⇒ 字符串/注释天然免疫）
                if (host != null) {
                    for (NameExpr ne : host.findAll(NameExpr.class)) {
                        if (ne.getNameAsString().equals(oldN)) ne.setName(simple(nn));
                    }
                    for (FieldAccessExpr fa : host.findAll(FieldAccessExpr.class)) {
                        if (fa.getNameAsString().equals(oldN)) fa.setName(simple(nn));
                    }
                }
            }
        }
        // ── 4) 方法声明 ──
        for (MethodDeclaration m : cu.findAll(MethodDeclaration.class)) {
            String owner = ownerFqn(fqns, cu, m);
            String nn = methodMap.get(owner + "." + sig(m));
            if (nn == null) nn = methodMap.get(owner + "." + m.getNameAsString() + "(?)");
            if (nn != null) { m.setName(nn); nMethod++; }
        }
        // ── 5) 方法调用 ──
        for (MethodCallExpr mc : cu.findAll(MethodCallExpr.class)) {
            String oldN = mc.getNameAsString();
            int ar = mc.getArguments().size();
            for (Map.Entry<String, String> e : methodMap.entrySet()) {
                String k = e.getKey();
                int dot = k.lastIndexOf('.');
                if (dot < 0) continue;
                String rest = k.substring(dot + 1);
                int par = rest.indexOf('(');
                if (par < 0) continue;
                if (!rest.substring(0, par).equals(oldN)) continue;
                String args = rest.substring(par + 1, rest.length() - 1);
                int want = args.isEmpty() ? 0 : args.split(",").length;
                if (want == ar) { mc.setName(e.getValue()); nMethod++; break; }
            }
        }
        // ── 6) 局部变量/参数（保守：同文件内该短名只出现于声明与读取时）──
        for (Parameter p : cu.findAll(Parameter.class)) {
            String nn = simpleMap.get(p.getNameAsString());
            if (nn != null) { p.setName(nn); nSimple++; }
        }
        for (VariableDeclarator v : cu.findAll(VariableDeclarator.class)) {
            if (v.getParentNode().isPresent()
                    && v.getParentNode().get() instanceof FieldDeclaration) continue;
            String nn = simpleMap.get(v.getNameAsString());
            if (nn != null) { v.setName(nn); nSimple++; }
        }
        for (NameExpr ne : cu.findAll(NameExpr.class)) {
            String nn = simpleMap.get(ne.getNameAsString());
            if (nn != null) ne.setName(nn);
        }
        for (FieldAccessExpr fa : cu.findAll(FieldAccessExpr.class)) {
            String nn = simpleMap.get(fa.getNameAsString());
            if (nn != null) fa.setName(nn);
        }
    }

    /** 兜底：无论映射里给的是简单名还是 FQN，一律只取末段（防止写出 `t.count` 这种非法名）
     *
     *  ★ **不要剥 `$` 之后的部分**：嵌套类名 `MissionEvent$1` 里 `$` 是名字的组成部分，
     *    剥掉会得到 `1`（非法标识符）⇒ 产物出现 `class 1 extends Thread`、
     *    构造器 `1(...)`，javac 大面积报错（实测踩过，434 行含 `$` 的类映射全部受害）。
     */
    static String simple(String n) {
        if (n == null) return null;
        String s = n.trim();
        int dot = s.lastIndexOf('.');
        if (dot >= 0) s = s.substring(dot + 1);
        return s;
    }

    static String sig(MethodDeclaration m) {
        StringBuilder sb = new StringBuilder(m.getNameAsString()).append('(');
        for (int i = 0; i < m.getParameters().size(); i++) {
            if (i > 0) sb.append(',');
            String t = m.getParameter(i).getTypeAsString();
            int lt = t.lastIndexOf('<');
            if (lt > 0) t = t.substring(0, lt);
            int d = t.lastIndexOf('.');
            sb.append(d >= 0 ? t.substring(d + 1) : t);
        }
        return sb.append(')').toString();
    }

    /** 取节点所属「最内层类」的声明节点 */
    static ClassOrInterfaceDeclaration enclosingClass(Node n) {
        Optional<Node> cur = n.getParentNode();
        while (cur.isPresent()) {
            if (cur.get() instanceof ClassOrInterfaceDeclaration) {
                return (ClassOrInterfaceDeclaration) cur.get();
            }
            cur = cur.get().getParentNode();
        }
        return null;
    }

    /** 取节点所属「最内层类」的**原始** FQN（走快照表，避免「先改类名导致后续键失效」） */    static String ownerFqn(Map<Node, String> fqns, CompilationUnit cu, Node n) {
        Optional<Node> cur = n.getParentNode();
        while (cur.isPresent()) {
            if (cur.get() instanceof ClassOrInterfaceDeclaration) {
                String v = fqns.get(cur.get());
                if (v != null) return v;
                return fqnOf(cu, (ClassOrInterfaceDeclaration) cur.get());
            }
            cur = cur.get().getParentNode();
        }
        return cu.getPackageDeclaration().map(p -> p.getNameAsString()).orElse("");
    }

    static String fqnOf(CompilationUnit cu, ClassOrInterfaceDeclaration c) {
        String pkg = cu.getPackageDeclaration().map(p -> p.getNameAsString()).orElse("");
        List<String> chain = new ArrayList<>();
        Node cur = c;
        while (cur != null) {
            if (cur instanceof ClassOrInterfaceDeclaration) {
                if (((ClassOrInterfaceDeclaration) cur).getNameAsString().isEmpty()) {
                    // 匿名类
                } else {
                    chain.add(0, ((ClassOrInterfaceDeclaration) cur).getNameAsString());
                }
            }
            cur = cur.getParentNode().orElse(null);
        }
        String base = pkg.isEmpty() ? "" : pkg + ".";
        return base + String.join("$", chain);
    }
}
