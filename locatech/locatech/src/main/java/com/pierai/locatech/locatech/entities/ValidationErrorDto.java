package com.pierai.locatech.locatech.entities;

import java.util.List;

public record ValidationErrorDto(List<String> errors, int status) {
}
