FROM openjdk:17.0.2-jdk
VOLUME /tmp
ADD target/xd-0.0.1-SNAPSHOT.jar servicio-firebase.jar
ENTRYPOINT ["java","-jar","./servicio-firebase.jar","--eureka.client.service-url.defaultZone=http://eureka:8761/eureka"]
