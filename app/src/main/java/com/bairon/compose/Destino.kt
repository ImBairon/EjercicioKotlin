package com.bairon.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bairon.compose.ui.theme.Typography


enum class Destino(
    val ruta: String,
    @StringRes val nombre: Int,
    val icono: ImageVector,
    val iconoSeleccionado: ImageVector
) {
    Inventario(
        ruta = "inventario",
        nombre = R.string.destino1,
        icono = Icons.Outlined.Home,
        iconoSeleccionado = Icons.Filled.Home
    ),

    Confirmacion(
        ruta = "confirmacion",
        nombre = R.string.destino2,
        icono = Icons.Outlined.CheckCircle,
        iconoSeleccionado = Icons.Filled.CheckCircle
    ),

    Usuario(
        ruta = "usuario",
        nombre = R.string.destino3,
        icono = Icons.Outlined.AccountCircle,
        iconoSeleccionado = Icons.Filled.AccountCircle
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Catalogo() {
    val productos = listOf(
        Triple("Peluche Estrella", "Estrella Brillante: Una estrella de peluche para iluminar tus noches.", "$12.99"),
        Triple("Cámara Canon", "Captura Perfecta: Cámara para inmortalizar tus momentos especiales.", "$15.99"),
        Triple("Muñeco Carita Feliz", "Carita Feliz: Un muñeco sonriente que alegra el día.", "$8.99"),
        Triple("Kit de Herramientas", "Herramientas Útiles: Todo lo necesario para tus reparaciones.", "$25.99"),
        Triple("Juguete Amigo Peludo", "Amigo Peludo: El compañero perfecto para tus aventuras.", "$29.99"),
        Triple("Corazón De Peluche", "Corazón Amoroso: Un corazón de peluche lleno de amor y cariño.", "$18.99"),
        Triple("Lámpara Luz Cálida", "Idea Brillante: Una lámpara para inspirar tus mejores ideas.", "$10.99"),
        Triple("Set de Construcción Segura", "Construcción Segura: Herramientas para construir tus sueños.", "$20.99"),
        Triple("Pack Mensajería", "Mensaje Rápido: Comunicación eficiente y segura.", "$11.99"),
        Triple("Reloj Alarma Puntual", "Alarma Puntual: Un reloj que te ayuda a nunca llegar tarde.", "$13.99")
    )

    val iconosProductos = listOf(
        Icons.Outlined.Star,
        Icons.Outlined.Screenshot,
        Icons.Outlined.Face,
        Icons.Outlined.Handyman,
        Icons.Outlined.Pets,
        Icons.Outlined.Favorite,
        Icons.Outlined.Lightbulb,
        Icons.Outlined.Build,
        Icons.Outlined.Email,
        Icons.Outlined.Alarm
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo") },
                actions = {
                    IconButton(onClick = { /* Acción al presionar el ícono */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Buscar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(productos.size) { index ->
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = iconosProductos[index],
                            contentDescription = "Producto",
                            modifier = Modifier.size(80.dp)
                        )
                        Column(
                            modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = productos[index].first,
                                style = Typography.titleMedium
                            )
                            Text(
                                text = productos[index].second,
                                style = Typography.bodyMedium
                            )
                        }
                        Text(
                            text = productos[index].third,
                            style = Typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}
