(ns mini-lord.system
  (:require [ring.adapter.jetty :as jetty]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(defn system
  "returns a new instance of the whole app"
  []
  {:handler handler
   :server {:port 3000}})

(defn start
  "Performs side effects to initialize the system, acquire resources,
  and start it running. Returns an updated instance of the system."
  [system]
  (jetty/run-jetty handler (:server system)))

(defn stop
  "Performs side effects to shut down the system and release its
  resources. Returns an updated instance of the system."
  [system]
  (println "stop"))

