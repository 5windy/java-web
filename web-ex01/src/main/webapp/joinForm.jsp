<%@page import="java.sql.Date"%>
<%@page import="user.model.UserRequestDto"%>
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
	<%
	UserRequestDto userDto = (UserRequestDto) request.getAttribute("userData");
	
	int telecom = 0;
	Date date = null;
	String birth = "";
	
	if(userDto != null) {
		telecom = userDto.getTelecom();
		date = userDto.getBirth();
		birth = date.toString();
		birth = birth.replaceAll("-", "");
	}
	
	%>
	<h1>회원가입</h1>
	<form id="form-join" method="POST" action="/joinFormPro.jsp">
		<p id="info">실명 인증된 아이디로 가입<span></span></p>
		<div>
			<input type="text" id="username" name="username" placeholder="아이디" value="<%=userDto != null ? userDto.getUsername() : ""%>">
			<input type="password" id="password" name="password" placeholder="비밀번호">
			<input type="email" id="email" name="email" placeholder="[선택] 이메일주소 (비밀번호 찾기 등 본인 확인용)" value="<%=userDto != null ? userDto.getEmail() : ""%>">
		</div>
		<ul class="error-msg-group">
			<li id="error-msg-username-pattern">아이디: 5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.</li>
			<li id="error-msg-username">아이디: 사용할 수 없는 아이디입니다. 다른 아이디를 입력해 주세요.</li>
			<li id="error-msg-username-empty">아이디: 필수 정보입니다.</li>
			<li id="error-msg-password-pattern">비밀번호: 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.</li>
			<li id="error-msg-password-empty">비밀번호: 필수 정보입니다.</li>
		</ul>
		<div>
			<input type="text" id="name" name="name" placeholder="이름" value="<%=userDto != null ? userDto.getName() : ""%>">
			<input type="number" id="birth" name="birth" placeholder="생년월일 8자리" value="<%=birth%>">
			<select id="telecom" name="telecom">
				<option value="" <%=telecom == 0 ? "selected" : ""%> disabled>통신사 선택</option>
				<option value="1" <%=telecom == 1 ? "selected" : "" %>>SKT</option>
				<option value="2" <%=telecom == 2 ? "selected" : "" %>>KT</option>
				<option value="3" <%=telecom == 3 ? "selected" : "" %>>LG U+</option>
				<option value="4" <%=telecom == 4 ? "selected" : "" %>>SKT 알뜰폰</option>
				<option value="5" <%=telecom == 5 ? "selected" : "" %>>KT 알뜰폰</option>
				<option value="6" <%=telecom == 6 ? "selected" : "" %>>LG U+ 알뜰폰</option>
			</select>
			<div id="radios">
				<div class="radio-group">
					<input type="radio" id="gender-male" name="gender" value="male" <%=userDto != null && userDto.getGender().equals("male") ? "checked" : ""%>>
					<input type="radio" id="gender-female" name="gender" value="female" <%=userDto != null && userDto.getGender().equals("female") ? "checked" : ""%>>
					<div>
						<label for="gender-male">
							<span id="label-for-gender-male">남자</span>
						</label>
						<label for="gender-female">
							<span id="label-for-gender-female">여자</span>
						</label>
					</div>
				</div>
				<div class="radio-group">
					<input type="radio" id="country-local" name="country" value="local" <%=userDto == null || (userDto != null && userDto.getCountry().equals("local")) ? "checked" : ""%>>
					<input type="radio" id="country-foreigner" name="country" value="foreigner" <%=userDto != null && userDto.getCountry().equals("foreigner") ? "checked" : ""%>>
					<div>
						<label for="country-local">
							<span id="label-for-country-local">내국인</span>
						</label>
						<label for="country-foreigner">
							<span id="label-for-country-foreigner">외국인</span>
						</label>
					</div>
				</div>
			</div>
			<input type="text" id="phone" name="phone" placeholder="휴대전화번호" value="<%=userDto != null ? userDto.getPhone() : ""%>">
		</div>
		<ul class="error-msg-group">
			<li id="error-msg-name-empty">이름: 필수 정보입니다.</li>
			<li id="error-msg-name-pattern">이름: 한글, 영문 대/소문자를 사용해 주세요. (특수기호, 공백 사용 불가)</li>
			<li id="error-msg-birth-empty">생년월일: 필수 정보입니다.</li>
			<li id="error-msg-birth-pattern">생년월일: 생년월일은 8자리 숫자로 입력해 주세요.</li>
			<li id="error-msg-birth-range">생년월일: 생년월일이 정확한지 확인해 주세요.</li>
			<li id="error-msg-telecom-empty">통신사: 이용하는 통신사를 선택해 주세요.</li>
			<li id="error-msg-gender-empty">성별: 성별을 선택해 주세요.</li>
			<li id="error-msg-phone-empty">휴대전화번호: 필수 정보입니다.</li>
			<li id="error-msg-phone-pattern">휴대전화번호: 휴대전화번호가 정확한지 확인해 주세요.</li>
		</ul>
		<div id="agree-box">
			<input type="checkbox" id="agree" name="agree">
			<label for="agree">
				<div id="icon-img"></div>
				<p><strong>[필수]</strong>인증 약관 전체동의</p>
			</label>
		</div>
		<p id="error-msg-agree" class="error-msg">필수 약관에 모두 동의해 주세요.</p>
	
		<input type="submit" value="인증요청">
	</form>

</body>
</html>