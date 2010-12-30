(ns com.curry.grassroots.config)

(declare CONFIG-MAP ENV)

(defn load-config [config-file env]
  (def ENV env)
  (def CONFIG-MAP (load-string (slurp config-file))))

(defn xmpp-host []
  (get-in CONFIG-MAP [ENV :host]))

(defn db []
  (get-in CONFIG-MAP [ENV :db]))