package membersDAO;

import models.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    private List<Member> members = new ArrayList<>();

    @Override
    public boolean addMember(Member member) {
       if( !isExist(member.getID(),member.getPhoneNumber()))
        { 
            members.add(member);
            System.out.println("Thành viên đã được thêm thành công.");
            return true;
        }
       else {
           System.out.println("SDT or rollnum da ton tai, nhap lai");
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
            System.out.println("Thành viên đã được xóa.");
        } else {
            System.out.println("Không tìm thấy thành viên.");
        }
    }

    @Override
    public void updateMember(Member member) {
        Member existingMember = findByID(member.getID());
        if (existingMember != null) {
            existingMember.setPosition(member.getPosition());
            existingMember.setPhoneNumber(member.getPhoneNumber());
            System.out.println("Thông tin thành viên đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy thành viên.");
        }
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
    public void printOutByName(String name){
        if (members.isEmpty()){
           System.out.println("CLB is empty!");
         } 
        for ( Member member : members){
            if (member.getName().contains(name)){
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
}
