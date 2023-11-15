package core.contracts;

import models.contracts.Member;
import models.contracts.Task;

import java.util.List;

public interface TaskManagementRepository {

    Member findMemberByUsername (String username);

    boolean memberExist(String username);

    Member creatMember(String username);





}
