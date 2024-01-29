package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelectAll {

	public static void main(String[] args) {

		List<BookVo> bookList = new ArrayList<BookVo>();

		// 번호 유영수 학원학생
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");

			// 3. SQL문 준비 / 바인딩 / 실행
			// - sql문 준비
			String query = "";
			query += " select * ";
			query += " from book b ";
			query += " left join author a on b.author_id = a.author_id ";
			query += " order by book_id asc ";
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			
			// - 실행
			rs = pstmt.executeQuery();

			// 검색결과에서 데이터 꺼내기
			while (rs.next()) {

				int bookno = rs.getInt("b.book_id");
				String booktitle = rs.getString("b.title");
				String bookpubs = rs.getString("b.pubs");
				String bookpub_date = rs.getString("b.pub_date");
				int bookauthorid = rs.getInt("b.author_id");
				
				int authorno = rs.getInt("a.author_id");
				String authorname = rs.getString("a.author_name");
				String authordesc = rs.getString("a.author_desc");

				// Vo묶기
				BookVo bookVo = new BookVo(bookno, booktitle, bookpubs, bookpub_date, bookauthorid, authorno, authorname, authordesc);

				// 리스트에 추가
				bookList.add(bookVo);
			}
			// 4.결과처리
//			System.out.println(authorList.toString());
			// 리스트를 이용해서 출력
			for (int i = 0; i < bookList.size(); i++) {
				int bookno = bookList.get(i).getBookId();
				String booktitle = bookList.get(i).getBookTitle();
				String bookpubs = bookList.get(i).getBookPubs();
				String bookpub_date = bookList.get(i).getBookpub_Date();
				int bookauthorid = bookList.get(i).getBookauthorId();
				
				int authorno = bookList.get(i).getAuthorId();
				String authorname = bookList.get(i).getAuthorName();
				String authordesc = bookList.get(i).getAuthorDesc();

				System.out.println(bookno + ".\t" + booktitle + "\t" + bookpubs + "\t" + bookpub_date + "\t" + bookauthorid
						+ "\t" + authorno + "\t" + authorname + "\t" + authordesc);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
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
				System.out.println("error:" + e);
			}
		}
	}
}
