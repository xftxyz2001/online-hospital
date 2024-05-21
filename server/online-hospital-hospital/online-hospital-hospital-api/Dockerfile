# Dockerfile for hospital
FROM container-registry.oracle.com/java/jdk-no-fee-term:21.0.3

WORKDIR /app

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


COPY ./target/online-hospital-hospital-api-0.0.1-SNAPSHOT.jar /app/hospital.jar

ENTRYPOINT ["java", "-jar", "/app/hospital.jar"]

EXPOSE 8220