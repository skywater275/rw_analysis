# MapPingAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\MapPingAction.java`
**方法总数**: 5

---

## `MapPingAction` — public MapPingAction()

- **行号**: 13-15 (3 行)
- **返回**: `public`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"c__cut_ping"`

### 方法体 (前 10 行)
```java
        super("c__cut_ping");
    }

    @Override
    public String b() {
        return "Map Ping";
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
- `"Map Ping"`

### 方法体 (前 10 行)
```java
        return "Map Ping";
    }

    @Override
    public String a() {
        return "Send a map ping to your allies";
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
- `"Send a map ping to your allies"`

### 方法体 (前 10 行)
```java
        return "Send a map ping to your allies";
    }

    @Override
    public boolean c(UnitInstance am2, boolean bl) {
        l l2 = l.B();
        l2.bS.I();
        return true;
    }

```

---

## `c` — boolean c(UnitInstance am2, boolean bl)

- **行号**: 28-32 (5 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `bS.I()`
- `l.B()`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        l2.bS.I();
        return true;
    }

    @Override
    public ad M() {
        l l2 = l.B();
        return l2.bT.v;
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
        return l2.bT.v;
    }
}

```

---
