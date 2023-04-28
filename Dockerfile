FROM eclipse-temurin:17

LABEL mentainer="dotrantb25@gmail.com"

WORKDIR /app

COPY target/thue-TNCN-2-0.0.1-SNAPSHOT.jar /app/thue-TNCN.jar

ENTRYPOINT ["java", "-jar", "thue-TNCN.jar"]