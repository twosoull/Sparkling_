package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BBuyVo;
import com.javaex.vo.BookingVo;
import com.javaex.vo.ConVo;
import com.javaex.vo.GymImgVo;
import com.javaex.vo.GymVo;

@Repository
public class GymDao {

	@Autowired
	private SqlSession sqlSession;
	
	//소유 체육관번호 리스트
	public List<GymVo> gymnoSelectList(int sellNo) {
		System.out.println("[GymDao] gymSelectList()");
		
		List<GymVo> gymList = sqlSession.selectList("gym.gymnoSelectList", sellNo);
		System.out.println("[GymDao] gymList>>> "+gymList);
		
		return gymList;
	}
	
	//소유 체육관 정보 리스트
	public List<GymVo> gymSelectList(int sellNo){
		System.out.println("[GymDao] gymSelectList()");
		
		List<GymVo> gyminfoList = sqlSession.selectList("gym.gymSelectList", sellNo);
		System.out.println("[GymDao] gyminfoList>>> "+gyminfoList);
		
		return gyminfoList;
	}
	
	//체육관 정보 불러오기
	public GymVo gymSelectOne(int gymNo) {
		System.out.println("[GymDao] gymSelectOne()");
		
		GymVo gymVo = sqlSession.selectOne("gym.gymSelectOne", gymNo);
		System.out.println("[GymDao] gymList>>> "+gymVo);
		
		return gymVo;
	}
	
	//최근 체육관 번호 불러오기
	public GymVo gymNoSelectOne(int sellNo) {
		System.out.println("[GymDao] gymNoSelectOne()");
		
		GymVo vo = sqlSession.selectOne("gym.gymNoSelectOne", sellNo);
		//System.out.println("테스트 체육관 번호 "+vo);
		
		return vo;
	}
	
	//체육관 등록
	public void gymInsert(GymVo gymVo) {
		System.out.println("[GymDao] gymInsert()");
		
		sqlSession.insert("gym.gymInsert", gymVo);
		// by영훈 21-04-17 테스트용
		//sqlSession.insert("gym.bookingTest", gymVo);
	}
	
	//편의시설 불러오기
	public List<ConVo> conSelectList(int gymno) {
		System.out.println("[GymDao] conSelect()");
		
		List<ConVo> conList = sqlSession.selectList("gym.conSelectList", gymno);
		System.out.println("[GymDao] conList>>> "+conList);
		
		return conList;
	}
	
	//편의시설 등록
	public void conInsert(ConVo conVo) {
		System.out.println("[GymDao] conInsert() >>> "+conVo);
		
		sqlSession.insert("gym.conInsert", conVo);
	}
	
	//편의시설 상태 수정
	public void conUpdate(ConVo conVo) {
		System.out.println("[GymDao] conUpdate()");
		
		sqlSession.update("gym.conUpdate", conVo);
	}
	
	//체육관 이미지 등록
	public void gimgInsert(GymImgVo gimgVo) {
		System.out.println("[GymDao] gimgInsert()");
		
		sqlSession.insert("gym.gimgInsert", gimgVo);
	}
	
	//대관 등록
	public void bookInsert(BookingVo bookVo) {
		System.out.println("[GymDao] bookInsert()");
		sqlSession.insert("gym.bookInsert", bookVo);
	}
	
	//대관 리스트 불러오기
	public List<BookingVo> bookSelectList(int gymNo) {
		System.out.println("[GymDao] bookGetList()");
		List<BookingVo> bList = sqlSession.selectList("gym.bookSelectList", gymNo);
		System.out.println("대관리스트 출력테스트> "+bList);
		return bList;
	}
	
	//대관 삭제
	public void bookDelete(int bookno) {
		System.out.println("[GymDao] bookDelete()");
		sqlSession.delete("gym.bookDelete", bookno);
	}

	//대관 리스트 예약자 불러오기
	public List<BBuyVo> selectBBuyUser(int bookNo) {
		System.out.println("[GymDao] selectBBuyUser");
		
		return sqlSession.selectList("gym.selectBBuyUser",bookNo);
		
	}
}
