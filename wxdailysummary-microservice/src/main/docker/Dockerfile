FROM java:8
VOLUME /tmp

ADD wxdailysummary-microservice-0.1.0.jar app.jar
ADD *.csv ./data/
RUN bash -c 'touch /app.jar'
EXPOSE 8765
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Ddata.dir=/data", "-jar","/app.jar"]
