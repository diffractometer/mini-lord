(ns mini-lord.system
 (:import [System/getenv])
 (:import [com.sendgrid SendGrid
                        SendGrid$Email
                        SendGrid$Response])
 (:require [ring.adapter.jetty :as jetty]
         [mini-lord.http.index :refer [handler]]
         [com.stuartsierra.component :as component]))

(defn email [from subject html to]
  (-> (SendGrid$Email.)
     (.setFrom from)
     (.setSubject subject)
     (.setHtml html)
     (.addTo to)))

(let [sendgrid (SendGrid.(System/getenv "SENDGRID_API_KEY"))]
  (.send sendgrid (email "hunter@hunterhusar.net" "subject" "html" "hhusar@openmarketshealth.com")))

(defrecord Server [handler server inst]
 component/Lifecycle
 (start [this]
   (if inst this
     (assoc this :inst 
        (jetty/run-jetty handler server))))
 (stop [this]
   (if inst (assoc this :inst (.stop inst)) this)))

(def s (atom (map->Server {:handler handler :server {:port 3000 :join? false}})))

(do @s)

;(swap! s component/start)
;(swap! s component/stop)
