(defproject clojure-prometheus-demo "0.1.0-SNAPSHOT"
  :description "docker-compose stack demonstrating clojure + prometheus + grafana"
  :url "https://github.com/joachimdraeger/clojure-prometheus-demo"
  :license {:name "Public Domain" }
  :dependencies [
                 [org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.logging "0.4.0"]
                 [org.slf4j/slf4j-log4j12 "1.7.21"]
                 [http-kit "2.3.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.1"]
                 [clj-http "3.9.1"]
                 [cheshire "5.8.0"]

                 [iapetos "0.1.7"]
                 [io.prometheus/simpleclient_hotspot "0.1.0"]
                 ]
  :main ^:skip-aot clojure-prometheus-demo.core
  :target-path "target/%s"
  :profiles {:uberjar
             {:aot [clojure-prometheus-demo.core]
              :uberjar-name "clojure-prometheus-demo.jar"}})
