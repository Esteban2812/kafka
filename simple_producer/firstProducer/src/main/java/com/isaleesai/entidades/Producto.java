package com.isaleesai.entidades;

public class Producto { 
	private double progId; 
	private String supc;
	private String brand;
	private String description;
	private String size;
	private String category;
	private String subCategory;
	private double price;
	private int quantity;
	
	public double getProgId() {
		return progId;
	}

	public void setProgId(int progId) {
		this.progId = progId;
	}

	public String getSupc() {
		return supc;
	}

	public void setSupc(String supc) {
		this.supc = supc;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public double getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(double creationTime) {
		this.creationTime = creationTime;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	private String country;
	private String sellerCode;
	private double creationTime;
	private String stock;
	
	public Producto(double progId,String supc,String brand,String description,String size,String category,String subCategory,double price,int quantity,String country,String sellerCode,double creationTime,String stock) { 
		this.progId = progId; 
		this.supc = supc; 
		this.brand = brand;
		this.description = description;
		this.size=size;
		this.category=category;
		this.subCategory=subCategory;
		this.price=price;
		this.quantity=quantity;
		this.country=country;
		this.sellerCode=sellerCode;
		this.creationTime=creationTime;
		this.stock=stock;

	} 
	
	public String toString(){
		return "Producto class info:: "+this.progId+"-"+this.description;
	}
}