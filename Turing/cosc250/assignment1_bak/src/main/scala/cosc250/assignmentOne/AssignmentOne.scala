package cosc250.assignmentOne

import scala.annotation.tailrec

object AssignmentOne {

  /*
  
   CHALLENGE 1: This challenge uses the inbuilt List type in Scala

   */
  object Challenge1 {

    /** 
     A palindrome is a list that is the same forwards as backwards. eg, List(1, 2, 2, 1)
     Make this function return true if a list is a palindrome. Note, you ARE permitted to
     use the inbuilt reverse method on Lists.

      My function reverses the list into a new list, checks every element in both that
      they match at their respective indices.
      */
    def isPalindrome[T](list:List[T]):Boolean = {
      val list2: List[T] = list.reverse
      for(i <- list.indices) {
        if (list(i) != list2(i)) {
          return false
        }
      }
      return true
    }

    /** 
     Make this function return true if every entry is bigger than its position in the list.
     So, List(1, 2, 3) would return true but List(1, 2, 2) would return false because the
     last entry is at position 2 (remember, lists are zero-indexed) but its value (exactly 2) 
     is not bigger than 2.

     You will find list.zipWithIndex and list.forall useful

      Iterating every element and comparing with index, return false if index bigger than
      corresponding element int value
    */
    def entriesBiggerThanIndex(list:List[Int]):Boolean = {
      for(i <- list.indices) {
        if (list(i) <= i) {
          return false
        }
      }
      return true
    }
    

    /**
     A little tricker. Make this function return true if every second entry in a list
     forms a palindrome. eg, List(99, 1, 98, 2, 97, 2, 96, 2, 95, 1) is a palindrome because
     every second element forms List(1, 2, 2, 1) which is a palindrome

     You might find zipWithIndex and filter useful to extract every second element.
     You might also find it helpful to print out an intermediate result

      Zips list with index, filters out odd indices (1,3,5,etc) repeats similar checks as first
      palindrome funciton above.
    */
    def secondPalindrome[T](list:List[T]):Boolean = {
      val list2: List[T] = list.zipWithIndex
        .filter({i => (i._2) % 2 != 0 }).map{case (j, _) => j}
      val list3: List[T] = list.reverse
      for(i <- list2.indices) {
        if (list3(i) != list2(i)) {
          false
        }
      }
      true
    }
  }

  object Challenge2 {

    /**
      * Let's implement a Scrabble scorer.
      * It should take into account the letters in the word *and* the squares the word sits on.
      *
      * I've created a sealed trait to model the different kinds of square. A "sealed trait" means that every class or
      * object that implements that trait is defined in the same program file. Knowing that there aren't any other
      * potential Squares out there (eg, being added later by other programmers) means the compiler can do cleverer
      * exhaustiveness-checking for us.
      *
      */

    sealed trait Square
    case object OrdinarySquare extends Square
    case object DoubleLetterScore extends Square
    case object TripleLetterScore extends Square
    case object DoubleWordScore extends Square
    case object TripleWordScore extends Square

    /**
    * (You may assume all letters are uppercase)
    *
    * 1: A, E, I, O, U, L, N, S, T, R.
    * 2: D, G.
    * 3: B, C, M, P.
    * 4: F, H, V, W, Y.
    * 5: K
    * 8: J, X.
    * 10: Q, Z.
    *
    * You might find using "mystring".contains(char) useful to keep it short
      *
      *
      * ## Takes defines letters and their values in nested Array of Tuples, checks each array element
      * ## until it finds the correct Tuple, then returns the value in that Tuple. If char is not
      * ## alphabetical, it will return a score of 0.

    */
    def letterScore(char:Char):Int = {
      val letters = Array(("AEIOULNSTR",1),("DG", 2),("BCMP",3),("FHVWY",4),("K",5),("JX",8),("QZ",10))
      for(i <- letters.indices) {
        if (letters(i)._1.contains(char.toUpper))
          return letters(i)._2
      }
      0
    }

    /**
      * This should work out what this letter scores, given the square it's on.
      * Don't forget - DoubleWordScores etc affect the word as a whole, not individual letters
      *
      * ## Matches letter multipliers of relevance, or passing regular letter value
      */
    def letterAndSquareScore(char:Char, sq:Square):Int = {
      val letter = letterScore(char)
      val value:Int = sq match {
        case DoubleLetterScore => 2*letter
        case TripleLetterScore => 3*letter
        case _ => letter
      }
      value
    }

    /** 
     This should work out the total score for each letter, given the square it's on.
     At this stage, DoubleWordScores etc should not be taken into account. Only Double and TripleLetterScores.

     Hint: you can "zip" a word with a Seq to produce a Seq of (char, Square) tuples.
     eg, "ABC".zip(Seq(1, 2, 3)) produces Seq(('A', 1), ('B', 2), ('C', 3))

     Hint: If you produce a Seq[Int] you can call seq.sum on it.


      ## Sums the value of each individual letter using a for loop
     */
    def totalLetterScore(word:String, squares:Seq[Square]):Int = {
      var sum, char_value: Int = 0
      for (i <- squares.indices) {
        char_value = letterAndSquareScore(word.charAt(i), squares(i))
        sum += char_value
      }
      sum
    }

    /**
      Calculate the scrabble score for a word on a set of squares.
      Now we have to take DoubleWordScore and TripleWordScore squares into account.
      They combine multiplicatively. So, if there are two TripleWordScores and a DoubleWordScore
      squares, the multiplier will be 3 * 3 * 2 = 18


      ## Takes the summed value for each individual letter and applies a multiplier
         derived from the sum of Double/TripleWordScore squares times their corresponding
         value
      */
    def scrabbleScore(word:String, squares:Seq[Square]):Int = {
      var total: Int = totalLetterScore(word, squares)
      for (i <- squares.indices) {
        if (squares(i) == DoubleWordScore) {
          total *= 2
        } else if (squares(i) == TripleWordScore) {
          total *= 3
        }
      }
      total
    }

  }

  /**
   Challenge 3 is the "8 queens problem" -- how to put eight queens on a chessboard without any of them attacking
   each other. In chess, queens can move and attack horizontally, vertically, and diagonally
   and can move (and attack) an unlimited number of squares away.
   */
  object Challenge3 {

    /** 
     First, let me define "type alias". A Position is a tuple of two integers, eg (1, 2)
     This makes the word "Position" a synonym for the type (Int, Int)
     The rows and columns of a board will be numbered 0 to 7. 
     Column number first, ie (x, y). So, in a game of chess, white's king would start at (4, 0)
     */
    type Position = (Int, Int)

    /*
     A Candidate solution is a sequence of positions. Again, this just creates a synonym
     for us, not a new class.
     */
    type Candidate = Seq[Position]

    /**
      * Are two positions in the same row?
      * ## Returns true if the x in position 1 equals the x in position 2
      * ## Returns false if not.
      * */
    def sameRow(p1:Position, p2:Position):Boolean = p1._2 == p2._2

    /** Are two positions in the same column? */
    def sameCol(p1:Position, p2:Position):Boolean = p1._1 == p2._1

    /** 
     Are two positions on the same diagonal? 
     Remember, there are two diagonals to worry about. 

     Hint: Think about the difference in their coordinates.

      ## Finds the absolute difference between the x's and between the y's then subtracks. I.e. if position1
      ## is 3 rows above/below and 3 columns left/right of positoin2, they are diagonal
    */
    def sameDiagonal(p1:Position, p2:Position):Boolean = (p1._1-p2._1).abs == (p1._2-p2._2).abs

    /**
     Now let's define a function to test whether queens in two positions are attacking each other.
     Don't forget a queen cannot attack itself. ie, (4,4) is not attacking (4,4)

      ## If true for any of the previous 3 functions, then they can attack, unless p1 equals p2.
      ## other wise, returns false.
    */
    def attackingEachOther(p1:Position, p2:Position):Boolean = {
      if (p1 == p2) return false // False if on the same position (same queen)
      if (sameRow(p1,p2) || sameCol(p1,p2) || sameDiagonal(p1,p2)) return true
      false
    }

    /**
     Using your attackingEachOther method, write a function that looks through a sequence of positions and finds if
     there are any queens attacking each other

      ## Uses a interactive nested for loop to compare the board efficiently.
      ## Outer loop goes from 0 to sequence length minus 2 (stops 1 before the last queen)
      ## Inner loop only checks each queen below it's own index
      ## Using a interactive nested loop as opposed to independent loops, changes the efficiency from
      ## 25 comparisons to just 10 for example.
     */
    def seqContainsAttack(queens:Seq[Position]):Boolean = {
      for (i <- 0 to queens.length-2) {
        for (j <- i + 1 until queens.length) {
          if (attackingEachOther(queens(i), queens(j))) return true
        }
      }
      false
    }

    /**
     A solution is valid if it does not contain an attack and has eight queens.
     Write a function to check if a solution is valid

      ## Simply returns true if the number of queens is 8 and if the seqContainsAttack returns false.
      ## Other wise it returns false.
     */
    def isValid(queens:Seq[Position]):Boolean = queens.length == 8 && !seqContainsAttack(queens)

    /**
      To solve eight queens, all of the queens must be in different columns (and there must be one in each column)
      So, rather input the full positions for each queen, we can just take (in order) the row number for each column.
      ie, Seq(1, 8, 2, 7) would mean there's a queen at (1,1) another at (2, 7), another at (3, 2), another at (4, 7).
      
      Write a function that takes a sequence of row numbers, eg, Seq(1, 8, 2, 7), and produces a sequence of
      positions, eg Seq((0, 1), (1, 8), (2, 2), (3, 7))

      ## Creates new sequence from 0 to length of queens (index) then zips index with queens (so index first)
      */
    def expandShortHand(queens:Seq[Int]):Seq[Position] = {
      val newSeq:Seq[Int] = Seq(0).++(1 to queens.length)
      newSeq.zip(queens)
    }

    /**
     Write a function that takes in a Seq of candidate solutions, and returns a Seq
     containing only those solutions that turn out to be valid.
     Reminder: Seq[Candidate] is an alias for Seq[Seq[(Int,Int)]]

      ## Loops though every element in the sequence and checks for valid sequence using isValid()
      ## If sequence is valid, it is added to the end of the return sequence using appended() alias :+
     */
    def filterCandidates(candidates:Seq[Candidate]):Seq[Candidate] = {
      var validCandidates:Seq[Candidate] = Seq()
      for (i <- candidates.indices) {
        if (isValid(candidates(i))) validCandidates = validCandidates :+ candidates(i)
      }
      validCandidates
    }

    /**
      * Now we're going to use another trick to make the whole computation very small. As well as the queens all being
      * in different columns, they're also all in different rows. So every solution is going to be a permutation of
      * Seq(1, 2, 3, 4, 5, 6, 7, 8). But we're going to need to filter the permutations to only the ones that work.
      *
      * If your previous functions work, this should work.
      */
    def eightQueens:Seq[Candidate] = {
      val perms = Seq(0, 1, 2, 3, 4, 5, 6, 7).permutations.toSeq
      val candidates = perms.map(expandShortHand)
      filterCandidates(candidates)
    }


  }

  /**

   Challenge 4 introduces Maps.

   In this, we're introducing a new immutable data structure: Map[K, T]
   A "map" associates a key with a value.

   One of the ways you can make a map is from a List or Seq of tuples.
   val list = List(1 -> 'A', 2 -> 'B', 3 -> 'C')
   val map = list.toMap

   or you can create one directly
   val map = Map(1 -> 'A', 2 -> 'B', 3 -> 'C')

   you can also get a Seq or List of tuples from a map by calling toSeq
   val seq:Seq(Int, Char) = map.toSeq

   You can get a new map with altered values by calling updated
   val withD = map.updated(4, 'D')
   val replaced = withD.updated(4, 'd')

   You can get a value from a map (given its key) by calling apply
   val d = replaced.apply(4)
   or, as by convention you don't have to say the word "apply"
   val d = replaced(4)

   */
  object Challenge4 {

    // We're going to need the alphabet for this, because we're going to produce a cypher
    val alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    /**
     Given a map, produce the inverse map. 
     So if the map is Map(1 -> 'A', 2 -> 'B', 3 -> 'C') produce Map('A' -> 1, 'B' -> 2, 'C' -> 3)

     Don't worry about duplicate entries.

      ## Takes the map then makes a new map by applying a function to all elements
     */
    def inverse[K, V](map:Map[K,V]):Map[V, K] = {
      map.map(_.swap)
    }

    /**
     A vignere table is a sequence of strings. Each string contains the alphabet shifted by
     1 more character. 
     
     So, 
     vignere(0) is ABCDEFGHIJKLMNOPQRSTUVWXYZ
     vignere(1) is BCDEFGHIJKLMNOPQRSTUVWXYZA
     vignere(2) is CDEFGHIJKLMNOPQRSTUVWXYZAB
     etc.

     You should produce these programmatically rather than hardcoding all 26 values.

     Hint: slice(from, to) produces a substring. 
     eg, alphabet.slice(13, 26) is "NOPQRSTUVWXYZ"

     Hint: ++ can be used to concatenate strings.

     Produce a vignere table as a Map[Int,String]

      ## Iterates 25 times each time adding to a sequence with the alphabet shifted using drop and dropRight
     */
    def vignereTable:Map[Int, String] = {
      val alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
      var seq: Seq[(Int, String)] = Seq()
      for (i:Int <- 0 to 25) seq = seq :+ (i, alpha.drop(i) + alpha.dropRight(26-i))
      seq.toMap
    }

    /**
     A vignere cypher relies on a key string. This is converted into a sequence of integers
     simply by taking the index of each letter in the alphabet.

     eg, "SCALA" -> Seq(18, 2, 0, 11, 0)

     Hint: String has an indexOf method that will tell you at which index a character first occurs
     eg, "SCALA".indexOf('S') is 0

     Hint: String has a toSeq method that will convert it into a Seq[Char]

      ## Converts string characters into chars then lowercases and fetches ASCII value (toInt) minus
      ## the offset of 97 characters in front of lowercase alphabet
     */
    def letterToNum(key:String):Seq[Int] = {
      var seq: Seq[Int] = Seq()
      for (i:Int <- 0 until key.length) seq = seq :+ (key.charAt(i).toLower.toInt-97)
      seq
    }

    /**
     Suppose we have plaintext to encrypt that says "GREAT" and a key "SCALA"

     A Vignere cypher first turns the key into a sequence of indices (18, 2, 0, 11, 0)
     It then uses this to get the sequence of strings from the vignere table (vignere(18), vignere(2), vignere(0), vignere(11), vignere(0))

     Each letter of the key is then turned into its index in the alphabet
     GREAT -> (6, 17, 4, 0, 19)
     and then those numbers are used to look up a letter from the corresponding vignere string
     GREAT -> (vignere(18)(6), vignere(2)(17), vignere(0)(4), vignere(11)(0), vignere(0)(19))
     producing YTELT

     Write a function that will encode a string using a Vignere cypher and a key.
     If the key is shorter than the plaintext, it should repeat the key until it is long enough.

     Hint: "ABC" * 3 produces "ABCABCABC"

     Hint: "ABC".zip("abcd") produces Seq(('A', 'a'), ('B', 'b'), ('C', 'c'))
           ie, zipping two strings together will produce a sequence only as long as the shorter string
           the same works for zipping Seqs together

     Hint: Seq('A', 'B', 'C').mkString will produce "ABC"

     Hint: You can try a Vignere cypher out online at https://cryptii.com/pipes/vigenere-cipher
           or read up on it on Wikipedia: https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher

      ## Takes key adjusts length by repetition of string according to length of plaintext. This is required
      ## as some keys may not be the appropriate length when passed.
      ## Performs appropriate fetching and index gathering as specified above.
     */
    def encode(plaintext:String, key:String):String = {
      val seqAlphaNum: Seq[Int] = letterToNum(key*((plaintext.length/key.length)+1))
      val textAlphaNum: Seq[Int] = letterToNum(plaintext)
      val vTable: Map[Int, String] = vignereTable
      var keyAlpha: Seq[String] = Seq()
      var returnString: Seq[Char] = Seq()

      for (i <- seqAlphaNum.indices) {
        keyAlpha = keyAlpha :+ vTable(seqAlphaNum(i))
      }
      for (i <- textAlphaNum.indices) {
        if (i < seqAlphaNum.length) {
          returnString = returnString :+ keyAlpha(i).charAt(textAlphaNum(i))
        }
      }
      returnString.mkString
    }

    /**
     To decode, we again need the sequence of Vignere strings that corresponds to the key.

     But this time, for each letter in the cyphertext, we look up its index in the vignere string
     and that gives us a character number to look up in the alphabet.
     */
    def decode(cyphertext:String, key:String):String = {
      val seqAlphaNum: Seq[Int] = letterToNum(key*((cyphertext.length/key.length)-1))
      val cypherAlphaNum: Seq[Int] = letterToNum(cyphertext)
      val vTable: Map[Int, String] = vignereTable
      var keyAlpha: Seq[String] = Seq()
      var returnString: Seq[Char] = Seq()

      for (i <- seqAlphaNum.indices) {
        keyAlpha = keyAlpha :+ vTable(seqAlphaNum(i))
      }
      for (i <- keyAlpha.indices) {
        returnString = returnString :+ keyAlpha(i).charAt(cypherAlphaNum(i))
      }
      println(returnString.mkString)
      returnString.mkString
    }

  }

  /**
   
    Let's finish with John Conway most famous creation: Conway's Game of Life.
    https://en.wikipedia.org/wiki/Conway's_Game_of_Life
    
    Suppose we have a grid of squares, say 20 by 20
    And a square can be filled in (alive) or not filled in (dead).
    
    And at each "time step", we generate a new grid using the following rules:
    Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
    Any live cell with two or three live neighbours lives on to the next generation.
    Any live cell with more than three live neighbours dies, as if by overpopulation.
    Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    (Each cell has eight neighbours)

   */
  object Challenge5 {

    /**
      We're going to define the game using an immutable Map.
      Here, I've used Scala's "type alias" syntax to say that a ConwayState is a map from a tuple of ints to a boolean
      
      The tuple is going to contain (x, y) coordinates, and the Boolean is going to contain the values.
      */
    type Position = (Int, Int)
    type ConwayState = Map[Position, Boolean]

    /**
      If an element in the map is missing, assume it to be false (dead). You can use getOrElse for this. This also
      has the advantage that we *can* ask about negative indices -- getOrElse((-1, -1), false) will be false

      Hint: Map has a getOrElse method

      ## Simply checks if position exists in Conway and if it's true, else returns false.
      */
    def isAlive(p:Position, s:ConwayState):Boolean = {
      s.getOrElse(p, false)
    }


    /**
      * Blinkers have a habit of toggling -- to help you test your code, I've included their definition.
      * If you have a blinker1, and you move forward one tick in the game state, you should get blinker2.
      * See the wikipedia page for more on this.
      */
    val blinker1:ConwayState = Map(
      (2, 1) -> true, (2, 2) -> true, (2, 3) -> true
    )
    val blinker2:ConwayState = Map(
      (1, 2) -> true, (2, 2) -> true, (3, 2) -> true
    )

    /**
      First, define a function that given a tuple and a ConwayState will count the number of live neighbours

      ## Checks every 8 possible neighbour positions and counts how many are alive, using the isAlive function.
      ## Returns count.
      */
    def liveNeighbours(pos:Position, state:ConwayState):Int = {
      val nPosSeq: Seq[(Int,Int)] = Seq((-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1))
      var x, y, count: Int = 0

      for (i <- nPosSeq.indices) {
        x = pos._1+nPosSeq(i)._1
        y = pos._2+nPosSeq(i)._2
        if (isAlive((x,y), state)) count += 1
      }
      count
    }

    /**
      * Next, define a function that determines whether a position should be alive or dead
      *
      * ## Fetches how many neighbours alive, and if position is alive. Returns boolean based on this information
      * ## and the rules defined above.
      */
    def aliveNextTurn(pos:(Int, Int), state:ConwayState):Boolean = {
      val nAlive:Int = liveNeighbours(pos, state)
      val alive:Boolean = isAlive(pos, state)

      if (nAlive < 2 || nAlive > 3) {
        false
      } else if (!alive && nAlive == 3 || alive) {
        true
      } else {
        false
      }
    }

    /**
      * Next, define a function that will compute the next state of the game of life, for a given maximum X and Y
      *
      * ## I was not able to figure this one out. I got stuck trying to fill in the blank spaces not
      * ## passed through the current ConwayState.
      */
    def nextConwayState(state:ConwayState, maxSize:(Int, Int) = (20, 20)):ConwayState = {
      ???
      var newState:Seq[(Position, Boolean)] = Seq().concat(state)
      var returnConway:Seq[(Position, Boolean)] = Seq()
      for (x <- 1 to 20) {
        for (y <- 1 to 20) {
//          if (newState(x*y)._1 == null) {
//            newState = newState.concat(((x,y),false))
//          }
          newState = newState :+ ((x,y), false)
          returnConway.updated(x*y,aliveNextTurn((x,y),state))
        }
      }
      returnConway.toMap
    }


  }


  /**
    Challenge 6 

    We have a little card game that uses an unusual deck.
    There are "Add" cards from 1 to 9, a Double card, a Negative card, and a Zero card.

    The way the game is played is you are dealt eight random cards, and you have to put them
    in the order that will give you the highest score.

    The way the score is calculated is this:
    You start with a score of zero and look at the first card.

    If the card is ZeroScore, you reset your running total to zero
    If the card is Add(number), you add the number to your running total
    If the card is NegativeScore, you mutliply the running total by -1.
      (NB: We only multiple the running total by -1. It does not affect how later cards are processed.
       So, Seq(Add(2), NegativeScore) would produce -2, but
           Seq(Add(2), NegativeScore, Add(3)) would produce 1 (the result of adding 3 to -2)
    If the card is DoubleScore, you double the running total  
      (Again, we only double the running total. It does not affect how later cards are processed.
       This isn't Scrabble.)

    You then move on to the next card and do the same, and so on until you have processed
    all the cards in your hand.

    There are NEVER duplicate cards in a hand.
    
   */
  object Challenge6 {

    sealed trait Card
    case object DoubleScore extends Card
    case object NegativeScore extends Card
    case class Add(n:Int) extends Card
    case object ZeroScore extends Card

    val allCards = List(DoubleScore, NegativeScore, ZeroScore) ++ (1 to 9).toList.map(Add)
    
    val rng = new scala.util.Random(99)
    def deal:Seq[Card] = rng.shuffle(allCards).take(8)
    
    /**
     Use foldLeft to work out the score for a hand
     */
    def processHand(cards:Seq[Card]):Int = {
      var score: Int = 0
      println(deal)
      for (i <- cards.indices) {
        cards(i) match {
          case DoubleScore => score = score + score
          case NegativeScore => score = score * -1
          case Add(n) => score = score + n
          case ZeroScore => score = 0
        }
      }
      score
    }
// This was my attempt at using foldLeft, i couldn't make it work.
//      score = deal.foldLeft(0) {
//        case (score, DoubleScore) => score+score
//        case (score, NegativeScore) => score * -1
//        case (score, Add(n)) => score + n
//        case (score, ZeroScore) => 0
//      }

    /**
     We're going to introduce an extra rule.

     Once you've placed your hand on the table, your opponent must remove
     one card from your hand. The other cards remain in the order they were placed,
     but that card is removed.

     Write a function that will remove one card from a hand, leaving the rest in order.

     Hint: There are no duplicate cards in a hand. You might find the filter method
     on Seq useful.

      ## Removes a given card from a given hand.
     */
    def removeCard(hand:Seq[Card], card:Card):Seq[Card] =  hand.filter(!_.equals(card))

    /**
     Write a function that calculates the change in score that is made to a hand by 
     removing a particular card. Eg, if it reduces it by 2 return -2

      ## Subtracts the score of the hand after the card is removed with the score of the hand before the card
      ## is removed.
     */
    def diffFromRemoving(hand:Seq[Card], card:Card):Int = processHand(removeCard(hand, card)) - processHand(hand)

    /**
     Write a function that will work out the best card for them to remove 
     (the one that reduces your score the most). 

     Hint: A hand is a short sequence. Try them all and pick the best one.

     Hint: you might find the minBy function on Seq[Int] useful

      ## This function compares the score of the given hand when each card is removed.
      ## Which ever score is the lowest, this is the best card to the remove from your opponent.
     */
    def bestCardToRemove(hand:Seq[Card]):Card = {
      var bestCard,lowestScore:Int = 0
      for(i <- hand.indices) {
        if (diffFromRemoving(hand, hand(i)) < lowestScore) {
          bestCard = i
          lowestScore = diffFromRemoving(hand, hand(i))
        }
      }
      hand(bestCard)
    }

    /**
     So now we're going to do something tricky.

     Given that our opponent will remove a card from our deck, what is the best order
     to place our cards down in?

     Let us assume that they will always pick the best card to remove from our hand, and
     work out what permutation of our cards will give us the maximum score after they've 
     removed it

     There is a "permutations" function that will give us all the permutations of our deck,
     so we can do this using maxBy

     NOTE: The tests for this function use your other functions. So if processHand or
     bestCardToRemove are wrong, this might appear to pass by mistake.

     Hint: There might be more than one "best order" that would result in the same score.
     In which case any of those will do.

      ## Iterates over every permutation of our hand, then using our previous functions this will find
      ## one of the combos with the best possible score.
     */
    def bestOrder(hand:Seq[Card]):Seq[Card] = {
      var bestHand,highestScore:Int = 0
      val combos:Seq[Seq[Card]] = hand.permutations.toSeq
      for (i <- combos.indices) {
        if (processHand(combos(i)) > highestScore) {
          highestScore = processHand(combos(i))
          bestHand = i
        }
      }
      // Tried a one-liner but didn't work for me.
      //    hand.permutations.maxBy(processHand(removeCard(_, bestCardToRemove(_)))
      combos(bestHand)
    }


  }


}
