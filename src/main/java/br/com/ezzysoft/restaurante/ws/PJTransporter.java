package br.com.ezzysoft.restaurante.ws;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author christian
 */
public class PJTransporter implements Serializable {
    private String status;
    private String message;
    private String cnpj;
    private String tipo;
    private String abertura;
    private String nome;
    private String fantasia;
    @JsonProperty("atividade_principal")
    private List<AtividadeRF> atividade_principal;
    @JsonProperty("atividades_secundarias")
    private List<AtividadeRF> atividades_secundarias;
    private String natureza_juridica;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String municipio;
    private String uf;
    private String email;
    private String telefone;
    private String efr;
    private String situacao;
    private String ultima_atualizacao;
    private String data_situacao;
    private String motivo_situacao;
    private String situacao_especial;
    private String data_situacao_especial;
    private String capital_social;
    @JsonProperty("qsa")
    private List<QSA> qsa;
    @JsonIgnore
    private String extra;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAbertura() {
        return abertura;
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public List<AtividadeRF> getAtividade_principal() {
        return atividade_principal;
    }

    public void setAtividade_principal(List<AtividadeRF> atividade_principal) {
        this.atividade_principal = atividade_principal;
    }

    public List<AtividadeRF> getAtividades_secundarias() {
        return atividades_secundarias;
    }

    public void setAtividades_secundarias(List<AtividadeRF> atividades_secundarias) {
        this.atividades_secundarias = atividades_secundarias;
    }

    public String getUltima_atualizacao() {
        return ultima_atualizacao;
    }

    public void setUltima_atualizacao(String ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }

    public String getNatureza_juridica() {
        return natureza_juridica;
    }

    public void setNatureza_juridica(String natureza_juridica) {
        this.natureza_juridica = natureza_juridica;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEfr() {
        return efr;
    }

    public void setEfr(String efr) {
        this.efr = efr;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getData_situacao() {
        return data_situacao;
    }

    public void setData_situacao(String data_situacao) {
        this.data_situacao = data_situacao;
    }

    public String getMotivo_situacao() {
        return motivo_situacao;
    }

    public void setMotivo_situacao(String motivo_situacao) {
        this.motivo_situacao = motivo_situacao;
    }

    public String getSituacao_especial() {
        return situacao_especial;
    }

    public void setSituacao_especial(String situacao_especial) {
        this.situacao_especial = situacao_especial;
    }

    public String getData_situacao_especial() {
        return data_situacao_especial;
    }

    public void setData_situacao_especial(String data_situacao_especial) {
        this.data_situacao_especial = data_situacao_especial;
    }

    public String getCapital_social() {
        return capital_social;
    }

    public void setCapital_social(String capital_social) {
        this.capital_social = capital_social;
    }

    public List<QSA> getQsa() {
        return qsa;
    }

    public void setQsa(List<QSA> qsa) {
        this.qsa = qsa;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "Status = " + status +
                "\nMessage = " + message +
                "\nCnpj = " + cnpj +
                "\nTipo = " + tipo +
                "\nAbertura = " + abertura +
                "\nNome = " + nome +
                "\nFantasia = " + fantasia +
                "\nAtividade Principal=" + atividade_principal +
                "\nAtividades Secundarias=" + atividades_secundarias +
                "\nNatureza Jurídica = " + natureza_juridica +
                "\nUltima Atualização = " + ultima_atualizacao +
                "\nLogradouro = " + logradouro +
                "\nNumero = " + numero +
                "\nComplemento = " + complemento +
                "\nCEP = " + cep +
                "\nBairro = " + bairro +
                "\nMunicipio = " + municipio +
                "\nUF = " + uf +
                "\nEmail = " + email +
                "\nTelefone = " + telefone +
                "\nEFR = " + efr +
                "\nSituacao = " + situacao +
                "\nData Situacao = " + data_situacao +
                "\nMotivo Situacao = " + motivo_situacao +
                "\nSituacao Especial = " + situacao_especial +
                "\nData Situacao Especial = " + data_situacao_especial +
                "\nCapital Social = " + capital_social +
                "\nQSA = " + qsa +
                "\nExtra = " + extra;
    }

    static class AtividadeRF implements Serializable {
        private String code;
        private String text;

        public AtividadeRF() {
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "\n{ code = " + code + ", text = " + text + " }";
        }
    }

    static class QSA implements Serializable {
        private String nome;

        private String qual;
        private String pais_origem;
        private String nome_rep_legal;
        private String qual_rep_legal;

        public QSA() {
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getQual() {
            return qual;
        }

        public void setQual(String qual) {
            this.qual = qual;
        }

        public String getPais_origem() {
            return pais_origem;
        }

        public void setPais_origem(String pais_origem) {
            this.pais_origem = pais_origem;
        }

        public String getNome_rep_legal() {
            return nome_rep_legal;
        }

        public void setNome_rep_legal(String nome_rep_legal) {
            this.nome_rep_legal = nome_rep_legal;
        }

        public String getQual_rep_legal() {
            return qual_rep_legal;
        }

        public void setQual_rep_legal(String qual_rep_legal) {
            this.qual_rep_legal = qual_rep_legal;
        }

        @Override
        public String toString() {
            return "\n{ Nome = " + nome +
                    "\nQual = " + qual +
                    "\nPais de Origem = " + pais_origem +
                    "\nNome Rep Legal = " + nome_rep_legal +
                    "\nQual Rep Legal = " + qual_rep_legal + " }";
        }
    }
    
}
