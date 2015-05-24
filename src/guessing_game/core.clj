(ns guessing-game.core
  (:gen-class))

(defn prompt 
  "Asks user a question and gets input"
  [prompt-line]
  (do (print prompt-line " ")
      (flush)
      (read-line))
)

(defn parse-int
  "Tries to read a number from a string. Returns nil if fails"
  [num]
  (try 
    (Integer/parseInt num)
    (catch Exception e nil))
)

(defn rand-num
  "Randomly generates a number between 1 and 100."
  []
  (inc (rand-int 100))
)

(defn -main
  "A guessing game where the user tries to guess a number between
   1 and 100 that the computer randomly picks."
  [& args]
  (loop [guess (prompt "Enter a number between 1 and 100:")
         ans (rand-num)]
    (if-let [n (parse-int guess)]
      (if (= n ans)
        (let [again? (prompt "You got it! Would you like to play again (y/n)?")]
          (if (= again? "y")
            (recur (prompt "Enter a number between 1 and 100:") (rand-num))
            (println "Goodbye!")))
        (recur (prompt (if (> n ans)
                         "Your guess is too high! Enter another guess:"
                         "Your guess is too low! Enter another guess:"))
                       ans))))
)
