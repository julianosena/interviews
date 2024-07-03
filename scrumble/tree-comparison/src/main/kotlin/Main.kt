package com.ci

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val ketchup = Item("Ketchup", "Tomato ketchup", 0.5)
    val ranch = Item("Ranch", "Ranch dressing", 0.7)
    val sauce = Item("Sauce", "Choice of sauces", 0.0, ketchup, ranch)
    val cheeseBurger = Item("Cheese Burger", "Cheesy delight", 5.0)
    val chickenBurger = Item("Chicken Burger", "Crispy chicken", 6.0, sauce)
    val burger = Item("Burger", "Various burgers", 0.0, cheeseBurger, chickenBurger)
    val coke = Item("Coke", "Classic Coke", 1.5)
    val dietCoke = Item("Diet Coke", "Diet version", 1.5)
    val drinks = Item("Drinks", "Various drinks", 0.0, coke, dietCoke)
    val frenchFries = Item("French Fries", "Crispy fries", 2.0)
    val onionRings = Item("Onion Rings", "Battered onion rings", 2.5)
    val sides = Item("Sides", "Various sides", 0.0, frenchFries, onionRings)

    val old = Menu(arrayOf(burger, drinks, sides))

    val currentSides = Item("Sides", "Various sides", 0.0, frenchFries, onionRings)

    val current = Menu(arrayOf(burger, drinks, currentSides))

    println(old == current)
}

data class Menu(val items: Array<Item>?) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Menu

        return items.contentEquals(other.items)
    }

    override fun hashCode(): Int {
        return items.contentHashCode()
    }
}

data class Item(val name : String, val description : String, val price: Double, val left : Item? = null, val right : Item? = null) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Item

        return ((name == other.name) &&
                (description == other.description) &&
                (price == other.price)) &&
                left == other.left && right == other.right
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)
        return result
    }
}