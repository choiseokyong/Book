package bookStoreProject12;

import java.io.Serializable;

public class Sale extends Book implements Serializable{
	String saleDate;
	int saleStock;
	int salePrice;
	String name;
	String phone;
	
	public Sale(String title, String genre, String author, int price, int stock, String receiptDate, String regDate, String saleDate, int saleStock, int salePrice) {
		super(title, genre, author, price, stock, receiptDate, regDate);
		this.saleDate = saleDate;
		this.saleStock = saleStock;
		this.salePrice= salePrice;
	}
	
	public Sale(String title, String genre, String author, int price, int stock, String receiptDate, String regDate, String name, String phone, String saleDate, int saleStock, int salePrice) {
		this(title, genre, author, price, stock, receiptDate, regDate, saleDate, saleStock, salePrice);
		this.name = name;
		this.phone = phone;
	}
	
	public void showInfo() {
	    System.out.println("┌─────────────────────────────────────────────");
	    System.out.println("│ 제목: " + String.format("%-33s", title));
	    System.out.println("│ 장르: " + String.format("%-33s", genre));
	    System.out.println("│ 작가: " + String.format("%-33s", author));
	    System.out.println("│ 가격: " + String.format("%s", "") + price);
	    System.out.println("│ 수량: " + String.format("%s", "") + stock);
	    System.out.println("│ 판매 가격: " + String.format("%s", "") + salePrice);
	    System.out.println("│ 판매개수: " + String.format("%s", "") + saleStock);
	    System.out.println("│ 판매 날짜: " + String.format("%-30s", saleDate));
	    System.out.println("└─────────────────────────────────────────────");
	}
	
	public void memberShowInfo() {
	    System.out.println("┌─────────────────────────────────────────────");
	    System.out.println("│ 제목: " + String.format("%-33s", title));
	    System.out.println("│ 장르: " + String.format("%-33s", genre));
	    System.out.println("│ 작가: " + String.format("%-33s", author));
	    System.out.println("│ 가격: " + String.format("%s", "") + price);
	    System.out.println("│ 수량: " + String.format("%s", "") + stock);
	    System.out.println("│ 이름: " + String.format("%s", "") + name);
	    System.out.println("│ 연락처: " + String.format("%s", "") + phone);
	    System.out.println("│ 판매 가격: " + String.format("%s", "") + salePrice);
	    System.out.println("│ 판매개수: " + String.format("%s", "") + saleStock);
	    System.out.println("│ 판매 날짜: " + String.format("%-30s", saleDate));
	    System.out.println("└─────────────────────────────────────────────");
	}
}