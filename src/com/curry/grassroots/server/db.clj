(ns com.curry.grassroots.server.db
  (require [clojureql.core :as core]))

(def DB {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :user "grassroots_user"
         :password "password"
         :subname "//localhost:3306/grassroots_prod"})

