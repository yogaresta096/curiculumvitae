package com.handayanto.curiculumvitae.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String timestamp; // Waktu saat kesalahan terjadi
    private String error; // Nama kesalahan
    private String message; // Pesan kesalahan
}
