$(function () {
  // 登录验证的controller url
  var loginUrl = "http://127.0.0.1:8000/user/login";

  $("#submit").click(function () {
    // 获取输入的帐号
    var username = $("#username").val();
    // 获取输入的密码
    var password = $("#psw").val();

    // 访问后台进行登录验证
    $.ajax({
      url: "http://127.0.0.1:8000/user/login",
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
			// alert("登录成功！");
          $.toast("登录成功！");
        } else {
			// alert("登录失败！" + data.message);
          $.toast("登录失败！" + data.message);
        }
      },
    });
  });
});
