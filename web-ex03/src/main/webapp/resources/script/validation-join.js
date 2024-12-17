import { updateErrorElementStyle, validateUsername, validatePassword, validateName, validateBirth, validatePhone, formatPhoneString } from "./validation.js";

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
	
	let isValidUsername = validateUsername(username.value);
	let isValidPassword = validatePassword(password.value);
	let isValidName = validateName(name.value);
	let isValidBirth = validateBirth(birth.value);
	let isValidTelecom = telecom.value !== "";
	let isValidPhone = validatePhone(phone.value);
	let isValidAgree = false;
	let isValidGender = genderMale.checked || genderFemale.checked;
	
	username.addEventListener("change", async(e) => {
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
		
		isValidUsername = await checkDuplUsername(input);
		updateErrorElementStyle(errDupl, !isValidUsername);
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
	  
	form.addEventListener("submit", async(e) => {
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
		
		isValidUsername = await checkDuplUsername(username.value);
		
		if(isValidUsername && isValidPassword && isValidName 
			&& isValidBirth && isValidTelecom && isValidPhone && isValidAgree && isValidGender) {
				form.submit();
		} 
	});
	
}

async function checkDuplUsername(username) {
	const response = await fetch("/service/api?command=search-username", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"username" : username
		})
	});
	const json = await response.json();
	
	return json.isValid;
}