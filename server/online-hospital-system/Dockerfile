# Dockerfile for system
FROM container-registry.oracle.com/java/jdk-no-fee-term:21.0.3

WORKDIR /app

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


COPY ./target/online-hospital-system-SNAPSHOT.jar /app/system.jar

ENTRYPOINT ["java", "-jar", "/app/system.jar"]

EXPOSE 8280