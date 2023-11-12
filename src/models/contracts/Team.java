package models.contracts;
import java.util.List;

public interface Team {
    String getName();

    List<Member> getMembers();

    List<Board> getBoards();
}
