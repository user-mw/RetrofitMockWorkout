package com.example.retrofitmockworkout.domain

data class Car(
    val id: Int,
    val brand: String,
    val model: String,
    val year: Int,
    val color: String,
    val cost: String
) {

    override fun toString(): String =
        """
            Brand: $brand,
            Model: $model,
            Year: $year,
            Cost: $cost
        """.trimIndent()
}