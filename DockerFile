FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/kaddem-0.0.1-RELEASE.war kaddem-0.0.1-RELEASE.war
ENTRYPOINT ["java","-jar","/kaddem-0.0.1-RELEASE.war"]
