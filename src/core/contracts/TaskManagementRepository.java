package core.contracts;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Task;
import models.contracts.Team;

import java.util.List;

public interface TaskManagementRepository {

    Member findMemberByUsername (String username);

    boolean memberExist(String username);

    Member createMember(String username);
    Board createBoard(String name);
    void addBoard(Board board);


}
