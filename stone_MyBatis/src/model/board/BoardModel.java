package model.board;

/**
 * �Խ��� �� Ŭ����
 * @since 2014.07.23
 * @made by ��âȭ
 */
public class BoardModel {
	
	//��ȣ 
	private int num;
	
	//����
	private String subject;
	
	//�ۼ���
	private String writer;
	
	//����
	private String contents;
	
	//������
	private String ip;
	
	//��ȸ��
	private int hit =0;
	
	//��� �Ͻ�
	private String regDate;
	
	//���� �Ͻ�
	private String modDate;
	
	//��������ȣ
	private String pageNum = "1";
	
	//�˻� �׸�
	private String searchType = "";
	
	//�˻���
	private String searchText = "";
	
	//��� ������ �Խù� ���� �� 
	private int listCount = 10;
	
	//��� ������ �׺������ ��� ��
	private int pagePerBlock = 10;
	
	/** ������� startIndex�� �߰� �ߴ�. �� ��������� ������������� �������
	 * �����ö� row ��ġ�� ������ �� ����Ѵ�.
	 * (SQL ���ǹ��� LIMIT #{startIndex},#{listCount} �̺κ�) */
	private int startIndex = 0;
	
	public int getStartIndex() {
		startIndex = listCount * (Integer.parseInt(pageNum)-1);
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	//������
	public BoardModel() {}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	
	
	
	
	
}