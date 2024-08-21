

import managers.MemberManager;
import assignment_pro.Menu;
import models.Event;
import eventsDAO.EventDAOImpl;
import models.Member;
import membersDAO.MemberDAOImpl;
import java.util.Scanner;


public class Main {


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Event event = new Event();
        EventDAOImpl eventDAO = new EventDAOImpl();
        MemberDAOImpl memberDAO = new MemberDAOImpl();
        
        Menu menu = new Menu(11);
        menu.addMenuItem("===== MENU QUẢN LÝ C U LẠC BỘ =====");
        menu.addMenuItem("1. Thêm thành viên mới");
        menu.addMenuItem("2. Xóa thành viên theo mssv");
        menu.addMenuItem("3. Cập nhật thông tin thành viên");
        menu.addMenuItem("4. Tìm kiếm sinh viên theo mã sinh viên");
        menu.addMenuItem("5. Tìm kiếm theo tên và in ra thành viên");
        menu.addMenuItem("6. Hiển thị danh sách thành viên");
        menu.addMenuItem("7. Thêm sự kiện");
        menu.addMenuItem("8. Hiển thị danh sách sự kiện");
        menu.addMenuItem("9. Sua");
        menu.addMenuItem("10. xoa sk");
        menu.addMenuItem("0. Thoat");
        
        
      
        
        MemberManager memberManager = new MemberManager();

        while (true) {
    //      showMenu();
            menu.showMenu();
            System.out.print("Chọn một tùy chọn: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    boolean addFinish= false;
                    do{
                    Member newMember = createMember();
                    addFinish = memberDAO.addMember(newMember);
                     
                    }
                    while(!addFinish);
                    break;
                case "2":
                    System.out.print("Nhập mã số sinh viên cần xóa: ");
                    String ID = scanner.nextLine();
                    memberDAO.removeMemberByID(ID);

                    break;
                case "3":
                    System.out.print("Nhập mã số sinh viên cần cập nhật: ");
                    String updateID = scanner.nextLine();
                    Member updatedMember = memberDAO.findByID(updateID);
                    if (updatedMember != null) {
                        System.out.print("Nhập vị trí mới: ");
                        updatedMember.setPosition(scanner.nextLine());
                        System.out.print("Nhập số điện thoại mới: ");
                        updatedMember.setPhoneNumber(scanner.nextLine());
                        memberDAO.updateMember(updatedMember);
                    } else {
                        System.out.println("==========NO Members Found==========");
                    }
                    
                    break;
                case "4":
                    System.out.print("Nhập mã số sinh viên cần tìm: ");
                    String findID = scanner.nextLine();
                    Member member = memberDAO.findByID(findID);
                    if (member != null) {
                        System.out.println(member);
                    } else {
                        System.out.println("==========NO Members Found==========");
                    }
                    break;
                case "5": 
                    if (!memberDAO.isEmpty()){
                        System.out.print("Enter name to find: ");
                        String name = scanner.nextLine();
                        memberDAO.printOutByName(name);
                    }else System.out.println("==========CLB is EMPTY!============");
                    break;
                case "6":
                    if (!memberDAO.isEmpty()){   
                    memberDAO.getAllMembers().forEach(System.out::println);
                    }else System.out.println("==========CLB is EMPTY!============");
                    break;
                case "7": 
                    eventDAO.addEvent(creatEvent());
                    break;
                case "8":
                    eventDAO.sortEventsByDate();
                    for (Event eventNeedSort : eventDAO.getAllEvents()) {
                        System.out.println(eventNeedSort);
                    }
                    break;
                case "9":
                    System.out.print("Nhập tên sự kiện cần cập nhật: ");
                    String updateName = scanner.nextLine();
                    Event eventToUpdate = eventDAO.getEventByName(updateName);
                    if (eventToUpdate != null) {
                        int newDay =32, newMonth=0, newYear=0;
                        while (!eventDAO.checkDate(newDay, newMonth,newYear))
                        {
                        System.out.print("Nhập ngày: ");
                        newDay = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter month: ");
                        newMonth = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Year: ");
                        newYear = Integer.parseInt(scanner.nextLine());
                            if(!eventDAO.checkDate(newDay, newMonth,newYear)){
                                System.out.println("NOT VALID! Please try again...");
                            }
                        }
                        event.setDate(String.format("%d/%d/%d", newDay, newMonth, newYear));
                        System.out.print("Nhập địa điểm mới: ");
                        String newLocation = scanner.nextLine();
                        eventToUpdate.setDate(event.getDate());
                        eventToUpdate.setLocation(newLocation);
                        eventDAO.updateEvent(eventToUpdate);
                    } else {
                        System.out.println("Sự kiện không tồn tại.");
                    }
                    break;
                case "10":
                    System.out.print("Nhập tên sự kiện cần xóa: ");
                    String deleteName = scanner.nextLine();
                    eventDAO.deleteEvent(deleteName);
                    break;
                case "0":
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    
    public static Member createMember() {
        System.out.print("Nhập tên thành viên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập mã số sinh viên: ");
        String ID = scanner.nextLine();
        System.out.print("Nhập vị trí: ");
        String position = scanner.nextLine();
        String phoneNumber;
        do{
            System.out.print("Nhập số điện thoại: ");
            phoneNumber = scanner.nextLine();
                if (!checkPhoneNumber(phoneNumber)){
                System.out.println("-----SDT chỉ bao gồm chữ số.Vui lòng nhập lại-----");
                }
        }while(!checkPhoneNumber(phoneNumber));
        return new Member( name, ID, position, phoneNumber);
    }
    public static Event creatEvent(){
        Event event = new Event();
        EventDAOImpl eventDAO = new EventDAOImpl();
        int day=32 , month=0, year=0;
        System.out.print("Nhập tên sự kiện: ");
        String eventName = scanner.nextLine();
        while (!eventDAO.checkDate(day, month,year))
            {
            System.out.print("Nhập ngày: ");
            day = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter month: ");
            month = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Year: ");
            year = Integer.parseInt(scanner.nextLine());
            if(!eventDAO.checkDate(day, month,year)){
                System.out.println("NOT VALID! Please try again...");
                }
            }
        event.setDate(String.format("%d/%d/%d", day, month, year));
                
        System.out.print("Nhập địa điểm: ");
        String location = scanner.nextLine();
        return event;
    }
    public static boolean checkPhoneNumber(String phone_Num) {
        return phone_Num.matches("\\d+");
        }
}
