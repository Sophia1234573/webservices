FROM maven:3.8.1-jdk-11 AS MAVEN_BUILD
COPY . /
WORKDIR /
RUN mvn clean -Dmaven.test.skip package

FROM tomcat:9.0-jdk16-temurin
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=MAVEN_BUILD target/spring.war /usr/local/tomcat/webapps/ROOT.war
COPY --from=MAVEN_BUILD target/spring /usr/local/tomcat/webapps/ROOT
CMD ["catalina.sh","run"]