(ns com.curry.grassroots.server.mysql-store
  (:use com.curry.grassroots.server.db)
  (:require [clojureql [core :as cql]]))

(def USERS (cql/table DB :users))
(def GROUPS (cql/table DB :groups))