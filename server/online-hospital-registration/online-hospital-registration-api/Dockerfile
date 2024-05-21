# Dockerfile for registration
FROM container-registry.oracle.com/java/jdk-no-fee-term:21.0.3

WORKDIR /app

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


COPY ./target/online-hospital-registration-api-0.0.1-SNAPSHOT.jar /app/registration.jar

ENTRYPOINT ["java", "-jar", "/app/registration.jar"]

EXPOSE 8230