(ns clojure-workshop-flatmap.cat-test
  (:use midje.sweet
        clojure-workshop-flatmap.cat)
  (:require [clojure.string :as str]))


(fact "read-file should actually read file contets"
      (read-file "test-resources/file1.txt") => "en\nto\ntre\n")


(fact "cat should return its input and state in a vector"
      (cat {} "1\n2") => [{} "1\n2"]
      (cat {:state 1} "1\n2") => [{:state 1} "1\n2"])

(fact "cat-files should be able to handle many files"
      (cat-files {} ["test-resources/file1.txt" "test-resources/file2.txt"])
        => "en\nto\ntre\n\nfire\nfem\nseks\n")


(fact "Should number all lines"
      (number-lines {} "text") =>   (contains      "     1 text")
      (number-lines {} "en\nto") => (contains (str "     1 en\n"
                                                   "     2 to"))
      (number-lines {} "en\n\nto") => (contains (str "     1 en\n"
                                                     "     2 \n"
                                                     "     3 to")))


(fact "Should number all lines and keep record of current line numbering"
      (number-lines {} "text") =>   [{:line-cnt 2}          "     1 text"]
      (number-lines {:line-cnt 2} "text") => [{:line-cnt 3} "     2 text"]
      (number-lines {} "en\nto") => [{:line-cnt 3} (str     "     1 en\n"
                                                            "     2 to")])


(fact "Should number non-empty-lines"
      (number-non-blank-lines {} "text") =>   (contains        "     1 text")
      (number-non-blank-lines {} "en\nto") => (contains   (str "     1 en\n"
                                                               "     2 to"))
      (number-non-blank-lines {} "en\n\nto") => (contains (str "     1 en\n"
                                                               "\n"
                                                               "     2 to")))


(fact "Should number all lines and keep record of current line numbering"
      (number-non-blank-lines {} "text") =>   [{:line-cnt 2}                 "     1 text"]
      (number-non-blank-lines {:line-cnt 2} "text") => [{:line-cnt 3}        "     2 text"]
      (number-non-blank-lines {} "en\nto") => [{:line-cnt 3} (str            "     1 en\n"
                                                                             "     2 to")]
      (number-non-blank-lines {:line-cnt 2} "en\n\nto") => [{:line-cnt 4} (str "     2 en\n"
                                                                             "\n"
                                                                             "     3 to")])