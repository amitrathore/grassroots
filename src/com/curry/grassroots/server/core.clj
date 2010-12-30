(ns com.curry.grassroots.server.core
  (:use net.cgrand.moustache)
  (:use [ring.adapter.jetty :only [run-jetty]])
  (:use ring.middleware.params
        ring.middleware.keyword-params)
  (:require [com.curry.grassroots.server.muc-config :as muc]
            [com.curry.grassroots.server.user :as user]
            [com.curry.grassroots.server.mysql-store :as store])
  (:require [com.curry.grassroots [config :as conf]]))

(declare grassroots-app)

(def config-file (str (.get (System/getenv) "GRASSROOTS_HOME") "/config/config.clj"))
(def env (.get (System/getenv) "GRASSROOTS_ENV"))

(conf/load-config config-file (keyword env))
(store/init)

(println "Loading config from:" config-file)
(println "Starting in" env "mode")

(def server (doto (Thread. #(run-jetty #'grassroots-app {:port 8080})) .start))

(def grassroots-app (app
                     wrap-params
                     wrap-keyword-params
                     ["user"] {:post user/new-user}
                     ["room_config"] {:get muc/new-room-config}
                     ["groups"] {:get user/list-groups}))