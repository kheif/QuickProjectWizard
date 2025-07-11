package com.github.cnrture.quickprojectwizard.projectwizard.composearch.ui

fun emptyActivity(
    packageName: String,
    projectName: String,
    startDest: String,
    isHiltEnable: Boolean,
    isNavigationEnable: Boolean,
    dataDiDomainPresentationUiPackages: Boolean,
): String {
    return when {
        !dataDiDomainPresentationUiPackages -> emptyActivity(packageName, projectName)
        isHiltEnable && isNavigationEnable -> hiltNavigation(packageName, projectName, startDest)
        isHiltEnable -> hilt(packageName, projectName)
        isNavigationEnable -> navigation(packageName, projectName, startDest)
        else -> emptyActivity(packageName, projectName)
    }
}

private fun hilt(packageName: String, projectName: String) = """
package $packageName.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import $packageName.ui.theme.${projectName}Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ${projectName}Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(text = "Hello, World!", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
""".trimIndent()

private fun hiltNavigation(packageName: String, projectName: String, startDest: String) = """
package $packageName.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import $packageName.navigation.NavigationGraph
import $packageName.navigation.Screen.${startDest}
import $packageName.ui.theme.${projectName}Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ${projectName}Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val startDestination = $startDest
                    NavigationGraph(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
""".trimIndent()


private fun emptyActivity(packageName: String, projectName: String) = """
package $packageName.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import $packageName.ui.theme.${projectName}Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ${projectName}Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(text = "Hello, World!", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
""".trimIndent()

private fun navigation(packageName: String, projectName: String, startDest: String) = """
package $packageName.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import $packageName.navigation.NavigationGraph
import $packageName.navigation.Screen.${startDest}
import $packageName.ui.theme.${projectName}Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ${projectName}Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val startDestination = $startDest
                    NavigationGraph(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
""".trimIndent()
