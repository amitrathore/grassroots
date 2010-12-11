(ns com.curry.grassroots.server.core
  (:use net.cgrand.moustache)
  (:use com.curry.grassroots.server.users.core)
  (:use [ring.adapter.jetty :only [run-jetty]])
  (:use com.curry.grassroots.server.users.core))

(declare grassroots-app)

;(def server (doto (Thread. #(run-jetty #'grassroots-app {:port 8080})) .start))

(def grassroots-app (app
                     :post handle-post
                     :get handle-get))