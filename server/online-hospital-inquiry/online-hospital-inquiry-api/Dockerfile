# Dockerfile for inquiry
FROM container-registry.oracle.com/java/jdk-no-fee-term:21.0.3

WORKDIR /app

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


COPY ./target/online-hospital-inquiry-api-0.0.1-SNAPSHOT.jar /app/inquiry.jar

ENTRYPOINT ["java", "-jar", "/app/inquiry.jar"]

EXPOSE 8240