window.onload = () => {
	const form = document.getElementById("form-join");
	
	const username = document.getElementById("username");
	const password = document.getElementById("password");
	const email = document.getElementById("email");
	const name = document.getElementById("name");
	const birth = document.getElementById("birth");
	const telecom = document.getElementById("telecom");
	
	const genderMale = document.getElementById("gender-male");
	const genderFemale = document.getElementById("gender-female");
	
	const countryLocal = document.getElementById("country-local");
	const countryForeigner = document.getElementById("country-foreigner");
	
	const phone = document.getElementById("phone");
	const agree = document.getElementById("agree");
	
	// 유효성 검사 로직 작성 
	// 1. 각 input 태그에 change event에 대한 리스너 달아주고,
	// 2. 이벤트 감지가 되면, 각 입력값에 대한 검증 처리를 하고 
	// 2-1. 조건부 오류 메세지 보여주기
	// 3. 모든 항목이 유효하면 
	// 4. submit 동작을 활성화
	
	let isValidUsername = false;
	let isValidPassword = false;
	let isValidName = false;
	let isValidBirth = false;
	let isValidTelecom = false;
	let isValidPhone = false;
	let isValidAgree = false;
	let isValidGender = false;
	
	username.addEventListener("change", e => {
		const input = e.target.value;
		
		const errEmpty = document.getElementById("error-msg-username-empty");
		const errPattern = document.getElementById("error-msg-username-pattern");
		const errDupl = document.getElementById("error-msg-username");
		
		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
		
		isValidUsername = validateUsername(input);
		
		if(!isValidUsername) {
			updateErrorElementStyle(errPattern, true);
		} else {
			updateErrorElementStyle(errPattern, false);
		}
	});
	
	username.addEventListener("focusout", e => {
		const input = e.target.value;
		
		const errEmpty = document.getElementById("error-msg-username-empty");
		
		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
	});
	
	password.addEventListener("change", e => {
		const input = e.target.value;
		
		const errPattern = document.getElementById("error-msg-password-pattern");
		const errEmpty = document.getElementById("error-msg-password-empty");
		
		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
		
		isValidPassword = validatePassword(input);
		
		if(!isValidPassword) {
			updateErrorElementStyle(errPattern, true);
		} else {
			updateErrorElementStyle(errPattern, false);			
		}
	});
	
	password.addEventListener("focusout", e => {
		const input = e.target.value;
		
		const errEmpty = document.getElementById("error-msg-password-empty");
		
		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
	});
	
	name.addEventListener("change", e => {
		const input = e.target.value;
				
		const errPattern = document.getElementById("error-msg-name-pattern");
		const errEmpty = document.getElementById("error-msg-name-empty");
		
		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
		
		isValidName = validateName(input);
		
		if(!isValidName) {
			updateErrorElementStyle(errPattern, true);
		} else {
			updateErrorElementStyle(errPattern, false);			
		}
	});
	
	name.addEventListener("focusout", e => {
		const input = e.target.value;
		
		const errEmpty = document.getElementById("error-msg-name-empty");
		
		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
	});
	
	birth.addEventListener("change", e => {
		const input = e.target.value;
						
		const errEmpty = document.getElementById("error-msg-birth-empty");
		const errPattern = document.getElementById("error-msg-birth-pattern");
		const errRange = document.getElementById("error-msg-birth-range");
		
		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
		
		isValidBirth = input.length === 8 && /^\d{8}$/.test(input);
				
		if(!isValidBirth) {
			updateErrorElementStyle(errPattern, true);
		} else {
			updateErrorElementStyle(errPattern, false);			
		}
		
		isValidBirth = validateBirth(input);
		
		if(!isValidBirth) {
			updateErrorElementStyle(errRange, true);
		} else {
			updateErrorElementStyle(errRange, false);			
		}
	});
	
	birth.addEventListener("focusout", e => {
		const input = e.target.value;
		
		const errEmpty = document.getElementById("error-msg-birth-empty");
		
		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
	});
	
	telecom.addEventListener("focusout", e => {
		const input = e.target.value;
		
		const errEmpty = document.getElementById("error-msg-telecom-empty");
		
		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			isValidTelecom = false;
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
			isValidTelecom = true;
		}
	});
	
	phone.addEventListener("change", e => {
		let input = e.target.value;
				
		const errPattern = document.getElementById("error-msg-phone-pattern");
		const errEmpty = document.getElementById("error-msg-phone-empty");

		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
		
		// 입력 문자열 중, 숫자 정보만 모아서 
		// ###-####-#### 포맷 안에 숫자 재배열 후, 
		// e.target.value 업데이트
		// input 업데이트
		input = formatPhoneString(input);
		phone.value = input;

		isValidPhone = validatePhone(input);

		if(!isValidPhone) {
			updateErrorElementStyle(errPattern, true);
		} else {
			updateErrorElementStyle(errPattern, false);			
		}
	});
	
	phone.addEventListener("focusout", e => {
		const input = e.target.value;
		
		const errEmpty = document.getElementById("error-msg-phone-empty");
		
		if(input === "") {
			updateErrorElementStyle(errEmpty, true);
			return;
		} else {
			updateErrorElementStyle(errEmpty, false);
		}
	});
	  
	form.addEventListener("submit", e => {
		e.preventDefault();	// 기본 동작 방지
		
		const errGenderEmpty = document.getElementById("error-msg-gender-empty");
		
		if(!genderMale.checked && !genderFemale.checked) {
			updateErrorElementStyle(errGenderEmpty, true);
			isValidGender = false;			
		} else {
			updateErrorElementStyle(errGenderEmpty, false);
			isValidGender = true;			
		}
		
		const agree = document.getElementById("agree");
		
		const errAgree = document.getElementById("error-msg-agree");
		
		if(!agree.checked) {
			updateErrorElementStyle(errAgree, true);
			isValidAgree = false;
		} else {
			updateErrorElementStyle(errAgree, false);
			isValidAgree = true;
		}
		
		/*
		let isValidUsername = false;
		let isValidPassword = false;
		let isValidName = false;
		let isValidBirth = false;
		let isValidTelecom = false;
		let isValidPhone = false;
		let isValidAgree = false;
		let isValidGender = false;
		*/
		
		if(!isValidUsername && username.value === "") {
			const error = document.getElementById("error-msg-username-empty");
			updateErrorElementStyle(error, true);
		}
		
		if(!isValidPassword && password.value === "") {
			const error = document.getElementById("error-msg-password-empty");
			updateErrorElementStyle(error, true);
		}
		
		if(!isValidName && name.value === "") {
			const error = document.getElementById("error-msg-name-empty");
			updateErrorElementStyle(error, true);			
		}
		
		if(!isValidBirth && birth.value === "") {
			const error = document.getElementById("error-msg-birth-empty");
			updateErrorElementStyle(error, true);						
		}
		
		if(!isValidTelecom && telecom.value === "") {
			const error = document.getElementById("error-msg-telecom-empty");
			updateErrorElementStyle(error, true);									
		}
		
		if(!isValidPhone && phone.value === "") {
			const error = document.getElementById("error-msg-phone-empty");
			updateErrorElementStyle(error, true);												
		}	
		
		if(isValidUsername && isValidPassword && isValidName 
			&& isValidBirth && isValidTelecom && isValidPhone && isValidAgree && isValidGender) {
				form.submit();
		} 
	});
	
}

function formatPhoneString(str) {
	let result = "";
	
	str = str.replaceAll(/\D/g, "");	// 숫자만 문자열 안에 남음
	console.log("str : ", str);
	
	if(str.length === 11 && /^[010]/.test(str)) {
		const head = str.substring(0, 3);
		const mid = str.substring(3,7);
		const tail = str.substring(7,11);		
		result = `${head}-${mid}-${tail}`;
	} else if(str.length === 10 && /^[011|016|017|018|019]/.test(str)) {
		const head = str.substring(0, 3);
		const mid = str.substring(3,6);
		const tail = str.substring(6,10);		
		result = `${head}-${mid}-${tail}`;
	} else {
		return str;
	}
	
	return result;
}


function updateErrorElementStyle(element, visible) {
	if(visible) {
		element.style.display = "block";
	} else {
		element.style.display = "none";	
	}
}

function validateUsername(username) {
	const regex = /^[a-z0-9](?:[_-]*[a-z0-9]){4,19}$/;
	return regex.test(username);
}

function validatePassword(password) {
	const regex = /^[a-zA-Z0-9_\-~`!@#$%^&*()+=|\\'";:.,/]{8,16}$/;
	return regex.test(password);
}

function validateName(name) {
	const regex = /^[가-힣a-zA-Z]+$/;
	return regex.test(name);
}

function validateBirth(birth) {
	const year = parseInt(birth.substring(0, 4));
	const month = parseInt(birth.substring(4, 6)) - 1;
	const day = parseInt(birth.substring(6, 8));
	
	const today = new Date();
	const target = new Date(year, month, day);
	
	return today.getTime() >= target.getTime();
}

function validatePhone(phone) {
	const regex = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
	return regex.test(phone);
}

