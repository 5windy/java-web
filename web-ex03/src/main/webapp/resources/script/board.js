const deleteBoard = async (code) => {
	await fetch(`/service/boards?command=delete&code=${code}`, {
		method: "DELETE"
	});
	location.href="/service/boards?command=list";
}