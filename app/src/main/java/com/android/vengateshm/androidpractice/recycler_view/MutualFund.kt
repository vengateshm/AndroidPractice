package com.android.vengateshm.androidpractice.recycler_view

data class MutualFund(
    val  id:Int,
    val name: String,
    val return1Yr: String,
    val return2Yr: String,
    val return3Yr: String,
    val risk: String,
    val type: String,
    val cap: String,
    val imageUrl: String,
)

val mutualFundList by lazy {
    listOf(
        MutualFund(
            id = 1,
            name = "ABC Equity Fund",
            return1Yr = "10%",
            return2Yr = "18%",
            return3Yr = "25%",
            risk = "Moderate",
            type = "Equity",
            cap = "Large",
            imageUrl = "https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        ),
        MutualFund(
            id = 2,
            name = "XYZ Bond Fund",
            return1Yr = "5%",
            return2Yr = "7%",
            return3Yr = "6%",
            risk = "Low",
            type = "Bond",
            cap = "Medium",
            imageUrl = "https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        ),
        MutualFund(
            id = 3,
            name = "PQR Balanced Fund",
            return1Yr = "8%",
            return2Yr = "12%",
            return3Yr = "14%",
            risk = "Moderate",
            type = "Balanced",
            cap = "Large & Medium",
            imageUrl = "https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        ),
        MutualFund(
            id = 4,
            name = "LMN Technology Fund",
            return1Yr = "15%",
            return2Yr = "20%",
            return3Yr = "30%",
            risk = "High",
            type = "Equity",
            cap = "Large & Small",
            imageUrl = "https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        ),
        MutualFund(
            id = 5,
            name = "DEF Income Fund",
            return1Yr = "6%",
            return2Yr = "8%",
            return3Yr = "7%",
            risk = "Low",
            type = "Income",
            cap = "Medium",
            imageUrl = " https ://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        ),
        MutualFund(
            id = 6,
            name = "GHI Growth Fund",
            return1Yr = "12%",
            return2Yr = "16%",
            return3Yr = "20%",
            risk = "Moderate",
            type = "Equity",
            cap = "Large & Medium",
            imageUrl = "https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        ),
        MutualFund(
            id = 7,
            name = "JKL Balanced Income Fund",
            return1Yr = "9%",
            return2Yr = "10%",
            return3Yr = "12%",
            risk = "Moderate",
            type = "Balanced",
            cap = "Medium",
            imageUrl = "https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        ),
        MutualFund(
            id = 8,
            name = "MNO Global Fund",
            return1Yr = "14%",
            return2Yr = "22%",
            return3Yr = "28%",
            risk = "High",
            type = "Global",
            cap = "Large",
            imageUrl = "https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        ),
        MutualFund(
            id = 9,
            name = "STU Small Cap Fund",
            return1Yr = "20%",
            return2Yr = "30%",
            return3Yr = "40%",
            risk = "High",
            type = "Equity",
            cap = "Small",
            imageUrl = "https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        ),
        MutualFund(
            id = 10,
            name = "VWX Value Fund",
            return1Yr = "10%",
            return2Yr = "15%",
            return3Yr = "18%",
            risk = "Moderate",
            type = "Equity",
            cap = "Large",
            imageUrl = "https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        ),
        MutualFund(
            id = 11,
            name = "YZA Municipal Bond Fund",
            return1Yr = "4%",
            return2Yr = "5%",
            return3Yr = "4.5%",
            risk = "Low",
            type = "Bond",
            cap = "Medium",
            imageUrl = "https://images.pexels.com/photos/534216/pexels-photo-534216.jpeg"
        )
    )
}