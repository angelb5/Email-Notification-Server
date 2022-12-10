FROM openjdk:17.0.2-jdk
VOLUME /tmp
ADD target/xd-0.0.1-SNAPSHOT.jar servicio-mail.jar
ENTRYPOINT ["java","-jar","./servicio-mail.jar","--eureka.client.service-url.defaultZone=http://eureka:8761/eureka"]
