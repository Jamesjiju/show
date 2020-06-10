package show.services;

import java.util.List;

import show.entity.District;

public interface IDistrictService {

	/**
	 * 查询省、市、区名称
	 */
	public List<District> findProvince(String lev);
	
}
