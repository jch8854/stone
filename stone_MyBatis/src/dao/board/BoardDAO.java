package dao.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.board.BoardModel;

public class BoardDAO implements BoardDAOImpl {
	
	//�����ͺ��̽� ���� Ŭ�������� �ʱ�ȭ, ��� �޼ҵ忡�� ����� �� �ְ� 
	//������� ����
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//�����ͺ��̽� ��������, ���� ���� ������ϰ� ���ȭ
	private final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	private final String DB_URL="jdbc:mysql://127.0.0.1:3306/stone";
	private final String DB_ID="root";
	private final String DB_PWD="123";
	
	/**
	 * �Խ��� ��� ��ȸ
	 * @param boardModel
	 * @return
	 */
	public List<BoardModel> selectList(BoardModel boardModel){
		
		int pageNum = Integer.parseInt(boardModel.getPageNum());
		int listCount = boardModel.getListCount();
		String searchType = boardModel.getSearchType();
		String searchText = boardModel.getSearchText();
		String whereSQL = "";
		List<BoardModel> boardList = null;
		try{
			//�˻��� ������ ����
			if(!"".equals(searchText)){
				if("ALL".equals(searchType)){
					whereSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%') OR CONTENTS LIKE CONCAT('%',?,'%') ";
					
				}else if("SUBJECT".equals(searchType)){
					whereSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%') ";
				}else if("WRITER".equals(searchType)){
					whereSQL = " WHERE WRITER LIKE CONCAT('%',?,'%') ";
				}else if("CONTENTS".equals(searchType)){
					whereSQL = " WHERE CONTENTS LIKE CONCAT('%',?,'%') ";
				}
			}
			
			//�����ͺ��̽� ��ü ����
			Class.forName(this.JDBC_DRIVER);
			this.conn = DriverManager.getConnection(this.DB_URL, this.DB_ID, this.DB_PWD);
			//�Խù� ��� ��ȸ
			this.pstmt = this.conn.prepareStatement("SELECT NUM, SUBJECT, WRITER, REG_DATE, HIT FROM BOARD "+whereSQL+" ORDER BY NUM DESC LIMIT ?, ?");
			if(!"".equals(whereSQL)){
				//��ü�˻��Ͻ�
				if("ALL".equals(searchType)){
					this.pstmt.setString(1, searchText);
					this.pstmt.setString(2, searchText);
					this.pstmt.setString(3, searchText);
					this.pstmt.setInt(4, listCount * (pageNum-1));
					this.pstmt.setInt(5, listCount);
				}else{
					this.pstmt.setString(1, searchText);
					this.pstmt.setInt(2, listCount * (pageNum-1));
					this.pstmt.setInt(3, listCount);
				}
				
			}else{
				this.pstmt.setInt(1, listCount * (pageNum-1));
				this.pstmt.setInt(2, listCount);
			}
			//��ȸ
			this.rs = this.pstmt.executeQuery();
			boardList = new ArrayList<BoardModel>();
			//LIST ��ü�� ����
			while(this.rs.next()){
				boardModel = new BoardModel();
				boardModel.setNum(this.rs.getInt("NUM"));
				boardModel.setSubject(this.rs.getString("SUBJECT"));
				boardModel.setWriter(this.rs.getString("WRITER"));
				boardModel.setHit(this.rs.getInt("HIT"));
				boardModel.setRegDate(this.rs.getString("REG_DATE"));
				boardList.add(boardModel);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			//����� ��ü ����
			close(this.rs,this.pstmt,this.conn);
			
	}
		return boardList;
	}
	
	/**
	 * �Խ��� �� ��ȸ
	 * @param boardModel
	 * @return
	 */
	public int selectCount(BoardModel boardModel){
		int totalCount = 0;
		String searchType = boardModel.getSearchType();
		String searchText = boardModel.getSearchText();
		String whereSQL = "";
		try{
			//�˻��� ������ ����
			if(!"".equals(searchText)){
				if("ALL".equals(searchType)){
					whereSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%') OR WRITER LIKE CONCAT('%',?,'%') OR CONTENTS LIKE CONCAT('%',?,'%') ";	
				}else if ("SUBJECT".equals(searchType)){
					whereSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%') ";
				}else if ("WRITER".equals(searchType)){
					whereSQL = " WHERE WRITER LIKE CONCAT('%',?,'%') ";
				}else if ("CONTENTS".equals(searchType)){
					whereSQL = " WHERE CONTENTS LIKE CONCAT('%',?,'%') ";
				}
			}
			//�����ͺ��̽� �ۼ�
			Class.forName(this.JDBC_DRIVER);
			this.conn = DriverManager.getConnection(this.DB_URL,this.DB_ID,this.DB_PWD);
			//�Խù��� �� ���� ��� ���� ����
			this.pstmt = this.conn.prepareStatement("SELECT COUNT(NUM) AS TOTAL FROM BOARD" + whereSQL);
			if(!"".equals(whereSQL)){
				if("ALL".equals(searchType)){
					this.pstmt.setString(1, searchText);
					this.pstmt.setString(2, searchText);
					this.pstmt.setString(3, searchText);
				}else{
					this.pstmt.setString(1, searchText);
				}
			}
			this.rs = this.pstmt.executeQuery();
			if(this.rs.next()){
				totalCount = this.rs.getInt("TOTAL");
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//����� ��ü ����
			close(this.rs,this.pstmt,this.conn);
		}
		return totalCount;
	}
	
	/**
	 * �Խ��� �� ��ȸ
	 * @param boardModel
	 * @return
	 */
	public BoardModel select (BoardModel boardModel){
		try{
			//�����ͺ��̽� ��ü ����
			Class.forName(this.JDBC_DRIVER);
			this.conn = DriverManager.getConnection(this.DB_URL,this.DB_ID,this.DB_PWD);
			//�Խù� �� ��ȸ ���� ����
			this.pstmt = this.conn.prepareStatement(
					"SELECT NUM, SUBJECT, CONTENTS, WRITER, HIT, REG_DATE FROM BOARD "+
				    "WHERE NUM = ?");
			this.pstmt.setInt(1, boardModel.getNum());
			this.rs = this.pstmt.executeQuery();
			//���� �����Ѵٸ�
			if(this.rs.next()){
				boardModel.setNum(this.rs.getInt("NUM"));
				boardModel.setSubject(this.rs.getString("SUBJECT"));
				boardModel.setContents(this.rs.getString("CONTENTS"));
				boardModel.setWriter(this.rs.getString("WRITER"));
				boardModel.setHit(this.rs.getInt("HIT"));
				boardModel.setRegDate(this.rs.getString("REG_DATE"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			close(this.rs,this.pstmt,this.conn);
		}
		return boardModel;
	}
	
	/**
	 * �Խ��� ��� ó��
	 * @param boardModel
	 */
	public void insert(BoardModel boardModel){
		try{
			Class.forName(this.JDBC_DRIVER);
			this.conn = DriverManager.getConnection(this.DB_URL, this.DB_ID, this.DB_PWD);
			this.pstmt = this.conn.prepareStatement(
					"INSERT INTO BOARD (SUBJECT, WRITER, CONTENTS, IP, HIT, REG_DATE, MOD_DATE) "+
			        "VALUES (?, ?, ?, ? , 0, NOW(), NOW())");
			this.pstmt.setString(1, boardModel.getSubject());
			this.pstmt.setString(2, boardModel.getWriter());
			this.pstmt.setString(3, boardModel.getContents());
			this.pstmt.setString(4, boardModel.getIp());
			this.pstmt.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			close(this.rs, this.pstmt, this.conn);
		}
	}
	
	/**
	 * �Խ��� ���� ó��
	 * @param boardModel
	 */
	public void update(BoardModel boardModel){
		try{
			//�����ͺ��̽� ��ü ����
			Class.forName(this.JDBC_DRIVER);
			this.conn = DriverManager.getConnection(this.DB_URL, this.DB_ID, this.DB_PWD);
			this.pstmt = this.conn.prepareStatement(
					"UPDATE BOARD SET SUBJECT = ?, WRITER = ?, CONTENTS = ?, IP = ?, MOD_DATE = NOW() "+
				    "WHERE NUM = ?");
			this.pstmt.setString(1, boardModel.getSubject());
			this.pstmt.setString(2, boardModel.getWriter());
			this.pstmt.setString(3, boardModel.getContents());
			this.pstmt.setString(4, boardModel.getIp());
			this.pstmt.setInt(5, boardModel.getNum());
			this.pstmt.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			close(this.rs, this.pstmt, this.conn);
		}
	}
	
	/**
	 * �Խ��� ��ȸ�� ���� ���� ó��
	 * @param boardModel
	 */
	public void updateHit(BoardModel boardModel){
		try{
			Class.forName(this.JDBC_DRIVER);
			this.conn = DriverManager.getConnection(this.DB_URL,this.DB_ID,this.DB_PWD);
			//��ȸ�� ���� ���� ����
			this.pstmt = this.conn.prepareStatement("UPDATE BOARD SET HIT = HIT + 1 WHERE NUM = ?");
			this.pstmt.setInt(1, boardModel.getNum());
			this.pstmt.executeUpdate();
					
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			close(this.rs,this.pstmt, this.conn);
		}
	}
	/**
	 * �Խ��� ���� ó��
	 * @param boardModel
	 */
	public void delete(BoardModel boardModel){
		try {
			Class.forName(this.JDBC_DRIVER);
			//��ȸ�� ���� ���� ����
			this.conn = DriverManager.getConnection(this.DB_URL, this.DB_ID, this.DB_PWD);
			this.pstmt = this.conn.prepareStatement("DELETE FROM BOARD WHERE NUM = ?");
			this.pstmt.setInt(1, boardModel.getNum());
			this.pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(this.rs, this.pstmt, this.conn);
		}
		
	}
	
	/**
	 * ����� ��ü�� ����
	 * @param rs
	 * @param pstmt
	 * @param conn
	 */
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}