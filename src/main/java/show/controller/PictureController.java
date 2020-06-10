package show.controller;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import show.entity.District;
import show.entity.Goods;
import show.entity.User;
import show.mapper.PictureMapper;
import show.mapper.UserMapper;
import show.service.ex.FileEmptyException;
import show.service.ex.FileSizeException;
import show.service.ex.FileTypeException;
import show.services.IUserService;
import show.services.PictureService;
import show.utils.ResponseResult;

/**
 * 图片信息相关的控制器类
 * @author HeRui
 *
 */
@RestController
@RequestMapping("pictures")
public class PictureController extends BaseController{

	//文件大小限制
	public static final long FILESIZE = 2*1024*1024;
	
	/**
	 * 上传头像的文件夹名称
	 */
	public static final String UPLOAD_DIR = "upload";
	
	public static final List<String> FILELIST = new ArrayList<>();
	
	static{
		FILELIST.add("image/jpeg");
		FILELIST.add("image/png");
		FILELIST.add("image/gif");
		FILELIST.add("image/bmp");
	}
	
	@Autowired
	public IUserService service;
	
	@Autowired
	public PictureService picservice;
	
	@Autowired
	public PictureMapper pictMapper;
	
	@Autowired
	public UserMapper userMapper;
	
	@RequestMapping("addPicture")
	public ResponseResult<String> addPicture(Goods good,District districe){
		District dis = service.findName(districe);
		good.setOut_address(dis.getProvince() + dis.getCity() + dis.getArea());
		//查询图片表id
		String in = picservice.addPicture(good);
		//返回结果
		return new ResponseResult<>(SUCCESS,in);
	}
	
	@RequestMapping("changePrice")
	public ResponseResult<Void> changePrice(Goods good,District districe){
		//修改图片价格
		picservice.updatePrice(good.getPrice(), good.getTitle());
		//返回结果
		return new ResponseResult<>(SUCCESS);
	}
	
	@RequestMapping("searchAll")
	public ResponseResult<List<Goods>> search(HttpSession session){
		//得到session中的用户姓名，登录后展示该用户下的图片
		String name = (String)session.getAttribute("username");
		// 根据登录的用户名查询出所对应的网管的图片
		List<Goods> go = picservice.findByPerName(name);
		// 查询出所有的图片供管理员查看
		List<Goods> go1 = picservice.findAll();
		//返回结果
		if("root".equals(name)){
			return new ResponseResult<>(SUCCESS,go1);
		}
		return new ResponseResult<>(SUCCESS,go);
	}
	
	@RequestMapping("findCusName")
	public ResponseResult<List<Goods>> findCusName(Goods good){
		// 网吧收益
		Double dou = 0.0;
		// 总收益
		Double dd = 0.0;
		// 得到投放地点
		Goods goo = picservice.findName(good);
		// 查询总图片数量
		Integer in = pictMapper.count();
		// 得到所有图片的价格
		List<Goods> all = picservice.allPrice();
		for(Goods gg : all){
			dd += gg.getPrice();
		}
		// 得到所有网吧的名称和对应的图片价格
		List<Goods> go = picservice.findCusName(goo.getProvince() + goo.getCity() + goo.getArea());
		// 查询对应网吧名称对应的的图片数量
		for(Goods gg : go){
			List<Goods> in1 = picservice.countAll(gg.getOut_per_name());
			dou = 0.0;
			for(Goods g : in1){
				dou += g.getPrice();
			}
			gg.setAdmin_money(dd);
			gg.setCom_money(dou);
			gg.setNum(in);
		}
		//返回结果
		return new ResponseResult<>(SUCCESS,go);
	}
	
	@RequestMapping("findPerName")
	public ResponseResult<List<Goods>> findPerName(Goods good){
		Double ddd = 0.0;
		// 总收益
		Double dd = 0.0;
		// 查询总图片数量
		Integer in = pictMapper.count();
		// 得到所有图片的价格
		List<Goods> all = picservice.allPrice();
		for(Goods gg : all){
			dd += gg.getPrice();
		}
		// 按网吧名称查询对应收益
		List<Goods> in2 = picservice.countAll(good.getOut_per_name());
		for(Goods gd : in2){
			ddd += gd.getPrice();
			gd.setCom_money(ddd);
			gd.setAdmin_money(dd);
			gd.setNum(in);
			gd.setOut_per_name(good.getOut_per_name());
		}
		//返回结果
		return new ResponseResult<>(SUCCESS,in2);
	}
	
	@RequestMapping("showMoney")
	public ResponseResult<Goods> showMoney(HttpSession session){
		// 得到网管名称
		String name = (String)session.getAttribute("username");
		// 网吧收益
		Double dou = 0.0;
		Goods good = new Goods();
		// 得到给定网吧名称下的图片价格总和
		List<Goods> go = picservice.countAll(name);
		// 遍历得到总价
		for(Goods gg : go){
			dou += gg.getPrice();
		}
		good.setCom_money(dou);
		good.setOut_per_name(name);
		//返回结果
		return new ResponseResult<>(SUCCESS,good);
	}
	
	@RequestMapping("/{name}/checkName")
	public ResponseResult<Void> showMoney(@PathVariable("name")String name){
		// 根据前台名称查询该用户有没有注册
		User user = userMapper.findByUsername(name);
		ResponseResult<Void> result = new ResponseResult<>();
		if(user == null){
			result.setState(400);
			result.setMessage("该网管还没有注册，不能录入！");
		}
		//返回结果
		return result;
	}
	
	@RequestMapping("/{id}/savePicture")
	public ResponseResult<String> savePicture(HttpServletRequest request,MultipartFile image,@PathVariable("id")Integer id){
		//校验文件格式
		this.checkFile(image);
		//确定要保存的文件夹
//		String path = request.getServletContext().getRealPath(UPLOAD_DIR);
		String path = "D:\\tts9\\workspace\\show\\src\\main\\resources\\static\\images";
//		String path = "D:\\upload";
//		String path = request.getContextPath();
		System.out.println("path:" + path);
		//判断该文件夹是否存在，若不存在就创建一个
		File parentfile = new File(path);
		try {
			String str = parentfile.getCanonicalPath();
			System.out.println("str:" + str);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!parentfile.exists()){
			parentfile.mkdir();//创建一个文件夹
		}
		//得到源文件及其扩展名（所要上传的文件的文件名）
		String oriFile = image.getOriginalFilename(); 
		System.out.println("oriFile:" + oriFile);
		String suffix = "";
		int index = oriFile.lastIndexOf(".");
		if(index != -1){
			//得到所要上传的文件的后缀名
			suffix = oriFile.substring(index);
		}
		//得到新的文件名
		String newFile = UUID.randomUUID().toString() + suffix;
		System.out.println("newFile:" + newFile);
		File destFile = new File(path,newFile);
		System.out.println("destFile:" + destFile);
		//上传文件
		try {
			image.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		//文件保存的路径为
		String path1 = "/images" + "/" + newFile;
		System.out.println("path1:" + path1);
		picservice.update(id,path1);
		//返回结果
		return new ResponseResult<>(SUCCESS,path1);
	}
	
	/**
	 * 校验文件格式
	 * @param file
	 */
	public void checkFile(MultipartFile file){
		//判断文件大小
		if(file.getSize() > FILESIZE){
			throw new FileSizeException("文件大小不合适,限定大小为:" + FILESIZE + "kb");
		}
		//判断文件格式是否正确
		if(!FILELIST.contains(file.getContentType())){
			for(String str : FILELIST){
				throw new FileTypeException("文件格式不正确,正确的格式为:" + str);
			}
		}
		//判断文件是否为空
		if(file.isEmpty()){
			throw new FileEmptyException("文件为空！");
		}
	}
}
