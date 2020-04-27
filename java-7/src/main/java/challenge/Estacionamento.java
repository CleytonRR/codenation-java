package challenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Estacionamento {
    private final int qtdVagas = 10;
    private final int idadeSenior = 55;

    LinkedList<Carro> carros = new LinkedList<>();

    public void estacionar(Carro carro) {
        if (carros.size() == qtdVagas) {
            for (Carro carroEstacionado : carros) {
                if (carroEstacionado.getMotorista().getIdade() <= idadeSenior) {
                    carros.removeFirstOccurrence(carroEstacionado);
                    break;
                }
            }
            if (carros.size() == qtdVagas) {
                throw new EstacionamentoException("Estacionamento cheio!");
            }
        }
        carros.add(carro);
    }

    public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carros.contains(carro);
    }
}
