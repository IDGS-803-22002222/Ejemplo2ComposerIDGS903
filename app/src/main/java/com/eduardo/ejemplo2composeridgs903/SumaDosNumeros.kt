package com.eduardo.ejemplo2composeridgs903


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun SumaDosNumeros() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    val options = listOf("suma", "resta", "multiplica", "divicion")
    var seleccion by remember { mutableStateOf("suma") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Numero 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        options.forEach { opera ->
            Row(
                Modifier
                    .selectable(
                        selected = (opera == seleccion),
                        onClick = { seleccion = opera }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (opera == seleccion),
                    onClick = { seleccion = opera }
                )
                Text(
                    text = opera,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Button(onClick = {
            val n1 = num1.toIntOrNull()
            val n2 = num2.toIntOrNull()

            resultado = if (n1 != null && n2 != null) {
                when (seleccion) {
                    "suma" -> "resultado: ${n1 + n2}"
                    "resta" -> "resultado: ${n1 - n2}"
                    "multiplica" -> "resultado: ${n1 * n2}"
                    "divicion" -> {
                        if (n2 != 0) {
                            "resultado: ${n1.toDouble() / n2.toDouble()}"
                        } else {
                            "no se puede dividir en cero"
                        }
                    }
                    else -> "operación no válida"
                }
            } else {
                "num invalido"
            }
        }) {
            Text("Sumar")
        }
        Text(text = resultado)
    }
}
