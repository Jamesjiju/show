package show.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import show.entity.Goods;
import show.service.ex.InsertException;
import show.service.ex.SelectException;
import show.service.ex.UpdateException;


/**
 * 用户相关的业务层接口
 * @author HeRui
 *
 */
public interface PictureService {

	/**
	 * 新增图片信息
	 * @param user
	 * @throws InsertException
	 */
	public String addPicture(Goods good)throws InsertException;
	
	/**
	 * 根据id更新图片路径
	 * @param id
	 * @throws UpdateException
	 */
	public void update(Integer id,String path) throws UpdateException;
	
	/**
	 * 关联用户表查询出所对应的图片并展示
	 */
	public List<Goods> findByPerName(String username);
	
	/**
	 * 查询出所有的图片供管理员查看
	 */
	public List<Goods> findAll();
	
	/**
	 * 查询网吧名称和对应的收益情况
	 * @return
	 */
	public List<Goods> findCusName(String out_address) throws SelectException;
	
	/**
	 * 根据图片投放地点和网管名称查询投放到该网吧中的图片数量
	 * @return
	 */
	public List<Goods> countAll(String out_cus_name);
	
	/**
	 * 获取收获地址名称
	 * @param address
	 */
	public Goods findName(Goods good) throws InsertException;
	
	/**
	 * 查询所有图片的价格
	 * @return
	 */
	public List<Goods> allPrice();
	
	/**
	 * 根据图片名称更新对应价格
	 * @param price
	 * @param title
	 * @throws UpdateException
	 */
	public void updatePrice(@Param("price")Double price,@Param("title")String title) throws UpdateException;
}
