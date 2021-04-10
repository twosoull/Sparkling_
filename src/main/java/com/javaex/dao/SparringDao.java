package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.AlarmVo;
import com.javaex.vo.BBuyVo;
import com.javaex.vo.BookingVo;
import com.javaex.vo.ConvenienceVo;
import com.javaex.vo.EventVo;
import com.javaex.vo.GymImgVo;
import com.javaex.vo.GymVo;
import com.javaex.vo.MatchScoreVo;
import com.javaex.vo.ProfileVo;
import com.javaex.vo.RecordVo;
import com.javaex.vo.SearchMatchVo;
import com.javaex.vo.UserVo;

@Repository
public class SparringDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//프로필생성
	public void insertProfile(ProfileVo profileVo) {
		System.out.println("[Dao] : insertProfile()");
		
		
		int count = sqlSession.insert("profile.insertProfile",profileVo);
		
		System.out.println("profile"+count);
		
	}
	
	//주종목생성
	public void insertEvent(EventVo eventVo) {
		// TODO Auto-generated method stub
		System.out.println("[Dao] : insertEvent");
		
		int count = sqlSession.insert("event.insertEvent",eventVo);
		System.out.println("event" + count);
	}

	public void insertRecord(RecordVo recordVo) {
		
		System.out.println("[Dao] : insertRecord");
		
		int count = sqlSession.insert("record.insertRecord",recordVo);
		
		System.out.println("insertRecord = "+count);
	}

	public List<ProfileVo> selectListProfile(int userNo) {
		System.out.println("[Dao] : selectListProfile");
		
		List profileList = sqlSession.selectList("profile.selectListProfile",userNo);
		
		for(int i = 0; i < profileList.size(); i++) {
			
		}
		
		return profileList;
	}

	public ProfileVo selectOneProfile(Map<String, Object> profileMap) {
		System.out.println("[Dao] : selectOneProfile");
		
		return sqlSession.selectOne("profile.selectOneProfileBydate",profileMap);
	}

	public List<EventVo> selectListEvent(int profileNo) {
		// TODO Auto-generated method stub
		//System.out.println("[Dao] : selectListEvent");
		
		List<EventVo> eventList = sqlSession.selectList("event.selectListEvent",profileNo);
		
		
		return eventList;
	}

	public List<RecordVo> selectListRecord(int profileNo) {
		System.out.println("[Dao] : selectListRecord");
		
		List<RecordVo> recordList = sqlSession.selectList("record.selectListRecord",profileNo);
		System.out.println("RecordList = "+ recordList);
		
		return recordList;
	}

	public List<GymVo> selectListGym() {
		System.out.println("[Dao] : selectListGym()");
		
		return sqlSession.selectList("gym.selectListGym");
		
		
	}

	public GymVo selectOneGym(int gymNo) {
		System.out.println("[Dao] : selectOneGym()");
		System.out.println(gymNo);
		
		return sqlSession.selectOne("gym.selectOneGym",gymNo);
		
	}

	public List<GymImgVo> selectListGymImg(int gymNo) {
		System.out.println("[DAO] : selectListGymImg");
		
		return sqlSession.selectList("gymimg.selectListGymImg",gymNo);
	}


	public List<ConvenienceVo> selectListCon(int gymNo) {
		System.out.println("[Dao] : selectListCon");
		
		List<ConvenienceVo>conList = sqlSession.selectList("convenience.selectListCon",gymNo);
		
		System.out.println(conList);
		
		return conList;
	}

	public GymImgVo selectOneGymImg(int gymImgNo) {
		System.out.println("[Dao] : selectOneGymImg");
		
		return sqlSession.selectOne("gymimg.selectOneGymImg",gymImgNo);
		
	}
	//첫화면 리스트
	public List<BookingVo> selectListBooking(Map<String, Object> map) {
		
		System.out.println("[Dao] : selectOneBooking()");
		
		List<BookingVo> bList = sqlSession.selectList("booking.selectListBooking",map);
		System.out.println(bList.toString());
		
		return bList;
	}

	public BookingVo selectOneBooking(int bookingNo) {
		System.out.println("[Dao] : selectOneBooking()");
		
		 return sqlSession.selectOne("booking.selectOneBooking",bookingNo);
		
	}

	public UserVo selectAddressUser(int userNo) {
		System.out.println("[Dao] : selectAddressUser()");
		
		return sqlSession.selectOne("user.selectOneUserAddress",userNo);
		
	}

	public void insertBBuy(BBuyVo bBuyVo) {
		System.out.println("[Dao] : inserBBuy");
		System.out.println(bBuyVo);
		int count = sqlSession.insert("bbuy.insertBBuy",bBuyVo);
		
		System.out.println(count);
	}
	
	// by 영훈 
	// 스파링 리스트 (메인페이지 사용)
	// 스파링 리스트(21-03-25) 
	public List<BBuyVo> selectBBuyList() {
		System.out.println("[Dao] : selectBBuyList");
		
		return sqlSession.selectList("bbuy.selectListBBuy");
		
		
	}
	// by 영훈 
	// 스파링 리스트 + 스파링 리스트의 검색기능 (searchMatchVo 사용)
	// 스파링 리스트(21-03-25) 검색(04-10)
	public List<BBuyVo> selectBBuyList(SearchMatchVo searchMatchVo) {
			System.out.println("[Dao] : selectBBuyList(SearchMatchVo)");
			
			return sqlSession.selectList("bbuy.selectListBBuynSearch",searchMatchVo);
			
			
	}
	
	//신청자구하기
	public List<BBuyVo> selectBBuyList2(int bookingNo) {
		System.out.println("[Dao] : selectBBuyList");
		
		List<BBuyVo> bbuyList = sqlSession.selectList("bbuy.selectListBBuy2",bookingNo);
		
		System.out.println("신청자 :" + bbuyList);
		
		return bbuyList;
	}
	
	public BBuyVo selectOneBBuy(BBuyVo bBuyVo) {
		System.out.println("[Dao] : selectOneBBuy");
		
		return sqlSession.selectOne("bbuy.selectOneBBuy",bBuyVo);
		
		//no로 등록자불러오기
	}

	public void insertBBuy2(BBuyVo bBuyVo) {
		System.out.println("[Dao] : insertBBuy2");
		
		int count = sqlSession.insert("bbuy.insertBBuy2",bBuyVo);
		
		System.out.println(count);
		
	}

	public BBuyVo selectOneMatchScore(int userNo) {
		System.out.println("[Dao] : selectOneMatchScore()");
		
		return sqlSession.selectOne("bbuy.selectOneMatchScore" , userNo);
	
		
	
	}

	public void updateBooking(int bookingNo) {
		System.out.println("[Dao] : updateBBuy");
		
		int count= sqlSession.update("booking.updateBooking",bookingNo);
		
		System.out.println("예약대기중으로 변경되었습니다 : " + count);
	}

	public BBuyVo selectOnebbuy2(BBuyVo  bbuyVo) {
		System.out.println("[Dao] : selectOnebbuy");
		
		return sqlSession.selectOne("bbuy.selectOnebbuy2", bbuyVo);
		
	}

	public ProfileVo selectProfileEvent(int profileNo) {
		System.out.println("[Dao] : selectProfileEvent");
		
		return sqlSession.selectOne("profile.selectProfileEvent",profileNo);
	}

	public void updateBBuy(BBuyVo bbuyVo) {
		System.out.println("[Dao] : updateBBuy");
		
		
		sqlSession.update("bbuy.updateBBuy",bbuyVo);
	}

	public void updateBooking2(int bookingNo) {
		System.out.println("[Dao] : updateBBuy");
		
		int count= sqlSession.update("booking.updateBooking2",bookingNo);
		
		System.out.println("예약중으로 변경되었습니다 : " + count);
		
	}

	public BBuyVo selectOneBBuy(Map userMap) {
		System.out.println("[Dao] : selectOneBBuy" );
		
		
		
		BBuyVo bbuyVo = sqlSession.selectOne("bbuy.selectOneBBuyuser",userMap);
		
		return bbuyVo;
	}

	public void insertBBuyForm(BBuyVo bbuyVo) {
		System.out.println("[Dao] : insertBBuyForm");
		
		sqlSession.insert("bbuy.insertBBuyForm",bbuyVo);
		
	}

	public BookingVo selectOnebookingNo(int bookingNo) {
		System.out.println("[Dao] : selectOnebookingNo");
		
		return sqlSession.selectOne("booking.selectOnebookingNo",bookingNo);
		
	}

	public void updateAcceptBBuyfail(int bookingNo) {
		System.out.println("[Dao] : updateAcceptBBuy()");
		
		sqlSession.update("bbuy.updateAcceptBBuyfail",bookingNo);
	}

	public void updateAcceptBBuysucc(Map map) {
		System.out.println("[Dao] : updateAcceptBBuysucc()");
		
		sqlSession.update("bbuy.updateAcceptBBuysucc",map);
	
	}

	public void updateBooking3(int bookingNo) {
		System.out.println("[Dao] : updateBooking3()");
		
		sqlSession.update("booking.updateBooking3",bookingNo);
	}

	public void updateBBuy2(BBuyVo bBuyVo) {
		System.out.println("[Dao] : updateBBuy2()");
		
		sqlSession.update("bbuy.updateBBuy2",bBuyVo);
	}

	public void updateBBuy3(BBuyVo bBuyVo) {
	
		
		System.out.println("[Dao] : updateBBuy3()");
		
		sqlSession.update("bbuy.updateBBuy3",bBuyVo);
		
	}

	public void updateBBuy4(BBuyVo bBuyVo) {
		// TODO Auto-generated method stub
		
		System.out.println("[Dao] : updateBBuy4()");
		
		sqlSession.update("bbuy.updateBBuy4",bBuyVo);
		
	}

	public void updateBBuyrefuse(int bbuyNo) {
		System.out.println("[Dao] : updateBBuyrefuse");
		
		sqlSession.update("bbuy.updateBBuyrefuse",bbuyNo);
	}

	public void removeBBuy(int bbuyno) {
		System.out.println("[Dao] : removeBBuy");
		
		sqlSession.delete("bbuy.deleteBBuy",bbuyno);
	}

	public void updatebbuyBack(int bookingno) {
		System.out.println("[Dao] : updatebbuyBack");
		
		sqlSession.update("bbuy.updatebbuyBack",bookingno);
	}

	public AlarmVo selectSellno(int bookingno) {
		System.out.println("[Dao] :  selectSellno ");
		
		return sqlSession.selectOne("alarm.selectSellno", bookingno);
		
	}

	public BBuyVo selectBBUYSC(int bbuyno) {
		System.out.println("[Dao] :  selectBBUYSC ");
		
		 return sqlSession.selectOne("bbuy.selectBBUYSC",bbuyno);
	}

	public int updateUserLevel(UserVo userVo) {
		System.out.println("[Dao] :   updateUserLevel ");
		
		return sqlSession.update("bbuy.updateUserLevel",userVo);
	}

	public List<BBuyVo> selectBBuyList(BBuyVo leVo) {
		System.out.println("[Dao] :   selectBBuyList ");
		
		return sqlSession.selectList("bbuy.selecteListBBuySearchLevel",leVo);
		
	}

	public UserVo selectOneUserVo(int userNo) {
		System.out.println("[Dao] :  selectOneUserVo ");
		
		return sqlSession.selectOne("bbuy.selectOneUserVo",userNo);
		
	}

	public List<BBuyVo> selectBBuyList3(int userno) {
		System.out.println("[Dao] :  selectBBuyList3 ");
		
		return sqlSession.selectList("bbuy.selectBBuyList3",userno);
	}

	public List<UserVo> selectListVictim(int bookingNo) {
		System.out.println("[Dao] :  selectListVictim ");
		
		return sqlSession.selectList("bbuy.selectListVictim",bookingNo);
		
	}

	public List<UserVo> selectListVictim2(int bookingNo) {
		System.out.println("[Dao] :  selectListVictim2");
		
		return sqlSession.selectList("bbuy.selectListVictim2",bookingNo);
		
	}

	public BBuyVo selectBBuyuserno(int bookingNo) {
		System.out.println("[Dao] :  selectBBuyuserno");
		
		return sqlSession.selectOne("bbuy.selectBBuyuserno",bookingNo);
		
	}

	public BBuyVo selectGymType(int bookingNo) {
		System.out.println("[Dao] :  selectGymType");
		
		return sqlSession.selectOne("bbuy.selectGymType",bookingNo);
	}

	public void insertProfile23(ProfileVo profileVo) {
		System.out.println("[Dao] : insertProfile23");
			int count = sqlSession.insert("profile.insertProfile23",profileVo);
		
		System.out.println("profile"+count);
	}

	public BBuyVo selectOnePartnerBBuy(BBuyVo bbuyVo) {
		System.out.println("[Dao] : selectOnePartnerBBuy");
		
		return sqlSession.selectOne("bbuy.selectOnePartnerBBuy",bbuyVo);
		
	}

	public void insertMatchScore(MatchScoreVo matchScoreVo) {
		System.out.println("[Dao] : insertMatchScore");
		
		int count = sqlSession.insert("bbuy.insertMatchScore",matchScoreVo);
		
		System.out.println(count);
	}

	public List<MatchScoreVo> selectListMatchScore(int userNo) {
		System.out.println("[Dao] : selectListMatchScore");
		
		return sqlSession.selectList("bbuy.selectListMatchScore",userNo);
		
	}

	


}
