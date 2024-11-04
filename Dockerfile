FROM openjdk:17-jdk-slim
EXPOSE 8083
ADD target/kaddem-0.0.3-RELEASE.war kaddem-0.0.3-RELEASE.war
ENTRYPOINT ["java","-jar","/kaddem-0.0.3-SNAPSHOT.war"]