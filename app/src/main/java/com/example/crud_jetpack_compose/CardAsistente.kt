package com.example.crud_jetpack_compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardAsistente(
    funNombre: (String) -> Unit,
    funFecha: (String) -> Unit,
    funTipoSangre: (String) -> Unit,
    funTelefono: (String) -> Unit,
    funCorreo: (String) -> Unit,
    funMontoPagado: (String) -> Unit,
    funTextButton: (String) -> Unit,
    funIsEditando: (Boolean) -> Unit,
    funBorrarAsistente: (String) -> Unit,
    asistente: Asistente
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp), Arrangement.Center){
            Text(text = asistente.nombre, modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(text = asistente.fecha, modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(text = asistente.tipoSangre, modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(text = asistente.telefono, modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(text = asistente.correo, modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(text = asistente.montoPagado, modifier = Modifier.align(Alignment.CenterHorizontally))
            Row(modifier = Modifier.fillMaxWidth()){
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                    onClick = {
                        funNombre(asistente.nombre)
                        funFecha(asistente.fecha)
                        funTipoSangre(asistente.tipoSangre)
                        funTelefono(asistente.telefono)
                        funCorreo(asistente.correo)
                        funMontoPagado(asistente.montoPagado)
                        funTextButton("Editar Asistente")
                        funIsEditando(true)
                    }
                ){
                    Text(text = "Editar", color = Color.White)
                }

                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    onClick = {
                        funBorrarAsistente(asistente.nombre)
                    }
                ){
                    Text(text = "Borrar", color = Color.White)
                }
            }
        }
    }
}