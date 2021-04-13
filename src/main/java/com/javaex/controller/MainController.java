package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.SparringService;
import com.javaex.service.StoreService;
import com.javaex.vo.BBuyVo;
import com.javaex.vo.ProductVo;
import com.javaex.vo.SearchMatchVo;

@Controller
@RequestMapping(value = "")
public class MainController {
	
	
	@Autowired
	SparringService sparringService;
	@Autowired
	StoreService storeService;
	
	// 메인페이지 추후 리스트 적용해야함
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(Model model ,@RequestParam(value="userno", required=false, defaultValue="0")int userno,
			@ModelAttribute SearchMatchVo searchMatchVo,
			@RequestParam(value="search", required = false, defaultValue = "") String search) {
		System.out.println("[cnt]메인페이지");
		
		
		//by영훈 (04-10)
		//메인페이지 매치리스트 (메인페이지에서 검색기능은 사용안하지만, 오류제거를 위해 searchMatchVo가 사용)
		List<BBuyVo> bBuyList = sparringService.match(userno,searchMatchVo);
		
		model.addAttribute("bBuyList",bBuyList);
		
		//by영훈 (04-13) 세림님 코드 추가함
		//메인페이지 쇼핑리스트

		List<ProductVo> storeList = storeService.storeList(search);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bBuyList",bBuyList);
		map.put("storeList", storeList);
		
		model.addAttribute("map", map);
		
		
		return "main/index";
	}


	
	/*하단 추후 적용 예정*/
	// 로그인 접근 오류
	@RequestMapping(value = "/errorlogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String errorLogin() {
		System.out.println("[cnt]로그인 접근 오류");
		return "main/error_loginform";
	}
	
	// 판매자 접근 오류
	@RequestMapping(value = "/errorseller", method = { RequestMethod.GET, RequestMethod.POST })
	public String errorSeller() {
		System.out.println("[cnt]판매자 접근 오류");
		return "main/error_seller_notice";
	}

	
	//주소검색 api
	@RequestMapping(value = "/address", method = { RequestMethod.GET})
	public String addressSearch() {

		System.out.println("[cnt]주소검색");
		
		return "main/Sample";
		

	}
	
	//주소검색 api
	@RequestMapping(value = "/addressapi", method = { RequestMethod.GET})
	public String addressApi() {

		System.out.println("[cnt]주소검색");
		
		return "main/jusoPopup";
		

	}
	

	
}
