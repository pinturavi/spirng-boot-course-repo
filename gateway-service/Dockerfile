FROM openjdk:8
ENV APP_FILE gateway-service-0.0.1-SNAPSHOT.jar
ENV APP_HOME /opt
EXPOSE 8080
COPY build/libs/$APP_FILE $APP_HOME
WORKDIR $APP_HOME
ENTRYPOINT ["bash", "-c"]
CMD ["java -jar $APP_FILE"]