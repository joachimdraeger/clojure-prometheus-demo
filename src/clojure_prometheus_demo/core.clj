(ns clojure-prometheus-demo.core
  (:require [clojure.tools.logging :as log]
            [ring.util.response :refer [response]]
            [org.httpkit.server :refer [run-server]]
            [compojure.core :refer [defroutes context GET]])
  (:gen-class))

(defonce server (atom nil))

(defroutes app-routes
    (GET "/healthcheck" []
        (response "Es geht mir gut!")))

(defn -main [& args]
  (log/info "Starting server")
  (reset! server (run-server app-routes {:port 8080 })))
