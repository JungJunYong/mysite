package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public BoardVo finByNo(Long no) {
		BoardVo vo = null;
		try {
			conn = getConnection();
			vo = new BoardVo();
			String sql = "select a.title,a.contents,b.name,a.g_no,a.o_no,a.depth,a.hit,a.delet from board a,user b\r\n"
					+ "where a.user_no = b.no\r\n" + "and a.no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				String name = rs.getString(3);
				int gNo = rs.getInt(4);
				int oNo = rs.getInt(5);
				int depth = rs.getInt(6);
				int hit = rs.getInt(7);
				boolean delete = rs.getBoolean(8);
				vo.setDelete(delete);
				vo.setHit(hit);
				vo.setDepth(depth);
				vo.setoNo(oNo);
				vo.setgNo(gNo);
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setUserName(name);
			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return vo;

	}

	public List<BoardVo> findByName(String s) {
		List<BoardVo> result = new ArrayList<BoardVo>();
		try {
			conn = getConnection();

			String sql = "select a.title,a.contents,b.name,a.g_no,a.o_no,a.depth,a.hit,a.delet,a.no,a.reg_date from board a,user b where  a.user_no=b.no and a.title like ?  and a.delet = 0 ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				String name = rs.getString(3);
				int gNo = rs.getInt(4);
				int oNo = rs.getInt(5);
				int depth = rs.getInt(6);
				int hit = rs.getInt(7);
				boolean delete = rs.getBoolean(8);
				Long no = rs.getLong(9);
				String regDate = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setRegDate(regDate);
				vo.setNo(no);
				vo.setDelete(delete);
				vo.setContents(contents);
				vo.setDepth(depth);
				vo.setgNo(gNo);
				vo.setHit(hit);
				vo.setTitle(title);
				vo.setUserName(name);
				vo.setoNo(oNo);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<BoardVo>();
		try {
			conn = getConnection();

			String sql = "select a.no,a.title,a.contents,a.hit,a.reg_date,a.g_no,a.o_no,a.depth,b.name,a.delet from board a,user b\r\n"
					+ "					where a.user_no = b.no\r\n" + "					order by g_no desc,o_no desc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				int gNo = rs.getInt(6);
				int oNo = rs.getInt(7);
				int depth = rs.getInt(8);
				String userName = rs.getString(9);
				boolean delete = rs.getBoolean(10);
				BoardVo vo = new BoardVo();
				vo.setDelete(delete);
				vo.setNo(no);
				vo.setContents(contents);
				vo.setDepth(depth);
				vo.setgNo(gNo);
				vo.setHit(hit);
				vo.setoNo(oNo);
				vo.setRegDate(regDate);
				vo.setTitle(title);
				vo.setUserName(userName);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> commentFind(BoardVo vo) {
		List<BoardVo> result = new ArrayList<BoardVo>();
		try {
			conn = getConnection();

			String sql = "select a.board_no,b.name,a.contents,a.no from comments a,user b where user_no = b.no and board_no = ?";	
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long no =rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				Long commentNo = rs.getLong(4);
				BoardVo bo = new BoardVo();
				bo.setUserNo(commentNo);
				bo.setNo(no);
				bo.setContents(contents);
				bo.setUserName(name);

				result.add(bo);
			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public Boolean delete(String no) {

		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set delet = 1 where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public Boolean commentDelete(String no) {
		
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete from comments where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public Boolean det(BoardVo vo) {

		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set o_no = o_no+1 where g_no = ?  and o_no >= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getgNo());
			pstmt.setInt(2, vo.getoNo());
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean detinsert(BoardVo vo) {

		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into board values(null,?,?,0,now(),?,?,?+1,?,0)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getgNo());
			pstmt.setLong(4, vo.getoNo());
			pstmt.setLong(5, vo.getDepth());
			pstmt.setLong(6, vo.getUserNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean commentInsert(BoardVo vo) {

		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into comments values(null,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setLong(2, vo.getUserNo());
			pstmt.setString(3, vo.getContents());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean insert(BoardVo vo) {

		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into board values(null,?,?,0,now(),(select max(a.no) from board a),1,0,?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public Boolean hitUpdate(BoardVo vo) {

		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set hit = ? where no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getHit());
			pstmt.setLong(2, vo.getNo());
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean update(BoardVo vo) {

		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set title = ? , contents = ? where no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.1.117:3307/webdb";
			conn =  DriverManager.getConnection(url,"webdb","webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		
		return conn;
	}
}
