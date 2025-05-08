package cat.itacademy.s05.t01.n01.repository;

import cat.itacademy.s05.t01.n01.model.Player;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PlayerRepo extends R2dbcRepository<Player, Integer> {
}
