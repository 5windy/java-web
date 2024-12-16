package board.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BoardResponseDto {

	private int code;
	private String username;
	private String name;
	private String title;
	private String content;
	private String status;
	private String postDate;
	private int likeCnt;
	private int viewCnt;
	private Timestamp regDate;
	private Timestamp modDate;

	public BoardResponseDto() {

	}

	public BoardResponseDto(int code, String username, String name, String title, String content, String status,
			String postDate, int likeCnt, int viewCnt, Timestamp regDate, Timestamp modDate) {
		this.code = code;
		this.username = username;
		this.name = name;
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

	public void setCode(int code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

}
