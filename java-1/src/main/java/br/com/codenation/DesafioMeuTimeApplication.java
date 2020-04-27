package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.jogador.Jogador;
import br.com.codenation.time.Time;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private List<Time> repositorioTime = new ArrayList<>();
	private List<Jogador> repositorioJogadores = new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		verificarIdentificador(id);
		repositorioTime.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		verificarIdentificador(id);
		searchTeamById(idTime);
		repositorioJogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = searchPlayerById(idJogador);
		searchTeamById(jogador.getIdTime()).
				setIdCapitao(jogador.getId());
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = searchTeamById(idTime);
		if (time.getIdCapitao() == null)
			throw new CapitaoNaoInformadoException("Capitão não informado");
		return time.getIdCapitao();
	}
	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return searchPlayerById(idJogador).getNome();
	}
	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return searchTeamById(idTime).getNome();
	}
	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		searchTeamById(idTime);
		return repositorioJogadores.stream()
				.sorted(Comparator.comparingLong(Jogador::getId))
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		searchTeamById(idTime);
		return repositorioJogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getNivelHabilidade))
				.map(Jogador::getId)
				.orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		 searchTeamById(idTime);
		return repositorioJogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.min(Comparator.comparing(Jogador::getDataNascimento))
				.map(Jogador::getId)
				.orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return repositorioTime.stream()
				.sorted(Comparator.comparingLong(Time::getId))
				.map(Time::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		searchTeamById(idTime);
		return repositorioJogadores.stream()
				.sorted(Comparator
						.comparing(Jogador::getSalario).reversed()
						.thenComparingLong(Jogador::getId))
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.map(Jogador::getId).findFirst()
				.orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));
	}
	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return searchPlayerById(idJogador).getSalario();
	}
	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return repositorioJogadores.stream()
				.sorted(Comparator
						.comparingInt(Jogador::getNivelHabilidade).reversed()
						.thenComparingLong(Jogador::getId))
				.limit(top)
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}
	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time timeCasa = searchTeamById(timeDaCasa);
		Time timeFora = searchTeamById(timeDeFora);
		if (timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal()))
			return timeFora.getCorUniformeSecundario();
		return timeFora.getCorUniformePrincipal();
	}
	private Time searchTeamById(Long idTime) {
		return repositorioTime.stream().filter(time -> time.getId().equals(idTime))
				.findFirst()
				.orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));
	}
	private Jogador searchPlayerById(Long idJogador) {
		return repositorioJogadores.stream().filter(jogador1 -> jogador1.getId().equals(idJogador))
				.findFirst().orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado"));
	}
	private boolean verifyPlayer(Long id) {
		return repositorioJogadores.stream().anyMatch(jogador -> jogador.getId().equals(id));
	}
	private boolean verifyTeam(Long id) {
		return repositorioTime.stream().anyMatch(time -> time.getId().equals(id));
	}
	public void verificarIdentificador(Long id) {
		if (verifyTeam(id) || verifyPlayer(id)) {
			throw new IdentificadorUtilizadoException("Id não disponivel");
		}

	}

}
