FROM openjdk:17-jdk-slim
LABEL maintainer Isaac
ADD target/S05T01N01A-BlackJack-0.0.1-SNAPSHOT.jar S05T01N01A-BlackJack-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","S05T01N01A-BlackJack-0.0.1-SNAPSHOT.jar"]