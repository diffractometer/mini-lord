(ns mini-lord.system
  (:require [ring.adapter.jetty :as jetty]
            [com.stuartsierra.component :as component]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(defrecord Server [handler server inst]
  component/Lifecycle
  (start [this]
    (if inst this
      (assoc this :inst (jetty/run-jetty handler server))))
  (stop [this]
    (if inst (assoc this :inst (.stop inst)) this)))

(def s (atom (map->Server {:handler handler :server {:port 3000 :join? false}})))

(do @s)

(swap! s component/start)
(swap! s component/stop)
