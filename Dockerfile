FROM openjdk:8-jdk-alpine

VOLUME /tmp

ENV JAVA_OPTS -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=70 -XX:CompressedClassSpaceSize=64m -XX:ReservedCodeCacheSize=64m -XX:MaxMetaspaceSize=256m -Xmx825m
ENV environment local
ENV tz Asia/Calcutta
ENV loadbalancer localhost

ENV NEW_RELIC_APP_NAME="MB Java;MB Java ($environment);MB Java - Aggregate ($environment)"
ENV NEW_RELIC_LICENSE_KEY 96556c66b7b385a16c5a229ce2494fe67f6b8320

ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ADD ./config /config/

ARG NEWRELIC_JAR_FILE
ARG NEWRELIC_YAML_FILE
ADD ${NEWRELIC_JAR_FILE} newrelic.jar
ADD ${NEWRELIC_YAML_FILE} newrelic.yml 

ENTRYPOINT ["sh", "-c", "java -XX:+PrintFlagsFinal $JAVA_OPTS -javaagent:/newrelic.jar -Dmilkbasket.application.timezone=$tz -Dservice.loadbalancer.url=$loadbalancer -Dspring.profiles.active=$environment -Dnewrelic.environment=$environment -Dmilkbasket.external.config=/config -Djava.security.egd=file:/dev/./urandom -jar /app.jar -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n"]