(ns com.curry.grassroots.server.users.core)

(defn new-user [req]
  (println "NEW:" req)
  "hello" (:username req))

(defn handle-post [req]
  {:body "from POST"})

(defn handle-get [req]
  (println "getting")
  {:body "from GET"})