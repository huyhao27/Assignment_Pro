package models;

import static membersDAO.MemberDAOImpl.formatName;

public class Member {
    private String name;
    private String position;
    private String phoneNumber;
    private String ID;

    public Member() {
    }

    public Member(String name,String ID, String position, String phoneNumber) {
        this.name = formatName(name);
        this.ID = ID;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

    // Getter và Setter
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Rollnumber: " + ID + ", Tên: " + name + ", Vị trí: " + position + ", Số điện thoại: " + phoneNumber;
    }
}