FROM amazoncorretto:17
VOLUME /tmp
ARG JAR_FILE=build/libs/crawler-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} crawler.jar

ENTRYPOINT ["java","-jar","/crawler.jar"]