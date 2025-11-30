[English](README.en.md) | [中文](README.md)

# 天气应用

这是一个简单的天气应用程序，允许用户输入城市名称来获取天气信息。应用使用了 Android 的 RecyclerView 来展示天气预报数据，并通过 HTTP 请求从服务器获取天气信息。

## 功能特性

- 输入城市名称获取天气信息
- 展示天气预报详情，包括温度、天气状况等
- 使用 RecyclerView 展示列表数据
- 包含关于页面

## 安装步骤

1. 确保你已经安装了 Android Studio。
2. 克隆仓库到本地：
   ```bash
   git clone https://github.com/zhuyu12246/Weather-forecast-software.git
   ```
3. 打开 Android Studio，选择 "Open an existing Android Studio project"，然后选择克隆下来的项目文件夹。
4. 同步 Gradle 项目。
5. 连接你的 Android 设备或启动模拟器。
6. 点击 "Run" 按钮来构建并运行应用。

## 使用说明

1. 打开应用后，在输入框中输入城市名称。
2. 点击 "查询" 按钮，应用将获取并展示该城市的天气信息。
3. 点击 "关于" 按钮，可以查看应用的关于信息。

## 项目结构

- `MainActivity.java`：主界面，处理用户输入和展示天气信息。
- `MyHttpConnection.java`：处理 HTTP 请求，获取天气数据。
- `CustomAdapter.java`：RecyclerView 的适配器，用于绑定天气数据。
- `ForeCastClass.java`：天气数据的模型类。
- `Item.java`：列表项的数据模型类。
- `AboutActivity.java`：关于页面。

## 许可证

本项目使用 MIT 许可证。详情请查看 LICENSE 文件。
