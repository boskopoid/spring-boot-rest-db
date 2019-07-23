FROM java:8
ADD target/simple-service-0.0.1-SNAPSHOT.jar kabbie-go.jar
RUN bash -c 'touch /kabbie-go.jar'
ENTRYPOINT ["java", "-jar", "/kabbie-go.jar" ]
