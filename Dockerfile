FROM openjdk:17-alpine
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","h2*.jar","-web","-webAllowOthers","-tcp","-tcpAllowOthers","-browser"]