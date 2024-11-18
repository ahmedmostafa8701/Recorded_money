package com.am_apps.recorded_money.confg

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min

var screenSize = Pair(0, 0)
const val testingWidth = 411.0
const val testingHeight = 819.0

// responsive width and height
fun responsiveWidth(width:Int) = (width * screenSize.first / testingWidth).dp
fun responsiveHeight(height:Int) = (height * screenSize.second / testingHeight).dp
fun responsiveWidth(width:Double) = (width * screenSize.first / testingWidth).dp
fun responsiveHeight(height:Double) = (height * screenSize.second / testingHeight).dp

// responsive font size
fun responsiveFont(size:Int) = (size * min(screenSize.first / testingWidth, screenSize.second / testingHeight)).sp
fun responsiveFont(size:Double) = (size * min(screenSize.first / testingWidth, screenSize.second / testingHeight)).sp

// responsive icon size and radius and padding

fun responsiveSize(size:Int) = (size * min(screenSize.first / testingWidth, screenSize.second / testingHeight)).dp
fun responsiveSize(size:Double) = (size * min(screenSize.first / testingWidth, screenSize.second / testingHeight)).dp