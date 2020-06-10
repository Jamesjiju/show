package show.mapper;

import java.util.List;

import show.entity.District;

/**
 * 处理省市区的持久层接口
 */
public interface DistrictMapper {
	
	/**
	 * 查询省、市、区名称
	 */
	public List<District> findProvince(String parent);
	
	/**
	 * 根据地址code得到地址名
	 * @param code
	 */
	public String findName(String code);
}
