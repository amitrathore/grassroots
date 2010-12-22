(ns com.curry.grassroots.server.core
  (:use net.cgrand.moustache)
  (:use com.curry.grassroots.server.user)
  (:use [ring.adapter.jetty :only [run-jetty]])
  (:use ring.middleware.params
        ring.middleware.keyword-params)
  (:require [com.curry.grassroots.server.muc-config :as muc])
  (:require [com.curry.grassroots [config :as conf]]))

(declare grassroots-app)

(conf/load-config "/Users/amit/workspace/grassroots/config/config.clj" :development)

(def server (doto (Thread. #(run-jetty #'grassroots-app {:port 8080})) .start))

(def grassroots-app (app
                     wrap-params
                     wrap-keyword-params
                     :post (app ["user"] new-user)
                     :get (app ["room_config"] muc/render-room-config)))