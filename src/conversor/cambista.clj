(ns conversor.cambista
  (:require [clj-http.client :as http-client]
            [cheshire.core :refer [parse-string]]))

(def chave (System/getenv "CHAVE_API"))
;(def chave "d5221f050a6d4dc4b9f2")

(def api-url "https://free.currconv.com/api/v7/convert")

(defn parametrizar-moedas [de para]
  (str de "_" para))

(defn obter-cotacao [de para]
  (let [moedas (parametrizar-moedas de para)]
    (-> (:body (http-client/get api-url
                                {:query-params {"q" moedas
                                                "apiKey" chave}}))
        (parse-string)
        (get-in ["results" moedas "val"]))))
