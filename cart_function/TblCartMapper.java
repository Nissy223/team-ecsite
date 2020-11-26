package jp.co.internous.lollipop.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.lollipop.model.domain.TblCart;
import jp.co.internous.lollipop.model.domain.dto.CartDto;

@Mapper
public interface TblCartMapper {
	//CartDto型のListにTblCartMapper.xmlで定義したfindByUserIdにuserIdを引数として呼び出す。@param:引数と引数の概要に関して記述
	List<CartDto> findByUserId(@Param("userId") int userId);
	
	//TblCart型のcartから、userId, productId, productCountのデータをtbl_cartテーブルに挿入する。
	//(オプションとして、getGeneratedKeys メソッドをtrueに設定(デフォルトfalseで取得しない設定)して、データベース側で自動生成されたキーを取得し、keyPropertyで指定したidにセットされる。)
	@Insert("INSERT INTO tbl_cart ("
			+ "user_id, product_id, product_count "
			+ ") "
			+ "VALUES ("
			+ "#{userId}, #{productId}, #{productCount} "
			+ ")")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(TblCart cart);
	
	//userIdとproductIdを引数として持ってきて、user_idと#{userId} & product_idと#{productId}のそれぞれの値が一致する条件で、
	//TblCart型のcartからproductCount(購入個数を足した値にする), userId, productId. 更新日時のデータをtbl_cartテーブルに更新する。
	@Update("UPDATE tbl_cart SET product_count = product_count + #{productCount}, updated_at = now() WHERE user_id = #{userId} AND product_id = #{productId}")
	int update(TblCart cart);
	
	//userIdを引数として持ってきて、user_idと#{userId}の値が一致する条件で、tbl_cartテーブルのuser_idのレコード件数をカウント関数で抽出する。
	@Select("SELECT count(user_id) FROM tbl_cart WHERE user_id = #{userId}")
	int findCountByUserId(@Param("userId") int userId);
	
	//tmpUserIdとuserIdを引数として持ってきて、user_idと#{tmpUserId}の値が一致する条件で、
	//user_id, 更新日時のデータをtbl_cartテーブルに更新する。
	@Update("UPDATE tbl_cart SET user_id = #{userId}, updated_at = now() WHERE user_id = #{tmpUserId}")
	int updateUserId(@Param("userId") int userId, @Param("tmpUserId") int tmpUserId);
	
	//userIdとproductIdを引数として持ってきて、user_idと#{userId} & product_idと#{productId}のそれぞれの値が一致する条件で、
	//tbl_cartテーブルのidのレコード件数をカウント関数で抽出する。
	@Select("SELECT count(id) FROM tbl_cart WHERE user_id = #{userId} AND product_id = #{productId}")
	int findCountByUserIdAndProuductId(@Param("userId") int userId, @Param("productId") int productId);
	
	//idを引数として持ってきて、idと#{id}の値が一致する条件で、tbl_cartテーブルのデータを削除する。
	@Delete("DELETE FROM tbl_cart WHERE id = #{id}")
	int deleteById(@Param("id") int id);
	
	//userIdを引数として持ってきて、user_idと#{userId}の値が一致する条件で、tbl_cartテーブルのデータを削除する。
	@Delete("DELETE FROM tbl_cart WHERE user_id = #{userId}")
	int deleteByUserId(@Param("userId") int userId);
}