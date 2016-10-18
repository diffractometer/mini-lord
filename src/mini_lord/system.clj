(ns mini-lord.system
  (:require [ring.adapter.jetty :as jetty]
            [com.stuartsierra.component :as component]))

(defn system
  "returns a new instance of the whole app"
  []
  (println "foo"))


(defn start
  "Performs side effects to initialize the system, acquire resources,
  and start it running. Returns an updated instance of the system."
  [system]
  (println "start"))

(defn stop
  "Performs side effects to shut down the system and release its
  resources. Returns an updated instance of the system."
  [system]
  (println "stop"))
