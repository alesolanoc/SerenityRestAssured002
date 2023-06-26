# docker build -t restassured001:1 .
# docker run -it restasssured001:1 ash
FROM maven:3.9.2
COPY src home/apiframework/src
COPY pom.xml home/apiframework/pom.xml
WORKDIR home/apiframework
ENTRYPOINT mvn clean verify
