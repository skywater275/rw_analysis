# P5.1 Call-Site 工具开发计划

> ⚠️ Phase B 阶段历史文档 — 当前 Phase B 状态见 [PLAN.md](../deobfuscation/PLAN.md) Phase B 章节

## 问题
sig_renamer.py 重命名方法声明但不更新调用点, 导致257错误。
根因:
1. 正则替换产生语法损坏 (`getstrictfp void`)
2. 只处理 `this.method(` 不处理其他对象的 `.method(`
3. 跨文件调用点未更新

## 方案: 声明-调用联合重命名

### 步骤1: 声明重命名 (sig_renamer当前逻辑, 需修复regex)
- 只重命名方法声明行
- 避免匹配注释/字符串中的方法名
- 输出 rename-audit.csv: {file, class, old_name, new_name, params}

### 步骤2: 同文件调用点更新
- 在声明所在的同一文件中, 替换 `this.oldName(` 和 `oldName(` 调用

### 步骤3: 跨文件调用点更新 (新增)
- 读取 rename-audit.csv
- 对每个重命名: 找到导入该类的所有文件
- 在这些文件中替换 `.oldName(` 引用
- 需要类型推断: `obj.method(` 中 obj 的类型

### 简化方案 (Phase 1)
1. 只重命名单重载方法 (1个类中只有1个 `a()`)
2. 在声明文件中: 替换声明 + `this.a(` + 裸 `a(`
3. 在其他文件中: 根据import关系全局搜索替换 `.a(`
4. 每批10个方法后 javac_gate 验证

## 优先级
P5.1 方法重命名需要此工具完成。当前状态: 5033单字符方法/719文件待处理。
