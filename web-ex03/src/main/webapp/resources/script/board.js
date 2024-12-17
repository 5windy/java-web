const deleteBoard = async (code) => {
	await fetch(`/service?command=delete-board&code=${code}`, {
		method: "DELETE"
	});
	location.href="/service?command=list";
}