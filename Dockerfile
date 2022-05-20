FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD target/stockexchange.jar stockexchange
ENTRYPOINT ["java","-jar","/stockexchange.jar"]