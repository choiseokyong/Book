package bookStoreProject12;

public class MenuChoiceException extends Exception {
	int wrongChoice;
	MenuChoiceException(int choice){
		super("잘못된 메뉴입니다.");
		wrongChoice = choice;
	}
	MenuChoiceException(String str){
		if(str.isEmpty()) {
			System.out.println("입력값이 없습니다.");
		}else {
			System.out.println("메뉴를 다시 선택해 주세요");
		}
	}
	public void showWrongChoice() {
		System.out.println(wrongChoice + "번은 존재하지 않는 메뉴입니다.");
	}
}