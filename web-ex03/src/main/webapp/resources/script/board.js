const deleteBoard = async (code) => {
	await fetch(`/service/boards?command=delete&code=${code}`, {
		method: "DELETE"
	});
	location.href="/service/boards?command=list";
}

const fetchData = async(page) => {
	const response = await fetch(`/service/api?command=boards&page=${page}`); 
	const list = await response.json();
	return list;
}

window.onload = async() => {
	const path = location.pathname;
	
	// '?page=1&param=apple'
	const search = location.search;
	const info = search.split(/\?|=|&/);
	const index = info.indexOf("page");
	
	let page = index != -1 ? parseInt(info[index + 1]) : 1;
	
	if(path === "/list") {
		const listContainer = document.getElementById("list-container");
		
		let list = await fetchData(page);
		if(page > 1 && list.length === 0) {
			list = await fetchData();
		}
		
		list.forEach(board => {
			listContainer.innerHTML += `
			<div>
				<span class="no">${board.code}</span> 
				<span class="title"><a href="/service/boards?command=view&no=${board.code }">${board.title}</a></span>
				<span class="author">${board.name }</span>
				<span class="reg-date">${board.regDate.split(' ')[0]}</span>
				<span class="view-cnt">${board.viewCnt }</span>
				<span class="like-cnt">${board.likeCnt }</span>
			</div>
			`;
		});
	}
}