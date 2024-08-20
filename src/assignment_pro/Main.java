import models.Event;
import eventsDAO.EventDAO;
import eventsDAO.EventDAOImpl;
import assignment_pro.Member;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    static ArrayList<Event> events = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EventDAOImpl eventDAO = new EventDAOImpl();
        Event event = new Event();
        Member member = new Member();
        while (true) {
            showMenu();
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    member.addMember();
                    break;
                case "2":
                    member.removeMemberByID();
                    break;
                case "3":
                    member.updateMember();
                    break;
                case "4":
                    System.out.print("Nhập mã số sinh viên cần tìm: ");
                    String ID = scanner.nextLine();
                    member.findByID(ID);
                    break;
                    
                case "5":
                    member.listMembers();
                    break;
                case "6":
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
        
                    
                    eventDAO.addEvent(new Event(eventName, event.getDate(), location));
                    break;
                case "7":
                    eventDAO.sortEventsByDate();
                    for (Event eventNeedSort : eventDAO.getAllEvents()) {
                        System.out.println(eventNeedSort);
                    }
                    break;
                case "8":
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
                case "9":
                    System.out.print("Nhập tên sự kiện cần xóa: ");
                    String deleteName = scanner.nextLine();
                    eventDAO.deleteEvent(deleteName);
                    break;
                case "10":
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    // Hiển thị menu chính
    public static void showMenu() {
        System.out.println("===== MENU QUẢN LÝ C U LẠC BỘ =====");
        System.out.println("1. Thêm thành viên mới");
        System.out.println("2. Xóa thành viên theo mssv");
        System.out.println("3. Cập nhật thông tin thành viên");
        System.out.println("4. Tìm kiếm sinh viên theo mã sinh viên");
        System.out.println("5. Hiển thị danh sách thành viên");
        System.out.println("6. Thêm sự kiện");
        System.out.println("7. Hiển thị danh sách sự kiện");
        System.out.println("8. Sua");
        System.out.println("9. xoa sk");
        System.out.println("10. Thoat");
        System.out.print("Chọn một tùy chọn: ");
    }  
}

