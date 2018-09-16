FROM clojure

WORKDIR /srv

ADD project.clj .
RUN lein deps

ADD src src
ADD resources resources
RUN lein uberjar

CMD exec java $JAVA_OPTS -jar target/uberjar/clojure-prometheus-demo.jar

#CMD ["java", "-jar", "target/uberjar/clojure-prometheus-demo.jar"]
