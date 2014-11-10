package com.example.indicemasacorporal;

import android.support.v7.app.ActionBarActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import excepciones.*;
/**
 * 
 * @author Oscar
 * @startuml
 * autonumber
 * MainActivity -[#red]> IndiceDeMasaCorporal : Envía peso y altura
 * note left:Si no hay NumberFormatException
 * MainActivity <[#0000FF]-- IndiceDeMasaCorporal : :Devuelve objeto Imc
 * note right:Si los parámetros son correctos
 * MainActivity <[#0000FF]-- IndiceDeMasaCorporal : :Devuelve Excepciones
 * note right:Si los parámetros son incorrectos
 * @enduml
 *  
 *
 */

public class MainActivity extends ActionBarActivity {
	EditText edAltura, edPeso;
	TextView txtImc, txtResultado;
	Button btnCalcula;
	IndiceMasaCorporal imc;
	ImageView image;
	private static final String LOGTAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edAltura = (EditText) findViewById(R.id.editAltura);
		edPeso = (EditText) findViewById(R.id.editPeso);
		txtImc = (TextView) findViewById(R.id.textView2);
		txtResultado = (TextView) findViewById(R.id.textView4);
		btnCalcula = (Button) findViewById(R.id.btnCalcula);
		image = (ImageView) findViewById(R.id.imageView1);
		btnCalcula.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				calcularImc();

			}
		});

	}

	private void calcularImc() {

		try {
			float peso = Float.parseFloat(edPeso.getText().toString());
			int altura = Integer.parseInt(edAltura.getText().toString());

			imc = new IndiceMasaCorporal(peso, altura);
			muestra();

		} catch (NumberFormatException e) {
			Log.e(LOGTAG, e.getMessage());
			ErrorCampoVacio err = new ErrorCampoVacio();
			err.show(getFragmentManager(), "");
			// return;
		} catch (ExcepcionDivByZero e) {
			Log.e(LOGTAG, e.getMessage());
			ErrorDivision err = new ErrorDivision();
			err.show(getFragmentManager(), "");

		} catch (ExcepcionRango e) {
			Log.e(LOGTAG, e.getMessage());
			ErrorFueraRango err = new ErrorFueraRango();
			err.show(getFragmentManager(), "");

		}catch(Exception e){
			Log.e(LOGTAG, e.getMessage());
		}

	}

	private void muestra() {
		txtImc.setText("  " + imc.getImc());
		txtImc.setBackgroundColor(Color.BLUE);
		txtImc.setTextColor(Color.RED);
		txtResultado.setText(imc.getResulado());
		txtResultado.setBackgroundColor(Color.YELLOW);
		txtResultado.setTextColor(Color.BLUE);
		asignaFoto();

		// Codigo para ocultar teclado Virtual
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(btnCalcula.getWindowToken(), 0);
	}

	private void asignaFoto() {

		switch (imc.getTag()) {
		case 0:
			image.setImageResource(R.drawable.esqueleto);
			break;
		case 1:
			image.setImageResource(R.drawable.normal);
			break;
		case 2:
			image.setImageResource(R.drawable.obeso);
			break;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
