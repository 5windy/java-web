import { updateErrorElementStyle, validateUsername, validatePassword } from "./validation.js";

window.onload = () => {
	const username = document.getElementById("username");
	const password = document.getElementById("password");
	
	const form = document.querySelector("form");
	
	form.addEventListener("submit", e => {
		e.preventDefault();
		
		// 모든 에러 메세지 숨기기 
		const errMsgGroup = document.getElementsByClassName("error-msg");
		for(let i=0; i<errMsgGroup.length; i++) {
			updateErrorElementStyle(errMsgGroup[i], false);
		}
		
		let isValid = true;
		
		isValid = username.value !== "";
		
		if(!isValid) {
			const errMsg = document.getElementById("error-msg-username-empty");
			updateErrorElementStyle(errMsg, true);
			return;
		}
		
		isValid = validateUsername(username.value);
		
		if(!isValid) {
			const errMsg = document.getElementById("error-msg-username-pattern");
			updateErrorElementStyle(errMsg, true);
			return;
		}
		
		isValid = password.value !== "";
		
		if(!isValid) {
			const errMsg = document.getElementById("error-msg-password-empty");
			updateErrorElementStyle(errMsg, true);
			return;
		}
		
		isValid = validatePassword(password.value);
		
		if(!isValid) {
			const errMsg = document.getElementById("error-msg-password-pattern");
			updateErrorElementStyle(errMsg, true);
			return;
		}
		
		if(isValid) {
			form.submit();
		}
	});
	
}