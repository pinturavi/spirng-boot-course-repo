FROM openjdk:8
ENV APP_FILE delivery-service-0.0.1-SNAPSHOT.jar
ENV APP_HOME /opt
EXPOSE 7020
COPY build/libs/$APP_FILE $APP_HOME
WORKDIR $APP_HOME
ENTRYPOINT ["bash", "-c"]
CMD ["java -jar $APP_FILE"]