package cat.itacademy.s05.t01.n01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="players")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private PlayerType type;
    private List<Integer> gains;

    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
        this.gains = new ArrayList<>();
    }

    public List<Integer> getGains() {
        return gains;
    }

    public Integer getTotalGains() {
        return gains.stream().mapToInt(Integer::intValue).sum();
    }

    public int getId() {
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
