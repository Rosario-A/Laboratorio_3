package com.example.crud_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crud_jetpack_compose.ui.theme.CRUD_Jetpack_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CRUD_Jetpack_ComposeTheme {
                //Necesitamos crear una lista para mantener lo objetos
                val listaAsistentes = remember { mutableStateListOf<Asistente>() }

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    //Usaremos una caja en la cual modificaremos el tamaño, para que tome el maximo de la pantalla
                    Box(modifier = Modifier.fillMaxSize()){
                        //llamaremos al composable el cual recibe la Lista de Asistentes
                        ScreenCRUD(listaAsistentes)
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenCRUD(listaAsistentes: MutableList<Asistente>){
    //Declarando un objeto MutableState en un composable
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var tipoSangre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var montoPagado by remember { mutableStateOf("") }
    //Estado para saber si estamos en modo edición o no
    var isEditando by remember { mutableStateOf(false) }
    //Estado para poder hacer el cambio en el texto del botón
    var textButton by remember { mutableStateOf("Agregar Asistente") }

    //Parte Visual
    //Debe tomar el ancho y alto posible de toda la pantalla y tenga una separación en todos sus lados de 12.dp
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(12.dp)) {
        //Esta otra columna nos ayuda a separar el formulario del RecyclerView
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            //Composable de formulario para recibir los estados
            InsertarDatos(
                nombre = nombre,
                //Funcion para poder detectar los cambios de este estado y actualizar
                funNombre = { nombre = it} ,

                fecha = fecha,
                //Funcion para poder detectar los cambios de este estado y actualizar
                funFecha = { fecha = it},

                tipoSangre = tipoSangre,
                //Funcion para poder detectar los cambios de este estado y actualizar
                funTipoSangre = { tipoSangre = it},

                telefono = telefono,
                //Funcion para poder detectar los cambios de este estado y actualizar
                funTelefono = { telefono = it},

                correo = correo,
                //Funcion para poder detectar los cambios de este estado y actualizar
                funCorreo = { correo = it},

                montoPagado = montoPagado,
                //Funcion para poder detectar los cambios de este estado y actualizar
                funMontoPagado = { montoPagado = it},

                //Estados
                isEditando = isEditando,
                funIsEditando = { isEditando = false},
                textButton = textButton,
                funTextButton = { textButton = it},
                listaAsistentes = listaAsistentes,

                //Funcion para resetear los campos del formulario
                funResetCampos = {
                    nombre = ""
                    fecha = ""
                    tipoSangre = ""
                    telefono = ""
                    correo = ""
                    montoPagado = ""
                }
            )
            
            Column(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                ){
                    items(listaAsistentes) { asistente ->
                        CardAsistente(
                            funNombre = { nombre = it},
                            funFecha = { fecha = it},
                            funTipoSangre = { tipoSangre = it},
                            funTelefono = { telefono = it},
                            funCorreo = { correo = it},
                            funMontoPagado = { montoPagado = it},
                            funTextButton = { textButton = it},
                            funIsEditando = { isEditando = it},
                            funBorrarAsistente = { borrarAsistente(it, listaAsistentes)},
                            asistente = asistente
                        )
                    }
                }
            }
        }
    }
}

fun agregarAsistente(nombre: String, fecha: String, tipoSangre: String, telefono: String, correo: String, montoPagado: String, listaAsistentes: MutableList<Asistente>){
    listaAsistentes.add(Asistente(nombre, fecha, tipoSangre, telefono, correo, montoPagado))
}

fun editarAsistente(nombre: String, fecha: String, tipoSangre: String, telefono: String, correo: String, montoPagado: String, listaAsistentes: MutableList<Asistente>){
    listaAsistentes.forEach { asistente ->
        if (asistente.nombre == nombre){
            asistente.fecha = fecha
            asistente.tipoSangre = tipoSangre
            asistente.telefono = telefono
            asistente.correo = correo
            asistente.montoPagado = montoPagado
        }
    }
}

fun borrarAsistente(nombre: String, listaAsistentes: MutableList<Asistente>){
    listaAsistentes.forEach { asistente ->
        if(asistente.nombre == nombre){
            listaAsistentes.remove(asistente)
        }
    }
}

