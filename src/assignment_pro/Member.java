package assignment_pro;
import java.util.ArrayList;
import java.util.Scanner;

public class Member {

    static ArrayList<Member> members = new ArrayList<>();
    String name;
    String position;
    String phoneNumber;
    String ID;
    Scanner scanner = new Scanner(System.in);

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Member(String name, String ID, String position, String phoneNumber) {
        this.name = name;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Rollnumber: " + ID + ", Tên: " + name + ", Vị trí: " + position + ", Số điện thoại: " + phoneNumber;
    }// Thêm thành viên mới

    public void addMember() {
        while (true) {
            System.out.print("Nhập tên thành viên: ");
            String name = scanner.nextLine();
            System.out.print("Enter rollnumber: ");
            String ID = scanner.nextLine();
            System.out.print("Nhập vị trí: ");
            String position = scanner.nextLine();

            String phoneNumber;
            do {
                System.out.print("Nhập số điện thoại: ");
                phoneNumber = scanner.nextLine();
                if (!checkPhoneNumber(phoneNumber)) {
                    System.out.println("SDT chỉ bao gồm các chữ số. Vui lòng nhập lại...");
                }
            } while (checkPhoneNumber(phoneNumber) == false);

            if (isExist(ID, phoneNumber)) {
                System.out.println("Thông tin trùng (mssv or sdt), vui lòng nhập lại");
                continue;
            }
            System.out.println("Thành viên đã được thêm thành công.");
            members.add(new Member(formatName(name), ID, position, phoneNumber));
            break;
        }
    }

    public static String formatName(String name) {

        name = name.trim().toLowerCase();

        String[] words = name.split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                formattedName.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        return formattedName.toString().trim();
    }

    public boolean checkPhoneNumber(String phone_Num) {
        return phone_Num.matches("\\d+");
    }

    // Xóa thành viên
    /* public void removeMemberByName() {
        System.out.print("Nhập tên thành viên cần xóa: ");
        String name = scanner.nextLine();
        
        Member memberToRemove = null;
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                memberToRemove = member;
                break;
            }
        }

        if (memberToRemove != null) {
            members.remove(memberToRemove);
            System.out.println("Thành viên đã được xóa.");
        } else {
            System.out.println("Không tìm thấy thành viên.");
        }
    }*/
    public void removeMemberByID() {
        System.out.print("Enter rollnumber want to remove: ");
        String IDremove = scanner.nextLine();

        Member memberToRemove = null;
        for (Member member : members) {
            if (member.ID.equalsIgnoreCase(IDremove)) {
                memberToRemove = member;
                break;
            }
        }

        if (memberToRemove != null) {
            members.remove(memberToRemove);
            System.out.println("Thành viên đã được xóa.");
        } else {
            System.out.println("Không tìm thấy thành viên.");
        }
    }

    // Cập nhật thông tin thành viên
    public void updateMember() {
        System.out.print("Nhập mssv thành viên cần cập nhật: ");
        String findID = scanner.nextLine();
        boolean isWrong = true;

        for (Member member : members) {
            while (isWrong) {
                isWrong = false;
                if (member.ID.equalsIgnoreCase(findID)) {
                    System.out.print("Nhập vị trí mới: ");
                    member.setPosition(scanner.nextLine());
                    System.out.print("Nhập số điện thoại mới: ");
                    String newPhone = scanner.nextLine();
                    member.setPhoneNumber(newPhone);
                    for (Member memberCheckPhone : members) {
                        if (!memberCheckPhone.ID.equalsIgnoreCase(findID) 
                            && newPhone.equals(memberCheckPhone.getPhoneNumber())) {
                            System.out.println("SDT da ton tai, vui long nhap lai");
                            isWrong = true;
                            break;
                        }
                    }
                } else {
                    System.out.println("Không tìm thấy thành viên.");
                }
            }
        }
        System.out.println("Thông tin thành viên đã được cập nhật.");
        return;
    }

    // Hiển thị danh sách thành viên
    public void listMembers() {
        if (members.isEmpty()) {
            System.out.println("Chưa có thành viên nào trong câu lạc bộ.");
        } else {
            System.out.println("===== DANH SÁCH THÀNH VIÊN =====");
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }

    public void findByID(String findID) {
        for (Member member : members) {
            if (member.ID.equalsIgnoreCase(findID)) {
                System.out.println(member);
                return;
            }
        }
        System.out.println("Không tìm thấy thành viên.");
    }

    public boolean isExist(String checkID, String checkPhone) {
        for (Member member : members) {
            if (member.ID.equalsIgnoreCase(checkID) 
                || member.phoneNumber.equals(checkPhone)) {
                return true;
            }
        }
        return false;
    }
}
