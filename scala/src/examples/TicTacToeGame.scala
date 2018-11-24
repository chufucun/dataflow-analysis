package examples

/**
  * 《七周七语言》 第五章 Scala 第一天:山丘上的城堡
  * 第一天自习习题: Tic Tac Toe 井子游戏  两个选手玩
  *
  */
object TicTacToeGame extends App {
  def tttFormat(board: Array[Char]): String =
    "|" + board(0) + "|" + board(1) + "|" + board(2) + "|\n" +
      "|" + board(3) + "|" + board(4) + "|" + board(5) + "|\n" +
      "|" + board(6) + "|" + board(7) + "|" + board(8) + "|\n"

  println("Enter the number of the square you want to occupy!\n" +
    tttFormat(GameObj.board))
  while (GameObj.atPlay) {
    println(tttFormat(GameObj.updatedStateArray))
  }
  GameObj.nextTurn
  println("game over! " + GameObj.nextTurn + "'s win!")
}


object GameObj {
  val board: Array[Char] = Array('1', '2', '3',
    '6', '5', '4',
    '7', '8', '9')
  var whosTurn = false

  def nextTurn: Char = {
    whosTurn = !whosTurn;
    if (whosTurn) 'X' else 'O'
  }

  def atPlay: Boolean =
    !(board(0) == 'X' && board(1) == 'X' && board(2) == 'X') &&
      !(board(3) == 'X' && board(4) == 'X' && board(5) == 'X') &&
      !(board(6) == 'X' && board(7) == 'X' && board(8) == 'X') &&
      !(board(0) == 'X' && board(4) == 'X' && board(8) == 'X') &&
      !(board(6) == 'X' && board(4) == 'X' && board(2) == 'X') &&
      !(board(0) == 'X' && board(3) == 'X' && board(6) == 'X') &&
      !(board(1) == 'X' && board(4) == 'X' && board(7) == 'X') &&
      !(board(2) == 'X' && board(5) == 'X' && board(8) == 'X') &&
      !(board(0) == 'O' && board(1) == 'O' && board(2) == 'O') &&
      !(board(3) == 'O' && board(4) == 'O' && board(5) == 'O') &&
      !(board(6) == 'O' && board(7) == 'O' && board(8) == 'O') &&
      !(board(0) == 'O' && board(4) == 'O' && board(8) == 'O') &&
      !(board(6) == 'O' && board(4) == 'O' && board(2) == 'O') &&
      !(board(0) == 'O' && board(3) == 'O' && board(6) == 'O') &&
      !(board(1) == 'O' && board(4) == 'O' && board(7) == 'O') &&
      !(board(2) == 'O' && board(5) == 'O' && board(8) == 'O')

  def updatedStateArray: Array[Char] = {
    val in: Int = Integer.parseInt(readLine()) - 1
    board.update(in, nextTurn)
    board
  }
}