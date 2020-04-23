package br.com.codenation.time.repositorios;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.time.Time;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTimes {
    private List<Time> times = new ArrayList<>();

    public void add(Time time ) throws IdentificadorUtilizadoException {
        if(verifyId(time.getId())) {
            this.times.add(time);
        }
    }

    public Boolean verifyId(Long id) throws IdentificadorUtilizadoException {
        for (Time time : times) {
            if (time.getId() == id) {
                throw new IdentificadorUtilizadoException("Identificador já utilizado");
            }
        }
        return true;
    }
}
