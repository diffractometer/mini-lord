(ns mini-lord.emailer
 (:import [System/getenv])
 (:import [com.sendgrid SendGrid
                        SendGrid$Email
                        SendGrid$Response]))

(defn structure-email [from subject html]
  (-> (SendGrid$Email.)
     (.setFrom from)
     (.setSubject subject)
     (.setHtml html)))

(defn fire-email [api_key email]
  (.send (SendGrid. api_key) email))

(defn send-email
  [{api_key :api_key to :to from :from subject :subject html :html from-name :from-name}]
  (let [email (-> (structure-email from subject html)
                  (.addTo to)
                  (.setFromName from-name))
        response (fire-email api_key email)]
    (.getMessage response)))

(send-email {:api_key (System/getenv "SENDGRID_API_KEY") :to "hhusar@openmarketshealth.com" :from "hunter@hunterhusar.net" :subject "TEST EMAIL" :html "body" :from-name "OpenMarkets Health"})
