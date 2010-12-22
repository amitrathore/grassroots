(ns com.curry.grassroots.server.user
  (:use org.curry.jabberwocky.connection
        org.curry.jabberwocky.server.registration)
  (:require [org.danlarkin [json :as json]])
  (:require [com.curry.grassroots [config :as conf]]))

(defn new-user [{:keys [params]}]
  (let [{:keys [jid password]} params
        _   (println "new-user:" params "jid, password:" jid password)
        c (new-connection {:host (conf/xmpp-host)})]
    {:body (json/encode-to-str {:result (register-new-user jid password c)})}))
