package models.contracts;
import java.util.List;

public interface Team {

    List<Member> getMembers();

    List<Board> getBoards();

    String getName();

    void addMembers(Member member);

    void addBoards(Board board);

}
