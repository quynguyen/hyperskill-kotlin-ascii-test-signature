import java.util.Scanner

/*
*   1 2 3 4 5
* 1   X X X
* 2     X X X
* 3
* 4 X X X
* 5
*
*   1 2 3 4 5
* 1   X X X
* 2   X X X
* 3   X X X
* 4
* 5
*
* */
data class Point(val col: Int, val row: Int) {

    fun isValid() = col >= 0 && row >= 0

    operator fun plus(wd: WeighedDirection) = when (wd.d) {
        Direction.HORIZONTAL -> Point(col + wd.len, row)
        Direction.VERTICAL -> Point(col, row + wd.len)
    }

}

enum class Direction {
    HORIZONTAL, VERTICAL;

    operator fun times(n: Int): WeighedDirection {
        return WeighedDirection(this, n)
    }
}

data class WeighedDirection(val d: Direction, val len: Int)

data class Ship(
        val id: Int,
        val size: Int, val start: Point, val direction: Direction,
        val hits: Set<Point> = setOf()) {

    val end = start + direction * (size - 1)
    val destroyed = hits.size == size

    // check for overlaps
    operator fun contains(other: Ship): Boolean {
        if (other.direction == direction) {
            return other.start in this || other.end in this
        }

        val vertical = if (other.direction == Direction.VERTICAL) other else this
        val horizontal = if (other.direction == Direction.HORIZONTAL) other else this

        return horizontal.start.row in vertical.start.row..vertical.end.row
                && vertical.start.col in horizontal.start.col..horizontal.end.col
    }

    operator fun contains(p: Point): Boolean {
        return when (direction) {
            Direction.HORIZONTAL -> start.row == p.row && start.col <= p.col && end.col >= p.col
            Direction.VERTICAL -> start.col == p.col && start.row <= p.row && end.row >= p.row
        }
    }
}

data class Board(
        val id: Int, val user: User,
        val width: Int, val height: Int,
        val ships: List<Ship> = listOf()) {

    fun fits(ship: Ship) = ship.start in this && ship.end in this

    fun isOverlap(ship: Ship): Boolean {
        return ships.any { ship in it }
    }

    operator fun contains(p: Point) = p.isValid() && p.col < width && p.row < height
}

// Extension function to check if any of the ships are at the given point
fun List<Ship>.contains(p: Point): Boolean {
    forEach {
        if (p in it) {
            return true
        }
    }

    return false
}

// Extension function to check the direction of the ship at the given point
fun List<Ship>.getShipDirection(p: Point): Direction? {
    forEach {
        if (p in it) {
            return it.direction
        }
    }

    return null
}

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    // put your code here
}