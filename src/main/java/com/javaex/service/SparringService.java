package com.javaex.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.javaex.dao.AlarmDao;
import com.javaex.dao.SparringDao;
import com.javaex.vo.AlarmContentVo;
import com.javaex.vo.AlarmVo;
import com.javaex.vo.BBuyVo;
import com.javaex.vo.BookingVo;
import com.javaex.vo.ConvenienceVo;
import com.javaex.vo.DayVo;
import com.javaex.vo.EventVo;
import com.javaex.vo.GymImgVo;
import com.javaex.vo.GymVo;
import com.javaex.vo.MatchScoreVo;
import com.javaex.vo.ProfileVo;
import com.javaex.vo.RecordVo;
import com.javaex.vo.SearchMatchVo;
import com.javaex.vo.UserVo;

@Service
public class SparringService {

	/*
	 * 체크박스는 list로 받고 1. 복싱 2.킥복싱 3.종합격투기 4.주짓수
	 */
	@Autowired
	SparringDao sparringDao;

	@Autowired
	AlarmDao alarmDao;

	public int profileWrite(ProfileVo profileVo, String[] eventName, RecordVo recordVo, int userNo) {
		System.out.println("[Service] : profileWrite()");
		/********* 프로필 인스트 **********/
		System.out.println("vo :" + profileVo);
		// profile insert + 셀렉트 키로 profileNo 생성

		String major = profileVo.getMajor();
		System.out.println("major" + major);
		if (major == null) {
			profileVo.setMajor("없음");
		}
		String word = profileVo.getWord();
		System.out.println("word" + word);
		if (word == "") {
			profileVo.setWord("잘 부탁드립니다");
		}

		sparringDao.insertProfile23(profileVo);
		System.out.println("vo2 :" + profileVo);

		// selectKey로 생성된 profileNo값 받기
		
		int profileNo = profileVo.getProfileNo();

		/********* 주종목(주특기) event 인서트 ********/
		// 질문하기 dao에서 반복인지 서비스인지 먼저 서비스로해봄 (질문해볼것)
		int[] eventName2 = new int[eventName.length];

		for (int i = 0; i < eventName.length; i++) {

			eventName2[i] = Integer.parseInt(eventName[i]);

			System.out.println(eventName2[i]);

			EventVo eventVo = new EventVo();

			eventVo.setProfileNo(profileNo);
			eventVo.setEventName(eventName2[i]);

			sparringDao.insertEvent(eventVo);

		}

		/*********** 공식기록 record 인서트 *********/
		System.out.println("확인" + recordVo.getRecordList().get(0));

		if (recordVo.getRecordList().get(0) != null) {
			for (int i = 0; i < recordVo.getRecordList().size(); i++) {
				System.out.println("??");
				RecordVo rVo = recordVo.getRecordList().get(i);

				// 받아온 profileNo 넣기
				rVo.setProfileNo(profileNo);
				
				
				if(rVo.getRecordName() == null) {
					System.out.println("네임");
					rVo.setRecordName(" ");
				}
				
				if(rVo.getRecordType().equals("대회분류")) {
					System.out.println("대회분류");
					rVo.setRecordType("");
				}
				
				if(rVo.getRecordDate() == null) {
					System.out.println("출전연도 null");
					rVo.setRecordDate(" ");
				}
				if(rVo.getRecordDate().equals("출전연도")) {
					System.out.println("출전연도");
					rVo.setRecordDate("");
				}
				
				if(rVo.getRecordMatch().equals("순위")) {
					System.out.println("순위");
					rVo.setRecordMatch("");
				}
				
				if(!rVo.getRecordEvent().equals("종목")) {
					System.out.println("종목");
				System.out.println(rVo.getRecordName());
				sparringDao.insertRecord(rVo);
				}

			}
		}
		
		/**알고리즘 넣기***/
		double userLevel = algo(profileVo, recordVo,userNo);
		
		UserVo userVo = new UserVo();
		
		userVo.setUser_no(userNo);
		userVo.setUser_level(userLevel);
		int count = sparringDao.updateUserLevel(userVo);
		
		return count;
	}

	// 처음 프로필을 들어갈때 날짜정보를 뿌려줘야하기때문에 날짜만 빼온다
	public List<ProfileVo> profileWriteForm(int userNo) {
		// TODO Auto-generated method stub
		System.out.println("[Service] : profileWriteForm");

		return sparringDao.selectListProfile(userNo);

	}

	// 프로필생성날짜로 vo 뿌리기
	public Map<String, Object> selectDate(int rownum, int userNo) {
		System.out.println("[Service] : selectDate");
		System.out.println(rownum);
		System.out.println(userNo);
		Map<String, Object> profileMap = new HashMap<String, Object>();
		profileMap.put("rownum", rownum);
		profileMap.put("userNo", userNo);

		System.out.println("rownum =" + rownum);
		System.out.println("userNo =" + userNo);

		/********************* 날짜에 대한 값들 보내기 ************************/

		// 프로필
		ProfileVo profileVo = sparringDao.selectOneProfile(profileMap);
		System.out.println(profileVo);

		int profileNo = profileVo.getProfileNo();
		// 주종목
		List<EventVo> eventList = sparringDao.selectListEvent(profileNo);

		// 공식기록
		List<RecordVo> recordList = sparringDao.selectListRecord(profileNo);

		// 하나에 묶기
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("profileVo", profileVo);
		pMap.put("eventList", eventList);
		pMap.put("recordList", recordList);
		
		System.out.println(recordList);
		return pMap;
	}

	public List<GymVo> rent() {
		System.out.println("[Service] : rent()");

		List<GymVo> gymList = sparringDao.selectListGym();
		DecimalFormat formatter = new DecimalFormat("###,###");

		System.out.println(gymList);

		for (int i = 0; i < gymList.size(); i++) {

			// 평균 비용의 1인분 값구하기
			int avgPrice = gymList.get(i).getAvgPrice();

			int avgOnePrice = (int) avgPrice / 2;

			System.out.println("avgOnePrice =" + avgOnePrice);
			// 금액 사이에 쉼표넣기
			gymList.get(i).setAvgOnePrice(avgOnePrice);

			String money = formatter.format(avgPrice);
			String moneyHalf = formatter.format(avgOnePrice);

			gymList.get(i).setMoney(money);
			gymList.get(i).setMoneyHalf(moneyHalf);

			System.out.println(money);
			System.out.println(moneyHalf);

			// 주소의 2번째 값만 가지고 하프주소 넣어주기

			/*
			 * 주소 api 끝나면 살리기 String[] addressHalf=
			 * gymList.get(i).getGym_address().split("\\s");
			 * 
			 * if(addressHalf.length <2) { gymList.get(i).setAddressHalf(addressHalf[1]);
			 * }else { gymList.get(i).setAddressHalf(addressHalf[0]); }
			 */
			gymList.get(i).setAddressHalf(gymList.get(i).getGym_address());
			// 여기까지포함

			System.out.println(gymList.get(i).getAddressHalf());

		}

		return gymList;

	}

	public GymVo rentDetail(int gymNo) {
		// TODO Auto-generated method stub
		System.out.println("[Service] : rentDetail()");

		// 1.gymVo를 받는다
		GymVo gymVo = sparringDao.selectOneGym(gymNo);
		System.out.println("gymVo =" + gymVo);

		// 1end
		// 2.gymImgList를 받는다
		List<GymImgVo> gymimgList = sparringDao.selectListGymImg(gymNo);
		System.out.println("gymimgVo = " + gymimgList);

		// 2end

		// 3. 날짜 뿌리기전 오늘기준으로 10일째의 날짜와 요일을 구한다
		Calendar cal = Calendar.getInstance();
		String[] weekDay = { "일", "월", "화", "수", "목", "금", "토" };

		// 뿌리기용 날짜 리스트
		List<DayVo> dayList = new ArrayList<DayVo>();

		// 오늘부터 10일 날짜구하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		String dd = sdf.format(cal.getTime());
		
		String[] dd1 = dd.split("/");
		
		String dayA = dd1[2];
		
		int k= 0 ;
		int month = cal.get(Calendar.MONTH) + 1;
		for (int i = 0; i < 10; i++) {
			int year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH) + i;
			int dayofWeek = cal.get(Calendar.DAY_OF_WEEK) + i;
			
			
			int d1 = cal.get(Calendar.DAY_OF_MONTH);
			cal.set(year,month-1,d1);
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			if(day == lastDay+1) {
				k = 0;
				month = month + 1;
			}
	        if(day > lastDay) {
	        	k += 1;
	        	day = 0;
	        	day += k;
	        }
	        
	        	
			if (dayofWeek > 7) {
				if (dayofWeek > 14) {
					dayofWeek = dayofWeek - 14;
				} else {
					dayofWeek = dayofWeek - 7;
				}
			}
			String today = "";
			switch (dayofWeek) {
			case 1:
				today = "일";
				break;
			case 2:
				today = "월";
				break;
			case 3:
				today = "화";
				break;
			case 4:
				today = "수";
				break;
			case 5:
				today = "목";
				break;
			case 6:
				today = "금";
				break;
			case 7:
				today = "토";
				break;
			}
			
			
			System.out.println(i + "=" + year + "." + month + "." + day + "." + today);

			/*********** 변경할수있음 **************/
			
			// day가 1일일경우 숫자상 1로 찍히기 때문에 10보다 작은수는 0을 붙여 01 02 식으로바꿔준다
			String dayString = "";
			
			String date = "";
			
			// 이렇게하는 이유는 검색을 03월 이렇게 되는데 month는 그냥 3 이기때문
			if (month < 10) {
				if(day <10) {
					dayString = "0"+day;
					
					date = year + "-0" + month + "-" + dayString;
				}else {
					
					date = year + "-0" + month + "-" + day;
				}
			} else {
				if(day <10) {
					dayString = "0"+day;
					
					date = year + "-0" + month + "-" + dayString;
				}else {
					
					date = year + "-" + month + "-" + day;
				}
			}
			
			DayVo dayVo = new DayVo(year, month, day, today, date);
			
			
			
			dayList.add(dayVo);
		}

		// 오늘부터 10일 날짜구하기 end
		// 4.오늘의 날짜로 대관 정보가 있는지 확인한후 하나의 vo를받는다
		// (다른날짜에 대한 정보는 ajax로 처리하기 때문)
		System.out.println("오늘 = " + dayList.get(0));
		String day1 = dayList.get(0).getDate();

		// 셀렉트 웨어절 구문이 바뀔수 있음 ***************************************
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("day", day1);
		map.put("gymNo", gymNo);

		List<BookingVo> bookingList = sparringDao.selectListBooking(map);

		// 5.편의시설 리스트

		List<ConvenienceVo> conList = sparringDao.selectListCon(gymNo);

		// gymimgList, dayList, bookingList, conList
		gymVo.setGymimgList(gymimgList);
		gymVo.setDayList(dayList);
		gymVo.setBookingList(bookingList);
		gymVo.setConList(conList);

		return gymVo;
	}

	public GymImgVo gymImgOne(int gymImgNo) {
		System.out.println("[Service] : gymImgOne");

		return sparringDao.selectOneGymImg(gymImgNo);

	}

	public List<BookingVo> bookingList(String date, int gymNo) {
		System.out.println("[Service] : bookingList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("day", date);
		map.put("gymNo", gymNo);

		List<BookingVo> bookingList = sparringDao.selectListBooking(map);
		return bookingList;
	}

	// 결제하기
	public Map<String, Object> payment(int bookingNo, int userNo, int subnum) {
		System.out.println("[Service] : payment()");
		// DecimalFormat formatter = new DecimalFormat("###,###");
		BookingVo bookingVo = sparringDao.selectOneBooking(bookingNo);

		System.out.println(bookingVo);

		// int price = bookingVo.getBooking_price();

		UserVo userVo = sparringDao.selectAddressUser(userNo);

		System.out.println(userVo);

		// vo를 Map으로 묶는다
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("bookingVo", bookingVo);
		map.put("userVo", userVo);

		return map;

	}

	public void pay(BBuyVo bBuyVo, int subnum, int bbuyno, int booking_no) {
		System.out.println("[Service] : pay()");
		int num = bBuyVo.getB_buy_price().lastIndexOf(".");

		String str = bBuyVo.getB_buy_price().substring(0, num);

		System.out.println(str);

		int price = Integer.parseInt(str);
		bBuyVo.setPrice(price);

		System.out.println(bBuyVo.getBooking_no());
		// 먼저 bbuy를 셀렉트 문으로 꺼내서

		// subNum이 0이면 시합등록자 1이면 신청자
		if (subnum == 0) {
			bBuyVo.setB_buy_player_state("시합등록자");

			bBuyVo.setB_buy_state("결제완료");
			sparringDao.insertBBuy(bBuyVo);

			/** 관장에게 알람 **/
			
			AlarmVo alarmVo = sparringDao.selectSellno(booking_no);
			System.out.println(alarmVo.getUser_no());
			System.out.println(alarmVo.getAlarm_title());

			AlarmContentVo alVo = new AlarmContentVo();
			
			alarmVo.setAlarm_content(alVo.getGym_reservation());
			
			System.out.println("셀넘버 :" + alarmVo.getSell_no());
			alarmDao.inserbookingAlarm(alarmVo); //o
			
			/* 시합등록자에게 알람 */
			BBuyVo bbuyVo = sparringDao.selectBBUYSC(bBuyVo.getB_buy_no());
			int UN = bbuyVo.getUser_no();
			alarmVo.setAlarm_title(alarmVo.getAlarm_title());
			alarmVo.setUser_no(UN);
			alarmVo.setAlarm_content(alVo.getPayment_complete());

			alarmDao.inserbookingAlarm(alarmVo);

			/***/

		} else if (subnum == 1) {
			bBuyVo.setB_buy_player_state("신청자");
			bBuyVo.setB_buy_state("결제완료");

			sparringDao.insertBBuy(bBuyVo);

			/* 신청자가 대관해서 결제하기 등록자에게 알람발송 */
			AlarmContentVo alarmContentVo = new AlarmContentVo();

			AlarmVo alVo = sparringDao.selectSellno(booking_no);
			AlarmVo userVo = alarmDao.bbuyuserNo(bbuyno);

			int uNo = userVo.getUser_no();

			alVo.setAlarm_content(alarmContentVo.matching_competition);
			alVo.setUser_no(uNo);

			alarmDao.insertMatchAlarm(alVo);

			/** 신청자가 대관해서결제하기 신청자에게 결제완료 알람발송 **/

			userVo = alarmDao.bbuyuserNo(bBuyVo.getB_buy_no());
			uNo = userVo.getUser_no();

			alVo.setAlarm_content(alarmContentVo.payment_complete);
			alVo.setUser_no(uNo);

			alarmDao.inserbookingAlarm(alVo);

			/**/
			AlarmVo sellVo = sparringDao.selectSellno(booking_no);
			sellVo.setAlarm_content(alarmContentVo.gym_reservation);

			alarmDao.inserbookingAlarm(sellVo);

		}

		int bookingNo = bBuyVo.getBooking_no();
		// 대관한 부킹정보는 예약으로 변경
		if (subnum == 0) {
			// 시합등록자가 대관할경우 예약대기중
			sparringDao.updateBooking(bookingNo);
		} else if (subnum == 1) {
			// 신청자가 대관할 경우 예약중
			sparringDao.updateBooking2(bookingNo);
		}

		// 신청자의 경우 등록자에게 부킹번호 주기
		if (subnum == 1) {
			BBuyVo vo = new BBuyVo();
			vo.setBooking_no(bookingNo);
			vo.setB_buy_no(bbuyno);

			sparringDao.updateBBuy(vo);

			// 시합등록자에게 알람보내기

		}

	}
	
	// by 영훈 
	// 스파링 리스트 + 스파링 리스트의 검색기능 (searchMatchVo 사용)
	// 스파링 리스트(21-03-25) 나에게맞는 매칭 (21-03-30) 검색(04-10)
	public List<BBuyVo> match(int userNo, SearchMatchVo searchMatchVo) {
		System.out.println("[Service] : match");
		
		
		//검색으로오는 ex) 09:00~12:00 를 
		//booking테이블의 booking_start와 booking_end 로 나눠주기
		System.out.println(searchMatchVo);
		if(!StringUtils.isEmpty(searchMatchVo.getTime())) {
			
			System.out.println("시간 나누기");
			if(!"".equals(searchMatchVo.getTime())) {
				System.out.println("시간 나누기2");
			
				String time =  searchMatchVo.getTime();
				
				Map<String,String> timeMap = takeStartEndTime(time);
				
				searchMatchVo.setBooking_start(timeMap.get("bookingStart"));
				searchMatchVo.setBooking_end(timeMap.get("bookingEnd"));
				System.out.println(searchMatchVo.getBooking_start());
				System.out.println(searchMatchVo.getBooking_end());
			}
		}
		//booking테이블의 booking_start와 booking_end 로 나눠주기 end
		System.out.println("통과");
		
		List<BBuyVo> bBuyList = null;
		if(userNo == 0) {
			//일반 매칭리스트 or 스파링 일반 검색 기능
			bBuyList = sparringDao.selectBBuyList(searchMatchVo);
		}else {
			//내게 맞는 매칭 검색은 userNo가 있다
			
			UserVo userVo = sparringDao.selectOneUserVo(userNo);
			BBuyVo leVo = new BBuyVo();
			leVo.setUser_no(userNo);
			double userLevel = 0;
			double highLevel = 0;
			double rowLevel = 0;
			// 몇부터 몇으로 할지 정하기 총 3가지 까지는 만들어주기
			if(userVo != null) {
				
				userLevel = userVo.getUser_level();
				System.out.println("유저레벨 :" + userLevel);
				highLevel = userLevel + 100; 
				rowLevel = userLevel - 100;
				
				if(rowLevel <0 ) {
					rowLevel = 0;
				}
				leVo.setHighLevel(highLevel);
				leVo.setRowLevel(rowLevel);
				System.out.println("하이레벨 :" + highLevel);
				System.out.println("로우레벨 :" + rowLevel);
				bBuyList = sparringDao.selectBBuyList(leVo);
				System.out.println("1단계 선택 = " + bBuyList);
				
				//레벨에 맞는 사용자가 없을 경우 다시 검색
				if(bBuyList == null) {
					highLevel = userLevel + 200; 
					rowLevel = userLevel - 200;
					
					if(rowLevel <0 ) {
						rowLevel = 0;
					}
					leVo.setHighLevel(highLevel);
					leVo.setRowLevel(rowLevel);
				
					bBuyList = sparringDao.selectBBuyList(leVo);
					System.out.println("2단계 선택 =" + bBuyList);
					
					//한번더 null 일경우 다시 넓게 검색
					if(bBuyList == null) {
						highLevel = userLevel + 500; 
						rowLevel = userLevel - 500;
						
						if(rowLevel <0 ) {
							rowLevel = 0;
						}
						leVo.setHighLevel(highLevel);
						leVo.setRowLevel(rowLevel);
					
						bBuyList = sparringDao.selectBBuyList(leVo);
						System.out.println("3단계 선택" + bBuyList);
						
						
					}
					
				}
				
				
				
				
				
			}
			
		}
		
		if(bBuyList != null) {
		for (int i = 0; i < bBuyList.size(); i++) {
			int profileNo = bBuyList.get(i).getProfile_no();

			/*
			 * api 주소 부분 생기면 수정해야할 부분 String[] addressHalf=
			 * bBuyList.get(i).getGym_address().split("\\s");
			 * 
			 * bBuyList.get(i).setAddressHalf(addressHalf[1]);
			 * 
			 */
			bBuyList.get(i).setAddressHalf(bBuyList.get(i).getGym_address());
			// 여기까지임
			List<EventVo> eventList = sparringDao.selectListEvent(profileNo);

			bBuyList.get(i).setEventList(eventList);

		}
		}
		return bBuyList;

	}

	// 대관구매를 하지 않는 경우 사용
	public BBuyVo insertBBuy(int bookingNo, int subNum, int userNo, ProfileVo profileVo) {
		System.out.println("[Service]: insertBBuy");
		BBuyVo bBuyVo = new BBuyVo();

		if (subNum == 1) {

			bBuyVo.setB_buy_player_state("신청자");
			// 결제안하고 신청한 상태에서 상대가 선택하면 결제

		} else {

			bBuyVo.setB_buy_player_state("시합등록자");

		}
		int profileNo = profileVo.getProfileNo();
		String event = profileVo.getProfileGymEvent();
		String time = profileVo.getTime();
		String day = profileVo.getDay();

		
		String address = profileVo.getAddress();

		bBuyVo.setBooking_no(bookingNo);
		bBuyVo.setUser_no(userNo);
		bBuyVo.setProfile_no(profileNo);
		bBuyVo.setB_buy_event(event);
		bBuyVo.setB_buy_time(time);
		bBuyVo.setB_buy_day(day);
		bBuyVo.setB_buy_address(address);

		System.out.println("event = " + event);
		System.out.println("time = " + time);
		System.out.println("day  = " + day);
		System.out.println("address = " + address);

		sparringDao.insertBBuy2(bBuyVo);

		return bBuyVo;
	}

	public Map<String, Object> MatchDetail(int bBuyNo, int userNo, int sessionuser, int bookingno) {
		System.out.println("[Service] : bBuyNo");

		Map<String, Object> map = new HashMap<String, Object>();
		BBuyVo bBuyVo = new BBuyVo();
		bBuyVo.setB_buy_no(bBuyNo);
		bBuyVo.setUser_no(userNo);

		// 시합등록자구하기
		BBuyVo vo = sparringDao.selectOneBBuy(bBuyVo);
		int event = 0;
		String WC = "";

		// 체급구하기
		if (vo != null) {
			if (vo.getBooking_no() == 0) {
				int profileNo1 = vo.getProfile_no();
				ProfileVo profileVo = sparringDao.selectProfileEvent(profileNo1);

				String gymEvent = profileVo.getProfileGymEvent();
				if (gymEvent.equals("복싱")) {
					event = 1;
				} else if (gymEvent.equals("킥복싱")) {
					event = 2;
				} else if (gymEvent.equals("종합격투기")) {
					event = 3;
				} else if (gymEvent.equals("주짓수")) {
					event = 4;
				}
				int weight = Integer.parseInt(vo.getWeight());
				WC = weightClass(event, weight);

			} else {
				BookingVo bookingVo = sparringDao.selectOneBooking(vo.getBooking_no());
				String gymEvent = bookingVo.getGym_event();
				if (gymEvent.equals("복싱")) {
					event = 1;
				} else if (gymEvent.equals("킥복싱")) {
					event = 2;
				} else if (gymEvent.equals("종합격투기")) {
					event = 3;
				} else if (gymEvent.equals("주짓수")) {
					event = 4;
				}
				int weight = Integer.parseInt(vo.getWeight());
				WC = weightClass(event, weight);

			}
		}
		System.out.println(vo);
		System.out.println("시합등록자 :" + WC);
		if (vo != null) {
			vo.setWeightC(WC);
			// 시합등록자 구하기END
		}
		// 시합 등록자의 승률구하기
		BBuyVo scoreVo = sparringDao.selectOneMatchScore(userNo);
		if (scoreVo != null) {

			double sumScore = scoreVo.getSumScore();
			double sumWin = scoreVo.getSumWin();
			System.out.println(sumScore + "," + sumWin);

			int rate = (int) ((sumWin / sumScore) * 100);
			System.out.println("승률 : " + rate);
			if (vo != null) {
				vo.setRate(rate);
				// 시합 등록자의 승률구하기END
				vo.setScoreCount(scoreVo.getScoreCount());
			}
		}

		/** 경력리스트,주특기 리스트 구하기 **/
		int profileNo = vo.getProfile_no();

		List<EventVo> eventList = sparringDao.selectListEvent(profileNo);

		// 경력리스트
		List<RecordVo> recordList = sparringDao.selectListRecord(profileNo);

		if (vo != null) {
			vo.setEventList(eventList);
			vo.setRecordList(recordList);
		}
		// *****상대방구하기****//
		// 상대방을 페이지에서 받아올수있는 bookingno로 구하지않고 불러와서 구하는 이유는
		// 대관구매하지 않은 시합등록자는 bookingno가 없어 상대를 불러올수없고
		// 두번째로 대관구매하지않은 시합등록자에게 시합신청할때 대관구매하는
		// 신청자의 bookingno를 시합등록자에게 update로 넣을 예정이기에
		// 누군가 신청한 후에 상대가 표시 되기때문에 불러와서 넣는 구조로한다
		// if(vo.getBooking_no() != 0) {

		int bookingNo = vo.getBooking_no();
		System.out.println(bookingNo);

		List<BBuyVo> bbuyList = sparringDao.selectBBuyList2(bookingNo);

		System.out.println(bbuyList);

		/* 주특기와 공식기록 찾기 */
		if (!bbuyList.isEmpty()) {

			BBuyVo vo2 = bbuyList.get(0);

			int event1 = 0;
			String WC2 = "";

			int profileNo2 = bbuyList.get(0).getProfile_no();

			List<EventVo> eventList2 = sparringDao.selectListEvent(profileNo2);
			List<RecordVo> recordList2 = sparringDao.selectListRecord(profileNo2);

			bbuyList.get(0).setEventList(eventList2);
			bbuyList.get(0).setRecordList(recordList2);
			// 신청자의 승률구하기
			int userNo2 = bbuyList.get(0).getUser_no();

			BBuyVo scoreVo2 = sparringDao.selectOneMatchScore(userNo2);

			if (scoreVo2 != null) {
				double sumScore2 = scoreVo2.getSumScore();
				double sumWin2 = scoreVo2.getSumWin();
				System.out.println("신청자:" + sumScore2 + "," + sumWin2);

				int rate2 = (int) ((sumWin2 / sumScore2) * 100);
				System.out.println("신청자 승률 : " + rate2);

				bbuyList.get(0).setRate(rate2);
				// 시합 등록자의 승률구하기END
			}
			map.put("bBuyList", bbuyList);

			// 체급구하기
			BookingVo bookingVo2 = sparringDao.selectOneBooking(vo2.getBooking_no());
			String gymEvent = bookingVo2.getGym_event();
			if (gymEvent.equals("복싱")) {
				event1 = 1;
			} else if (gymEvent.equals("킥복싱")) {
				event1 = 2;
			} else if (gymEvent.equals("종합격투기")) {
				event1 = 3;
			} else if (gymEvent.equals("주짓수")) {
				event1 = 4;
			}
			int weight2 = Integer.parseInt(vo2.getWeight());
			WC2 = weightClass(event1, weight2);

			System.out.println(vo);
			System.out.println("신청자 :" + WC2);
			vo2.setWeightC(WC2);
			// 시합등록자 구하기END
		}

		/********* 대관정보구하기 **********/
		if (bookingNo != 0) {
			BookingVo bookingVo = sparringDao.selectOneBooking(bookingNo);

			int gymNo = bookingVo.getGym_no();
			// 1.gymVo를 받는다
			GymVo gymVo = sparringDao.selectOneGym(gymNo);
			System.out.println("gymVo =" + gymVo);

			// 1end
			// 2.gymImgList를 받는다

			List<GymImgVo> gymimgList = sparringDao.selectListGymImg(gymNo);
			System.out.println("gymimgVo = " + gymimgList);

			// 2end
			//
			// 5.편의시설 리스트

			List<ConvenienceVo> conList = sparringDao.selectListCon(gymNo);

			gymVo.setGymimgList(gymimgList);
			gymVo.setConList(conList);
			/********* 대관정보 구하기 end **********/

			map.put("gAVo", gymVo);
		}
		// MAP에 넣기

		// 이미등록한 신청자 확인
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("bookingNo", bookingNo);
		userMap.put("sessionuser", sessionuser);
		BBuyVo bbuyVoUser = sparringDao.selectOneBBuy(userMap);

		System.out.println();
		if (bbuyVoUser != null) {
			map.put("bbuyVoUser", bbuyVoUser);
		}

		/// *********대관한 대관정보가 예약중인지 가져오기*********/////

		BookingVo bookingVo = sparringDao.selectOnebookingNo(bookingNo);
		if (bookingVo != null) {

			System.out.println(bookingVo.getBooking_state());
			map.put("booking_state", bookingVo.getBooking_state());
		}

		//// *********************************************///

		// *****수락하기 나누기 ******///
		// 시합 등록자가 결제 완료의경우 (직접대관을 한 경우) 상대에게 결제알람을 보낸다
		// 시합 등록자가 결제완료인지 확인

		// 시합등록자가 대관하지 않고 신청자가 먼저 대관을 한경우 수락 하기 버튼을 누르면
		// 해당 bookingNo를 결제한다

		map.put("bBuyVo", vo);
		
		return map;
	}

	public BBuyVo bbuyOne(int bbuyNo, int userNo) {
		System.out.println("[Service] : bbuyOne");

		BBuyVo bbuyVo = new BBuyVo();

		bbuyVo.setB_buy_no(bbuyNo);
		bbuyVo.setUser_no(userNo);
		BBuyVo vo = sparringDao.selectOnebbuy2(bbuyVo);

		System.out.println(vo);

		// 체급구하기

		int event1 = 0;
		String WC2 = "";

		// 체급구하기
		BookingVo bookingVo2 = sparringDao.selectOneBooking(vo.getBooking_no());
		String gymEvent = bookingVo2.getGym_event();
		if (gymEvent.equals("복싱")) {
			event1 = 1;
		} else if (gymEvent.equals("킥복싱")) {
			event1 = 2;
		} else if (gymEvent.equals("종합격투기")) {
			event1 = 3;
		} else if (gymEvent.equals("주짓수")) {
			event1 = 4;
		}
		int weight2 = Integer.parseInt(vo.getWeight());
		WC2 = weightClass(event1, weight2);

		System.out.println(vo);
		System.out.println("신청자 :" + WC2);
		vo.setWeightC(WC2);
		// 체급

		// 신청자 의 승률구하기 (등록자랑 같은거사용)
		if (bbuyVo.getScoreCount() != 0) {
			BBuyVo scoreVo = sparringDao.selectOneMatchScore(userNo);

			double sumScore = scoreVo.getSumScore();
			double sumWin = scoreVo.getSumWin();
			System.out.println(sumScore + "," + sumWin);

			int rate = (int) ((sumWin / sumScore) * 100);
			System.out.println("승률 : " + rate);

			vo.setRate(rate);
			// 시합 등록자의 승률구하기END
		}
		/** 경력리스트,주특기 리스트 구하기 **/
		int profileNo = vo.getProfile_no();

		List<EventVo> eventList = sparringDao.selectListEvent(profileNo);

		// 경력리스트
		List<RecordVo> recordList = sparringDao.selectListRecord(profileNo);

		vo.setEventList(eventList);
		vo.setRecordList(recordList);

		return vo;
	}

	/****/

	public String weightClass(int event, int weight) {

		String wc = "";
		/* 복싱 = 1 킥복싱 = 2 종합격투기 =3 주짓수 = 4 */

		if (event == 1) {

			if (weight <= 46) {
				wc = "라이트미니엄";

			}

			if (46 < weight && weight <= 48) {
				wc = "미니멈급";

			}

			if (48 < weight && weight <= 49) {
				wc = "라이트플라이급";

			}
			if (49 < weight && weight <= 51) {
				wc = "플라이급";
			}
			if (51 < weight && weight <= 52) {
				wc = "슈퍼플라이급";

			}
			if (51 < weight && weight <= 52) {
				wc = "슈퍼플라이급";
			}
			if (52 < weight && weight <= 54) {
				wc = "밴텀급";
			}
			if (54 < weight && weight <= 55) {
				wc = "슈퍼밴텀급";
			}
			if (55 < weight && weight <= 57) {
				wc = "페더급";
			}
			if (57 < weight && weight <= 59) {
				wc = "슈퍼페더급급";
			}
			if (59 < weight && weight <= 61) {
				wc = "라이트급";
			}

			if (bool(weight, 61, 63)) {
				wc = "슈퍼라이트급";
			}
			if (bool(weight, 63, 67)) {
				wc = "웰터급";

			}
			if (bool(weight, 67, 70)) {
				wc = "슈퍼웰터급";
			}
			if (bool(weight, 70, 73)) {
				wc = "미들급";
			}
			if (bool(weight, 73, 76)) {
				wc = "슈퍼미들급";
			}
			if (bool(weight, 76, 79)) {
				wc = "라이트헤비급";
			}
			if (bool(weight, 79, 91)) {
				wc = "크루저급";
			}
			if (91 < weight) {
				wc = "헤비급";
			}

		} else if (event == 2) {
			if (weight <= 50) {
				wc = "플라이급";
			}
			if (bool(weight, 50, 55)) {
				wc = "벤텀급";
			}
			if (bool(weight, 55, 60)) {
				wc = "페더급";
			}
			if (bool(weight, 60, 65)) {
				wc = "라이트급";
			}
			if (bool(weight, 65, 70)) {
				wc = "웹터급";
			}
			if (bool(weight, 70, 75)) {
				wc = "미들급";
			}
			if (bool(weight, 75, 80)) {
				wc = "라이트 헤비급";
			}
			if (bool(weight, 80, 85)) {
				wc = "헤비급";
			}
			if (bool(weight, 85, 89)) {
				wc = "슈퍼헤비급";
			}
			if (90 <= weight) {
				wc = "무제한급";
			}

		} else if (event == 3) {
			if (weight <= 52) {
				wc = "스트로급";
			}
			if (bool(weight, 52, 57)) {
				wc = "플라이급";
			}
			if (bool(weight, 57, 61)) {
				wc = "팬텀급";
			}
			if (bool(weight, 61, 66)) {
				wc = "페터급";
			}
			if (bool(weight, 66, 70)) {
				wc = "라이트급";
			}
			if (bool(weight, 70, 75)) {
				wc = "슈퍼라이트급";
			}
			if (bool(weight, 75, 77)) {
				wc = "웹터급";
			}
			if (bool(weight, 77, 79)) {
				wc = "슈퍼웰터급";
			}

			if (bool(weight, 79, 84)) {
				wc = "미들급";
			}
			if (bool(weight, 84, 86)) {
				wc = "슈퍼미들급";
			}
			if (bool(weight, 86, 93)) {
				wc = "라이트헤비급";
			}
			if (bool(weight, 93, 102)) {
				wc = "크루저급";
			}
			if (bool(weight, 102, 120)) {
				wc = "헤비급";
			}
			if (120 < weight) {
				wc = "슈퍼헤비급";
			}
		} else if (event == 4) {

			if (weight <= 58) {
				wc = "루스터급";
			}

			if (bool(weight, 59, 64)) {
				wc = "라이트 페더급";
			}

			if (bool(weight, 64, 70)) {
				wc = "페더급";
			}

			if (bool(weight, 70, 76)) {
				wc = "라이트급";
			}
			if (bool(weight, 76, 82)) {
				wc = "미들급";
			}
			if (bool(weight, 82, 88)) {
				wc = "미디엄헤비급";
			}
			if (bool(weight, 88, 94)) {
				wc = "헤비급";
			}
			if (bool(weight, 94, 101)) {
				wc = "수퍼헤비";
			}
			if (102 <= weight) {
				wc = "울트라헤비";
			}
		}

		System.out.println("체급 : " + wc);

		return wc;

	}

	public static boolean bool(int weight, int low, int high) {

		boolean tf = (low < weight && weight <= high);

		return tf;
	}

	public void insertBBuy2(int userNo, int profileNo, int bookingNo, int bbuyuser) {
		System.out.println("[Service] : insertBBuy2");
		AlarmContentVo alarmContentVo = new AlarmContentVo();
		BBuyVo bbuyVo = new BBuyVo();
		bbuyVo.setUser_no(userNo);
		bbuyVo.setProfile_no(profileNo);
		bbuyVo.setBooking_no(bookingNo);

		sparringDao.insertBBuyForm(bbuyVo);

		/* 대관등록자에게 신청자가 '신청하기' */
		AlarmVo alarmVo = sparringDao.selectSellno(bookingNo);

		alarmVo.setAlarm_content(alarmContentVo.matching_competition);
		alarmVo.setUser_no(bbuyuser);

		alarmDao.insertMatchAlarm(alarmVo);
		/* 끝 */
	}

	public void accept(int partnerUserNo, int bookingNo) {
		

		System.out.println("[service]: accept()");

		// 일단 다 예정자로 만든다 (대기자로 만드는 이유는 이후에 상대가 수락이아닌 거절을 할경우)
		// 다시 신청자로 돌려주기 위함인데 그전에 거절한 사용자는 아예 탈락자로 보내기위함
		sparringDao.updateAcceptBBuyfail(bookingNo);

		// 파트너만 선택자로 바꾼다

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookingNo", bookingNo);
		map.put("userNo", partnerUserNo);
		sparringDao.updateAcceptBBuysucc(map);



	}
	
	public void matching_competition(int partnerUserNo, int bookingNo) {
		AlarmContentVo alarmContentVo = new AlarmContentVo();
		/** 대관등록자가 수락하기'' 신청자에게 알람발송 **/
		AlarmVo alarmVo = sparringDao.selectSellno(bookingNo);

		alarmVo.setAlarm_content(alarmContentVo.matching_accept);
		alarmVo.setUser_no(partnerUserNo); // 파트너넘버

		alarmDao.insertMatchAlarm(alarmVo);
		/**/
	}

	public void acceptpayment(int partnerUserNo, int bookingNo) {
		System.out.println("[Dao]: accept() : acceptpayment");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookingNo", bookingNo);
		map.put("userNo", partnerUserNo);

		sparringDao.updateAcceptBBuysucc(map);

	}

	public void acceptpay(BBuyVo bBuyVo, int bbuyno, int bookingNo , int userNo, int partneruserno) {
		System.out.println("[Dao]: accept() : acceptpay");

		int num = bBuyVo.getB_buy_price().lastIndexOf(".");

		String str = bBuyVo.getB_buy_price().substring(0, num);

		System.out.println(str);

		int price = Integer.parseInt(str);
		bBuyVo.setPrice(price);

		bBuyVo.setB_buy_player_state("시합결정자");

		// 부킹 결제완료로 바꿔주기
		sparringDao.updateBooking3(bookingNo);

		bBuyVo.setB_buy_no(bbuyno);
		bBuyVo.setBooking_no(bookingNo);
		sparringDao.updateBBuy2(bBuyVo);
		
		
		/* 매치등록자가 수락하고 결제하기 알람발송 "결제완료되었습니다"**/
		
		AlarmContentVo alarmContentVo = new AlarmContentVo();
		
		AlarmVo alVo = sparringDao.selectSellno(bookingNo);

		alVo.setAlarm_content(alarmContentVo.payment_complete);
		
		alVo.setUser_no(userNo);
		alarmDao.inserbookingAlarm(alVo);
		/******/
		/*****등록자에게 알람발송 매치가 성사되었습니다 ****/
		
		alVo.setAlarm_content(alarmContentVo.matching_successful);
		alarmDao.insertMatchAlarm(alVo);
		
		/****신청자에게 매치가 성사되었습니다****/
		alVo.setUser_no(partneruserno);
		alarmDao.insertMatchAlarm(alVo);
		
		/**시설관리에게 알람발송 예약된 대관상품의 결제가 완료되었습니다**/
		alVo = sparringDao.selectSellno(bookingNo);
		alVo.setAlarm_content(alarmContentVo.booking_payment_complete02);
		alarmDao.inserbookingAlarm(alVo);
	}

	public void acceptPartner(int partneruserno, int bookingNo, BBuyVo bBuyVo, int bbuyno, int bookingNo2, int mybbuyno, int userNo) {
		System.out.println("[Service] : acceptPartner");

		int num = bBuyVo.getB_buy_price().lastIndexOf(".");

		String str = bBuyVo.getB_buy_price().substring(0, num);

		System.out.println(str);

		int price = Integer.parseInt(str);
		bBuyVo.setPrice(price);
		// 상대 player_state (시합등록자)변경해주기
		bBuyVo.setB_buy_player_state("시합결정자");
		bBuyVo.setB_buy_no(bbuyno);
		bBuyVo.setBooking_no(bookingNo);
		sparringDao.updateBBuy3(bBuyVo);

		// 일단 다 대기자로 만든다 (대기자로 만드는 이유는 이후에 상대가 수락이아닌 거절을 할경우)
		// 다시 신청자로 돌려주기 위함인데 그전에 거절한 사용자는 아예 탈락자로 보내기위함
		sparringDao.updateAcceptBBuyfail(bookingNo);

		// 내 결제상태 바꾸기
		bBuyVo.setB_buy_no(mybbuyno);
		System.out.println(bBuyVo.getB_buy_no());

		sparringDao.updateBBuy4(bBuyVo);

		// 부킹 결제완료로 바꿔주기
		sparringDao.updateBooking3(bookingNo);
		
		/******등록자에게 수락을 받은 신청자가 결제하기 버튼을 눌렀을때******/
		
		AlarmContentVo alarmContentVo = new AlarmContentVo();
		
		AlarmVo alVo = sparringDao.selectSellno(bookingNo);

		alVo.setAlarm_content(alarmContentVo.matching_successful);
		
		alVo.setUser_no(partneruserno);
		alarmDao.insertMatchAlarm(alVo);
		
		
		alVo.setUser_no(userNo);
		alarmDao.insertMatchAlarm(alVo);
		
		/****신청자에게 결제완료****/
		alVo.setAlarm_content(alarmContentVo.payment_complete);
		alVo.setUser_no(userNo);
		alarmDao.inserbookingAlarm(alVo);
		/**시설관리에게 알람발송 예약된 대관상품의 결제가 완료되었습니다**/
		
		alVo = sparringDao.selectSellno(bookingNo);
		alVo.setAlarm_content(alarmContentVo.booking_payment_complete01);
		alarmDao.inserbookingAlarm(alVo);

	}

	public void refuse(int bbuyNo, int bookingNo, int partnerUserNo) {
		System.out.println("[Service] : refuse()");
		AlarmContentVo alarmContentVo = new AlarmContentVo();
		System.out.println(bbuyNo);
		sparringDao.updateBBuyrefuse(bbuyNo);

		AlarmVo alarmVo = sparringDao.selectSellno(bookingNo);

		alarmVo.setAlarm_content(alarmContentVo.matching_refused);
		alarmVo.setUser_no(partnerUserNo); // 파트너넘버

		alarmDao.insertMatchAlarm(alarmVo);

	}

	public void cancel(int bbuyno, int bookingno, int userNo) {
		System.out.println("[Service] : cancel");
		System.out.println(bbuyno);
		System.out.println(bookingno);
		// 취소한 사용자 삭제
		sparringDao.removeBBuy(bbuyno);

		// 대기자를 다시 신청자로 변경
		sparringDao.updatebbuyBack(bookingno);

		/**시합 등록자가 수락한 신청자가 취소하기 클릭 알람발송**/
		
		AlarmContentVo alarmContentVo = new AlarmContentVo();
		
		AlarmVo alarmVo = sparringDao.selectSellno(bookingno);

		//alarmVo.setAlarm_content(alarmContentVo.matching_cancled);
		alarmVo.setUser_no(userNo); //등록자넘버

		alarmDao.insertMatchAlarm(alarmVo);
	}

	public List<BBuyVo> myMatch(int userno) {
		System.out.println("[Service ] : myMatch");
		System.out.println("유저넘버 : " + userno);
			
		List<BBuyVo> bList = sparringDao.selectBBuyList3(userno);
		
		System.out.println(bList);
		
		for(int i = 0 ; i <bList.size(); i++) {
			int bookingNo = bList.get(i).getBooking_no();
			String player = bList.get(i).getB_buy_player_state();
			
			if(player.equals("신청자") || player.equals("선택자") || player.equals("탈락자")) {
				System.out.println("내 상태 : " + player );
				
				List<UserVo> nList = sparringDao.selectListVictim(bookingNo);
				
				System.out.println("상대 : " + nList);
				bList.get(i).setUserList(nList);
				
			}else if(player.equals("시합등록자") ||player.equals("시합결정자")) {
				System.out.println("내 상태 : " + player);
				List<UserVo> nList2 = sparringDao.selectListVictim2(bookingNo);
				System.out.println("상대 : " + nList2);
				bList.get(i).setUserList(nList2);
			}
			
			BBuyVo bvo = sparringDao.selectBBuyuserno(bookingNo);
			
			if(bvo != null) {
				
				bList.get(i).setU_no(bvo.getU_no());
				bList.get(i).setB_buy_no(bvo.getB_buy_no());
			}
			
		}
		
		System.out.println(bList);
		
		return bList;
	
	}

	public double algo(ProfileVo profileVo, RecordVo recordVo, int userNo) {
		System.out.println("algo");
		double userLevel = 0;
		
		String career = profileVo.getCareer();

		String major = profileVo.getMajor();

		int exp = profileVo.getExp();

		String recentlyExer = profileVo.getRecentlyExer();
		
		if(career.equals("프로")) {
			userLevel += 2000;
			
			System.out.println("프로 userLevel = " + userLevel);
		}else if(career.equals("아마추어")) {
			userLevel += 0;
			System.out.println("아마추어 userLevel = " + userLevel);
		}
		
		if(!major.equals(null)&&!major.isEmpty()) {
			userLevel += 300;
			
			System.out.println("전공userLevel = " + userLevel);
		}
		
		if(exp >=20) {
			userLevel += 20;
		}
		
		
		for (int i = 0; i < recordVo.getRecordList().size(); i++) {
			RecordVo reVo = recordVo.getRecordList().get(i);
			String recordEvent = reVo.getRecordEvent();
			String recordDate = reVo.getRecordDate();
			String recordType = reVo.getRecordType();
			String recordMatch = reVo.getRecordMatch();

			if (recordEvent.equals("1") || recordEvent.equals("2")) {

				userLevel += boxkickbox(recordDate, recordMatch, recordType);
				
				System.out.println("복싱 & 킥복싱userLevel = " + userLevel);
			} else if (recordEvent.equals("3")) {
				
				userLevel += mixbox(recordDate, recordMatch, recordType);
				System.out.println("종합 격투기userLevel = " + userLevel);
			} else if (recordEvent.equals("4")) {
				
				userLevel += jujitsu(recordDate, recordMatch, recordType);

				System.out.println("주짓수userLevel = " + userLevel);
			}

		}
		//end
		
		if(recentlyExer.equals("주 5회 이상")) {
			userLevel = userLevel * 1.1;
		}else if(recentlyExer.equals("주 3회 이상")) {
			userLevel = userLevel * 1;
		}else if(recentlyExer.equals("주 1회 이상")) {
			userLevel = userLevel * 0.9;
		}else if(recentlyExer.equals("월 1회 이상")) {
			userLevel = userLevel * 0.7;
			
		}else if(recentlyExer.equals("3달 이상 안함")) {
			userLevel = userLevel * 0.5;
			
		}else if(recentlyExer.equals("6달 이상 안함")) {
			userLevel = userLevel * 0.3;
		}
		
		System.out.println(userLevel);
		double matchLevel = matchScoreLevel(userNo);
		
		userLevel += matchLevel;
		
		return userLevel;
	}
	
	public double matchScoreLevel(int userNo) {
		
		
		double halfResult = 0;
		double count = 0;
		double result = 0;
		//먼저 MatchScore를 List로 가져온다
		List<MatchScoreVo> mScoreList = sparringDao.selectListMatchScore(userNo);
		System.out.println("mScoreList =" + mScoreList);
		if(!mScoreList.isEmpty()) {
			
			for(int i = 0; i < mScoreList.size(); i++) {
				MatchScoreVo mScoreVo = mScoreList.get(i);
				//스파링수에 비해 이긴수를 구한다
				double score = mScoreVo.getScore();
				double win = mScoreVo.getWin();
				String matchAttri = mScoreVo.getMatchAttri();
				
				
				double winExp = Math.round((win / score)*100.0)/100.0;
			
				
				if(matchAttri.equals("물")) {
					halfResult += 0.9;
				}else if(matchAttri.equals("불")) {
					halfResult += 1.0;
				}else if(matchAttri.equals("핵")) {
					halfResult += 1.1;
				}
				
				System.out.println("halfResult =" + halfResult);
				
				count++;
			}
			halfResult =  Math.round((halfResult / count)*100.0)/100.0;
			
			System.out.println("halfResult /= count" +halfResult);
			
			//퍼센트로 곱하기 위해 100을 곱해준다
			
			halfResult = halfResult * 100;
			
			//총 스파일 전적에 따라 부여되는 값 
			if(1<= count && count <=5) {
				result = 0.5* halfResult;
			}else if(5 < count && count <=10) {
				result = 1 * halfResult;
			}else if(10 < count && count <=15) {
				result = 2 * halfResult;
			}else if(15 < count && count <=20) {
				result = 3 * halfResult;
			}else if(20 < count && count <=30) {
				result = 4 * halfResult;
			}else if(30 < count && count <=40) {
				result = 6 * halfResult;
			}else if(40 < count && count <=50) {
				result = 8 * halfResult;
			}else if(50 < count && count <=60) {
				result =  10 * halfResult;
			}else if(60 < count && count <=70) {
				result =  12 * halfResult;
			}else if(70 < count && count <=80) {
				result =  14 * halfResult;
			}else if(80 < count && count <=90) {
				result =  16 * halfResult;
			}else if(90 < count && count <=100) {
				result =  18 * halfResult;
			}else if(100 <= count) {
				result =  20 * halfResult;
			}
			System.out.println("Match SCORE result = "+ result);
		}
		
		return result;
	}
	

	public int boxkickbox(String recordDate, String recordMatch, String recordType) {

		int userLevel = 0;
		System.out.println("복싱, 킥복싱");

		if (recordType.equals("세계선수권대회")) {
			
			System.out.println("세계선수권대회 ");
			
			if (recordDate.equals("4년 주기 대회")) {
				System.out.println("4년 주기 대회 ");
				if (recordMatch.equals("우승")) {

					userLevel += 1800;
				} else if (recordMatch.equals("준우승")) {

					userLevel += 1530;
				} else if (recordMatch.equals("4강")) {

					userLevel += 1300;
				} else if (recordMatch.equals("8강")) {

					userLevel += 1105;
				} else if (recordMatch.equals("16강")) {

					userLevel += 939;

				}

			} else if (recordDate.equals("3년 주기 대회")) {
				if (recordMatch.equals("우승")) {

					userLevel += 1400;
				} else if (recordMatch.equals("준우승")) {

					userLevel += 1190;
				} else if (recordMatch.equals("4강")) {

					userLevel += 1011;
				} else if (recordMatch.equals("8강")) {

					userLevel += 859;
				} else if (recordMatch.equals("16강")) {

					userLevel += 730;

				}

			} else if (recordDate.equals("2년 주기 대회")) {
				if (recordMatch.equals("우승")) {

					userLevel += 1350;
				} else if (recordMatch.equals("준우승")) {

					userLevel += 1147;
				} else if (recordMatch.equals("4강")) {

					userLevel += 974;
				} else if (recordMatch.equals("8강")) {

					userLevel += 827;
				} else if (recordMatch.equals("16강")) {

					userLevel += 702;

				}

			} else if (recordDate.equals("1년 주기 대회")) {
				if (recordMatch.equals("우승")) {

					userLevel += 1150;
				} else if (recordMatch.equals("준우승")) {

					userLevel += 977;
				} else if (recordMatch.equals("4강")) {

					userLevel += 830;
				} else if (recordMatch.equals("8강")) {

					userLevel += 705;
				} else if (recordMatch.equals("16강")) {

					userLevel += 599;

				}
			}
		} else if (recordType.equals("아시아경기대회")) {
			if (recordMatch.equals("우승")) {

				userLevel += 1200;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 1020;
			} else if (recordMatch.equals("4강")) {

				userLevel += 867;
			} else if (recordMatch.equals("8강")) {

				userLevel += 736;
			} else if (recordMatch.equals("16강")) {

				userLevel += 625;

			}

		} else if (recordType.equals("유니버시아드대회")) {
			if (recordMatch.equals("우승")) {

				userLevel += 1100;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 935;
			} else if (recordMatch.equals("4강")) {

				userLevel += 794;
			} else if (recordMatch.equals("8강")) {

				userLevel += 674;
			} else if (recordMatch.equals("16강")) {

				userLevel += 572;

			}
		} else if (recordType.equals("아시아선수권대회")) {
			if (recordMatch.equals("우승")) {

				userLevel += 1050;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 892;
			} else if (recordMatch.equals("4강")) {

				userLevel += 758;
			} else if (recordMatch.equals("8강")) {

				userLevel += 644;
			} else if (recordMatch.equals("16강")) {

				userLevel += 547;

			}
		} else if (recordType.equals("지역대회")) {
			if (recordMatch.equals("우승")) {

				userLevel += 100;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 85;
			} else if (recordMatch.equals("4강")) {

				userLevel += 72;
			} else if (recordMatch.equals("8강")) {

				userLevel += 0;
			} else if (recordMatch.equals("16강")) {

				userLevel += 0;

			}

		}

		return userLevel;
	}

	public int mixbox(String recordDate, String recordMatch, String recordType) {
		int userLevel = 0;
		System.out.println("종합격투기");
		if (recordType.equals("UFC")) {

			if (recordMatch.equals("우승")) {

				userLevel += 1800;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 1530;
			} else if (recordMatch.equals("4강")) {

				userLevel += 1300;
			} else if (recordMatch.equals("8강")) {

				userLevel += 1105;
			} else if (recordMatch.equals("16강")) {

				userLevel += 939;

			}

		} else if (recordDate.equals("벨라토르")) {
			if (recordMatch.equals("우승")) {

				userLevel += 1400;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 1190;
			} else if (recordMatch.equals("4강")) {

				userLevel += 1011;
			} else if (recordMatch.equals("8강")) {

				userLevel += 859;
			} else if (recordMatch.equals("16강")) {

				userLevel += 730;

			}

		} else if (recordDate.equals("K-1")) {
			if (recordMatch.equals("우승")) {

				userLevel += 1350;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 1147;
			} else if (recordMatch.equals("4강")) {

				userLevel += 974;
			} else if (recordMatch.equals("8강")) {

				userLevel += 827;
			} else if (recordMatch.equals("16강")) {

				userLevel += 702;

			}

		} else if (recordType.equals("PRIDEFC")) {
			if (recordMatch.equals("우승")) {

				userLevel += 1200;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 1020;
			} else if (recordMatch.equals("4강")) {

				userLevel += 867;
			} else if (recordMatch.equals("8강")) {

				userLevel += 736;
			} else if (recordMatch.equals("16강")) {

				userLevel += 625;

			}

		} else if (recordType.equals("ROAD FC")) {
			if (recordMatch.equals("우승")) {

				userLevel += 820;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 697;
			} else if (recordMatch.equals("4강")) {

				userLevel += 592;
			} else if (recordMatch.equals("8강")) {

				userLevel += 503;
			} else if (recordMatch.equals("16강")) {

				userLevel += 427;

			}
		} else if (recordType.equals("지역대회")) {
			if (recordMatch.equals("우승")) {

				userLevel += 100;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 85;
			} else if (recordMatch.equals("4강")) {

				userLevel += 72;
			} else if (recordMatch.equals("8강")) {

				userLevel += 0;
			} else if (recordMatch.equals("16강")) {

				userLevel += 0;

			}

		}
		return userLevel;
	}
	
	public int jujitsu(String recordDate, String recordMatch, String recordType) {
		int userLevel = 0;
		System.out.println("주짓수");
		if (recordType.equals("세계수짓수선수권")) {

			if (recordMatch.equals("우승")) {

				userLevel += 1800;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 1530;
			} else if (recordMatch.equals("4강")) {

				userLevel += 1300;
			} else if (recordMatch.equals("8강")) {

				userLevel += 1105;
			} else if (recordMatch.equals("16강")) {

				userLevel += 939;

			}

		} else if (recordDate.equals("비도복세계주짓수선수권")) {
			if (recordMatch.equals("우승")) {

				userLevel += 1400;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 1190;
			} else if (recordMatch.equals("4강")) {

				userLevel += 1011;
			} else if (recordMatch.equals("8강")) {

				userLevel += 859;
			} else if (recordMatch.equals("16강")) {

				userLevel += 730;

			}

		} else if (recordDate.equals("팬암챔피언쉽") || recordDate.equals("유러피언챔피언")) {
			if (recordMatch.equals("우승")) {

				userLevel += 1350;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 1147;
			} else if (recordMatch.equals("4강")) {

				userLevel += 974;
			} else if (recordMatch.equals("8강")) {

				userLevel += 827;
			} else if (recordMatch.equals("16강")) {

				userLevel += 702;

			}

		} else if (recordType.equals("IBJJF지역대회")) {
			if (recordMatch.equals("우승")) {

				userLevel += 1200;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 1020;
			} else if (recordMatch.equals("4강")) {

				userLevel += 867;
			} else if (recordMatch.equals("8강")) {

				userLevel += 736;
			} else if (recordMatch.equals("16강")) {

				userLevel += 625;

			}

		} else if (recordType.equals("KBJJF관련대회")) {
			if (recordMatch.equals("우승")) {

				userLevel += 300;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 255;
			} else if (recordMatch.equals("4강")) {

				userLevel += 216;
			} else if (recordMatch.equals("8강")) {

				userLevel += 183;
			} else if (recordMatch.equals("16강")) {

				userLevel += 155;

			}
		} else if (recordType.equals("지역대회")) {
			if (recordMatch.equals("우승")) {

				userLevel += 100;
			} else if (recordMatch.equals("준우승")) {

				userLevel += 85;
			} else if (recordMatch.equals("4강")) {

				userLevel += 72;
			} else if (recordMatch.equals("8강")) {

				userLevel += 0;
			} else if (recordMatch.equals("16강")) {

				userLevel += 0;

			}

		}
		return  userLevel;
	}

	public String gymType(int bookingNo) {
		System.out.println("[gymType] :  gymType");
		
		BBuyVo bbuyVo = sparringDao.selectGymType(bookingNo);
		
		System.out.println("종목은?" + bbuyVo.getGym_event());
		
		String event = bbuyVo.getGym_event();
		return event;
	}

	public void profileWrite(ProfileVo profileVo) {
		

		String major = profileVo.getMajor();
		System.out.println("major" + major);
		if (major == null) {
			profileVo.setMajor("없음");
		}
		String word = profileVo.getWord();
		System.out.println("word" + word);
		if (word == null || word == "") {
			profileVo.setWord("잘 부탁드립니다");
		}

		sparringDao.insertProfile23(profileVo);
		System.out.println("vo2 :" + profileVo);
		


		/********* 주종목(주특기) event 인서트 ********/
		
		int profileNo = profileVo.getProfileNo();
		List<EventVo> eList = profileVo.getEventList();
		for (int i = 0; i < eList.size(); i++) {

		
			System.out.println(eList.get(i));

			EventVo eventVo = new EventVo();

			eventVo.setProfileNo(profileNo);
			eventVo.setEventName(eList.get(i).getEventName());

			sparringDao.insertEvent(eventVo);

		}
		
		/*********** 공식기록 record 인서트 *********/
		
		List<RecordVo> rList = profileVo.getRecordList();
		if (rList.get(0).getRecordType() != "") {
			for (int i = 0; i < rList.size(); i++) {
				System.out.println("??");
				RecordVo rVo = rList.get(i);
				
				System.out.println(rVo);
				// 받아온 profileNo 넣기
				rVo.setProfileNo(profileNo);
				
				
				if(rVo.getRecordName() == null) {
					rVo.setRecordName(" ");
				}
				
				if(rVo.getRecordType().equals("대회분류")) {
					rVo.setRecordType("");
				}
				
				if(rVo.getRecordDate().equals("출전연도")) {
					rVo.setRecordDate("");
				}
				
				if(rVo.getRecordMatch().equals("순위")) {
					rVo.setRecordMatch("");
				}
				
				if(!rVo.getRecordEvent().equals("종목")) {
					
				System.out.println(rVo.getRecordName());
				sparringDao.insertRecord(rVo);
				}

			}
		}
		
		RecordVo recordVo = new RecordVo();
		recordVo.setRecordList(rList);
		
		
		/**알고리즘 넣기***/
		int userNo = profileVo.getUserNo();
		double userLevel = algo(profileVo, recordVo, userNo);
		
		
		UserVo userVo = new UserVo();
		
		userVo.setUser_no(userNo);
		userVo.setUser_level(userLevel);
		int count = sparringDao.updateUserLevel(userVo);
		
		
		
	}

	public BBuyVo sparringEva(BBuyVo bbuyVo) {
		System.out.println("[Service] : sparringEva");
		
		
		//내가 아닌 상대를 구하는 Dao
		BBuyVo bVo = sparringDao.selectOnePartnerBBuy(bbuyVo);
		System.out.println(bVo);
		
		return bVo;
	}

	public void evaWrite(MatchScoreVo matchScoreVo) {
		System.out.println("[Service] : evaWrite");
		
		sparringDao.insertMatchScore(matchScoreVo);
		
	}
	
	
	/*******************일반 메소드**********************/
	
	public Map<String,String> takeStartEndTime(String time) {
		
			String[] timeArray = time.split("~");
			
			Map<String,String> timeMap = new HashMap<String,String>();
			
			timeMap.put("bookingStart",timeArray[0]);
			timeMap.put("bookingEnd",timeArray[1]);
		return timeMap;
	}
	
	
}
