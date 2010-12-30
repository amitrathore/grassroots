(ns com.curry.grassroots.server.mysql-store
  (:require [com.curry.grassroots [config :as conf]])  
  (:require [clojureql [core :as cql]]))

(defn init []
  (def USERS (cql/table (conf/db) :users))
  (def GROUPS (cql/table (conf/db) :groups)))