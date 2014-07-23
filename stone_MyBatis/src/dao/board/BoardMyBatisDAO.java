package dao.board;

import java.util.List;

import model.board.BoardModel;
import mybatis.MyBatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 게시판 MYBATIS DAO 클래스
 * @since 2014.07.23
 * @made by 정창화
 */
public class BoardMyBatisDAO implements BoardDAOImpl {
	
	/** Mybatis SQL 팩토리 */
	private SqlSessionFactory sessionFactory = null;
	
	/** 이 클래스의 생성자로 생성시 MyBatis의 세션을 얻어온다. */
	public BoardMyBatisDAO(){
		this.sessionFactory = MyBatis.getSqlSessionFactory();
	}
	
	/**
	 * 게시판 목록 조회
	 * @param boardModel
	 * @return
	 */
	@Override
	public List<BoardModel> selectList(BoardModel boardModel) {
		/** 생성자에서 얻은 팩토리에서 SqlSession 객체를 얻는다. */
		SqlSession session = this.sessionFactory.openSession();
		try{
			/** selectList() 메소드는 하나이상의 결과값을 얻는 메소드로
			 * 첫번째 인자는 board.xml의 <select id="selectList"/>와 일치해야하고
			 * 두번째 인자는 <select resultType="BoardModel"/>과 일치해야한다.  */
			return session.selectList("board.selectList", boardModel);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			/** 사용 후에는 종료 */
			if(session != null) session.close();
		}
		return null;
	}
	
	/**
	 * 게시판 수 조회
	 * @param boardModel
	 * @return
	 */
	@Override
	public int selectCount(BoardModel boardModel) {
		SqlSession session = this.sessionFactory.openSession();
		try{
			return session.selectOne("board.selectCount",boardModel);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session !=null) session.close();
		}
		return 0;
	}
	
	/**
	 * 게시판 상세 조회
	 * @param boardModel
	 * @return
	 */
	@Override
	public BoardModel select(BoardModel boardModel) {
		SqlSession session = this.sessionFactory.openSession();
		try{
			return session.selectOne("board.select", boardModel);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if( session != null) session.close();
		}
		return null;
	}
	
	/**
	 * 게시판 등록 처리
	 * @param boardModel
	 */
	@Override
	public void insert(BoardModel boardModel) {
		SqlSession session = this.sessionFactory.openSession();
		try{
			session.insert("board.insert",boardModel);
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			if( session != null) session.close();
		}
	}
	
	/**
	 * 게시판 수정 처리
	 * @param boardModel
	 */
	@Override
	public void update(BoardModel boardModel) {
		SqlSession session = this.sessionFactory.openSession();
		try{
			session.update("board.update",boardModel);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null) session.close();
		}
	}
	
	/**
	 * 게시판 조회수 증가 수정 처리
	 * @param boardModel
	 */
	@Override
	public void updateHit(BoardModel boardModel) {
		SqlSession session = this.sessionFactory.openSession();
		try{
			session.insert("board.updateHit",boardModel);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null) session.close();
		}
	}
	
	/**
	 * 게시판 삭제 처리
	 * @param boardModel
	 */
	@Override
	public void delete(BoardModel boardModel) {
		SqlSession session = this.sessionFactory.openSession();
		try{
			session.delete("board.delete",boardModel);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null)session.close();
		}
	}
}
