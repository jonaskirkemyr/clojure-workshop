(defproject clojure-workshop-flatmap "0.1.0-SNAPSHOT"
  :description
  "Workshop material for learning clojure.
  lein uberjar in solution branch will create the tac program-"
  :url "https://gitlab.com/stoyle/clojure-workshop-flatmap"
  :license {:name "Creative Commons Attribution 4.0 International Public License"
            :url "http://creativecommons.org/licenses/by/4.0/"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.cli "0.3.1"]]
  :main clojure-workshop-flatmap.cat
  :aot :all
  :uberjar-name "cat.jar"
  :profiles {:dev {:dependencies [[midje "1.6.3"]]
                   :plugins [[lein-midje "3.1.1"]]}})
