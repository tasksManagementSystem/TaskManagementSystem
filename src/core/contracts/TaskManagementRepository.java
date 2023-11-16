package core.contracts;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Task;
import models.contracts.Team;
import models.enums.Severity;

import java.util.List;

public interface TaskManagementRepository {

    List<Member> getMemberList();

    Member findMemberByUsername (String username);

    boolean memberExist(String username);

    Member createMember(String username);
    Board createBoard(String name);
    Team createTeam(String name);
    void addBoard(Board board);
    List<Board> getBoards();
    List<Team> getTeamsList();

    Severity getSeverity();
    Team findTeamByName(String name);

}
