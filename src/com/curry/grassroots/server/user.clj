(ns com.curry.grassroots.server.user
  (:use org.curry.jabberwocky.connection
        org.curry.jabberwocky.server.registration
        com.curry.grassroots.server.mysql-store)
  (:require [clojureql [core :as cql]])
  (:require [org.danlarkin [json :as json]])
  (:require [com.curry.grassroots [config :as conf]]))

(defn register-user-locally [jid password]
  (let [user {:email jid :encrypted_password password}]
    @(cql/conj! USERS user)))

(defn list-groups [{:keys [params]}]
  (let [groups @(cql/select GROUPS (cql/where (= :username (:username params))))
        names (doall (map :groupname groups))]
    {:body (json/encode-to-str names)}))

(defn new-user [{:keys [params]}]
  (let [{:keys [jid password]} params
        _   (println "new-user:" params "jid, password:" jid password)
        c (new-connection {:host (conf/xmpp-host)})
        result (register-new-user jid password c)]
    (if result
      (register-user-locally jid password))
    {:body (json/encode-to-str {:result result})}))
