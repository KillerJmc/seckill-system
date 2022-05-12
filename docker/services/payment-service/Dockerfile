FROM openjdk:17
COPY app.jar /root/app.jar
ENTRYPOINT /wait && java -jar /root/app.jar --spring.profiles.active=test
