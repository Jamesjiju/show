package show.entity;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;// 商品id
	private String username;//用户名
	private Integer category;// 分类id
	private String title;// 商品标题
	private Double price;// 商品单价
	private Integer num;// 库存总数量
	private Integer num1;// 各网吧库存图片数量
	private String image;// 图片路径
	private Integer status;// 商品状态 1：上架 2：下架 3：删除
	private Integer if_out;// 是否投放
	private Date out_time;// 下架时间
	private Date on_time;// 投放时间
	private String out_address;// 意向投放地点
	private String createdUser;// 创建人
	private String createdTime;// 创建时间
	private String out_cus_name;// 投放网吧名称
	private String out_per_name;// 投放网管名称
	private Double admin_money;// 总收益
	private String province;// 省
	private String city;// 市
	private String area;// 区
	private Double com_money;// 网管收益
    private Integer[] pageNum;
	public Integer[] getPageNum() {
		return pageNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getAdmin_money() {
		return admin_money;
	}

	public void setAdmin_money(Double admin_money) {
		this.admin_money = admin_money;
	}

	public Double getCom_money() {
		return com_money;
	}

	public void setCom_money(Double com_money) {
		this.com_money = com_money;
	}

	public Integer getNum1() {
		return num1;
	}

	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setPageNum(Integer[] pageNum) {
		this.pageNum = pageNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNum() {
		return num;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getIf_out() {
		return if_out;
	}

	public void setIf_out(Integer if_out) {
		this.if_out = if_out;
	}

	public Date getOut_time() {
		return out_time;
	}

	public void setOut_time(Date out_time) {
		this.out_time = out_time;
	}

	public Date getOn_time() {
		return on_time;
	}

	public void setOn_time(Date on_time) {
		this.on_time = on_time;
	}

	public String getOut_address() {
		return out_address;
	}

	public void setOut_address(String out_address) {
		this.out_address = out_address;
	}

	public String getOut_cus_name() {
		return out_cus_name;
	}

	public void setOut_cus_name(String out_cus_name) {
		this.out_cus_name = out_cus_name;
	}

	public String getOut_per_name() {
		return out_per_name;
	}

	public void setOut_per_name(String out_per_name) {
		this.out_per_name = out_per_name;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", category=" + category + ", title=" + title + ", price=" + price + ", num=" + num
				+ ", image=" + image + ", status=" + status + ", if_out=" + if_out + ", out_time=" + out_time
				+ ", on_time=" + on_time + ", out_address=" + out_address + ", createdUser=" + createdUser
				+ ", createdTime=" + createdTime + ", out_cus_name=" + out_cus_name + ", out_per_name=" + out_per_name
				+ "]";
	}

}
