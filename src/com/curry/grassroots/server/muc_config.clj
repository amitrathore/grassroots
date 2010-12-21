(ns com.curry.grassroots.server.muc-config
  (:require [net.cgrand.enlive-html :as html])
  (:use com.curry.grassroots.server.web-utils))

(html/deftemplate room-config
  (html/xml-resource "com/curry/grassroots/server/templates/muc_room_config.xml")
  [ctxt]
  [(html/attr? :id)] (html/set-attr :id (:id ctxt) :to (:to ctxt))
  [(html/attr-ends :var "roomname") :value] (html/content (:roomname ctxt))
  [(html/attr-ends :var "roomdesc") :value] (html/content (:roomdesc ctxt))
  )

(defn render-room-config [{params :params}]
  (render-response (room-config params)))