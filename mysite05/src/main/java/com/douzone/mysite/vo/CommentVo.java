package com.douzone.mysite.vo;

public class CommentVo {
	private String contents;
	private Long no;
	private Long boardNo;
	private String userName;
	private Long userNo;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @return the userNo
	 */
	public Long getUserNo() {
		return userNo;
	}
	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	@Override
	public String toString() {
		return "CommentVo [contents=" + contents + ", no=" + no + ", boardNo=" + boardNo + ", userName=" + userName
				+ ", userNo=" + userNo + "]";
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return the no
	 */
	public Long getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(Long no) {
		this.no = no;
	}
	/**
	 * @return the boardNo
	 */
	public Long getBoardNo() {
		return boardNo;
	}
	/**
	 * @param boardNo the boardNo to set
	 */
	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}


	
	

}
