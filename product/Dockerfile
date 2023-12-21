FROM openjdk:17
VOLUME /tmp
ADD ./target/product-0.0.1-SNAPSHOT.jar products-service.jar
ENTRYPOINT [ "java", "-jar", "/products-service.jar" ]