CURRENT_DIR=$(cd `dirname $0`; pwd)
INFERRED_ROOT=$(cd `dirname $0`; cd .. ; pwd)

export GRASSROOTS_HOME=${GRASSROOTS_HOME:-"${CURRENT_DIR}"}

grassroots_jars="${GRASSROOTS_HOME}/lib/*:${GRASSROOTS_HOME}/lib/dev/*"

grassroots_clp="${GRASSROOTS_HOME}/src"
grassroots_clp="${grassroots_clp}:${GRASSROOTS_HOME}/lib/*"
grassroots_clp="${grassroots_clp}:${GRASSROOTS_HOME}/checkouts/ring/src"
grassroots_clp="${grassroots_clp}:${GRASSROOTS_HOME}/checkouts/moustache/src"
grassroots_clp="${grassroots_clp}:${GRASSROOTS_HOME}/checkouts/clojureql/src"
grassroots_clp="${grassroots_clp}:${GRASSROOTS_HOME}/checkouts/jabberwocky/src"
grassroots_clp="${grassroots_clp}:${GRASSROOTS_HOME}/checkouts/enlive/src"

# Whether to load the repl or script
if [ -z "$1" ]; then
	clj_class=clojure.lang.Repl
else
	clj_class=clojure.lang.Script
fi

clj_cp="."
[ -f /etc/clojure.conf ] && . /etc/clojure.conf
[ -f ~/.clojure.conf ]   && . ~/.clojure.conf
[ -f ~/.clojurerc ] && clj_rc=~/.clojurerc]
clj_cp="${clj_cp}:${grassroots_jars}:${grassroots_clp}:${clj_ext}/*"

if [ -n "${clj_lib}" ]; then
    export LD_LIBRARY_PATH=${clj_lib}:$LD_LIBRARY_PATH
fi

clj_mem="-Xms256m -Xmx512m"
# If not set in ~/.clojure.conf use these settings.
if [ -z "${clj_opts}" ]; then
    clj_opts="${clj_mem}"
fi

# It is possible to override the opts from the command line (or an env var).
if [ ! "${GRASSROOTS_OPTS}" = "" ]; then
    clj_opts="${GRASSROOTS_OPTS}"
fi

# You should really put YourKit settings in "extras" below.
#extras="-Dcom.sun.enterprise.web.connector.grizzly.keepAliveTimeoutInSeconds=600"
# extras="-agentlib:yjpagent"
#extras="-XX:+UseParallelGC -XX:-UseGCOverheadLimit"

cmd="java ${extras} ${clj_opts} -server -Dpid=$$ -agentlib:jdwp=transport=dt_socket,server=y,suspend=n -cp ${clj_cp}:${clj} ${clj_class} ${clj_rc} $1 $*"
echo "${cmd}"
${cmd}
