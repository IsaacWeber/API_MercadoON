package br.com.mercadoon.util;

import java.util.Base64;

public class ImageUtil {

    // Codifica64 para mostrar na tela com Thymeleaf
    public String getImageData(byte[] data) {
        return Base64.getMimeEncoder().encodeToString(data);
    }
}
