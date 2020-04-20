package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {

		double salarioLiquido = salarioBase;
		if (salarioBase <= 1039) {
			return Math.round(0.0);
		}
		double descontoInss = this.calcularInss(salarioBase);
		salarioLiquido -= descontoInss;
		double descontoIrrf = this.calcularIrrf(salarioLiquido);
		salarioLiquido -= descontoIrrf;
		return Math.round(salarioLiquido);
	}

	private double calcularInss(double salarioBase) {
		double salarioInss = 0;
		if (salarioBase <= 1500) {
			salarioInss = (salarioBase * 8)/100;
		} else if(salarioBase > 1500 && salarioBase <= 4000) {
			salarioInss = (salarioBase * 9)/100;
		} else if(salarioBase > 4000) {
			salarioInss = (salarioBase*11)/100;
		}

		return salarioInss;
	}

	private double calcularIrrf(double salarioInss) {
		double salarioIrrf = 0;
		if (salarioInss < 3000) {
			return 0;
		} else if(salarioInss > 3000 && salarioInss <= 6000) {
			salarioIrrf =(salarioInss * 7.5)/100;
		} else if(salarioInss > 6000) {
			salarioIrrf =(salarioInss * 15)/100;
		}

		return salarioIrrf;
	}
}
