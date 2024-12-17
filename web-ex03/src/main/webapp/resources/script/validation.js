export function updateErrorElementStyle(element, visible) {
	if(visible) {
		element.style.display = "block";
	} else {
		element.style.display = "none";	
	}
}

export function validateUsername(username) {
	const regex = /^[a-z0-9](?:[_-]*[a-z0-9]){4,19}$/;
	return regex.test(username);
}

export function validatePassword(password) {
	const regex = /^[a-zA-Z0-9_\-~`!@#$%^&*()+=|\\'";:.,/]{8,16}$/;
	return regex.test(password);
}

export function validateName(name) {
	const regex = /^[가-힣a-zA-Z]+$/;
	return regex.test(name);
}

export function validateBirth(birth) {
	const year = parseInt(birth.substring(0, 4));
	const month = parseInt(birth.substring(4, 6)) - 1;
	const day = parseInt(birth.substring(6, 8));
	
	const today = new Date();
	const target = new Date(year, month, day);
	
	return today.getTime() >= target.getTime();
}

export function validatePhone(phone) {
	const regex = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
	return regex.test(phone);
}

export function formatPhoneString(str) {
	let result = "";
	
	str = str.replaceAll(/\D/g, "");	// 숫자만 문자열 안에 남음
	
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