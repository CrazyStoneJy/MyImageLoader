## 图像加载框架
使用AI来配合学习，不断优化一个可用的Android图片加载框架。

# ImageLoader

一个简洁高效的 Android 图片加载库，支持内存缓存、磁盘缓存、网络图片获取与解码，适合学习和二次开发。

## 目录结构

```
imageLoader/
├── build.gradle.kts           // Gradle 构建脚本
├── proguard-rules.pro         // ProGuard 混淆规则
├── consumer-rules.pro         // 消费者 ProGuard 规则
├── sampledata/                // 示例数据目录（当前为空）
└── src/
    ├── main/
    │   ├── AndroidManifest.xml
    │   └── java/me/crazystone/study/imageloader/
    │       ├── ImageLoader.kt         // 图片加载核心入口
    │       ├── ImageViewExt.kt        // ImageView 扩展方法
    │       ├── ContextExt.kt          // Context 扩展方法
    │       ├── cache/                 // 缓存相关实现
    │       │   ├── DiskCache.kt       // 磁盘缓存
    │       │   ├── MemoryCache.kt
    │       │   ├── LruMemoryCache.kt
    │       │   └── SimpleDiskCache.kt
    │       ├── fetch/                 // 网络图片获取
    │       │   ├── NetworkFetcher.kt
    │       │   └── OkHttpFetcher.kt
    │       ├── decode/                // 图片解码
    │       │   └── BitmapDecoder.kt
    │       └── model/                 // 数据模型
    │           ├── ImageRequest.kt
    │           └── ImageResult.kt
    ├── androidTest/                   // 仪器化测试
    └── test/                          // 单元测试
```

## 主要功能模块

- **ImageLoader**：图片加载核心，支持初始化、内存/磁盘缓存、网络拉取、解码。
- **缓存（cache）**：
  - `MemoryCache`/`LruMemoryCache`：基于 LRU 的内存缓存。
  - `DiskCache`/`SimpleDiskCache`：简单的文件磁盘缓存。
- **网络（fetch）**：
  - `NetworkFetcher`：网络获取接口。
  - `OkHttpFetcher`：基于 OkHttp 实现的图片下载。
- **解码（decode）**：
  - `BitmapDecoder`：将 InputStream 解码为 Bitmap。
- **扩展（ImageViewExt/ContextExt）**：
  - `ImageView.loadImage(url, ...)`：一行代码加载图片到 ImageView。
  - `Context.findLifecycleOwner()`：辅助查找生命周期。
- **数据模型（model）**：
  - `ImageRequest`、`ImageResult`：请求与结果的数据结构。

## 依赖与构建

- **最低 SDK**：24
- **编译 SDK**：35
- **主要依赖**：
  - AndroidX Core、AppCompat、Material
  - OkHttp
  - Kotlin 协程
- **构建方式**：
  - 使用 Gradle 构建（见 `build.gradle.kts`）

## 基本用法

1. **初始化**（建议在 Application 中调用）：

```kotlin
ImageLoader.init(context)
```

2. **在 ImageView 中加载图片**：

```kotlin
imageView.loadImage(
    url = "https://example.com/image.png",
    placeholder = ContextCompat.getDrawable(context, R.drawable.ic_placeholder),
    onError = { /* 失败回调 */ }
)
```

3. **手动加载图片（协程环境下）**：

```kotlin
val bitmap = ImageLoader.load("https://example.com/image.png")
```

## 测试

- `src/androidTest/`：包含基础的仪器化测试（如包名校验）。
- `src/test/`：包含基础的单元测试（如加法示例）。

## 混淆与 ProGuard

- 默认提供 `proguard-rules.pro`，可根据实际需求调整。
- `consumer-rules.pro` 为空，供集成方自定义。

## 贡献

欢迎 Issue 和 PR！

---

本项目仅供学习与参考，欢迎二次开发。 