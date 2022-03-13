package org.acme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AdicionarRestauranteDTO {

    @NotEmpty
    public String proprietario;

    @NotEmpty
    public String cnpj;

    @Size(min = 3, max = 30)
    public String nome;

    public LocalizacaoDTO localizacao;
}
