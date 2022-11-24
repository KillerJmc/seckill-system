FROM openjdk:17
COPY app.jar /root/app.jar
ENTRYPOINT /wait && java --add-opens java.base/sun.reflect.annotation=ALL-UNNAMED \
  -jar /root/app.jar --spring.profiles.active=prod

