(ns com.curry.grassroots.server.db
  (require [clojureql.core :as core]))

(def DB {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :user "grassroots_user"
         :password "grassroots_pass"
         :subname "//lookingglass.local:3306/cql"})

