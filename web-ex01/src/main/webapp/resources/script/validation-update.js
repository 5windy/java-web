import {validatePassword, validatePhone, formatPhoneString, updateErrorElementStyle} from "./validation.js";

window.onload = () => {
	const form = document.querySelector("form");	
	
	const password = document.getElementById("password");
	const newPassword = document.getElementById("new-password");
	const phone = document.getElementById("phone");
	
	let isValidPassword = false;
	let isValidNewPassword = false;
	let isValidPhone = false;
	
	password.addEventListener("change", e => {
		const errMsgEmpty = document.getElementById("error-msg-password-empty");
		isValidPassword = password.value !== "";
		updateErrorElementStyle(errMsgEmpty, !isValidPassword);
	})
	
	newPassword.addEventListener("change", e => {
		const errMsg = document.getElementById("error-msg-password-pattern");
		isValidNewPassword = validatePassword(e.target.value);
		updateErrorElementStyle(errMsg, !isValidNewPassword);
		
		const errMsgEmpty = document.getElementById("error-msg-password-empty");
		isValidPassword = password.value !== "";
		updateErrorElementStyle(errMsgEmpty, !isValidPassword);
	});
	
	phone.addEventListener("change", e => {
		phone.value = formatPhoneString(e.target.value);
		
		const errMsg = document.getElementById("error-msg-phone-pattern");
		isValidPhone = validatePhone(phone.value);
		updateErrorElementStyle(errMsg, !isValidPhone);
	});
	
	form.addEventListener("submit", e => {
		e.preventDefault();
		
		if(newPassword.value !== "") {
			const errMsgEmpty = document.getElementById("error-msg-password-pattern");
			isValidPassword = password.value !== "";
			updateErrorElementStyle(errMsgEmpty, !isValidPassword);
			
			const errMsg = document.getElementById("error-msg-password-pattern");
			isValidNewPassword = validatePassword(newPassword.value);
			updateErrorElementStyle(errMsg, !isValidNewPassword);
		}
		
		const errMsg = document.getElementById("error-msg-phone-pattern");
		isValidPhone = validatePhone(phone.value);
		updateErrorElementStyle(errMsg, !isValidPhone);
		
		if(newPassword.value !== "" && isValidPassword && isValidNewPassword) {
			form.submit();		
		} else if(newPassword.value === "" && isValidPhone) {
			form.submit();					
		}
	});
}