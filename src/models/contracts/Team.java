package models.contracts;
import java.util.List;

public interface Team {

    List<Member> getMembers();

    List<Board> getBoards();

    String showAllTeams();

    void addBoard(Board board);

    String getName();

    void addMembers(Member member);
   // List<String> getTeamNames();
}
