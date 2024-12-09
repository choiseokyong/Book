package bookStoreProject12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookManager {
	private static BookManager bm = new BookManager();
	static BookManager getBookManager() {
		return bm;
	}
	
	Set<Book> BookStorage = new HashSet<Book>();
	List<Sale> saleBookStorage = new LinkedList<Sale>();
	Set<Member> memberStorage = new HashSet<Member>();
	
	private String today = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	private final File dataFile = new File("book.dat"); 
	private final File saleDataFile = new File("saleBook.dat"); 
	private final File memberFile = new File("member.dat"); 
	
	
	private BookManager(){
		BookStorage = (Set<Book>) readList(dataFile, BookStorage);
		saleBookStorage = (List<Sale>) readList(saleDataFile, saleBookStorage);
		memberStorage = (Set<Member>) readList(memberFile, memberStorage);
	}
	
	// int 장르선택지를 => String 장르이름으로 변경해 주는 메소드
		private String genreChoice(int num) throws MenuChoiceException {
			String genre = null;
			 switch (num) {
	         case Genre.ROMANCE:genre = "로맨스";break;
	         case Genre.ESSAY:genre = "에세이";break;
	         case Genre.REFERENCEBOOK:genre = "참고서";break;
	         case Genre.GENRENOVEL:genre = "장르소설";break;
	         case Genre.NOVEL:genre = "소설";break;
	         case Genre.SCIENCE:genre = "과학";break;
	         case Genre.SOCIAL:genre = "사회";break;
	         case Genre.ETC:genre = "기타";break;
	         default:
	        	 throw new MenuChoiceException(num);
	     }
			return genre;
		}
	
   public static String getInput(String str) throws DataInputException{
	   while (true) {
	      System.out.println(str);
	      String input = MenuViewer.sc.nextLine();
	      
    	  try {
    		  if(input.isEmpty()) {
    			  throw new DataInputException(input);
    		  }else {
		         return input;
		      }
    		  
    	  }catch(DataInputException e) {

    	  }
	   }
   }
	// 회원정보 등록 메소드
	private Member inputMember() throws DataInputException{
	    String name = getInput("회원명 ==> ");
	    int memberLevel = 0;
	    while (true) {
	        try {
	            memberLevel = Integer.parseInt(getInput("등급==>"));
	            break;
	        } catch (NumberFormatException e) {
	            System.out.println("유효한 숫자를 입력하세요.");
	        }
	    }
	    
	    int mileage = 0;
	    while (true) {
	        try {
	            mileage = Integer.parseInt(getInput("마일리지==>"));
	            break;
	        } catch (NumberFormatException e) {
	            System.out.println("유효한 숫자를 입력하세요.");
	        }
	    }
	    
	    String phoneNumber = getInput("전화번호==>");
	    String address = getInput("주소==>");
	    
	    int age = 0;
	    while (true) {
	        try {
	            age = Integer.parseInt(getInput("나이==>"));
	            break;
	        } catch (NumberFormatException e) {
	            System.out.println("유효한 숫자를 입력하세요.");
	        }
	    }
	    
	    int discount = 0;
	    while (true) {
	        try {
	            discount = Integer.parseInt(getInput("할인률==>"));
	            break;
	        } catch (NumberFormatException e) {
	            System.out.println("유효한 숫자를 입력하세요.");
	        }
	    }

	    Member newMember = new Member(name, memberLevel, mileage, discount, today, phoneNumber, address, age);
	    return newMember;
	}
	
	private Book inputBook() throws DataInputException, MenuChoiceException{
		String title = getInput("제목 입력 =>");
		MenuViewer.showGenreMenu();
		int division = Integer.parseInt(getInput("장르 선택(1~8 사이 숫자 입력):"));
		String genre = genreChoice(division);
		String author = getInput("작가 입력 =>");
		int price = Integer.parseInt(getInput("가격 입력 =>"));
		int stock = Integer.parseInt(getInput("수량 입력 =>"));
		String receiptDate = today;
		String regDate = today;
		
		Book newBook = new Book(title,genre,author,price,stock,receiptDate,regDate);
		return newBook;
		
	}
	
	// 도서 등록 메소드
	public void inputData() throws MenuChoiceException, DataInputException{
		System.out.println("1. 회원가입, 2. 도서등록");
        System.out.print("선택>> ");
        int choice = Integer.parseInt(MenuViewer.sc.nextLine());
        
        switch(choice) {
        case 1:
        	Member temp1 = inputMember();
            memberStorage.add(temp1);
            writeList(memberFile,memberStorage);
            System.out.println("=============");
            System.out.println(" 회원가입 완료 ");
            System.out.println("=============");
        	break;
        case 2:
        	Book temp2 = inputBook();
			BookStorage.add(temp2);
			writeList(dataFile,BookStorage);
			System.out.println("====================");
			System.out.println("     도서 등록완료     ");
			System.out.println("====================");
        	break;
        default:throw new MenuChoiceException(choice);
        }
	}
	
	// 도서 수정 메소드
	public void upData() throws MenuChoiceException {
		System.out.println("1. 회원 수정, 2. 도서 수정");
        System.out.print("선택>> ");
        int first_choice = Integer.parseInt(MenuViewer.sc.nextLine());
        
        if (first_choice < 1 || first_choice > 2) {
        	throw new MenuChoiceException(first_choice);
        } else if(first_choice == 1){
        	System.out.print("수정할 회원명 입력: ");
            String update_name = MenuViewer.sc.nextLine();
            System.out.print("수정할 회원의 전화번호 입력: ");
            String update_phone = MenuViewer.sc.nextLine();

            Iterator<Member> it = memberStorage.iterator();
            boolean isUpdate = false;

            while(it.hasNext()) {
                Member updateMember = it.next();
                
                if(updateMember.name.equals(update_name) && updateMember.phoneNumber.equals(update_phone)) {
                    isUpdate = true;
                    System.out.println("수정할 회원 정보:");
                    updateMember.showInfo();

                    System.out.println("수정할 정보를 선택해 주세요:");
                    System.out.println("========================================================");
                    System.out.println("| 1. 전화번호 2. 주소 3. 나이 4. 회원 등급 5. 마일리지 6. 할인율 |");
                    System.out.println("========================================================");
                    System.out.print("수정 선택 => ");
                    int choice = Integer.parseInt(MenuViewer.sc.nextLine());

                    switch(choice) {
                        case 1:
                            System.out.print("새 전화번호 입력: ");
                            String newPhone = MenuViewer.sc.nextLine();
                            updateMember.phoneNumber = newPhone;
                            break;
                        case 2:
                            System.out.print("새 주소 입력: ");
                            String newAddress = MenuViewer.sc.nextLine();
                            updateMember.address = newAddress;
                            break;
                        case 3:
                            System.out.print("새 나이 입력: ");
                            int newAge = Integer.parseInt(MenuViewer.sc.nextLine());
                            updateMember.age = newAge;
                            break;
                        case 4:
                            System.out.print("새 회원 등급 입력: ");
                            int newLevel = Integer.parseInt(MenuViewer.sc.nextLine());
                            updateMember.memberLevel = newLevel;
                            break;
                        case 5:
                            System.out.print("새 마일리지 입력: ");
                            int newMileage = Integer.parseInt(MenuViewer.sc.nextLine());
                            updateMember.mileage = newMileage;
                            break;
                        case 6:
                            System.out.print("새 할인율 입력: ");
                            int newDiscount = Integer.parseInt(MenuViewer.sc.nextLine());
                            updateMember.discount = newDiscount;
                            break;
                        default:
                            System.out.println("잘못된 선택입니다.");
                            return;
                    }
                    
                    System.out.println("회원 정보 수정 완료.");
                    writeList(memberFile,memberStorage);
                    break;
                }
            }

            if(!isUpdate) {
                System.out.println("해당 회원 정보를 찾을 수 없습니다.");
            }
        } else {
        	System.out.print("수정할 도서의 제목 입력");
        	String delete_title = MenuViewer.sc.nextLine();
        	System.out.println("수정할 도서의 저자 입력");
        	String delete_author = MenuViewer.sc.nextLine();
        	Iterator<Book> it = BookStorage.iterator();
        	boolean isUpdate = false;
        	while(it.hasNext()) {
        		Book updateBook = it.next();
        		if(updateBook.title.equals(delete_title) &&  updateBook.author.equals(delete_author)) {
        			isUpdate = true;
        			System.out.println("수정할 도서:" );
        			updateBook.showInfo();
        			String title="", author="",receiptDate="",regDate="";  
        			int stock=0, price=0;
        			String YN = "";

        			System.out.println("수정 하시겠습니까? 입력: y/n");
        			YN = MenuViewer.sc.nextLine();
        			if (YN.equals("y") || YN.equals("n")) {
        				if(YN.equals("y")) {
        					int choice;
        					System.out.println("수정할 정보를 선택해 주세요=>");
        					System.out.println("================================");
        					System.out.println("| 1.제목 2.장르 3.작가 4.수량 5.가격 |");
        					System.out.println("================================");
        					System.out.print("번호 선택 =>");
        					choice = Integer.parseInt(MenuViewer.sc.nextLine());

        					if(choice<1 || choice>5)
        						throw new MenuChoiceException(choice);

        					switch(choice) {
        					case 1 : System.out.println("제목: ");
        					title = MenuViewer.sc.nextLine();
        					updateBook.title = title;break;

        					case 2 : System.out.println("장르: ");
        					MenuViewer.showGenreMenu(); //장르 선택지를 출력해 주는 메소드
        					int division = Integer.parseInt(MenuViewer.sc.nextLine());
        					String genre = genreChoice(division);
        					updateBook.genre = genre;break;

        					case 3 : System.out.println("작가: ");
        					author = MenuViewer.sc.nextLine();
        					updateBook.author = author;break;

        					case 4 :

        						System.out.println("수량: ");
        						stock = Integer.parseInt(MenuViewer.sc.nextLine());
        						updateBook.stock = stock;
        						receiptDate = today;
        						regDate = today;
        						updateBook.receiptDate = receiptDate;
        						updateBook.regDate = regDate;break;

        					case 5 :
        						System.out.println("가격: ");
        						price = Integer.parseInt(MenuViewer.sc.nextLine());
        						updateBook.price = price;break;
        					}

        					writeList(dataFile,BookStorage);
        					System.out.println("수정 완료");break;

        				}else if(YN.equals("n")){
        					System.out.println("수정 취소");break;
        				}

        			}throw new MenuChoiceException(YN);


        		}
        	} if(!isUpdate){
        		System.out.println("수정할 도서 정보가 없습니다.");
        	}
        }
	}

	// 도서 삭제 메소드
	public void deleteData() {
		System.out.print("삭제할 책 이름 입력");
		String delete_title = MenuViewer.sc.nextLine();
		System.out.println("삭제할 책 저자 입력");
		String delete_author = MenuViewer.sc.nextLine();
		
		Iterator<Book> it = BookStorage.iterator();
		boolean isRemove=false;
		while(it.hasNext()) {
			Book temp = it.next();
			if(temp.title.equals(delete_title) &&  temp.author.equals(delete_author)) {
				it.remove();
				isRemove = true;
				
				writeList(dataFile,BookStorage); // 삭제된 정보 저장
				System.out.println("도서 정보 삭제 완료");
				
			}
		}
		
		if(!isRemove) {
			System.out.println("삭제하려는 도서가 존재하지 않습니다.");
		}
	}
	// 도서 전체 조회 메소드 
	public void printAll() throws MenuChoiceException {
		System.out.println("1. 회원, 2. 도서");
    	System.out.print("선택>> ");
        int choice = Integer.parseInt(MenuViewer.sc.nextLine());
        
        if (choice == 1) {
	        if (BookStorage.isEmpty()) {
	            System.out.println("등록된 회원이 없습니다.");
	        } else {
	            for (Member member : memberStorage) { 
	                member.showInfo();
	            }
	        }
        } else {
        	if (BookStorage.isEmpty()) {
	            System.out.println("등록된 도서가 없습니다.");
	        } else {
	        	System.out.println("==========================================");
	        	System.out.println(" 1. 전체조회 | 2. 장르별 | 3. 도서별 | 4. 작가별 ");
	        	System.out.println("==========================================");
	        	System.out.print("선택>> ");
	            int sec_choice = Integer.parseInt(MenuViewer.sc.nextLine());
	            switch(sec_choice) {
	            	case 1 :
	    	            for (Book book : BookStorage) { 
	    	            	book.showInfo();
	    	            }
	    	            break;
	            	case 2 :
	            		MenuViewer.showGenreMenu();
	                    int genreChoice = Integer.parseInt(MenuViewer.sc.nextLine());
	                    String genreV2 =  genreChoice(genreChoice);
	                   
	                    boolean found = false; 

	                    for (Book book : BookStorage) { 
	                        if (genreV2.equals(book.getGenre())) { 
	                            book.showInfo(); 
	                            found = true; 
	                        }
	                    }
	                    if (!found) {
	                        System.out.println("해당 정보가 없습니다.");
	                    }
	                    break;
	            	case 3 :
	            	    System.out.print("도서명 ==> ");
	            	    String title = MenuViewer.sc.nextLine();
	            	    if(title.isEmpty()) {
	            	    	System.out.println("도서명을 입력해주세요");
	            	    	return;
	            	    }
	            	    boolean title_found = false;

	            	    for (Book book : BookStorage) {
	            	        if (book.getTitle().contains(title)) {
	            	            book.showInfo();
	            	            title_found = true;
	            	        }
	            	    }
	            	    if (!title_found) {
	            	        System.out.println("해당 도서가 없습니다.");
	            	    }
	            	    break;
	            	case 4 :
	            		//저자명 구간
	            		System.out.print("작가명 ==> ");
	            	    String author = MenuViewer.sc.nextLine();
	            	    
	            	    boolean author_found = false;

	            	    for (Book book : BookStorage) {
	            	        if (book.getAuthor().contains(author)) {
	            	            book.showInfo();
	            	            title_found = true;
	            	        }
	            	    }
	            	    if (!author_found) {
	            	        System.out.println("해당 작가가 없습니다.");
	            	    }
	            	    break;
	            	default :
	            		System.out.println("잘못된 입력입니다.");
	            		break;
	            }
	        }
        }
	}
	//writeList()메소드 하나로 모든 파일을 저장할 수 있도록 변경
	public void writeList(File fileName, Collection data) {
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
		}catch (FileNotFoundException e) {
			System.out.println("파일을 확인하세요.");
		}catch( IOException e) {
			System.out.println("IOException 발생!");
		}finally {
			try {
				if(oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (IOException e) {
				System.out.println("IOException 발생!");
			}
			
		}
	}

	//readList()메소드 하나로 모든 파일을 불러올 수 있도록 변경
	public Collection readList(File fileName,Collection data){
		if(!fileName.exists()) {
			return data;
		}
		FileInputStream fis;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			data = (Collection) ois.readObject();
			
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}catch (ClassNotFoundException e) {
			e.getMessage();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return data;
	}

	public void saleBook() throws DataInputException, MenuChoiceException{
		System.out.println("=====================");
		System.out.println(" 1. 판매  | 2. 매출조회 |");
		System.out.println("=====================");
		String strChoice = MenuViewer.sc.nextLine(); 		
		if(strChoice.isEmpty()) { 							
			throw new DataInputException();}
		int choice = Integer.parseInt(strChoice);

		if(choice==1) {
			System.out.println("=====================");
			System.out.println(" 1. 회원  | 2. 비회원 |");
			System.out.println("=====================");
			System.out.print("선택 =>");
			int memberChoice = Integer.parseInt(MenuViewer.sc.nextLine());
			boolean isBook=false;
			switch(memberChoice) {
			case 1:
				System.out.print("이름 입력 =>");
	        	String name = MenuViewer.sc.nextLine();
	        	if(name.isEmpty()) { 
					throw new DataInputException();
				}
	        	System.out.print("연락처 입력 =>");
	        	String phone = MenuViewer.sc.nextLine();
	        	if(phone.isEmpty()) { 
					throw new DataInputException();
				}
	        	
	        	double discountRatio = 0.00; 
	        	int memberLevel = -1; 
	        	boolean memberFound = false;
	        	
	        	for (Member mem : memberStorage) {
	        	    if (mem.name.equals(name) && mem.phoneNumber.equals(phone)) {
	        	        memberFound = true; 
	        	        memberLevel = mem.memberLevel; 
	        	        switch (memberLevel) {
	        	            case 1:
	        	                discountRatio = 0.05; break;
	        	            case 2:
	        	                discountRatio = 0.10; break;
	        	            case 3:
	        	                discountRatio = 0.15; break;
	        	            case 4:
	        	                discountRatio = 0.20; break;
	        	        }
	        	        break;
	        	    }
	        	}

	        	if (!memberFound) {
	        	    System.out.println("회원등록이 필요합니다.");
	        	    return;
	        	}
	        	for (Book book : BookStorage) { 
					book.showInfo();
				}
	        	System.out.println("도서명 입력: ");
				String saleTitle1= MenuViewer.sc.nextLine();
				if(saleTitle1.isEmpty()) { 							
					throw new DataInputException();}

				System.out.println("작가명 입력: ");
				String saleAuthor1= MenuViewer.sc.nextLine();
				if(saleAuthor1.isEmpty()) { 							
					throw new DataInputException();}

				System.out.println("판매수량 입력: ");
				String strSaleStock1 = MenuViewer.sc.nextLine();
				if(strSaleStock1.isEmpty()) { 							
					throw new DataInputException();}
				int saleStock1 = Integer.parseInt(strSaleStock1);
				for (Book book : BookStorage) { 
					if(book.title.equals(saleTitle1) &&  book.author.equals(saleAuthor1)) {
						isBook = true;

						if(book.stock >= saleStock1) {
							book.stock=book.stock - saleStock1;
							int salePrice = saleStock1 * (int)(book.price * (1 - discountRatio));
							String saleDate = today;
							Sale saleBook = new Sale(
									book.title,
									book.genre,
									book.author,
									book.price,
									book.stock, 
									book.receiptDate, 
									book.regDate, 
									name, phone, saleDate, saleStock1, salePrice);
							saleBookStorage.add(saleBook);
							saleBook.memberShowInfo();
							writeList(dataFile, BookStorage);
							writeList(saleDataFile, saleBookStorage);
							System.out.println("판매 완료");
						}else {
							System.out.println("재고 수량이 부족합니다."); return;
						}
					}
				}
				break;
			case 2:
				for (Book book : BookStorage) { 
					book.showInfo();
				}

				System.out.println("도서명 입력: ");
				String saleTitle2= MenuViewer.sc.nextLine();
				if(saleTitle2.isEmpty()) { 							
					throw new DataInputException();}

				System.out.println("작가명 입력: ");
				String saleAuthor2= MenuViewer.sc.nextLine();
				if(saleAuthor2.isEmpty()) { 							
					throw new DataInputException();}

				System.out.println("판매수량 입력: ");
				String strSaleStock2 = MenuViewer.sc.nextLine();
				if(strSaleStock2.isEmpty()) { 							
					throw new DataInputException();}
				int saleStock2 = Integer.parseInt(strSaleStock2);

				//받은 데이터로 Book 전체조회 
				Iterator<Book> it = BookStorage.iterator();
				
				while(it.hasNext()) {
					Book temp = it.next();
					if(temp.title.equals(saleTitle2) &&  temp.author.equals(saleAuthor2)) {
						isBook = true;

						if(temp.stock>=saleStock2) {
							temp.stock=temp.stock-saleStock2;
							int salePrice=saleStock2*temp.price;
							String saleDate=today;
							Sale saleBook = new Sale(temp.title,temp.genre,temp.author,temp.price,temp.stock, temp.receiptDate, temp.regDate,saleDate,saleStock2,salePrice);
							saleBookStorage.add(saleBook);
							saleBook.showInfo();
							
							writeList(dataFile,BookStorage);
							writeList(saleDataFile,saleBookStorage);
							System.out.println("판매 완료");
						}else {
							System.out.println("재고 수량이 부족합니다.");return;
						}
					}else {
						System.out.println("도서가 존재하지 않습니다.");
					}
				}
				break;
			default:
				throw new MenuChoiceException(memberChoice);
			}

		}else if(choice == 2) {
			System.out.println("========================================================");
			System.out.println(" 1. 전체 매출조회  | 2. 장르별 매출조회 | 3. 판매량 높은 순 조회 ");
			System.out.println("========================================================");
			String strSubChoice = MenuViewer.sc.nextLine(); 		
			if(strSubChoice.isEmpty()) { 							
				throw new DataInputException();}
			int subChoice = Integer.parseInt(strSubChoice);
			if(subChoice==1) {
				int total=0;
				for (Sale saleBook : saleBookStorage) {
					if(saleBook.name != null && saleBook.phone != null) {
						saleBook.memberShowInfo();
					}else {
						saleBook.showInfo();
					}
	            	total = total + saleBook.salePrice;
	            }
				System.out.println("전체 매출액: "+ total);
				
			}else if(subChoice==2) {
				MenuViewer.showGenreMenu();
				String strDivision = MenuViewer.sc.nextLine();
				if(strDivision.isEmpty()) {
					throw new DataInputException();}
				int division = Integer.parseInt(strDivision);
				String genre = genreChoice(division); //genreChoice()메소드 사용
				int total=0;
				for (Sale saleBook : saleBookStorage) {
                    if (genre.equals(saleBook.getGenre())) { 
                    	saleBook.showInfo(); 
                    	total = total +saleBook.salePrice;
                    }
                }
				System.out.println("장르별 매출액: "+ total);
			} else if(subChoice == 3) {
				printSalesByStock();
			}
		}
	}

	// 판매 내역 출력
	public void printSalesByStock() {
	    if (saleBookStorage.isEmpty()) {
	        System.out.println("판매 내역이 없습니다.");
	        return;
	    }

	    Map<String, Sale> salesMap = new HashMap<>();

	    for (Sale sale : saleBookStorage) {
	        if (salesMap.containsKey(sale.title)) {
	            Sale existingSale = salesMap.get(sale.title);
	             int sumSaleStock= existingSale.saleStock + sale.saleStock;
	             int sumSalePrice= existingSale.salePrice + sale.salePrice;
	             salesMap.replace(sale.title, new Sale(sale.title,sale.genre,sale.author,sale.price,sale.stock, sale.receiptDate, sale.regDate,today,sumSaleStock,sumSalePrice));
	        } else {
	            salesMap.put(sale.title, sale);
	        }
	    }
	    
	    List<Sale> finalSales = new ArrayList<>(salesMap.values());

	    Collections.sort(finalSales, new Comparator<Sale>() {
	        @Override
	        public int compare(Sale s1, Sale s2) {
	            return Integer.compare(s2.saleStock, s1.saleStock);
	        }
	    });

	    System.out.println("판매 수량이 높은 순서로 정렬된 판매 내역:");
	    for (Sale sale : finalSales) {
	        sale.showInfo();
	    }
	}


	

}
