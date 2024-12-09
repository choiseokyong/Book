package bookStoreProject12;

public class Main {

	public static void main(String[] args) {
		BookManager bm = BookManager.getBookManager();
		int choice;
		while(true) {
			try {
				MenuViewer.showMenu();
				choice = Integer.parseInt(MenuViewer.sc.nextLine());
			if(choice<MainMenu.INPUT || choice > MainMenu.EXIT)
					throw new MenuChoiceException(choice);
				switch(choice) {
				case MainMenu.INPUT: bm.inputData();break;
				case MainMenu.UPDATE : bm.upData();break;
				case MainMenu.DELETE : bm.deleteData();break;
				case MainMenu.DISPAY_ALL : bm.printAll();break;
				case MainMenu.SALE : bm.saleBook();break;
				case MainMenu.EXIT : System.out.println("END");return;
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력해 주세요");

			}catch(MenuChoiceException e) {
				System.out.println("메뉴를 다시 선택해 주세요");
			}catch(Exception e) {
				
			}
		}
	}
}
