FROM openjdk:17
VOLUME /tmp
ADD ./target/item-0.0.1-SNAPSHOT.jar items-service.jar
ENTRYPOINT [ "java", "-jar", "/items-service.jar" ]