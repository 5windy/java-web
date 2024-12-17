package board.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Board {

	private int code;
	private String username;
	private String title;
	private String content;
	private String status;
	private LocalDateTime postDate;
	private int likeCnt;
	private int viewCnt;
	private Timestamp regDate;
	private Timestamp modDate;

	public Board(int code, String username, String title, String content, String status, LocalDateTime postDate,
			int likeCnt, int viewCnt, Timestamp regDate, Timestamp modDate) {
		this.code = code;
		this.username = username;
		this.title = title;
		this.content = content;
		this.status = status;
		this.postDate = postDate;
		this.likeCnt = likeCnt;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public int getCode() {
		return code;
	}

	public String getUsername() {
		return username;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getStatus() {
		return status;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

}
