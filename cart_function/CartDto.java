package jp.co.internous.lollipop.model.domain.dto;

import java.sql.Timestamp;


//Data Transfer Objectの略で、mst_productとtbl_cart 2つのテーブルの内容を格納する。
public class CartDto{
	//	tbl_cart.id(ID)
	private int id;
	//	mst_product.productName(商品名)
	private String productName;
	//	mst_product.imageFullPath(画像フルパス)
	private String imageFullPath;
	//	mst_product.price(価格)
	private int price;
	//	tbl_cart.productCount(購入個数)
	private int productCount;
	//	 mst_product.price * tbl_cart.productCount(小計)
	private int subtotal;
	//	mst_product.createdAt,tbl_cart.createdAt(登録日時)
	private Timestamp createdAt;
	//	mst_product.updatedAt,tbl_cart.updatedAt(更新日時)
	private Timestamp updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getImageFullPath() {
		return imageFullPath;
	}
	public void setImageFullPath(String imageFullPath) {
		this.imageFullPath = imageFullPath;
	}
	
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}