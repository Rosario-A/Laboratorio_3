package com.example.crud_jetpack_compose

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
//Recibiremos lo mismo del MainActivity cada uno con su funcion lambda
fun InsertarDatos(
    nombre: String,
    funNombre: (String) -> Unit,
    fecha: String ,
    funFecha: (String) -> Unit,
    tipoSangre: String,
    funTipoSangre: (String) -> Unit,
    telefono: String,
    funTelefono: (String) -> Unit,
    correo: String,
    funCorreo: (String) -> Unit,
    montoPagado: String,
    funMontoPagado: (String) -> Unit,
    isEditando: Boolean,
    funIsEditando: () -> Unit,
    textButton: String,
    funTextButton: (String) -> Unit,
    listaAsistentes: MutableList<Asistente>,
    funResetCampos: () -> Unit
){
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Formulario de Asistentes",
        style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center)
    )
    Spacer(modifier = Modifier.padding(vertical = 3.dp))

    //
    OutlinedTextField(
        //que tome el ancho posible el TextField
        modifier = Modifier.fillMaxWidth(),
        //El valor que va a tomar
        value = nombre,
        //Que tipo de teclado tenemos
        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        onValueChange = { funNombre(it) },
        //Vamos a agregar un text
        label = { Text(text = "Nombre")},
        //Cuando estemos editando no queremos que sea modificable ya que seria el ID
        enabled = !isEditando
    )
    Spacer(modifier = Modifier.padding(vertical = 2.dp))

    var ffecha: String by rememberSaveable { mutableStateOf("")}
    val anio: Int
    val mes: Int
    val dia: Int
    val mCalendar = Calendar.getInstance()
    anio = mCalendar.get(Calendar.YEAR)
    mes = mCalendar.get(Calendar.MONTH)
    dia = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { Datepicker,anio:Int,mes:Int,dia:Int->
            ffecha = "$dia/${mes+1}/$anio"
            funFecha(ffecha)
        }, anio, mes, dia
    )
    Box(modifier = Modifier.fillMaxWidth()){
        Row(/*modifier = Modifier.align(Alignment.Center)*/){
            OutlinedTextField(
                //que tome el ancho posible el TextField
                //modifier = Modifier.fillMaxWidth(),

                value = fecha,
                onValueChange = {funFecha(it)},
                readOnly = true,
                label = {Text(text = "Fecha")}
            )
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
                    .padding(4.dp)
                    .clickable {
                        mDatePickerDialog.show()
                    }

            )

        }
    }
    Spacer(modifier = Modifier.padding(vertical = 2.dp))


    Box(modifier = Modifier.fillMaxWidth()){
        Row(/*modifier = Modifier.align(Alignment.Center)*/){
            OutlinedTextField(
                //que tome el ancho posible el TextField
                modifier = Modifier.size(100.dp,64.dp),
                //El valor que va a tomar
                value = tipoSangre,
                //Que tipo de teclado tenemos
                keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                maxLines = 1,
                onValueChange = { funTipoSangre(it) },
                //Vamos a agregar un text
                label = { Text(text = "Sangre")},
            )
            OutlinedTextField(
                //que tome el ancho posible el TextField
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                //El valor que va a tomar
                value = telefono,
                //Que tipo de teclado tenemos
                keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                maxLines = 1,
                onValueChange = { funTelefono(it) },
                //Vamos a agregar un text
                label = { Text(text = "Telefono")},
            )
        }
    }
    Spacer(modifier = Modifier.padding(vertical = 2.dp))


    OutlinedTextField(
        //que tome el ancho posible el TextField
        modifier = Modifier.fillMaxWidth(),
        //El valor que va a tomar
        value = correo,
        //Que tipo de teclado tenemos
        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        onValueChange = { funCorreo(it) },
        //Vamos a agregar un text
        label = { Text(text = "Correo")},
    )
    Spacer(modifier = Modifier.padding(vertical = 2.dp))


    OutlinedTextField(
        //que tome el ancho posible el TextField
        modifier = Modifier.fillMaxWidth(),
        //El valor que va a tomar
        value = montoPagado,
        //Que tipo de teclado tenemos
        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        maxLines = 1,
        onValueChange = { funMontoPagado(it) },
        //Vamos a agregar un text
        label = { Text(text = "Monto Pagado")},
    )
    Spacer(modifier = Modifier.padding(vertical = 4.dp))

    //Definimos el boton
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black), //colores del boton
        onClick = {
            //Si es true llamamos a una funcion editarAsistente
            if (isEditando){
                editarAsistente(nombre, fecha, tipoSangre, telefono, correo, montoPagado, listaAsistentes)
                funTextButton("Agregar Asistente")
                funIsEditando()
            } else {
                agregarAsistente(nombre, fecha, tipoSangre, telefono, correo, montoPagado, listaAsistentes)
            }
            funResetCampos()
        }
    ){
        Text(
            color = Color.White,
            text = textButton
        )

    }
}