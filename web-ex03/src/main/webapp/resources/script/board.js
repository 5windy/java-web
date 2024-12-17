const deleteBoard = async (code) => {
	await fetch(`/board/delete?code=${code}`, {
		method: "DELETE"
	});
	location.href="/boards";
}