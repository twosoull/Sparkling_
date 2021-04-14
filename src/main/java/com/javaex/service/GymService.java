package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GymDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BBuyVo;
import com.javaex.vo.BookingVo;
import com.javaex.vo.ConVo;
import com.javaex.vo.GymImgVo;
import com.javaex.vo.GymVo;
import com.javaex.vo.ProdimgVo;
import com.javaex.vo.SellerVo;

@Service
public class GymService {

	@Autowired
	private GymDao gymDao;

	@Autowired
	private UserDao userdao;

	// 체육관 관리 : 모든 체육관 정보 불러오기 (탭)
	public Map<String, Object> gymInfo(int sellNo, int gymNo) {
		System.out.println("[GymService] gymInfo()");

		// 소유 체육관 번호 리스트
		List<GymVo> gymList = gymDao.gymnoSelectList(sellNo);
		
		// 체육관 하나 정보 불러오기
		GymVo gymVo = gymDao.gymSelectOne(gymNo);
		List<ConVo> conList = gymDao.conSelectList(gymNo);

		Map<String, Object> gymMap = new HashMap<String, Object>();
		gymMap.put("gymList", gymList);
		gymMap.put("gymVo", gymVo);
		gymMap.put("conList", conList);

		return gymMap;
	}

	// 체육관 관리 : 모든 체육관 정보 불러오기 (처음)
	public Map<String, Object> gymInfo(int sellNo) {
		System.out.println("[GymService] gymInfo()");
		
		// 소유 체육관 번호 리스트
		List<GymVo> gymList = gymDao.gymnoSelectList(sellNo);
		
		// 체육관 하나 정보 (처음)
		List<GymVo> gyminfoList = gymDao.gymSelectList(sellNo);
		GymVo gymVo = gyminfoList.get(0);
		List<ConVo> conList = gymDao.conSelectList(gymVo.getGym_no());
		
		Map<String, Object> gymMap = new HashMap<String, Object>();
		gymMap.put("gymList", gymList);
		gymMap.put("gymVo", gymVo);
		gymMap.put("conList", conList);

		return gymMap;
	}
	
	// 체육관 하나 정보 불러오기
	public GymVo gymOne(int gymNo) {
		System.out.println("[GymService] gymInfo()");

		return gymDao.gymSelectOne(gymNo);
	}

	// 최근에 저장한 체육관 번호 불러오기
	public GymVo gymNo(int sellNo) {
		System.out.println("[GymService] gymNo() " + sellNo);

		return gymDao.gymNoSelectOne(sellNo);
	}

	// 체육관 등록 (체육관, 체크박스값, 파일 등록)
	public void gymAdd(GymVo gymVo, List<String> conve,
					MultipartFile mainfile,
					MultipartFile subfile) {
		System.out.println("[GymService] gymAdd()");

		String con;
		ConVo conVo;

		// 체육관 정보만 insert
		String address = gymVo.getRoadaddress()+" "+gymVo.getAddressdetail();
		gymVo.setGym_address(address);
		
		gymDao.gymInsert(gymVo);

		// 등록한 체육관 번호 가져오기(이거 하려면 insert문에 select key 써야 됨)
		int gymNo = gymVo.getGym_no();
		System.out.println("체육관번호>>> " + gymNo);

		// 주변시설 데이터 6가지를 전부 넣은 후
		// 먼저 0 다 넣는 게 아니라 유무에 따라 0, 1 넣는 걸로 수정 필요 
		// --> if문으로 다 해보려고 했는데 6가지가 다 안 들어가서 원상복구.
		
		ConVo conVo1 = new ConVo(gymNo, "주차장");
		ConVo conVo2 = new ConVo(gymNo, "샤워실");
		ConVo conVo3 = new ConVo(gymNo, "수건");
		ConVo conVo4 = new ConVo(gymNo, "락커");
		ConVo conVo5 = new ConVo(gymNo, "글러브");
		ConVo conVo6 = new ConVo(gymNo, "운동복");

		gymDao.conInsert(conVo1);
		gymDao.conInsert(conVo2);
		gymDao.conInsert(conVo3);
		gymDao.conInsert(conVo4);
		gymDao.conInsert(conVo5);
		gymDao.conInsert(conVo6);

		for (int i = 0; i < conve.size(); i++) {
			con = conve.get(i);
			
			if ("park".equals(con)) {
				conVo = new ConVo(gymNo, "주차장", 1);
				gymDao.conUpdate(conVo);
				
			}  else if ("shower".equals(con)) {
				conVo = new ConVo(gymNo, "샤워실", 1);
				gymDao.conUpdate(conVo);

			} else if ("towel".equals(con)) {
				conVo = new ConVo(gymNo, "수건", 1);
				gymDao.conUpdate(conVo);

			} else if ("locker".equals(con)) {
				conVo = new ConVo(gymNo, "락커", 1);
				gymDao.conUpdate(conVo);

			} else if ("glove".equals(con)) {
				conVo = new ConVo(gymNo, "글러브", 1);
				gymDao.conUpdate(conVo);

			} else if ("wear".equals(con)) {
				conVo = new ConVo(gymNo, "운동복", 1);
				gymDao.conUpdate(conVo);

			} 

		}

		/////////////////////////////체육관 이미지 처리
		/*
		System.out.println("[file original name] --> " + file.getOriginalFilename());

		///// DB에 저장할 파일정보 수집/////

		// 서버 파일패스(경로)
		String saveDir = "C:\\javaStudy\\upload";

		// 오리지널 파일명
		String orgName = file.getOriginalFilename();

		// 확장자명
		String exName = orgName.substring(orgName.lastIndexOf("."));

		// 서버 저장파일명 + 확장자명 --> logoFile에 해당
		String imgFile = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

		// 서버 저장경로+파일명
		String filePath = saveDir + "\\" + imgFile;

		// 서버 하드디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);

			bos.write(fileData);
			bos.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		GymImgVo gimgVo = new GymImgVo(gymNo, imgFile, orgName);
		gymDao.gimgInsert(gimgVo);
		*/

		try {
			// 메인파일 저장
			String mainfilePath = "C:\\javaStudy\\upload"; // 설정파일로 뺀다.
			String mainfileOriginName = mainfile.getOriginalFilename();

			System.out.println("기존 파일명 : " + mainfileOriginName);

			String mainsavename = System.currentTimeMillis() + UUID.randomUUID().toString() + mainfileOriginName; // 파일명
			String mainfileFullPath = mainfilePath + "/" + mainsavename; // 파일 전체 경로
			System.out.println("originalFilename:" + mainsavename + ", fileFullPath:" + mainfileFullPath);

			System.out.println("메인이미지 등록");
			GymImgVo gimgVo = new GymImgVo();
			gimgVo.setGym_img_savename(mainsavename);
			gimgVo.setGym_img_name(mainfileOriginName);
			gimgVo.setGym_img_type("main");
			System.out.println("메인이미지 등록 체육관번호" + gymNo);
			gimgVo.setGym_no(gymNo);

			System.out.println("[service]체육관이미지등록 출발" + gimgVo);
			gymDao.gimgInsert(gimgVo);
			
			// 서브파일 저장
			String subfilePath = "C:\\javaStudy\\upload"; // 설정파일로 뺀다.
			String subfileOriginName = subfile.getOriginalFilename();

			System.out.println("기존 파일명 : " + subfileOriginName);

			String subsavename = System.currentTimeMillis() + UUID.randomUUID().toString() + subfileOriginName; // 파일명
			String subfileFullPath = subfilePath + "/" + subsavename; // 파일 전체 경로
			System.out.println("originalFilename:" + subsavename + ", fileFullPath:" + subfileFullPath);

			System.out.println("메인이미지 등록");
			GymImgVo gimgVo2 = new GymImgVo();
			gimgVo2.setGym_img_savename(subsavename);
			gimgVo2.setGym_img_name(subfileOriginName);
			gimgVo2.setGym_img_type("sub");
			System.out.println("메인이미지 등록 체육관번호" + gymNo);
			gimgVo2.setGym_no(gymNo);

			System.out.println("[service]체육관이미지등록 출발" + gimgVo);
			gymDao.gimgInsert(gimgVo);

		} catch (Exception e) {
			System.out.println("postTempFile_ERROR======>");

			e.printStackTrace();
		}
	}

	// 대관 등록
	public void bookAdd(BookingVo bookVo) {
		System.out.println("[GymService] bookAdd()");
		gymDao.bookInsert(bookVo);
	}

	// 대관 관리 페이지 (처음) (체육관 리스트 + 체육관 정보 + 대관 등록 정보 리스트)
	public Map<String, Object> bookManage(int sellNo) {
		System.out.println("[GymService] bookAddForm()");

		// 소유 체육관 리스트
		List<GymVo> gymList = gymDao.gymnoSelectList(sellNo);
		
		//체육관 정보 + 대관 목록
		List<GymVo> gyminfoList = gymDao.gymSelectList(sellNo);
		
		//대관리스트0번 체육관 정보
		GymVo gymVo1 = gyminfoList.get(0);
		int gymNo = gymVo1.getGym_no();
		
		//대관 목록
		List<BookingVo> bookList = gymDao.bookSelectList(gymNo);
	
		System.out.println("[서비스] 리스트0번체육관> "+gymVo1);
		System.out.println("[서비스] 대관목록> " + bookList);

		//체육관 정보 + 대관 리스트
		if (bookList != null) {
			gymVo1.setBookingList(bookList);
		}
		
		//대관 리스트에 예약자 있으면 예약자명 넣음
		for(int i = 0 ; i < bookList.size(); i++) {
			int bookNo = bookList.get(i).getBooking_no();
			BookingVo bVo = bookList.get(i);
			
			List<BBuyVo> bbuyList= gymDao.selectBBuyUser(bookNo);
			
			if(!bbuyList.isEmpty()) {
				for(int j = 0 ; j <bbuyList.size(); j++) {
					if(bVo.getUser_name1() == null) {
						bVo.setUser_name1(bbuyList.get(j).getUser_name());  
						
					}else if(bVo.getUser_name2() == null) {
						bVo.setUser_name2(bbuyList.get(j).getUser_name());  
					}
						
				}
				
			}
			
		}
		
		Map<String, Object> bookMap = new HashMap<String, Object>();
		bookMap.put("gymList", gymList);
		bookMap.put("gymVo", gymVo1);

		return bookMap;
	}
	
	// 대관 관리 페이지 탭 (체육관 리스트 + 체육관 정보 + 대관 등록 정보 리스트)
	public Map<String, Object> bookManage(int sellNo, int gymNo) {
		System.out.println("[GymService] bookAddForm()");

		// 소유 체육관 리스트
		List<GymVo> gymList = gymDao.gymnoSelectList(sellNo);
		
		//체육관 정보 + 대관 목록
		GymVo gymVo = gymDao.gymSelectOne(gymNo);
		
		//대관 목록
		List<BookingVo> bookList = gymDao.bookSelectList(gymNo);

		//체육관 정보 + 대관 리스트
		if (bookList != null) {
			gymVo.setBookingList(bookList);
		}
		
		//대관 리스트에 예약자 있으면 예약자명 넣음
		for(int i = 0 ; i < bookList.size(); i++) {
			int bookNo = bookList.get(i).getBooking_no();
			BookingVo bVo = bookList.get(i);
			
			List<BBuyVo> bbuyList= gymDao.selectBBuyUser(bookNo);
			
			if(!bbuyList.isEmpty()) {
				for(int j = 0 ; j <bbuyList.size(); j++) {
					if(bVo.getUser_name1() == null) {
						bVo.setUser_name1(bbuyList.get(j).getUser_name());  
						
					}else if(bVo.getUser_name2() == null) {
						bVo.setUser_name2(bbuyList.get(j).getUser_name());  
					}
						
				}
				
			}
			
		}
		
		Map<String, Object> bookMap = new HashMap<String, Object>();
		bookMap.put("gymList", gymList);
		bookMap.put("gymVo", gymVo);

		return bookMap;
	}

	// 대관 리스트 출력
	public List<BookingVo> bookList(int gymno) {
		System.out.println("[GymService] bookList()");
		return gymDao.bookSelectList(gymno);

	}

	// 대관 삭제
	public void bookRemove(int bookno) {
		System.out.println("[GymService] bookRemove()");

		gymDao.bookDelete(bookno);
	}
	/////////////////////////////////////////////////

	// 대관판매자계정등록
	public void bookSellerAdd(SellerVo sellervo) {
		String book_sell_address = sellervo.getRoadaddress() + sellervo.getAddressdetail();
		sellervo.setBook_sell_address(book_sell_address);

		userdao.sellerBookInsert(sellervo);
	}

	// 판매자 정보 가져오기
	public SellerVo selectUser(int sell_no) {
		return userdao.sellerSelectOne(sell_no);
	}

	// 대관 판매자 계정 수정
	public void bookSellerModify(SellerVo sellervo) {
		String book_sell_address = sellervo.getRoadaddress() + sellervo.getAddressdetail();
		sellervo.setBook_sell_address(book_sell_address);

		userdao.bookSellerUpdate(sellervo);
	}
}
