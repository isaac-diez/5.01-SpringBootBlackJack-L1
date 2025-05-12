package cat.itacademy.s05.t01.n01.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("players")
public class Player {

    @Id
    private Integer id;
    private String name;
    private PlayerType type;
    private int gains = 0;

    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }

    public Integer getGains() {
        return gains;
    }

    public void setGains(int bet) {
        gains = gains + bet;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }
}
