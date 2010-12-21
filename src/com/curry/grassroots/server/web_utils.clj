(ns com.curry.grassroots.server.web-utils
  (:use [ring.util.response :only [response file-response]]))

(defn render [t]
  (apply str t))

(def render-response
     (comp response render))