package com.example.imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc.ui.theme.ImcTheme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculadoraIMC()
                }
            }
        }
    }
}

@Composable
fun CalculadoraIMC() {
    var peso by remember { mutableStateOf("") }
    var estatura by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var indice by remember { mutableStateOf("") }
    var mostrarResultado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Salud y Bienestar",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Vamos a calcular tu Índice de Masa Corporal",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "Escribe los siguientes datos para calcular tu IMC",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                )

                Column {
                    Text(
                        "Peso",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = peso,
                        onValueChange = {
                            peso = it
                            mostrarResultado = false
                        },
                        placeholder = { Text("Digita tu peso ") },
                        suffix = { Text("kg") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp),
                        shape = MaterialTheme.shapes.medium,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        singleLine = true
                    )
                }

                Column {
                    Text(
                        "Estatura",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = estatura,
                        onValueChange = {
                            estatura = it
                            mostrarResultado = false
                        },
                        placeholder = { Text("Digita tu Estatura ") },
                        suffix = { Text("m") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp),
                        shape = MaterialTheme.shapes.medium,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        resultado = calcularResultado(peso, estatura)
                        indice = obtenerIndice(resultado)
                        mostrarResultado = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 8.dp
                    )
                ) {
                    Text(
                        "CALCULAR IMC",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                // Texto informativo
                Text(
                    "Los campos marcados son obligatorios",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(top = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        if (mostrarResultado && !resultado.startsWith("Error")) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(obtenerImagenSegunIndice(indice)),
                        contentDescription = "Resultado IMC",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(bottom = 16.dp)
                    )

                    Text(
                        "Tu IMC es: $resultado",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Clasificación: $indice",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Text(
                        obtenerMensajeSegunIndice(indice),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        } else if (resultado.startsWith("Error")) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    resultado,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}

fun obtenerImagenSegunIndice(indice: String): Int {
    return when (indice) {
        "Delgadez severa", "Delgadez moderada", "Delgadez leve" -> R.drawable.bajo_peso
        "Peso normal" -> R.drawable.peso_normal
        "Preobeso" -> R.drawable.sobrepeso
        "Obesidad leve", "Obesidad media", "Obesidad mórbida" -> R.drawable.obesidad
        else -> R.drawable.default_image
    }
}

fun obtenerMensajeSegunIndice(indice: String): String {
    return when (indice) {
        "Delgadez severa", "Delgadez moderada", "Delgadez leve" ->
            "Es importante mejorar tu nutrición y alcanzar un peso saludable."
        "Peso normal" ->
            "Felicitaciones! Mantén tus hábitos saludables de alimentación."
        "Preobeso" ->
            "Debes hacer más actividad física y mejorar tu alimentación."
        "Obesidad leve", "Obesidad media", "Obesidad mórbida" ->
            "Te recomendamos consultar a un médico para tus hábitos alimenticios."
        else -> ""
    }
}

fun calcularResultado(pesoStr: String, estaturaStr: String): String {
    return try {
        if (pesoStr.isEmpty() || estaturaStr.isEmpty()) {
            return "Error: Por favor completa todos los campos"
        }

        val peso = pesoStr.toFloat()
        val estatura = estaturaStr.toFloat()

        if (peso < 20 || peso > 300) {
            return "Error: El peso debe estar entre 20 y 300 kg"
        }
        if (estatura < 0.5 || estatura > 2.5) {
            return "Error: La estatura debe estar entre 0.5 y 2.5 m"
        }

        val imc = peso / (estatura * estatura)
        val decimalFormat = DecimalFormat("#.##")
        decimalFormat.format(imc)
    } catch (e: Exception) {
        "Error: Ingresa valores válidos para peso y estatura"
    }
}

fun obtenerIndice(imcStr: String): String {
    return try {
        val imc = imcStr.toFloat()

        when {
            imc < 16 -> "Delgadez severa"
            imc < 17 -> "Delgadez moderada"
            imc < 18.5 -> "Delgadez leve"
            imc < 25 -> "Peso normal"
            imc < 30 -> "Preobeso"
            imc < 35 -> "Obesidad leve"
            imc < 40 -> "Obesidad media"
            else -> "Obesidad mórbida"
        }
    } catch (e: Exception) {
        "Error"
    }
}
