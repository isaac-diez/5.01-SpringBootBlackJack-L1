package cat.itacademy.s05.t01.n01.repository;

import cat.itacademy.s05.t01.n01.model.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameRepo extends ReactiveMongoRepository<Game, Integer> {
}
