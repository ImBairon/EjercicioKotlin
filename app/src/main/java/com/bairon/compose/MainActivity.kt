package com.bairon.compose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bairon.compose.ui.theme.AppTheme
import com.bairon.compose.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val destinoActual = navBackStackEntry?.destination

                            Destino.entries.forEach { destino ->
                                val destinoSeleccionado =
                                    destinoActual?.hierarchy?.any { it.route == destino.ruta } == true

                                NavigationBarItem(
                                    selected = destinoSeleccionado,
                                    onClick = {
                                        if (!destinoSeleccionado) {
                                            navController.navigate(destino.ruta) {
                                                popUpTo(navController.graph.findStartDestination().id)
                                                launchSingleTop = true
                                            }
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (destinoSeleccionado) destino.iconoSeleccionado else destino.icono,
                                            contentDescription = stringResource(id = destino.nombre)
                                        )
                                    },
                                    label = {
                                        Text(text = stringResource(id = destino.nombre))
                                    }
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Destino.entries.first().ruta
                        ) {
                            composable(
                                route = Destino.Inventario.ruta,
                                enterTransition = {
                                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                                },
                                exitTransition = {
                                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                                }
                            ) {
                                Catalogo() // Llamamos al Composable de Catalogo en este destino
                            }

                            composable(
                                route = Destino.Confirmacion.ruta,
                                enterTransition = {
                                    when (initialState.destination.route) {
                                        Destino.Inventario.ruta -> slideIntoContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Left
                                        )

                                        else -> slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                                    }
                                },
                                exitTransition = {
                                    when (targetState.destination.route) {
                                        Destino.Inventario.ruta -> slideOutOfContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Right
                                        )

                                        else -> slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                                    }

                                }
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "¡Tu Producto ha sido confirmado! \n\n")
                                    Text(text = "Primero Registrate en el apartado de usuario ->")
                                }
                            }

                            composable(
                                route = Destino.Usuario.ruta,
                                enterTransition = {
                                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                                },
                                exitTransition = {
                                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                                }
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        Text(text = "Perfil de Usuario", style = Typography.titleLarge)

                                        TextField(
                                            value = "",
                                            onValueChange = {  },
                                            label = { Text("Nombre de Usuario") },
                                            modifier = Modifier.fillMaxWidth()
                                        )

                                        TextField(
                                            value = "",
                                            onValueChange = { /* Manejar el cambio */ },
                                            label = { Text("Correo Electrónico") },
                                            modifier = Modifier.fillMaxWidth()
                                        )

                                        Button(onClick = { }) {
                                            Text("Guardar")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
