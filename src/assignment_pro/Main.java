package assignment_pro;

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

        Menu menu = new Menu(13);
        menu.addMenuItem("===== CLUB MANAGEMENT MENU =====");
        menu.addMenuItem("1. Add new member.");
        menu.addMenuItem("2. Remove member by student RollNumber(ID).");
        menu.addMenuItem("3. Update member information.");
        menu.addMenuItem("4. Search students by student RollNumber(ID).");
        menu.addMenuItem("5. Search by name and print out members.");
        menu.addMenuItem("6. Show member list.");
        menu.addMenuItem("7. Add event.");
        menu.addMenuItem("8. Show event list.");
        menu.addMenuItem("9. Search by name and print out event(s).");
        menu.addMenuItem("10. Update event.");
        menu.addMenuItem("11.Delete event.");
        menu.addMenuItem("0. Exit program.");

        MemberManager memberManager = new MemberManager();

        while (true) {
            //      showMenu();
            menu.showMenu();
            System.out.print("Select an option:");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    boolean addFinish = false;
                    do {
                        Member newMember = createMember();
                        addFinish = memberDAO.addMember(newMember);

                    } while (!addFinish);
                    break;
                case "2":
                    System.out.print("Enter the student ID to remove: ");
                    String ID = scanner.nextLine();
                    memberDAO.removeMemberByID(ID);

                    break;
                case "3":
                    System.out.print("Enter student ID to update: ");
                    String updateID = scanner.nextLine();
                    Member updatedMember = memberDAO.findByID(updateID);
                    Member member = new Member();
                    String newPhoneNumber;
                    String position;
                    System.out.print("Enter NEW Position: ");
                    position = scanner.nextLine();
                    if (updatedMember != null) {
                        do{
                            System.out.print("Enter NEW Phone Number: ");
                            newPhoneNumber = scanner.nextLine();
                            if (!memberDAO.checkValidOfPhoneNumber(newPhoneNumber, updateID)){
                                System.out.println("PhoneNumber is existed, please try again...");
                                }   
                        }while (!memberDAO.checkValidOfPhoneNumber(newPhoneNumber, updateID));
                        member.setPhoneNumber(newPhoneNumber);
                        member.setPosition(position);
                        memberDAO.updateMember(member, updatedMember);
                    } else {
                        System.out.println("=======NO Members Found========");
                    }

                    break;
                case "4":
                    System.out.print("Enter the student ID are looking for: ");
                    String findID = scanner.nextLine();
                    Member memberFind = memberDAO.findByID(findID);
                    if (memberFind != null) {
                        System.out.println(memberFind);
                    } else {
                        System.out.println("=======NO Members Found========");
                    }
                    break;
                case "5":
                    if (!memberDAO.isEmpty()) {
                        System.out.print("Enter name to find: ");
                        String name = scanner.nextLine();
                        memberDAO.printOutByName(name);
                    } else {
                        System.out.println("==========Person does not exist!============");
                    }
                    break;
                case "6":
                    if (!memberDAO.isEmpty()) {
                        memberDAO.getAllMembers().forEach(System.out::println);
                    } else {
                        System.out.println("==========CLUB is EMPTY!============");
                    }
                    break;
                case "7":
                    eventDAO.addEvent(createEvent());
                    break;
                case "8":
                    if (!eventDAO.isEmpty()) {
                        eventDAO.sortEventsByDate();
                        for (Event eventNeedSort : eventDAO.getAllEvents()) {
                            System.out.println(eventNeedSort);
                        }
                    } else {
                        System.out.println("=======NO Events Found========");
                    }
                    break;
                case "9":
                    if (!eventDAO.isEmpty()) {
                        System.out.print("Enter name event to find: ");
                        String name = scanner.nextLine();
                        eventDAO.printOutByName(name);
                    } else {
                        System.out.println("==========Event does not exist !============");
                    }
                    break;
                case "10":
                    System.out.print("Enter the event name to update: ");
                    String updateName = scanner.nextLine();
                    Event eventToUpdate = eventDAO.getEventByName(updateName);
                    Event eventNew = new Event();
                    if (eventToUpdate != null) {
                        int newDay = 32, newMonth = 0, newYear = 0;
                        while (!eventDAO.checkDate(newDay, newMonth, newYear)) {
                            System.out.print("Enter Day: ");
                            newDay = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter Month: ");
                            newMonth = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter Year: ");
                            newYear = Integer.parseInt(scanner.nextLine());
                            if (!eventDAO.checkDate(newDay, newMonth, newYear)) {
                                System.out.println("NOT VALID! Please try again...");
                            }
                        }
                        event.setDate(String.format("%d/%d/%d", newDay, newMonth, newYear));
                        System.out.print("Enter NEW Location: ");
                        String newLocation = scanner.nextLine();
                        eventNew.setDate(event.getDate());
                        eventNew.setLocation(newLocation);
                        eventDAO.updateEvent(eventNew, eventToUpdate);

                    } else {
                        System.out.println("=======NO Events Found========");
                    }
                    break;
                case "11":
                    System.out.print("Enter the name of the event to remove: ");
                    String deleteName = scanner.nextLine();
                    eventDAO.deleteEvent(deleteName);
                    break;
                case "0":
                    System.out.println("Exit program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid selection. Please select again.");
            }
        }
    }

    public static Member createMember() {
        String name, ID, position;
        boolean isValid = true;
        do {
            System.out.print("Enter Name: ");
            name = scanner.nextLine();
            System.out.print("Enter RollNumber: ");
            ID = scanner.nextLine();
            System.out.print("Enter Position: ");
            position = scanner.nextLine();
            if (!isValidInput(ID)
                    || !isValidInput(name)
                    || !isValidInput(position)) {
                System.out.println("======INVALID INPUT======");
                isValid = false;
            } else {
                isValid = true;
            }
        } while (!isValid);
        String phoneNumber;
        do {
            System.out.print("Enter Phone Number: ");
            phoneNumber = scanner.nextLine();
            if (!checkPhoneNumber(phoneNumber)) {
                System.out.println("-----Phone number must contain only numbers. Please re-enter.-----");
            }
        } while (!checkPhoneNumber(phoneNumber));
        return new Member(name, ID, position, phoneNumber);
    }

    public static Event createEvent() {
        Event event = new Event();
        EventDAOImpl eventDAO = new EventDAOImpl();
        int day = 32, month = 0, year = 0;
        String eventName;
        do {
            System.out.print("Enter event name: ");
            eventName = scanner.nextLine();
            if (!isValidInput(eventName)||
                !eventDAO.checkValidOfEvent(eventName)) {
                System.out.println("======INVALID NAME======");
            }
        } while (!isValidInput(eventName)|| !eventDAO.checkValidOfEvent(eventName));
        event.setEventName(eventName);
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print("Enter Day: ");
                day = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter month: ");
                month = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Year: ");
                year = Integer.parseInt(scanner.nextLine());
                if (!eventDAO.checkDate(day, month, year)) {
                    System.out.println("=======NOT VALID! Please try again...======");
                } else {
                    validDate = true; // Đánh dấu rằng ngày đã hợp lệ
                }
            } catch (NumberFormatException e) {

                System.out.println("===Please enter valid numbers for day, month, and year!===");
            }
        }

        event.setDate(String.format("%d/%d/%d", day, month, year));
        String location;
        do {
            System.out.print("Enter Location: ");
            location = scanner.nextLine();
            if (!isValidInput(location)) {
                System.out.println("======INVALID LOCATION======");
            }
        } while (!isValidInput(location));
        event.setLocation(location);
        return event;
    }

    public static boolean checkPhoneNumber(String phone_Num) {
        return phone_Num.matches("\\d+");
    }

    public static boolean isValidInput(String input) {
        String regex = ".*[a-zA-Z0-9]+.*";
        return input.matches(regex);
    }

}
