{:development {
               :host "lookingglass.local"
               :db {
                    :classname "com.mysql.jdbc.Driver"
                    :subprotocol "mysql"
                    :user "grassroots_user"
                    :password "password"
                    :subname "//lookingglass.local:3306/grassroots_dev"}}
 :test {
               :host "lookingglass.local"
               :db {
                    :classname "com.mysql.jdbc.Driver"
                    :subprotocol "mysql"
                    :user "grassroots_user"
                    :password "password"
                    :subname "//localhost:3306/grassroots_prod"}}
 :production {
               :host "chatter.currylogic.com"
               :db {
                    :classname "com.mysql.jdbc.Driver"
                    :subprotocol "mysql"
                    :user "grassroots_user"
                    :password "password"
                    :subname "//localhost:3306/grassroots_prod"}}}