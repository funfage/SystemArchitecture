# 实验目的

1. 学习Springboot框架
2. 学习MVC模式开发一个简单的登录功能
3. 强化对MySQL数据库以及Mybatis框架的使用

# 实验平台

1. 操作系统：window10
2. IDE工具：IntelliJ IDEA，Vscode
3. 数据库图形化工具：Navicat
4. 版本控制工具：GIt

# 实验前准备

1. 安装IntelliJ IDEA和Vscode工具
2. 安装Navicat工具
3. 安装Git工具

# 实验内容

## 一、 创建pringboot工程并运行

* 新建项目选择Spring Initializer，接着选择jdk版本，点击Next

![image-20200426182829779](imgs\image-20200426182829779.png)

* 填写项目元数据信息

![image-20200426183924742](imgs\image-20200426183924742.png)

* 引入项目依赖，由于是web开发，所以选择spring web相关依赖

![image-20200426184029198](imgs\image-20200426184029198.png)

* 选择项目名和项目保存的位置，点击finish即可创建项目

![image-20200426184155904](imgs\image-20200426184155904.png)

* 项目的目录结构如下所示

![image-20200426184402021](imgs\image-20200426184402021.png)

* 点击工具栏的运行即可启动项目

![image-20200426184445295](imgs\image-20200426184445295.png)

* 查看日志信息，确认运行成功

![image-20200426184601006](imgs\image-20200426184601006.png)

## 二、springboot整合mybatis

### 1、引入数据库相关依赖

![image-20200426213138560](imgs\image-20200426213138560.png)

### 2、mybatis相关配置

（1）application.yml的配置如下

![image-20200426213320983](imgs\image-20200426213320983.png)

（2）mybatis-config.xml的配置信息如下

![image-20200426213726822](imgs\image-20200426213726822.png)

###  3、运行测试

（1）编写测试类

![image-20200426214138390](imgs\image-20200426214138390.png)

（2）运行测试类，并查看数据库是否添加了一条用户记录

![image-20200426214232678](imgs\image-20200426214232678.png)

![image-20200426214400869](imgs\image-20200426214400869.png)

## 三、结果集的封装

### 1、错误码的枚举类

~~~java
@Getter
public enum ResultError {
    ;

    //返回的错误码
    private Integer code;

    //返回的信息
    private String message;

    ResultError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
~~~

### 2、返回结果封装

~~~java
@Getter
public class ResultVo {

    private Integer code;
    private Boolean success;
    private Object data;
    private String message;

    public static ResultVo success(Object data, String msg) {
        return new ResultVo(0, true, data, msg);
    }

    public static ResultVo failure(ResultError error) {
        return new ResultVo(error.getCode(), false, error.getMessage());
    }

    public ResultVo(Integer code, Boolean success, Object data, String msg) {
        this.code = code;
        this.success = success;
        this.data = data;
        this.message = msg;
    }

    public ResultVo(Integer code, Boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.message = msg;
    }
}
~~~

## 四、基于mvc模式的后台开发

### 1、项目目录结构

![image-20200427113141173](imgs\image-20200427113141173.png)

### 2、登录模块controller层代码示例

~~~java
@RestController //指明该类下的所有方法都会返回json数据格式
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService; //注入service接口类

    @PostMapping("/login") //post请求
    public ResultVo login(User user) {
        //调用service接口，查找该用户是否存在
        if (userService.getByUNameAndPWord(user.getUsername(), user.getPassword()) != null) {
            //返回成功的信息
            return ResultVo.success(user);
        } else {
            //返回失败的信息
            return ResultVo.failure(ResultError.LOGIN_FAILURE);
        }
    }
}
~~~

## 五、前端项目搭建

### 1、项目结构

![image-20200427113927425](imgs\image-20200427113927425.png)

### 2、调用后台登录接口的js代码示例

~~~javascript
$(function () {
  // 登录验证的controller url
  var loginUrl = "http://localhost:8000/user/login";
  $("#submit").click(function () {
    // 获取输入的帐号
    var username = $("#username").val();
    // 获取输入的密码
    var password = $("#psw").val();
    // 访问后台进行登录验证
    $.ajax({
      url: loginUrl,
      async: false,
      cache: false,
      type: "post",
	  dataType: "json",
      data: {
        username: username,
        password: password,
	  },
      success: function (data) {
        if (data.success) {
          $.toast("登录成功！");
        } else {
          $.toast("登录失败！" + data.message);
        }
      },
    });
  });
});

~~~

## 六、登录功能演示

1. 打开登录界面

![image-20200427114400266](imgs\image-20200427114400266.png)

2. 输入正确的用户名和密码

![image-20200427114455458](imgs\image-20200427114455458.png)

3. 输入错误的用户名或密码

![image-20200427114509506](imgs\image-20200427114509506.png)



# 实验总结

​	通过本次的框架学习和登录功能的开发，首先是让我对web开发的mvc模式有了更深入的理解，同时也体会到前后端分离开发方式的优势。其次，通过对springboot框架的学习和使用，让我深刻体会到了springboot框架相比于之前学的smm框架所要配置的东西实在是要少太多了，因此极大提高了我的开发效率，让我对这门框架的学习有了浓厚的兴趣。通过本实验的学习，让我受益匪浅。















