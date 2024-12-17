import { validatePassword, validatePhone, formatPhoneString, updateErrorElementStyle, checkDuplEmail, checkDuplPhone } from "./validation.js";

window.onload = () => {
	const form = document.querySelector("form");

	const password = document.getElementById("password");
	const newPassword = document.getElementById("new-password");
	const phone = document.getElementById("phone");
	const email = document.getElementById("email");

	const originEmail = document.getElementById("user-email");
	const originPhone = document.getElementById("user-phone");

	let isValidPassword = false;
	let isValidNewPassword = false;
	let isValidPhone = false;
	let isValidEmail = false;

	password.addEventListener("change", e => {
		const errMsgEmpty = document.getElementById("error-msg-password-empty");
		isValidPassword = password.value !== "";
		updateErrorElementStyle(errMsgEmpty, !isValidPassword);
	});

	newPassword.addEventListener("change", e => {
		const errMsg = document.getElementById("error-msg-password-pattern");
		isValidNewPassword = validatePassword(e.target.value);
		updateErrorElementStyle(errMsg, !isValidNewPassword);

		const errMsgEmpty = document.getElementById("error-msg-password-empty");
		isValidPassword = password.value !== "";
		updateErrorElementStyle(errMsgEmpty, !isValidPassword);
	});

	phone.addEventListener("change", async (e) => {
		phone.value = formatPhoneString(e.target.value);

		const errMsgPattern = document.getElementById("error-msg-phone-pattern");
		isValidPhone = validatePhone(phone.value);
		updateErrorElementStyle(errMsgPattern, !isValidPhone);

		const errMsg = document.getElementById("error-msg-phone");
		isValidPhone = await checkDuplPhone(phone.value);
		isValidPhone = !isValidPhone ? phone.value === originPhone.value : isValidPhone;
		updateErrorElementStyle(errMsg, !isValidPhone);
	});

	email.addEventListener("change", async (e) => {
		const errMsg = document.getElementById("error-msg-email");

		isValidEmail = await checkDuplEmail(email.value);
		isValidEmail = !isValidEmail ? email.value === originEmail.value : isValidEmail;

		updateErrorElementStyle(errMsg, !isValidEmail);
	});

	form.addEventListener("submit", async (e) => {
		e.preventDefault();

		if (newPassword.value !== "") {
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

		isValidEmail = await checkDuplEmail(email.value);
		isValidEmail = !isValidEmail ? email.value === originEmail.value : isValidEmail;

		isValidPhone = await checkDuplPhone(phone.value);
		isValidPhone = !isValidPhone ? phone.value === originPhone.value : isValidPhone;

		if (newPassword.value !== "" && isValidPassword && isValidNewPassword && isValidEmail) {
			form.submit();
		} else if (newPassword.value === "" && isValidPhone && isValidEmail) {
			form.submit();
		}
	});
}