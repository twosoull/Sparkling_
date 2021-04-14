package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GymService;
import com.javaex.vo.BookingVo;
import com.javaex.vo.GymVo;
import com.javaex.vo.SellerVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/mypage/book", method= {RequestMethod.GET , RequestMethod.POST})
public class GymController {
	
	@Autowired
	private GymService gymService;
	
	//체육관 관리 페이지
	@RequestMapping(value="/gym", method= {RequestMethod.GET , RequestMethod.POST})
	public String gymInfo(@RequestParam("no") int sellNo,
						@RequestParam("gymno") int gymNo, Model model) {
		System.out.println("[GymController] gym()");
		
		//탭 gymList + gymVo
		if(gymNo != 0) {
			Map<String, Object> gymMap = gymService.gymInfo(sellNo, gymNo);
			model.addAttribute("gymMap", gymMap);
			
			return "mypage/mypage_resrvation/mypage_gyminfo";
		}
		
		//처음 gymList + gymVo
		Map<String, Object> gymMap = gymService.gymInfo(sellNo);
		model.addAttribute("gymMap", gymMap);
		
		return "mypage/mypage_resrvation/mypage_gyminfo";
	}
	
	//체육관 등록폼
	@RequestMapping(value="/gymaddform", method= {RequestMethod.GET , RequestMethod.POST})
	public String gymAddForm() {
		System.out.println("[GymController] gymAddForm()");
		
		return "mypage/mypage_resrvation/mypage_gymadd";
	}
	
	//체육관 등록
	@RequestMapping(value="/gymadd", method= {RequestMethod.GET , RequestMethod.POST})
	public String gymAdd(@ModelAttribute GymVo gymVo,
					@RequestParam("conve") List<String> conve,
					@RequestParam("file1") MultipartFile mainfile,
					@RequestParam("file2") MultipartFile subfile,
					HttpSession session) {
		System.out.println("[GymController] gymAdd()");
		System.out.println(gymVo);
		System.out.println(conve);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//대관판매자인지 검사
		int bookType = authUser.getBook_type();
		int sellNo = authUser.getSell_no();
		System.out.println("사업자번호>>> "+sellNo);
		
		if(bookType == 1) {			
			gymVo.setSell_no(sellNo);
			
			gymService.gymAdd(gymVo, conve, mainfile, subfile);
		}
		int gymNo = gymVo.getGym_no();
		
		return "redirect:/mypage/book/gym?no="+sellNo+"&gymno="+gymNo;
	}
	
	//체육관 삭제
	@RequestMapping(value="/gymremove", method= {RequestMethod.GET , RequestMethod.POST})
	public String gymRemove(@RequestParam("no") int sellNo,
							@RequestParam("gymno") int gymNo) {
		System.out.println("[GymController] gymRemove() "+sellNo+" / "+gymNo);
		
		//삭제정책 : 체육관 삭제하면 대관 등록한 거 다 삭제할지 대관 등록된 거 있으면 삭제 못하게 할지
		//테이블삭제 순서 con --> gym
		
		return "redirect:/mypage/book/gyminfo?no="+sellNo;
	}
	
	//대관 등록 페이지
	@RequestMapping(value="/bookaddform", method= {RequestMethod.GET , RequestMethod.POST})
	public String bookAddForm(@RequestParam("gymno") int gymNo, Model model) {
		System.out.println("[GymController] bookAddForm()");
		
		//대관 등록할 체육관 정보 불러오기
		GymVo gymVo = gymService.gymOne(gymNo);
		model.addAttribute("gymVo", gymVo);
		
		return "mypage/mypage_resrvation/mypage_bookingadd";
	}
	
	//대관 등록
	@RequestMapping(value="/bookadd", method= {RequestMethod.GET , RequestMethod.POST})
	public String bookAdd(@ModelAttribute BookingVo bookVo) {
		System.out.println("[GymController] bookAdd()>>> "+bookVo);
		
		gymService.bookAdd(bookVo);
		
		return "redirect:/mypage/book/bookaddform?gymno="+bookVo.getGym_no();
	}
	
	//대관 리스트 달력 출력(ajax)
	@ResponseBody
	@RequestMapping(value="/booklist", method= {RequestMethod.GET , RequestMethod.POST})
	public List<BookingVo> bookList(@RequestParam("gymno") int gymno) {
		System.out.println("[GymController] booklist()>>> "+gymno);
		
		List<BookingVo> bookList = gymService.bookList(gymno);
		
		return bookList;
	}
	
	//대관 삭제
	@RequestMapping(value="/bookremove", method= {RequestMethod.GET , RequestMethod.POST})
	public String bookRemove(@RequestParam("bookno") int bookno, @RequestParam("gymno") int gymno) {
		System.out.println("[GymController] bookRemove()>>> "+bookno+"/"+gymno);
		
		gymService.bookRemove(bookno);
		
		return "redirect:/mypage/book/bookmanage?gymno="+gymno;
	}
	
	//대관 관리 페이지
	@RequestMapping(value="/bookmanage", method= {RequestMethod.GET , RequestMethod.POST})
	public String bookManage(@RequestParam("no") int sellNo, 
							@RequestParam("gymno") int gymNo,
							Model model) {
		System.out.println("[GymController] bookManage()>>> "+sellNo+" / "+gymNo);
		
		// gymList + gymVo (탭)
		if(gymNo != 0) {
			Map<String, Object> bookMap = gymService.bookManage(sellNo, gymNo);
			model.addAttribute("bookMap", bookMap);
			
			return "mypage/mypage_resrvation/mypage_bookinglist";
		}
		
		// gymList + gymVo (처음)
		Map<String, Object> bookMap = gymService.bookManage(sellNo);
		model.addAttribute("bookMap", bookMap);
		
		return "mypage/mypage_resrvation/mypage_bookinglist";
	}
	
	//테스트
	@RequestMapping(value="/qqq", method= {RequestMethod.GET , RequestMethod.POST})
	public String qqq() {
		return "mypage/mypage_resrvation/mypage_bookinglist";
	}
	
	//수익관리
	@RequestMapping(value="/profit", method= {RequestMethod.GET , RequestMethod.POST})
	public String profit() {
		return "mypage/mypage_seller/seller_profit";
	}
	
	
	
	/////////////////////////////////////////////////////////////////////
	
	// 대관판매자 계정등록 폼
	@RequestMapping(value = "/bookselleraddform", method = { RequestMethod.GET, RequestMethod.POST })
	public String bookDellerAddForm(Model model, HttpSession session) {
		System.out.println("[cnt] 대관판매자 계정등록폼");
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		model.addAttribute("authUser", authUser);

		return "mypage/mypage_seller/book_seller_add";
	}

	// 대관판매자 대관계정 등록
	@RequestMapping(value = "/bookselleradd", method = { RequestMethod.GET, RequestMethod.POST })
	public String bookSellerAdd(@ModelAttribute SellerVo sellervo, HttpSession session) {
		System.out.println("[cnt] 대관판매자 계정 등록");

		gymService.bookSellerAdd(sellervo);
		System.out.println("돌아옴");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		authUser.setSell_no(sellervo.getSell_no());
		authUser.setBook_type(sellervo.getBook_type());

		return "redirect:/mypage/book/gymaddform";

	}

	// 대관판매자계정관리 페이지
	@RequestMapping(value = "/bookmanageform", method = { RequestMethod.GET, RequestMethod.POST })
	public String bookManageForm(HttpSession session, Model model) {
		System.out.println("[cnt] 판매자계정수정 페이지");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		int sell_no = authUser.getSell_no();

		SellerVo sellervo = gymService.selectUser(sell_no);
		System.out.println("[cnt]판매자정보" + sellervo);
		model.addAttribute("sellervo", sellervo);

		return "mypage/mypage_seller/book_manage";
	}

	// 대관판매자 대관 계정 수정
	@RequestMapping(value = "/booksellermodify", method = { RequestMethod.GET, RequestMethod.POST })
	public String bookSellerModify(@ModelAttribute SellerVo sellervo, HttpSession session) {
		System.out.println("[cnt] 대관판매자 계정 수정" + sellervo);

		gymService.bookSellerModify(sellervo);
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		authUser.setBook_type(sellervo.getBook_type());

		System.out.println("[cnt] 대관판매자 계정 수정완료" + sellervo);
		return "redirect:/mypage/book/gymaddform";
	}

}
