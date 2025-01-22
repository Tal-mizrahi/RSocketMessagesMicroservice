FROM openjdk:21-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} RSocketMessagesMicroservice.jar
EXPOSE 7001
ENTRYPOINT ["java","-jar","/RSocketMessagesMicroservice.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]