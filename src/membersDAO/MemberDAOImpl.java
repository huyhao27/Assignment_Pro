package membersDAO;

import models.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    private List<Member> members = new ArrayList<>();

    @Override
    public boolean addMember(Member member) {
        if (!isExist(member.getID(), member.getPhoneNumber())) {
            members.add(member);
            System.out.println("Member added successfully.");
            return true;
        } else {
            System.out.println("The phone number or roll number already exists, please re-enter.");
            return false;
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

    @Override
    public void removeMemberByID(String ID) {
        Member memberToRemove = findByID(ID);
        if (memberToRemove != null) {
            members.remove(memberToRemove);
            System.out.println("Member has been deleted.");
        } else {
            System.out.println("No members found.");
        }
    }

    @Override
    public void updateMember(Member member, Member updateMember) {

        updateMember.setPosition(member.getPosition());
        updateMember.setPhoneNumber(member.getPhoneNumber());
        System.out.println("Member information has been updated.");

    }

    @Override
    public Member findByID(String ID) {
        for (Member member : members) {
            if (member.getID().equalsIgnoreCase(ID)) {
                return member;
            }
        }
        return null;
    }

    public void printOutByName(String name) {
        for (Member member : members) {
            StringBuilder find = new StringBuilder(member.getName().toLowerCase());
            if (find.toString().contains(name.toLowerCase())) {
                System.out.println(member);
            }
        }
    }

    @Override
    public List<Member> getAllMembers() {
        return members;
    }

    /*public boolean isExist(String checkID, String checkPhone) {
        for (Member member : members) {
            if (member.getID().equalsIgnoreCase(checkID) 
                || member.getPhoneNumber().equals(checkPhone)) {
                return true;
            }
        }
        return false;
    }*/
    @Override
    public boolean isExist(String checkID, String checkPhone) {
        for (Member member : members) {
            if (member.getID().equalsIgnoreCase(checkID)
                    || member.getPhoneNumber().equals(checkPhone)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        if (members.isEmpty()) {
            return true;
        }
        return false;
    }
    public boolean checkValidOfPhoneNumber(String newPhoneNumber, String updateID){
        for ( Member o : members){
            if ( !o.getID().equalsIgnoreCase(updateID)&& o.getPhoneNumber().equals(newPhoneNumber) ){
                return false;
            }
        }
        return true;
    }
}
