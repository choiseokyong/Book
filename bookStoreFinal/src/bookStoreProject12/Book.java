package bookStoreProject12;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable{
	Date now = new Date(); //Date 인스턴스 생성
	// 필드
	String title; //책 제목
	String genre; // 장르
	String author; // 저자
	int price;  //가격
	int stock; // 재고
	String receiptDate; //수령 날짜?
	String regDate; //등록 날짜
	//생성자
	// 책의 정보에 공란이 없도록 저장.
	public Book(String title, String genre, String author, int price, int stock, String receiptDate, String regDate) {
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.stock = stock;
		this.price = price;
		this.receiptDate = receiptDate;
		this.regDate = regDate;
		}
	
		public String getGenre() {
		    return genre;
		}
		
		public String getTitle() {
		    return title;
		}
		
		public String getAuthor() {
		    return author;
		}
	@Override
	public int hashCode() {
		return title.hashCode() + author.hashCode(); //
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Book) {
			Book temp = (Book) obj;
			return temp.title.equals(title) && (temp.author.equals(author));
		}else {
			return false;
		}
	}
	
	public void showInfo() {
		System.out.printf("제목: %s 장르: %s 작가: %s 가격: %d 수량: %d 입고날짜: %s 등록날짜: %s%n",title, genre,author,price,stock,receiptDate,regDate);
	}
}
