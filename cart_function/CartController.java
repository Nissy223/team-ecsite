package jp.co.internous.lollipop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.lollipop.model.domain.TblCart;
import jp.co.internous.lollipop.model.domain.dto.CartDto;
import jp.co.internous.lollipop.model.form.CartForm;
import jp.co.internous.lollipop.model.mapper.TblCartMapper;
import jp.co.internous.lollipop.model.session.LoginSession;
//コントローラーとしての機能を付与。
//マッピングにより、/lollipop/cartにアクセスがあった場合、メソッドが呼び出される。
@Controller
@RequestMapping("/lollipop/cart")
public class CartController {
	//new 演算子を使うことなくインスタンス化。
	@Autowired
	private TblCartMapper tblCartMapper;
	@Autowired
	private LoginSession loginSession;
	
	//WebサービスAPIとして作成するためのJSON形式を扱えるようGsonをインスタンス化。
	private Gson gson = new Gson();
	
	//マッピングにより、/にアクセスがあった場合、メソッドが呼び出される。
	@RequestMapping("/")
	public String index(Model m) {
		// 三項演算子を用いて、ユーザーIDを取得。(true ユーザーID : else 仮ユーザーID)
		int userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();

		//CartDto型Listのcartsをインスタンス化して、TblcartMapperのfindByUserIdを呼び出し、上記で取得したユーザーIDに紐づくカート情報を取得する。
		List<CartDto> carts = tblCartMapper.findByUserId(userId);
		//loginSessionとcartsをフロントに渡すModelに追加。
		//(addAttributeメソッドでView側へ渡すオブジェクトデータを格納。(第一引数:変数名/第二引数:オブジェクト名))
		m.addAttribute("loginSession",loginSession);
		m.addAttribute("carts",carts);
		//戻り値として、cart.htmlを返す。
		return "cart";
	}
	
	//マッピングにより、/addにアクセスがあった場合、メソッドが呼び出される。
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {
		
		// 三項演算子を用いて、ユーザーIDを取得。(true ユーザーID : else 仮ユーザーID)
		int userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
		//CartFormに上記で取得したユーザーIDをセット。
		f.setUserId(userId);
		
		//CartFormを引数として、CartDto型のcartをインスタンス化。
		TblCart cart = new TblCart(f);
		//resultを初期化。
		int result = 0;
		//もし、上記で取得したユーザーIDとCartFormのプロダクトIDを引数として、TblcartMapperのfindCountByUserIdAndProuductIdを呼び出し、
		//プロダクトIDが0より大きかった場合、
		if (tblCartMapper.findCountByUserIdAndProuductId(userId, f.getProductId()) > 0) {
			//cartを引数として、TblcartMapperのupdateを呼び出し、resultに代入。
			result = tblCartMapper.update(cart);
			//0以下の場合、cartを引数として、TblcartMapperのinsertを呼び出し、resultに代入。
		} else {
			result = tblCartMapper.insert(cart);
		}
		//もし、resultが0より大きかった場合、
		if (result > 0) {
			//CartDto型Listのcartsをインスタンス化して、TblcartMapperのfindByUserIdを呼び出し、上記で取得したユーザーIDに紐づくカート情報を取得する。
			List<CartDto> carts = tblCartMapper.findByUserId(userId);
			//loginSessionとcartsをフロントに渡すModelに追加。
			//(addAttributeメソッドでView側へ渡すオブジェクトデータを格納。(第一引数:変数名/第二引数:オブジェクト名))
			m.addAttribute("loginSession",loginSession);
			m.addAttribute("carts",carts);
		}
		//戻り値として、cart.htmlを返す。
		return "cart";
	}
	
	//SuppressWarnings(警告を抑制)unchecked(例:キャストによって、不適当なキーや値があるかどうかを照会しようとすると、例外がスローされる場合)
	//マッピングにより、/deleteにアクセスがあった場合、メソッドが呼び出される。
	//return文で返却される文字列そのものが、レスポンスの本体(body)であることを設定。
	@SuppressWarnings("unchecked")
	@RequestMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		//resultを初期化。
		int result = 0;
		//キーとしてString型、値としてString型Listを持つmapをインスタンス化して、checkedIdListとMap.classをJSONからJavaへ変換する。(デシリアライズ)
		Map<String, List<String>> map = gson.fromJson(checkedIdList, Map.class);
		//String型ListのcheckedIdsにmapから取得したcheckedIdListを代入する。
		List<String> checkedIds = map.get("checkedIdList");
		//拡張for文をcheckedIdsを引数として、TblcartMapperのdeleteByIdを実行する。
		//(deleteByIdの引数は、IntegerクラスのparseIntメソッドを用いて、文字列をINT型の値に変換している。)
		//resultに繰り返した回数を足していく。
		for (String id : checkedIds) {
			result += tblCartMapper.deleteById(Integer.parseInt(id));
		}
		//戻り値として、resultの0より大きい数字を返す。(true)
		return result > 0;
	}
}