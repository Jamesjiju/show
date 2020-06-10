package show.entity;

import java.io.Serializable;


/**
 * 用户相关的实体类
 * 
 * @author HeRui
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = -5057680178618048993L;

	private Integer uid;
	private String username;
	private String password;
	private Integer gender;
	private String salt;
	private String address;
	private Double admin_money;
	private Double com_money;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", gender=" + gender
				+ ", salt=" + salt + ", address=" + address + ", admin_money=" + admin_money + ", com_money="
				+ com_money + "]";
	}

}
