package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaex.dao.UserDao;
import com.javaex.vo.KakaoVo;
import com.javaex.vo.NaverVo;
import com.javaex.vo.TokenVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userdao;

	// 로그인
	public UserVo login(UserVo uservo) {
		return userdao.selectUser(uservo);
	}

	// 카카오 회원가입
	public UserVo kakaoJoin(String code) {

		System.out.println("[service]카카오 회원가입");
		RestTemplate rt = new RestTemplate();

		// HttpHeaders 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		String key = "e90198f391392e46dc3459bec3b08f4b";
		String redirect_uri = "http://localhost:8088/Sparkling_/user/join";

		params.add("grant_type", "authorization_code");
		params.add("client_id", key);
		params.add("redirect_uri", redirect_uri);
		params.add("code", code);

		// HttpHeaders, HttpBody 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kokoaTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - post방식으로 그리고 response 변수의 응답을 받음
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kokoaTokenRequest, String.class);

		// Gson, Json simple, objectMapper
		TokenVo kakaovo = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			kakaovo = objectMapper.readValue(response.getBody(), TokenVo.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		RestTemplate rt2 = new RestTemplate();

		// HttpHeaders 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + kakaovo.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeaders, HttpBody 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kokoaProfileRequest2 = new HttpEntity<>(headers2);

		// Http 요청하기 - post방식으로 그리고 response 변수의 응답을 받음
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kokoaProfileRequest2, String.class);

		// Gson, Json simple, objectMapper
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoVo KakaoUserinfo = null;

		try {
			KakaoUserinfo = objectMapper2.readValue(response2.getBody(), KakaoVo.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		UUID uuidpassword = UUID.randomUUID();

		UserVo uservo = new UserVo();
		
		String user_id = "kakao" + KakaoUserinfo.getId();
		
		uservo.setUser_id(user_id);
		uservo.setPassword(uuidpassword.toString());
		uservo.setNickname(KakaoUserinfo.getProperties().getNickname());

		System.out.println("회원가입 여부확인");
		int count = userdao.joinUserIdChechSelect(user_id);

		if (count == 1) {
			userdao.selectUser(uservo);
			return uservo;
		} else {
			userdao.kakaoInsert(uservo);
			uservo.setUser_no(uservo.getUser_no());
			return uservo;
		}
	}
	
	//네이버 회원가입
	public UserVo naverJoin(String code, String state) {
		
		
		System.out.println("[service]네이버 회원가입");
		RestTemplate rt = new RestTemplate();	
		
		// HttpHeaders 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Content-type", "json");
		
		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		String key = "9ElAXUf0q0NhBnY7bqKl";
		String client_secret = "sAPPTwOGRF";
	

		params.add("grant_type", "authorization_code");
		params.add("client_id", key);
		params.add("client_secret", client_secret);
		params.add("code", code);
		params.add("state", state);


		
		

		// HttpHeaders, HttpBody 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> naverTokenRequest 
		= new HttpEntity<>(params, headers);

		// Http 요청하기 - post방식으로 그리고 response 변수의 응답을 받음
		ResponseEntity<String> response = rt.exchange("https://nid.naver.com/oauth2.0/token", HttpMethod.POST,
				naverTokenRequest, String.class);

		// Gson, Json simple, objectMapper
		TokenVo navervo = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			navervo = objectMapper.readValue(response.getBody(), TokenVo.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		System.out.println(navervo.getAccess_token());
		
		
		
		RestTemplate rt2 = new RestTemplate();

		// HttpHeaders 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + navervo.getAccess_token());

		// HttpHeaders, HttpBody 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> naverTokenRequest2 = new HttpEntity<>(headers2);

		// Http 요청하기 - post방식으로 그리고 response 변수의 응답을 받음
		ResponseEntity<String> response2 = rt2.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.POST,
				naverTokenRequest2, String.class);

		// Gson, Json simple, objectMapper
				ObjectMapper objectMapper2 = new ObjectMapper();
				NaverVo naverUserinfo = null;

				try {
					naverUserinfo = objectMapper2.readValue(response2.getBody(), NaverVo.class);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		
				UUID uuidpassword = UUID.randomUUID();
		
				UserVo uservo = new UserVo();
				
				String user_id = "naver" + naverUserinfo.getResponse().getId();
				uservo.setUser_id(user_id);
				uservo.setPassword(uuidpassword.toString());
				uservo.setNickname(naverUserinfo.getResponse().getNickname());
				uservo.setGender(naverUserinfo.getResponse().getGender());
				uservo.setUser_name(naverUserinfo.getResponse().getName());

				System.out.println("회원가입 여부확인");
				int count = userdao.joinUserIdChechSelect(user_id);
		
				if (count == 1) {
					userdao.selectUser(uservo);
					return uservo;
				} else {
					userdao.naverInsert(uservo);
					uservo.setUser_no(uservo.getUser_no());
					return uservo;
				}		
	}
	
	//회원가입-회원정보입력
	public void joininfoWrite(UserVo uservo, MultipartFile profilphoto) {
		
		System.out.println("[service]회원가입 회원정보입력");
		
		
		//db 저장할 정보 수집
		String saveDir = "C:\\javaStudy\\upload";
			
		//오리지널 파일이름
		String orgName = profilphoto.getOriginalFilename();

		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));

		//서버 저장 파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName" + saveName);
		
		//서버 파일 패스 --> 저장경로
		String filePath = saveDir + "\\" + saveName;
		
		//서버하드디스크 파일저장
		try {
			profilphoto.transferTo(new File(filePath));
			uservo.setUser_photo(saveName);
			
			userdao.joininfoUPdate(uservo);
			
		} catch(IOException e) {
			e.printStackTrace();
			
		}

	}
}
