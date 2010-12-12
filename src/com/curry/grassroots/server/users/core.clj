(ns com.curry.grassroots.server.users.core
  (:use org.curry.jabberwocky.connection
        org.curry.jabberwocky.server.registration)
  (:require [org.danlarkin [json :as json]])
  (:require [com.curry.grassroots [config :as conf]]))

(defn new-user [{:keys [params]}]
  (let [{:keys [username password]} params
        c (new-connection {:host (conf/xmpp-host)})]
    {:body (json/encode-to-str {:result (register-new-user username password c)})}))
