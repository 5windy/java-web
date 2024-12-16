package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class BoardDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private BoardDao() {
	}

	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	public BoardResponseDto createBoard(BoardRequestDto boardDto) {
		BoardResponseDto board = null;

		conn = DBManager.getConnection();

		if (conn != null) {
			try {
				setInsertPstmtInstance(boardDto);

				pstmt.setString(1, boardDto.getUsername());
				pstmt.setString(2, boardDto.getTitle());
				pstmt.setString(3, boardDto.getContent());
				pstmt.execute();

				board = findLatestBoardByUsername(boardDto.getUsername());
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
		return board;
	}

	private void setInsertPstmtInstance(BoardRequestDto boardDto) throws SQLException {
		String sql = "INSERT INTO boards(username, title, content) VALUES(?, ?, ?)";

		String status = boardDto.getStatus();

		switch (status) {
		case "hide":
			sql = "INSERT INTO boards(username, title, content, status) VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(4, boardDto.getStatus());
			return;
		case "reserv":
			sql = "INSERT INTO boards(username, title, content, status, post_date) VALUES(?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(4, boardDto.getStatus());
			pstmt.setString(5, boardDto.getPostDate().toString());
			return;
		default:
			pstmt = conn.prepareStatement(sql);
			return;
		}
	}

	public int getTotalSize() {
		int size = 0;

		conn = DBManager.getConnection();

		String sql = "SELECT COUNT(*) FROM boards";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				size = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return size;
	}

	public List<BoardResponseDto> findBoardAll(int page) {
		List<BoardResponseDto> list = new ArrayList<>();

		conn = DBManager.getConnection();

		String sql = "SELECT * FROM boards_view LIMIT 10 OFFSET ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page - 1) * 10);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int code = rs.getInt(1);
				String username = rs.getString(2);
				String title = rs.getString(3);
				String contet = rs.getString(4);
				String status = rs.getString(5);
				String postDate = rs.getString(6);
				int likeCnt = rs.getInt(7);
				int viewCnt = rs.getInt(8);
				Timestamp regDate = rs.getTimestamp(9);
				Timestamp modDate = rs.getTimestamp(10);
				String name = rs.getString(11);

				BoardResponseDto boardDto = new BoardResponseDto(code, username, name, title, contet, status, postDate,
						likeCnt, viewCnt, regDate, modDate);
				list.add(boardDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public List<BoardResponseDto> findBoardAllByUsername(String username, int page) {
		List<BoardResponseDto> list = new ArrayList<>();

		conn = DBManager.getConnection();

		String sql = "SELECT * FROM boards_view LIMIT 10 OFFSET ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page - 1) * 10);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int code = rs.getInt(1);
				String title = rs.getString(3);
				String contet = rs.getString(4);
				String status = rs.getString(5);
				String postDate = rs.getString(6);
				int likeCnt = rs.getInt(7);
				int viewCnt = rs.getInt(8);
				Timestamp regDate = rs.getTimestamp(9);
				Timestamp modDate = rs.getTimestamp(10);
				String name = rs.getString(11);

				BoardResponseDto boardDto = new BoardResponseDto(code, username, name, title, contet, status, postDate,
						likeCnt, viewCnt, regDate, modDate);
				list.add(boardDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public BoardResponseDto findBoardByCode(int code) {
		BoardResponseDto board = null;

		conn = DBManager.getConnection();

		String sql = "SELECT * FROM boards_view WHERE code=? AND (status='show' OR status='reserv' AND post_date <= NOW())";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String username = rs.getString(2);
				String title = rs.getString(3);
				String contet = rs.getString(4);
				String status = rs.getString(5);
				String postDate = rs.getString(6);
				int likeCnt = rs.getInt(7);
				int viewCnt = rs.getInt(8);
				Timestamp regDate = rs.getTimestamp(9);
				Timestamp modDate = rs.getTimestamp(10);
				String name = rs.getString(11);

				board = new BoardResponseDto(code, username, name, title, contet, status, postDate, likeCnt, viewCnt,
						regDate, modDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return board;
	}

	private BoardResponseDto findLatestBoardByUsername(String username) {
		BoardResponseDto board = null;

		conn = DBManager.getConnection();

		String sql = "SELECT * FROM boards_view WHERE username=? ORDER BY code DESC LIMIT 1";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int code = rs.getInt(1);
				String title = rs.getString(3);
				String contet = rs.getString(4);
				String status = rs.getString(5);
				String postDate = rs.getString(6);
				int likeCnt = rs.getInt(7);
				int viewCnt = rs.getInt(8);
				Timestamp regDate = rs.getTimestamp(9);
				Timestamp modDate = rs.getTimestamp(10);
				String name = rs.getString(11);

				board = new BoardResponseDto(code, username, name, title, contet, status, postDate, likeCnt, viewCnt,
						regDate, modDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return board;
	}

	public BoardResponseDto updateBoard(int code, BoardRequestDto boardDto) {
		BoardResponseDto board = null;

		conn = DBManager.getConnection();

		String sql = "UPDATE boards SET title=?, content=?, status=?, post_date=? WHERE code=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardDto.getTitle());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setString(3, boardDto.getStatus());
			pstmt.setString(4, boardDto.getPostDate());
			pstmt.setInt(5, code);
			pstmt.execute();

			board = findBoardByCode(code);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return board;
	}

	public void deleteBoardByCode(int code) {
		conn = DBManager.getConnection();

		String sql = "DELETE FROM boards WHERE code=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
}
