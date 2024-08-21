package membersDAO;

import models.Member;
import java.util.List;

public interface MemberDAO {
    boolean addMember(Member member);
    void removeMemberByID(String ID);
    void updateMember(Member member, Member updateMember);
    Member findByID(String ID);
    List<Member> getAllMembers();
    boolean isExist(String checkID, String checkPhone);
}
