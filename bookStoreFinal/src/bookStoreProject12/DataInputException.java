package bookStoreProject12;

public class DataInputException extends Exception{

	public static void inputCheck(){
		System.out.println("============");
		System.out.println(" 입력오류입니다");
		System.out.println("============");
	}
	DataInputException(){
		System.out.println("입력오류.");
	}
	DataInputException(String str){
		System.out.println("=============");
		System.out.println("입력이 없습니다.");
		System.out.println("=============");
	}
	
}

