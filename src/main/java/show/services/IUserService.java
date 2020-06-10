package show.services;

import java.util.List;

import show.entity.District;
import show.entity.Goods;
import show.entity.User;
import show.service.ex.InsertException;
import show.service.ex.PasswordNotMatchException;
import show.service.ex.UpdateException;
import show.service.ex.UserNotFoundException;
import show.service.ex.UsernameDuplicateException;

/**
 * 用户相关的业务层接口
 * @author HeRui
 *
 */
public interface IUserService {

	/**
	 * 用户注册
	 * @param user 将要注册的用户数据
	 * @throws UsernameDuplicateException
	 * @throws InsertException
	 */
	void reg(User user)throws UsernameDuplicateException,InsertException;
	
	/**
	 * 新增收获地址
	 * @param address
	 */
	public District findName(District district) throws InsertException;
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	User login(String username,String password)throws UserNotFoundException,PasswordNotMatchException;
	
	/**
	 * 根据用户名查找数据
	 * @param username
	 * @return
	 * @throws UserNotFoundException
	 */
	User getByUsername(String username) throws UserNotFoundException;
	
	/**
	 * 模糊查询商品数据，用于前台页面展示
	 * @param title
	 * @return
	 */
//	public List<Goods> selectAll(String title);
}
