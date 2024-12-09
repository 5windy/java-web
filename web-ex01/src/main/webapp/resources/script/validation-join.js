window.onload = () => {
	const form = document.getElementById("form-join");
	
	form.addEventListener("submit", e => {
		e.preventDefault();	// 기본 동작 방지
		
		// 유효성 검사 로직 작성 
		// 1. 각 input 태그에 change event에 대한 리스너 달아주고,
		// 2. 이벤트 감지가 되면, 각 입력값에 대한 검증 처리를 하고 
		// 2-1. 조건부 오류 메세지 보여주기
		// 3. 모든 항목이 유효하면 
		// 4. submit 동작을 활성화  
		
		// form.submit();
	})
	
}