package dao.board;

import java.util.List;
import model.board.BoardModel;

public interface BoardDAOImpl {
	/**
	 * �Խ��� ��� ��ȸ
	 * @param boardModel
	 * @return
	 */
	public List<BoardModel> selectList(BoardModel boardModel);
	
	/**
	 * �Խ��� �� ��ȸ
	 * @param boardModel
	 * @return
	 */
	public int selectCount(BoardModel boardModel);
	
	/**
	 * �Խ��� �� ��ȸ
	 * @param boardModel
	 * @return
	 */
	public BoardModel select(BoardModel boardModel);
	
	/**
	 * �Խ��� ��� ó��
	 * @param boardModel
	 */
	public void insert(BoardModel boardModel);
	
	/**
	 * �Խ��� ���� ó��
	 * @param boardModel
	 */
	public void update(BoardModel boardModel);
	
	/**
	 * �Խ��� ��ȸ�� ���� ���� ó��
	 * @param boardModel
	 */
	public void updateHit(BoardModel boardModel);
	
	/**
	 * �Խ��� ���� ó��
	 * @param boardModel
	 */
	public void delete(BoardModel boardModel);
}
