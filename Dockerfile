FROM amazoncorretto:17 AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY domain domain
COPY application application
COPY scheduler scheduler
COPY crawler crawler
RUN chmod +x ./gradlew
RUN ./gradlew :application:clean :application:build -x test
RUN ./gradlew :domain:compileJava
RUN ./gradlew :application:bootJar
RUN ./gradlew :crawler:clean :crawler:build -x test
RUN ./gradlew :crawler:bootJar
RUN ./gradlew :scheduler:clean :scheduler:build -x test
RUN ./gradlew :scheduler:bootJar

FROM amazoncorretto:17
COPY --from=builder /application/build/libs/*.jar app.jar
COPY --from=builder /crawler/build/libs/*.jar crawler.jar
COPY --from=builder /scheduler/build/libs/*.jar scheduler.jar