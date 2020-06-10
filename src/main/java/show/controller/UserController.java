package show.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import show.entity.District;
import show.entity.Goods;
import show.entity.User;
import show.services.IUserService;
import show.utils.ResponseResult;

/**
 * 用户相关的控制器类
 * @author HeRui
 *
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController{

	@Autowired
	public IUserService service;
	
	@RequestMapping("reg")
	public ResponseResult<Void> reg(HttpServletRequest request,User user,District districe){
		//获取用户的性别
		Integer gender = Integer.parseInt(request.getParameter("nan"));
		//将gender封装到user中
		user.setGender(gender);
		District dis = service.findName(districe);
		user.setAddress(dis.getProvince() + dis.getCity() + dis.getArea());
		//执行注册
		service.reg(user);
		//返回结果
		return new ResponseResult<>(SUCCESS);
	}
	
	@RequestMapping("login")
	public ResponseResult<User> login(User user,HttpSession session){
		int str = SUCCESS;
		//调用业务层方法执行登录
		User result = service.login(user.getUsername(), user.getPassword());
		//将用户名和uid封装到session中
		session.setAttribute("uid", String.valueOf(result.getUid()));
		session.setAttribute("username", String.valueOf(result.getUsername()));
		// 如果不是管理员登陆，返回201
		if(!"root".equals(result.getUsername())){
			str = 201;
		}
		//返回结果
		return new ResponseResult<>(str,result);
	}
	
	@RequestMapping("find")
	public ResponseResult<User> show(User user,HttpSession session){
		//获取用户名
		String username = session.getAttribute("username").toString();
		//执行查找
		User user2 = service.getByUsername(username);
		//返回结果
		return new ResponseResult<>(SUCCESS,user2);
	}
	 
//	@RequestMapping("searchAll")
//	public ResponseResult<List<Goods>> searchAll(Goods good){
//		//执行查询
//		List<Goods> goods = service.selectAll(good.getTitle());
//		//返回结果
//		return new ResponseResult<>(SUCCESS,goods); 
//	}
}
