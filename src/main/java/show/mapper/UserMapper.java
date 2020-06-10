package show.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import show.entity.Goods;
import show.entity.User;

/**
 * 用户数据相关的持久层接口
 * @author HeRui
 *
 */
public interface UserMapper {

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	Integer insert(User user);
	
	/**
	 * 根据用户名修改用户数据
	 * @param username
	 * @return
	 */
	Integer updateWord(@Param("username") String username,
						@Param("adj") String adj,
						@Param("none") String none);
	
	/**
	 * 根据用户名查找用户数据
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
	/**
	 * 模糊查询商品数据，用于前台页面展示
	 * @param title
	 * @return
	 */
	public List<Goods> selectAll(@Param("title")String title,@Param("endNum")String endNum,@Param("startNum")String startNum);
	
	/**
	 * 模糊查询商品数据，为分页数据做准备
	 * @param title
	 * @return
	 */
	public Integer selectCount(@Param("title")String title);
}
