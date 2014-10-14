package com.example.indicemasacorporal;
import java.text.DecimalFormat;

import android.util.Log;

import  excepciones.*;

public class IndiceMasaCorporal {
	private float peso;
	private int altura;
	private float imc;
	private byte tag;
	private String resulado;

	private final static int MAX_PESO = 300;
	private final static int MIN_PESO = 10;
	private final static int MAX_ALTURA = 300;
	private final static int MIN_ALTURA = 100;

	public IndiceMasaCorporal(float peso, int altura) throws ExcepcionDivByZero,ExcepcionRango {
		// super();
		if (validarRango(altura, peso)) {
			this.peso = peso;
			this.altura = altura;
			this.imc = calcularImc();
			this.resulado = clasificacion(imc);
		}else{
			throw new ExcepcionRango() ;
		}

	}

	private static boolean validarRango(int altura, float peso)throws ExcepcionDivByZero {


		if (altura == 0) {
			throw new ExcepcionDivByZero();
		}

		if (altura < MIN_ALTURA || altura > MAX_ALTURA) {
			return false;
		}
		if (peso < MIN_PESO || peso > MAX_PESO) {
			return false;
		}

		return true;
	}


	public float calcularImc(){
		
		float resultado = (float)(peso/Math.pow((float)altura/100, 2));
		Log.e("IMC",""+resultado);
		
		return resultado;
		
	}

	public String clasificacion(float imc) {

		if (imc < 16) {
			tag=0;
			return "Infrapeso: Delgadez severa";

		} else if ( imc <= 16.99) {
			tag=0;
			return "Infrapeso: Delgadez moderada";

		} else if ( imc <= 18.49) {
			tag=0;
			return "Infrapeso: Delgadez aceptable";

		} else if ( imc <= 24.99) {
			tag=1;
			return "Peso normal";

		} else if (imc <= 29.99) {
			tag=2;
			return "Sobrepeso";

		} else if ( imc <= 34.99) {
			tag=2;
			return "Obeso: Tipo I";

		} else if ( imc <= 40) {
			tag=2;
			return "Obeso: Tipo II";

		} else if (imc > 40) {
			tag=2;
			return "Obeso: Tipo II";
			
		} else {
			return "No se pudo registrar";
		}

	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getImc() {
		DecimalFormat df = new DecimalFormat("0.##");
		return df.format(imc);
	}

	public void setImc(float imc) {
		this.imc = imc;
	}

	public String getResulado() {
		return resulado;
	}

	public void setResulado(String resulado) {
		this.resulado = resulado;
	}

	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}
	
	

	
}


