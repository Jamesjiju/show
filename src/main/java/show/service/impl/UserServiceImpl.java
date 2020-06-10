package show.service.impl;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import show.entity.District;
import show.entity.Goods;
import show.entity.User;
import show.mapper.DistrictMapper;
import show.mapper.UserMapper;
import show.service.ex.InsertException;
import show.service.ex.PasswordNotMatchException;
import show.service.ex.UpdateException;
import show.service.ex.UserNotFoundException;
import show.service.ex.UsernameDuplicateException;
import show.services.IUserService;
/**
 * 用户数据相关的业务层实现类
 * @author HeRui
 *
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	public UserMapper userMapper;
	
	@Autowired
	public DistrictMapper disMapper;
	
	@Override
	public void reg(User user) throws UsernameDuplicateException, InsertException {
		//根据尝试注册的用户名查询数据
		findByUsername(user.getUsername());
		//生成随机盐
		String salt = UUID.randomUUID().toString();
		//基于源密码和盐值执行加密
		String pad = getMd5Password(user.getPassword(), salt);
		//将盐和加密结果封装到user对象中
		user.setPassword(pad);
		user.setSalt(salt);
		//执行注册
		insert(user);
	}
	
	/**
	 * 根据地址code得到对应得地址名
	 * @param address
	 */
	public District findName(District district) {
		String proviceName = disMapper.findName(district.getProvince());
		String cityName = disMapper.findName(district.getCity());
		String areaName = disMapper.findName(district.getArea());
		district.setProvince(proviceName);
		district.setCity(cityName);
		district.setArea(areaName);
		return district;
	}
	
	@Override
	public User login(String username, String password) throws UserNotFoundException,PasswordNotMatchException {
		//根据用户名查找用户数据
		User result = findByname(username);
		//对原密码进行加密并得到新的密码
		String psd = getMd5Password(password, result.getSalt());
		//基于盐值对加密后的密码与数据库中密码进行对比
		if(!psd.equals(result.getPassword())){
			//密码不匹配
			throw new PasswordNotMatchException("对不起，您输入的密码错误！");
		}
		return result;
	}
	
	@Override
	public User getByUsername(String username) throws UserNotFoundException {
		return findByname(username);
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	private void insert(User user){
		Integer in = userMapper.insert(user);
		if(in != 1){
			throw new InsertException("对不起，发生未知错误！");
		}
	}
	
	/**
	 * 根据用户名查找用户数据
	 * @param username
	 * @return
	 */
	private User findByname(String username){
		User user = userMapper.findByUsername(username);
		if(user == null){
			throw new UserNotFoundException("对不起，该用户不存在！");
		}
		return user;
	}
	
	/**
	 * 根据用户名查找用户数据
	 * @param username
	 * @return
	 */
	private void findByUsername(String username){
		User user = userMapper.findByUsername(username);
		if(user != null){
			throw new UsernameDuplicateException("对不起，该用户已被注册！");
		}
	}
	
	/**
	 * 将密码执行加密
	 * @param password 源密码
	 * @param salt 盐值
	 * @return 加密后的结果
	 */
	private String getMd5Password(String password,String salt) {
		//拼接原密码与盐值
		String str = salt + password + salt;
		//循环加密5次
		for(int i=0;i<5;i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes());
		}
		//返回结果
		return str;
	}

	/**
	 * 根据用户名修改用户数据
	 * @param username
	 * @return
	 */
	private void updateWord(String username,String adj,String none){
		Integer in = userMapper.updateWord(username, adj, none);
		if(in != 1){
			throw new UpdateException("对不起，数据更新失败！");
		}
	}

	/**
	 * 模糊查询商品数据，用于前台页面展示
	 * @param title
	 * @return
	 */
//	@Override
//	public List<Goods> selectAll(String title) {
//		Integer num = 0;
//		//查询商品数据有多少条，一共需要展示几页
//		Integer in = userMapper.selectCount(title);
//		//用查询出来的商品数据总和取整于12（每页展示12条）
//		if(in != null){
//			num = in / 12;
//		}
//		//得到了需要展示的页数，遍历将页数放入对应的数组中
//		Goods good = new Goods();
//		Integer[] inn = new Integer[num];
//		for(int i = 1;i <= num;i++){
//			inn[i-1] = i;
//		} 
//		good.setPageNum(inn);
//		List<Goods> list = userMapper.selectAll(title,"13","0");
//		list.add(0, good);
//		return list;
//	}
}































