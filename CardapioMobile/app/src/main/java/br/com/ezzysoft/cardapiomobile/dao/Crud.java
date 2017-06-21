package br.com.ezzysoft.cardapiomobile.dao;

import java.util.List;

import br.com.ezzysoft.cardapiomobile.util.exception.ErroSistema;

/**
 * Created by christian on 21/06/17.
 */

public interface Crud<E> {

    public long salvar(E entidade) throws ErroSistema;

    public void deletar(E entidade) throws ErroSistema;

    public List<E> buscar() throws ErroSistema;
}
