(ns com.curry.grassroots.server.core
  (:use net.cgrand.moustache)
  (:use [ring.adapter.jetty :only [run-jetty]])
  (:use ring.middleware.params
        ring.middleware.keyword-params)
  (:require [com.curry.grassroots.server.muc-config :as muc]
            [com.curry.grassroots.server.user :as user])
  (:require [com.curry.grassroots [config :as conf]]))

(declare grassroots-app)

(conf/load-config "/Users/amit/workspace/grassroots/config/config.clj" :development)

(def server (doto (Thread. #(run-jetty #'grassroots-app {:port 8080})) .start))

(def grassroots-app (app
                     wrap-params
                     wrap-keyword-params
                     ["user"] {:post user/new-user}
                     ["room_config"] {:get muc/new-room-config}
                     ["groups"] {:get user/list-groups}))