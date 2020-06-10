package show.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import show.entity.District;
import show.services.IDistrictService;
import show.utils.ResponseResult;

@RestController
@RequestMapping("area")
public class DistrictController extends BaseController{

	@Autowired
	public IDistrictService service;
	
	@GetMapping("findProvince")
	public ResponseResult<List<District>> findProvice(@RequestParam("parent")String parent){
		List<District> list = service.findProvince(parent);
		return new ResponseResult<List<District>>(SUCCESS,list);
	}
	
	@RequestMapping("findCity")
	public ResponseResult<List<District>> findCity(@RequestParam("parent")String proviceId){
		List<District> list = service.findProvince(proviceId);
		return new ResponseResult<>(SUCCESS,list);
	}
	
	@RequestMapping("findArea")
	public ResponseResult<List<District>> findArea(@RequestParam("parent")String areaId){
		List<District> list = service.findProvince(areaId);
		return new ResponseResult<>(SUCCESS,list);
	}
}
