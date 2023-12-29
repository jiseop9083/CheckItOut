package com.example.madcampweek1

data class ProfileDTO(
    val name: String,
    val studentID: Int,
    val phoneNumber: String,
    val major: Major
)

enum class Major {
    ComputerScience,
    IndustrialDesign,
    MechanicalEngineering,
    NuclearEngineering,
}
