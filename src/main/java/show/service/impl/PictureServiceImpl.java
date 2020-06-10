package show.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import show.entity.District;
import show.entity.Goods;
import show.mapper.DistrictMapper;
import show.mapper.PictureMapper;
import show.service.ex.InsertException;
import show.service.ex.SelectException;
import show.service.ex.UpdateException;
import show.services.PictureService;
/**
 * 用户数据相关的业务层实现类
 * @author HeRui
 *
 */
@Service
public class PictureServiceImpl implements PictureService {

	@Autowired
	public PictureMapper picMapper;
	
	@Autowired
	public DistrictMapper disMapper;
	
	/**
	 * 新增图片方法
	 * @param good
	 * @throws InsertException
	 */
	@Override
	public String addPicture(Goods good) throws InsertException {
		picMapper.addPicture(good);
		return good.getId();
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

	/**
	 * 根据图片名称更新图片路径
	 */
	@Override
	public void update(Integer id,String path) throws UpdateException {
		picMapper.update(id,path);
	}

	/**
	 * 查询出所有图片管理员查看
	 */
	public List<Goods> findAll(){
		return picMapper.findAll();
	}

	/**
	 * 查询网吧名称和对应的收益情况
	 */
	@Override
	public List<Goods> findCusName(String out_address) throws SelectException{
		List<Goods> doo = picMapper.findCusName(out_address);
		if(doo.size() <= 0){
			throw new SelectException("未查询到任何数据！");
		}
		return doo;
	}

	/**
	 * 根据图片投放地点和网管名称查询投放到该网吧中的图片数量
	 */
	@Override
	public List<Goods> countAll(String out_per_name) {
		List<Goods> list = picMapper.countAll(out_per_name);
		if(list.size() <= 0){
			throw new SelectException("未查询到任何数据！");
		}
		return list;
	}
	
	/**
	 * 根据地址code得到对应得地址名
	 * @param address
	 */
	public Goods findName(Goods good) {
		String proviceName = disMapper.findName(good.getProvince());
		String cityName = disMapper.findName(good.getCity());
		String areaName = disMapper.findName(good.getArea());
		good.setProvince(proviceName);
		good.setCity(cityName);
		good.setArea(areaName);
		return good;
	}

	/**
	 * 查询所有图片的价格
	 */
	@Override
	public List<Goods> allPrice() {
		return picMapper.allPrice();
	}

	/**
	 * 根据图片名称更新图片价格
	 */
	@Override
	public void updatePrice(Double price, String title) throws UpdateException {
		picMapper.updatePrice(price, title);
	}

	/**
	 * 关联用户表中的用户名，查询出对应的图片并展示
	 */
	@Override
	public List<Goods> findByPerName(String username) {
		return picMapper.findByPerName(username);
	}
}































