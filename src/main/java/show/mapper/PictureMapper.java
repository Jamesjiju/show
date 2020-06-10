package show.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import show.entity.Goods;
import show.service.ex.UpdateException;

/**
 * 新增图片
 * @author HeRui
 *
 */
public interface PictureMapper {

	/**
	 * 新增图片
	 * @param good
	 * @return
	 */
	public Integer addPicture(Goods good);
	
	/**
	 * 根据图片名称更新图片路径
	 * @param title
	 * @param path
	 */
	public void update(@Param("id")Integer id,@Param("image")String image);
	
	/**
	 * 关联用户表中的网管名称查询出对应的图片路径并展示
	 */
	public List<Goods> findByPerName(String username);
	
	/**
	 * 查询所有图片路径供管理员查看
	 */
	public List<Goods> findAll();
	
	/**
	 * 查询网吧名称和对应的收益情况
	 * @return
	 */
	public List<Goods> findCusName(String out_address);
	
	/**
	 * 查询图片总数
	 * @return
	 */
	public Integer count();
	
	/**
	 * 查询所有图片对应的价格
	 * @return
	 */
	public List<Goods> allPrice();
	
	/**
	 * 根据图片投放地点和网管名称查询投放到该网吧中的图片数量
	 * @return
	 */
	public List<Goods> countAll(String out_cus_name);
	
	/**
	 * 根据图片名称更新对应价格
	 * @param price
	 * @param title
	 * @throws UpdateException
	 */
	public void updatePrice(@Param("price")Double price,@Param("title")String title) throws UpdateException;
}
