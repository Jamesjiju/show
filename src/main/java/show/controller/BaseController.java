package show.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import show.service.ex.AccessDeniedException;
import show.service.ex.DeleteException;
import show.service.ex.FileEmptyException;
import show.service.ex.FileSizeException;
import show.service.ex.FileTypeException;
import show.service.ex.InsertException;
import show.service.ex.PasswordNotMatchException;
import show.service.ex.SelectException;
import show.service.ex.ServiceException;
import show.service.ex.UpdateException;
import show.service.ex.UserNotFoundException;
import show.service.ex.UsernameDuplicateException;
import show.utils.ResponseResult;

/**
 * 控制器的其类
 */
public abstract class BaseController {
	/**
	 * 响应结果时用于表示操作成功
	 */
	protected static final int SUCCESS = 200;
	
	/**
	 * 从Session获取uid
	 * @param session HttpSession
	 * @return uid
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseResult<Void> handlerException(Throwable e){
		ResponseResult<Void> rr = new ResponseResult<>();
		rr.setMessage(e.getMessage());
		if(e instanceof UsernameDuplicateException) {
			//400-用户名冲突异常
			rr.setState(400);
		}else if(e instanceof UserNotFoundException) {
			//401-用户名不存在异常
			rr.setState(401);
		}else if(e instanceof PasswordNotMatchException) {
			//402-密码错误异常
			rr.setState(402);
		}else if(e instanceof AccessDeniedException) {
			//404-非法访问异常
			rr.setState(404);
		}else if(e instanceof InsertException) {
			//500-插入数据异常
			rr.setState(500);
		}else if(e instanceof UpdateException) {
			//501-修改错误异常
			rr.setState(501);
		}else if(e instanceof DeleteException) {
			//502-删除错误异常
			rr.setState(502);
		}else if(e instanceof FileSizeException){
			// 503-文件大小异常
			rr.setState(503);
		}else if(e instanceof FileTypeException){
			// 504-文件类型异常
			rr.setState(504);
		}else if(e instanceof FileEmptyException){
			// 505-文件夹空异常
			rr.setState(505);
		}else if(e instanceof SelectException){
			// 506-查询数据为空异常
			rr.setState(506);
		}
		return rr;
	}
}
























