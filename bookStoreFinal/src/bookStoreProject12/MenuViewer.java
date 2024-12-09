package bookStoreProject12;

import java.util.Scanner;

public class MenuViewer {

	static Scanner sc = new Scanner(System.in);

	public static void showMenu() {
		System.out.println("┌──────────────────────┐");
        System.out.println("│   Choose             │");
        System.out.println("├─────┬────────────────┤");
        System.out.println("│ no. │  List          │");
        System.out.println("├─────┼────────────────┤");
        System.out.println("│  1  │ Insert Data    │");
        System.out.println("├─────┼────────────────┤");
        System.out.println("│  2  │ Search Data    │");
        System.out.println("├─────┼────────────────┤");
        System.out.println("│  3  │ Update Data    │");
        System.out.println("├─────┼────────────────┤");
        System.out.println("│  4  │ Delete Data    │");
        System.out.println("├─────┼────────────────┤");
        System.out.println("│  5  │ Print Data     │");
        System.out.println("├─────┼────────────────┤");
        System.out.println("│  6  │ Sale Book      │");
        System.out.println("├─────┼────────────────┤");
        System.out.println("│  7  │ End            │");
        System.out.println("└─────┴────────────────┘");
        System.out.print("선택 : ");
	}
	//장르 메뉴를 보여주는 메소드 추가
	public static void showGenreMenu() {
		System.out.println("=======================================================");
        System.out.println("| 1.로맨스 2.에세이 3.참고서 4.장르소설 5.소설 6.과학 7.사회 8.기타 |");
        System.out.println("=======================================================");
	}
}
