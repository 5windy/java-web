<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/form.css">
<script src="/resources/script/validation-join.js"></script>
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	<form id="form-join" method="POST" action="/joinFormPro.jsp">
		<div>
			<input type="text" id="username" name="username" placeholder="아이디">
			<input type="password" id="password" name="password" placeholder="비밀번호">
			<input type="email" id="email" name="email" placeholder="[선택] 이메일주소 (비밀번호 찾기 등 본인 확인용)">
		</div>
		<ul class="error-msg-group">
			<li id="error-msg-username">아이디: 사용할 수 없는 아이디입니다. 다른 아이디를 입력해 주세요.</li>
			<li id="error-msg-password">비밀번호: 필수 정보입니다.</li>
		</ul>
		<div>
			<input type="text" id="name" name="name" placeholder="이름">
			<input type="number" id="birth" name="birth" placeholder="생년월일 8자리">
			<select name="telecom">
				<option value="" selected disabled>통신사 선택</option>
				<option value="skt">SKT</option>
				<option value="kt">KT</option>
				<option value="lgt">LG U+</option>
				<option value="skt-low">SKT 알뜰폰</option>
				<option value="kt-low">KT 알뜰폰</option>
				<option value="lgt-low">LG U+ 알뜰폰</option>
			</select>
			<div>
				<span>남자</span>
				<input type="radio" id="gender-male" name="gender" value="male">
				<span>여자</span>
				<input type="radio" id="gender-female" name="gender" value="female">
			</div>
			<div>
				<span>내국인</span>
				<input type="radio" id="country-local" name="country" value="local" checked>
				<span>외국인</span>
				<input type="radio" id="country-foreigner" name="country" value="foreigner">
			</div>
			<input type="text" id="phone" name="phone" placeholder="휴대전화번호">
		</div>
		<ul class="error-msg-group">
			<li id="error-msg-name">이름: 필수 정보입니다.</li>
			<li id="error-msg-birth">생년월일: 필수 정보입니다.</li>
			<li id="error-msg-telecom">통신사: 이용하는 통신사를 선택해 주세요.</li>
			<li id="error-msg-gender">성별: 성별을 선택해 주세요.</li>
			<li id="error-msg-phone">휴대전화번호: 필수 정보입니다.</li>
		</ul>
		<div>
			<input type="checkbox" id="agree" name="agree">
			<p><strong>[필수]</strong>인증 약관 전체동의</p>
		</div>
		<p id="error-msg-agree" class="error-msg">필수 약관에 모두 동의해 주세요.</p>
	
		<input type="submit" value="인증요청">
	</form>


</body>
</html>