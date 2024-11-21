package com.am_apps.recorded_money.confg

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min

var screenSize = Pair(0, 0)
const val testingWidth = 411.0
const val testingHeight = 819.0

// responsive width and height
@Composable
fun responsiveWidth(width:Int) = (width * LocalConfiguration.current.screenWidthDp / testingWidth).dp
@Composable
fun responsiveHeight(height:Int) = (height * LocalConfiguration.current.screenHeightDp / testingHeight).dp
@Composable
fun responsiveWidth(width:Double) = (width * LocalConfiguration.current.screenWidthDp / testingWidth).dp
@Composable
fun responsiveHeight(height:Double) = (height * LocalConfiguration.current.screenHeightDp / testingHeight).dp

// responsive font size
@Composable
fun responsiveFont(size:Int) = (size * min(LocalConfiguration.current.screenWidthDp / testingWidth, LocalConfiguration.current.screenHeightDp / testingHeight)).sp
@Composable
fun responsiveFont(size:Double) = (size * min(LocalConfiguration.current.screenWidthDp / testingWidth, LocalConfiguration.current.screenHeightDp / testingHeight)).sp

// responsive icon size and radius and padding
@Composable
fun responsiveSize(size:Int) = (size * min(LocalConfiguration.current.screenWidthDp / testingWidth, LocalConfiguration.current.screenHeightDp / testingHeight)).dp
@Composable
fun responsiveSize(size:Double) = (size * min(LocalConfiguration.current.screenWidthDp / testingWidth, LocalConfiguration.current.screenHeightDp / testingHeight)).dp