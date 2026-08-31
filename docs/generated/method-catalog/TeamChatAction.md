# TeamChatAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\TeamChatAction.java`
**方法总数**: 5

---

## `TeamChatAction` — public TeamChatAction()

- **行号**: 13-15 (3 行)
- **返回**: `public`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"c__cut_chat"`

### 方法体 (前 10 行)
```java
        super("c__cut_chat");
    }

    @Override
    public String b() {
        return "Team Chat";
    }

    @Override
    public String a() {
```

---

## `b` — String b()

- **行号**: 18-20 (3 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"Team Chat"`

### 方法体 (前 10 行)
```java
        return "Team Chat";
    }

    @Override
    public String a() {
        return "Send a team chat message to your allies";
    }

    @Override
    public boolean c(UnitInstance am2, boolean bl) {
```

---

## `a` — String a()

- **行号**: 23-25 (3 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"Send a team chat message to your allies"`

### 方法体 (前 10 行)
```java
        return "Send a team chat message to your allies";
    }

    @Override
    public boolean c(UnitInstance am2, boolean bl) {
        l l2 = l.B();
        l2.bS.g.n();
        return true;
    }

```

---

## `c` — boolean c(UnitInstance am2, boolean bl)

- **行号**: 28-32 (5 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `g.n()`
- `l.B()`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        l2.bS.g.n();
        return true;
    }

    @Override
    public ad M() {
        l l2 = l.B();
        return l2.bT.u;
    }
```

---

## `M` — ad M()

- **行号**: 35-38 (4 行)
- **返回**: `ad`
- **参数**: ``

### 调用的方法
- `l.B()`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        return l2.bT.u;
    }
}

```

---
