<%@page import="user.model.User"%>
<%@page import="user.model.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/style/form.css">
<script type="module" src="/resources/script/validation-update.js"></script>
<title>마이페이지</title>
</head>
<body>
<c:if test="${empty log }">
	<c:redirect url="/login" />
</c:if>

<%-- 
Update Form 만들기 
action : /updateFormPro.jsp
button :
ㄴ 수정 -> submit 
ㄴ 탈퇴 -> /deleteForm.jsp 로 이동 (비밀번호 검증 후 처리)
ㄴ 로그아웃 -> /logoutPro.jsp 로 이동

부분적으로 수정가능하며 (username, name, birth, gender disabled)
email, phone의 경우에는 unique 검증
--%>

<h4>${log.name}님 환영합니다!</h4>
<h1>회원정보</h1>
	<form method="POST" action="/user/update">
		<div>
			<input type="text" id="username" name="username" placeholder="아이디" value="${log.username}" disabled>
			<input type="password" id="password" name="password" placeholder="기존 비밀번호">
			<input type="password" id="new-password" name="new-password" placeholder="새로운 비밀번호">
			<input type="email" id="email" name="email" placeholder="[선택] 이메일주소 (비밀번호 찾기 등 본인 확인용)" value="${log.email}">
		</div>
		<ul class="error-msg-group">
			<li id="error-msg-password-empty">기존 비밀번호: 비밀번호 변경 시, 필수값입니다.</li>
			<li id="error-msg-password-pattern">새로운 비밀번호: 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.</li>
		</ul>
		<div>
			<input type="text" id="name" name="name" placeholder="이름" value="${log.name}" disabled>
			<input type="number" id="birth" name="birth" placeholder="생년월일 8자리" value="${log.birth.toString().replaceAll('-', '') }" disabled>
			<select id="telecom" name="telecom">
				<option value="" ${empty log ? "selected" : "" } disabled>통신사 선택</option>
				<option value="1" ${log.telecom eq 1 ? "selected" : ""}>SKT</option>
				<option value="2" ${log.telecom eq 2 ? "selected" : ""}>KT</option>
				<option value="3" ${log.telecom eq 3 ? "selected" : ""}>LG U+</option>
				<option value="4" ${log.telecom eq 4 ? "selected" : ""}>SKT 알뜰폰</option>
				<option value="5" ${log.telecom eq 5 ? "selected" : ""}>KT 알뜰폰</option>
				<option value="6" ${log.telecom eq 6 ? "selected" : ""}>LG U+ 알뜰폰</option>
			</select>
			<div id="radios">
				<div class="radio-group">
					<input type="radio" id="gender-male" name="gender" value="male" ${log.gender eq "male" ? "checked" : "" } disabled>
					<input type="radio" id="gender-female" name="gender" value="female" ${log.gender eq "female" ? "checked" : ""} disabled>
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
					<input type="radio" id="country-local" name="country" value="local" ${empty log or log.country eq "local" ? "checked" : "" }>
					<input type="radio" id="country-foreigner" name="country" value="foreigner" ${log.country eq "foreigner" ? "checked" : ""}>
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
			<input type="text" id="phone" name="phone" placeholder="휴대전화번호" value="${log.phone}">
		</div>
		<ul class="error-msg-group">
			<li id="error-msg-phone-pattern">휴대전화번호: 휴대전화번호가 정확한지 확인해 주세요.</li>
		</ul>
	
		<div id="btn-group">
			<input type="submit" value="수정">
			<input type="button" value="로그아웃" onclick="location.href='/user/logout'">
			<input type="button" value="탈퇴" onclick="location.href='/delete'">
		</div>
	</form>

</body>
</html>