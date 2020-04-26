# 实验目的

1. 学习Springboot框架
2. 学习MVC模式开发一个简单的登录功能
3. 强化对MySQL数据库以及Mybatis框架的使用

# 实验平台

1. 操作系统：window10
2. IDE工具：IntelliJ IDEA
3. 数据库图形化工具：Navicat
4. 版本控制工具：GIt

# 实验前准备

1. 安装IntelliJ IDEA工具
2. 安装Navicat工具
3. 安装Git工具

# 实验内容

## 一、 创建pringboot工程并运行

* 新建项目选择Spring Initializer，接着选择jdk版本，点击Next

![image-20200426182829779](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426182829779.png)

* 填写项目元数据信息

![image-20200426183924742](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426183924742.png)

* 引入项目依赖，由于是web开发，所以选择spring web相关依赖

![image-20200426184029198](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426184029198.png)

* 选择项目名和项目保存的位置，点击finish即可创建项目

![image-20200426184155904](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426184155904.png)

* 项目的目录结构如下所示

![image-20200426184402021](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426184402021.png)

* 点击工具栏的运行即可启动项目

![image-20200426184445295](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426184445295.png)

* 查看日志信息，确认运行成功

![image-20200426184601006](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426184601006.png)

## 二、springboot整合mybatis

### 1、引入数据库相关依赖

![image-20200426213138560](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426213138560.png)

### 2、mybatis相关配置

（1）application.yml的配置如下

![image-20200426213320983](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426213320983.png)

（2）mybatis-config.xml的配置信息如下

![image-20200426213726822](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426213726822.png)

###  3、运行测试

（1）编写测试类

![image-20200426214138390](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426214138390.png)

（2）运行测试类，并查看数据库是否添加了一条用户记录

![image-20200426214232678](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426214232678.png)

![image-20200426214400869](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20200426214400869.png)







# 实验总结