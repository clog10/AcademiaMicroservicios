FROM openjdk:17
EXPOSE 8090
VOLUME /tmp
ADD ./target/gateway-server-0.0.1-SNAPSHOT.jar gateway-server.jar
ENTRYPOINT [ "java", "-jar", "/gateway-server.jar" ]