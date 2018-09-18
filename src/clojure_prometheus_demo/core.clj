(ns clojure-prometheus-demo.core
  (:require
    [clojure.string :as s]
    [clojure.tools.logging :as log]
    [ring.util.response :refer [response header]]
    [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
    [org.httpkit.server :refer [run-server]]
    [compojure.core :refer [defroutes context GET]]
    [clj-http.client :as client]

    )
  (:gen-class))


(defn postcode->long-lat [postcode]
  (->
    (client/get (str "https://api.postcodes.io/postcodes/" postcode) {:as :json})
    :body
    :result
    (select-keys [:longitude :latitude])))

; https://data.police.uk/docs/method/crime-street/
(defn long-lat->crimes [{:keys [longitude latitude]}]
  (log/infof "Looking up crimes for long:%s lat:%s" longitude latitude)
    (client/get "https://data.police.uk/api/crimes-street/all-crime"
                {:query-params {"lng" longitude "lat" latitude}
                 :as :json}))

(defn crimes->text [postcode]
  (str
    (->> postcode postcode->long-lat long-lat->crimes
         :body (map :category) frequencies
         (sort-by last)
         (map #(s/join ": " %))
         (s/join "\n"))
    "\n"))

(defonce server (atom nil))

(defroutes app-routes
  (GET "/healthcheck" []
       (response "Es geht mir gut!"))
  (GET "/crime/:postcode" [postcode]
       (->
         postcode
         crimes->text
         response
         (header "Content-Type" "text/plain"))))

(def app
  (-> app-routes
      (wrap-defaults api-defaults)))

(defn -main [& args]
  (log/info "Starting server")
  (reset! server (run-server app {:port 8080 })))
