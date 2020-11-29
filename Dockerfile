FROM azul/zulu-openjdk-alpine:11
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/bank-count-service.jar
WORKDIR /app
ENTRYPOINT java -jar bank-count-service.jar