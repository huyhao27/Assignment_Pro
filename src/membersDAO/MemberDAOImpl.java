package membersDAO;

import models.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    private List<Member> members = new ArrayList<>();

    @Override
    public void addMember(Member member) {
        members.add(member);
        System.out.println("Thành viên đã được thêm thành công.");
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

    @Override
    public List<Member> getAllMembers() {
        return members;
    }

    @Override
    public boolean isExist(String checkID, String checkPhone) {
        for (Member member : members) {
            if (member.getID().equalsIgnoreCase(checkID) || member.getPhoneNumber().equals(checkPhone)) {
                return true;
            }
        }
        return false;
    }
}