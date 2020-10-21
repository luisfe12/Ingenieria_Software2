package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //creaciond eobjetos editext
    private EditText etcodigo, etdescripcion, etprecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etcodigo = (EditText)findViewById(R.id.txt_codigo);
        etdescripcion = (EditText)findViewById(R.id.txt_descripcion);
        etprecio = (EditText)findViewById(R.id.txt_precio);
    }

    //metodos paar los botones
    public void Registrar(View view){
        //primero crear objeto de la clase que creamos paar la bd
        //pide cuatro parametros: un contex, nombre de la bd, paarmetro nulo, version d ela bd
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //abrir base de datos de modo lectura y escritura
        //crear objeto de la clase sqlitedatabase y se iguala al anterior
        //nombre de base de datos
        SQLiteDatabase basededatos = admin.getWritableDatabase();//este emtodo utilizamos bd en modo escritura y lectura, ya crramos la conexion con nuetra clase creada

        //comenzar a trabajar con los datos que el usuario nos proporcione
        String codigo = etcodigo.getText().toString();
        String descripcion = etdescripcion.getText().toString();
        String precio = etprecio.getText().toString();

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            //para colocarlo dentro de la base de datos
            ContentValues registro = new ContentValues();
           // pasar referencia y nombre de la variable que tiene la tabla
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);
            //con eso guardamos los valores

            //ahora insertaremso dentro de la tabla articulos
            //nombre de la tabla, un null y la varibel que contine a los registros
            basededatos.insert("articulos", null, registro);
            //importante cerrar la base de datos
            basededatos.close();
            //limpiar los campos donde el usuario ha escrito
            etcodigo.setText("");
            etdescripcion.setText("");
            etprecio.setText("");

            Toast.makeText(this, "Se registro correctamente", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this, "Llnen todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Buscar(View view){
        //nuevo objeto creado para la bd
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //nueva base de datos par lectura de escritura
        SQLiteDatabase basededatos = admin.getWritableDatabase();//lectura y conexion con la bd

        //crramos varible paar pbtener el codigo como string
        String codigo = etcodigo.getText().toString();
        if (!codigo.isEmpty()){//para ver si el codigo ni viene vacio
            //cursor os ayuda al momento para seleccionar un producto atraves de su código
            Cursor fila = basededatos.rawQuery("select descripcion, precio from Articulos where codigo ="+ codigo, null);//este metodo nos ayuda a aplicar un select

            //estrucutra paar comprobar si encontro datos
            if (fila.moveToFirst()){//revisar si la consulta continen valores
                //los numeros van ascendiendo de la form acomo consultamos valores es un vector
                etdescripcion.setText(fila.getString(0));//obtenr la cadena de string que obtines
                etprecio.setText(fila.getString(1));//segundo valor que consultamos
                basededatos.close();
            }
            else {
                Toast.makeText(this, " No existe el articulo que buscas", Toast.LENGTH_SHORT).show();
                basededatos.close();
            }
        }

        else{//si no pone codigo
            Toast.makeText(this, "Registe el código por favor cpp", Toast.LENGTH_SHORT).show();
        }
    }

    //para la eliminacion
    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        //crearbd lectura y escirtura
        SQLiteDatabase basededatos = admin.getWritableDatabase();//modo lectur ay escritura
        //string para buscar y eliminar
        String codigo = etcodigo.getText().toString();

        if (!codigo.isEmpty()){
            //varible de tipo entero, eliminar devuelve un entero los parametros que se pasan son la tabla codigo = codigo
            int cantidad = basededatos.delete("Articulos", "codigo=" + codigo, null);
            basededatos.close();

            //agora limpiar campos
            etcodigo.setText("");
            etdescripcion.setText("");
            etprecio.setText("");
            //condiconales paar ver si se elimino
            if (cantidad == 1){//igual a uno es por que si lo elimino
                Toast.makeText(this,"Elimino articulo", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Ingrese el codigo causa", Toast.LENGTH_SHORT).show();
        }
    }

    //para modificar un articulo o producto
    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        //cramos la base de datos
        SQLiteDatabase basededatos = admin.getWritableDatabase();

        //varibles donde se guarda lo modficado por el usuario, se crean todos por que no sabemos que vaa a modificar
        // necesario guardar  all en el modificar
        String codigo = etcodigo.getText().toString();
        String descripcion = etdescripcion.getText().toString();
        String precio = etprecio.getText().toString();

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            //variable de registro paar los valores
            ContentValues registro = new ContentValues();
            //guardamso los valores con referencia en el registro
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            //variable de tipo entero paar guardar las modificaciones
            //el modififcar retorna enteros
            //el whereClaususe = codigo que identifica
            int cantidad = basededatos.update("Articulos", registro,"codigo=" + codigo,null);
            basededatos.close();

            //verfifcar si modifico
            if (cantidad == 1){
                Toast.makeText(this, "articulo modificado correctamente", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Articulo inexistente", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}