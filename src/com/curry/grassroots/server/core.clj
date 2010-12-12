(ns com.curry.grassroots.server.core
  (:use net.cgrand.moustache)
  (:use com.curry.grassroots.server.users.core)
  (:use [ring.adapter.jetty :only [run-jetty]])
  (:use com.curry.grassroots.server.users.core)
  (:use ring.middleware.params
        ring.middleware.keyword-params))

(declare grassroots-app)

;(def server (doto (Thread. #(run-jetty #'grassroots-app {:port 8080})) .start))

(def grassroots-app (app
                     wrap-params
                     wrap-keyword-params
                     :post (app ["user"] new-user)))