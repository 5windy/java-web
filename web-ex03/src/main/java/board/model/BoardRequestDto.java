package board.model;

public class BoardRequestDto {

	private String username;
	private String title;
	private String content;
	private String status;
	private String postDate;

	public BoardRequestDto() {

	}

	public BoardRequestDto(String username, String title, String content) {
		super();
		this.username = username;
		this.title = title;
		this.content = content;
	}

	public BoardRequestDto(String username, String title, String content, String status, String postDate) {
		super();
		this.username = username;
		this.title = title;
		this.content = content;
		this.status = status;
		this.postDate = postDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

}
