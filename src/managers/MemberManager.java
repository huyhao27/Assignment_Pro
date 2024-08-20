package managers;

import java.util.ArrayList;
import java.util.List;
import models.Member;

public class MemberManager {
    private List<Member> members;

    public MemberManager() {
        this.members = new ArrayList<>();
    }

    public void add(Member member) {
        if (!isExist(member.getID(), member.getPhoneNumber())) {
            members.add(member);
            System.out.println("Member added successfully.");
        } else {
            System.out.println("Duplicate information (ID or phone number), please enter again.");
        }
    }

    public void remove(String memberID) {
        Member memberToRemove = findByID(memberID);
        if (memberToRemove != null) {
            members.remove(memberToRemove);
            System.out.println("Member removed successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

    public void update(String memberID, String newPosition, String newPhoneNumber) {
        Member member = findByID(memberID);
        if (member != null) {
            if (!isPhoneNumberExist(newPhoneNumber, memberID)) {
                member.setPosition(newPosition);
                member.setPhoneNumber(newPhoneNumber);
                System.out.println("Member updated successfully.");
            } else {
                System.out.println("Phone number already exists, please enter a different one.");
            }
        } else {
            System.out.println("Member not found.");
        }
    }

    public List<Member> getAllMembers() {
        return members;
    }

    private Member findByID(String memberID) {
        for (Member member : members) {
            if (member.getID().equalsIgnoreCase(memberID)) {
                return member;
            }
        }
        return null;
    }

    private boolean isExist(String checkID, String checkPhone) {
        for (Member member : members) {
            if (member.getID().equalsIgnoreCase(checkID) || member.getPhoneNumber().equals(checkPhone)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPhoneNumberExist(String phoneNumber, String excludeID) {
        for (Member member : members) {
            if (!member.getID().equalsIgnoreCase(excludeID) && member.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}
