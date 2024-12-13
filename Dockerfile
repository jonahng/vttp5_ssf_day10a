FROM eclipse-temurin:23-noble AS builder

WORKDIR /src

COPY mvnw .
COPY pom.xml .

COPY .mvn .mvn
COPY src src


RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true


# Get the filename of the jar
# target/vttp5_ssf_day09practice-0.0.1-SNAPSHOT.jar

FROM eclipse-temurin:23-jre-noble

WORKDIR /app

COPY --from=builder /src/target/noticeboard-0.0.1-SNAPSHOT.jar app.jar
#the /src at the start is the workdir that was declared in the first stage


#!!!
#NOT SURE ABOUT THIS PORT ONE!!!!!!MAYBE go bak to the simple port method in darryl example.
#!!!!!!!!!!!!!! THIS IS CHUK CODE
#ENV PORT=8080
#Railway gives a port variable asPORT
#EXPOSE ${PORT}
#SERVER_PORT is the one that links to the spring application. so this takes the railway port given and uses that in our program.
#ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar


RUN apt update && apt install -y curl

#This is the one that worked with railway previously.
ENV SERVER_PORT 3000
EXPOSE ${SERVER_PORT}

HEALTHCHECK --interval=60s --timeout=10s --start-period=120s --retries=3 \
   CMD curl http://localhost:${SERVER_PORT}/status || exit 1
#ALWAYS REMEMBER TO CHANGE THE NAME TO CURRENT PROJECT NAME
ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar app.jar