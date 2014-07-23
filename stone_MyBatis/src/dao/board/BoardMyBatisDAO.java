package dao.board;

import java.util.List;

import model.board.BoardModel;
import mybatis.MyBatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * �Խ��� MYBATIS DAO Ŭ����
 * @since 2014.07.23
 * @made by ��âȭ
 */
public class BoardMyBatisDAO implements BoardDAOImpl {
	
	/** Mybatis SQL ���丮 */
	private SqlSessionFactory sessionFactory = null;
	
	/** �� Ŭ������ �����ڷ� ������ MyBatis�� ������ ���´�. */
	public BoardMyBatisDAO(){
		this.sessionFactory = MyBatis.getSqlSessionFactory();
	}
	
	/**
	 * �Խ��� ��� ��ȸ
	 * @param boardModel
	 * @return
	 */
	@Override
	public List<BoardModel> selectList(BoardModel boardModel) {
		/** �����ڿ��� ���� ���丮���� SqlSession ��ü�� ��´�. */
		SqlSession session = this.sessionFactory.openSession();
		try{
			/** selectList() �޼ҵ�� �ϳ��̻��� ������� ��� �޼ҵ��
			 * ù��° ���ڴ� board.xml�� <select id="selectList"/>�� ��ġ�ؾ��ϰ�
			 * �ι�° ���ڴ� <select resultType="BoardModel"/>�� ��ġ�ؾ��Ѵ�.  */
			return session.selectList("board.selectList", boardModel);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			/** ��� �Ŀ��� ���� */
			if(session != null) session.close();
		}
		return null;
	}
	
	/**
	 * �Խ��� �� ��ȸ
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
	 * �Խ��� �� ��ȸ
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
	 * �Խ��� ��� ó��
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
	 * �Խ��� ���� ó��
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
	 * �Խ��� ��ȸ�� ���� ���� ó��
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
	 * �Խ��� ���� ó��
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
