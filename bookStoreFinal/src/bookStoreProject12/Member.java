package bookStoreProject12;

import java.io.Serializable;
import java.util.Objects;

public class Member implements Serializable {
	String name;
	int memberLevel;
	int mileage;
	int discount;
	String reg_date;
	String phoneNumber;
	String address;
	int age;
    
	public Member() {
		this("아무개", 1, 0, 10, "0000-00-00", "010-0000-0000", "주소 없음", 0);
	}
	
	public String getName() {
        return name;
    }
	
	Member(String name, int memberLevel, int mileage, int discount, String reg_date, String phoneNumber, String address, int age) {
		this.name = name;
        this.memberLevel = memberLevel;
        this.mileage = mileage;
        this.discount = discount;
        this.reg_date = reg_date;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
    }

    void showInfo() {
        System.out.printf("이름: %s, 전화번호: %s, 주소: %s, 나이: %d, 회원 등급: %d, 마일리지: %d, 할인율: %d%%, 등록일: %s%n", 
            name, phoneNumber, address, age, memberLevel, mileage, discount, reg_date);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Member)) return false;
        Member M = (Member) obj;
        return name.equals(M.name) && phoneNumber.equals(M.phoneNumber);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(name, memberLevel, mileage, discount, reg_date, phoneNumber, address, age);
        return Objects.hash(name, phoneNumber);
    }
}

