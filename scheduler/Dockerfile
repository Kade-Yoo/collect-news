FROM amazoncorretto:17
VOLUME /tmp
ARG JAR_FILE=build/libs/scheduler-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} scheduler.jar

ENTRYPOINT ["java","-jar","/scheduler.jar"]