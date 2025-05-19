## 5.01-SpringBootBlackJack-L1

This project is a RESTful reactive application built with **Spring Boot** that manages a game of BlackJack sort of autonomously, persisted in an **MySQL** using the **r2dbc** for players and MongoDB for all games related and structured following the **MVC (Model-View-Controller)** pattern.

## 📦 Package Structure

The project is organized under the base package `cat.itacademy.s04.t02.n01` with the following structure:

- `controllers`: Handles HTTP requests (REST API)
- `model`: Contains the main entities: `Player, Game, Card, Deck and Hand` entities together with 5 supporting enums `CardName, CardSuit, CardValue, PlayerDecision and PlayerType`.
- `repository`: Defines the two interfaces for persistence: Player and Game
- `service`: Contains main business logic for CRUD operations
- `session`: Contains all individual methods that take care of all aspects of a game
- `exception`: Manages custom exceptions and global error handling
- `dto`: receives name of player and initial bet to start a new game.

The Game needs a player and an initial bet to start. Two cards are drawn for the player and two for the dealer. Then the automatic strategy kicks in depending on cards value:
- between 9 and 11 -> doubledown: bet is double.
- the two cards are equal value -> split: player plays 2 hands separately. No doubledowns, just hit and stand, or bust.
- while value of hand is below 17 -> hit: another card will be dealt.
- value over 17 and below or equal to 21 -> stand: no more cards will be asked.

Then the dealer plays until the hand value is over 17.

The value of the hand (or split hands) of the player will be compared to the value of hand of the dealer. The higher wins if nobody busted. If player wins, the bet will be added up to the list of gains. 

### 🔗 Available Player Endpoints:
| Method | URL                   | Description                        |
|--------|-----------------------|------------------------------------|
| POST   | `/players/add`        | Adds a new player to the database  |
| PUT    | `/players/update`      | Updates an existing player         |
| DELETE | `/players/delete/{id}` | Deletes a player by ID          |
| GET    | `/players/getOne/{id}` | Retrieves a single player by ID |
| GET    | `/players/getAll`      | Retrieves all players              |

### 🔗 Available Game Endpoints:
| Method | URL                      | Description                              |
|--------|--------------------------|------------------------------------------|
| POST   | `/games/add`             | Adds a new game to the database          |
| POST   | `/games/{id}/play` | Plays a newly created game               |
| DELETE | `/games/delete/{id}`     | Deletes a game by ID                     |
| GET    | `/games/getOne/{id}`     | Retrieves details of a single game by ID |
| GET    | `/games/getAll`          | Retrieves all games                      |

## 5.01-SpringBootBlackJack-L2
App is dockerized into an image containing its own instance, plus both MySQL and MongoBD databases.

It's updated into Docker Hub: https://hub.docker.com/r/isaacdiez/blackjack

You can retrieve it with the following command: docker pull isaacdiez/blackjack


### 💻Stack used: Project created with:

    Java v.17
    Spring Boot 3.4.5:
        Spring WebFlux
        R2DBC 1.0.2
        Spring Boot DevTools
        Swagger 2.5.0
    Maven v.3.9.9.
    IDE IntelliJ Idea v. 24.3.1.1

📋Requirements: No specific requirement but the Java and Maven version or newer.

🛠️Installation:

    Clone this repo: -> git clone

▶️Execution: No specific instruction.

🌐Deployment: N/A.

🤝Contributions:

Contributions are welcome! Please follow the following steps to contribute:

    Fork the repository.
    Create a new: git checkout branch -b feature/News.
    Make your changes and commit them: git commit - 'Add New Functionality'.
    Upload the changes to your branch: git push feature/News.
    Do a pull request.





