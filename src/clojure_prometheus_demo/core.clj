(ns clojure-prometheus-demo.core
  (:require [clojure.tools.logging :as log]
            [ring.util.response :refer [response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [org.httpkit.server :refer [run-server]]
            [compojure.core :refer [defroutes context GET]]

            [iapetos.core :as prometheus]
            [iapetos.collector.jvm :as jvm]
            [iapetos.collector.ring :as ring]

            )
  (:gen-class))


(defonce registry
         (-> (prometheus/collector-registry)
             jvm/initialize
             ring/initialize))

(defn wrap-metrics [app]
  (ring/wrap-metrics app
                     registry
                     {:path "/metrics" :path-fn (fn [r] "/")}))


(defonce server (atom nil))

(defroutes app-routes
    (GET "/healthcheck" []
        (response "Es geht mir gut!")))

(def app
  (-> app-routes
      (wrap-defaults api-defaults)
      wrap-metrics))

(defn -main [& args]
  (log/info "Starting server")
  (reset! server (run-server app {:port 8080 })))
