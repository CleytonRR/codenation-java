package challenge;

import java.util.Objects;

public class Carro {

    private final Motorista motorista;

    private final String placa;

    private final Cor cor;

    private Carro(Motorista motorista, String placa, Cor cor) {
        validarDados(placa, cor);
        validarMotorista(motorista);
        this.motorista = motorista;
        this.placa = placa;
        this.cor = cor;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public String getPlaca() {
        return placa;
    }

    public Cor getCor() {
        return cor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Carro carro = (Carro) o;
        return Objects.equals(motorista, carro.motorista) &&
                Objects.equals(placa, carro.placa) &&
                cor == carro.cor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(motorista, placa, cor);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "motorista=" + motorista +
                ", placa='" + placa + '\'' +
                ", cor=" + cor +
                '}';
    }

    public static CarroBuilder builder() {
        return new CarroBuilder();
    }


    public static class CarroBuilder {

        private Motorista motorista;

        private String placa;

        private Cor cor;

        private CarroBuilder() {
        }

        public CarroBuilder withMotorista(Motorista motorista) {
            if (motorista == null) {
                throw new EstacionamentoException("Deve possuir um motorista");
            }
            this.motorista = motorista;
            return this;
        }

        public CarroBuilder withPlaca(String placa) {
            if (placa.isEmpty()) {
                throw new NullPointerException("Carro deve possuir placa");
            }
            this.placa = placa;
            return this;
        }

        public CarroBuilder withCor(Cor cor) {
            if (cor == null) {
                throw new NullPointerException("Carro deve possuir cor");
            }
            this.cor = cor;
            return this;
        }

        public Carro build() {
            return new Carro(motorista, placa, cor);
        }
    }
    public void validarMotorista(Motorista motorista) {
        if (motorista == null) {
            throw new EstacionamentoException("Deve possuir um motorista");
        }
    }
    public void validarDados(String placa, Cor cor) {
        if (placa == null || cor == null) {
            throw new NullPointerException("Dados invalidos");
        }
    }
}
