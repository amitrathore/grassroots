(ns com.curry.grassroots.server.muc-config
  (:require [net.cgrand.enlive-html :as html])
  (:require [clojureql [core :as cql]])
  (:require [org.danlarkin [json :as json]])  
  (:use com.curry.grassroots.server.web-utils
        com.curry.grassroots.server.mysql-store))

(html/deftemplate room-config
  (html/xml-resource "com/curry/grassroots/server/templates/muc_room_config.xml")
  [ctxt]
  [(html/attr? :id)] (html/set-attr :id (:id ctxt) :to (:to ctxt))
  [(html/attr-ends :var "roomname") :value] (html/content (:roomname ctxt))
  [(html/attr-ends :var "roomdesc") :value] (html/content (:roomdesc ctxt)))

(defn record-new-room-locally [username groupname]
  (try
    @(cql/conj! GROUPS {:username username :groupname groupname})
    true
    (catch Exception e
      false)))

(defn new-room-config [{params :params}]
  (let [group-created (record-new-room-locally (:username params) (:roomname params))
        config-xml (render (room-config params))]
    {:body (json/encode-to-str {:result group-created :config config-xml})}))