package jp.co.internous.lollipop.model.domain;

import java.sql.Timestamp;

import jp.co.internous.lollipop.model.form.CartForm;


 //tbl_cartテーブルに出し入れするデータを格納する。
 public class TblCart {
	//ID
	private int id;
	//ユーザーID
	private int userId;
	//商品ID
	private int productId;
	//個数
	private int productCount;
	//登録日時
	private Timestamp createdAt;
	//更新日時
	private Timestamp updatedAt;
	
	//インスタンス化の際に初期設定せず、後からデータをSetterで受け取るコンストラクタ
	public TblCart() {}
	
	//CartFormからのデータをまとめて受け取るコンストラクタ
	public TblCart(CartForm f) {
		userId = f.getUserId();
		productId = f.getProductId();
		productCount = f.getProductCount();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
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