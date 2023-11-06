FROM fredrikhgrelland/alpine-jdk11-openssl
WORKDIR /app
COPY build/libs/*SNAPSHOT.jar ./users.jar

CMD ["java", "-jar", "users.jar"]