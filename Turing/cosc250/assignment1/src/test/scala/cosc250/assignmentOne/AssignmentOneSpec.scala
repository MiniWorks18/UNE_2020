package cosc250.assignmentOne

import org.scalatest._

import scala.collection.immutable.TreeSet


/**
  * This is a specification file for ScalaTest. It's a set of unit tests written in a way that's designed to be
  * read easily.
  */
class AssignmentOneSpec extends FlatSpec with Matchers {

  import AssignmentOne._

  "Challenge1" should "determine palindromes correctly" in {
    Challenge1.isPalindrome(List(1, 2, 2, 1)) should be (true)
    Challenge1.isPalindrome(List(1, 2, 3, 2, 1)) should be (true)
    Challenge1.isPalindrome(List()) should be (true)
    Challenge1.isPalindrome(List(1)) should be (true)
    Challenge1.isPalindrome(List(1, 1)) should be (true)
    Challenge1.isPalindrome(List(1, 2)) should be (false)
    Challenge1.isPalindrome(List(1, 2, 1, 1)) should be (false)
    Challenge1.isPalindrome((1 to 50).toList ++ (1 to 50).reverse.toList) should be (true)
  }

  it should "determine if list entries are bigger than their index" in {
    Challenge1.entriesBiggerThanIndex(List(1, 2, 3)) should be (true)
    Challenge1.entriesBiggerThanIndex(List()) should be (true)
    Challenge1.entriesBiggerThanIndex(List(0, 1, 2)) should be (false)
    Challenge1.entriesBiggerThanIndex(List(0, 0, 3)) should be (false)
    Challenge1.entriesBiggerThanIndex((1 to 50).toList ++ (1 to 50).reverse.toList) should be (false)
  }

  it should "determine if every second entry in a list forms a palindrome" in {
    Challenge1.secondPalindrome(List()) should be (true)
    Challenge1.secondPalindrome(List(1)) should be (true)
    Challenge1.secondPalindrome(List(1, 2)) should be (true)
    Challenge1.secondPalindrome(List(1, 1, 99, 2, -99, 2, 103, 1)) should be (true)
    Challenge1.secondPalindrome(List(1, 1, 99, 2, -99, 2, 103, 1, 567)) should be (true)
    Challenge1.secondPalindrome(List(1, 1, 99, 2, 8, 3, -99, 2, 103, 1, 567)) should be (true)
  }


  "Scrabble scoring" should "score individual letters correctly" in { 
    import Challenge2._

    letterScore('X') should be(8) 
    letterScore('Q') should be(10)
    letterScore('R') should be(1)
    letterScore('S') should be(1)
  }

  it should "score letters on double and triple letter scores correctly" in {
    import Challenge2._

    letterAndSquareScore('X', DoubleLetterScore) should be (16)
    letterAndSquareScore('Q', TripleLetterScore) should be (30)
    letterAndSquareScore('R', DoubleLetterScore) should be (2)
    letterAndSquareScore('S', OrdinarySquare) should be (1)
    letterAndSquareScore('S', DoubleWordScore) should be (1)
    letterAndSquareScore('S', TripleWordScore) should be (1)
  }
  
  it should "work out the letter scores for words correctly" in {
    import Challenge2._
    totalLetterScore("XYLOPHONE", "XYLOPHONE".map(_ => OrdinarySquare)) should be (24)
    totalLetterScore("HERRING", "HERRING".map(_ => OrdinarySquare)) should be (11)
    totalLetterScore("HERRING", "HERRING".map(_ => DoubleWordScore)) should be (11)
    totalLetterScore("HERRING", Seq(OrdinarySquare, OrdinarySquare, DoubleLetterScore, OrdinarySquare, OrdinarySquare, OrdinarySquare, DoubleLetterScore)) should be (14)
    totalLetterScore("HERRING", Seq(OrdinarySquare, OrdinarySquare, DoubleLetterScore, OrdinarySquare, OrdinarySquare, OrdinarySquare, TripleLetterScore)) should be (16)
  }

  it should "score words, including double and triple word score squares, corretly" in {
    import Challenge2._

    scrabbleScore("HERRING", Seq(DoubleWordScore, OrdinarySquare, DoubleLetterScore, OrdinarySquare, OrdinarySquare, OrdinarySquare, TripleLetterScore)) should be (32)
    scrabbleScore("HERRING", Seq(DoubleWordScore, TripleWordScore, DoubleLetterScore, OrdinarySquare, OrdinarySquare, OrdinarySquare, TripleLetterScore)) should be (96)
  }

  "Eight queens" should "understand rows" in {
    import Challenge3._

    sameRow((0, 1), (7, 1)) should be (true)
    sameRow((0, 2), (1, 2)) should be (true)
    sameRow((0, 3), (0, 1)) should be (false)
    sameRow((0, 1), (4, 5)) should be (false)
  }

  it should "understand columns" in {
    import Challenge3._

    sameCol((0, 1), (0, 6)) should be (true)
    sameCol((2, 2), (2, 1)) should be (true)
    sameCol((4, 3), (3, 3)) should be (false)
    sameCol((0, 1), (4, 5)) should be (false)
  }

  it should "understand diagonals" in {
    import Challenge3._

    sameDiagonal((0, 1), (1, 2)) should be (true)
    sameDiagonal((0, 1), (1, 0)) should be (true)
    sameDiagonal((0, 1), (5, 6)) should be (true)
    sameDiagonal((0, 1), (6, 5)) should be (false)
  }

  it should "understand attacks" in {
    import Challenge3._

    attackingEachOther((7, 1), (7, 1)) should be (false)
    attackingEachOther((7, 1), (5, 2)) should be (false)
    attackingEachOther((7, 1), (2, 2)) should be (false)
    attackingEachOther((7, 1), (1, 1)) should be (true)
    attackingEachOther((7, 7), (1, 1)) should be (true)
  }

  it should "find attacks in a Sequence" in {
    import Challenge3._

    seqContainsAttack(Seq(0 -> 1, 1 -> 1)) should be (true)
    seqContainsAttack(Seq(0 -> 1, 1 -> 3, 2 -> 2, 3 -> 4)) should be (true)
    seqContainsAttack(Seq(0 -> 1, 1 -> 3, 2 -> 2)) should be (true)
    seqContainsAttack(Seq.empty) should be (false)
    seqContainsAttack(Seq(0 -> 1)) should be (false)
    seqContainsAttack(Seq(0 -> 1, 1 -> 3, 2 -> 5)) should be (false)
  }

  it should "understand valid solutions" in {
    import Challenge3._

    isValid(Seq((0,0), (1,4), (2,7), (3,5), (4,2), (5,6), (6,1), (7,3))) should be (true)
    isValid(Seq((0,0), (1,5), (2,7), (3,2), (4,6), (5,3), (6,1), (7,4))) should be (true)
    isValid(Seq(0 -> 7, 2 -> 0, 3 -> 2, 4 -> 5, 5 -> 1, 6 -> 6, 7 -> 5)) should be (false)
    isValid(Seq(0 -> 7, 1 -> 4, 2 -> 2, 3 -> 0, 4 -> 5, 5 -> 1, 6 -> 6, 7 -> 5)) should be (false)
  }

  it should "expand shorthand" in {
    import Challenge3._

    expandShortHand(Seq()) should be (Seq())
    expandShortHand(Seq(1, 2, 3)) should be (Seq((0, 1), (1, 2), (2, 3)))
  }

  it should "find 92 solutions" in {
    import Challenge3._

    eightQueens.count(_ => true) should be (92)
  }

  "Maps and Crypto" should "produce the inverse of a Map" in {
    import Challenge4._

    inverse(Map.empty[Int,Char]) should be (Map.empty)
    inverse(Map(1 -> 'A')) should be (Map('A' -> 1))
    inverse(Map(1 -> 'A', 2 -> 'B')) should be (Map('A' -> 1, 'B' -> 2))
  }

  it should "produce a Vignere table" in {
    import Challenge4._

    vignereTable(1).mkString should be ("BCDEFGHIJKLMNOPQRSTUVWXYZA")
    vignereTable(2).mkString should be ("CDEFGHIJKLMNOPQRSTUVWXYZAB")
    vignereTable(13).mkString should be ("NOPQRSTUVWXYZABCDEFGHIJKLM")
    vignereTable.toSeq.length should be (26)
  }

  it should "convert a key string into a sequence of indices in the alphabet" in {
    import Challenge4._ 

    letterToNum("SCALA") should be (Seq(18, 2, 0, 11, 0))
    letterToNum("FUNCTIONALPROGRAMMING") should be (Seq(5, 20, 13, 2, 19, 8, 14, 13, 0, 11, 15, 17, 14, 6, 17, 0, 12, 12, 8, 13, 6))
    letterToNum("") should be (Seq.empty)
  }

  it should "encode plaintext correctly" in {
    import Challenge4._

    encode("GREAT", "SCALA") should be ("YTELT")
    encode("FUNCTIONALPROGRAMMING", "SCALA") should be ("XWNNTAQNLLHTORRSOMTNY")
  }

  it should "decode plaintext correctly" in {
    import Challenge4._

    decode("YTELT", "SCALA") should be ("GREAT")
    decode("XWNNTAQNLLHTORRSOMTNY", "SCALA") should be ("FUNCTIONALPROGRAMMING")
  }

  "Game of Life" should "determine if a cell is alive now correctly" in {
    import Challenge5._

    isAlive((2, 1), blinker1) should be (true)
    isAlive((1, 2), blinker2) should be (true)
    isAlive((-1, 1), blinker1) should be (false)
    isAlive((1, 3), blinker2) should be (false)
  }

  it should "calclulate live neighbours" in {
    import Challenge5._
    
    liveNeighbours((2, 2), blinker1) should be (2)
    liveNeighbours((2, 1), blinker1) should be (1)
  }

  it should "calculate the next liveness state of a cell" in {
    import Challenge5._

    aliveNextTurn((2, 1), blinker1) should be (false)
    aliveNextTurn((2, 2), blinker1) should be (true)
    aliveNextTurn((1, 2), blinker1) should be (true)
  }

  it should "calculate the next game state" in {
    import Challenge5._

    def justTheLiveOnes(c:ConwayState) = c.filter { case (k,v) => v }

    justTheLiveOnes(nextConwayState(blinker1)) should be (blinker2)
    justTheLiveOnes(nextConwayState(blinker2)) should be (blinker1)
  }

  "Card challenge" should "calculate scores correctly" in {
    import Challenge6._

    processHand(Seq()) should be (0)
    processHand(Seq(Add(3))) should be (3)
    processHand(Seq(Add(3), NegativeScore)) should be (-3)
    processHand(Seq(Add(3), NegativeScore, Add(2))) should be (-1)
    processHand(Seq(Add(3), NegativeScore, Add(2), DoubleScore)) should be (-2)
    processHand(Seq(Add(3), NegativeScore, Add(2), DoubleScore, ZeroScore)) should be (0)
    processHand(Seq(Add(3), NegativeScore, Add(2), DoubleScore, ZeroScore, NegativeScore)) should be (0)
    processHand(Seq(Add(3), NegativeScore, Add(2), DoubleScore, ZeroScore, DoubleScore)) should be (0)
    
  }

  it should "remove cards from a hand" in {
    import Challenge6._

    removeCard(Seq(Add(5)), Add(5)) should be (Seq.empty)
    removeCard(Seq(Add(5), Add(4)), Add(4)) should be (Seq(Add(5)))
    removeCard(Seq(Add(5), DoubleScore, Add(4)), DoubleScore) should be (Seq(Add(5), Add(4)))
    removeCard(Seq(Add(5), DoubleScore, Add(4)), Add(5)) should be (Seq(DoubleScore, Add(4)))
  }

  it should "calculate the difference in score from removing a card" in {
    import Challenge6._

    diffFromRemoving(Seq(Add(5)), Add(5)) should be (-5)
    diffFromRemoving(Seq(DoubleScore), DoubleScore) should be (0)
    diffFromRemoving(Seq(NegativeScore, Add(5)), NegativeScore) should be (0)
    diffFromRemoving(Seq(Add(5), NegativeScore, Add(3)), NegativeScore) should be (10)
  }

  it should "choose the best card for our oppenent to remove" in {
    import Challenge6._

    bestCardToRemove(Seq(Add(5), Add(3), Add(2))) should be (Add(5))
    bestCardToRemove(Seq(Add(5), Add(3), Add(4), DoubleScore)) should be (DoubleScore)
    bestCardToRemove(Seq(Add(5), Add(3), NegativeScore, ZeroScore)) should be (ZeroScore)
    bestCardToRemove(Seq(Add(5), Add(3), DoubleScore)) should be (Add(5))
  }

  it should "choose the best order for our hand" in {
    import Challenge6._

    bestOrder(Seq(Add(5))) should be (Seq(Add(5)))
    
    // A little internal utility for checking there is not a higher scoring order
    def isBest(h:Seq[Card]):Boolean = {
      def scoreHand(h:Seq[Card]) = processHand(removeCard(h, bestCardToRemove(h)))

      val hScore = scoreHand(h)
      h.permutations.find { alt => scoreHand(alt) > hScore } match {
        case Some(order) => println(s"Found a better order: $order"); false
        case _ => true
      }
    }

    isBest(bestOrder(Seq(Add(5), NegativeScore, Add(4)))) should be (true)
    isBest(bestOrder(Seq(Add(5), NegativeScore, Add(4), DoubleScore))) should be (true)
    isBest(bestOrder(Seq(Add(5), NegativeScore, Add(4), DoubleScore, ZeroScore, Add(4)))) should be (true)
  }

}
