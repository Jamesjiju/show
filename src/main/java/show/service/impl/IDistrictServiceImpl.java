package show.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import show.entity.District;
import show.mapper.DistrictMapper;
import show.services.IDistrictService;
@Service
public class IDistrictServiceImpl implements IDistrictService{

	@Autowired
	public DistrictMapper mapper;
	
	/**
	 * 返回省名称列表
	 */
	@Override
	public List<District> findProvince(String parent) {
		List<District> list = mapper.findProvince(parent);
		return list;
	}

}
