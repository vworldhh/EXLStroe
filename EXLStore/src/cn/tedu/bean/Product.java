package cn.tedu.bean;

public class Product {

	private String id;
	private String name;
	private double price;
	private String category;
	private String imgurl;
	private int pnum;
	private String description;
	private int stat; //当前商品的状态 0 为 下架 1 为上架
	
	
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", imgurl="
				+ imgurl + ", pnum=" + pnum + ", description=" + description + "]";
	}
	
	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Product)){
			return false;
		}
		Product other = (Product) obj;
		if (id != null && id.equals(other.getId())) {
			return true;
		}
		
		return false;
	}
	
	
	
}
