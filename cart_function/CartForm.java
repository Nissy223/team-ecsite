package jp.co.internous.lollipop.model.form;

import java.io.Serializable;


//cart.htmlからの入力内容を格納する。
public class CartForm implements Serializable{
	private static final long serialVersionUID=1L;
	//ユーザーID
	private int userId;
	//選択されている商品ID
	private int productId;
	//購入個数
	private int productCount;
	
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
}