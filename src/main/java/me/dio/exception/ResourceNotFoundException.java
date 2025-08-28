package me.dio.exception;

/**
 * Exceção para recursos não encontrados
 */
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String resource, Long id) {
        super(String.format("%s com ID %d não foi encontrado", resource, id));
    }
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
