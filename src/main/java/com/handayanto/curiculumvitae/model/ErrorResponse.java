package com.handayanto.curiculumvitae.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp; // Waktu saat kesalahan terjadi
    private int status; // Kode status HTTP
    private String error; // Nama kesalahan
    private String message; // Pesan kesalahan
    private String path; // Jalur endpoint yang diakses
}
