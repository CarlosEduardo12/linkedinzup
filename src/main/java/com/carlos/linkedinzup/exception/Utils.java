package com.carlos.linkedinzup.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Utils {

    private String error;
    private List<Campo> extra;

    @Data
    public static class Campo{
        private String campo;
        private String mensagem;

        public Campo(String campo, String mensagem){
            super();
            this.campo = campo;
            this.mensagem = mensagem;
        }
    }

}
