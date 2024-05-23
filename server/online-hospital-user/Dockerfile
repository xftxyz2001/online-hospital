# Dockerfile for user
FROM container-registry.oracle.com/java/jdk-no-fee-term:21.0.3

WORKDIR /app

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


COPY ./target/online-hospital-user-0.0.1-SNAPSHOT.jar /app/user.jar

ENTRYPOINT ["java", "-jar", "/app/user.jar"]

EXPOSE 8210